/* Copyright (c) 2020 DeflatedPickle under the MIT license */

@file:Suppress("unused")

package com.deflatedpickle.undulation.widget

import javax.swing.JSlider

class DoubleSlider(
    min: Double,
    max: Double,
    _value: Double,
    private val factor: Double
) : JSlider(
    (min * factor).toInt(),
    (max * factor).toInt(),
    (_value * factor).toInt()
) {
    var minimum: Double = min
        get() = field / this.factor
        set(value) {
            super.setMinimum(value.toInt())
            field = value * this.factor
        }

    var maximum: Double = max
        get() = field / this.factor
        set(value) {
            super.setMaximum(value.toInt())
            field = value * this.factor
        }

    var value: Double = _value
        get() = field / this.factor
        set(value) {
            super.setValue((value / this.factor).toInt())
            field = value * this.factor
        }
}
