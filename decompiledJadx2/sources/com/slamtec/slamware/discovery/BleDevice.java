package com.slamtec.slamware.discovery;

import android.bluetooth.BluetoothDevice;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class BleDevice extends Device {
    private BluetoothDevice device;

    public BleDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    @Override // com.slamtec.slamware.discovery.Device
    public boolean canBeFoundWith(DiscoveryMode discoveryMode) {
        return discoveryMode == DiscoveryMode.BLE;
    }
}
