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

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Rectangle::class)
object RectangleSerializer : KSerializer<Rectangle> {
    override val descriptor = PrimitiveSerialDescriptor(
        serialName = "Rectangle",
        kind = PrimitiveKind.STRING,
    )

    override fun serialize(encoder: Encoder, value: Rectangle) =
        encoder.encodeSerializableValue(
            MapSerializer(String.serializer(), Int.serializer()),
            mapOf(
                "x" to value.x,
                "y" to value.y,
                "width" to value.width,
                "height" to value.height,
            )
        )

    override fun deserialize(decoder: Decoder): Rectangle {
        val decode = decoder.decodeSerializableValue(
            MapSerializer(String.serializer(), Int.serializer()),
        )

        return Rectangle(
            decode["x"] ?: 0,
            decode["y"] ?: 0,
            decode["width"] ?: 0,
            decode["height"] ?: 0,
        )
    }
}