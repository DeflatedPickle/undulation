/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints
import java.awt.Insets

object StickNorth : GridBagConstraints() {
    init {
        anchor = NORTH
        insets = Insets(2, 2, 2, 2)
    }
}
