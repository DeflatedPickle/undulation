package com.deflatedpickle.undulation.widget

import com.java2s.ComponentPanel
import javax.swing.JToggleButton
import org.jdesktop.swingx.JXCollapsiblePane
import org.jdesktop.swingx.JXLabel
import org.jdesktop.swingx.JXPanel

class CollapsiblePanel(text: String) : ComponentPanel() {
    val collapse = JXCollapsiblePane().apply {
        isCollapsed = true
    }

    init {
        this.titleComponent = JXPanel().apply {
            add(JXLabel(text.capitalize()).apply {
                font = font.deriveFont(16f)

            })
            add(JToggleButton(collapse.actionMap[JXCollapsiblePane.TOGGLE_ACTION]).apply {
                setText("Open")
            })
        }
        panel.add(collapse)
    }
}