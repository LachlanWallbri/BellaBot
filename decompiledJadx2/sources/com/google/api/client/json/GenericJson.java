package com.google.api.client.json;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Throwables;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class GenericJson extends GenericData implements Cloneable {
    private JsonFactory jsonFactory;

    public final JsonFactory getFactory() {
        return this.jsonFactory;
    }

    public final void setFactory(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap
    public String toString() {
        JsonFactory jsonFactory = this.jsonFactory;
        if (jsonFactory != null) {
            try {
                return jsonFactory.toString(this);
            } catch (IOException e) {
                throw Throwables.propagate(e);
            }
        }
        return super.toString();
    }

    public String toPrettyString() throws IOException {
        JsonFactory jsonFactory = this.jsonFactory;
        if (jsonFactory != null) {
            return jsonFactory.toPrettyString(this);
        }
        return super.toString();
    }

    @Override // com.google.api.client.util.GenericData, java.util.AbstractMap
    public GenericJson clone() {
        return (GenericJson) super.clone();
    }

    @Override // com.google.api.client.util.GenericData
    public GenericJson set(String str, Object obj) {
        return (GenericJson) super.set(str, obj);
    }
}
