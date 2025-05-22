package com.pudutech.peanut.robot_ui.bluetooth.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
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
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static boolean bluetoothIsConnected(BluetoothDevice bluetoothDevice) {
        try {
            Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void unregisterBluetoothReceiver(BroadcastReceiver broadcastReceiver, Context context) {
        if (broadcastReceiver == null || context == null) {
            return;
        }
        context.unregisterReceiver(broadcastReceiver);
    }
}
