package com.deflatedpickle.undulation.widget

import com.bric.colorpicker.ColorPickerDialog
import java.awt.Color
import javax.swing.SwingUtilities
import javax.swing.event.ChangeEvent

class ColourSelectButton(
    colour: Color,
) : ColourButton(colour) {
    init {
        addActionListener {
            ColorPickerDialog.showDialog(
                SwingUtilities.windowForComponent(this),
                color,
            )?.let {
                color = it
                mattePainter.fillPaint = it
            }

            changeListeners.forEach { it.stateChanged(ChangeEvent(this)) }
        }
    }
}