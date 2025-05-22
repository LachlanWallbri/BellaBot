package org.simpleframework.xml.convert;

import org.simpleframework.xml.strategy.Value;

/* loaded from: classes9.dex */
class Reference implements Value {
    private Class actual;
    private Object data;
    private Value value;

    @Override // org.simpleframework.xml.strategy.Value
    public int getLength() {
        return 0;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public boolean isReference() {
        return true;
    }

    public Reference(Value value, Object obj, Class cls) {
        this.actual = cls;
        this.value = value;
        this.data = obj;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public Class getType() {
        Object obj = this.data;
        if (obj != null) {
            return obj.getClass();
        }
        return this.actual;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public Object getValue() {
        return this.data;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public void setValue(Object obj) {
        Value value = this.value;
        if (value != null) {
            value.setValue(obj);
        }
        this.data = obj;
    }
}
