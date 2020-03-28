package org.undadhorde

import org.rspeer.runetek.event.listeners.RenderListener
import org.rspeer.runetek.event.listeners.SkillListener
import org.rspeer.runetek.event.types.RenderEvent
import org.rspeer.runetek.event.types.SkillEvent
import org.rspeer.script.ScriptMeta
import org.rspeer.script.task.TaskScript
import org.undadhorde.skilltracker.addSkill
import org.undadhorde.skilltracker.drawSkills
import org.undadhorde.tasks.Banker
import org.undadhorde.tasks.Fighter
import org.undadhorde.tasks.Startup
import org.undadhorde.tasks.Walker
import java.awt.Graphics2D



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
class Main : TaskScript(), RenderListener, SkillListener {

    val startupGUI = GUI()

    override fun notify(renderEvent: RenderEvent) {
        val g2d = renderEvent.getSource() as Graphics2D
        drawSkills(g2d)
    }

    override fun notify(skillEvent: SkillEvent) {
        addSkill(skillEvent)
    }

    val tasks = arrayOf(Startup(), Walker(), Banker(), Fighter())

    override fun onStart() {
        submit(*tasks)
        startupGUI.isVisible = true
    }

    override fun onStop() {
        super.onStop()
        startupGUI.isVisible = false
    }
}