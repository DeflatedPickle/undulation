/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.widget

import com.deflatedpickle.undulation.constraints.FillBothFinishLine
import com.java2s.ComponentPanel
import javax.swing.JToggleButton
import org.jdesktop.swingx.JXCollapsiblePane
import org.jdesktop.swingx.JXLabel
import org.jdesktop.swingx.JXPanel
import java.awt.BorderLayout
import java.awt.GridBagLayout

class CollapsiblePanel(text: String) : ComponentPanel() {
    val collapse = JXCollapsiblePane().apply {
        layout = GridBagLayout()
        isCollapsed = true
    }

    init {
        layout = BorderLayout()

        titleComponent = JXPanel().apply {
            add(JXLabel(text.capitalize()).apply {
                font = font.deriveFont(16f)
            })
            add(JToggleButton(collapse.actionMap[JXCollapsiblePane.TOGGLE_ACTION]).apply {
                setText("Open")
            })
        }

        panel.add(collapse, FillBothFinishLine)
    }
}
