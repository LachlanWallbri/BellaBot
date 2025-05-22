package com.pudutech.bumblebee.business.core_devices_task.camera;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.base.SwitchInterface;
import com.pudutech.bumblebee.business.base.SwitchListener;
import com.pudutech.bumblebee.business.core_devices_task.IDeviceListenerDispatcher;
import com.pudutech.mirsdk.aidl.DeviceInterface;
import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: MarkerCameraController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001f\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0002\u0010\"J\u0010\u0010!\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0015H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006$"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/camera/MarkerCameraController;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/base/SwitchListener;", "Lcom/pudutech/bumblebee/business/base/SwitchInterface;", "()V", "TAG", "", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "controller", "getController$module_bumblebee_business_robotRelease", "()Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "setController$module_bumblebee_business_robotRelease", "(Lcom/pudutech/mirsdk/aidl/DeviceInterface;)V", "deviceListener", "Lcom/pudutech/bumblebee/business/core_devices_task/IDeviceListenerDispatcher;", "getDeviceListener", "()Lcom/pudutech/bumblebee/business/core_devices_task/IDeviceListenerDispatcher;", "deviceListener$delegate", "Lkotlin/Lazy;", "isOn", "", "()Ljava/lang/Boolean;", "isRunning", "Ljava/lang/Boolean;", "isSwitchOn", "objSting", "retryTime_ms", "", "getRetryTime_ms", "()J", "fsm", "", "turnOn", "(ZLjava/lang/Boolean;)V", "onOrOff", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MarkerCameraController extends BaseMultiListenerImpl<SwitchListener> implements SwitchInterface {
    private DeviceInterface controller;
    private Boolean isRunning;
    private boolean isSwitchOn;
    private final String TAG = "MarkerCameraController";
    private final String objSting = toString();

    /* renamed from: deviceListener$delegate, reason: from kotlin metadata */
    private final Lazy deviceListener = LazyKt.lazy(new MarkerCameraController$deviceListener$2(this));

    private final IDeviceListenerDispatcher getDeviceListener() {
        return (IDeviceListenerDispatcher) this.deviceListener.getValue();
    }

    @Override // com.pudutech.bumblebee.business.base.SwitchInterface
    public long getRetryTime_ms() {
        return 2000L;
    }

    @Override // com.pudutech.bumblebee.business.base.SwitchInterface
    /* renamed from: isOn, reason: from getter */
    public Boolean getIsRunning() {
        return this.isRunning;
    }

    /* renamed from: getController$module_bumblebee_business_robotRelease, reason: from getter */
    public final DeviceInterface getController() {
        return this.controller;
    }

    public final void setController$module_bumblebee_business_robotRelease(DeviceInterface deviceInterface) {
        DeviceInterface deviceInterface2 = this.controller;
        if (deviceInterface2 != null) {
            deviceInterface2.removeListener(this.TAG);
        }
        this.controller = deviceInterface;
        DeviceInterface deviceInterface3 = this.controller;
        if (deviceInterface3 != null) {
            deviceInterface3.addListener(this.TAG, getDeviceListener());
        }
    }

    @Override // com.pudutech.bumblebee.business.base.SwitchInterface
    public void turnOn(boolean onOrOff) {
        Pdlog.m3273d(this.TAG, "turnOn onOrOff=" + onOrOff);
        if (onOrOff) {
            DevicesControlHelper deviceControlHelper = MirSdkManager.INSTANCE.getDeviceControlHelper();
            if (deviceControlHelper != null) {
                deviceControlHelper.openDevice(DevicesControlHelper.DeviceName.MARKER_CAMERA);
                return;
            }
            return;
        }
        DevicesControlHelper deviceControlHelper2 = MirSdkManager.INSTANCE.getDeviceControlHelper();
        if (deviceControlHelper2 != null) {
            deviceControlHelper2.closeDevice(CollectionsKt.arrayListOf(DevicesControlHelper.DeviceName.MARKER_CAMERA));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fsm(boolean turnOn, Boolean isRunning) {
        if (!Intrinsics.areEqual(Boolean.valueOf(turnOn), isRunning)) {
            Pdlog.m3275i(this.TAG, "try again");
            if (turnOn) {
                DeviceInterface deviceInterface = this.controller;
                if (deviceInterface != null) {
                    deviceInterface.runMarkerCamera();
                    return;
                }
                return;
            }
            DeviceInterface deviceInterface2 = this.controller;
            if (deviceInterface2 != null) {
                deviceInterface2.stopMarkerCamera();
            }
        }
    }
}
