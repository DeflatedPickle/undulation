package com.deflatedpickle.undulation.functions

import com.deflatedpickle.undulation.widget.ColourButton
import java.awt.Color
import java.awt.event.ActionEvent

fun ColourButton(colour: Color, tooltip: String? = null, function: (ActionEvent) -> Unit) =
    ColourButton(colour).apply {
        toolTipText = tooltip
        addActionListener {
            function(it)
        }
    }