package com.youth.banner.util;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

/* loaded from: classes7.dex */
public class BannerLifecycleObserverAdapter implements LifecycleObserver {
    private final LifecycleOwner mLifecycleOwner;
    private final BannerLifecycleObserver mObserver;

    public BannerLifecycleObserverAdapter(LifecycleOwner lifecycleOwner, BannerLifecycleObserver bannerLifecycleObserver) {
        this.mLifecycleOwner = lifecycleOwner;
        this.mObserver = bannerLifecycleObserver;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        LogUtils.m3895i("onStart");
        this.mObserver.onStart(this.mLifecycleOwner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        LogUtils.m3895i("onStop");
        this.mObserver.onStop(this.mLifecycleOwner);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        LogUtils.m3895i("onDestroy");
        this.mObserver.onDestroy(this.mLifecycleOwner);
    }
}
