/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.widget.taginput

import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.event.ChangeEvent
import org.jdesktop.swingx.JXButton
import org.jdesktop.swingx.JXLabel

class TagComponent(text: String, input: TagInput) : JPanel() {
    private val label = JXLabel(text)
    private val close = JXButton("X")

    init {
        layout = BorderLayout()

        add(close, BorderLayout.EAST)
        add(label, BorderLayout.WEST)

        label.isOpaque = true
        close.isOpaque = true

        close.addActionListener {
            input.tags.remove(label.text)
            input.tagArea.apply {
                remove(this@TagComponent)
                input.fireChangedUpdate(ChangeEvent(this))
                repaint()
                revalidate()
            }
        }
    }
}
