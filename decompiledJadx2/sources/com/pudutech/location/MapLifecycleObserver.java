package com.pudutech.location;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import kotlin.Metadata;
import org.apache.commons.codec.language.bm.Languages;

/* compiled from: LocationListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0017J\b\u0010\u0004\u001a\u00020\u0003H\u0017J\b\u0010\u0005\u001a\u00020\u0003H\u0017J\b\u0010\u0006\u001a\u00020\u0003H\u0017J\b\u0010\u0007\u001a\u00020\u0003H\u0017J\b\u0010\b\u001a\u00020\u0003H\u0017J\b\u0010\t\u001a\u00020\u0003H\u0017¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/location/MapLifecycleObserver;", "Landroidx/lifecycle/LifecycleObserver;", Languages.ANY, "", "create", "destroy", "pause", "resume", "start", "stop", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface MapLifecycleObserver extends LifecycleObserver {

    /* compiled from: LocationListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        public static void any(MapLifecycleObserver mapLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public static void create(MapLifecycleObserver mapLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public static void destroy(MapLifecycleObserver mapLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public static void pause(MapLifecycleObserver mapLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public static void resume(MapLifecycleObserver mapLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public static void start(MapLifecycleObserver mapLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public static void stop(MapLifecycleObserver mapLifecycleObserver) {
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void any();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void create();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void destroy();

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void pause();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void resume();

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start();

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop();
}
