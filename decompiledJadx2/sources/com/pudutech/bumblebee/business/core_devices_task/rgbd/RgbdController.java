package com.pudutech.bumblebee.business.core_devices_task.rgbd;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: RgbdController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010 \u001a\u00020\u0017J\b\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u0017H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006%"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/rgbd/RgbdController;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/base/SwitchListener;", "Lcom/pudutech/bumblebee/business/base/SwitchInterface;", "()V", "TAG", "", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "controller", "getController$module_bumblebee_business_robotRelease", "()Lcom/pudutech/mirsdk/aidl/DeviceInterface;", "setController$module_bumblebee_business_robotRelease", "(Lcom/pudutech/mirsdk/aidl/DeviceInterface;)V", "delayRetryRunnable", "Ljava/lang/Runnable;", "deviceListener", "Lcom/pudutech/bumblebee/business/core_devices_task/IDeviceListenerDispatcher;", "getDeviceListener", "()Lcom/pudutech/bumblebee/business/core_devices_task/IDeviceListenerDispatcher;", "deviceListener$delegate", "Lkotlin/Lazy;", "isOn", "", "()Ljava/lang/Boolean;", "isRunning", "Ljava/lang/Boolean;", "isSwitchOn", "retryTime_ms", "", "getRetryTime_ms", "()J", "checkClean", "fsm", "", "turnOn", "onOrOff", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RgbdController extends BaseMultiListenerImpl<SwitchListener> implements SwitchInterface {
    private DeviceInterface controller;
    private Boolean isRunning;
    private boolean isSwitchOn;
    private final String TAG = "RgbdController";

    /* renamed from: deviceListener$delegate, reason: from kotlin metadata */
    private final Lazy deviceListener = LazyKt.lazy(new RgbdController$deviceListener$2(this));
    private final Runnable delayRetryRunnable = new Runnable() { // from class: com.pudutech.bumblebee.business.core_devices_task.rgbd.RgbdController$delayRetryRunnable$1
        @Override // java.lang.Runnable
        public final void run() {
            RgbdController.this.fsm();
        }
    };

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

    /* JADX INFO: Access modifiers changed from: private */
    public final void fsm() {
        Pdlog.m3275i(this.TAG, "fsm isSwitchOn=" + this.isSwitchOn + " isRunning=" + this.isRunning);
        TimerThread.INSTANCE.post(new Function0<Unit>() { // from class: com.pudutech.bumblebee.business.core_devices_task.rgbd.RgbdController$fsm$1
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
                Runnable runnable;
                boolean z;
                Boolean bool;
                String str;
                Runnable runnable2;
                boolean z2;
                TimerThread timerThread = TimerThread.INSTANCE;
                runnable = RgbdController.this.delayRetryRunnable;
                timerThread.remove(runnable);
                z = RgbdController.this.isSwitchOn;
                Boolean valueOf = Boolean.valueOf(z);
                bool = RgbdController.this.isRunning;
                if (!Intrinsics.areEqual(valueOf, bool)) {
                    str = RgbdController.this.TAG;
                    Pdlog.m3275i(str, "try again");
                    DeviceInterface controller = RgbdController.this.getController();
                    if (controller != null) {
                        z2 = RgbdController.this.isSwitchOn;
                        controller.controlRGBD(z2);
                    }
                    TimerThread timerThread2 = TimerThread.INSTANCE;
                    runnable2 = RgbdController.this.delayRetryRunnable;
                    timerThread2.post(runnable2, RgbdController.this.getRetryTime_ms());
                }
            }
        });
    }

    public final boolean checkClean() {
        DeviceInterface deviceInterface = this.controller;
        if (deviceInterface != null) {
            return deviceInterface.rgbdOilStainCheck();
        }
        return true;
    }

    @Override // com.pudutech.bumblebee.business.base.SwitchInterface
    public void turnOn(boolean onOrOff) {
        Pdlog.m3273d(this.TAG, "turnOn onOrOff=" + onOrOff);
        if (onOrOff) {
            DevicesControlHelper deviceControlHelper = MirSdkManager.INSTANCE.getDeviceControlHelper();
            if (deviceControlHelper != null) {
                deviceControlHelper.openDevice(DevicesControlHelper.DeviceName.RGBD);
                return;
            }
            return;
        }
        DevicesControlHelper deviceControlHelper2 = MirSdkManager.INSTANCE.getDeviceControlHelper();
        if (deviceControlHelper2 != null) {
            deviceControlHelper2.closeDevice(CollectionsKt.arrayListOf(DevicesControlHelper.DeviceName.RGBD));
        }
    }
}
