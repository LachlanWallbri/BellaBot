package com.google.common.util.concurrent;

import java.util.function.LongBinaryOperator;

/* compiled from: lambda */
/* renamed from: com.google.common.util.concurrent.-$$Lambda$AtomicLongMap$dplkPhACWDPIy18ogwdupEQaN40, reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$AtomicLongMap$dplkPhACWDPIy18ogwdupEQaN40 implements LongBinaryOperator {
    public static final /* synthetic */ $$Lambda$AtomicLongMap$dplkPhACWDPIy18ogwdupEQaN40 INSTANCE = new $$Lambda$AtomicLongMap$dplkPhACWDPIy18ogwdupEQaN40();

    private /* synthetic */ $$Lambda$AtomicLongMap$dplkPhACWDPIy18ogwdupEQaN40() {
    }

    @Override // java.util.function.LongBinaryOperator
    public final long applyAsLong(long j, long j2) {
        long sum;
        sum = Long.sum(j, j2);
        return sum;
    }
}
