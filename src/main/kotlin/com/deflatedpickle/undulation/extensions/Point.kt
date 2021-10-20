/* Copyright (c) 2021 DeflatedPickle under the MIT license */

@file:Suppress("unused")

package com.deflatedpickle.undulation.extensions

import java.awt.Dimension
import java.awt.Point
import kotlin.math.abs

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

operator fun Point.unaryPlus() = Point(abs(x), abs(y))
operator fun Point.unaryMinus() = Point(x * -1, y * -1)

operator fun Point.minus(value: Point) = Point(x - value.x, y - value.y)
operator fun Point.minus(value: Int) = Point(x - value, y - value)

operator fun Point.div(value: Int) = Point(x / value, y / value)
