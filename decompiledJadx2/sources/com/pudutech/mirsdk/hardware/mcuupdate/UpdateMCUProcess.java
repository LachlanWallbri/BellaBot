package com.pudutech.mirsdk.hardware.mcuupdate;

import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.mcuupdate.IAPProtocol;
import com.pudutech.mirsdk.hardware.serialize.HardwareBoard;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* loaded from: classes2.dex */
public class UpdateMCUProcess {
    public static final int INT_LENGTH = 4;
    private static final int MOST_TRY_TIMES = 8;
    private static final int READ_WAIT_CHK_VERSION_TIME_MS = 100;
    private static final String TAG = "UpdateMCU";
    private static final int WAIT_CHK_VERSION_TIME_MS = 1000;
    private static final int WAIT_TIME_MS = 500;
    private MCU Bellabot;
    private MCU Chassis;
    private MCU External;
    private MCU Hola;
    private ArrayList<MCU> all;
    private Callback callback;
    private int fileSize;
    private String lastError;
    private ArrayBlockingQueue receiveQueue;
    byte targetID;
    private InputStream updateFile;
    int blockIndex = 0;
    private int BLOCK_SIZE = 1024;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes2.dex */
    public interface Callback {
        void log(int i, String str, String str2);

        void sendToMCU(byte[] bArr);
    }

    public void setReceive(byte[] bArr) {
        ArrayBlockingQueue arrayBlockingQueue;
        if (bArr[0] == 22 && (arrayBlockingQueue = this.receiveQueue) != null) {
            arrayBlockingQueue.offer(bArr);
        }
    }

    public String getLastError() {
        return this.lastError;
    }

    private void send(byte[] bArr) {
        logv(TAG, "send " + ByteUtils.INSTANCE.hexToString(bArr));
        Callback callback = this.callback;
        if (callback != null) {
            callback.sendToMCU(bArr);
        }
    }

    private void log(int i, String str, String str2) {
        Callback callback = this.callback;
        if (callback != null) {
            callback.log(i, str, str2);
        }
    }

    private void logv(String str, String str2) {
        Pdlog.m3276v(str, str2);
    }

    private void logd(String str, String str2) {
        Pdlog.m3273d(str, str2);
    }

    private void logw(String str, String str2) {
        Pdlog.m3277w(str, str2);
    }

    public UpdateMCUProcess(String str, InputStream inputStream, ProductMachineType productMachineType, Callback callback) {
        this.fileSize = 0;
        try {
            this.fileSize = inputStream.available();
        } catch (IOException e) {
            Pdlog.m3274e(TAG, "exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
        }
        if (this.fileSize == 0) {
            return;
        }
        this.Chassis = new MCU((byte) HardwareBoard.Main.getId(), "Chassis");
        ArrayList<MCU> arrayList = new ArrayList<>();
        this.all = arrayList;
        arrayList.add(this.Chassis);
        switch (C51941.$SwitchMap$com$pudutech$mirsdk$hardware$serialize$MachineModel[productMachineType.getModel().ordinal()]) {
            case 1:
            case 2:
            case 3:
                this.all.add(new MCU((byte) HardwareBoard.Extern3399.getId(), "RK3399etb"));
                break;
            case 4:
            case 5:
                this.all.add(new MCU((byte) HardwareBoard.Tail.getId(), "Fboard"));
                break;
            case 6:
                this.all.add(new MCU((byte) HardwareBoard.Tail.getId(), "Fboard"));
                this.all.add(new MCU((byte) HardwareBoard.AtomizationUV.getId(), "Fboard0B"));
                this.all.add(new MCU((byte) HardwareBoard.RotationUV.getId(), "Fboard0C"));
                break;
            case 7:
                this.all.add(new MCU((byte) HardwareBoard.PowerManagement.getId(), "Fboard20"));
                this.all.add(new MCU((byte) HardwareBoard.PeaunatHead.getId(), "Fboard21"));
                Pdlog.m3273d(TAG, "peanut update");
                break;
        }
        Iterator<MCU> it = this.all.iterator();
        while (it.hasNext()) {
            MCU next = it.next();
            if (str.indexOf(next.getName()) != -1) {
                this.targetID = next.getDeviceID();
                this.updateFile = inputStream;
            }
        }
        this.callback = callback;
        this.receiveQueue = new ArrayBlockingQueue(100);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* renamed from: com.pudutech.mirsdk.hardware.mcuupdate.UpdateMCUProcess$1 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C51941 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$mirsdk$hardware$serialize$MachineModel;

        static {
            int[] iArr = new int[MachineModel.values().length];
            $SwitchMap$com$pudutech$mirsdk$hardware$serialize$MachineModel = iArr;
            try {
                iArr[MachineModel.Hls.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$mirsdk$hardware$serialize$MachineModel[MachineModel.Puductor.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pudutech$mirsdk$hardware$serialize$MachineModel[MachineModel.Phoenix.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$mirsdk$hardware$serialize$MachineModel[MachineModel.BellaBot.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pudutech$mirsdk$hardware$serialize$MachineModel[MachineModel.RecycleDog.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$pudutech$mirsdk$hardware$serialize$MachineModel[MachineModel.Ninetales.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$pudutech$mirsdk$hardware$serialize$MachineModel[MachineModel.Peanut.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private boolean setDeviceCAN(byte b, boolean z) throws InterruptedException {
        logd(TAG, "set device targetID=" + ((int) b) + " CAN isOpen=" + z);
        int i = 30;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            this.receiveQueue.clear();
            send(IAPProtocol.CANSwitch(b, z));
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (true) {
                if (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                    byte[] bArr = (byte[]) this.receiveQueue.poll(100L, TimeUnit.MILLISECONDS);
                    if (bArr != null) {
                        IAPProtocol.CHECK_RESULT checkCANSwitch = IAPProtocol.checkCANSwitch(bArr, b, z);
                        logd(TAG, "result=" + checkCANSwitch + " receive=" + ByteUtils.INSTANCE.hexToString(bArr));
                        if (checkCANSwitch != IAPProtocol.CHECK_RESULT.EXPECTED) {
                            if (checkCANSwitch != IAPProtocol.CHECK_RESULT.WRONG_PROTOCOL && checkCANSwitch != IAPProtocol.CHECK_RESULT.UNEXPECTED) {
                                logw(TAG, "tryTimes=" + i + " fail when receive=" + ByteUtils.INSTANCE.hexToString(bArr));
                                break;
                            }
                        } else {
                            return true;
                        }
                    }
                } else {
                    break;
                }
            }
            logw(TAG, "tryTimes=" + i + " spend time=" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private boolean setAllClose() throws InterruptedException {
        logd(TAG, "set all close");
        Iterator<MCU> it = this.all.iterator();
        while (it.hasNext()) {
            if (!setDeviceCAN(it.next().getDeviceID(), false)) {
                return false;
            }
        }
        return true;
    }

    private boolean setAllOpen() throws InterruptedException {
        logd(TAG, "set all open");
        Iterator<MCU> it = this.all.iterator();
        while (it.hasNext()) {
            if (!setDeviceCAN(it.next().getDeviceID(), true)) {
                return false;
            }
        }
        return true;
    }

    private boolean resetToIAP() throws InterruptedException {
        logd(TAG, "resetToIAP");
        int i = 8;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            this.receiveQueue.clear();
            send(IAPProtocol.ResetToIAP(this.targetID));
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                byte[] bArr = (byte[]) this.receiveQueue.poll(100L, TimeUnit.MILLISECONDS);
                if (bArr != null) {
                    IAPProtocol.CHECK_RESULT checkResetIAP = IAPProtocol.checkResetIAP(bArr, this.targetID);
                    logd(TAG, "result=" + checkResetIAP + " receive=" + ByteUtils.INSTANCE.hexToString(bArr));
                    if (checkResetIAP == IAPProtocol.CHECK_RESULT.EXPECTED) {
                        return true;
                    }
                }
            }
            logw(TAG, "tryTimes=" + (8 - i) + " spend time=" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private boolean checkModeWanted(boolean z) throws InterruptedException {
        int i = 8;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            logd(TAG, "checkModeWanted");
            this.receiveQueue.clear();
            send(IAPProtocol.ModeRequest(this.targetID));
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (true) {
                if (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                    byte[] bArr = (byte[]) this.receiveQueue.poll(100L, TimeUnit.MILLISECONDS);
                    if (bArr != null) {
                        IAPProtocol.CHECK_RESULT checkModeRequest = IAPProtocol.checkModeRequest(bArr, this.targetID, z);
                        logd(TAG, "result=" + checkModeRequest + " receive=" + ByteUtils.INSTANCE.hexToString(bArr));
                        if (checkModeRequest != IAPProtocol.CHECK_RESULT.EXPECTED) {
                            if (checkModeRequest != IAPProtocol.CHECK_RESULT.WRONG_PROTOCOL && checkModeRequest != IAPProtocol.CHECK_RESULT.UNEXPECTED) {
                                logw(TAG, "tryTimes=" + i + " fail when receive=" + ByteUtils.INSTANCE.hexToString(bArr));
                                break;
                            }
                        } else {
                            return true;
                        }
                    }
                } else {
                    break;
                }
            }
            logw(TAG, "tryTimes=" + (8 - i) + " spend time=" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private Pair<Boolean, Boolean> checkVersionLatest(byte[] bArr) throws InterruptedException {
        long elapsedRealtime;
        logd(TAG, "checkVersionLatest " + ((int) bArr[0]) + "." + ((int) bArr[1]) + "." + ((int) bArr[2]));
        int i = 8;
        while (true) {
            i--;
            if (i > 0) {
                this.receiveQueue.clear();
                send(IAPProtocol.ReadVersionRequest(this.targetID));
                elapsedRealtime = SystemClock.elapsedRealtime();
                while (true) {
                    if (SystemClock.elapsedRealtime() - elapsedRealtime >= 1000) {
                        break;
                    }
                    byte[] bArr2 = (byte[]) this.receiveQueue.poll(100L, TimeUnit.MILLISECONDS);
                    if (bArr2 != null) {
                        IAPProtocol.CHECK_RESULT checkVersionLatest = IAPProtocol.checkVersionLatest(bArr2, this.targetID, bArr);
                        logd(TAG, "result=" + checkVersionLatest + " receive=" + ByteUtils.INSTANCE.hexToString(bArr2));
                        if (checkVersionLatest == IAPProtocol.CHECK_RESULT.EXPECTED) {
                            return Pair.create(true, true);
                        }
                        if (checkVersionLatest != IAPProtocol.CHECK_RESULT.WRONG_PROTOCOL) {
                            if (checkVersionLatest == IAPProtocol.CHECK_RESULT.UNEXPECTED) {
                                return Pair.create(true, false);
                            }
                            logw(TAG, " fail when receive=" + ByteUtils.INSTANCE.hexToString(bArr2));
                        }
                    }
                }
            } else {
                return Pair.create(false, false);
            }
            logw(TAG, "tryTimes=" + (8 - i) + " spend time=" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private boolean sendFileInfoAndReadyToUpdate() throws InterruptedException {
        logd(TAG, "sendFileInfoAndReadyToUpdate");
        int i = 8;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            this.receiveQueue.clear();
            send(IAPProtocol.FileInfo(this.targetID, this.fileSize));
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (true) {
                if (SystemClock.elapsedRealtime() - elapsedRealtime < 10000) {
                    byte[] bArr = (byte[]) this.receiveQueue.poll(100L, TimeUnit.MILLISECONDS);
                    if (bArr != null) {
                        IAPProtocol.CHECK_RESULT checkFileInfo = IAPProtocol.checkFileInfo(bArr, this.targetID, this.fileSize);
                        logd(TAG, "result=" + checkFileInfo + " receive=" + ByteUtils.INSTANCE.hexToString(bArr));
                        if (checkFileInfo != IAPProtocol.CHECK_RESULT.EXPECTED) {
                            if (checkFileInfo != IAPProtocol.CHECK_RESULT.WRONG_PROTOCOL) {
                                logw(TAG, " fail when receive=" + ByteUtils.INSTANCE.hexToString(bArr));
                                break;
                            }
                        } else {
                            return true;
                        }
                    }
                } else {
                    break;
                }
            }
            logw(TAG, "tryTimes=" + (8 - i) + " spend time=" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private boolean sendBlockInfo(int i, int i2) throws InterruptedException {
        logd(TAG, "sendBlockInfo index=" + i2 + " size=" + i);
        int i3 = 8;
        while (true) {
            i3--;
            if (i3 <= 0) {
                return false;
            }
            this.receiveQueue.clear();
            send(IAPProtocol.BlockInfo(this.targetID, i, i2));
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (true) {
                if (SystemClock.elapsedRealtime() - elapsedRealtime < 1000) {
                    byte[] bArr = (byte[]) this.receiveQueue.poll(100L, TimeUnit.MILLISECONDS);
                    if (bArr != null) {
                        IAPProtocol.CHECK_RESULT checkBlockInfo = IAPProtocol.checkBlockInfo(bArr, this.targetID, i, i2);
                        logd(TAG, "result=" + checkBlockInfo + " receive=" + ByteUtils.INSTANCE.hexToString(bArr));
                        if (checkBlockInfo != IAPProtocol.CHECK_RESULT.EXPECTED) {
                            if (checkBlockInfo != IAPProtocol.CHECK_RESULT.WRONG_PROTOCOL) {
                                logw(TAG, "tryTimes=" + i3 + " fail when receive=" + ByteUtils.INSTANCE.hexToString(bArr));
                                break;
                            }
                        } else {
                            return true;
                        }
                    }
                } else {
                    break;
                }
            }
            logw(TAG, "tryTimes=" + (8 - i3) + " spend time=" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private boolean sendBlock(byte[] bArr, int i, int i2) throws InterruptedException {
        byte[] bArr2;
        logd(TAG, "sendBlock ");
        int i3 = 8;
        do {
            i3--;
            if (i3 <= 0 || !sendBlockInfo(i, i2)) {
                return false;
            }
            sendBytes(bArr, i);
            bArr2 = new byte[i + 6];
            bArr2[0] = (byte) ((i2 >> 8) & 255);
            bArr2[1] = (byte) (i2 & 255);
            bArr2[2] = 22;
            bArr2[3] = this.targetID;
            bArr2[4] = 8;
            bArr2[5] = 0;
            System.arraycopy(bArr, 0, bArr2, 6, i);
        } while (!sendBlockCRC32(ByteBuffer.allocate(4).putInt(IAPProtocol.calculateCRC32(bArr2)).array()));
        return true;
    }

    private void sendBytes(byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            byte[] bArr2 = {0, 0, 0, 0, 0};
            for (int i3 = 0; i3 < 5 && i2 < i; i3++) {
                bArr2[i3] = bArr[i2];
                i2++;
            }
            send(IAPProtocol.BytesPackage(this.targetID, bArr2));
        }
    }

    private boolean sendBlockCRC32(byte[] bArr) throws InterruptedException {
        logd(TAG, "sendBlockCRC32 crc32=" + ByteUtils.INSTANCE.hexToString(bArr));
        int i = 8;
        while (true) {
            i--;
            if (i <= 0) {
                return false;
            }
            this.receiveQueue.clear();
            send(IAPProtocol.blockCRC32(this.targetID, bArr));
            long elapsedRealtime = SystemClock.elapsedRealtime();
            while (true) {
                if (SystemClock.elapsedRealtime() - elapsedRealtime < 10000) {
                    byte[] bArr2 = (byte[]) this.receiveQueue.poll(100L, TimeUnit.MILLISECONDS);
                    if (bArr2 != null) {
                        IAPProtocol.CHECK_RESULT checkBlockCRC32 = IAPProtocol.checkBlockCRC32(bArr2, this.targetID);
                        logd(TAG, "result=" + checkBlockCRC32 + " receive=" + ByteUtils.INSTANCE.hexToString(bArr2));
                        if (checkBlockCRC32 != IAPProtocol.CHECK_RESULT.EXPECTED) {
                            if (checkBlockCRC32 != IAPProtocol.CHECK_RESULT.WRONG_PROTOCOL) {
                                logw(TAG, " fail when receive=" + ByteUtils.INSTANCE.hexToString(bArr2));
                                break;
                            }
                        } else {
                            return true;
                        }
                    }
                } else {
                    break;
                }
            }
            logw(TAG, "tryTimes=" + (8 - i) + " spend time=" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        }
    }

    private void report(String str) {
        logd(TAG, "report " + str);
    }

    public boolean setIAPMode() throws InterruptedException {
        logd(TAG, "reset to IAP");
        if (!setAllClose() && !setDeviceCAN(this.targetID, false)) {
            this.lastError = "close can device fail,can_id:0x" + Integer.toHexString(this.targetID);
            return false;
        }
        if (!resetToIAP()) {
            this.lastError = "reset to IAP fail,can_id:0x" + Integer.toHexString(this.targetID);
            return false;
        }
        Thread.sleep(500L);
        if (checkModeWanted(true)) {
            return true;
        }
        this.lastError = "reset to IAP mode success,but it fails to check whether it is in IAP mode,can_id:0x" + Integer.toHexString(this.targetID);
        return false;
    }

    public boolean checkLatestVersionInIAP(byte[] bArr) throws InterruptedException {
        logd(TAG, "checkLatestVersionInIAP");
        Pair<Boolean, Boolean> checkVersionLatest = checkVersionLatest(bArr);
        if (!((Boolean) checkVersionLatest.first).booleanValue()) {
            logw(TAG, "no response");
            return false;
        }
        if (!((Boolean) checkVersionLatest.second).booleanValue()) {
            return false;
        }
        logw(TAG, "no need to update");
        return true;
    }

    public boolean updating(byte[] bArr) throws InterruptedException, IOException {
        if (!sendFileInfoAndReadyToUpdate()) {
            this.lastError = "send upgrade related information fail,can_id:0x" + Integer.toHexString(this.targetID);
            return false;
        }
        byte[] bArr2 = new byte[this.BLOCK_SIZE];
        while (true) {
            int read = this.updateFile.read(bArr2);
            if (read != -1) {
                if (sendBlock(bArr2, read, this.blockIndex)) {
                    this.blockIndex++;
                } else {
                    this.lastError = "send block info fail,can_id:0x" + Integer.toHexString(this.targetID);
                    return false;
                }
            } else {
                if (!resetToAPP()) {
                    this.lastError = "reset to app mode fail,can_id:0x" + Integer.toHexString(this.targetID);
                    return false;
                }
                Pair<Boolean, Boolean> checkVersionLatest = checkVersionLatest(bArr);
                if (!((Boolean) checkVersionLatest.first).booleanValue()) {
                    this.lastError = "firmware upgrade success,but check the version protocol did not return,can_id:0x" + Integer.toHexString(this.targetID);
                    return false;
                }
                if (((Boolean) checkVersionLatest.second).booleanValue()) {
                    return true;
                }
                this.lastError = "firmware upgrade success,but the version number is wrong ,can_id:0x" + Integer.toHexString(this.targetID);
                return false;
            }
        }
    }

    public boolean resetToAPP() throws InterruptedException {
        int i = 8;
        do {
            i--;
            if (i <= 0) {
                return false;
            }
            logd(TAG, "resetToAPP");
            send(IAPProtocol.ResetToAPP(this.targetID));
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                Pdlog.m3274e(TAG, "exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
            }
            if (!checkModeWanted(false)) {
                return false;
            }
            if (setAllOpen()) {
                return true;
            }
        } while (!setDeviceCAN(this.targetID, true));
        return true;
    }
}
