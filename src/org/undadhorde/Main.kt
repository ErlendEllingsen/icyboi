package org.undadhorde

import org.rspeer.runetek.api.commons.Time
import org.rspeer.runetek.api.component.Bank
import org.rspeer.runetek.api.component.GrandExchange
import org.rspeer.runetek.api.component.GrandExchangeSetup
import org.rspeer.runetek.api.movement.position.Area
import org.rspeer.runetek.api.movement.position.Position
import org.rspeer.runetek.api.query.GrandExchangeOfferQueryBuilder
import org.rspeer.runetek.api.scene.Players
import org.rspeer.runetek.api.scene.SceneObjects
import org.rspeer.runetek.providers.RSGrandExchangeOffer
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta
import org.rspeer.script.task.Task
import org.rspeer.script.task.TaskScript
import org.rspeer.ui.Log
import org.undadhorde.tasks.Banker
import org.undadhorde.tasks.Fighter
import org.undadhorde.tasks.Startup
import org.undadhorde.tasks.Walker
import kotlin.random.Random

fun longWait(): Int {
    return org.rspeer.runetek.api.commons.math.Random.nextInt(5000,8500)
}

fun shortWait(): Int {
    return org.rspeer.runetek.api.commons.math.Random.nextInt(2000,4500)
}

fun shorterWait(): Int {
    return org.rspeer.runetek.api.commons.math.Random.nextInt(1000,2500)
}


@ScriptMeta(developer = "undadhorde", name="Icyboi", desc="Runs and fights:)")
class Main : TaskScript() {

    val tasks = arrayOf(Startup(), Walker(), Banker(), Fighter())

    override fun onStart() {
        submit(*tasks)
        GUI().isVisible = true
    }

    override fun onStop() {
        super.onStop()
    }
}