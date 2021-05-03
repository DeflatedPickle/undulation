package com.deflatedpickle.undulation.constraints

import java.awt.GridBagConstraints

object StickWestFinishLine : GridBagConstraints() {
    init {
        anchor = WEST
        gridwidth = REMAINDER
    }
}