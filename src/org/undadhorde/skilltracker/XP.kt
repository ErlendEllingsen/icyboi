package org.undadhorde.skilltracker


import org.rspeer.runetek.api.component.tab.Skill
import org.rspeer.runetek.api.component.tab.Skills

import java.awt.*

class XP(val skill: Skill) {
    private val startXp: Int
    private val startTime: Long
    private val startLvl: Int

    init {
        startXp = Skills.getExperience(skill)
        startTime = System.currentTimeMillis()
        startLvl = Skills.getLevel(skill)
    }

    fun xpHour(): String {
        val upTime = System.currentTimeMillis() - startTime
        val gainedXp = (Skills.getExperience(skill) - startXp).toDouble()
        return String.format("%.1f", gainedXp * 3600000 / upTime / 1000) + "k/h"
    }

    fun xpToLvl(): Double {
        return Skills.getExperienceToNextLevel(skill).toDouble() / 1000
    }

    fun gainedXp(): Double {
        return (Skills.getExperience(skill).toDouble() - startXp) / 1000
    }

    fun gainedLvl(): Int {
        val currentSkillLevel = Skills.getLevel(skill)
        return currentSkillLevel - startLvl
    }

    fun percentToLvl(): Long {

        val currentXp = Skills.getExperience(skill).toLong()

        val currentSkillLvl = Skills.getLevel(skill)

        val xpNextLvl = Skills.getExperienceAt(currentSkillLvl).toLong()

        val xpAtLvl = Skills.getExperienceAt(currentSkillLvl + 1).toLong()

        return (xpNextLvl - currentXp) * 100 / (xpNextLvl - xpAtLvl)

    }

    fun percentThroughLevel(): Long {

        val currentXp = Skills.getExperience(skill).toLong()

        val currentSkillLvl = Skills.getLevel(skill)

        val xpNextLvl = Skills.getExperienceAt(currentSkillLvl).toLong()

        val xpAtLvl = Skills.getExperienceAt(currentSkillLvl + 1).toLong()

        return 100 - (xpNextLvl - currentXp) * 100 / (xpNextLvl - xpAtLvl)

    }
}
