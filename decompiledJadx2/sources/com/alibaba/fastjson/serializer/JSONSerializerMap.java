package com.alibaba.fastjson.serializer;

import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes.dex */
public class JSONSerializerMap extends SerializeConfig {
    public final boolean put(Class<?> cls, ObjectSerializer objectSerializer) {
        return super.put((Type) cls, objectSerializer);
    }
}
