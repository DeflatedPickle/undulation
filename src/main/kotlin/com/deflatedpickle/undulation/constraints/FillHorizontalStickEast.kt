package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints

object FillHorizontalStickEast : GridBagConstraints() {
    init {
        anchor = EAST
        fill = HORIZONTAL
        weightx = 1.0
    }
}