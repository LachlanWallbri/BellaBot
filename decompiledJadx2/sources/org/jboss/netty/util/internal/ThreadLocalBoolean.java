package org.jboss.netty.util.internal;

/* loaded from: classes7.dex */
public class ThreadLocalBoolean extends ThreadLocal<Boolean> {
    private final boolean defaultValue;

    public ThreadLocalBoolean() {
        this(false);
    }

    public ThreadLocalBoolean(boolean z) {
        this.defaultValue = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.ThreadLocal
    public Boolean initialValue() {
        return this.defaultValue ? Boolean.TRUE : Boolean.FALSE;
    }
}
