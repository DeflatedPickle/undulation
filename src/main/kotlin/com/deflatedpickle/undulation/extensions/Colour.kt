package com.deflatedpickle.undulation.extensions

import com.deflatedpickle.marvin.Colour
import java.awt.Color

fun Colour.toAwt() = Color(r, g, b, a)