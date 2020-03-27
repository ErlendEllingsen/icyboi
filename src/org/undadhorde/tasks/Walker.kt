package org.undadhorde.tasks

import org.rspeer.runetek.api.commons.math.Random
import org.rspeer.runetek.api.component.tab.Inventory
import org.rspeer.runetek.api.movement.Movement
import org.rspeer.runetek.api.scene.Players
import org.rspeer.runetek.api.scene.SceneObjects
import org.rspeer.script.task.Task
import org.rspeer.ui.Log
import org.undadhorde.*

data class PhaseRes(val finished: Boolean, val longWait: Boolean)


class Walker(): Task() {

    private fun notFinished(): PhaseRes {
        return PhaseRes(finished = false, longWait = false)
    }

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

    fun walkToBank(): PhaseRes {
        if (BANK_POS.contains(Players.getLocal())) {
            switchState(BotState.BANKING)
            return PhaseRes(finished = true, longWait = false)
        }
        Movement.walkToRandomized(BANK_POS.center)
        return notFinished()
    }

    fun walkToCaveEntrance(): PhaseRes {
        if (CAVE_ENTRANCE.contains(Players.getLocal())) {
            SceneObjects.getNearest("Trapdoor").interact("Climb-down")
            switchState(BotState.WALKING_TO_FIGHT_SPOT)
            return PhaseRes(finished = true, longWait = true)
        }
        Movement.walkToRandomized(CAVE_ENTRANCE.center)
        return notFinished()
    }

    fun walkToFightSpot(): PhaseRes {
        if (FIGHT_POS.contains(Players.getLocal())) {
            switchState(BotState.FIGHTING)
            return PhaseRes(finished = true, longWait = false)
        }
        Movement.walkToRandomized(FIGHT_POS.center)
        return notFinished()
    }

    fun walkToLadder(): PhaseRes {
        if (LADDER_POS.contains(Players.getLocal())) {
            SceneObjects.getNearest("Ladder").interact("Climb-up")
            switchState(BotState.WALKING_TO_BANK)
            return PhaseRes(finished = true, longWait = true)
        }
        Movement.walkToRandomized(LADDER_POS.center)
        return notFinished()
    }

    override fun execute(): Int {
            Log.fine(currentState.toString())

            val res = when (currentState) {
                BotState.WALKING_TO_BANK -> walkToBank()
                BotState.WALKING_TO_CAVE_ENTRANCE -> walkToCaveEntrance()
                BotState.WALKING_TO_FIGHT_SPOT -> walkToFightSpot()
                BotState.WALKING_TO_LADDER -> walkToLadder()
                else -> notFinished()
            }

        return if (res.longWait) Random.nextInt(5000,10000) else Random.nextInt(750,1500)
    }
}