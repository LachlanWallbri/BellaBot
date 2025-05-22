package com.slamtec.slamware.discovery;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
final class BleScanDevice {
    private static final int TIME_OUT = 10000;
    private final BleDevice mDevice;
    private BluetoothGatt mGatt;
    private final BleScanDeviceListener mListener;
    private boolean success = false;
    private final Timer timer = new Timer();
    private static final UUID UUID_SERVICE = UUID.fromString("00001848-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_INFO = UUID.fromString("00002aec-0000-1000-8000-00805f9b34fb");
    private static final UUID UUID_MODEL = UUID.fromString("00002aed-0000-1000-8000-00805f9b34fb");

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public interface BleScanDeviceListener {
        void onFailure(BleDevice bleDevice);

        void onSuccess(BleDevice bleDevice);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BleScanDevice(BleDevice bleDevice, BleScanDeviceListener bleScanDeviceListener) {
        this.mDevice = bleDevice;
        this.mListener = bleScanDeviceListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void execute(Context context) {
        this.mGatt = this.mDevice.getDevice().connectGatt(context, false, new BluetoothGattCallback() { // from class: com.slamtec.slamware.discovery.BleScanDevice.1
            @Override // android.bluetooth.BluetoothGattCallback
            public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
                super.onConnectionStateChange(bluetoothGatt, i, i2);
                if (i == 133) {
                    bluetoothGatt.close();
                    BleScanDevice.this.mListener.onFailure(BleScanDevice.this.mDevice);
                } else {
                    if (i2 == 2) {
                        BleScanDevice.this.startTimer();
                        bluetoothGatt.discoverServices();
                        return;
                    }
                    BleScanDevice.this.timer.cancel();
                    bluetoothGatt.close();
                    if (BleScanDevice.this.success) {
                        return;
                    }
                    BleScanDevice.this.mListener.onFailure(BleScanDevice.this.mDevice);
                }
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
                super.onServicesDiscovered(bluetoothGatt, i);
                if (i == 0) {
                    BluetoothGattService service = bluetoothGatt.getService(BleScanDevice.UUID_SERVICE);
                    if (service != null) {
                        bluetoothGatt.readCharacteristic(service.getCharacteristic(BleScanDevice.UUID_INFO));
                        return;
                    } else {
                        bluetoothGatt.disconnect();
                        return;
                    }
                }
                bluetoothGatt.disconnect();
            }

            @Override // android.bluetooth.BluetoothGattCallback
            public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
                super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
                if (i != 0) {
                    bluetoothGatt.disconnect();
                    return;
                }
                if (bluetoothGattCharacteristic != null) {
                    if (bluetoothGattCharacteristic.getUuid().compareTo(BleScanDevice.UUID_INFO) != 0) {
                        if (bluetoothGattCharacteristic.getUuid().compareTo(BleScanDevice.UUID_MODEL) == 0) {
                            try {
                                String[] split = new String(bluetoothGattCharacteristic.getValue()).split(",");
                                if (split.length == 6) {
                                    int parseInt = Integer.parseInt(split[0]);
                                    String str = split[1];
                                    int parseInt2 = Integer.parseInt(split[2]);
                                    String str2 = split[3];
                                    String str3 = split[4];
                                    String str4 = split[5];
                                    BleScanDevice.this.mDevice.setManufactureId(parseInt);
                                    BleScanDevice.this.mDevice.setManufactureName(str);
                                    BleScanDevice.this.mDevice.setModelId(parseInt2);
                                    BleScanDevice.this.mDevice.setModelName(str2);
                                    BleScanDevice.this.mDevice.setHardwareVersion(str3);
                                    BleScanDevice.this.mDevice.setSoftwareVersion(str4);
                                    BleScanDevice.this.success = true;
                                    BleScanDevice.this.timer.cancel();
                                    bluetoothGatt.disconnect();
                                    BleScanDevice.this.mListener.onSuccess(BleScanDevice.this.mDevice);
                                } else {
                                    bluetoothGatt.disconnect();
                                }
                                return;
                            } catch (Exception unused) {
                                bluetoothGatt.disconnect();
                                return;
                            }
                        }
                        return;
                    }
                    try {
                        ByteBuffer wrap = ByteBuffer.wrap(bluetoothGattCharacteristic.getValue());
                        BleScanDevice.this.mDevice.setDeviceId(new UUID(wrap.getLong(), wrap.getLong()));
                        BleScanDevice.this.mDevice.setDeviceName(bluetoothGatt.getDevice().getName());
                        bluetoothGatt.readCharacteristic(bluetoothGatt.getService(BleScanDevice.UUID_SERVICE).getCharacteristic(BleScanDevice.UUID_MODEL));
                        return;
                    } catch (Exception unused2) {
                        bluetoothGatt.disconnect();
                        return;
                    }
                }
                bluetoothGatt.disconnect();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTimer() {
        this.timer.schedule(new TimerTask() { // from class: com.slamtec.slamware.discovery.BleScanDevice.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (BleScanDevice.this.success) {
                    return;
                }
                BleScanDevice.this.mGatt.disconnect();
            }
        }, 10000L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stop() {
        this.timer.cancel();
        this.mGatt.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BleDevice getDevice() {
        return this.mDevice;
    }
}
