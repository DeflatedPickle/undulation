package com.deflatedpickle.undulation.functions

import com.deflatedpickle.undulation.widget.ColourButton
import java.awt.Color

fun ColourButton(colour: Color, tooltip: String? = null, function: (ColourButton) -> Unit) =
    ColourButton(colour).apply {
        toolTipText = tooltip
        addActionListener {
            function(this)
        }
    }