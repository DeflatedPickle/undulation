@file:Suppress("unused")

package com.deflatedpickle.undulation.extensions

import java.awt.Dimension
import java.awt.Point

fun Point.toDimension() = Dimension(
    x, y
)

fun Point.toDimension(dimension: Dimension) = dimension.apply {
    width = x
    height = y
}

fun Point.fromDimension(point: Point): Point = this.apply {
    x = point.x
    y = point.y
}

operator fun Point.compareTo(size: Point): Int =
    if (this.x > size.x || this.y > size.y) 1
    else if (this.x < size.x && this.y < size.y) -1
    else 0
