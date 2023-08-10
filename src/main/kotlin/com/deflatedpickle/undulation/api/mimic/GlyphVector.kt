package com.deflatedpickle.undulation.api.mimic

import com.deflatedpickle.undulation.serializer.FontRenderContextSerializer
import com.deflatedpickle.undulation.serializer.FontSerializer
import com.deflatedpickle.undulation.serializer.GlyphJustificationInfoSerializer
import com.deflatedpickle.undulation.serializer.GlyphMetricsSerializer
import com.deflatedpickle.undulation.serializer.Point2DSerializer
import com.deflatedpickle.undulation.serializer.Rectangle2DSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.awt.Font
import java.awt.Shape
import java.awt.font.FontRenderContext
import java.awt.font.GlyphJustificationInfo
import java.awt.font.GlyphMetrics
import java.awt.geom.AffineTransform
import java.awt.geom.GeneralPath
import java.awt.geom.Point2D
import java.awt.geom.Rectangle2D

@Serializable
data class GlyphVector(
    val font: @Serializable(FontSerializer::class) Font,
    val fontRenderContext: @Serializable(FontRenderContextSerializer::class) FontRenderContext,
    val numGlyphs: Int,
    val glyphCode: Int,
    val logicalBounds: @Serializable(Rectangle2DSerializer::class) Rectangle2D,
    val visualBounds: @Serializable(Rectangle2DSerializer::class) Rectangle2D,
    @Transient val outline: Shape = GeneralPath(),
    @Transient val glyphOutline: Shape = GeneralPath(),
    var glyphPosition: @Serializable(Point2DSerializer::class) Point2D,
    @Transient var transform: AffineTransform = AffineTransform(),
    val metrics: @Serializable(GlyphMetricsSerializer::class) GlyphMetrics,
    val justificationInfo: @Serializable(GlyphJustificationInfoSerializer::class) GlyphJustificationInfo?,
)