package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.util.Separators;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public interface PrettyPrinter {
    public static final Separators DEFAULT_SEPARATORS = Separators.createDefaultInstance();
    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");

    void beforeArrayValues(JsonGenerator jsonGenerator) throws IOException;

    void beforeObjectEntries(JsonGenerator jsonGenerator) throws IOException;

    void writeArrayValueSeparator(JsonGenerator jsonGenerator) throws IOException;

    void writeEndArray(JsonGenerator jsonGenerator, int i) throws IOException;

    void writeEndObject(JsonGenerator jsonGenerator, int i) throws IOException;

    void writeObjectEntrySeparator(JsonGenerator jsonGenerator) throws IOException;

    void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) throws IOException;

    void writeRootValueSeparator(JsonGenerator jsonGenerator) throws IOException;

    void writeStartArray(JsonGenerator jsonGenerator) throws IOException;

    void writeStartObject(JsonGenerator jsonGenerator) throws IOException;
}
