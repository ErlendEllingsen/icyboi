package org.undadhorde.tasks

import org.rspeer.runetek.api.commons.math.Distance
import org.rspeer.runetek.api.commons.math.Random
import org.rspeer.runetek.api.component.Bank
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