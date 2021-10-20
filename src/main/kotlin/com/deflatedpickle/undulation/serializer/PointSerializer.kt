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

@ExperimentalSerializationApi
@Serializer(forClass = Point::class)
object PointSerializer : KSerializer<Point> {
    override val descriptor = PrimitiveSerialDescriptor(
        serialName = "Point",
        kind = PrimitiveKind.STRING,
    )

    override fun serialize(encoder: Encoder, value: Point) =
        encoder.encodeSerializableValue(
            MapSerializer(String.serializer(), Int.serializer()),
            mapOf(
                "x" to value.x,
                "y" to value.y,
            )
        )

    override fun deserialize(decoder: Decoder): Point {
        val decode = decoder.decodeSerializableValue(
            MapSerializer(String.serializer(), Int.serializer()),
        )

        return Point(decode["x"] ?: 0, decode["y"] ?: 0)
    }
}