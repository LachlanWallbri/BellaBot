package com.pudutech.bumblebee.business.core_devices_task;

import com.pudutech.mirsdk.aidl.IDeviceListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: IDeviceListenerDispatcher.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u001c\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010\u0003\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010 \u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010\"\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010#\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010$\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0005H\u0016R7\u0010\u0003\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR7\u0010\u000e\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR7\u0010\u0012\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000b\"\u0004\b\u0014\u0010\rR7\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\rR7\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000b\"\u0004\b\u001a\u0010\rR7\u0010\u001b\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000b\"\u0004\b\u001d\u0010\r¨\u0006%"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/core_devices_task/IDeviceListenerDispatcher;", "Lcom/pudutech/mirsdk/aidl/IDeviceListener$Stub;", "()V", "onCollision", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "p0", "", "getOnCollision", "()Lkotlin/jvm/functions/Function1;", "setOnCollision", "(Lkotlin/jvm/functions/Function1;)V", "onEmergencyKeyPressed", "isPressed", "getOnEmergencyKeyPressed", "setOnEmergencyKeyPressed", "onIRLEDMethod", "getOnIRLEDMethod", "setOnIRLEDMethod", "onLidarMethod", "getOnLidarMethod", "setOnLidarMethod", "onMarkerCameraMethod", "getOnMarkerCameraMethod", "setOnMarkerCameraMethod", "onRGBDMethod", "getOnRGBDMethod", "setOnRGBDMethod", "onBumperSwitchStatus", "onDisinfectionPower", "onIRLED", "onLidar", "onLockMotor", "onMarkerCamera", "onRGBD", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IDeviceListenerDispatcher extends IDeviceListener.Stub {
    private Function1<? super Boolean, Unit> onCollision;
    private Function1<? super Boolean, Unit> onEmergencyKeyPressed;
    private Function1<? super Boolean, Unit> onIRLEDMethod;
    private Function1<? super Boolean, Unit> onLidarMethod;
    private Function1<? super Boolean, Unit> onMarkerCameraMethod;
    private Function1<? super Boolean, Unit> onRGBDMethod;

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onBumperSwitchStatus(boolean p0) {
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onDisinfectionPower(boolean p0) {
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onLockMotor(boolean p0) {
    }

    public final Function1<Boolean, Unit> getOnLidarMethod() {
        return this.onLidarMethod;
    }

    public final void setOnLidarMethod(Function1<? super Boolean, Unit> function1) {
        this.onLidarMethod = function1;
    }

    public final Function1<Boolean, Unit> getOnIRLEDMethod() {
        return this.onIRLEDMethod;
    }

    public final void setOnIRLEDMethod(Function1<? super Boolean, Unit> function1) {
        this.onIRLEDMethod = function1;
    }

    public final Function1<Boolean, Unit> getOnRGBDMethod() {
        return this.onRGBDMethod;
    }

    public final void setOnRGBDMethod(Function1<? super Boolean, Unit> function1) {
        this.onRGBDMethod = function1;
    }

    public final Function1<Boolean, Unit> getOnMarkerCameraMethod() {
        return this.onMarkerCameraMethod;
    }

    public final void setOnMarkerCameraMethod(Function1<? super Boolean, Unit> function1) {
        this.onMarkerCameraMethod = function1;
    }

    public final Function1<Boolean, Unit> getOnCollision() {
        return this.onCollision;
    }

    public final void setOnCollision(Function1<? super Boolean, Unit> function1) {
        this.onCollision = function1;
    }

    public final Function1<Boolean, Unit> getOnEmergencyKeyPressed() {
        return this.onEmergencyKeyPressed;
    }

    public final void setOnEmergencyKeyPressed(Function1<? super Boolean, Unit> function1) {
        this.onEmergencyKeyPressed = function1;
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onLidar(boolean p0) {
        Function1<? super Boolean, Unit> function1 = this.onLidarMethod;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(p0));
        }
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onIRLED(boolean p0) {
        Function1<? super Boolean, Unit> function1 = this.onIRLEDMethod;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(p0));
        }
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onEmergencyKeyPressed(boolean isPressed) {
        Function1<? super Boolean, Unit> function1 = this.onEmergencyKeyPressed;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(isPressed));
        }
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onRGBD(boolean p0) {
        Function1<? super Boolean, Unit> function1 = this.onRGBDMethod;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(p0));
        }
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onMarkerCamera(boolean p0) {
        Function1<? super Boolean, Unit> function1 = this.onMarkerCameraMethod;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(p0));
        }
    }

    @Override // com.pudutech.mirsdk.aidl.IDeviceListener
    public void onCollision(boolean p0) {
        Function1<? super Boolean, Unit> function1 = this.onCollision;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(p0));
        }
    }
}
