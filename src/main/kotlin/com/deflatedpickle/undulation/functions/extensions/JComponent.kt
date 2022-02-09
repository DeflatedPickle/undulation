package com.deflatedpickle.undulation.functions.extensions

import javax.swing.JComponent

fun JComponent.updateUIRecursively() {
    updateUI()

    for (i in components.filterIsInstance<JComponent>()) {
        i.updateUIRecursively()
    }
}