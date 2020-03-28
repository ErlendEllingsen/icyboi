package org.undadhorde.skilltracker


import org.rspeer.runetek.api.component.tab.Skill

import java.awt.*
import java.util.Collections
import java.util.HashMap

internal object ColorHelper {
    val FOREGROUND_COLOUR_MAP = createForegroundMap()

    val BACKGROUND_COLOUR_MAP = createBackgroundMap()

    private fun createForegroundMap(): Map<Skill, Color> {
        val result = HashMap<Skill, Color>()
        result[Skill.ATTACK] = Color(155, 32, 7)
        result[Skill.DEFENCE] = Color(98, 119, 190)
        result[Skill.STRENGTH] = Color(4, 149, 90)
        result[Skill.HITPOINTS] = Color(131, 126, 126)
        result[Skill.RANGED] = Color(109, 144, 23)
        result[Skill.PRAYER] = Color(159, 147, 35)
        result[Skill.MAGIC] = Color(50, 80, 193)
        result[Skill.COOKING] = Color(112, 35, 134)
        result[Skill.WOODCUTTING] = Color(52, 140, 37)
        result[Skill.FLETCHING] = Color(3, 141, 125)
        result[Skill.FISHING] = Color(106, 132, 164)
        result[Skill.FIREMAKING] = Color(189, 120, 25)
        result[Skill.CRAFTING] = Color(151, 110, 77)
        result[Skill.SMITHING] = Color(108, 107, 82)
        result[Skill.MINING] = Color(93, 143, 167)
        result[Skill.HERBLORE] = Color(7, 133, 9)
        result[Skill.AGILITY] = Color(58, 60, 137)
        result[Skill.THIEVING] = Color(108, 52, 87)
        result[Skill.SLAYER] = Color(100, 100, 100)
        result[Skill.FARMING] = Color(101, 152, 63)
        result[Skill.RUNECRAFTING] = Color(170, 141, 26)
        result[Skill.HUNTER] = Color(92, 89, 65)
        result[Skill.CONSTRUCTION] = Color(130, 116, 95)
        return Collections.unmodifiableMap(result)
    }

    private fun createBackgroundMap(): Map<Skill, Color> {
        val result = HashMap<Skill, Color>()
        result[Skill.ATTACK] = Color(94, 0, 0)
        result[Skill.DEFENCE] = Color(41, 71, 136)
        result[Skill.STRENGTH] = Color(0, 97, 43)
        result[Skill.HITPOINTS] = Color(131, 126, 126)
        result[Skill.RANGED] = Color(56, 93, 0)
        result[Skill.PRAYER] = Color(159, 147, 35)
        result[Skill.MAGIC] = Color(104, 97, 0)
        result[Skill.COOKING] = Color(60, 0, 83)
        result[Skill.WOODCUTTING] = Color(0, 89, 0)
        result[Skill.FLETCHING] = Color(0, 90, 76)
        result[Skill.FISHING] = Color(56, 83, 112)
        result[Skill.FIREMAKING] = Color(130, 71, 0)
        result[Skill.CRAFTING] = Color(98, 62, 32)
        result[Skill.SMITHING] = Color(60, 60, 37)
        result[Skill.MINING] = Color(39, 93, 115)
        result[Skill.HERBLORE] = Color(0, 82, 0)
        result[Skill.AGILITY] = Color(0, 18, 86)
        result[Skill.THIEVING] = Color(58, 4, 42)
        result[Skill.SLAYER] = Color(53, 53, 53)
        result[Skill.FARMING] = Color(49, 101, 10)
        result[Skill.RUNECRAFTING] = Color(113, 91, 0)
        result[Skill.HUNTER] = Color(46, 44, 22)
        result[Skill.CONSTRUCTION] = Color(80, 68, 49)
        return Collections.unmodifiableMap(result)
    }
}
