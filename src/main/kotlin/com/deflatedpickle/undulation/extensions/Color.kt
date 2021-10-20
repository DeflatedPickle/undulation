package com.deflatedpickle.undulation.extensions

import com.deflatedpickle.marvin.Colour
import java.awt.Color

fun Color.toColour() = Colour(red, green, blue, alpha)