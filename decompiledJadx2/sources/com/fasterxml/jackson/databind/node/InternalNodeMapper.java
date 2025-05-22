package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;

/* loaded from: classes2.dex */
final class InternalNodeMapper {
    private static final JsonMapper JSON_MAPPER = new JsonMapper();
    private static final ObjectWriter STD_WRITER = JSON_MAPPER.writer();
    private static final ObjectWriter PRETTY_WRITER = JSON_MAPPER.writer().withDefaultPrettyPrinter();
    private static final ObjectReader NODE_READER = JSON_MAPPER.readerFor(JsonNode.class);

    InternalNodeMapper() {
    }

    public static String nodeToString(JsonNode jsonNode) {
        try {
            return STD_WRITER.writeValueAsString(jsonNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String nodeToPrettyString(JsonNode jsonNode) {
        try {
            return PRETTY_WRITER.writeValueAsString(jsonNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] valueToBytes(Object obj) throws IOException {
        return JSON_MAPPER.writeValueAsBytes(obj);
    }

    public static JsonNode bytesToNode(byte[] bArr) throws IOException {
        return (JsonNode) NODE_READER.readValue(bArr);
    }
}
