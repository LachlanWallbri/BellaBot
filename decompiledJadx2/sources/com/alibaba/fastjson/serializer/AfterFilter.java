package com.alibaba.fastjson.serializer;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class AfterFilter implements SerializeFilter {
    private static final ThreadLocal<JSONSerializer> serializerLocal = new ThreadLocal<>();
    private static final ThreadLocal<Character> seperatorLocal = new ThreadLocal<>();
    private static final Character COMMA = ',';

    public abstract void writeAfter(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        serializerLocal.set(jSONSerializer);
        seperatorLocal.set(Character.valueOf(c));
        writeAfter(obj);
        serializerLocal.set(null);
        return seperatorLocal.get().charValue();
    }

    protected final void writeKeyValue(String str, Object obj) {
        JSONSerializer jSONSerializer = serializerLocal.get();
        char charValue = seperatorLocal.get().charValue();
        jSONSerializer.writeKeyValue(charValue, str, obj);
        if (charValue != ',') {
            seperatorLocal.set(COMMA);
        }
    }
}
