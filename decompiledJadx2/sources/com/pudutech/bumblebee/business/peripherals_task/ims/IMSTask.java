package com.pudutech.bumblebee.business.peripherals_task.ims;

import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.robotsdk.RobotPeripherals;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.DeviceStatusChangedListener;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.MsgReceivedListener;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IMSTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u000b\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\fJ\u0006\u0010\r\u001a\u00020\bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0013\u001a\u00020\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/ims/IMSTask;", "", "()V", "robotInterface", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "robotPeripherals", "Lcom/pudutech/bumblebee/business/robotsdk/RobotPeripherals;", "addDeviceStatusChangedListener", "", "listener", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/DeviceStatusChangedListener;", "addMsgReceivedListener", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/MsgReceivedListener;", "flush", "getDeviceStatus", "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDeviceStatus;", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/bumblebee/robot/aidl/serialize/PeripheralDevice;", "init", "sendMsg", "bytes", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IMSTask {
    private RobotInterface robotInterface;
    private RobotPeripherals robotPeripherals;

    public final void init(RobotInterface robotInterface, RobotPeripherals robotPeripherals) {
        Intrinsics.checkParameterIsNotNull(robotPeripherals, "robotPeripherals");
        this.robotInterface = robotInterface;
        this.robotPeripherals = robotPeripherals;
    }

    public final PeripheralDeviceStatus getDeviceStatus(PeripheralDevice device) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        RobotInterface robotInterface = this.robotInterface;
        if (robotInterface != null) {
            return robotInterface.getDeviceStatus(device);
        }
        return null;
    }

    public final void addDeviceStatusChangedListener(DeviceStatusChangedListener listener) {
        BaseMultiListenerImpl<DeviceStatusChangedListener> deviceStatusChangedListeners;
        if (listener == null) {
            return;
        }
        Pdlog.m3273d("IMSTask", "robotPeripherals?.deviceStatusChangedListeners = " + listener);
        RobotPeripherals robotPeripherals = this.robotPeripherals;
        if (robotPeripherals == null || (deviceStatusChangedListeners = robotPeripherals.getDeviceStatusChangedListeners()) == null) {
            return;
        }
        deviceStatusChangedListeners.addListener(listener);
    }

    public final void sendMsg(byte[] bytes) {
        RobotInterface robotInterface = this.robotInterface;
        if (robotInterface != null) {
            robotInterface.broadcastToRemoteDevice(bytes);
        }
    }

    public final void flush() {
        RobotInterface robotInterface = this.robotInterface;
        if (robotInterface != null) {
            robotInterface.flushLoraSerialPortCache();
        }
    }

    public final void addMsgReceivedListener(MsgReceivedListener listener) {
        RobotPeripherals robotPeripherals;
        BaseMultiListenerImpl<MsgReceivedListener> msgReceivedListeners;
        if (listener == null || (robotPeripherals = this.robotPeripherals) == null || (msgReceivedListeners = robotPeripherals.getMsgReceivedListeners()) == null) {
            return;
        }
        msgReceivedListeners.addListener(listener);
    }
}
