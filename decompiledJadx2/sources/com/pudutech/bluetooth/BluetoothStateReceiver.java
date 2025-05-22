package com.pudutech.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.pudutech.bluetooth.BluetoothState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: BluetoothStateReceiver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\t¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bluetooth/BluetoothStateReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "TAG", "", "bluetoothStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/pudutech/bluetooth/BluetoothState;", "getBluetoothStateFlow", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "deviceBondStateChangeFlow", "Landroid/bluetooth/BluetoothDevice;", "getDeviceBondStateChangeFlow", "deviceConnectStateChangeFlow", "Lcom/pudutech/bluetooth/DeviceWithConnectState;", "getDeviceConnectStateChangeFlow", "filter", "Landroid/content/IntentFilter;", "getFilter", "()Landroid/content/IntentFilter;", "scanDeviceFlow", "getScanDeviceFlow", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "bluetooth_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BluetoothStateReceiver extends BroadcastReceiver {
    private final IntentFilter filter;
    private final String TAG = "BluetoothStateReceiver";
    private final MutableStateFlow<BluetoothDevice> scanDeviceFlow = StateFlowKt.MutableStateFlow(null);
    private final MutableStateFlow<BluetoothState> bluetoothStateFlow = StateFlowKt.MutableStateFlow(BluetoothState.UnKnow.INSTANCE);
    private final MutableStateFlow<BluetoothDevice> deviceBondStateChangeFlow = StateFlowKt.MutableStateFlow(null);
    private final MutableStateFlow<DeviceWithConnectState> deviceConnectStateChangeFlow = StateFlowKt.MutableStateFlow(null);

    public BluetoothStateReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        this.filter = intentFilter;
    }

    public final MutableStateFlow<BluetoothDevice> getScanDeviceFlow() {
        return this.scanDeviceFlow;
    }

    public final MutableStateFlow<BluetoothState> getBluetoothStateFlow() {
        return this.bluetoothStateFlow;
    }

    public final MutableStateFlow<BluetoothDevice> getDeviceBondStateChangeFlow() {
        return this.deviceBondStateChangeFlow;
    }

    public final MutableStateFlow<DeviceWithConnectState> getDeviceConnectStateChangeFlow() {
        return this.deviceConnectStateChangeFlow;
    }

    public final IntentFilter getFilter() {
        return this.filter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BluetoothDevice bluetoothDevice;
        BluetoothDevice bluetoothDevice2;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        String action = intent.getAction();
        Log.i(this.TAG, "onReceive > intent: action = " + action + " , extras = " + intent.getExtras() + ' ');
        if (action == null) {
            return;
        }
        switch (action.hashCode()) {
            case -1530327060:
                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    this.bluetoothStateFlow.setValue(BluetoothState.INSTANCE.fromState(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", BluetoothState.UnKnow.INSTANCE.getState())));
                    return;
                }
                return;
            case 1167529923:
                if (!action.equals("android.bluetooth.device.action.FOUND") || (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) == null) {
                    return;
                }
                String name = bluetoothDevice.getName();
                String address = bluetoothDevice.getAddress();
                Log.i(this.TAG, "onReceive > name = " + name + " mac = " + address + ' ');
                this.scanDeviceFlow.setValue(bluetoothDevice);
                return;
            case 1244161670:
                if (!action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED") || (bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) == null) {
                    return;
                }
                this.deviceConnectStateChangeFlow.setValue(new DeviceWithConnectState(bluetoothDevice2, DeviceConnectState.INSTANCE.fromState(intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1))));
                return;
            case 2116862345:
                if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                    MutableStateFlow<BluetoothDevice> mutableStateFlow = this.deviceBondStateChangeFlow;
                    BluetoothDevice bluetoothDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (bluetoothDevice3 != null) {
                        mutableStateFlow.setValue(bluetoothDevice3);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }
}
