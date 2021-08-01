/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints

object FillHorizontalStickEast : GridBagConstraints() {
    init {
        anchor = EAST
        fill = HORIZONTAL
        weightx = 1.0
    }
}
