/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.functions.extensions

import com.deflatedpickle.undulation.api.MenuButtonType
import java.awt.event.ActionEvent
import javax.swing.Icon
import javax.swing.JCheckBoxMenuItem
import javax.swing.JMenu
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.JRadioButtonMenuItem
import javax.swing.KeyStroke
import javax.swing.SwingUtilities

fun JPopupMenu.add(
    text: String,
    icon: Icon? = null,
    accelerator: KeyStroke? = null,
    message: String? = null,
    index: Int = -1,
    enabled: Boolean = true,
    type: MenuButtonType = MenuButtonType.BUTTON,
    action: (ActionEvent) -> Unit
) = when (type) {
    MenuButtonType.BUTTON -> JMenuItem(text, icon)
    MenuButtonType.CHECK -> JCheckBoxMenuItem(text, icon)
    MenuButtonType.RADIO -> JRadioButtonMenuItem(text, icon)
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
        if (index == -1) this@add.componentCount else index
    )
}


fun JPopupMenu.disableAll() {
    for (i in this.components) {
        if (i is JMenu) {
            i.disableAll()
        } else if (i is JPopupMenu) {
            i.disableAll()
        }

        i.isEnabled = false
    }
}
