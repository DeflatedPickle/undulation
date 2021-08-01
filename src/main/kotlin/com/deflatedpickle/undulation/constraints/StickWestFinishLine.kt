/* Copyright (c) 2021 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints

object StickWestFinishLine : GridBagConstraints() {
    init {
        anchor = WEST
        gridwidth = REMAINDER
    }
}
