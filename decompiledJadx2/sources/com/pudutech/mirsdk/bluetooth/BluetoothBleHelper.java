package com.pudutech.mirsdk.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelUuid;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.base.CommonKt;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;

/* compiled from: BluetoothBleHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000«\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0012\n\u0000*\u0003\u0012 #\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u00105\u001a\u0002062\u0006\u00107\u001a\u00020+J\u000e\u00108\u001a\u0002062\u0006\u00109\u001a\u00020:J\u000e\u0010;\u001a\u0002062\u0006\u00109\u001a\u00020\u000eJ\u000e\u0010<\u001a\u0002062\u0006\u0010=\u001a\u00020\tJ\u000e\u0010>\u001a\u0002062\u0006\u0010=\u001a\u00020\tJ\u0010\u0010?\u001a\u00020\u00042\b\u0010=\u001a\u0004\u0018\u00010\tJ\u000e\u0010@\u001a\u0002062\u0006\u0010A\u001a\u00020\u001eJ\"\u0010B\u001a\u0002062\u0006\u0010=\u001a\u00020\t2\u0006\u0010C\u001a\u00020\u00042\b\b\u0002\u0010D\u001a\u00020\u0019H\u0002J\u0010\u0010E\u001a\u0002062\u0006\u0010=\u001a\u00020\tH\u0002J\u000e\u0010F\u001a\u0002062\u0006\u00107\u001a\u00020+J\u000e\u0010G\u001a\u0002062\u0006\u00109\u001a\u00020:J\u000e\u0010H\u001a\u0002062\u0006\u00109\u001a\u00020\u000eJ*\u0010I\u001a\u0002062\u0006\u0010J\u001a\u00020\u00192\b\b\u0002\u0010K\u001a\u00020)2\u0010\b\u0002\u0010L\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&J,\u0010M\u001a\u0002062\u0006\u0010J\u001a\u00020\u00192\b\b\u0002\u0010K\u001a\u00020)2\u0010\b\u0002\u0010L\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&H\u0002J\u0016\u0010N\u001a\u00020\u00192\u0006\u0010=\u001a\u00020\t2\u0006\u0010O\u001a\u00020PR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R*\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00160\u0015j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u0010\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0004\n\u0002\u0010$R\u0016\u0010%\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010,\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020-0\u0015j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020-`\u0017¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\b\u0012\u0004\u0012\u00020\t03X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020\t03X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Q"}, m3961d2 = {"Lcom/pudutech/mirsdk/bluetooth/BluetoothBleHelper;", "", "()V", "MTU_SIZE", "", "NOTIFY_UUID", "Ljava/util/UUID;", "kotlin.jvm.PlatformType", "TAG", "", "WRITE_UUID", "blueScanBleListener", "", "Ljava/lang/ref/WeakReference;", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleScanStateListener;", "bluetoothBleContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "bluetoothStateReceiver", "com/pudutech/mirsdk/bluetooth/BluetoothBleHelper$bluetoothStateReceiver$1", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleHelper$bluetoothStateReceiver$1;", "deviceInfoMap", "Ljava/util/HashMap;", "Lcom/pudutech/mirsdk/bluetooth/BluetoothDeviceInfo;", "Lkotlin/collections/HashMap;", "isNeedScan", "", "isScanning", "mBluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "mContext", "Landroid/content/Context;", "mGattCallback", "com/pudutech/mirsdk/bluetooth/BluetoothBleHelper$mGattCallback$1", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleHelper$mGattCallback$1;", "mScanCallback", "com/pudutech/mirsdk/bluetooth/BluetoothBleHelper$mScanCallback$1", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleHelper$mScanCallback$1;", "recentFilter", "", "Landroid/bluetooth/le/ScanFilter;", "recentScanOutTime", "", "scanCallbackList", "Landroid/bluetooth/le/ScanCallback;", "scanResultList", "Landroid/bluetooth/le/ScanResult;", "getScanResultList", "()Ljava/util/HashMap;", "scanTimeoutJob", "Lkotlinx/coroutines/Job;", "waitConnectSet", "", "waitDisconnectSet", "addBleScanCallback", "", "callback", "addBluetoothBleListeners", "listener", "Lcom/pudutech/mirsdk/bluetooth/BluetoothBleListener;", "addBluetoothScanStateListener", "connectDevice", "mac", "disconnectDevice", "getConnectState", "initHelper", "context", "notifyConnectState", "state", "isAllowNotify", "realConnectDevice", "removeBleScanCallBack", "removeBluetoothBleListeners", "removeBluetoothScanStateListener", "scanBleDevice", "enable", "outTime", "filter", "scanBleDeviceEx", "writeData", "data", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BluetoothBleHelper {
    private static final int MTU_SIZE = 128;
    private static final String TAG = "BluetoothBleHelper";
    private static final List<WeakReference<BluetoothBleScanStateListener>> blueScanBleListener;
    private static final ExecutorCoroutineDispatcher bluetoothBleContext;
    private static final BluetoothBleHelper$bluetoothStateReceiver$1 bluetoothStateReceiver;
    private static boolean isNeedScan;
    private static boolean isScanning;
    private static BluetoothAdapter mBluetoothAdapter;
    private static Context mContext;
    private static final BluetoothBleHelper$mGattCallback$1 mGattCallback;
    private static final BluetoothBleHelper$mScanCallback$1 mScanCallback;
    private static List<ScanFilter> recentFilter;
    private static long recentScanOutTime;
    private static final List<WeakReference<ScanCallback>> scanCallbackList;
    private static final HashMap<String, ScanResult> scanResultList;
    private static Job scanTimeoutJob;
    private static Set<String> waitConnectSet;
    private static Set<String> waitDisconnectSet;
    public static final BluetoothBleHelper INSTANCE = new BluetoothBleHelper();
    private static final UUID WRITE_UUID = UUID.fromString("0000c303-0000-1000-8000-00805f9b34fb");
    private static final UUID NOTIFY_UUID = UUID.fromString("0000c305-0000-1000-8000-00805f9b34fb");
    private static final HashMap<String, BluetoothDeviceInfo> deviceInfoMap = new HashMap<>();

    /* JADX WARN: Type inference failed for: r0v10, types: [com.pudutech.mirsdk.bluetooth.BluetoothBleHelper$mScanCallback$1] */
    /* JADX WARN: Type inference failed for: r0v20, types: [com.pudutech.mirsdk.bluetooth.BluetoothBleHelper$bluetoothStateReceiver$1] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.pudutech.mirsdk.bluetooth.BluetoothBleHelper$mGattCallback$1] */
    static {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Intrinsics.checkExpressionValueIsNotNull(defaultAdapter, "BluetoothAdapter.getDefaultAdapter()");
        mBluetoothAdapter = defaultAdapter;
        bluetoothBleContext = ThreadPoolDispatcherKt.newSingleThreadContext("Bluetooth_Ble");
        mGattCallback = new BluetoothGattCallback() { // from class: com.pudutech.mirsdk.bluetooth.BluetoothBleHelper$mGattCallback$1
            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                HashMap hashMap;
                Set set;
                Set set2;
                ExecutorCoroutineDispatcher executorCoroutineDispatcher;
                HashMap hashMap2;
                Intrinsics.checkParameterIsNotNull(gatt, "gatt");
                super.onConnectionStateChange(gatt, status, newState);
                BluetoothDevice device = gatt.getDevice();
                String address = device != null ? device.getAddress() : null;
                Pdlog.m3275i("BluetoothBleHelper", "onConnectionStateChange mac: " + address + " state: " + newState);
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                booleanRef.element = true;
                if (address != null) {
                    if (newState == 0) {
                        BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
                        hashMap = BluetoothBleHelper.deviceInfoMap;
                        BluetoothDeviceInfo bluetoothDeviceInfo = (BluetoothDeviceInfo) hashMap.get(address);
                        if (bluetoothDeviceInfo != null) {
                            bluetoothDeviceInfo.setCharacteristic((BluetoothGattCharacteristic) null);
                        }
                        BluetoothBleHelper bluetoothBleHelper2 = BluetoothBleHelper.INSTANCE;
                        set = BluetoothBleHelper.waitConnectSet;
                        set.remove(address);
                        gatt.close();
                    } else if (newState == 2) {
                        BluetoothBleHelper bluetoothBleHelper3 = BluetoothBleHelper.INSTANCE;
                        set2 = BluetoothBleHelper.waitDisconnectSet;
                        if (set2.contains(address)) {
                            booleanRef.element = false;
                            BluetoothBleHelper bluetoothBleHelper4 = BluetoothBleHelper.INSTANCE;
                            hashMap2 = BluetoothBleHelper.deviceInfoMap;
                            BluetoothDeviceInfo bluetoothDeviceInfo2 = (BluetoothDeviceInfo) hashMap2.get(address);
                            if (bluetoothDeviceInfo2 != null) {
                                bluetoothDeviceInfo2.setConnectState(newState);
                            }
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C4885x5536ce33(null, newState, address, booleanRef, gatt), 3, null);
                        } else {
                            FunctionScope functionScope = FunctionScope.INSTANCE;
                            BluetoothBleHelper bluetoothBleHelper5 = BluetoothBleHelper.INSTANCE;
                            executorCoroutineDispatcher = BluetoothBleHelper.bluetoothBleContext;
                            BuildersKt__Builders_commonKt.launch$default(functionScope, executorCoroutineDispatcher, null, new C4886x5536ce34(null, newState, address, booleanRef, gatt), 2, null);
                        }
                    }
                    BluetoothBleHelper.INSTANCE.notifyConnectState(address, newState, booleanRef.element);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                HashMap hashMap;
                ExecutorCoroutineDispatcher executorCoroutineDispatcher;
                ExecutorCoroutineDispatcher executorCoroutineDispatcher2;
                UUID uuid;
                HashMap hashMap2;
                UUID uuid2;
                Intrinsics.checkParameterIsNotNull(gatt, "gatt");
                super.onServicesDiscovered(gatt, status);
                if (status == 0) {
                    for (BluetoothGattService service : gatt.getServices()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("service.uuid: ");
                        Intrinsics.checkExpressionValueIsNotNull(service, "service");
                        sb.append(service.getUuid().toString());
                        Pdlog.m3275i("BluetoothBleHelper", sb.toString());
                        for (BluetoothGattCharacteristic characteristic : service.getCharacteristics()) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("characteristic.uuid: ");
                            Intrinsics.checkExpressionValueIsNotNull(characteristic, "characteristic");
                            sb2.append(characteristic.getUuid().toString());
                            Pdlog.m3275i("BluetoothBleHelper", sb2.toString());
                            Pdlog.m3275i("BluetoothBleHelper", "characteristic.permissions: " + characteristic.getPermissions());
                            UUID uuid3 = characteristic.getUuid();
                            BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
                            uuid = BluetoothBleHelper.WRITE_UUID;
                            if (Intrinsics.areEqual(uuid3, uuid)) {
                                Pdlog.m3273d("BluetoothBleHelper", "Find write characteristic");
                                BluetoothBleHelper bluetoothBleHelper2 = BluetoothBleHelper.INSTANCE;
                                hashMap2 = BluetoothBleHelper.deviceInfoMap;
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
                                BluetoothBleHelper bluetoothBleHelper3 = BluetoothBleHelper.INSTANCE;
                                uuid2 = BluetoothBleHelper.NOTIFY_UUID;
                                if (Intrinsics.areEqual(uuid4, uuid2)) {
                                    Pdlog.m3273d("BluetoothBleHelper", "Find notify characteristic");
                                    gatt.setCharacteristicNotification(characteristic, true);
                                }
                            }
                        }
                    }
                    BluetoothBleHelper bluetoothBleHelper4 = BluetoothBleHelper.INSTANCE;
                    hashMap = BluetoothBleHelper.deviceInfoMap;
                    BluetoothDevice device2 = gatt.getDevice();
                    String address2 = device2 != null ? device2.getAddress() : null;
                    if (address2 == null) {
                        Intrinsics.throwNpe();
                    }
                    BluetoothDeviceInfo bluetoothDeviceInfo2 = (BluetoothDeviceInfo) hashMap.get(address2);
                    if ((bluetoothDeviceInfo2 != null ? bluetoothDeviceInfo2.getCharacteristic() : null) == null) {
                        Pdlog.m3274e("BluetoothBleHelper", "Can not find characteristic");
                        BluetoothBleHelper bluetoothBleHelper5 = BluetoothBleHelper.INSTANCE;
                        BluetoothDevice device3 = gatt.getDevice();
                        String address3 = device3 != null ? device3.getAddress() : null;
                        if (address3 == null) {
                            Intrinsics.throwNpe();
                        }
                        bluetoothBleHelper5.disconnectDevice(address3);
                        FunctionScope functionScope = FunctionScope.INSTANCE;
                        BluetoothBleHelper bluetoothBleHelper6 = BluetoothBleHelper.INSTANCE;
                        executorCoroutineDispatcher2 = BluetoothBleHelper.bluetoothBleContext;
                        BuildersKt__Builders_commonKt.launch$default(functionScope, executorCoroutineDispatcher2, null, new BluetoothBleHelper$mGattCallback$1$onServicesDiscovered$1(gatt, null), 2, null);
                        return;
                    }
                    FunctionScope functionScope2 = FunctionScope.INSTANCE;
                    BluetoothBleHelper bluetoothBleHelper7 = BluetoothBleHelper.INSTANCE;
                    executorCoroutineDispatcher = BluetoothBleHelper.bluetoothBleContext;
                    BuildersKt__Builders_commonKt.launch$default(functionScope2, executorCoroutineDispatcher, null, new BluetoothBleHelper$mGattCallback$1$onServicesDiscovered$2(gatt, status, null), 2, null);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                Intrinsics.checkParameterIsNotNull(gatt, "gatt");
                Intrinsics.checkParameterIsNotNull(characteristic, "characteristic");
                super.onCharacteristicRead(gatt, characteristic, status);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                ExecutorCoroutineDispatcher executorCoroutineDispatcher;
                Intrinsics.checkParameterIsNotNull(gatt, "gatt");
                Intrinsics.checkParameterIsNotNull(characteristic, "characteristic");
                super.onCharacteristicWrite(gatt, characteristic, status);
                StringBuilder sb = new StringBuilder();
                sb.append("onCharacteristicWrite: uuid:");
                sb.append(characteristic.getUuid());
                sb.append(" value:");
                byte[] value = characteristic.getValue();
                Intrinsics.checkExpressionValueIsNotNull(value, "characteristic.value");
                sb.append(CommonKt.toHexString(value));
                sb.append(" state:");
                sb.append(status);
                Pdlog.m3275i("BluetoothBleHelper", sb.toString());
                FunctionScope functionScope = FunctionScope.INSTANCE;
                BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
                executorCoroutineDispatcher = BluetoothBleHelper.bluetoothBleContext;
                BuildersKt__Builders_commonKt.launch$default(functionScope, executorCoroutineDispatcher, null, new BluetoothBleHelper$mGattCallback$1$onCharacteristicWrite$1(gatt, characteristic, status, null), 2, null);
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                UUID uuid;
                ExecutorCoroutineDispatcher executorCoroutineDispatcher;
                Intrinsics.checkParameterIsNotNull(gatt, "gatt");
                Intrinsics.checkParameterIsNotNull(characteristic, "characteristic");
                super.onCharacteristicChanged(gatt, characteristic);
                UUID uuid2 = characteristic.getUuid();
                BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
                uuid = BluetoothBleHelper.NOTIFY_UUID;
                if (uuid2.equals(uuid)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onCharacteristicChanged ==> ");
                    byte[] value = characteristic.getValue();
                    Intrinsics.checkExpressionValueIsNotNull(value, "characteristic.value");
                    sb.append(CommonKt.toHexString(value));
                    Pdlog.m3273d("BluetoothBleHelper", sb.toString());
                    byte[] value2 = characteristic.getValue();
                    Intrinsics.checkExpressionValueIsNotNull(value2, "characteristic.value");
                    byte[] copyOfRange = ArraysKt.copyOfRange(value2, 0, characteristic.getValue().length);
                    FunctionScope functionScope = FunctionScope.INSTANCE;
                    BluetoothBleHelper bluetoothBleHelper2 = BluetoothBleHelper.INSTANCE;
                    executorCoroutineDispatcher = BluetoothBleHelper.bluetoothBleContext;
                    BuildersKt__Builders_commonKt.launch$default(functionScope, executorCoroutineDispatcher, null, new BluetoothBleHelper$mGattCallback$1$onCharacteristicChanged$1(gatt, copyOfRange, null), 2, null);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                super.onMtuChanged(gatt, mtu, status);
                Pdlog.m3273d("BluetoothBleHelper", "onMtuChanged -->" + mtu);
                if (gatt != null) {
                    gatt.discoverServices();
                }
            }
        };
        mScanCallback = new ScanCallback() { // from class: com.pudutech.mirsdk.bluetooth.BluetoothBleHelper$mScanCallback$1
            @Override // android.bluetooth.le.ScanCallback
            public void onScanResult(int callbackType, ScanResult result) {
                List list;
                ScanRecord scanRecord;
                ScanRecord scanRecord2;
                byte[] bytes;
                BluetoothDevice device;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("mScanCallback == onScanResult type：");
                sb.append(callbackType);
                sb.append(" result:");
                String str = null;
                sb.append((result == null || (device = result.getDevice()) == null) ? null : device.getAddress());
                sb.append(" scanRecord:");
                if (result != null && (scanRecord2 = result.getScanRecord()) != null && (bytes = scanRecord2.getBytes()) != null) {
                    str = CommonKt.toHexString(bytes);
                }
                sb.append(str);
                objArr[0] = sb.toString();
                Pdlog.m3273d("BluetoothBleHelper", objArr);
                if (result != null && (scanRecord = result.getScanRecord()) != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Name:" + scanRecord.getDeviceName() + '\n');
                    if (scanRecord.getServiceUuids() != null) {
                        Iterator<ParcelUuid> it = scanRecord.getServiceUuids().iterator();
                        while (it.hasNext()) {
                            stringBuffer.append("uuid: " + it.next() + '\n');
                        }
                    }
                    Pdlog.m3273d("BluetoothBleHelper", "mScanCallback " + stringBuffer);
                }
                super.onScanResult(callbackType, result);
                if (result != null) {
                    HashMap<String, ScanResult> scanResultList2 = BluetoothBleHelper.INSTANCE.getScanResultList();
                    BluetoothDevice device2 = result.getDevice();
                    Intrinsics.checkExpressionValueIsNotNull(device2, "it.device");
                    String address = device2.getAddress();
                    Intrinsics.checkExpressionValueIsNotNull(address, "it.device.address");
                    scanResultList2.put(address, result);
                }
                BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
                list = BluetoothBleHelper.scanCallbackList;
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    ScanCallback scanCallback = (ScanCallback) ((WeakReference) it2.next()).get();
                    if (scanCallback != null) {
                        scanCallback.onScanResult(callbackType, result);
                    }
                }
            }

            @Override // android.bluetooth.le.ScanCallback
            public void onScanFailed(int errorCode) {
                List list;
                Pdlog.m3273d("BluetoothBleHelper", "mScanCallback == onScanFailed errorCode:" + errorCode);
                super.onScanFailed(errorCode);
                BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
                list = BluetoothBleHelper.scanCallbackList;
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ScanCallback scanCallback = (ScanCallback) ((WeakReference) it.next()).get();
                    if (scanCallback != null) {
                        scanCallback.onScanFailed(errorCode);
                    }
                }
            }
        };
        scanResultList = new HashMap<>();
        scanCallbackList = new ArrayList();
        blueScanBleListener = new ArrayList();
        waitConnectSet = new LinkedHashSet();
        waitDisconnectSet = new LinkedHashSet();
        bluetoothStateReceiver = new BroadcastReceiver() { // from class: com.pudutech.mirsdk.bluetooth.BluetoothBleHelper$bluetoothStateReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                boolean z;
                Set set;
                long j;
                List list;
                if (intent == null || !Intrinsics.areEqual(intent.getAction(), "android.bluetooth.adapter.action.STATE_CHANGED")) {
                    return;
                }
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)) {
                    case 10:
                        Pdlog.m3273d("BluetoothBleHelper", "bluetooth off");
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new BluetoothBleHelper$bluetoothStateReceiver$1$onReceive$1$2(null), 3, null);
                        return;
                    case 11:
                        Pdlog.m3273d("BluetoothBleHelper", "bluetooth turning on");
                        return;
                    case 12:
                        Pdlog.m3273d("BluetoothBleHelper", "bluetooth on");
                        BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
                        z = BluetoothBleHelper.isNeedScan;
                        if (z) {
                            BluetoothBleHelper bluetoothBleHelper2 = BluetoothBleHelper.INSTANCE;
                            BluetoothBleHelper.isNeedScan = false;
                            BluetoothBleHelper bluetoothBleHelper3 = BluetoothBleHelper.INSTANCE;
                            BluetoothBleHelper bluetoothBleHelper4 = BluetoothBleHelper.INSTANCE;
                            j = BluetoothBleHelper.recentScanOutTime;
                            BluetoothBleHelper bluetoothBleHelper5 = BluetoothBleHelper.INSTANCE;
                            list = BluetoothBleHelper.recentFilter;
                            bluetoothBleHelper3.scanBleDeviceEx(true, j, list);
                        }
                        BluetoothBleHelper bluetoothBleHelper6 = BluetoothBleHelper.INSTANCE;
                        set = BluetoothBleHelper.waitConnectSet;
                        if (!set.isEmpty()) {
                            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new BluetoothBleHelper$bluetoothStateReceiver$1$onReceive$1$1(null), 3, null);
                            return;
                        }
                        return;
                    case 13:
                        Pdlog.m3273d("BluetoothBleHelper", "bluetooth turning off");
                        return;
                    default:
                        return;
                }
            }
        };
    }

    private BluetoothBleHelper() {
    }

    public static final /* synthetic */ Context access$getMContext$p(BluetoothBleHelper bluetoothBleHelper) {
        Context context = mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        return context;
    }

    public final void initHelper(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (mContext != null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        mContext = applicationContext;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        Context context2 = mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        context2.registerReceiver(bluetoothStateReceiver, intentFilter);
        if (mBluetoothAdapter.isEnabled()) {
            return;
        }
        mBluetoothAdapter.enable();
    }

    public final HashMap<String, ScanResult> getScanResultList() {
        return scanResultList;
    }

    public final void addBleScanCallback(ScanCallback callback) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Iterator<T> it = scanCallbackList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual((ScanCallback) ((WeakReference) obj).get(), callback)) {
                    break;
                }
            }
        }
        if (((WeakReference) obj) != null) {
            return;
        }
        Boolean.valueOf(scanCallbackList.add(new WeakReference<>(callback)));
    }

    public final void removeBleScanCallBack(ScanCallback callback) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Iterator<T> it = scanCallbackList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual((ScanCallback) ((WeakReference) obj).get(), callback)) {
                    break;
                }
            }
        }
        WeakReference weakReference = (WeakReference) obj;
        if (weakReference != null) {
            scanCallbackList.remove(weakReference);
        }
    }

    public final void addBluetoothBleListeners(BluetoothBleListener listener) {
        List<WeakReference<BluetoothBleListener>> bleListener;
        Object obj;
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (!deviceInfoMap.containsKey(listener.getMac())) {
            deviceInfoMap.put(listener.getMac(), new BluetoothDeviceInfo(listener.getMac(), null, null, 0, null, 30, null));
        }
        BluetoothDeviceInfo bluetoothDeviceInfo = deviceInfoMap.get(listener.getMac());
        if (bluetoothDeviceInfo == null || (bleListener = bluetoothDeviceInfo.getBleListener()) == null) {
            return;
        }
        Iterator<T> it = bleListener.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual((BluetoothBleListener) ((WeakReference) obj).get(), listener)) {
                    break;
                }
            }
        }
        if (((WeakReference) obj) != null) {
            return;
        }
        Boolean.valueOf(bleListener.add(new WeakReference<>(listener)));
    }

    public final void removeBluetoothBleListeners(BluetoothBleListener listener) {
        List<WeakReference<BluetoothBleListener>> bleListener;
        Object obj;
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        BluetoothDeviceInfo bluetoothDeviceInfo = deviceInfoMap.get(listener.getMac());
        if (bluetoothDeviceInfo == null || (bleListener = bluetoothDeviceInfo.getBleListener()) == null) {
            return;
        }
        Iterator<T> it = bleListener.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual((BluetoothBleListener) ((WeakReference) obj).get(), listener)) {
                    break;
                }
            }
        }
        WeakReference weakReference = (WeakReference) obj;
        if (weakReference != null) {
            bleListener.remove(weakReference);
        }
    }

    public final void addBluetoothScanStateListener(BluetoothBleScanStateListener listener) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Iterator<T> it = blueScanBleListener.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual((BluetoothBleScanStateListener) ((WeakReference) obj).get(), listener)) {
                    break;
                }
            }
        }
        if (((WeakReference) obj) != null) {
            return;
        }
        Boolean.valueOf(blueScanBleListener.add(new WeakReference<>(listener)));
    }

    public final void removeBluetoothScanStateListener(BluetoothBleScanStateListener listener) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Iterator<T> it = blueScanBleListener.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual((BluetoothBleScanStateListener) ((WeakReference) obj).get(), listener)) {
                    break;
                }
            }
        }
        WeakReference weakReference = (WeakReference) obj;
        if (weakReference != null) {
            blueScanBleListener.remove(weakReference);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void scanBleDevice$default(BluetoothBleHelper bluetoothBleHelper, boolean z, long j, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 5000;
        }
        if ((i & 4) != 0) {
            list = (List) null;
        }
        bluetoothBleHelper.scanBleDevice(z, j, list);
    }

    public final void scanBleDevice(boolean enable, long outTime, List<ScanFilter> filter) {
        Pdlog.m3273d(TAG, "scanBleDevice enable:" + enable + " outTime:" + outTime);
        recentScanOutTime = outTime;
        recentFilter = filter;
        if (!mBluetoothAdapter.isEnabled()) {
            Pdlog.m3277w(TAG, "scanBleDevice bluetooth was not open");
            if (enable) {
                isNeedScan = true;
                Iterator<T> it = blueScanBleListener.iterator();
                while (it.hasNext()) {
                    BluetoothBleScanStateListener bluetoothBleScanStateListener = (BluetoothBleScanStateListener) ((WeakReference) it.next()).get();
                    if (bluetoothBleScanStateListener != null) {
                        bluetoothBleScanStateListener.onScanState(true);
                    }
                }
                mBluetoothAdapter.enable();
                return;
            }
            isNeedScan = false;
            Iterator<T> it2 = blueScanBleListener.iterator();
            while (it2.hasNext()) {
                BluetoothBleScanStateListener bluetoothBleScanStateListener2 = (BluetoothBleScanStateListener) ((WeakReference) it2.next()).get();
                if (bluetoothBleScanStateListener2 != null) {
                    bluetoothBleScanStateListener2.onScanState(false);
                }
            }
            return;
        }
        scanBleDeviceEx(enable, outTime, filter);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void scanBleDeviceEx$default(BluetoothBleHelper bluetoothBleHelper, boolean z, long j, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 5000;
        }
        if ((i & 4) != 0) {
            list = (List) null;
        }
        bluetoothBleHelper.scanBleDeviceEx(z, j, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scanBleDeviceEx(boolean enable, long outTime, List<ScanFilter> filter) {
        Job launch$default;
        if (!enable) {
            BuildersKt__BuildersKt.runBlocking$default(null, new BluetoothBleHelper$scanBleDeviceEx$3(null), 1, null);
            isScanning = false;
            BluetoothLeScanner bluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(mScanCallback);
            }
            Iterator<T> it = blueScanBleListener.iterator();
            while (it.hasNext()) {
                BluetoothBleScanStateListener bluetoothBleScanStateListener = (BluetoothBleScanStateListener) ((WeakReference) it.next()).get();
                if (bluetoothBleScanStateListener != null) {
                    bluetoothBleScanStateListener.onScanState(false);
                }
            }
            return;
        }
        if (isScanning) {
            Pdlog.m3273d(TAG, "ble is already scanning");
            return;
        }
        isScanning = true;
        launch$default = BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new BluetoothBleHelper$scanBleDeviceEx$1(outTime, null), 3, null);
        scanTimeoutJob = launch$default;
        scanResultList.clear();
        BluetoothLeScanner bluetoothLeScanner2 = mBluetoothAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner2 != null) {
            bluetoothLeScanner2.startScan(filter, new ScanSettings.Builder().build(), mScanCallback);
        }
        Iterator<T> it2 = blueScanBleListener.iterator();
        while (it2.hasNext()) {
            BluetoothBleScanStateListener bluetoothBleScanStateListener2 = (BluetoothBleScanStateListener) ((WeakReference) it2.next()).get();
            if (bluetoothBleScanStateListener2 != null) {
                bluetoothBleScanStateListener2.onScanState(true);
            }
        }
    }

    public final void connectDevice(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        if (getConnectState(mac) == 2 || getConnectState(mac) == 1) {
            return;
        }
        if (waitDisconnectSet.contains(mac)) {
            waitDisconnectSet.remove(mac);
        }
        if (mBluetoothAdapter.isEnabled()) {
            realConnectDevice(mac);
            return;
        }
        notifyConnectState$default(this, mac, 1, false, 4, null);
        waitConnectSet.add(mac);
        mBluetoothAdapter.enable();
    }

    public final void disconnectDevice(String mac) {
        BluetoothGatt bluetoothGatt;
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        if (getConnectState(mac) == 0) {
            return;
        }
        if (getConnectState(mac) == 1) {
            Pdlog.m3273d(TAG, "delay disconnect " + mac);
            waitDisconnectSet.add(mac);
            return;
        }
        waitDisconnectSet.remove(mac);
        waitConnectSet.remove(mac);
        BluetoothDeviceInfo bluetoothDeviceInfo = deviceInfoMap.get(mac);
        if (bluetoothDeviceInfo == null || (bluetoothGatt = bluetoothDeviceInfo.getBluetoothGatt()) == null) {
            return;
        }
        bluetoothGatt.disconnect();
    }

    public final boolean writeData(String mac, byte[] data) {
        BluetoothGattCharacteristic characteristic;
        BluetoothGatt bluetoothGatt;
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(data, "data");
        Pdlog.m3273d(TAG, "writeData mac:" + mac + " data:" + CommonKt.toHexString(data));
        Boolean bool = null;
        if (getConnectState(mac) != 2) {
            Pdlog.m3273d(TAG, mac + " write data fail because not connected");
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, bluetoothBleContext, null, new BluetoothBleHelper$writeData$1(mac, data, null), 2, null);
            return false;
        }
        BluetoothDeviceInfo bluetoothDeviceInfo = deviceInfoMap.get(mac);
        if (bluetoothDeviceInfo != null && (characteristic = bluetoothDeviceInfo.getCharacteristic()) != null) {
            characteristic.setValue(data);
            BluetoothDeviceInfo bluetoothDeviceInfo2 = deviceInfoMap.get(mac);
            if (bluetoothDeviceInfo2 != null && (bluetoothGatt = bluetoothDeviceInfo2.getBluetoothGatt()) != null) {
                bool = Boolean.valueOf(bluetoothGatt.writeCharacteristic(characteristic));
            }
            Pdlog.m3273d(TAG, "writeData result " + bool);
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }
        Pdlog.m3273d(TAG, "write characteristic was null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void notifyConnectState$default(BluetoothBleHelper bluetoothBleHelper, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        bluetoothBleHelper.notifyConnectState(str, i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyConnectState(String mac, int state, boolean isAllowNotify) {
        BluetoothDeviceInfo bluetoothDeviceInfo = deviceInfoMap.get(mac);
        if (bluetoothDeviceInfo != null) {
            bluetoothDeviceInfo.setConnectState(state);
        }
        if (isAllowNotify) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, bluetoothBleContext, null, new BluetoothBleHelper$notifyConnectState$1(mac, state, null), 2, null);
        }
    }

    public final int getConnectState(String mac) {
        BluetoothDeviceInfo bluetoothDeviceInfo;
        String str = mac;
        if ((str == null || str.length() == 0) || (bluetoothDeviceInfo = deviceInfoMap.get(mac)) == null) {
            return 0;
        }
        return bluetoothDeviceInfo.getConnectState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void realConnectDevice(String mac) {
        if (mac.length() == 0) {
            Pdlog.m3274e(TAG, "mac was empty");
            return;
        }
        Pdlog.m3273d(TAG, "ConnectDevice mac[" + mac + ']');
        BluetoothDevice remoteDevice = mBluetoothAdapter.getRemoteDevice(mac);
        if (!deviceInfoMap.containsKey(mac)) {
            deviceInfoMap.put(mac, new BluetoothDeviceInfo(mac, null, null, 0, null, 30, null));
        }
        notifyConnectState$default(this, mac, 1, false, 4, null);
        Context context = mContext;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        BluetoothGatt connectGatt = remoteDevice.connectGatt(context, false, mGattCallback);
        BluetoothDeviceInfo bluetoothDeviceInfo = deviceInfoMap.get(mac);
        if (bluetoothDeviceInfo != null) {
            bluetoothDeviceInfo.setBluetoothGatt(connectGatt);
        }
    }
}
