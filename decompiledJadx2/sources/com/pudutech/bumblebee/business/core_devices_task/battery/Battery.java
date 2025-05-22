package com.pudutech.bumblebee.business.core_devices_task.battery;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.SwitchInterface;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.BatteryListener;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: Battery.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010)\u001a\u00020*J\u001a\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\b2\b\u0010-\u001a\u0004\u0018\u00010\u0019H\u0016J\u0018\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\bH\u0016J1\u00100\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\b2!\u00101\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bRA\u0010\f\u001a)\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\u000f0\u000e0\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000bR\u001e\u0010#\u001a\u00020\"2\u0006\u0010\u0007\u001a\u00020\"@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/battery/Battery;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/SystemBatteryListener;", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/BatteryListener;", "()V", "TAG", "", "<set-?>", "", "batteryLevel", "getBatteryLevel", "()I", "batteryLevelListener", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Ljava/lang/ref/WeakReference;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "level", "", "getBatteryLevelListener", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "setBatteryLevelListener", "(Ljava/util/concurrent/CopyOnWriteArrayList;)V", "chargerState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "getChargerState", "()Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "setChargerState", "(Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;)V", "delayTurnOffSensors", "Ljava/lang/Runnable;", "powerPercent", "getPowerPercent", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/PowerState;", "powerState", "getPowerState", "()Lcom/pudutech/bumblebee/business/core_devices_task/battery/PowerState;", "sensors", "", "Lcom/pudutech/bumblebee/business/base/SwitchInterface;", "getRealBattery", "", "onBattery", "p0", "p1", "onBatteryFloorLevelLimitResult", "state", "setBatteryLevel", "onBatteryLevelListener", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Battery extends BaseMultiListenerImpl<SystemBatteryListener> implements BatteryListener {
    private final String TAG = "Battery";
    private int powerPercent = 100;
    private PowerState powerState = PowerState.NORMAL;
    private ChargeState chargerState = ChargeState.Idle;
    private int batteryLevel = 5;
    private CopyOnWriteArrayList<WeakReference<Function1<Integer, Unit>>> batteryLevelListener = new CopyOnWriteArrayList<>();
    private final Runnable delayTurnOffSensors = new Runnable() { // from class: com.pudutech.bumblebee.business.core_devices_task.battery.Battery$delayTurnOffSensors$1
        @Override // java.lang.Runnable
        public final void run() {
            List list;
            String str;
            Peripherals.INSTANCE.getPallet().turnSensorsOn(false);
            list = Battery.this.sensors;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((SwitchInterface) it.next()).turnOn(false);
            }
            str = Battery.this.TAG;
            Pdlog.m3273d(str, "sensors turnOn false");
        }
    };
    private final List<SwitchInterface> sensors = CollectionsKt.listOf((Object[]) new SwitchInterface[]{CoreDevices.INSTANCE.getIrled(), CoreDevices.INSTANCE.getRgbd()});

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v8, types: [T, com.pudutech.mirsdk.hardware.serialize.ChargeState] */
    /* JADX WARN: Type inference failed for: r2v16, types: [T, com.pudutech.bumblebee.business.core_devices_task.battery.PowerState] */
    /* JADX WARN: Type inference failed for: r5v1, types: [T, com.pudutech.mirsdk.hardware.serialize.ChargeState] */
    /* JADX WARN: Type inference failed for: r6v1, types: [T, com.pudutech.bumblebee.business.core_devices_task.battery.PowerState] */
    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.BatteryListener
    public void onBattery(final int p0, ChargeState p1) {
        PowerState powerState;
        Pdlog.m3273d(this.TAG, "percent=" + p0 + " charge state=" + p1);
        boolean z = this.powerPercent == p0;
        this.powerPercent = p0;
        int i = this.powerPercent;
        if (i > 10) {
            powerState = PowerState.NORMAL;
        } else if (i > 5) {
            powerState = PowerState.LOW_LEVEL1;
        } else if (i > 2) {
            powerState = PowerState.LOW_LEVEL2;
        } else {
            powerState = PowerState.EMPTY;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (PowerState) 0;
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = (ChargeState) 0;
        if (this.powerState != powerState) {
            this.powerState = powerState;
            objectRef.element = this.powerState;
        }
        if (this.chargerState != p1) {
            this.chargerState = p1;
            ?? r11 = this.chargerState;
            objectRef2.element = r11;
            if (r11 == ChargeState.Idle) {
                TimerThread.INSTANCE.remove(this.delayTurnOffSensors);
                Peripherals.INSTANCE.getPallet().turnSensorsOn(true);
                Iterator<T> it = this.sensors.iterator();
                while (it.hasNext()) {
                    ((SwitchInterface) it.next()).turnOn(true);
                }
                Pdlog.m3273d(this.TAG, "sensors turnOn true");
            } else {
                TimerThread.INSTANCE.post(this.delayTurnOffSensors, SolicitService.CAMERA_OPEN_TIME_OUT);
            }
        }
        if (((PowerState) objectRef.element) != null || ((ChargeState) objectRef2.element) != null) {
            Pdlog.m3273d(this.TAG, "改变ps=" + ((PowerState) objectRef.element) + " cs=" + ((ChargeState) objectRef2.element));
            getListeners().forEach(new Function1<SystemBatteryListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.battery.Battery$onBattery$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SystemBatteryListener systemBatteryListener) {
                    invoke2(systemBatteryListener);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SystemBatteryListener it2) {
                    Intrinsics.checkParameterIsNotNull(it2, "it");
                    it2.onStateChange(Battery.this.getPowerPercent(), (PowerState) objectRef.element, (ChargeState) objectRef2.element);
                }
            });
        }
        if (z) {
            return;
        }
        getListeners().forEach(new Function1<SystemBatteryListener, Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.battery.Battery$onBattery$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SystemBatteryListener systemBatteryListener) {
                invoke2(systemBatteryListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SystemBatteryListener it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                it2.onPowerChange(p0);
            }
        });
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.BatteryListener
    public void onBatteryFloorLevelLimitResult(int state, int level) {
        if (state == 1) {
            Iterator<T> it = this.batteryLevelListener.iterator();
            while (it.hasNext()) {
                Function1 function1 = (Function1) ((WeakReference) it.next()).get();
                if (function1 != null) {
                }
            }
            this.batteryLevelListener.clear();
        }
        this.batteryLevel = level;
    }

    public final int getPowerPercent() {
        return this.powerPercent;
    }

    public final PowerState getPowerState() {
        return this.powerState;
    }

    public final ChargeState getChargerState() {
        return this.chargerState;
    }

    public final void setChargerState(ChargeState chargeState) {
        this.chargerState = chargeState;
    }

    public final int getBatteryLevel() {
        return this.batteryLevel;
    }

    public final CopyOnWriteArrayList<WeakReference<Function1<Integer, Unit>>> getBatteryLevelListener() {
        return this.batteryLevelListener;
    }

    public final void setBatteryLevelListener(CopyOnWriteArrayList<WeakReference<Function1<Integer, Unit>>> copyOnWriteArrayList) {
        Intrinsics.checkParameterIsNotNull(copyOnWriteArrayList, "<set-?>");
        this.batteryLevelListener = copyOnWriteArrayList;
    }

    public final void setBatteryLevel(int level, Function1<? super Integer, Unit> onBatteryLevelListener) {
        Intrinsics.checkParameterIsNotNull(onBatteryLevelListener, "onBatteryLevelListener");
        SDK.INSTANCE.controlBatteryLevel(level);
        this.batteryLevelListener.add(new WeakReference<>(onBatteryLevelListener));
    }

    public final float getRealBattery() {
        int i = this.batteryLevel;
        return i + ((this.powerPercent * (100.0f - i)) / 100.0f);
    }
}
