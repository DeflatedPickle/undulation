/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints
import java.awt.Insets

object FillVerticalStickEast : GridBagConstraints() {
    init {
        anchor = EAST
        fill = VERTICAL
        weighty = 1.0
        insets = Insets(2, 2, 2, 2)
    }
}
