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
import java.awt.Point
import java.awt.geom.Point2D

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Point2D::class)
object Point2DSerializer : KSerializer<Point2D> {
    override fun serialize(encoder: Encoder, value: Point2D) =
        encoder.encodeSerializableValue(
            MapSerializer(String.serializer(), Double.serializer()),
            mapOf(
                "x" to value.x,
                "y" to value.y,
            )
        )

    override fun deserialize(decoder: Decoder): Point2D {
        val decode = decoder.decodeSerializableValue(
            MapSerializer(String.serializer(), Double.serializer()),
        )

        return Point2D.Double(
            decode["x"] ?: 0.0,
            decode["y"] ?: 0.0
        )
    }
}