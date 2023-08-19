/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints
import java.awt.Insets

object FillHorizontal : GridBagConstraints() {
    init {
        anchor = CENTER
        fill = HORIZONTAL
        weightx = 1.0
        insets = Insets(2, 2, 2, 2)
    }
}
