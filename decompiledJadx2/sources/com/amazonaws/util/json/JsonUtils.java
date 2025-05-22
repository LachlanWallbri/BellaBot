package com.amazonaws.util.json;

import com.amazonaws.AmazonClientException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class JsonUtils {
    private static final AwsJsonFactory FACTORY = new GsonFactory();

    @Deprecated
    /* loaded from: classes.dex */
    public enum JsonEngine {
        Gson,
        Jackson
    }

    @Deprecated
    static void setJsonEngine(AwsJsonFactory awsJsonFactory) {
    }

    @Deprecated
    public static void setJsonEngine(JsonEngine jsonEngine) {
    }

    public static AwsJsonReader getJsonReader(Reader reader) {
        return FACTORY.getJsonReader(reader);
    }

    public static AwsJsonWriter getJsonWriter(Writer writer) {
        return FACTORY.getJsonWriter(writer);
    }

    public static Map<String, String> jsonToMap(Reader reader) {
        AwsJsonReader jsonReader = getJsonReader(reader);
        try {
            if (jsonReader.peek() == null) {
                return Collections.EMPTY_MAP;
            }
            HashMap hashMap = new HashMap();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.isContainer()) {
                    jsonReader.skipValue();
                } else {
                    hashMap.put(nextName, jsonReader.nextString());
                }
            }
            jsonReader.endObject();
            jsonReader.close();
            return Collections.unmodifiableMap(hashMap);
        } catch (IOException e) {
            throw new AmazonClientException("Unable to parse JSON String.", e);
        }
    }

    public static Map<String, String> jsonToMap(String str) {
        if (str == null || str.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        return jsonToMap(new StringReader(str));
    }

    public static Map<String, String> jsonToStringMapWithList(Reader reader) {
        AwsJsonReader jsonReader = getJsonReader(reader);
        try {
            if (jsonReader.peek() == null) {
                return Collections.EMPTY_MAP;
            }
            HashMap hashMap = new HashMap();
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.isContainer()) {
                    if (AwsJsonToken.BEGIN_ARRAY.equals(jsonReader.peek())) {
                        StringWriter stringWriter = new StringWriter();
                        AwsJsonWriter jsonWriter = getJsonWriter(stringWriter);
                        jsonReader.beginArray();
                        jsonWriter.beginArray();
                        while (!AwsJsonToken.END_ARRAY.equals(jsonReader.peek())) {
                            try {
                                AwsJsonToken peek = jsonReader.peek();
                                if (AwsJsonToken.BEGIN_OBJECT.equals(peek)) {
                                    jsonReader.beginObject();
                                    jsonWriter.beginObject();
                                } else if (AwsJsonToken.FIELD_NAME.equals(peek)) {
                                    String nextName2 = jsonReader.nextName();
                                    if (!AwsJsonToken.BEGIN_ARRAY.equals(jsonReader.peek())) {
                                        jsonWriter.name(nextName2);
                                    }
                                } else if (AwsJsonToken.END_OBJECT.equals(peek)) {
                                    jsonReader.endObject();
                                    jsonWriter.endObject();
                                } else if (AwsJsonToken.END_ARRAY.equals(peek)) {
                                    jsonReader.endArray();
                                    jsonWriter.endArray();
                                } else {
                                    if (!AwsJsonToken.VALUE_STRING.equals(peek) && !AwsJsonToken.VALUE_NUMBER.equals(peek) && !AwsJsonToken.VALUE_NULL.equals(peek) && !AwsJsonToken.VALUE_BOOLEAN.equals(peek)) {
                                        jsonReader.skipValue();
                                    }
                                    jsonWriter.value(jsonReader.nextString());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        jsonReader.endArray();
                        jsonWriter.endArray();
                        jsonWriter.flush();
                        jsonWriter.close();
                        hashMap.put(nextName, stringWriter.toString());
                    } else {
                        jsonReader.skipValue();
                    }
                } else {
                    hashMap.put(nextName, jsonReader.nextString());
                }
            }
            jsonReader.endObject();
            jsonReader.close();
            return Collections.unmodifiableMap(hashMap);
        } catch (IOException e2) {
            throw new AmazonClientException("Unable to parse JSON String.", e2);
        }
    }

    public static String mapToString(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                jsonWriter.name(entry.getKey()).value(entry.getValue());
            }
            jsonWriter.endObject();
            jsonWriter.close();
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AmazonClientException("Unable to serialize to JSON String.", e);
        }
    }

    private static boolean isClassAvailable(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
