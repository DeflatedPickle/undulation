package com.deflatedpickle.undulation.functions.extensions

import java.awt.Dimension

infix fun Int.x(other: Int) = Dimension(this, other)