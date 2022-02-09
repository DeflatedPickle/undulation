package com.deflatedpickle.undulation.functions

import javax.swing.Icon

fun JButton(text: String? = null, icon: Icon? = null, function: () -> Unit) =
    javax.swing.JButton(text, icon).apply {
    addActionListener {
        function()
    }
}