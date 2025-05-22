package com.pudutech.disinfect.baselib.ext.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.pudutech.disinfect.baselib.callback.livedata.BooleanLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: KtxAppLifeObserver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0003J\b\u0010\n\u001a\u00020\tH\u0003R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/ext/lifecycle/KtxAppLifeObserver;", "Landroidx/lifecycle/LifecycleObserver;", "()V", "isForeground", "Lcom/pudutech/disinfect/baselib/callback/livedata/BooleanLiveData;", "()Lcom/pudutech/disinfect/baselib/callback/livedata/BooleanLiveData;", "setForeground", "(Lcom/pudutech/disinfect/baselib/callback/livedata/BooleanLiveData;)V", "obBackground", "", "onForeground", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class KtxAppLifeObserver implements LifecycleObserver {
    private BooleanLiveData isForeground = new BooleanLiveData();

    /* renamed from: isForeground, reason: from getter */
    public final BooleanLiveData getIsForeground() {
        return this.isForeground;
    }

    public final void setForeground(BooleanLiveData booleanLiveData) {
        Intrinsics.checkParameterIsNotNull(booleanLiveData, "<set-?>");
        this.isForeground = booleanLiveData;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private final void onForeground() {
        this.isForeground.setValue(true);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private final void obBackground() {
        this.isForeground.setValue(false);
    }
}
