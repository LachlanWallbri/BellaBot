package com.pudutech.bumblebee.business.behavior;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreenListener;
import com.pudutech.bumblebee.business.robotsdk.RobotPeripherals;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.TouchListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DelayResumeActive.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000G\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0003\u0007\f\u0015\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u001d\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u001bJ\b\u0010\u001c\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/behavior/DelayResumeActive;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/behavior/DelayResumeListener;", "()V", "TAG", "", "lcdListener", "com/pudutech/bumblebee/business/behavior/DelayResumeActive$lcdListener$1", "Lcom/pudutech/bumblebee/business/behavior/DelayResumeActive$lcdListener$1;", "loop", "Ljava/lang/Runnable;", "spdListener", "com/pudutech/bumblebee/business/behavior/DelayResumeActive$spdListener$1", "Lcom/pudutech/bumblebee/business/behavior/DelayResumeActive$spdListener$1;", "task", "Ljava/lang/ref/WeakReference;", "Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", "timeLeft_ms", "", "time_ms", "touchListener", "com/pudutech/bumblebee/business/behavior/DelayResumeActive$touchListener$1", "Lcom/pudutech/bumblebee/business/behavior/DelayResumeActive$touchListener$1;", "cancelTask", "", "loopTask", "post", "post$module_bumblebee_business_robotRelease", "resetTimer", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DelayResumeActive extends BaseMultiListenerImpl<DelayResumeListener> {
    private static WeakReference<BaseTaskInterface> task;
    private static long timeLeft_ms;
    public static final DelayResumeActive INSTANCE = new DelayResumeActive();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static long time_ms = 1000;
    private static final Runnable loop = new Runnable() { // from class: com.pudutech.bumblebee.business.behavior.DelayResumeActive$loop$1
        @Override // java.lang.Runnable
        public final void run() {
            DelayResumeActive.INSTANCE.loopTask();
        }
    };
    private static final DelayResumeActive$spdListener$1 spdListener = new SpeedListener() { // from class: com.pudutech.bumblebee.business.behavior.DelayResumeActive$spdListener$1
        private final double MOVING_MPS = 0.02d;
        private final double ROTATE_RPS = 0.1d;

        @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener
        public void onSpeed(double p0, double p1) {
            if (Math.abs(p0) > this.MOVING_MPS || Math.abs(p1) > this.ROTATE_RPS) {
                Behavior.INSTANCE.runOnTaskThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.behavior.DelayResumeActive$spdListener$1$onSpeed$1
                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        DelayResumeActive.INSTANCE.resetTimer();
                    }
                });
            }
        }
    };
    private static final DelayResumeActive$touchListener$1 touchListener = new TouchListener() { // from class: com.pudutech.bumblebee.business.behavior.DelayResumeActive$touchListener$1
        @Override // com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.TouchListener
        public void onTouch(TouchPlace place, TouchState state) {
            Intrinsics.checkParameterIsNotNull(place, "place");
            Intrinsics.checkParameterIsNotNull(state, "state");
            Behavior.INSTANCE.runOnTaskThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.behavior.DelayResumeActive$touchListener$1$onTouch$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    DelayResumeActive.INSTANCE.resetTimer();
                }
            });
        }
    };
    private static final DelayResumeActive$lcdListener$1 lcdListener = new LCDScreenListener() { // from class: com.pudutech.bumblebee.business.behavior.DelayResumeActive$lcdListener$1
        @Override // com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreenListener
        public void onTouchEvent() {
            DelayResumeActive.INSTANCE.resetTimer();
        }
    };

    private DelayResumeActive() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loopTask() {
        BaseTaskInterface baseTaskInterface;
        Pdlog.m3273d(TAG, "loopTask timeLeft_ms=" + timeLeft_ms);
        if (timeLeft_ms > 0) {
            getListeners().forEach(new Function1<DelayResumeListener, Unit>() { // from class: com.pudutech.bumblebee.business.behavior.DelayResumeActive$loopTask$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(DelayResumeListener delayResumeListener) {
                    invoke2(delayResumeListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(DelayResumeListener it) {
                    long j;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    DelayResumeActive delayResumeActive = DelayResumeActive.INSTANCE;
                    j = DelayResumeActive.timeLeft_ms;
                    it.onCountDownTimeChange(j);
                }
            });
            Behavior.INSTANCE.runOnTaskThread(100L, loop);
            long j = timeLeft_ms;
            long j2 = 100;
            timeLeft_ms = j > j2 ? j - j2 : 0L;
            return;
        }
        WeakReference<BaseTaskInterface> weakReference = task;
        if (weakReference != null && (baseTaskInterface = weakReference.get()) != null) {
            baseTaskInterface.setActive(true);
        }
        task = (WeakReference) null;
        Behavior.INSTANCE.removeTaskDelayOnTaskThread(loop);
    }

    public final void post$module_bumblebee_business_robotRelease(BaseTaskInterface task2, long time_ms2) {
        Intrinsics.checkParameterIsNotNull(task2, "task");
        Pdlog.m3273d(TAG, "post delay active time_ms=" + time_ms2);
        Behavior.INSTANCE.removeTaskDelayOnTaskThread(loop);
        SDK.INSTANCE.getSpeedListeners().addListener(spdListener);
        RobotPeripherals.INSTANCE.getTouchListeners().addListener(touchListener);
        Peripherals.INSTANCE.getLcd().addListener(lcdListener);
        task = new WeakReference<>(task2);
        time_ms = time_ms2;
        timeLeft_ms = time_ms2;
        loopTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetTimer() {
        Pdlog.m3273d(TAG, "reset timer");
        WeakReference<BaseTaskInterface> weakReference = task;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        Behavior.INSTANCE.removeTaskDelayOnTaskThread(loop);
        timeLeft_ms = time_ms;
        INSTANCE.loopTask();
    }

    public final void cancelTask() {
        WeakReference<BaseTaskInterface> weakReference = task;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        Behavior.INSTANCE.removeTaskDelayOnTaskThread(loop);
        SDK.INSTANCE.getSpeedListeners().removeListener(spdListener);
        RobotPeripherals.INSTANCE.getTouchListeners().removeListener(touchListener);
        Peripherals.INSTANCE.getLcd().removeListener(lcdListener);
        task = (WeakReference) null;
        Pdlog.m3273d(TAG, "cancel task");
    }
}
