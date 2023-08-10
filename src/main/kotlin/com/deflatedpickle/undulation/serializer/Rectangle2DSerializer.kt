package com.deflatedpickle.undulation.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.awt.Rectangle
import java.awt.geom.Rectangle2D

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Rectangle2D::class)
object Rectangle2DSerializer : KSerializer<Rectangle2D> {
    override fun serialize(encoder: Encoder, value: Rectangle2D) =
        encoder.encodeSerializableValue(
            MapSerializer(String.serializer(), Double.serializer()),
            mapOf(
                "x" to value.x,
                "y" to value.y,
                "width" to value.width,
                "height" to value.height,
            )
        )

    override fun deserialize(decoder: Decoder): Rectangle2D {
        val decode = decoder.decodeSerializableValue(
            MapSerializer(String.serializer(), Double.serializer()),
        )

        return Rectangle2D.Double(
            decode["x"] ?: 0.0,
            decode["y"] ?: 0.0,
            decode["width"] ?: 0.0,
            decode["height"] ?: 0.0,
        )
    }
}