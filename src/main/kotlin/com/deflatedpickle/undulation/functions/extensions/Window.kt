package com.deflatedpickle.undulation.functions.extensions

import java.awt.GraphicsEnvironment
import java.awt.Window

// https://stackoverflow.com/a/33799118
fun Window.getScreenDevice() = GraphicsEnvironment
    .getLocalGraphicsEnvironment()
    .screenDevices
    .firstOrNull { it.defaultConfiguration.bounds.intersects(bounds) }