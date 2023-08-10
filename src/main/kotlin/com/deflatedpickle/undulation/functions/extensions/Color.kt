package com.deflatedpickle.undulation.functions.extensions

import java.awt.Color

fun Color.getContrast(): Color {
    val y = ((299 * red + 587 * green + 114 * blue) / 1000).toDouble()
    return if (y >= 128) Color.black else Color.white
}

fun Color.getComplementary() = Color(rgb.xor(0x00ffffff))