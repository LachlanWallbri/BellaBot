package com.pudutech.disinfect.baselib.ext.lifecycle;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: LifecycleExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u001aC\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u00052!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u0007\u001a\u001e\u0010\u000b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001a5\u0010\u000b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\f\"\b\u0012\u0004\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\r¨\u0006\u000e"}, m3961d2 = {"observe", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/LifecycleOwner;", "liveData", "Landroidx/lifecycle/LiveData;", "observer", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "t", "removeObservers", "", "(Landroidx/lifecycle/LifecycleOwner;[Landroidx/lifecycle/LiveData;)V", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LifecycleExtKt {
    public static final <T> void observe(LifecycleOwner observe, LiveData<T> liveData, final Function1<? super T, Unit> observer) {
        Intrinsics.checkParameterIsNotNull(observe, "$this$observe");
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        if (liveData != null) {
            liveData.observe(observe, new Observer() { // from class: com.pudutech.disinfect.baselib.ext.lifecycle.LifecycleExtKt$sam$androidx_lifecycle_Observer$0
                @Override // androidx.lifecycle.Observer
                public final /* synthetic */ void onChanged(Object obj) {
                    Intrinsics.checkExpressionValueIsNotNull(Function1.this.invoke(obj), "invoke(...)");
                }
            });
        }
    }

    public static final <T> void removeObservers(LifecycleOwner removeObservers, LiveData<T> liveData) {
        Intrinsics.checkParameterIsNotNull(removeObservers, "$this$removeObservers");
        Intrinsics.checkParameterIsNotNull(liveData, "liveData");
        liveData.removeObservers(removeObservers);
    }

    public static final <T> void removeObservers(LifecycleOwner removeObservers, LiveData<T>... liveData) {
        Intrinsics.checkParameterIsNotNull(removeObservers, "$this$removeObservers");
        Intrinsics.checkParameterIsNotNull(liveData, "liveData");
        for (LiveData<T> liveData2 : liveData) {
            liveData2.removeObservers(removeObservers);
        }
    }
}
