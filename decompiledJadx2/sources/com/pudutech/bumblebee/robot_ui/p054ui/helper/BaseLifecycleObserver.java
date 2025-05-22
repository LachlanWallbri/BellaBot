package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import kotlin.Metadata;
import org.apache.commons.codec.language.bm.Languages;

/* compiled from: BaseLifecycleObserver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0017J\b\u0010\u0004\u001a\u00020\u0003H\u0017J\b\u0010\u0005\u001a\u00020\u0003H\u0017J\b\u0010\u0006\u001a\u00020\u0003H\u0017J\b\u0010\u0007\u001a\u00020\u0003H\u0017J\b\u0010\b\u001a\u00020\u0003H\u0017J\b\u0010\t\u001a\u00020\u0003H\u0017¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/BaseLifecycleObserver;", "Landroidx/lifecycle/LifecycleObserver;", Languages.ANY, "", "create", "destroy", "pause", "resume", "start", "stop", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public interface BaseLifecycleObserver extends LifecycleObserver {

    /* compiled from: BaseLifecycleObserver.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        public static void any(BaseLifecycleObserver baseLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        public static void create(BaseLifecycleObserver baseLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public static void destroy(BaseLifecycleObserver baseLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public static void pause(BaseLifecycleObserver baseLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public static void resume(BaseLifecycleObserver baseLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public static void start(BaseLifecycleObserver baseLifecycleObserver) {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public static void stop(BaseLifecycleObserver baseLifecycleObserver) {
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
