package com.pudutech.mirsdk.hardware.machineinfo;

import com.pudutech.base.Pdlog;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes4.dex */
public class HardwareConfigureHandler {
    public static final byte MACHINE_INFO_RECODE_DEVICE_ID = 2;
    private static final String TAG = "HardwareConfigureHandler";
    Callback call;
    private ArrayBlockingQueue receiveQueue = new ArrayBlockingQueue(500);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes4.dex */
    public interface Callback {
        void send(byte[] bArr);
    }

    private void sendToMCU(byte[] bArr) {
        this.call.send(bArr);
    }

    public HardwareConfigureHandler(Callback callback) {
        this.call = callback;
    }

    public boolean flashToMCU() {
        int i = 0;
        Pdlog.m3273d(TAG, "send info to mcu ");
        while (true) {
            if (i != 25 && i != 26) {
                byte[] bArr = HardwareConfigure.getInstance().get4Bytes(i);
                if (bArr == null || bArr.length == 0) {
                    break;
                }
                sendToMCU(combineWriteCmd((byte) i, bArr));
            }
            i++;
        }
        byte[] bArr2 = {80, 122, -118, -123, 117, 90, 2, 0};
        bArr2[7] = addSum(bArr2);
        sendToMCU(bArr2);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        byte[] bArr3 = {0, 84, 0, 0, 0, 0, 2, 0};
        bArr3[7] = addSum(bArr3);
        sendToMCU(bArr3);
        return true;
    }

    byte[] combineWriteCmd(byte b, byte[] bArr) {
        Pdlog.m3273d(TAG, "combineWriteCmd id", Byte.valueOf(b), "data0", Byte.valueOf(bArr[0]), "data1", Byte.valueOf(bArr[1]), "data2", Byte.valueOf(bArr[2]), "data3", Byte.valueOf(bArr[3]));
        byte[] bArr2 = {81, b, bArr[0], bArr[1], bArr[2], bArr[3], 2, addSum(bArr2)};
        return bArr2;
    }

    private byte addSum(byte[] bArr) {
        byte b = 0;
        for (int i = 0; i < 7; i++) {
            b = (byte) (b ^ bArr[i]);
        }
        return b;
    }
}
