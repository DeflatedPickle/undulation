/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints
import java.awt.Insets

object FillBothFinishLine : GridBagConstraints() {
    init {
        anchor = CENTER
        fill = BOTH
        weightx = 1.0
        weighty = 1.0
        gridwidth = REMAINDER
        insets = Insets(2, 2, 2, 2)
    }
}
