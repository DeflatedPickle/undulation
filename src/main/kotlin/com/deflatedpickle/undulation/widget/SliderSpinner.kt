/* Copyright (c) 2021 DeflatedPickle under the MIT license */

@file:Suppress("unused", "FunctionName")

package com.deflatedpickle.undulation.widget

import com.deflatedpickle.undulation.constraints.FillHorizontal
import com.deflatedpickle.undulation.constraints.FillHorizontalFinishLine
import java.awt.GridBagLayout
import javax.swing.JSlider
import javax.swing.JSpinner
import javax.swing.SpinnerNumberModel
import javax.swing.event.ChangeEvent
import kotlin.math.roundToInt
import kotlin.reflect.KClassifier
import kotlin.reflect.typeOf
import org.jdesktop.swingx.JXPanel

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T : Number> SliderSpinner(
    value: T,
    minValue: T,
    maxValue: T
) = ActualSliderSpinner(typeOf<T>().classifier!!, value, minValue, maxValue)

class ActualSliderSpinner<T : Number>(
    clazz: KClassifier,
    value: T,
    minValue: T,
    maxValue: T
) : JXPanel() {
    private val slider = when (clazz) {
        Int::class -> JSlider(
            minValue.toInt(),
            maxValue.toInt(),
            value.toInt()
        )
        Double::class -> DoubleSlider(
            minValue.toDouble(),
            maxValue.toDouble(),
            value.toDouble(),
            100.0
        )
        Float::class -> DoubleSlider(
            (minValue.toFloat()).toDouble(),
            (maxValue.toFloat()).toDouble(),
            (value.toFloat()).toDouble(),
            100.0
        )
        else -> throw IllegalArgumentException()
    }

    private val spinner = JSpinner(
        when (clazz) {
            Int::class -> SpinnerNumberModel(
                value.toInt(),
                minValue.toInt(),
                maxValue.toInt(),
                1
            )
            Double::class -> SpinnerNumberModel(
                value.toDouble(),
                minValue.toDouble(),
                maxValue.toDouble(),
                0.01
            )
            Float::class -> SpinnerNumberModel(
                value.toFloat(),
                minValue.toFloat(),
                maxValue.toFloat(),
                0.01f
            )
            else -> throw IllegalArgumentException()
        }
    )

    var value = value
        set(value) {
            slider.value = when (value) {
                is Int -> value
                is Double -> (value.toDouble() * 100).toInt()
                is Float -> (value.toFloat() * 100).toInt()
                else -> throw IllegalArgumentException()
            }
            (spinner.model as SpinnerNumberModel).value = value
            field = value
        }

    var minValue = minValue
        set(value) {
            slider.minimum = when (value) {
                is Int -> value
                is Double -> (value.toDouble() * 100).toInt()
                is Float -> (value.toFloat() * 100).toInt()
                else -> throw IllegalArgumentException()
            }
            (spinner.model as SpinnerNumberModel).minimum = value as Comparable<Number>
            field = value
        }

    var maxValue = value
        set(value) {
            slider.maximum = when (value) {
                is Int -> value
                is Double -> (value.toDouble() * 100).toInt()
                is Float -> (value.toFloat() * 100).toInt()
                else -> throw IllegalArgumentException()
            }
            (spinner.model as SpinnerNumberModel).maximum = value as Comparable<Number>
            field = value
        }

    init {
        layout = GridBagLayout()

        add(slider, FillHorizontal)
        add(spinner, FillHorizontalFinishLine)

        this.minValue = (spinner.model as SpinnerNumberModel).minimum as T
        this.maxValue = (spinner.model as SpinnerNumberModel).maximum as T

        slider.addChangeListener {
            spinner.value = when (clazz) {
                Int::class -> slider.value
                Double::class -> slider.value.toDouble() / 100
                Float::class -> slider.value.toFloat() / 100
                else -> throw IllegalArgumentException()
            }
            this.value = slider.value as T
        }

        spinner.addChangeListener {
            slider.value = when (clazz) {
                Int::class -> when (spinner.value) {
                    is Int -> spinner.value as Int
                    is Double -> (spinner.value as Double).roundToInt()
                    is Float -> (spinner.value as Float).roundToInt()
                    else -> throw IllegalArgumentException()
                }
                Double::class -> (spinner.value as Double * 100).toInt()
                Float::class -> (spinner.value as Float * 100).toInt()
                else -> throw IllegalArgumentException()
            }
            this.value = spinner.value as T
        }
    }

    fun addChangeListener(l: (ChangeEvent) -> Unit) {
        slider.addChangeListener(l)
        spinner.addChangeListener(l)
    }
}
