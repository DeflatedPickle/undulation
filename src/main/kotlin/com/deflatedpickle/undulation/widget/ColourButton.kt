/* Copyright (c) 2020 DeflatedPickle under the MIT license */

@file:Suppress("unused")

package com.deflatedpickle.undulation.widget

import org.jdesktop.swingx.JXButton
import org.jdesktop.swingx.painter.CompoundPainter
import org.jdesktop.swingx.painter.MattePainter
import java.awt.Color
import java.awt.Dimension

open class ColourButton(colour: Color) : JXButton() {
    var mattePainter = MattePainter(colour)
    private var compoundPainter = CompoundPainter<JXButton>(mattePainter)

    var colour = colour
        set(value) {
            mattePainter = MattePainter(value)
            compoundPainter = CompoundPainter<JXButton>(mattePainter)
            backgroundPainter = compoundPainter

            field = value
        }

    init {
        backgroundPainter = compoundPainter
        isBorderPainted = false

        preferredSize = Dimension(20, 20)
    }
}
