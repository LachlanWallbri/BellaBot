package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class ByteBufferSerializer extends StdScalarSerializer<ByteBuffer> {
    public ByteBufferSerializer() {
        super(ByteBuffer.class);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(ByteBuffer byteBuffer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (byteBuffer.hasArray()) {
            int position = byteBuffer.position();
            jsonGenerator.writeBinary(byteBuffer.array(), byteBuffer.arrayOffset() + position, byteBuffer.limit() - position);
            return;
        }
        ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        if (asReadOnlyBuffer.position() > 0) {
            asReadOnlyBuffer.rewind();
        }
        ByteBufferBackedInputStream byteBufferBackedInputStream = new ByteBufferBackedInputStream(asReadOnlyBuffer);
        jsonGenerator.writeBinary(byteBufferBackedInputStream, asReadOnlyBuffer.remaining());
        byteBufferBackedInputStream.close();
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdScalarSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
        if (expectArrayFormat != null) {
            expectArrayFormat.itemsFormat(JsonFormatTypes.INTEGER);
        }
    }
}
