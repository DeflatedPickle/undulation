package com.deflatedpickle.undulation.functions

import com.deflatedpickle.undulation.api.MenuButtonType
import java.awt.event.ActionEvent
import javax.swing.*

fun JMenuItem(
    text: String,
    icon: Icon? = null,
    accelerator: KeyStroke? = null,
    message: String? = null,
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
}