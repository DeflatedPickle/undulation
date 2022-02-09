/* Copyright (c) 2020 DeflatedPickle under the MIT license */

package com.deflatedpickle.undulation.functions.extensions

import java.awt.event.ActionEvent
import javax.swing.Icon
import javax.swing.JMenu
import javax.swing.JMenuItem
import javax.swing.JPopupMenu

fun JMenu.add(text: String, icon: Icon? = null, index: Int = -1, enabled: Boolean = true, action: (ActionEvent) -> Unit) =
    JMenuItem(text, icon).apply {
        isEnabled = enabled
        addActionListener { action(it) }
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
