package com.felhr.usbserial;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbRequest;
import com.felhr.usbserial.UsbSerialInterface;
import com.felhr.utils.SafeUsbRequest;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class SerialReadThread extends AbstractWorkerThread {
    static int DEFAULT_READ_BUFFER_SIZE = 1024;
    static int USB_REQUEST_NUM = 48;
    private int bufferSize;
    private ByteBuffer[] byteBuffers;
    private UsbSerialInterface.UsbReadCallback callback;
    private UsbDeviceConnection connection;
    private UsbEndpoint pointIn;
    private UsbRequest[] usbRequests;

    public SerialReadThread(UsbEndpoint usbEndpoint, UsbDeviceConnection usbDeviceConnection, int i) {
        this.bufferSize = DEFAULT_READ_BUFFER_SIZE;
        int i2 = USB_REQUEST_NUM;
        this.usbRequests = new UsbRequest[i2];
        this.byteBuffers = new ByteBuffer[i2];
        this.pointIn = usbEndpoint;
        this.connection = usbDeviceConnection;
        this.bufferSize = i;
        int i3 = 0;
        while (true) {
            UsbRequest[] usbRequestArr = this.usbRequests;
            if (i3 < usbRequestArr.length) {
                usbRequestArr[i3] = new SafeUsbRequest();
                this.usbRequests[i3].initialize(usbDeviceConnection, this.pointIn);
                this.byteBuffers[i3] = ByteBuffer.allocate(this.bufferSize);
                i3++;
            } else {
                setPriority(10);
                setName("UsbReadThd");
                return;
            }
        }
    }

    @Override // com.felhr.usbserial.AbstractWorkerThread
    void doRun() {
        for (int i = 0; i < USB_REQUEST_NUM; i++) {
            this.usbRequests[i].queue(this.byteBuffers[i], this.bufferSize);
        }
        while (this.keep) {
            for (int i2 = 0; i2 < USB_REQUEST_NUM; i2++) {
                UsbRequest requestWait = this.connection.requestWait();
                if (requestWait != null && requestWait == this.usbRequests[i2]) {
                    byte[] bArr = new byte[this.byteBuffers[i2].position()];
                    this.byteBuffers[i2].position(0);
                    this.byteBuffers[i2].get(bArr, 0, bArr.length);
                    this.byteBuffers[i2].clear();
                    UsbSerialInterface.UsbReadCallback usbReadCallback = this.callback;
                    if (usbReadCallback != null) {
                        usbReadCallback.onReceivedData(bArr);
                    }
                    this.usbRequests[i2].queue(this.byteBuffers[i2], this.bufferSize);
                }
            }
        }
    }

    public void setonReceiveCallback(UsbSerialInterface.UsbReadCallback usbReadCallback) {
        this.callback = usbReadCallback;
    }
}
