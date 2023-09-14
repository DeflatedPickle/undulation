package com.deflatedpickle.undulation.functions

import javax.swing.JMenu

fun JMenu(
    text: String,
    mnemonic: Int? = null,
    enabled: Boolean = true,
) = JMenu(text).apply {
    isEnabled = enabled

    mnemonic?.let {
        setMnemonic(mnemonic)
    }
}