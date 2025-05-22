package org.simpleframework.xml.strategy;

/* loaded from: classes9.dex */
class ArrayValue implements Value {
    private int size;
    private Class type;
    private Object value;

    @Override // org.simpleframework.xml.strategy.Value
    public boolean isReference() {
        return false;
    }

    public ArrayValue(Class cls, int i) {
        this.type = cls;
        this.size = i;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public Object getValue() {
        return this.value;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public void setValue(Object obj) {
        this.value = obj;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public Class getType() {
        return this.type;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public int getLength() {
        return this.size;
    }
}
