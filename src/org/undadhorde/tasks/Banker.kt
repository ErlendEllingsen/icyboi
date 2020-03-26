package org.undadhorde.tasks

import org.rspeer.runetek.api.commons.math.Random
import org.rspeer.runetek.api.component.Bank
import org.rspeer.script.task.Task
import org.undadhorde.BotState
import org.undadhorde.currentState
import org.undadhorde.switchState

enum class BankingState {
    INIT,
    DEPOSITING_ITEMS,
    WITHDRAWING_FOOD,
    DONE
}

var currentBankingState = BankingState.INIT

class Banker(): Task() {

    private fun longWait(): Int {
        return Random.nextInt(5000,8500)
    }

    private fun shortWait(): Int {
        return Random.nextInt(2000,4500)
    }

    override fun validate(): Boolean {
        return currentState === BotState.BANKING
    }

    override fun execute(): Int {

        if (!Bank.isOpen()) {
            Bank.open();
            currentBankingState = BankingState.DEPOSITING_ITEMS
            return longWait()
        }

        if (currentBankingState == BankingState.DEPOSITING_ITEMS) {
            Bank.depositAll { true }
            currentBankingState = BankingState.WITHDRAWING_FOOD
            return shortWait()
        } else if (currentBankingState == BankingState.WITHDRAWING_FOOD) {
            Bank.withdraw("Shark", 28)
            currentBankingState = BankingState.DONE
            switchState(BotState.WALKING_TO_CAVE_ENTRANCE)
            return shortWait()
        }

        return shortWait()
    }
}