package org.undadhorde.tasks

import org.rspeer.runetek.api.commons.math.Random
import org.rspeer.runetek.api.component.tab.Inventory
import org.rspeer.runetek.api.movement.Movement
import org.rspeer.runetek.api.movement.position.Area
import org.rspeer.runetek.api.scene.Players
import org.rspeer.runetek.api.scene.SceneObjects
import org.rspeer.script.task.Task
import org.rspeer.ui.Log
import org.undadhorde.*

data class PhaseRes(val finished: Boolean, val longWait: Boolean)

class WalkPhase(
    val state: BotState,
    val nextState: BotState,
    val target: Area,
    val phaseResFinished: PhaseRes,
    val phaseResOngoing: PhaseRes,
    val executor: () -> Unit,
    val validator: () -> Boolean
) {

    var atTarget = false

    fun reset() {
        atTarget = false
    }

    fun process(): PhaseRes {
        if (!atTarget && target.contains(Players.getLocal())) atTarget = true
        if (atTarget) {
            if (validator()) {
                reset()
                switchState(nextState)
            } else {
                executor()
            }
            return phaseResFinished
        }
        Movement.walkToRandomized(target.center)
        return phaseResOngoing
    }
}

class Walker() : Task() {

    private fun notFinished(): PhaseRes {
        return PhaseRes(finished = false, longWait = false)
    }

    override fun validate(): Boolean {
        if (!listOf(
                BotState.WALKING_TO_BANK,
                BotState.WALKING_TO_CAVE_ENTRANCE,
                BotState.WALKING_TO_FIGHT_SPOT,
                BotState.WALKING_TO_LADDER
            ).contains(currentState)
        ) {
            return false
        }

        return true
    }

    val walkToBank = WalkPhase(
        state = BotState.WALKING_TO_BANK,
        nextState = BotState.BANKING,
        target = BANK_POS,
        phaseResFinished = PhaseRes(
            finished = true,
            longWait = false
        ),
        phaseResOngoing = notFinished(),
        executor = { },
        validator = {
            BANK_POS.contains(Players.getLocal())
        }
    )

    val walkToCaveEntrance = WalkPhase(
        state = BotState.WALKING_TO_CAVE_ENTRANCE,
        nextState = BotState.WALKING_TO_FIGHT_SPOT,
        target = CAVE_ENTRANCE,
        phaseResFinished = PhaseRes(
            finished = true,
            longWait = true
        ),
        phaseResOngoing = notFinished(),
        executor = {
            SceneObjects.getNearest("Trapdoor").interact("Climb-down")
        },
        validator = {
            LADDER_POS.contains(Players.getLocal())
        }
    )

    val walkToFightSpot = WalkPhase(
        state = BotState.WALKING_TO_FIGHT_SPOT,
        nextState = BotState.FIGHTING,
        target = FIGHT_POS,
        phaseResFinished = PhaseRes(
            finished = true,
            longWait = false
        ),
        phaseResOngoing = notFinished(),
        executor = {},
        validator = {
            FIGHT_POS.contains(Players.getLocal())
        }
    )

    val walkToLadder = WalkPhase(
        state = BotState.WALKING_TO_LADDER,
        nextState = BotState.WALKING_TO_BANK,
        target = LADDER_POS,
        phaseResFinished = PhaseRes(
            finished = true,
            longWait = true
        ),
        phaseResOngoing = notFinished(),
        executor = {
            SceneObjects.getNearest("Ladder").interact("Climb-up")
        },
        validator = {
            CAVE_ENTRANCE.contains(Players.getLocal())
        }
    )

    override fun execute(): Int {
        Log.fine(currentState.toString())



        val res = when (currentState) {
            BotState.WALKING_TO_BANK -> walkToBank.process()
            BotState.WALKING_TO_CAVE_ENTRANCE -> walkToCaveEntrance.process()
            BotState.WALKING_TO_FIGHT_SPOT -> walkToFightSpot.process()
            BotState.WALKING_TO_LADDER -> walkToLadder.process()
            else -> notFinished()
        }

        return if (res.longWait) Random.nextInt(5000, 10000) else Random.nextInt(750, 1500)
    }
}