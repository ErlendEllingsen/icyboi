package org.undadhorde.tasks

import org.rspeer.runetek.adapter.component.Item
import org.rspeer.runetek.adapter.scene.Npc
import org.rspeer.runetek.api.commons.math.Random
import org.rspeer.runetek.api.component.tab.Inventory
import org.rspeer.runetek.api.movement.Movement
import org.rspeer.runetek.api.scene.Npcs
import org.rspeer.runetek.api.scene.Players
import org.rspeer.script.task.Task
import org.undadhorde.*
import java.util.function.Predicate

class Fighter() : Task() {
    override fun validate(): Boolean {
        return currentState === BotState.FIGHTING
    }

    val IN_COMBAT_TARGET = Predicate { t: Npc ->
        t.target == Players.getLocal()
                && t.healthPercent > 0
                && t.targetIndex != -1
                && Movement.isInteractable(t.position)
    }

    val NOT_IN_COMBAT_TARGET = Predicate { t: Npc ->
        t.targetIndex == -1
                && t.name == "Ice giant"
                && t.healthPercent > 0
                && t.getHealthBar() == null
                && Movement.isInteractable(t.position)
    }

    override fun execute(): Int {

        // Check if currently fighting
        var currentFight = Npcs.getNearest(IN_COMBAT_TARGET)
        if (currentFight === null) currentFight = Npcs.getNearest(NOT_IN_COMBAT_TARGET)

        // Verify hp, eat or flight
        if (Players.getLocal().healthPercent < Random.nextInt(30,40)) {
            if (!Inventory.contains("Shark")) {
                switchState(BotState.WALKING_TO_LADDER)
                return shorterWait()
            } else {
                Inventory.getFirst {i -> i.name == "Shark"}.interact("Eat")
                return shortWait()
            }
        }

        // prepare attack
        if (!Players.getLocal().isAnimating) {
            currentFight.interact("Attack")
            return shorterWait()
        }

        return shorterWait()
    }
}