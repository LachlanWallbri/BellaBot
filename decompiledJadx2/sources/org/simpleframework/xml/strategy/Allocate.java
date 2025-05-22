package org.simpleframework.xml.strategy;

import java.util.Map;

/* loaded from: classes9.dex */
class Allocate implements Value {
    private String key;
    private Map map;
    private Value value;

    @Override // org.simpleframework.xml.strategy.Value
    public boolean isReference() {
        return false;
    }

    public Allocate(Value value, Map map, String str) {
        this.value = value;
        this.map = map;
        this.key = str;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public Object getValue() {
        return this.map.get(this.key);
    }

    @Override // org.simpleframework.xml.strategy.Value
    public void setValue(Object obj) {
        String str = this.key;
        if (str != null) {
            this.map.put(str, obj);
        }
        this.value.setValue(obj);
    }

    @Override // org.simpleframework.xml.strategy.Value
    public Class getType() {
        return this.value.getType();
    }

    @Override // org.simpleframework.xml.strategy.Value
    public int getLength() {
        return this.value.getLength();
    }
}
