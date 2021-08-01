/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints
import java.awt.Insets

object FillBoth : GridBagConstraints() {
    init {
        anchor = NORTH
        fill = BOTH
        insets = Insets(2, 2, 2, 2)
    }
}
