package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbRequest;
import android.util.Log;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.util.MonotonicClock;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.EnumSet;

/* loaded from: classes4.dex */
public abstract class CommonUsbSerialPort implements UsbSerialPort {
    private static final int DEFAULT_WRITE_BUFFER_SIZE = 16384;
    private static final int MAX_READ_SIZE = 16384;
    private static final String TAG = CommonUsbSerialPort.class.getSimpleName();
    protected final UsbDevice mDevice;
    protected final int mPortNumber;
    protected UsbEndpoint mReadEndpoint;
    protected UsbRequest mUsbRequest;
    protected UsbEndpoint mWriteEndpoint;
    protected UsbDeviceConnection mConnection = null;
    protected final Object mWriteBufferLock = new Object();
    protected byte[] mWriteBuffer = new byte[16384];

    protected abstract void closeInt();

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public abstract EnumSet<UsbSerialPort.ControlLine> getControlLines() throws IOException;

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public abstract EnumSet<UsbSerialPort.ControlLine> getSupportedControlLines() throws IOException;

    protected abstract void openInt(UsbDeviceConnection connection) throws IOException;

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public abstract void setParameters(int baudRate, int dataBits, int stopBits, int parity) throws IOException;

    public CommonUsbSerialPort(UsbDevice device, int portNumber) {
        this.mDevice = device;
        this.mPortNumber = portNumber;
    }

    public String toString() {
        return String.format("<%s device_name=%s device_id=%s port_number=%s>", getClass().getSimpleName(), this.mDevice.getDeviceName(), Integer.valueOf(this.mDevice.getDeviceId()), Integer.valueOf(this.mPortNumber));
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public UsbDevice getDevice() {
        return this.mDevice;
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public int getPortNumber() {
        return this.mPortNumber;
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public UsbEndpoint getWriteEndpoint() {
        return this.mWriteEndpoint;
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public UsbEndpoint getReadEndpoint() {
        return this.mReadEndpoint;
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public String getSerial() {
        return this.mConnection.getSerial();
    }

    public final void setWriteBufferSize(int bufferSize) {
        synchronized (this.mWriteBufferLock) {
            if (bufferSize == this.mWriteBuffer.length) {
                return;
            }
            this.mWriteBuffer = new byte[bufferSize];
        }
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public void open(UsbDeviceConnection connection) throws IOException {
        if (this.mConnection != null) {
            throw new IOException("Already open");
        }
        if (connection == null) {
            throw new IllegalArgumentException("Connection is null");
        }
        this.mConnection = connection;
        try {
            openInt(connection);
            if (this.mReadEndpoint == null || this.mWriteEndpoint == null) {
                throw new IOException("Could not get read & write endpoints");
            }
            this.mUsbRequest = new UsbRequest();
            this.mUsbRequest.initialize(this.mConnection, this.mReadEndpoint);
        } catch (Exception e) {
            try {
                close();
            } catch (Exception unused) {
            }
            throw e;
        }
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.mConnection == null) {
            throw new IOException("Already closed");
        }
        try {
            this.mUsbRequest.cancel();
        } catch (Exception unused) {
        }
        this.mUsbRequest = null;
        try {
            closeInt();
        } catch (Exception unused2) {
        }
        try {
            this.mConnection.close();
        } catch (Exception unused3) {
        }
        this.mConnection = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void testConnection() throws IOException {
        byte[] bArr = new byte[2];
        if (this.mConnection.controlTransfer(128, 0, 0, 0, bArr, bArr.length, 200) < 0) {
            throw new IOException("USB get_status request failed");
        }
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public int read(final byte[] dest, final int timeout) throws IOException {
        return read(dest, timeout, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int read(final byte[] dest, final int timeout, boolean testConnection) throws IOException {
        int position;
        if (this.mConnection == null) {
            throw new IOException("Connection closed");
        }
        if (dest.length <= 0) {
            throw new IllegalArgumentException("Read buffer to small");
        }
        if (timeout != 0) {
            long millis = testConnection ? MonotonicClock.millis() + timeout : 0L;
            position = this.mConnection.bulkTransfer(this.mReadEndpoint, dest, Math.min(dest.length, 16384), timeout);
            if (position == -1 && testConnection && MonotonicClock.millis() < millis) {
                testConnection();
            }
        } else {
            ByteBuffer wrap = ByteBuffer.wrap(dest);
            if (!this.mUsbRequest.queue(wrap, dest.length)) {
                throw new IOException("Queueing USB request failed");
            }
            if (this.mConnection.requestWait() == null) {
                throw new IOException("Waiting for USB request failed");
            }
            position = wrap.position();
            if (position == 0) {
                testConnection();
            }
        }
        return Math.max(position, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0040 A[Catch: all -> 0x00e9, TryCatch #0 {, blocks: (B:11:0x0017, B:17:0x0030, B:22:0x0048, B:35:0x0040, B:37:0x0024), top: B:10:0x0017 }] */
    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(final byte[] src, final int timeout) throws IOException {
        int min;
        byte[] bArr;
        int i;
        int bulkTransfer;
        long millis = timeout == 0 ? 0L : MonotonicClock.millis() + timeout;
        if (this.mConnection == null) {
            throw new IOException("Connection closed");
        }
        int i2 = 0;
        while (i2 < src.length) {
            synchronized (this.mWriteBufferLock) {
                min = Math.min(src.length - i2, this.mWriteBuffer.length);
                if (i2 == 0) {
                    bArr = src;
                } else {
                    System.arraycopy(src, i2, this.mWriteBuffer, 0, min);
                    bArr = this.mWriteBuffer;
                }
                if (timeout != 0 && i2 != 0) {
                    i = (int) (millis - MonotonicClock.millis());
                    if (i == 0) {
                        i = -1;
                    }
                    bulkTransfer = i >= 0 ? -2 : this.mConnection.bulkTransfer(this.mWriteEndpoint, bArr, min, i);
                }
                i = timeout;
                if (i >= 0) {
                }
            }
            Log.d(TAG, "Wrote " + bulkTransfer + "/" + min + " offset " + i2 + "/" + src.length + " timeout " + i);
            if (bulkTransfer <= 0) {
                if (timeout != 0 && MonotonicClock.millis() >= millis) {
                    SerialTimeoutException serialTimeoutException = new SerialTimeoutException("Error writing " + min + " bytes at offset " + i2 + " of total " + src.length + ", rc=" + bulkTransfer);
                    serialTimeoutException.bytesTransferred = i2;
                    throw serialTimeoutException;
                }
                throw new IOException("Error writing " + min + " bytes at offset " + i2 + " of total " + src.length);
            }
            i2 += bulkTransfer;
        }
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public boolean isOpen() {
        return this.mConnection != null;
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public boolean getCD() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public boolean getCTS() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public boolean getDSR() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public boolean getDTR() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public void setDTR(boolean value) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public boolean getRI() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public boolean getRTS() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public void setRTS(boolean value) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public void purgeHwBuffers(boolean purgeWriteBuffers, boolean purgeReadBuffers) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.hoho.android.usbserial.driver.UsbSerialPort
    public void setBreak(boolean value) throws IOException {
        throw new UnsupportedOperationException();
    }
}
