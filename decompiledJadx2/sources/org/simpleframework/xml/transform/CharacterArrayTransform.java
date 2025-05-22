package org.simpleframework.xml.transform;

import java.lang.reflect.Array;

/* loaded from: classes9.dex */
class CharacterArrayTransform implements Transform {
    private final Class entry;

    public CharacterArrayTransform(Class cls) {
        this.entry = cls;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public Object read(String str) throws Exception {
        char[] charArray = str.toCharArray();
        return this.entry == Character.TYPE ? charArray : read(charArray, charArray.length);
    }

    private Object read(char[] cArr, int i) throws Exception {
        Object newInstance = Array.newInstance((Class<?>) this.entry, i);
        for (int i2 = 0; i2 < i; i2++) {
            Array.set(newInstance, i2, Character.valueOf(cArr[i2]));
        }
        return newInstance;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Object obj) throws Exception {
        int length = Array.getLength(obj);
        if (this.entry == Character.TYPE) {
            return new String((char[]) obj);
        }
        return write(obj, length);
    }

    private String write(Object obj, int i) throws Exception {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            Object obj2 = Array.get(obj, i2);
            if (obj2 != null) {
                sb.append(obj2);
            }
        }
        return sb.toString();
    }
}
