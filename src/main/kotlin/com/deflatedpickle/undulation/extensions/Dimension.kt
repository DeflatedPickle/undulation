/* Copyright (c) 2020 DeflatedPickle under the MIT license */

@file:Suppress("unused")

package com.deflatedpickle.undulation.extensions

import java.awt.Dimension
import java.awt.Point

fun Dimension.toPoint() = Point(
    width, height
)

fun Dimension.toPoint(point: Point) = point.apply {
    x = width
    y = height
}

fun Dimension.fromPoint(point: Point): Dimension = this.apply {
    width = point.x
    height = point.y
}

operator fun Dimension.compareTo(size: Dimension): Int =
    if (this.width > size.width || this.height > size.height) 1
    else if (this.width < size.width && this.height < size.height) -1
    else 0

operator fun Dimension.div(value: Int) = Dimension(
    this.width / value,
    this.height / value
)
