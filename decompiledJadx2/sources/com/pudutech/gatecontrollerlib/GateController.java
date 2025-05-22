package com.pudutech.gatecontrollerlib;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.content.IntentFilter;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.UStringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: GateController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000m\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\u0012\n\u0002\b\u0002*\u0001\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u0019J\u000e\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u000bJ\u000e\u0010%\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000bJ\u0010\u0010&\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000bH\u0002J\u0012\u0010'\u001a\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010(\u001a\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010\u000bJ\u0006\u0010)\u001a\u00020 J\"\u0010*\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00062\b\b\u0002\u0010,\u001a\u00020\u0016H\u0002J \u0010-\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u00062\b\b\u0002\u0010/\u001a\u00020\u0016J\u0010\u00100\u001a\u00020 2\u0006\u0010$\u001a\u00020\u000bH\u0002J\u000e\u00101\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000bJ\u0016\u00102\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u000b2\u0006\u00103\u001a\u000204J\f\u00105\u001a\u00020\u000b*\u000204H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00066"}, m3961d2 = {"Lcom/pudutech/gatecontrollerlib/GateController;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "MTU_SIZE", "", "NOTIFY_UUID", "Ljava/util/UUID;", "kotlin.jvm.PlatformType", "TAG", "", "WRITE_UUID", "bluetoothHelper", "Lcom/pudutech/gatecontrollerlib/BluetoothHelper;", "bluetoothStateReceiver", "com/pudutech/gatecontrollerlib/GateController$bluetoothStateReceiver$1", "Lcom/pudutech/gatecontrollerlib/GateController$bluetoothStateReceiver$1;", "deviceInfoMap", "Ljava/util/HashMap;", "Lcom/pudutech/gatecontrollerlib/BluetoothDeviceInfo;", "isInit", "", "listeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/gatecontrollerlib/GateControllerListener;", "mGattCallback", "Landroid/bluetooth/BluetoothGattCallback;", "waitConnectSet", "", "waitDisconnectSet", "addBluetoothBleListeners", "", "name", "listener", MqttServiceConstants.CONNECT_ACTION, "mac", MqttServiceConstants.DISCONNECT_ACTION, "disconnectDevice", "getConnectState", "getConnectionState", "initIntent", "notifyConnectState", "state", "isAllowNotify", "openGate", "timeout", MapElement.Key.DIR, "realConnectDevice", "removeBluetoothBleListeners", MqttServiceConstants.SEND_ACTION, "data", "", "toHexString", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GateController {
    private final int MTU_SIZE;
    private final UUID NOTIFY_UUID;
    private final String TAG;
    private final UUID WRITE_UUID;
    private BluetoothHelper bluetoothHelper;
    private final GateController$bluetoothStateReceiver$1 bluetoothStateReceiver;
    private final Context context;
    private final HashMap<String, BluetoothDeviceInfo> deviceInfoMap;
    private boolean isInit;
    private ThreadSafeListener<GateControllerListener> listeners;
    private final BluetoothGattCallback mGattCallback;
    private Set<String> waitConnectSet;
    private Set<String> waitDisconnectSet;

    public GateController(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.MTU_SIZE = 128;
        this.TAG = "GateController";
        this.bluetoothHelper = new BluetoothHelper(this.context, null).setPermissionRequired(true).create();
        this.WRITE_UUID = UUID.fromString("0000c303-0000-1000-8000-00805f9b34fb");
        this.NOTIFY_UUID = UUID.fromString("0000c305-0000-1000-8000-00805f9b34fb");
        this.deviceInfoMap = new HashMap<>();
        this.waitConnectSet = new LinkedHashSet();
        this.waitDisconnectSet = new LinkedHashSet();
        this.listeners = new ThreadSafeListener<>();
        this.mGattCallback = new GateController$mGattCallback$1(this);
        this.bluetoothStateReceiver = new GateController$bluetoothStateReceiver$1(this);
    }

    public final void initIntent() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.context.registerReceiver(this.bluetoothStateReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void notifyConnectState$default(GateController gateController, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        gateController.notifyConnectState(str, i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyConnectState(String mac, int state, boolean isAllowNotify) {
        BluetoothDeviceInfo bluetoothDeviceInfo = this.deviceInfoMap.get(mac);
        if (bluetoothDeviceInfo != null) {
            bluetoothDeviceInfo.setConnectState(state);
        }
        if (isAllowNotify) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new GateController$notifyConnectState$1(this, mac, state, null), 3, null);
        }
    }

    private final int getConnectState(String mac) {
        BluetoothDeviceInfo bluetoothDeviceInfo;
        String str = mac;
        if ((str == null || str.length() == 0) || (bluetoothDeviceInfo = this.deviceInfoMap.get(mac)) == null) {
            return 0;
        }
        return bluetoothDeviceInfo.getConnectState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disconnectDevice(String mac) {
        BluetoothGatt bluetoothGatt;
        if (getConnectState(mac) == 0) {
            return;
        }
        if (getConnectState(mac) == 1) {
            Pdlog.m3273d(this.TAG, "delay disconnect " + mac);
            this.waitDisconnectSet.add(mac);
            return;
        }
        this.waitDisconnectSet.remove(mac);
        this.waitConnectSet.remove(mac);
        Pdlog.m3275i(this.TAG, mac + " will disconnect");
        BluetoothDeviceInfo bluetoothDeviceInfo = this.deviceInfoMap.get(mac);
        if (bluetoothDeviceInfo == null || (bluetoothGatt = bluetoothDeviceInfo.getBluetoothGatt()) == null) {
            return;
        }
        bluetoothGatt.disconnect();
    }

    public final int getConnectionState(String mac) {
        String str;
        if (mac == null) {
            str = null;
        } else {
            if (mac == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            str = mac.toUpperCase();
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toUpperCase()");
        }
        return getConnectState(str);
    }

    public final void disconnect(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        String upperCase = mac.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        disconnectDevice(upperCase);
    }

    public final boolean connect(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        String upperCase = mac.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        if (!this.isInit) {
            initIntent();
            this.isInit = true;
        }
        if (getConnectState(upperCase) == 2 || getConnectState(upperCase) == 1) {
            Pdlog.m3274e(this.TAG, "current state is " + getConnectState(upperCase));
            return true;
        }
        if (this.waitDisconnectSet.contains(upperCase)) {
            this.waitDisconnectSet.remove(upperCase);
        }
        if (!this.bluetoothHelper.isBluetoothEnabled()) {
            notifyConnectState$default(this, upperCase, 1, false, 4, null);
            this.waitConnectSet.add(upperCase);
            this.bluetoothHelper.enableBluetooth();
        } else {
            Pdlog.m3273d(this.TAG, "Trying to create a new connection.");
            realConnectDevice(upperCase);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void realConnectDevice(String mac) {
        if (mac.length() == 0) {
            Pdlog.m3274e(this.TAG, "mac was empty");
            return;
        }
        Pdlog.m3273d(this.TAG, "ConnectDevice mac[" + mac + ']');
        BluetoothDevice device = this.bluetoothHelper.getDevice(mac);
        if (!this.deviceInfoMap.containsKey(mac)) {
            this.deviceInfoMap.put(mac, new BluetoothDeviceInfo(mac, null, null, 0, 14, null));
        }
        notifyConnectState$default(this, mac, 1, false, 4, null);
        BluetoothGatt connectGatt = device.connectGatt(this.context, false, this.mGattCallback);
        BluetoothDeviceInfo bluetoothDeviceInfo = this.deviceInfoMap.get(mac);
        if (bluetoothDeviceInfo != null) {
            bluetoothDeviceInfo.setBluetoothGatt(connectGatt);
        }
    }

    public final void addBluetoothBleListeners(String name, GateControllerListener listener) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listeners.add(name, listener);
    }

    public final void removeBluetoothBleListeners(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.listeners.remove(name);
    }

    public final boolean send(String mac, byte[] data) {
        BluetoothGattCharacteristic characteristic;
        BluetoothGatt bluetoothGatt;
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(data, "data");
        String upperCase = mac.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        Pdlog.m3273d(this.TAG, "writeData mac:" + upperCase + " data:" + toHexString(data));
        if (getConnectState(upperCase) != 2) {
            Pdlog.m3273d(this.TAG, upperCase + " write data fail because not connected");
            return false;
        }
        BluetoothDeviceInfo bluetoothDeviceInfo = this.deviceInfoMap.get(upperCase);
        if (bluetoothDeviceInfo != null && (characteristic = bluetoothDeviceInfo.getCharacteristic()) != null) {
            characteristic.setValue(data);
            BluetoothDeviceInfo bluetoothDeviceInfo2 = this.deviceInfoMap.get(upperCase);
            Boolean valueOf = (bluetoothDeviceInfo2 == null || (bluetoothGatt = bluetoothDeviceInfo2.getBluetoothGatt()) == null) ? null : Boolean.valueOf(bluetoothGatt.writeCharacteristic(characteristic));
            Pdlog.m3273d(this.TAG, "writeData result " + valueOf);
            if (valueOf != null) {
                return valueOf.booleanValue();
            }
            return false;
        }
        Pdlog.m3273d(this.TAG, "write characteristic was null");
        return false;
    }

    public static /* synthetic */ boolean openGate$default(GateController gateController, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        return gateController.openGate(str, i, z);
    }

    public final boolean openGate(String mac, int timeout, boolean dir) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        if (timeout < 0) {
            return false;
        }
        byte[] build = new FrameBuilder().openGate(timeout, dir).build();
        Pdlog.m3275i(this.TAG, "send data : " + toHexString(build));
        return send(mac, build);
    }

    public final String toHexString(byte[] toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        return CollectionsKt.joinToString$default(UByteArray.m4570boximpl(UByteArray.m4572constructorimpl(toHexString)), " ", null, null, 0, null, new Function1<UByte, String>() { // from class: com.pudutech.gatecontrollerlib.GateController$toHexString$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(UByte uByte) {
                return invoke(uByte.getData());
            }

            public final String invoke(byte b) {
                return StringsKt.padStart(UStringsKt.m5471toStringLxnNnR4(b, 16), 2, '0');
            }
        }, 30, null);
    }
}
