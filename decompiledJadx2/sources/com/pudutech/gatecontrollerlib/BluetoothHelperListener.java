package com.pudutech.gatecontrollerlib;

import android.bluetooth.BluetoothDevice;
import com.iflytek.speech.UtilityConfig;
import kotlin.Metadata;

/* compiled from: BluetoothHelperListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/gatecontrollerlib/BluetoothHelperListener;", "", "getBluetoothDeviceList", "", UtilityConfig.KEY_DEVICE_INFO, "Landroid/bluetooth/BluetoothDevice;", "onDisabledBluetooth", "onEnabledBluetooth", "onFinishDiscovery", "onStartDiscovery", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface BluetoothHelperListener {
    void getBluetoothDeviceList(BluetoothDevice device);

    void onDisabledBluetooth();

    void onEnabledBluetooth();

    void onFinishDiscovery();

    void onStartDiscovery();
}
