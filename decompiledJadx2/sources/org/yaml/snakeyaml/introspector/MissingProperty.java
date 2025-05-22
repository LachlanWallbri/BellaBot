package org.yaml.snakeyaml.introspector;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class MissingProperty extends Property {
    @Override // org.yaml.snakeyaml.introspector.Property
    public Object get(Object obj) {
        return obj;
    }

    @Override // org.yaml.snakeyaml.introspector.Property
    public Class<?>[] getActualTypeArguments() {
        return new Class[0];
    }

    @Override // org.yaml.snakeyaml.introspector.Property
    public void set(Object obj, Object obj2) throws Exception {
    }

    public MissingProperty(String str) {
        super(str, Object.class);
    }
}
