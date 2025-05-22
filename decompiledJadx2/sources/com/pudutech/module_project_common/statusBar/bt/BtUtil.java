package com.pudutech.module_project_common.statusBar.bt;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

/* loaded from: classes3.dex */
public class BtUtil {
    public static boolean isOpen(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    public static void searchDevices(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null) {
            bluetoothAdapter.startDiscovery();
        }
    }

    public static void cancelDiscovery(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null) {
            bluetoothAdapter.cancelDiscovery();
        }
    }

    public static void registerBluetoothReceiver(BroadcastReceiver broadcastReceiver, Context context) {
        if (broadcastReceiver == null || context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static void unregisterBluetoothReceiver(BroadcastReceiver broadcastReceiver, Context context) {
        if (broadcastReceiver == null || context == null) {
            return;
        }
        context.unregisterReceiver(broadcastReceiver);
    }
}
