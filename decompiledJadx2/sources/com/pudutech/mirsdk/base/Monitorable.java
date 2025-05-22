package com.pudutech.mirsdk.base;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Monitorable.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\rJ)\u0010\u0013\u001a\u00020\r2!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\tJ\u001d\u0010\u0011\u001a\u00020\r2\u0006\u0010\f\u001a\u00028\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017R\u0010\u0010\u0005\u001a\u00028\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R/\u0010\u0007\u001a#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u00008F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0004¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mirsdk/base/Monitorable;", ExifInterface.LONGITUDE_EAST, "", "default", "(Ljava/lang/Object;)V", "_value", "Ljava/lang/Object;", "listeners", "", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "v", "", ES6Iterator.VALUE_PROPERTY, "getValue", "()Ljava/lang/Object;", "setValue", "invokeChange", "onChange", "callback", "notifyAnyway", "", "(Ljava/lang/Object;Z)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Monitorable<E> {
    private E _value;
    private final List<Function1<E, Unit>> listeners = new ArrayList();

    public Monitorable(E e) {
        this._value = e;
    }

    public final E getValue() {
        return this._value;
    }

    public final void setValue(E e) {
        if (!Intrinsics.areEqual(this._value, e)) {
            this._value = e;
            invokeChange();
        }
    }

    public final void onChange(Function1<? super E, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.listeners.add(callback);
        callback.invoke(getValue());
    }

    public final void invokeChange() {
        Iterator<Function1<E, Unit>> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().invoke(this._value);
        }
    }

    public static /* synthetic */ void setValue$default(Monitorable monitorable, Object obj, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            z = true;
        }
        monitorable.setValue(obj, z);
    }

    public final void setValue(E v, boolean notifyAnyway) {
        this._value = v;
        if (notifyAnyway) {
            invokeChange();
        }
    }
}
