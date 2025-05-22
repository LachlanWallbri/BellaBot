package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

/* loaded from: classes9.dex */
class OverrideValue implements Value {
    private final Class type;
    private final Value value;

    public OverrideValue(Value value, Class cls) {
        this.value = value;
        this.type = cls;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public Object getValue() {
        return this.value.getValue();
    }

    @Override // org.simpleframework.xml.strategy.Value
    public void setValue(Object obj) {
        this.value.setValue(obj);
    }

    @Override // org.simpleframework.xml.strategy.Value
    public Class getType() {
        return this.type;
    }

    @Override // org.simpleframework.xml.strategy.Value
    public int getLength() {
        return this.value.getLength();
    }

    @Override // org.simpleframework.xml.strategy.Value
    public boolean isReference() {
        return this.value.isReference();
    }
}
