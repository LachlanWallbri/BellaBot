package com.google.api.client.util;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class Joiner {
    private final com.google.common.base.Joiner wrapped;

    /* renamed from: on */
    public static Joiner m576on(char c) {
        return new Joiner(com.google.common.base.Joiner.m608on(c));
    }

    private Joiner(com.google.common.base.Joiner joiner) {
        this.wrapped = joiner;
    }

    public final String join(Iterable<?> iterable) {
        return this.wrapped.join(iterable);
    }
}
