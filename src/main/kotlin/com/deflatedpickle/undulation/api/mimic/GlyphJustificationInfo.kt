package com.deflatedpickle.undulation.api.mimic

import kotlinx.serialization.Serializable

@Serializable
data class GlyphJustificationInfo(
    val weight: Float,
    val growAbsorb: Boolean,
    val growPriority: Int,
    val growLeftLimit: Float,
    val growRightLimit: Float,
    val shrinkAbsorb: Boolean,
    val shrinkPriority: Int,
    val shrinkLeftLimit: Float,
    val shrinkRightLimit: Float,
)