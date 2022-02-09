/* Copyright (c) 2020 DeflatedPickle under the MIT license */

@file:Suppress("UNUSED_PARAMETER")

package com.deflatedpickle.undulation.functions.extensions

import com.deflatedpickle.undulation.api.ButtonType
import org.jdesktop.swingx.JXButton
import javax.swing.*

fun JToolBar.add(
    text: String? = null,
    icon: Icon? = null,
    tooltip: String = "",
    enabled: Boolean = true,
    type: ButtonType = ButtonType.PRESS,
    action: () -> Unit
): AbstractButton = when (type) {
    ButtonType.PRESS -> JXButton(text, icon)
    ButtonType.TOGGLE -> JToggleButton(text, icon)
    ButtonType.RADIO -> JRadioButton(text, icon)
}.apply {
    if (tooltip.isNotEmpty()) {
        toolTipText = tooltip
    }
    isEnabled = enabled
    addActionListener { action() }
    this@add.add(this)
}