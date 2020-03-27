package org.undadhorde.tasks

import org.rspeer.runetek.api.commons.math.Random
import org.rspeer.runetek.api.component.tab.Inventory
import org.rspeer.runetek.api.movement.Movement
import org.rspeer.runetek.api.scene.Players
import org.rspeer.runetek.api.scene.SceneObjects
import org.rspeer.script.task.Task
import org.rspeer.ui.Log
import org.undadhorde.*

//import org.undadhorde.BANK_AREA

class Walker(): Task() {
    override fun validate(): Boolean {
        if (!listOf(
                BotState.WALKING_TO_BANK,
                BotState.WALKING_TO_CAVE_ENTRANCE,
                BotState.WALKING_TO_FIGHT_SPOT,
                BotState.WALKING_TO_LADDER).contains(currentState)) {
            return false
        }

        return true
    }

    fun walkToBank(): Boolean {
        if (BANK_POS.contains(Players.getLocal())) {
            switchState(BotState.BANKING)
            return true
        }
        Movement.walkToRandomized(BANK_POS.center)
        return false
    }

    fun walkToCaveEntrance(): Boolean {
        if (CAVE_ENTRANCE.contains(Players.getLocal())) {
            SceneObjects.getNearest("Trapdoor").interact("Climb-down")
            switchState(BotState.WALKING_TO_FIGHT_SPOT)
            return true
        }
        Movement.walkToRandomized(CAVE_ENTRANCE.center)
        return false
    }

    fun walkToFightSpot(): Boolean {
        if (FIGHT_POS.contains(Players.getLocal())) {
            switchState(BotState.FIGHTING)
            return true
        }
        Movement.walkToRandomized(FIGHT_POS.center)
        return false
    }

    fun walkToLadder(): Boolean {
        if (LADDER_POS.contains(Players.getLocal())) {
            SceneObjects.getNearest("Ladder").interact("Climb-up")
            switchState(BotState.WALKING_TO_BANK)
            return true
        }
        Movement.walkToRandomized(LADDER_POS.center)
        return false
    }

    override fun execute(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

//        Log.fine("Hello boo")

            Log.fine(currentState.toString())

            val res = when (currentState) {
                BotState.WALKING_TO_BANK -> walkToBank()
                BotState.WALKING_TO_CAVE_ENTRANCE -> walkToCaveEntrance()
                BotState.WALKING_TO_FIGHT_SPOT -> walkToFightSpot()
                BotState.WALKING_TO_LADDER -> walkToLadder()
                else -> false
            }

        return if (res) Random.nextInt(5000,10000) else Random.nextInt(2500,5000)

//        val plr = Players.getLocal()
//        if (BANK_AREA.contains(plr)) {
//            Movement.walkTo(CAVE_ENTRANCE.center)
//        } else if (CAVE_ENTRANCE.contains(plr)) {
//            Movem
//        }

//        Movement.walkTo(CAVE_ENTRANCE.center)

    }
}