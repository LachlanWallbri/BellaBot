package com.felhr.usbserial;

import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class SerialOutputStream extends OutputStream {
    protected final UsbSerialInterface device;

    public SerialOutputStream(UsbSerialInterface usbSerialInterface) {
        this.device = usbSerialInterface;
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.device.write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.device.write(bArr);
    }
}
