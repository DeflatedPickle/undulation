/* Copyright (c) 2020 DeflatedPickle under the MIT license */

@file:Suppress("UNUSED_PARAMETER")

package com.deflatedpickle.undulation.extensions

import javax.swing.Icon
import javax.swing.JToolBar
import org.jdesktop.swingx.JXButton

fun JToolBar.add(
    text: String? = null,
    icon: Icon? = null,
    tooltip: String = "",
    enabled: Boolean = true,
    action: () -> Unit
): JXButton = JXButton(text, icon).apply {
    if (tooltip.isNotEmpty()) {
        toolTipText = tooltip
    }
    isEnabled = enabled
    addActionListener { action() }
    this@add.add(this)
}
