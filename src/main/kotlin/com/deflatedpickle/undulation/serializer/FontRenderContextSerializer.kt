package com.deflatedpickle.undulation.serializer

import com.deflatedpickle.undulation.api.FontStyle
import com.deflatedpickle.undulation.api.mimic.FontRenderContext as FontRenderContextMimic
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.awt.Font
import java.awt.Point
import java.awt.font.FontRenderContext
import java.awt.geom.AffineTransform

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = FontRenderContext::class)
object FontRenderContextSerializer : KSerializer<FontRenderContext> {
    override fun serialize(encoder: Encoder, value: FontRenderContext) {
        encoder.encodeSerializableValue(
            FontRenderContextMimic.serializer(),
            FontRenderContextMimic(
                value.isAntiAliased,
                value.usesFractionalMetrics(),
            )
        )
    }

    override fun deserialize(decoder: Decoder): FontRenderContext {
        val mimic = decoder.decodeSerializableValue(FontRenderContextMimic.serializer())

        return FontRenderContext(
            AffineTransform(),
            mimic.isAntiAliased,
            mimic.usesFractionalMetrics,
        )
    }
}