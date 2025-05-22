package com.pudutech.pd_network.report.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes6.dex */
class MapTypeAdapter extends TypeAdapter<Object> {
    private final TypeAdapter<Object> delegate = new Gson().getAdapter(Object.class);

    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public Object read2(JsonReader jsonReader) throws IOException {
        switch (jsonReader.peek()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(read2(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            case BEGIN_OBJECT:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    linkedTreeMap.put(jsonReader.nextName(), read2(jsonReader));
                }
                jsonReader.endObject();
                return linkedTreeMap;
            case STRING:
                return jsonReader.nextString();
            case NUMBER:
                double nextDouble = jsonReader.nextDouble();
                if (nextDouble > 9.223372036854776E18d) {
                    return String.valueOf(nextDouble);
                }
                long j = (long) nextDouble;
                if (nextDouble == j || nextDouble == 0.0d) {
                    return Long.valueOf(j);
                }
                return Double.valueOf(nextDouble);
            case BOOLEAN:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case NULL:
                jsonReader.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Object obj) throws IOException {
        this.delegate.write(jsonWriter, obj);
    }
}
