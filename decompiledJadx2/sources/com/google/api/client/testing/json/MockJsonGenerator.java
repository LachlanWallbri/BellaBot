package com.google.api.client.testing.json;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class MockJsonGenerator extends JsonGenerator {
    private final JsonFactory factory;

    @Override // com.google.api.client.json.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator, java.io.Flushable
    public void flush() throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeBoolean(boolean z) throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeEndArray() throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeEndObject() throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeFieldName(String str) throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNull() throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(double d) throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(float f) throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(int i) throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(long j) throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(String str) throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeNumber(BigInteger bigInteger) throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeStartArray() throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeStartObject() throws IOException {
    }

    @Override // com.google.api.client.json.JsonGenerator
    public void writeString(String str) throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MockJsonGenerator(JsonFactory jsonFactory) {
        this.factory = jsonFactory;
    }

    @Override // com.google.api.client.json.JsonGenerator
    public JsonFactory getFactory() {
        return this.factory;
    }
}
