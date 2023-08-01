/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.functions.extensions

import com.deflatedpickle.undulation.api.MenuButtonType
import com.deflatedpickle.undulation.api.MenuButtonType.BUTTON
import com.deflatedpickle.undulation.api.MenuButtonType.CHECK
import com.deflatedpickle.undulation.api.MenuButtonType.RADIO
import java.awt.MenuShortcut
import java.awt.event.ActionEvent
import javax.swing.Icon
import javax.swing.JCheckBoxMenuItem
import javax.swing.JMenu
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.JRadioButtonMenuItem
import javax.swing.KeyStroke
import javax.swing.SwingUtilities

fun JMenu.add(
    text: String,
    icon: Icon? = null,
    accelerator: KeyStroke? = null,
    message: String? = null,
    index: Int = -1,
    enabled: Boolean = true,
    type: MenuButtonType = BUTTON,
    action: (ActionEvent) -> Unit
) = when (type) {
    BUTTON -> JMenuItem(text, icon)
    CHECK -> JCheckBoxMenuItem(text, icon)
    RADIO -> JRadioButtonMenuItem(text, icon)
}.apply {
    isEnabled = enabled

    accelerator?.let {
        mnemonic = accelerator.keyCode
        setAccelerator(accelerator)
    }

    message?.let {
        toolTipText = message
        putClientProperty("statusMessage", message)
    }

    addActionListener { action(it) }
    addActionListener {
        SwingUtilities.getWindowAncestor(this)
    }

    this@add.insert(
        this,
        if (index == -1) this@add.itemCount else index
    )
}


fun JMenu.disableAll() {
    for (i in this.menuComponents) {
        if (i is JMenu) {
            i.disableAll()
        } else if (i is JPopupMenu) {
            i.disableAll()
        }

        i.isEnabled = false
    }
}
