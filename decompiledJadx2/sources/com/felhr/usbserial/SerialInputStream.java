package com.felhr.usbserial;

import com.felhr.usbserial.UsbSerialInterface;
import java.io.InputStream;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class SerialInputStream extends InputStream implements UsbSerialInterface.UsbReadCallback {
    protected final UsbSerialInterface device;
    protected ArrayBlockingQueue data = new ArrayBlockingQueue(256);
    protected volatile boolean is_open = true;

    public SerialInputStream(UsbSerialInterface usbSerialInterface) {
        this.device = usbSerialInterface;
        usbSerialInterface.read(this);
    }

    @Override // java.io.InputStream
    public int read() {
        while (this.is_open) {
            try {
                return ((Integer) this.data.take()).intValue();
            } catch (InterruptedException unused) {
            }
        }
        return -1;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.is_open = false;
        try {
            this.data.put(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.felhr.usbserial.UsbSerialInterface.UsbReadCallback
    public void onReceivedData(byte[] bArr) {
        for (byte b : bArr) {
            try {
                this.data.put(Integer.valueOf(b & 255));
            } catch (InterruptedException unused) {
            }
        }
    }
}
