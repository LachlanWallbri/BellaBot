package com.google.api.client.json;

import java.lang.reflect.Field;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class CustomizeJsonParser {
    public void handleUnrecognizedKey(Object obj, String str) {
    }

    public Collection<Object> newInstanceForArray(Object obj, Field field) {
        return null;
    }

    public Object newInstanceForObject(Object obj, Class<?> cls) {
        return null;
    }

    public boolean stopAt(Object obj, String str) {
        return false;
    }
}
