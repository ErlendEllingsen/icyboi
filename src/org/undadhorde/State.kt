package org.undadhorde

import org.rspeer.runetek.api.movement.Movement
import org.rspeer.ui.Log
import org.undadhorde.tasks.BankingState
import org.undadhorde.tasks.currentBankingState

enum class BotState {
    INIT,
    IDLE,
    BUYING,
    BANKING,
    WALKING_TO_CAVE_ENTRANCE,
    WALKING_TO_FIGHT_SPOT,
    FIGHTING,
    WALKING_TO_LADDER,
    WALKING_TO_BANK,
}

var currentState = BotState.INIT

fun switchState(newState: BotState) {
    Log.fine("STATE", "New states " + newState.toString())

    if (newState === BotState.BANKING) {
        // Reset banking state
        currentBankingState = BankingState.INIT
    }
//    else if (newState === BotState.WALKING_TO_LADDER) {
//        Movement.toggleRun(true)
//    }

    currentState = newState
}