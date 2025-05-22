package com.felhr.usbserial;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import com.felhr.deviceids.CP2130Ids;
import com.felhr.usbserial.UsbSpiInterface;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class UsbSpiDevice implements UsbSpiInterface {
    private static final String CLASS_ID = UsbSerialDevice.class.getSimpleName();
    protected static final int USB_TIMEOUT = 5000;
    protected final UsbDeviceConnection connection;
    protected final UsbDevice device;
    private UsbEndpoint inEndpoint;
    private UsbEndpoint outEndpoint;
    protected ReadThread readThread;
    protected SerialBuffer serialBuffer = new SerialBuffer(false);
    protected WriteThread writeThread;

    @Override // com.felhr.usbserial.UsbSpiInterface
    public abstract void closeSPI();

    @Override // com.felhr.usbserial.UsbSpiInterface
    public abstract boolean connectSPI();

    @Override // com.felhr.usbserial.UsbSpiInterface
    public abstract int getClockDivider();

    @Override // com.felhr.usbserial.UsbSpiInterface
    public abstract int getSelectedSlave();

    @Override // com.felhr.usbserial.UsbSpiInterface
    public abstract void readMISO(int i);

    @Override // com.felhr.usbserial.UsbSpiInterface
    public abstract void selectSlave(int i);

    @Override // com.felhr.usbserial.UsbSpiInterface
    public abstract void setClock(int i);

    @Override // com.felhr.usbserial.UsbSpiInterface
    public abstract void writeMOSI(byte[] bArr);

    @Override // com.felhr.usbserial.UsbSpiInterface
    public abstract void writeRead(byte[] bArr, int i);

    public UsbSpiDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this.device = usbDevice;
        this.connection = usbDeviceConnection;
    }

    public static UsbSpiDevice createUsbSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        return createUsbSerialDevice(usbDevice, usbDeviceConnection, -1);
    }

    public static UsbSpiDevice createUsbSerialDevice(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection, int i) {
        if (CP2130Ids.isDeviceSupported(usbDevice.getVendorId(), usbDevice.getProductId())) {
            return new CP2130SpiDevice(usbDevice, usbDeviceConnection, i);
        }
        return null;
    }

    @Override // com.felhr.usbserial.UsbSpiInterface
    public void setMISOCallback(UsbSpiInterface.UsbMISOCallback usbMISOCallback) {
        this.readThread.setCallback(usbMISOCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public class WriteThread extends Thread {
        private UsbEndpoint outEndpoint;
        private AtomicBoolean working = new AtomicBoolean(true);

        public WriteThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.working.get()) {
                byte[] writeBuffer = UsbSpiDevice.this.serialBuffer.getWriteBuffer();
                UsbSpiDevice.this.connection.bulkTransfer(this.outEndpoint, writeBuffer, writeBuffer.length, 5000);
            }
        }

        public void setUsbEndpoint(UsbEndpoint usbEndpoint) {
            this.outEndpoint = usbEndpoint;
        }

        public void stopWriteThread() {
            this.working.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public class ReadThread extends Thread {
        private UsbEndpoint inEndpoint;
        private UsbSpiInterface.UsbMISOCallback misoCallback;
        private AtomicBoolean working = new AtomicBoolean(true);

        public ReadThread() {
        }

        public void setCallback(UsbSpiInterface.UsbMISOCallback usbMISOCallback) {
            this.misoCallback = usbMISOCallback;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.working.get()) {
                int bulkTransfer = this.inEndpoint != null ? UsbSpiDevice.this.connection.bulkTransfer(this.inEndpoint, UsbSpiDevice.this.serialBuffer.getBufferCompatible(), 16384, 0) : 0;
                if (bulkTransfer > 0) {
                    onReceivedData(UsbSpiDevice.this.serialBuffer.getDataReceivedCompatible(bulkTransfer));
                }
            }
        }

        public void setUsbEndpoint(UsbEndpoint usbEndpoint) {
            this.inEndpoint = usbEndpoint;
        }

        public void stopReadThread() {
            this.working.set(false);
        }

        private void onReceivedData(byte[] bArr) {
            UsbSpiInterface.UsbMISOCallback usbMISOCallback = this.misoCallback;
            if (usbMISOCallback != null) {
                usbMISOCallback.onReceivedData(bArr);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setThreadsParams(UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2) {
        WriteThread writeThread = this.writeThread;
        if (writeThread != null) {
            writeThread.setUsbEndpoint(usbEndpoint2);
        }
        ReadThread readThread = this.readThread;
        if (readThread != null) {
            readThread.setUsbEndpoint(usbEndpoint);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void killWorkingThread() {
        ReadThread readThread = this.readThread;
        if (readThread != null) {
            readThread.stopReadThread();
            this.readThread = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void restartWorkingThread() {
        ReadThread readThread = new ReadThread();
        this.readThread = readThread;
        readThread.start();
        do {
        } while (!this.readThread.isAlive());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void killWriteThread() {
        WriteThread writeThread = this.writeThread;
        if (writeThread != null) {
            writeThread.stopWriteThread();
            this.writeThread = null;
            this.serialBuffer.resetWriteBuffer();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void restartWriteThread() {
        if (this.writeThread == null) {
            WriteThread writeThread = new WriteThread();
            this.writeThread = writeThread;
            writeThread.start();
            do {
            } while (!this.writeThread.isAlive());
        }
    }
}
