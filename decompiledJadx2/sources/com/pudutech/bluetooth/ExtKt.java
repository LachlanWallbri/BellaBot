package com.pudutech.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.iflytek.speech.UtilityConfig;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\t\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\n\u001a\u00020\u0005*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\r"}, m3961d2 = {"TAG", "", "getTAG", "()Ljava/lang/String;", "connectDevice", "", "Landroid/bluetooth/BluetoothA2dp;", UtilityConfig.KEY_DEVICE_INFO, "Landroid/bluetooth/BluetoothDevice;", "disconnectDevice", "enableBluetooth", "Landroid/bluetooth/BluetoothAdapter;", "systemApp", "bluetooth_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ExtKt {
    private static final String TAG = "BluetoothExt";

    public static final String getTAG() {
        return TAG;
    }

    public static final boolean connectDevice(BluetoothA2dp connectDevice, BluetoothDevice device) {
        Intrinsics.checkParameterIsNotNull(connectDevice, "$this$connectDevice");
        Intrinsics.checkParameterIsNotNull(device, "device");
        connectDevice.getConnectionState(device);
        Object invoke = connectDevice.getClass().getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class).invoke(connectDevice, device);
        if (invoke != null) {
            return ((Boolean) invoke).booleanValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
    }

    public static final boolean disconnectDevice(BluetoothA2dp disconnectDevice, BluetoothDevice device) {
        Intrinsics.checkParameterIsNotNull(disconnectDevice, "$this$disconnectDevice");
        Intrinsics.checkParameterIsNotNull(device, "device");
        Object invoke = disconnectDevice.getClass().getMethod(MqttServiceConstants.DISCONNECT_ACTION, BluetoothDevice.class).invoke(disconnectDevice, device);
        if (invoke != null) {
            return ((Boolean) invoke).booleanValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
    }

    public static final boolean enableBluetooth(BluetoothAdapter enableBluetooth, boolean z) {
        Intrinsics.checkParameterIsNotNull(enableBluetooth, "$this$enableBluetooth");
        Log.i(TAG, "enableBluetooth > " + z);
        if (!z) {
            return enableBluetooth.enable();
        }
        Log.i(TAG, "enableBluetooth > systemApi");
        Object invoke = enableBluetooth.getClass().getMethod("enableNoAutoConnect", new Class[0]).invoke(enableBluetooth, new Object[0]);
        if (invoke != null) {
            return ((Boolean) invoke).booleanValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
    }
}
