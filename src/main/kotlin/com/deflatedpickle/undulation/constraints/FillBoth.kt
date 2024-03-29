/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints
import java.awt.Insets

object FillBoth : GridBagConstraints() {
    init {
        anchor = NORTH
        fill = BOTH
        weightx = 1.0
        weighty = 1.0
        insets = Insets(2, 2, 2, 2)
    }
}
