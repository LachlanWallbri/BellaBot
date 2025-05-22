package com.pudutech.mirsdk.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Event.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001b\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0001¢\u0006\u0002\u0010\u000eJ>\u0010\u000f\u001a\u00020\f26\u0010\u0010\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007JA\u0010\u0011\u001a\u00020\f26\u0010\u0010\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007H\u0086\u0002RD\u0010\u0005\u001a8\u00124\u00122\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/base/Event2;", "T1", "T2", "", "()V", "listeners", "", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value1", "value2", "", "emit", "(Ljava/lang/Object;Ljava/lang/Object;)V", DebugKt.DEBUG_PROPERTY_VALUE_ON, "callback", "plusAssign", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Event2<T1, T2> {
    private final List<Function2<T1, T2, Unit>> listeners = new ArrayList();

    /* renamed from: on */
    public final void m3299on(Function2<? super T1, ? super T2, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.listeners.add(callback);
    }

    public final void emit(T1 value1, T2 value2) {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((Function2) it.next()).invoke(value1, value2);
        }
    }

    public final void plusAssign(Function2<? super T1, ? super T2, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        m3299on(callback);
    }
}
