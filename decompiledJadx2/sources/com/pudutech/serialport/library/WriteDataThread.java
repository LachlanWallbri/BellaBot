package com.pudutech.serialport.library;

import com.pudutech.base.Pdlog;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class WriteDataThread extends Thread {
    private static final String TAG = WriteDataThread.class.getSimpleName();
    private SerialPortHelper mSerialPortHelper;
    private final ConcurrentLinkedQueue<byte[]> mWriteDataQueue = new ConcurrentLinkedQueue<>();

    public WriteDataThread(SerialPortHelper serialPortHelper) {
        this.mSerialPortHelper = serialPortHelper;
    }

    public void addDataToQueue(byte[] bArr) {
        synchronized (this) {
            this.mWriteDataQueue.offer(bArr);
            Pdlog.m3273d(TAG, "addDataToQueue() data = " + Arrays.toString(bArr));
            Pdlog.m3273d(TAG, "notify.");
            notify();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!isInterrupted()) {
            if (this.mSerialPortHelper.isOpened()) {
                synchronized (this.mWriteDataQueue) {
                    while (this.mWriteDataQueue.peek() != null) {
                        writeData(this.mWriteDataQueue.poll());
                    }
                }
            }
            if (!this.mSerialPortHelper.isOpened()) {
                Pdlog.m3273d(TAG, "The SerialPort is closed, thread run is need to wait.");
            }
            if (this.mWriteDataQueue.size() == 0) {
                Pdlog.m3273d(TAG, "The WriteDataQueue is empty, thread run is need to wait.");
            }
            synchronized (this) {
                try {
                    Pdlog.m3273d(TAG, "wait.");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeData(byte[] bArr) {
        Pdlog.m3273d(TAG, "writeData() data = " + Arrays.toString(bArr));
        if (bArr == null || bArr.length == 0) {
            Pdlog.m3274e(TAG, "writeData failure, reason : data is null or empty.");
            return;
        }
        OutputStream outputStream = this.mSerialPortHelper.getOutputStream();
        if (outputStream == null) {
            Pdlog.m3274e(TAG, "writeData failure, reason : outputStream is.");
            return;
        }
        try {
            outputStream.write(bArr);
            Pdlog.m3273d(TAG, "writeData success, length = " + bArr.length + "\tdata = " + Arrays.toString(bArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
