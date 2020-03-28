package org.undadhorde.tasks

import org.rspeer.runetek.api.commons.math.Random
import org.rspeer.runetek.api.component.Bank
import org.rspeer.runetek.api.component.tab.Inventory
import org.rspeer.runetek.api.scene.Players
import org.rspeer.script.task.Task
import org.undadhorde.*

enum class BankingState {
    INIT,
    DEPOSITING_ITEMS,
    WITHDRAWING_FOOD,
    DONE
}

var currentBankingState = BankingState.INIT

class Banker(): Task() {

    override fun validate(): Boolean {
        return currentState === BotState.BANKING
    }

    override fun execute(): Int {

        if (!Bank.isOpen()) {
            Bank.open()
            currentBankingState = if (!Inventory.isEmpty()) BankingState.DEPOSITING_ITEMS else BankingState.WITHDRAWING_FOOD
            return shortWait()
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