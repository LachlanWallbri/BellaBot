package com.pudutech.mirsdk.base;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Event.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0013\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0002\u0010\fJ)\u0010\r\u001a\u00020\n2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0006J,\u0010\u000f\u001a\u00020\n2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0006H\u0086\u0002R/\u0010\u0004\u001a#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/base/Event;", ExifInterface.GPS_DIRECTION_TRUE, "", "()V", "listeners", "", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", ES6Iterator.VALUE_PROPERTY, "", "emit", "(Ljava/lang/Object;)V", DebugKt.DEBUG_PROPERTY_VALUE_ON, "callback", "plusAssign", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Event<T> {
    private final List<Function1<T, Unit>> listeners = new ArrayList();

    /* renamed from: on */
    public final void m3298on(Function1<? super T, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.listeners.add(callback);
    }

    public final void emit(T value) {
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(value);
        }
    }

    public final void plusAssign(Function1<? super T, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        m3298on(callback);
    }
}
