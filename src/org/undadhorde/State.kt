package org.undadhorde

import org.rspeer.ui.Log

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

var currentState = BotState.WALKING_TO_CAVE_ENTRANCE

fun switchState(newState: BotState) {
    Log.fine("STATE", "New state " + newState.toString())
    currentState = newState
}