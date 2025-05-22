package org.apache.commons.compress.harmony.unpack200.bytecode;

/* loaded from: classes9.dex */
public abstract class CPConstantNumber extends CPConstant {
    public CPConstantNumber(byte b, Object obj, int i) {
        super(b, obj, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Number getNumber() {
        return (Number) getValue();
    }
}
