package com.deflatedpickle.undulation.api.mimic

import com.deflatedpickle.undulation.serializer.Rectangle2DSerializer
import kotlinx.serialization.Serializable
import java.awt.geom.Rectangle2D

@Serializable
data class GlyphMetrics(
    val advance: Float,
    val bounds: @Serializable(Rectangle2DSerializer::class) Rectangle2D,
    val glyphType: Byte,
)
