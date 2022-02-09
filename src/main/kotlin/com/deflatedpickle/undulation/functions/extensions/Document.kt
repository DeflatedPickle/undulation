/* Copyright (c) 2021 DeflatedPickle under the MIT license */

@file:Suppress("unused")

package com.deflatedpickle.undulation.functions.extensions

import javax.swing.text.Document

fun Document.getText(): String = this.getText(0, this.length)
