package com.amazonaws.util.json;

import com.amazonaws.util.BinaryUtils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Date;

/* loaded from: classes.dex */
final class GsonFactory implements AwsJsonFactory {
    @Override // com.amazonaws.util.json.AwsJsonFactory
    public AwsJsonReader getJsonReader(Reader reader) {
        return new GsonReader(reader);
    }

    @Override // com.amazonaws.util.json.AwsJsonFactory
    public AwsJsonWriter getJsonWriter(Writer writer) {
        return new GsonWriter(writer);
    }

    /* loaded from: classes.dex */
    private static final class GsonReader implements AwsJsonReader {
        private final JsonReader reader;

        public GsonReader(Reader reader) {
            this.reader = new JsonReader(reader);
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void beginArray() throws IOException {
            this.reader.beginArray();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void endArray() throws IOException {
            this.reader.endArray();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void beginObject() throws IOException {
            this.reader.beginObject();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void endObject() throws IOException {
            this.reader.endObject();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public boolean isContainer() throws IOException {
            JsonToken peek = this.reader.peek();
            return JsonToken.BEGIN_ARRAY.equals(peek) || JsonToken.BEGIN_OBJECT.equals(peek);
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public boolean hasNext() throws IOException {
            return this.reader.hasNext();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public String nextName() throws IOException {
            return this.reader.nextName();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public String nextString() throws IOException {
            JsonToken peek = this.reader.peek();
            if (JsonToken.NULL.equals(peek)) {
                this.reader.nextNull();
                return null;
            }
            if (JsonToken.BOOLEAN.equals(peek)) {
                return this.reader.nextBoolean() ? "true" : "false";
            }
            return this.reader.nextString();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void skipValue() throws IOException {
            this.reader.skipValue();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public AwsJsonToken peek() throws IOException {
            try {
                return GsonFactory.convert(this.reader.peek());
            } catch (EOFException unused) {
                return null;
            }
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void close() throws IOException {
            this.reader.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AwsJsonToken convert(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken) {
            case BEGIN_ARRAY:
                return AwsJsonToken.BEGIN_ARRAY;
            case END_ARRAY:
                return AwsJsonToken.END_ARRAY;
            case BEGIN_OBJECT:
                return AwsJsonToken.BEGIN_OBJECT;
            case END_OBJECT:
                return AwsJsonToken.END_OBJECT;
            case NAME:
                return AwsJsonToken.FIELD_NAME;
            case BOOLEAN:
                return AwsJsonToken.VALUE_BOOLEAN;
            case NUMBER:
                return AwsJsonToken.VALUE_NUMBER;
            case NULL:
                return AwsJsonToken.VALUE_NULL;
            case STRING:
                return AwsJsonToken.VALUE_STRING;
            case END_DOCUMENT:
                return null;
            default:
                return AwsJsonToken.UNKNOWN;
        }
    }

    /* loaded from: classes.dex */
    private static final class GsonWriter implements AwsJsonWriter {
        private static final int NEGATIVE_THREE = -3;
        private final JsonWriter writer;

        public GsonWriter(Writer writer) {
            this.writer = new JsonWriter(writer);
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter beginArray() throws IOException {
            this.writer.beginArray();
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter endArray() throws IOException {
            this.writer.endArray();
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter beginObject() throws IOException {
            this.writer.beginObject();
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter endObject() throws IOException {
            this.writer.endObject();
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter name(String str) throws IOException {
            this.writer.name(str);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(String str) throws IOException {
            this.writer.value(str);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(boolean z) throws IOException {
            this.writer.value(z);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(double d) throws IOException {
            this.writer.value(d);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(long j) throws IOException {
            this.writer.value(j);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(Number number) throws IOException {
            this.writer.value(number);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(Date date) throws IOException {
            this.writer.value(BigDecimal.valueOf(date.getTime()).scaleByPowerOfTen(-3));
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(ByteBuffer byteBuffer) throws IOException {
            byteBuffer.mark();
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr, 0, bArr.length);
            byteBuffer.reset();
            this.writer.value(BinaryUtils.toBase64(bArr));
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value() throws IOException {
            this.writer.nullValue();
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public void flush() throws IOException {
            this.writer.flush();
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public void close() throws IOException {
            this.writer.close();
        }
    }
}
