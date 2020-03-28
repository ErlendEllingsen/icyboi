package org.undadhorde.tasks

import org.rspeer.runetek.api.commons.math.Distance
import org.rspeer.runetek.api.commons.math.Random
import org.rspeer.runetek.api.component.Bank
import org.rspeer.runetek.api.component.tab.Inventory
import org.rspeer.runetek.api.scene.Players
import org.rspeer.script.task.Task
import org.rspeer.ui.Log
import org.undadhorde.*

class Startup(): Task() {

    override fun validate(): Boolean {
        return currentState === BotState.INIT
    }

    override fun execute(): Int {

        if (Players.getLocal() === null) {
            Log.info("Not fetching player, assuming not logged in")
            return shortWait()
        }

        // CHECK 1: RESUME FIGHT - Check if in fighting spot AND got food.
        if (Inventory.getCount(activeFood.foodName)>4 && Distance.between(FIGHT_POS.center, Players.getLocal()) < 15.0) {
            switchState(BotState.FIGHTING)
            return shortWait()
        }

        // CHECK 2: Running pos
        // Check which position is closer, if closest to fight pos, assume we're in the dungeon
        val plr = Players.getLocal()
        val distFightPos = Distance.between(plr, FIGHT_POS.center)
        val distBankPos = Distance.between(plr, BANK_POS.center)

        if (distFightPos < distBankPos) {
            // assume in cave
            switchState(BotState.WALKING_TO_LADDER)
        } else {
            switchState(BotState.WALKING_TO_BANK)
        }

        return shortWait()
    }
}