package com.pudutech.mirsdkwrap.lib.robot.device;

import com.pudutech.mirsdk.aidl.DeviceInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DisinfectionPowerDeviceControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u000f\u001a\u00020\u0010H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0010H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8T@TX\u0094\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/robot/device/DisinfectionPowerDeviceControl;", "Lcom/pudutech/mirsdkwrap/lib/robot/device/BaseDeviceControl;", "deviceInterface", "Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "(Lcom/pudutech/mirsdk/aidl/DeviceInterface;)V", "TAG", "", "getTAG", "()Ljava/lang/String;", ES6Iterator.VALUE_PROPERTY, "", "isCheckOpenResult", "()Z", "setCheckOpenResult", "(Z)V", "closeDevice", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openDevice", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DisinfectionPowerDeviceControl extends BaseDeviceControl {
    private final String TAG;
    private final DeviceInterface deviceInterface;

    @Override // com.pudutech.mirsdkwrap.lib.robot.device.BaseDeviceControl
    /* renamed from: isCheckOpenResult */
    protected boolean getIsCheckOpenResult() {
        return false;
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.device.BaseDeviceControl
    protected void setCheckOpenResult(boolean z) {
    }

    public DisinfectionPowerDeviceControl(DeviceInterface deviceInterface) {
        Intrinsics.checkParameterIsNotNull(deviceInterface, "deviceInterface");
        this.deviceInterface = deviceInterface;
        this.TAG = "DisinfectionPowerDeviceControl";
    }

    @Override // com.pudutech.mirsdkwrap.lib.robot.device.BaseDeviceControl
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.robot.device.BaseDeviceControl
    public Object openDevice(Continuation<? super Unit> continuation) {
        this.deviceInterface.runDisinfection();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.mirsdkwrap.lib.robot.device.BaseDeviceControl
    public Object closeDevice(Continuation<? super Unit> continuation) {
        this.deviceInterface.stopDisinfection();
        return Unit.INSTANCE;
    }
}
