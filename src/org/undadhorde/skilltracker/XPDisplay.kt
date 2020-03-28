package org.undadhorde.skilltracker


import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.event.types.SkillEvent;

import java.awt.*;
import java.util.*;
import java.util.List;


val BOTTOM_Y = 325;
val skillList: kotlin.collections.MutableList<XP> = mutableListOf()

fun addSkill(skillEvent: SkillEvent) {
    if (skillEvent.type == SkillEvent.TYPE_EXPERIENCE && skillList.stream().noneMatch { e ->
            e.skill == skillEvent.source
        }) {
        skillList.add(XP(skillEvent.source));
    }
}

fun drawSkills(g2d: Graphics2D) {

    g2d.color = Color.yellow
    g2d.drawString("PRIVATE SCRIPT 'Icyboi' by Erlend/Undadhorde", 5, BOTTOM_Y - (skillList.size)*15)

    var i = -1
    for (experience in skillList) {
        i++
        val skill = experience.skill
        val x = 5;
        val y = BOTTOM_Y - (i * 15);
        val width = 350;
        g2d.color = (ColorHelper.FOREGROUND_COLOUR_MAP.get(key = skill));
        g2d.fillRect(x, y + 2, width, 15);
        val percentageWidth = (((100.0 - experience.percentToLvl()) / 100.0) * width);
        g2d.setColor(ColorHelper.BACKGROUND_COLOUR_MAP.get(skill));
        g2d.fillRect((x + (width - percentageWidth)).toInt(), y + 2, percentageWidth.toInt(), 15);

        g2d.setColor(Color.darkGray);
        g2d.drawRect(x, y + 2, width, 15);

        g2d.setColor(Color.black);
        val myFont = Font("Helvetica", Font.PLAIN, 12);
        g2d.setFont(myFont);
        val displayText = String.format(
            "%s (Lvl. %d - %d%%) | %.1f k gained | %.1f k rem. | %s",
            skill.toString(),
            Skills.getLevel(skill),
            experience.percentToLvl(),
            experience.gainedXp(),
            experience.xpToLvl(),
            experience.xpHour()
        );
        g2d.drawString(displayText, x + 2, y + 14);
    }
}

