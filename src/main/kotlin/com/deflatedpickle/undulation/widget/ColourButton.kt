/* Copyright (c) 2020 DeflatedPickle under the MIT license */

@file:Suppress("unused")

package com.deflatedpickle.undulation.widget

import org.jdesktop.swingx.JXButton
import org.jdesktop.swingx.painter.CompoundPainter
import org.jdesktop.swingx.painter.MattePainter
import java.awt.Color
import java.awt.Dimension

open class ColourButton(var color: Color) : JXButton() {
    val mattePainter = MattePainter(color)
    private val compoundPainter = CompoundPainter<JXButton>(mattePainter)

    init {
        backgroundPainter = compoundPainter
        isBorderPainted = false

        preferredSize = Dimension(20, 20)
    }
}
