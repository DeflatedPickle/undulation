/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints

object StickEastFinishLine : GridBagConstraints() {
    init {
        anchor = EAST
        gridwidth = REMAINDER
    }
}
