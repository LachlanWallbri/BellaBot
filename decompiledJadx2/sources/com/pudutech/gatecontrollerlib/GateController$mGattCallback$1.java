package com.pudutech.gatecontrollerlib;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import com.pudutech.base.Pdlog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: GateController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J\"\u0010\r\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u0010"}, m3961d2 = {"com/pudutech/gatecontrollerlib/GateController$mGattCallback$1", "Landroid/bluetooth/BluetoothGattCallback;", "onCharacteristicChanged", "", "gatt", "Landroid/bluetooth/BluetoothGatt;", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "onCharacteristicRead", "status", "", "onConnectionStateChange", "newState", "onMtuChanged", "mtu", "onServicesDiscovered", "GateControllerLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class GateController$mGattCallback$1 extends BluetoothGattCallback {
    final /* synthetic */ GateController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GateController$mGattCallback$1(GateController gateController) {
        this.this$0 = gateController;
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
        String str;
        HashMap hashMap;
        Set set;
        Set set2;
        HashMap hashMap2;
        Intrinsics.checkParameterIsNotNull(gatt, "gatt");
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "onConnectionStateChange from " + status + " to " + newState);
        super.onConnectionStateChange(gatt, status, newState);
        BluetoothDevice device = gatt.getDevice();
        String address = device != null ? device.getAddress() : null;
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        if (address != null) {
            if (newState == 0) {
                hashMap = this.this$0.deviceInfoMap;
                BluetoothDeviceInfo bluetoothDeviceInfo = (BluetoothDeviceInfo) hashMap.get(address);
                if (bluetoothDeviceInfo != null) {
                    bluetoothDeviceInfo.setCharacteristic((BluetoothGattCharacteristic) null);
                }
                set = this.this$0.waitConnectSet;
                set.remove(address);
                gatt.close();
            } else if (newState == 2) {
                set2 = this.this$0.waitDisconnectSet;
                if (!set2.contains(address)) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C4595xf9857f20(null, this, newState, address, booleanRef, gatt), 3, null);
                } else {
                    booleanRef.element = false;
                    hashMap2 = this.this$0.deviceInfoMap;
                    BluetoothDeviceInfo bluetoothDeviceInfo2 = (BluetoothDeviceInfo) hashMap2.get(address);
                    if (bluetoothDeviceInfo2 != null) {
                        bluetoothDeviceInfo2.setConnectState(newState);
                    }
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C4594xf9857f1f(null, this, newState, address, booleanRef, gatt), 3, null);
                }
            }
        }
        GateController gateController = this.this$0;
        BluetoothDevice device2 = gatt.getDevice();
        Intrinsics.checkExpressionValueIsNotNull(device2, "gatt.device");
        String address2 = device2.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address2, "gatt.device.address");
        gateController.notifyConnectState(address2, newState, booleanRef.element);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
        String str;
        HashMap hashMap;
        String str2;
        String str3;
        String str4;
        String str5;
        UUID uuid;
        String str6;
        HashMap hashMap2;
        UUID uuid2;
        String str7;
        Intrinsics.checkParameterIsNotNull(gatt, "gatt");
        if (status == 0) {
            str = this.this$0.TAG;
            Pdlog.m3277w(str, "onServicesDiscovered received: " + status);
            Iterator<BluetoothGattService> it = gatt.getServices().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BluetoothGattService service = it.next();
                str3 = this.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("service.uuid: ");
                Intrinsics.checkExpressionValueIsNotNull(service, "service");
                sb.append(service.getUuid().toString());
                Pdlog.m3275i(str3, sb.toString());
                for (BluetoothGattCharacteristic characteristic : service.getCharacteristics()) {
                    str4 = this.this$0.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("characteristic.uuid: ");
                    Intrinsics.checkExpressionValueIsNotNull(characteristic, "characteristic");
                    sb2.append(characteristic.getUuid().toString());
                    Pdlog.m3275i(str4, sb2.toString());
                    str5 = this.this$0.TAG;
                    Pdlog.m3275i(str5, "characteristic.permissions: " + characteristic.getPermissions());
                    UUID uuid3 = characteristic.getUuid();
                    uuid = this.this$0.WRITE_UUID;
                    if (Intrinsics.areEqual(uuid3, uuid)) {
                        str6 = this.this$0.TAG;
                        Pdlog.m3274e(str6, "Find write characteristic");
                        hashMap2 = this.this$0.deviceInfoMap;
                        BluetoothDevice device = gatt.getDevice();
                        String address = device != null ? device.getAddress() : null;
                        if (address == null) {
                            Intrinsics.throwNpe();
                        }
                        BluetoothDeviceInfo bluetoothDeviceInfo = (BluetoothDeviceInfo) hashMap2.get(address);
                        if (bluetoothDeviceInfo != null) {
                            bluetoothDeviceInfo.setCharacteristic(characteristic);
                        }
                    } else {
                        UUID uuid4 = characteristic.getUuid();
                        uuid2 = this.this$0.NOTIFY_UUID;
                        if (Intrinsics.areEqual(uuid4, uuid2)) {
                            str7 = this.this$0.TAG;
                            Pdlog.m3274e(str7, "Find notify characteristic");
                            gatt.setCharacteristicNotification(characteristic, true);
                        }
                    }
                }
            }
            hashMap = this.this$0.deviceInfoMap;
            BluetoothDevice device2 = gatt.getDevice();
            String address2 = device2 != null ? device2.getAddress() : null;
            if (address2 == null) {
                Intrinsics.throwNpe();
            }
            BluetoothDeviceInfo bluetoothDeviceInfo2 = (BluetoothDeviceInfo) hashMap.get(address2);
            if ((bluetoothDeviceInfo2 != null ? bluetoothDeviceInfo2.getCharacteristic() : null) == null) {
                str2 = this.this$0.TAG;
                Pdlog.m3274e(str2, "Can not find characteristic");
                GateController gateController = this.this$0;
                BluetoothDevice device3 = gatt.getDevice();
                String address3 = device3 != null ? device3.getAddress() : null;
                if (address3 == null) {
                    Intrinsics.throwNpe();
                }
                gateController.disconnectDevice(address3);
            }
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        String str;
        Intrinsics.checkParameterIsNotNull(gatt, "gatt");
        Intrinsics.checkParameterIsNotNull(characteristic, "characteristic");
        super.onCharacteristicRead(gatt, characteristic, status);
        str = this.this$0.TAG;
        Pdlog.m3274e(str, "onCharacteristicRead, status " + status);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        UUID uuid;
        String str;
        String str2;
        Intrinsics.checkParameterIsNotNull(gatt, "gatt");
        Intrinsics.checkParameterIsNotNull(characteristic, "characteristic");
        super.onCharacteristicChanged(gatt, characteristic);
        UUID uuid2 = characteristic.getUuid();
        uuid = this.this$0.NOTIFY_UUID;
        if (uuid2.equals(uuid)) {
            str = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("onCharacteristicChanged ==> ");
            GateController gateController = this.this$0;
            byte[] value = characteristic.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value, "characteristic.value");
            sb.append(gateController.toHexString(value));
            Pdlog.m3273d(str, sb.toString());
            byte[] value2 = characteristic.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value2, "characteristic.value");
            byte[] copyOfRange = ArraysKt.copyOfRange(value2, 0, characteristic.getValue().length);
            byte[] value3 = characteristic.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value3, "characteristic.value");
            GateControllerMsg build = new GateControllerMsg(value3).build();
            str2 = this.this$0.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("recv msg ");
            sb2.append(build.getErrorCode());
            sb2.append(" , ");
            GateController gateController2 = this.this$0;
            byte[] value4 = characteristic.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value4, "characteristic.value");
            sb2.append(gateController2.toHexString(value4));
            Pdlog.m3273d(str2, sb2.toString());
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new GateController$mGattCallback$1$onCharacteristicChanged$1(this, gatt, copyOfRange, build, null), 3, null);
        }
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
        String str;
        String str2;
        String str3;
        super.onMtuChanged(gatt, mtu, status);
        if (status == 0) {
            str3 = this.this$0.TAG;
            Pdlog.m3273d(str3, "onMtuChanged is success");
        } else {
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "onMtuChanged is false");
        }
        str2 = this.this$0.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("Attempting to start service discovery: ");
        sb.append(gatt != null ? Boolean.valueOf(gatt.discoverServices()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(str2, objArr);
    }
}
