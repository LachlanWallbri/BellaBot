package com.slamtec.slamware.discovery;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.slamtec.slamware.discovery.BleScanDevice;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public final class BleScanManager implements BleScanDevice.BleScanDeviceListener {
    private final WeakReference<Context> mContext;
    private BleScanDevice mCurrentDevice;
    private final BleScanManagerListener mListener;
    private final ConcurrentHashMap<String, BleDevice> pendingDevices = new ConcurrentHashMap<>();
    private final ConcurrentLinkedQueue<String> successDevices = new ConcurrentLinkedQueue<>();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public interface BleScanManagerListener {
        void onDeviceFound(BleDevice bleDevice);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BleScanManager(WeakReference<Context> weakReference, BleScanManagerListener bleScanManagerListener) {
        this.mContext = weakReference;
        this.mListener = bleScanManagerListener;
    }

    private synchronized void setCurrentDevice(BleScanDevice bleScanDevice) {
        this.mCurrentDevice = bleScanDevice;
    }

    private synchronized void appendSuccess(BleDevice bleDevice) {
        this.successDevices.add(bleDevice.getDevice().getAddress());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void queue(BluetoothDevice bluetoothDevice) {
        if (!this.pendingDevices.containsKey(bluetoothDevice.getAddress()) && !this.successDevices.contains(bluetoothDevice.getAddress()) && (this.mCurrentDevice == null || !this.mCurrentDevice.getDevice().getDevice().getAddress().equals(bluetoothDevice.getAddress()))) {
            this.pendingDevices.put(bluetoothDevice.getAddress(), new BleDevice(bluetoothDevice));
            drive();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void reset() {
        this.successDevices.clear();
        this.pendingDevices.clear();
        if (this.mCurrentDevice != null) {
            this.mCurrentDevice.stop();
        }
        setCurrentDevice(null);
    }

    private synchronized void drive() {
        if (this.mCurrentDevice != null) {
            return;
        }
        if (this.pendingDevices.size() == 0) {
            return;
        }
        if (this.mContext.get() == null) {
            return;
        }
        BleScanDevice bleScanDevice = new BleScanDevice(this.pendingDevices.remove(this.pendingDevices.entrySet().iterator().next().getKey()), this);
        setCurrentDevice(bleScanDevice);
        bleScanDevice.execute(this.mContext.get());
    }

    @Override // com.slamtec.slamware.discovery.BleScanDevice.BleScanDeviceListener
    public void onSuccess(BleDevice bleDevice) {
        appendSuccess(bleDevice);
        this.mListener.onDeviceFound(bleDevice);
        setCurrentDevice(null);
        drive();
    }

    @Override // com.slamtec.slamware.discovery.BleScanDevice.BleScanDeviceListener
    public void onFailure(BleDevice bleDevice) {
        setCurrentDevice(null);
        drive();
    }
}
