package com.pudutech.bumblebee.business.core_devices_task.power_save;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.SwitchInterface;
import com.pudutech.bumblebee.business.base.SwitchListener;
import com.pudutech.bumblebee.business.base.SwitchListenerKt;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.robotsdk.RobotSetting;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PowerSaveTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t*\u0001\u0011\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\tH\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\u0019\u001a\u00020\u0014H\u0002J\u0006\u0010\u001a\u001a\u00020\u0014J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\u000e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveTask;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveListener;", "()V", "TAG", "", "event", "Lcom/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveEvent;", "isCounterOn", "", "sensors", "", "Lcom/pudutech/bumblebee/business/base/SwitchListener;", "sleepRunnable", "Ljava/lang/Runnable;", "standByRunnable", "switchListener", "com/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveTask$switchListener$1", "Lcom/pudutech/bumblebee/business/core_devices_task/power_save/PowerSaveTask$switchListener$1;", "checkAllDeviceReady", "", "enableCPU", "onOrOff", "executePowerSaveMode", "fsm", "quitPowerSaveMode", "resetTimer", "resetTimerAndClosePowerSaveMode", "setCountDown", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PowerSaveTask extends BaseMultiListenerImpl<PowerSaveListener> {
    private PowerSaveEvent event;
    private boolean isCounterOn;
    private final String TAG = "PowerSaveTask";
    private final List<BaseMultiListenerImpl<SwitchListener>> sensors = CollectionsKt.listOf((Object[]) new BaseMultiListenerImpl[]{CoreDevices.INSTANCE.getIrled(), CoreDevices.INSTANCE.getRgbd()});
    private final PowerSaveTask$switchListener$1 switchListener = new PowerSaveTask$switchListener$1(this);
    private final Runnable standByRunnable = new Runnable() { // from class: com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveTask$standByRunnable$1
        @Override // java.lang.Runnable
        public final void run() {
            String str;
            Runnable runnable;
            str = PowerSaveTask.this.TAG;
            Pdlog.m3275i(str, "on stand by");
            PowerSaveTask.this.executePowerSaveMode();
            PowerSaveTask.this.fsm(PowerSaveEvent.STAND_BY);
            TimerThread timerThread = TimerThread.INSTANCE;
            runnable = PowerSaveTask.this.sleepRunnable;
            timerThread.post(runnable, HardwareConfig.RGBDFwUpdateTimeOut);
        }
    };
    private final Runnable sleepRunnable = new Runnable() { // from class: com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveTask$sleepRunnable$1
        @Override // java.lang.Runnable
        public final void run() {
            String str;
            str = PowerSaveTask.this.TAG;
            Pdlog.m3275i(str, "on sleep");
            PowerSaveTask.this.fsm(PowerSaveEvent.SLEEP);
        }
    };

    private final void enableCPU(boolean onOrOff) {
    }

    public final void setCountDown(final boolean onOrOff) {
        Pdlog.m3275i(this.TAG, "actionTimerCount " + onOrOff + ' ' + this.event);
        TimerThread.INSTANCE.post(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveTask$setCountDown$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                String str;
                PowerSaveEvent powerSaveEvent;
                Runnable runnable;
                Runnable runnable2;
                str = PowerSaveTask.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("actionTimerCount ");
                sb.append(onOrOff);
                sb.append(' ');
                powerSaveEvent = PowerSaveTask.this.event;
                sb.append(powerSaveEvent);
                Pdlog.m3275i(str, sb.toString());
                if (onOrOff) {
                    PowerSaveTask.this.resetTimerAndClosePowerSaveMode();
                } else {
                    TimerThread timerThread = TimerThread.INSTANCE;
                    runnable = PowerSaveTask.this.standByRunnable;
                    timerThread.remove(runnable);
                    TimerThread timerThread2 = TimerThread.INSTANCE;
                    runnable2 = PowerSaveTask.this.sleepRunnable;
                    timerThread2.remove(runnable2);
                    PowerSaveTask.this.quitPowerSaveMode();
                }
                PowerSaveTask.this.isCounterOn = onOrOff;
            }
        });
    }

    public final void resetTimer() {
        Pdlog.m3273d(this.TAG, "resetTimer: ");
        TimerThread.INSTANCE.post(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveTask$resetTimer$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                boolean z;
                z = PowerSaveTask.this.isCounterOn;
                if (z) {
                    PowerSaveTask.this.resetTimerAndClosePowerSaveMode();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkAllDeviceReady() {
        Pdlog.m3273d(this.TAG, "checkAllDeviceReady: ");
        Iterator<T> it = this.sensors.iterator();
        while (it.hasNext()) {
            if (((BaseMultiListenerImpl) it.next()) == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.base.SwitchInterface");
            }
            if (!Intrinsics.areEqual((Object) ((SwitchInterface) r2).getIsRunning(), (Object) true)) {
                return;
            }
        }
        if (this.event == PowerSaveEvent.RESUMING) {
            fsm(PowerSaveEvent.DEVICES_READY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void quitPowerSaveMode() {
        Pdlog.m3273d(this.TAG, "quitPowerSaveMode event:" + this.event + ' ');
        if (this.event == PowerSaveEvent.SLEEP || this.event == PowerSaveEvent.STAND_BY || this.event == null) {
            Pdlog.m3275i(this.TAG, "quitPowerSaveMode");
            fsm(PowerSaveEvent.RESUMING);
            Peripherals.INSTANCE.getPallet().turnSensorsOn(true);
            enableCPU(true);
            Iterator<T> it = this.sensors.iterator();
            while (it.hasNext()) {
                ((BaseMultiListenerImpl) it.next()).addListener(this.switchListener);
            }
            Iterator<T> it2 = this.sensors.iterator();
            while (it2.hasNext()) {
                SwitchListenerKt.m4293switch((BaseMultiListenerImpl) it2.next()).turnOn(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void executePowerSaveMode() {
        Pdlog.m3275i(this.TAG, "executePowerSaveMode");
        Peripherals.INSTANCE.getPallet().turnSensorsOn(false);
        enableCPU(false);
        Iterator<T> it = this.sensors.iterator();
        while (it.hasNext()) {
            ((BaseMultiListenerImpl) it.next()).removeListener(this.switchListener);
        }
        Iterator<T> it2 = this.sensors.iterator();
        while (it2.hasNext()) {
            SwitchListenerKt.m4293switch((BaseMultiListenerImpl) it2.next()).turnOn(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fsm(final PowerSaveEvent event) {
        Pdlog.m3273d(this.TAG, "fsm this.event=" + this.event + " event=" + event);
        if (this.event == event) {
            return;
        }
        this.event = event;
        getListeners().forEach(new Function1<PowerSaveListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveTask$fsm$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PowerSaveListener powerSaveListener) {
                invoke2(powerSaveListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PowerSaveListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onEvent(PowerSaveEvent.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetTimerAndClosePowerSaveMode() {
        TimerThread.INSTANCE.remove(this.standByRunnable);
        TimerThread.INSTANCE.remove(this.sleepRunnable);
        long autoSleepDelay = RobotSetting.INSTANCE.getAutoSleepDelay();
        if (autoSleepDelay != -1) {
            TimerThread.INSTANCE.post(this.standByRunnable, autoSleepDelay);
        }
        quitPowerSaveMode();
    }
}
