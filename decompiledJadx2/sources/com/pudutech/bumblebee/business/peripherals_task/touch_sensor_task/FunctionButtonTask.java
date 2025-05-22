package com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task;

import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.ListenerList;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.bumblebee.business.movementTask.CruiseTask;
import com.pudutech.bumblebee.business.movementTask.DeliverTask;
import com.pudutech.bumblebee.business.movementTask.GoHomeTask;
import com.pudutech.bumblebee.business.movementTask.IdleTask;
import com.pudutech.bumblebee.business.movementTask.RecycleTask;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.TouchListener;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdkwrap.lib.robot.MachineInfoHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: FunctionButtonTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000b\"\u0004\b\u0019\u0010\rR\u001a\u0010\u001a\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\rR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/FunctionButtonTask;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/FunctionButtonListener;", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/TouchListener;", "()V", "FILTER_TIME_MS", "", "TAG", "", "cruisePauseKeepTime_ms", "getCruisePauseKeepTime_ms", "()J", "setCruisePauseKeepTime_ms", "(J)V", ES6Iterator.VALUE_PROPERTY, "", "isMute", "()Z", "setMute", "(Z)V", "isOnError", "setOnError", "lastDownTime", "notCruisePauseKeepTime_ms", "getNotCruisePauseKeepTime_ms", "setNotCruisePauseKeepTime_ms", "recyclePauseKeepTime_ms", "getRecyclePauseKeepTime_ms", "setRecyclePauseKeepTime_ms", "runnable", "Ljava/lang/Runnable;", "onTouch", "", "place", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchPlace;", "state", "Lcom/pudutech/bumblebee/robot/aidl/serialize/TouchState;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FunctionButtonTask extends BaseMultiListenerImpl<FunctionButtonListener> implements TouchListener {
    private boolean isMute;
    private boolean isOnError;
    private long lastDownTime;
    private final String TAG = "FunctionButtonTask";
    private final long FILTER_TIME_MS = 150;
    private final Runnable runnable = new Runnable() { // from class: com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonTask$runnable$1
        @Override // java.lang.Runnable
        public final void run() {
            String str;
            ListenerList listeners;
            if (FunctionButtonTask.this.getIsMute() || FunctionButtonTask.this.getIsOnError()) {
                str = FunctionButtonTask.this.TAG;
                Pdlog.m3275i(str, "isMute=" + FunctionButtonTask.this.getIsMute() + "  isOnError=" + FunctionButtonTask.this.getIsOnError());
                return;
            }
            Behavior.INSTANCE.runOnTaskThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonTask$runnable$1.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    String str2;
                    BaseTaskInterface movementTask;
                    String str3;
                    String str4;
                    str2 = FunctionButtonTask.this.TAG;
                    Pdlog.m3275i(str2, "onClick when task=" + Behavior.INSTANCE.getMovementTask());
                    if ((Behavior.INSTANCE.getMovementTask() instanceof IdleTask) || (movementTask = Behavior.INSTANCE.getMovementTask()) == null) {
                        return;
                    }
                    str3 = FunctionButtonTask.this.TAG;
                    Pdlog.m3275i(str3, "active=" + movementTask.getIsActive());
                    if (movementTask.getIsActive()) {
                        if ((movementTask instanceof GoHomeTask) || (movementTask instanceof DeliverTask)) {
                            movementTask.pause(FunctionButtonTask.this.getNotCruisePauseKeepTime_ms());
                            return;
                        }
                        if (movementTask instanceof CruiseTask) {
                            movementTask.pause(FunctionButtonTask.this.getCruisePauseKeepTime_ms());
                            return;
                        } else if (movementTask instanceof RecycleTask) {
                            movementTask.pause(FunctionButtonTask.this.getRecyclePauseKeepTime_ms());
                            return;
                        } else {
                            str4 = FunctionButtonTask.this.TAG;
                            Pdlog.m3274e(str4, "no define task type");
                            return;
                        }
                    }
                    movementTask.setActive(true);
                }
            });
            listeners = FunctionButtonTask.this.getListeners();
            listeners.forEach(new Function1<FunctionButtonListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonTask$runnable$1.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(FunctionButtonListener functionButtonListener) {
                    invoke2(functionButtonListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(FunctionButtonListener it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    it.onClick();
                }
            });
        }
    };
    private long cruisePauseKeepTime_ms = 20000;
    private long notCruisePauseKeepTime_ms = 10000;
    private long recyclePauseKeepTime_ms = 10000;

    @Override // com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.TouchListener
    public void onTouch(TouchPlace place, TouchState state) {
        Integer robotMinorVersion;
        Intrinsics.checkParameterIsNotNull(place, "place");
        Intrinsics.checkParameterIsNotNull(state, "state");
        if (place == TouchPlace.FunctionButton) {
            Pdlog.m3275i(this.TAG, "onTouch " + state);
            if (state == TouchState.DOWN) {
                if (SystemClock.elapsedRealtime() - this.lastDownTime > this.FILTER_TIME_MS * 2) {
                    TimerThread.INSTANCE.post(this.runnable, this.FILTER_TIME_MS);
                    this.lastDownTime = SystemClock.elapsedRealtime();
                    return;
                }
                Pdlog.m3277w(this.TAG, "second touch in short time.ignore it. " + (SystemClock.elapsedRealtime() - this.lastDownTime));
                return;
            }
            if (state == TouchState.UP) {
                if (SystemClock.elapsedRealtime() - this.lastDownTime > this.FILTER_TIME_MS || MachineInfoHelper.INSTANCE.getRobotType() != MachineModel.BellaBot) {
                    return;
                }
                Integer robotMinorVersion2 = MachineInfoHelper.INSTANCE.getRobotMinorVersion();
                if ((robotMinorVersion2 != null && robotMinorVersion2.intValue() == 0) || ((robotMinorVersion = MachineInfoHelper.INSTANCE.getRobotMinorVersion()) != null && robotMinorVersion.intValue() == 1)) {
                    TimerThread.INSTANCE.remove(this.runnable);
                    Pdlog.m3277w(this.TAG, "touch too short.ignore it.  " + (SystemClock.elapsedRealtime() - this.lastDownTime));
                    return;
                }
                return;
            }
            Pdlog.m3274e(this.TAG, "not define touch state type");
        }
    }

    /* renamed from: isMute, reason: from getter */
    public final boolean getIsMute() {
        return this.isMute;
    }

    public final void setMute(boolean z) {
        this.isMute = z;
        Pdlog.m3275i(this.TAG, "set isMute=" + z);
    }

    /* renamed from: isOnError, reason: from getter */
    public final boolean getIsOnError() {
        return this.isOnError;
    }

    public final void setOnError(boolean z) {
        this.isOnError = z;
        Pdlog.m3275i(this.TAG, "set isOnError=" + z);
    }

    public final long getCruisePauseKeepTime_ms() {
        return this.cruisePauseKeepTime_ms;
    }

    public final void setCruisePauseKeepTime_ms(long j) {
        this.cruisePauseKeepTime_ms = j;
    }

    public final long getNotCruisePauseKeepTime_ms() {
        return this.notCruisePauseKeepTime_ms;
    }

    public final void setNotCruisePauseKeepTime_ms(long j) {
        this.notCruisePauseKeepTime_ms = j;
    }

    public final long getRecyclePauseKeepTime_ms() {
        return this.recyclePauseKeepTime_ms;
    }

    public final void setRecyclePauseKeepTime_ms(long j) {
        this.recyclePauseKeepTime_ms = j;
    }
}
