package org.undadhorde

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


        val box = JComboBox<BotState>(BotState.values())
        panel.add(box)

        val changeBtn = JButton("Set state")
        panel.add(changeBtn)

        changeBtn.addActionListener { el ->
            switchState(box.getItemAt(box.selectedIndex))
        }

        defaultCloseOperation = WindowConstants.HIDE_ON_CLOSE
        add(panel)
        pack()
    }

}