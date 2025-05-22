package com.slamtec.slamware.discovery;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import com.iflytek.cloud.SpeechConstant;
import com.slamtec.slamware.discovery.AbstractDiscover;
import com.slamtec.slamware.discovery.BleScanManager;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
class BleDiscover extends AbstractDiscover implements BleScanManager.BleScanManagerListener {
    private BluetoothAdapter bluetoothAdapter;
    private boolean configing;
    private AbstractDiscover.BleConfigureListener configureListener;
    private WeakReference<Context> context;
    private AbstractDiscover.DiscoveryListener listener;
    private String password;
    private final BleScanManager scanManager;
    private String ssid;
    private static final UUID UUID_WRITING = UUID.fromString("00002aea-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_NOTIFY = UUID.fromString("00002aeb-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_SERVICE = UUID.fromString("00001848-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_INFO = UUID.fromString("00002aec-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_MODEL = UUID.fromString("00002aed-0000-1000-8000-00805f9b34fb");
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() { // from class: com.slamtec.slamware.discovery.BleDiscover.1
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            String name = bluetoothDevice.getName();
            if (name == null || !name.toLowerCase().contains("slamware")) {
                return;
            }
            BleDiscover.this.scanManager.queue(bluetoothDevice);
        }
    };
    private BluetoothGattCallback gattConfigureCallback = new BluetoothGattCallback() { // from class: com.slamtec.slamware.discovery.BleDiscover.2
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            if (!BleDiscover.this.configing || i2 != 2) {
                if (BleDiscover.this.configing && i2 == 0) {
                    BleDiscover.this.configing = false;
                    BleDiscover.this.configureListener.onConfigureFailure(AbstractDiscover.BLE_CONFIG_ERROR_BLE_DISCONNECTED);
                    bluetoothGatt.close();
                    return;
                }
                return;
            }
            bluetoothGatt.discoverServices();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            BleDiscover.this.enableNotify(bluetoothGatt);
            BleDiscover.this.writeConfig(bluetoothGatt);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (!BleDiscover.this.configing || i == 0) {
                return;
            }
            BleDiscover.this.configing = false;
            BleDiscover.this.configureListener.onConfigureFailure(AbstractDiscover.BLE_CONFIG_ERROR_WRITING_FAIL);
            bluetoothGatt.disconnect();
            bluetoothGatt.close();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            BleDiscover.this.parseConfig(bluetoothGatt, bluetoothGattCharacteristic);
        }
    };
    private AbstractDiscover.DiscoverStatus status = AbstractDiscover.DiscoverStatus.STOPPED;

    public BleDiscover(WeakReference<Context> weakReference) {
        this.context = weakReference;
        this.bluetoothAdapter = ((BluetoothManager) weakReference.get().getSystemService(SpeechConstant.BLUETOOTH)).getAdapter();
        this.scanManager = new BleScanManager(weakReference, this);
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public void setListener(AbstractDiscover.DiscoveryListener discoveryListener) {
        this.listener = discoveryListener;
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public AbstractDiscover.DiscoverStatus getStatus(DiscoveryMode discoveryMode) {
        return this.status;
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public void start(DiscoveryMode discoveryMode) {
        if (this.status == AbstractDiscover.DiscoverStatus.WORKING) {
            return;
        }
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            this.status = AbstractDiscover.DiscoverStatus.ERROR;
            this.listener.onDiscoveryError(this, "Discover: bluetooth is shut down");
        } else {
            this.scanManager.reset();
            this.status = AbstractDiscover.DiscoverStatus.WORKING;
            this.bluetoothAdapter.startLeScan(new UUID[]{UUID_SERVICE}, this.leScanCallback);
            this.listener.onStartDiscovery(this);
        }
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public void stop(DiscoveryMode discoveryMode) {
        if (this.status == AbstractDiscover.DiscoverStatus.WORKING) {
            this.bluetoothAdapter.stopLeScan(this.leScanCallback);
            this.listener.onStopDiscovery(this);
            this.status = AbstractDiscover.DiscoverStatus.STOPPED;
            this.scanManager.reset();
        }
    }

    @Override // com.slamtec.slamware.discovery.AbstractDiscover
    public DiscoveryMode getMode() {
        return DiscoveryMode.BLE;
    }

    @Override // com.slamtec.slamware.discovery.BleScanManager.BleScanManagerListener
    public void onDeviceFound(BleDevice bleDevice) {
        this.listener.onDeviceFound(this, bleDevice);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void configDevice(Device device, String str, String str2, AbstractDiscover.BleConfigureListener bleConfigureListener) {
        if (str == null || str.isEmpty()) {
            bleConfigureListener.onConfigureFailure(AbstractDiscover.BLE_CONFIG_ERROR_SSID_REQUIRED);
            return;
        }
        if (!(device instanceof BleDevice)) {
            bleConfigureListener.onConfigureFailure("Discover: bluetoothInvalid device");
            return;
        }
        this.configureListener = bleConfigureListener;
        this.configing = true;
        this.ssid = str;
        this.password = str2;
        ((BleDevice) device).getDevice().connectGatt(this.context.get(), false, this.gattConfigureCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableNotify(BluetoothGatt bluetoothGatt) {
        bluetoothGatt.setCharacteristicNotification(bluetoothGatt.getService(UUID_SERVICE).getCharacteristic(UUID_NOTIFY), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeConfig(BluetoothGatt bluetoothGatt) {
        byte[] bArr;
        String str;
        BluetoothGattCharacteristic characteristic = bluetoothGatt.getService(UUID_SERVICE).getCharacteristic(UUID_WRITING);
        String str2 = this.ssid;
        if (str2 != null && !str2.isEmpty() && ((str = this.password) == null || str.isEmpty())) {
            byte[] bytes = this.ssid.getBytes(Charset.forName("UTF-8"));
            bArr = new byte[bytes.length + 2];
            bArr[0] = 1;
            bArr[1] = (byte) bytes.length;
            System.arraycopy(bytes, 0, bArr, 2, bytes.length);
        } else {
            String str3 = this.ssid;
            if (str3 != null && !str3.isEmpty()) {
                byte[] bytes2 = this.ssid.getBytes(Charset.forName("UTF-8"));
                byte[] bytes3 = this.password.getBytes(Charset.forName("UTF-8"));
                byte[] bArr2 = new byte[bytes2.length + bytes3.length + 4];
                bArr2[0] = 1;
                bArr2[1] = (byte) bytes2.length;
                System.arraycopy(bytes2, 0, bArr2, 2, bytes2.length);
                bArr2[bytes2.length + 2] = 2;
                bArr2[bytes2.length + 3] = (byte) bytes3.length;
                System.arraycopy(bytes3, 0, bArr2, bytes2.length + 4, bytes3.length);
                bArr = bArr2;
            } else {
                this.configureListener.onConfigureFailure(AbstractDiscover.BLE_CONFIG_ERROR_SSID_REQUIRED);
                return;
            }
        }
        byte[] bArr3 = new byte[bArr.length + 1];
        bArr3[0] = (byte) (bArr.length + 1);
        System.arraycopy(bArr, 0, bArr3, 1, bArr.length);
        characteristic.setValue(bArr3);
        bluetoothGatt.writeCharacteristic(characteristic);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseConfig(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (!bluetoothGattCharacteristic.getUuid().equals(UUID_NOTIFY)) {
            bluetoothGatt.disconnect();
            bluetoothGatt.close();
            return;
        }
        byte b = bluetoothGattCharacteristic.getValue()[0];
        if (b == 0) {
            this.configureListener.onConfigureSuccess();
        } else if (b == 1) {
            this.configureListener.onConfigureFailure(AbstractDiscover.BLE_CONFIG_ERROR_UNABLE_CONNECT_WIFI);
        } else if (b != 2) {
            return;
        } else {
            this.configureListener.onConfigureFailure(AbstractDiscover.BLE_CONFIG_ERROR_INVALID_PWD);
        }
        this.configing = false;
        this.ssid = null;
        this.password = null;
        this.configureListener = null;
        bluetoothGatt.disconnect();
        bluetoothGatt.close();
    }
}
