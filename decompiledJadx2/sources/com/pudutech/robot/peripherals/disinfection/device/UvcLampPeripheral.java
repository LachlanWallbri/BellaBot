package com.pudutech.robot.peripherals.disinfection.device;

import com.pudutech.base.Pdlog;
import com.pudutech.robot.peripherals.disinfection.DeviceName;
import com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals;
import com.pudutech.robot.peripherals.disinfection.UvcLampDeviceError;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* compiled from: UvcLampPeripheral.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\f\u001a\u00020\rH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0010j\b\u0012\u0004\u0012\u00020\u0005`\u0011J!\u0010\u0012\u001a\u00020\r2\u0010\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u0014H\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\rH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/device/UvcLampPeripheral;", "Lcom/pudutech/robot/peripherals/disinfection/device/BasePeripheral;", "()V", "errorList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;", "lightUvcLamp", "", "getLightUvcLamp", "()Z", "setLightUvcLamp", "(Z)V", "closeDevice", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getErrorList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "onError", "p0", "", "onError$module_robot_peripherals_release", "([Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;)V", "openDevice", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class UvcLampPeripheral extends BasePeripheral {
    private static final String TAG = "UvcLampPeripheral_New";
    private boolean lightUvcLamp = true;
    private final CopyOnWriteArrayList<UvcLampDeviceError> errorList = new CopyOnWriteArrayList<>();

    public final boolean getLightUvcLamp() {
        return this.lightUvcLamp;
    }

    public final void setLightUvcLamp(boolean z) {
        this.lightUvcLamp = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.robot.peripherals.disinfection.device.BasePeripheral
    public Object closeDevice(Continuation<? super Unit> continuation) {
        Unit unit;
        Pdlog.m3273d(TAG, "peripherals closeDevice");
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.closeDevice(DeviceName.UvCLampDevice);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.robot.peripherals.disinfection.device.BasePeripheral
    public Object openDevice(Continuation<? super Unit> continuation) {
        Unit unit;
        Pdlog.m3273d(TAG, "peripherals openDevice");
        this.errorList.clear();
        IDisinfectRobotPeripherals disinfectDeviceRobotPeripherals = DisinfectRobotDeviceManager.INSTANCE.getDisinfectDeviceRobotPeripherals();
        if (disinfectDeviceRobotPeripherals != null) {
            disinfectDeviceRobotPeripherals.openUvcLampDevice(true, this.lightUvcLamp);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        return unit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
    }

    public final void onError$module_robot_peripherals_release(UvcLampDeviceError[] p0) {
        if (p0 != null) {
            Pdlog.m3273d(TAG, "peripherals onError " + p0);
            this.errorList.clear();
            this.errorList.addAll(ArraysKt.toList(p0));
        }
    }

    public final ArrayList<UvcLampDeviceError> getErrorList() {
        ArrayList<UvcLampDeviceError> arrayList = new ArrayList<>();
        arrayList.addAll(this.errorList);
        return arrayList;
    }
}
