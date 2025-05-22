package com.pudutech.mirsdk.bluetooth;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BluetoothDeviceInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b¢\u0006\u0002\u0010\u000eJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\u0015\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bHÆ\u0003JK\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\tHÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u001d\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006*"}, m3961d2 = {"Lcom/pudutech/mirsdk/bluetooth/BluetoothDeviceInfo;", "", "mac", "", "bluetoothGatt", "Landroid/bluetooth/BluetoothGatt;", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "connectState", "", "bleListener", "", "Ljava/lang/ref/WeakReference;", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleListener;", "(Ljava/lang/String;Landroid/bluetooth/BluetoothGatt;Landroid/bluetooth/BluetoothGattCharacteristic;ILjava/util/List;)V", "getBleListener", "()Ljava/util/List;", "getBluetoothGatt", "()Landroid/bluetooth/BluetoothGatt;", "setBluetoothGatt", "(Landroid/bluetooth/BluetoothGatt;)V", "getCharacteristic", "()Landroid/bluetooth/BluetoothGattCharacteristic;", "setCharacteristic", "(Landroid/bluetooth/BluetoothGattCharacteristic;)V", "getConnectState", "()I", "setConnectState", "(I)V", "getMac", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class BluetoothDeviceInfo {
    private final List<WeakReference<BluetoothBleListener>> bleListener;
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic characteristic;
    private int connectState;
    private final String mac;

    public static /* synthetic */ BluetoothDeviceInfo copy$default(BluetoothDeviceInfo bluetoothDeviceInfo, String str, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bluetoothDeviceInfo.mac;
        }
        if ((i2 & 2) != 0) {
            bluetoothGatt = bluetoothDeviceInfo.bluetoothGatt;
        }
        BluetoothGatt bluetoothGatt2 = bluetoothGatt;
        if ((i2 & 4) != 0) {
            bluetoothGattCharacteristic = bluetoothDeviceInfo.characteristic;
        }
        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = bluetoothGattCharacteristic;
        if ((i2 & 8) != 0) {
            i = bluetoothDeviceInfo.connectState;
        }
        int i3 = i;
        if ((i2 & 16) != 0) {
            list = bluetoothDeviceInfo.bleListener;
        }
        return bluetoothDeviceInfo.copy(str, bluetoothGatt2, bluetoothGattCharacteristic2, i3, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component2, reason: from getter */
    public final BluetoothGatt getBluetoothGatt() {
        return this.bluetoothGatt;
    }

    /* renamed from: component3, reason: from getter */
    public final BluetoothGattCharacteristic getCharacteristic() {
        return this.characteristic;
    }

    /* renamed from: component4, reason: from getter */
    public final int getConnectState() {
        return this.connectState;
    }

    public final List<WeakReference<BluetoothBleListener>> component5() {
        return this.bleListener;
    }

    public final BluetoothDeviceInfo copy(String mac, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic characteristic, int connectState, List<WeakReference<BluetoothBleListener>> bleListener) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(bleListener, "bleListener");
        return new BluetoothDeviceInfo(mac, bluetoothGatt, characteristic, connectState, bleListener);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BluetoothDeviceInfo)) {
            return false;
        }
        BluetoothDeviceInfo bluetoothDeviceInfo = (BluetoothDeviceInfo) other;
        return Intrinsics.areEqual(this.mac, bluetoothDeviceInfo.mac) && Intrinsics.areEqual(this.bluetoothGatt, bluetoothDeviceInfo.bluetoothGatt) && Intrinsics.areEqual(this.characteristic, bluetoothDeviceInfo.characteristic) && this.connectState == bluetoothDeviceInfo.connectState && Intrinsics.areEqual(this.bleListener, bluetoothDeviceInfo.bleListener);
    }

    public int hashCode() {
        String str = this.mac;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        BluetoothGatt bluetoothGatt = this.bluetoothGatt;
        int hashCode2 = (hashCode + (bluetoothGatt != null ? bluetoothGatt.hashCode() : 0)) * 31;
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.characteristic;
        int hashCode3 = (((hashCode2 + (bluetoothGattCharacteristic != null ? bluetoothGattCharacteristic.hashCode() : 0)) * 31) + this.connectState) * 31;
        List<WeakReference<BluetoothBleListener>> list = this.bleListener;
        return hashCode3 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "BluetoothDeviceInfo(mac=" + this.mac + ", bluetoothGatt=" + this.bluetoothGatt + ", characteristic=" + this.characteristic + ", connectState=" + this.connectState + ", bleListener=" + this.bleListener + ")";
    }

    public BluetoothDeviceInfo(String mac, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, List<WeakReference<BluetoothBleListener>> bleListener) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(bleListener, "bleListener");
        this.mac = mac;
        this.bluetoothGatt = bluetoothGatt;
        this.characteristic = bluetoothGattCharacteristic;
        this.connectState = i;
        this.bleListener = bleListener;
    }

    public final String getMac() {
        return this.mac;
    }

    public /* synthetic */ BluetoothDeviceInfo(String str, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? (BluetoothGatt) null : bluetoothGatt, (i2 & 4) != 0 ? (BluetoothGattCharacteristic) null : bluetoothGattCharacteristic, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? new ArrayList() : arrayList);
    }

    public final BluetoothGatt getBluetoothGatt() {
        return this.bluetoothGatt;
    }

    public final void setBluetoothGatt(BluetoothGatt bluetoothGatt) {
        this.bluetoothGatt = bluetoothGatt;
    }

    public final BluetoothGattCharacteristic getCharacteristic() {
        return this.characteristic;
    }

    public final void setCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.characteristic = bluetoothGattCharacteristic;
    }

    public final int getConnectState() {
        return this.connectState;
    }

    public final void setConnectState(int i) {
        this.connectState = i;
    }

    public final List<WeakReference<BluetoothBleListener>> getBleListener() {
        return this.bleListener;
    }
}
