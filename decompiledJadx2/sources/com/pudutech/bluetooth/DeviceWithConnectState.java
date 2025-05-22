package com.pudutech.bluetooth;

import android.bluetooth.BluetoothDevice;
import com.iflytek.speech.UtilityConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bluetooth/DeviceWithConnectState;", "", UtilityConfig.KEY_DEVICE_INFO, "Landroid/bluetooth/BluetoothDevice;", "state", "Lcom/pudutech/bluetooth/DeviceConnectState;", "(Landroid/bluetooth/BluetoothDevice;Lcom/pudutech/bluetooth/DeviceConnectState;)V", "getDevice", "()Landroid/bluetooth/BluetoothDevice;", "getState", "()Lcom/pudutech/bluetooth/DeviceConnectState;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class DeviceWithConnectState {
    private final BluetoothDevice device;
    private final DeviceConnectState state;

    public static /* synthetic */ DeviceWithConnectState copy$default(DeviceWithConnectState deviceWithConnectState, BluetoothDevice bluetoothDevice, DeviceConnectState deviceConnectState, int i, Object obj) {
        if ((i & 1) != 0) {
            bluetoothDevice = deviceWithConnectState.device;
        }
        if ((i & 2) != 0) {
            deviceConnectState = deviceWithConnectState.state;
        }
        return deviceWithConnectState.copy(bluetoothDevice, deviceConnectState);
    }

    /* renamed from: component1, reason: from getter */
    public final BluetoothDevice getDevice() {
        return this.device;
    }

    /* renamed from: component2, reason: from getter */
    public final DeviceConnectState getState() {
        return this.state;
    }

    public final DeviceWithConnectState copy(BluetoothDevice device, DeviceConnectState state) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        Intrinsics.checkParameterIsNotNull(state, "state");
        return new DeviceWithConnectState(device, state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceWithConnectState)) {
            return false;
        }
        DeviceWithConnectState deviceWithConnectState = (DeviceWithConnectState) other;
        return Intrinsics.areEqual(this.device, deviceWithConnectState.device) && Intrinsics.areEqual(this.state, deviceWithConnectState.state);
    }

    public int hashCode() {
        BluetoothDevice bluetoothDevice = this.device;
        int hashCode = (bluetoothDevice != null ? bluetoothDevice.hashCode() : 0) * 31;
        DeviceConnectState deviceConnectState = this.state;
        return hashCode + (deviceConnectState != null ? deviceConnectState.hashCode() : 0);
    }

    public String toString() {
        return "DeviceWithConnectState(device=" + this.device + ", state=" + this.state + ")";
    }

    public DeviceWithConnectState(BluetoothDevice device, DeviceConnectState state) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        Intrinsics.checkParameterIsNotNull(state, "state");
        this.device = device;
        this.state = state;
    }

    public final BluetoothDevice getDevice() {
        return this.device;
    }

    public final DeviceConnectState getState() {
        return this.state;
    }
}
