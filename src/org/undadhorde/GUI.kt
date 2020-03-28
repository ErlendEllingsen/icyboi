package org.undadhorde

import org.rspeer.ui.Log
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JFrame
import javax.swing.WindowConstants
import javax.swing.BoxLayout
import javax.swing.JPanel



class GUI : JFrame() {
    init {
        title = "icyboi"

        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        // Botstate
        val botState = JComboBox(BotState.values())
        panel.add(botState)

        val changeBtn = JButton("Set state")
        panel.add(changeBtn)

        changeBtn.addActionListener { el ->
            switchState(botState.getItemAt(botState.selectedIndex))
        }

        // Food
        val foodSelector = JComboBox(UserFood.values())
        foodSelector.selectedItem = activeFood
        panel.add(foodSelector)

        val changeFoodBtn = JButton("Set food")
        panel.add(changeFoodBtn)

        changeFoodBtn.addActionListener { el ->
            activeFood = foodSelector.getItemAt(foodSelector.selectedIndex)
            Log.fine("Set food to " + activeFood.foodName)
        }

        defaultCloseOperation = WindowConstants.HIDE_ON_CLOSE
        add(panel)
        pack()
    }

}