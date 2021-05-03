package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints

object StickEastFinishLine : GridBagConstraints() {
    init {
        anchor = EAST
        gridwidth = REMAINDER
    }
}