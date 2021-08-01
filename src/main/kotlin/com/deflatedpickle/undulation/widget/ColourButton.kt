/* Copyright (c) 2020 DeflatedPickle under the MIT license */

@file:Suppress("unused")

package com.deflatedpickle.undulation.widget

import com.bric.colorpicker.ColorPickerDialog
import java.awt.Color
import javax.swing.SwingUtilities
import org.jdesktop.swingx.JXButton
import org.jdesktop.swingx.painter.CompoundPainter
import org.jdesktop.swingx.painter.MattePainter

class ColourButton(var color: Color) : JXButton() {
    private val mattePainter = MattePainter(color)
    private val compoundPainter = CompoundPainter<JXButton>(mattePainter)

    init {
        backgroundPainter = compoundPainter

        addActionListener {
            color = ColorPickerDialog.showDialog(
                SwingUtilities.windowForComponent(this),
                color
            )
            mattePainter.fillPaint = color
        }
    }
}
