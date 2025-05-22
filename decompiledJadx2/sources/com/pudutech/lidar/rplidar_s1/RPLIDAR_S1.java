package com.pudutech.lidar.rplidar_s1;

import android.os.SystemClock;
import com.felhr.utils.HexData;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import com.pudutech.lidar.rplidar_s1.RPLIDAR_S1_PROTOCOL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes.dex */
public class RPLIDAR_S1 extends SerialLidar {
    static final byte[] SCAN_RESPONSE_BEGIN = {84, 0, 0, 64, -123};
    private static final String TAG = "Lidar";
    Thread parseDataThread;
    private long last_protocol_error_print_stamp_ = 0;
    private long startScanTimestamp = -1;
    private int crcErrorCount = 0;
    private ControlStep step = ControlStep.IDLE;
    private Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.rplidar_s1.RPLIDAR_S1.1
        @Override // java.lang.Runnable
        public void run() {
            RPLIDAR_S1.this.controlFSM();
        }
    };
    private boolean isReceiveResponse = false;
    boolean isRunning = false;
    Runnable runnable = new Runnable() { // from class: com.pudutech.lidar.rplidar_s1.RPLIDAR_S1.4
        private long timestamp = 0;

        @Override // java.lang.Runnable
        public void run() {
            while (RPLIDAR_S1.this.isRunning) {
                if (RPLIDAR_S1.this.isReceiveResponse) {
                    RPLIDAR_S1.this.parseData();
                } else {
                    RPLIDAR_S1 rplidar_s1 = RPLIDAR_S1.this;
                    rplidar_s1.isReceiveResponse = rplidar_s1.checkReceiveScanResponse();
                    if (RPLIDAR_S1.this.isReceiveResponse) {
                        Pdlog.m3276v("Lidar", "parse lidar protocol head success");
                    }
                    if (this.timestamp == 0) {
                        this.timestamp = SystemClock.elapsedRealtime();
                    }
                    if (SystemClock.elapsedRealtime() - this.timestamp > 1000) {
                        Pdlog.m3277w("Lidar", "no more wait scan response protocol head");
                        RPLIDAR_S1.this.isReceiveResponse = true;
                    }
                }
            }
        }
    };
    private final byte[] pre = new byte[84];
    private final byte[] now = new byte[84];
    RPLIDAR_S1_PROTOCOL RPLIDARS1PROTOCAL = new RPLIDAR_S1_PROTOCOL();
    ArrayBlockingQueue<Byte> receive = new ArrayBlockingQueue<>(1024);
    List<LidarNode> nodes = new ArrayList();
    private boolean isOneFrameComplete = false;
    private long sumErrCnt = 0;
    private boolean isStartFlag = true;
    private int last = -1;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes.dex */
    public enum ControlStep {
        START_DTR,
        START_MOTOR,
        SCAN,
        STOP,
        STOP_DTR,
        IDLE
    }

    @Override // com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 256000;
    }

    int getBitFromByte(byte b, int i) {
        return (b & (1 << i)) == 0 ? 0 : 1;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public long getPowerOnDelay_ms() {
        return 2000L;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public long getScanCMDValidInterval_ms() {
        return 100L;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] bArr) {
        if (this.startScanTimestamp != -1) {
            if (SystemClock.elapsedRealtime() - this.startScanTimestamp < 5000) {
                Pdlog.m3273d("Lidar", "receive data len " + bArr.length + HexData.hexToString(bArr));
            } else {
                this.startScanTimestamp = -1L;
            }
        }
        if (this.isRunning) {
            if (this.parseDataThread == null) {
                this.receive.clear();
                Thread thread = new Thread(this.runnable);
                this.parseDataThread = thread;
                thread.start();
            }
            for (byte b : bArr) {
                try {
                    this.receive.offer(Byte.valueOf(b), 10L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Pdlog.m3274e("Lidar", "parser error: " + e.toString());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void controlFSM() {
        Pdlog.m3275i("Lidar", "controlFSM step=" + this.step);
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            int i = C47315.$SwitchMap$com$pudutech$lidar$rplidar_s1$RPLIDAR_S1$ControlStep[this.step.ordinal()];
            if (i == 1) {
                lidarInterface.setDTR(false);
                this.step = ControlStep.START_MOTOR;
                this.controlHandler.postDelayed(this.controlRunnable, 100L);
                return;
            }
            if (i == 2) {
                lidarInterface.send(new byte[]{-91, -126, 5, 0, 0, 0, 0, 0, 34});
                this.step = ControlStep.SCAN;
                this.controlHandler.postDelayed(this.controlRunnable, 200L);
            } else if (i == 3) {
                lidarInterface.send(new byte[]{-91, -88, 2, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 2, -18});
                this.step = ControlStep.IDLE;
            } else if (i == 4) {
                lidarInterface.send(new byte[]{-91, 37});
                this.step = ControlStep.STOP_DTR;
                this.controlHandler.postDelayed(this.controlRunnable, 500L);
            } else {
                if (i != 5) {
                    return;
                }
                lidarInterface.setDTR(true);
                this.step = ControlStep.IDLE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: com.pudutech.lidar.rplidar_s1.RPLIDAR_S1$5 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C47315 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$rplidar_s1$RPLIDAR_S1$ControlStep;

        static {
            int[] iArr = new int[ControlStep.values().length];
            $SwitchMap$com$pudutech$lidar$rplidar_s1$RPLIDAR_S1$ControlStep = iArr;
            try {
                iArr[ControlStep.START_DTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidar_s1$RPLIDAR_S1$ControlStep[ControlStep.START_MOTOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidar_s1$RPLIDAR_S1$ControlStep[ControlStep.SCAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidar_s1$RPLIDAR_S1$ControlStep[ControlStep.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidar_s1$RPLIDAR_S1$ControlStep[ControlStep.STOP_DTR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3273d("Lidar", "startScan s1");
        this.isStartFlag = true;
        this.last = -1;
        this.isRunning = true;
        this.crcErrorCount = 0;
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidar_s1.RPLIDAR_S1.2
            @Override // java.lang.Runnable
            public void run() {
                if (RPLIDAR_S1.this.step == ControlStep.IDLE || RPLIDAR_S1.this.step == ControlStep.STOP || RPLIDAR_S1.this.step == ControlStep.STOP_DTR) {
                    RPLIDAR_S1.this.controlHandler.removeCallbacks(RPLIDAR_S1.this.controlRunnable);
                    RPLIDAR_S1.this.startScanTimestamp = SystemClock.elapsedRealtime();
                    RPLIDAR_S1.this.step = ControlStep.START_DTR;
                    RPLIDAR_S1.this.controlFSM();
                }
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3273d("Lidar", "stopScan s1");
        this.isStartFlag = true;
        this.last = -1;
        this.crcErrorCount = 0;
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidar_s1.RPLIDAR_S1.3
            @Override // java.lang.Runnable
            public void run() {
                if (RPLIDAR_S1.this.step == ControlStep.IDLE || !(RPLIDAR_S1.this.step == ControlStep.STOP || RPLIDAR_S1.this.step == ControlStep.STOP_DTR)) {
                    RPLIDAR_S1.this.controlHandler.removeCallbacks(RPLIDAR_S1.this.controlRunnable);
                    RPLIDAR_S1.this.step = ControlStep.STOP;
                    RPLIDAR_S1.this.controlFSM();
                }
            }
        });
    }

    private void printProtocolError(String str) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.last_protocol_error_print_stamp_ > 500) {
            this.last_protocol_error_print_stamp_ = elapsedRealtime;
            Pdlog.m3274e("Lidar", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkReceiveScanResponse() {
        if (readOneBytesFromReceive() != -91) {
            return false;
        }
        Pdlog.m3275i("Lidar", "response A5 OK");
        if (readOneBytesFromReceive() != 90) {
            return false;
        }
        Pdlog.m3275i("Lidar", "response 5A OK");
        return checkBytesSame(readBytesFromReceive(SCAN_RESPONSE_BEGIN.length), SCAN_RESPONSE_BEGIN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean parseData() {
        byte readOneBytesFromReceive = readOneBytesFromReceive();
        if (((readOneBytesFromReceive & 240) >> 4) != 10) {
            Pdlog.m3277w("Lidar", "protocol error, sync1 must be 0x0A, byte1=" + HexData.hexToString(new byte[]{readOneBytesFromReceive}));
            this.isOneFrameComplete = false;
            Arrays.fill(this.pre, (byte) 0);
            return false;
        }
        this.now[0] = readOneBytesFromReceive;
        byte readOneBytesFromReceive2 = readOneBytesFromReceive();
        if (((readOneBytesFromReceive2 & 240) >> 4) != 5) {
            Pdlog.m3277w("Lidar", "protocol error, sync2 must be 0x05, byte2=" + HexData.hexToString(new byte[]{readOneBytesFromReceive2}));
            this.isOneFrameComplete = false;
            Arrays.fill(this.pre, (byte) 0);
            return false;
        }
        this.now[1] = readOneBytesFromReceive2;
        int i = readOneBytesFromReceive & 15;
        byte[] readBytesFromReceive = readBytesFromReceive(82);
        if (!checkOnePackageSum((byte) (i | ((readOneBytesFromReceive2 & 15) << 4)), readBytesFromReceive)) {
            this.sumErrCnt++;
            this.isOneFrameComplete = false;
            Arrays.fill(this.pre, (byte) 0);
            return false;
        }
        System.arraycopy(readBytesFromReceive, 0, this.now, 2, 82);
        if (this.isOneFrameComplete) {
            int i2 = -1;
            Iterator<RPLIDAR_S1_PROTOCOL.Node> it = this.RPLIDARS1PROTOCAL.test(this.pre, this.now).iterator();
            while (it.hasNext()) {
                RPLIDAR_S1_PROTOCOL.Node next = it.next();
                LidarNode lidarNode = new LidarNode();
                lidarNode.dist = (int) (next.dist_mm_q2_u32 / 4);
                lidarNode.angle = (next.angle_q14_u16 * 90) / 16384;
                lidarNode.distance_m = (next.dist_mm_q2_u32 / 4.0d) / 1000.0d;
                lidarNode.angleInRad = Math.toRadians((next.angle_q14_u16 * 90) / 16384.0d) - 3.141592653589793d;
                if (lidarNode.angleInRad < 0.0d) {
                    lidarNode.angleInRad += 6.283185307179586d;
                }
                this.nodes.add(lidarNode);
                i2 = lidarNode.angle - 180;
                if (i2 > 360) {
                    i2 -= 360;
                }
                if (i2 < 0) {
                    i2 += 360;
                }
            }
            if (i2 > 0) {
                if (checkOneFrameComplete(i2)) {
                    LidarListener listener = getListener();
                    if (listener != null) {
                        if (this.nodes.isEmpty()) {
                            Pdlog.m3277w("Lidar", "empty frame");
                            listener.onProtocolError("empty frame");
                        } else {
                            listener.onOneFrameComplete(this.nodes);
                        }
                    } else {
                        Pdlog.m3277w("Lidar", "lidarCallback is null");
                    }
                    this.nodes = new ArrayList();
                } else {
                    Pdlog.m3273d("Lidar", "checkOneFrameComplete fail endAngle=" + i2 + ",isStartFlag=" + this.isStartFlag + ",last=" + this.last);
                }
            } else {
                Pdlog.m3277w("Lidar", "endAngle < 0");
            }
        }
        byte[] bArr = this.now;
        System.arraycopy(bArr, 0, this.pre, 0, bArr.length);
        this.isOneFrameComplete = true;
        return true;
    }

    private boolean checkOneFrameComplete(int i) {
        int i2 = this.last;
        this.last = i;
        if (i < i2) {
            return false;
        }
        if (i > 315 || i < 135) {
            if (this.isStartFlag) {
                this.isStartFlag = false;
                return true;
            }
        } else {
            this.isStartFlag = true;
        }
        return false;
    }

    private boolean checkOnePackageSum(byte b, byte[] bArr) {
        byte b2 = 0;
        for (byte b3 : bArr) {
            b2 = (byte) (b2 ^ b3);
        }
        if (b2 == b) {
            this.crcErrorCount = 0;
            return true;
        }
        this.crcErrorCount++;
        Pdlog.m3277w("Lidar", "protocol error, checkSum[" + ((int) b2) + "] != receiveCheckSum[" + ((int) b) + "] errCnt is " + this.sumErrCnt + "," + HexData.hexToString(bArr));
        if (this.crcErrorCount > 10) {
            LidarListener listener = getListener();
            if (listener != null) {
                listener.onProtocolError("protocol error, checkSum has occur " + this.crcErrorCount);
            }
            this.crcErrorCount = 0;
        }
        return false;
    }

    boolean checkBytesSame(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            Pdlog.m3274e("Lidar", "not same. receives= " + HexData.hexToString(bArr) + " wants=" + HexData.hexToString(bArr2));
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                Pdlog.m3274e("Lidar", "not same. receives= " + HexData.hexToString(bArr) + " wants=" + HexData.hexToString(bArr2));
                return false;
            }
        }
        return true;
    }

    private byte readOneBytesFromReceive() {
        try {
            return this.receive.take().byteValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Pdlog.m3274e("Lidar", "receive one fail: " + e.toString());
            onReadFail("readOneBytesFromReceive fail: " + e.getMessage());
            return (byte) 0;
        }
    }

    private byte[] readBytesFromReceive(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            try {
                bArr[i2] = this.receive.take().byteValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Pdlog.m3274e("Lidar", "receive " + i + " fail: " + e.toString());
                onReadFail("readBytesFromReceive " + i + " fail: " + e.getMessage());
            }
        }
        return bArr;
    }

    private void onReadFail(String str) {
        LidarListener listener = getListener();
        if (listener != null) {
            listener.onProtocolError(str);
        }
    }
}
