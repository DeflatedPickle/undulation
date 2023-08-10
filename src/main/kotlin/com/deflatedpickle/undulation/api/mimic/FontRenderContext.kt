package com.deflatedpickle.undulation.api.mimic

import kotlinx.serialization.Serializable

@Serializable
data class FontRenderContext(
    val isAntiAliased: Boolean,
    val usesFractionalMetrics: Boolean,
)