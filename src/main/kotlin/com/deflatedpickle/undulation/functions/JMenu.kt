package com.deflatedpickle.undulation.functions

import javax.swing.JMenu

fun JMenu(text: String, mnemonic: Int) =
    JMenu(text).apply {
        setMnemonic(mnemonic)
    }