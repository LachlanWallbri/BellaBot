package com.pudutech.gatecontrollerlib;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BluetoothDeviceInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J5\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\tHÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006#"}, m3961d2 = {"Lcom/pudutech/gatecontrollerlib/BluetoothDeviceInfo;", "", "mac", "", "bluetoothGatt", "Landroid/bluetooth/BluetoothGatt;", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "connectState", "", "(Ljava/lang/String;Landroid/bluetooth/BluetoothGatt;Landroid/bluetooth/BluetoothGattCharacteristic;I)V", "getBluetoothGatt", "()Landroid/bluetooth/BluetoothGatt;", "setBluetoothGatt", "(Landroid/bluetooth/BluetoothGatt;)V", "getCharacteristic", "()Landroid/bluetooth/BluetoothGattCharacteristic;", "setCharacteristic", "(Landroid/bluetooth/BluetoothGattCharacteristic;)V", "getConnectState", "()I", "setConnectState", "(I)V", "getMac", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class BluetoothDeviceInfo {
    private BluetoothGatt bluetoothGatt;
    private BluetoothGattCharacteristic characteristic;
    private int connectState;
    private final String mac;

    public static /* synthetic */ BluetoothDeviceInfo copy$default(BluetoothDeviceInfo bluetoothDeviceInfo, String str, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bluetoothDeviceInfo.mac;
        }
        if ((i2 & 2) != 0) {
            bluetoothGatt = bluetoothDeviceInfo.bluetoothGatt;
        }
        if ((i2 & 4) != 0) {
            bluetoothGattCharacteristic = bluetoothDeviceInfo.characteristic;
        }
        if ((i2 & 8) != 0) {
            i = bluetoothDeviceInfo.connectState;
        }
        return bluetoothDeviceInfo.copy(str, bluetoothGatt, bluetoothGattCharacteristic, i);
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

    public final BluetoothDeviceInfo copy(String mac, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic characteristic, int connectState) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        return new BluetoothDeviceInfo(mac, bluetoothGatt, characteristic, connectState);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BluetoothDeviceInfo)) {
            return false;
        }
        BluetoothDeviceInfo bluetoothDeviceInfo = (BluetoothDeviceInfo) other;
        return Intrinsics.areEqual(this.mac, bluetoothDeviceInfo.mac) && Intrinsics.areEqual(this.bluetoothGatt, bluetoothDeviceInfo.bluetoothGatt) && Intrinsics.areEqual(this.characteristic, bluetoothDeviceInfo.characteristic) && this.connectState == bluetoothDeviceInfo.connectState;
    }

    public int hashCode() {
        String str = this.mac;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        BluetoothGatt bluetoothGatt = this.bluetoothGatt;
        int hashCode2 = (hashCode + (bluetoothGatt != null ? bluetoothGatt.hashCode() : 0)) * 31;
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.characteristic;
        return ((hashCode2 + (bluetoothGattCharacteristic != null ? bluetoothGattCharacteristic.hashCode() : 0)) * 31) + Integer.hashCode(this.connectState);
    }

    public String toString() {
        return "BluetoothDeviceInfo(mac=" + this.mac + ", bluetoothGatt=" + this.bluetoothGatt + ", characteristic=" + this.characteristic + ", connectState=" + this.connectState + ")";
    }

    public BluetoothDeviceInfo(String mac, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.mac = mac;
        this.bluetoothGatt = bluetoothGatt;
        this.characteristic = bluetoothGattCharacteristic;
        this.connectState = i;
    }

    public final String getMac() {
        return this.mac;
    }

    public /* synthetic */ BluetoothDeviceInfo(String str, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? (BluetoothGatt) null : bluetoothGatt, (i2 & 4) != 0 ? (BluetoothGattCharacteristic) null : bluetoothGattCharacteristic, (i2 & 8) != 0 ? 0 : i);
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
}
