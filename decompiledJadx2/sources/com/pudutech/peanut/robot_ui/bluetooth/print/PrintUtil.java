package com.pudutech.peanut.robot_ui.bluetooth.print;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.bluetooth.base.AppInfo;
import com.pudutech.peanut.robot_ui.bluetooth.bt.BtUtil;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes5.dex */
public class PrintUtil {
    public static final String ACTION_PRINT = "action_print";
    public static final String ACTION_PRINT_BITMAP = "action_print_bitmap";
    public static final String ACTION_PRINT_PAINTING = "action_print_painting";
    public static final String ACTION_PRINT_TEST = "action_print_test";
    public static final String ACTION_PRINT_TEST_TWO = "action_print_test_two";
    public static final String ACTION_PRINT_TICKET = "action_print_ticket";
    private static final String DEFAULT_BLUETOOTH_DEVICE_ADDRESS = "default_bluetooth_device_address";
    private static final String DEFAULT_BLUETOOTH_DEVICE_NAME = "default_bluetooth_device_name";
    private static final String FILENAME = "bt";
    public static final String PRINT_EXTRA = "print_extra";
    public static final String SHOP_NAME = "shop_name";
    public static final String SHOP_URL = "shop_url";
    public static final String TABLE_NAME = "table_name";
    public static final String TABLE_NUMBER = "table_number";

    public static void setDefaultBluetoothDeviceAddress(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILENAME, 0).edit();
        edit.putString(DEFAULT_BLUETOOTH_DEVICE_ADDRESS, str);
        edit.apply();
        AppInfo.btAddress = str;
    }

    public static void setDefaultBluetoothDeviceName(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILENAME, 0).edit();
        edit.putString(DEFAULT_BLUETOOTH_DEVICE_NAME, str);
        edit.apply();
        AppInfo.btName = str;
    }

    public static boolean isBondPrinter(Context context, BluetoothAdapter bluetoothAdapter) {
        Set<BluetoothDevice> bondedDevices;
        if (!BtUtil.isOpen(bluetoothAdapter)) {
            return false;
        }
        String defaultBluethoothDeviceAddress = getDefaultBluethoothDeviceAddress(context);
        if (!TextUtils.isEmpty(defaultBluethoothDeviceAddress) && (bondedDevices = bluetoothAdapter.getBondedDevices()) != null && !bondedDevices.isEmpty()) {
            Iterator<BluetoothDevice> it = bondedDevices.iterator();
            while (it.hasNext()) {
                if (it.next().getAddress().equals(defaultBluethoothDeviceAddress)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean bluetoothIsConnected(BluetoothDevice bluetoothDevice) {
        boolean z;
        try {
            Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", new Class[0]);
            declaredMethod.setAccessible(true);
            z = ((Boolean) declaredMethod.invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        Pdlog.m3274e("isConnected", new Gson().toJson(bluetoothDevice) + "" + z);
        return z;
    }

    public static String getDefaultBluethoothDeviceAddress(Context context) {
        return context.getSharedPreferences(FILENAME, 0).getString(DEFAULT_BLUETOOTH_DEVICE_ADDRESS, "");
    }

    public static boolean isBondPrinterIgnoreBluetooth(Context context) {
        return (TextUtils.isEmpty(getDefaultBluethoothDeviceAddress(context)) || TextUtils.isEmpty(getDefaultBluetoothDeviceName(context))) ? false : true;
    }

    public static String getDefaultBluetoothDeviceName(Context context) {
        return context.getSharedPreferences(FILENAME, 0).getString(DEFAULT_BLUETOOTH_DEVICE_NAME, "");
    }

    public static void apply(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            editor.commit();
        }
    }
}
