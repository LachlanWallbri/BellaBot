package com.pudutech.serialport.library;

import com.pudutech.base.Pdlog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class ReadDataThread extends Thread {
    private static final String TAG = ReadDataThread.class.getSimpleName();
    private SerialPortHelper mSerialPortHelper;

    public ReadDataThread(SerialPortHelper serialPortHelper) {
        this.mSerialPortHelper = serialPortHelper;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (!isInterrupted()) {
            try {
                if (this.mSerialPortHelper.isOpened()) {
                    InputStream inputStream = this.mSerialPortHelper.getInputStream();
                    if (inputStream == null) {
                        return;
                    }
                    byte[] bArr = new byte[512];
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        byte[] bArr2 = new byte[read];
                        System.arraycopy(bArr, 0, bArr2, 0, read);
                        Pdlog.m3273d(TAG, "readData success, length = " + read + "\tdata = " + Arrays.toString(bArr2));
                        onDataReceive(bArr2, read);
                    }
                } else {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void onDataReceive(byte[] bArr, int i) {
        if (this.mSerialPortHelper.getSerialPortDataReceiveCallback() != null) {
            this.mSerialPortHelper.getSerialPortDataReceiveCallback().onReceive(bArr, i);
        }
    }
}
