package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints

object FillVerticalStickEast : GridBagConstraints() {
    init {
        anchor = EAST
        fill = VERTICAL
        weighty = 1.0
    }
}
