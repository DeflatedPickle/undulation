/* Copyright (c) 2020 DeflatedPickle under the MIT license */

@file:Suppress("UNUSED_PARAMETER")

package com.deflatedpickle.undulation.functions.extensions

import com.deflatedpickle.undulation.api.ButtonType
import com.deflatedpickle.undulation.functions.AbstractButton
import javax.swing.*

fun JToolBar.add(
    text: String? = null,
    icon: Icon? = null,
    tooltip: String = "",
    enabled: Boolean = true,
    type: ButtonType = ButtonType.PRESS,
    action: (AbstractButton) -> Unit
): AbstractButton = AbstractButton(
    text,
    icon,
    tooltip,
    enabled,
    type,
    action
).apply { this@add.add(this) }