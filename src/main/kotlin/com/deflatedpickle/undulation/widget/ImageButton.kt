/* Copyright (c) 2020 DeflatedPickle under the MIT license */

@file:Suppress("unused")

package com.deflatedpickle.undulation.widget

import org.jdesktop.swingx.JXButton
import java.awt.Image
import javax.swing.BorderFactory
import javax.swing.ImageIcon

class ImageButton(image: Image) : JXButton(ImageIcon(image)) {
    init {
        border = BorderFactory.createEmptyBorder()
        isContentAreaFilled = false
    }
}
