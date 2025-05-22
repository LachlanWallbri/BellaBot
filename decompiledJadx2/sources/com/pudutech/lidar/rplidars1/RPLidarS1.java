package com.pudutech.lidar.rplidars1;

import android.os.SystemClock;
import com.felhr.utils.HexData;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.ListNodePool;
import com.pudutech.lidar.base.LidarDeviceInfo;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import com.pudutech.lidar.rplidars1.RPLidarS1Protocol;
import com.pudutech.lidar.util.BufferUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* loaded from: classes5.dex */
public class RPLidarS1 extends SerialLidar {
    private static final int RECEIVE_SCAN_RESPONSE_LONG = 5;
    static final byte[] SCAN_RESPONSE_BEGIN = {84, 0, 0, 64, -123};
    private static final String TAG = "RPLidarS1";
    private long startScanTimestamp = -1;
    private int crcErrorCount = 0;
    private int continuouslyDataHeadErrorCount = 0;

    /* renamed from: sn */
    private String f5470sn = "";
    private volatile ParserStep parserStep = ParserStep.START_SCAN_HEAD_A5;
    private byte[] devicesByte = {-91, 90, 20, 0, 0, 0, 4};
    private ControlStep step = ControlStep.IDLE;
    private final Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.rplidars1.RPLidarS1.1
        @Override // java.lang.Runnable
        public void run() {
            RPLidarS1.this.controlFSM();
        }
    };
    private volatile boolean isReceiveResponse = false;
    boolean isRunning = false;
    private long timestamp = 0;
    private int receiveScanResponseCount = 0;
    private byte[] receiveScanResponse = new byte[5];
    private final byte[] pre = new byte[84];
    private final byte[] now = new byte[84];
    RPLidarS1Protocol rpLidarS1Protocol = new RPLidarS1Protocol();
    ArrayList<LidarNode> nodes = ListLidarNodePool.obtain();
    private boolean isOneFrameComplete = false;
    private long sumErrCnt = 0;
    private int lidarSrcDataCount = 0;
    private final byte[] lidarSrcData = new byte[82];
    private final byte[] lidarScanResponseData = new byte[5];
    private boolean isStartFlag = true;
    private int last = -1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum ControlStep {
        START_DTR,
        START_MOTOR,
        SCAN,
        STOP,
        STOP_DTR,
        IDLE
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum ParseDeviceStep {
        UNKNOWN,
        HEAD_A5,
        HEAD_5A,
        HEAD_14,
        HEAD_001,
        HEAD_002,
        HEAD_003,
        HEAD_04
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum ParserStep {
        START_SCAN_HEAD_A5,
        START_SCAN_HEAD_5A,
        START_SCAN_PYLOAD,
        PARSER_HEAD_0A,
        PARSER_HEAD_05,
        PARSER_PYLOAD
    }

    @Override // com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 256000;
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
        parseDevice(bArr);
        if (this.startScanTimestamp != -1) {
            if (SystemClock.elapsedRealtime() - this.startScanTimestamp < 5000) {
                Pdlog.m3273d(TAG, "receive data len " + bArr.length + " " + HexData.hexToString(bArr));
            } else {
                this.startScanTimestamp = -1L;
            }
        }
        if (this.isRunning) {
            if (this.isReceiveResponse) {
                parseData(bArr);
                return;
            }
            this.isReceiveResponse = checkReceiveScanResponse(bArr);
            if (this.isReceiveResponse) {
                Pdlog.m3276v(TAG, "parse lidar protocol head success");
            }
            if (this.timestamp == 0) {
                this.timestamp = SystemClock.elapsedRealtime();
            }
            if (SystemClock.elapsedRealtime() - this.timestamp > 2000) {
                Pdlog.m3277w(TAG, "no more wait scan response protocol head");
                this.parserStep = ParserStep.PARSER_HEAD_0A;
                this.isReceiveResponse = true;
            }
        }
    }

    private void parseDevice(byte[] bArr) {
        if (this.f5470sn.isEmpty() && bArr.length > 26 && BufferUtil.INSTANCE.containsByteArray(this.devicesByte, bArr)) {
            Pdlog.m3273d(TAG, "parseDevice src:" + HexData.hexToString(bArr));
            byte[] bArr2 = new byte[20];
            byte[] bArr3 = new byte[16];
            ParseDeviceStep parseDeviceStep = ParseDeviceStep.UNKNOWN;
            int i = 0;
            while (true) {
                if (i < bArr.length) {
                    if (bArr[i] == -91) {
                        parseDeviceStep = ParseDeviceStep.HEAD_A5;
                    }
                    switch (parseDeviceStep) {
                        case HEAD_A5:
                            if (bArr[i] != -91) {
                                break;
                            } else {
                                parseDeviceStep = ParseDeviceStep.HEAD_5A;
                                break;
                            }
                        case HEAD_5A:
                            if (bArr[i] == 90) {
                                parseDeviceStep = ParseDeviceStep.HEAD_14;
                                break;
                            } else {
                                parseDeviceStep = ParseDeviceStep.HEAD_A5;
                                break;
                            }
                        case HEAD_14:
                            if (bArr[i] == 20) {
                                parseDeviceStep = ParseDeviceStep.HEAD_001;
                                break;
                            } else {
                                parseDeviceStep = ParseDeviceStep.HEAD_A5;
                                break;
                            }
                        case HEAD_001:
                            if (bArr[i] == 0) {
                                parseDeviceStep = ParseDeviceStep.HEAD_002;
                                break;
                            } else {
                                parseDeviceStep = ParseDeviceStep.HEAD_A5;
                                break;
                            }
                        case HEAD_002:
                            if (bArr[i] == 0) {
                                parseDeviceStep = ParseDeviceStep.HEAD_003;
                                break;
                            } else {
                                parseDeviceStep = ParseDeviceStep.HEAD_A5;
                                break;
                            }
                        case HEAD_003:
                            if (bArr[i] == 0) {
                                parseDeviceStep = ParseDeviceStep.HEAD_04;
                                break;
                            } else {
                                parseDeviceStep = ParseDeviceStep.HEAD_A5;
                                break;
                            }
                        case HEAD_04:
                            if (bArr[i] != 4) {
                                break;
                            } else {
                                break;
                            }
                    }
                    i++;
                } else {
                    i = 0;
                }
            }
            if (i > 0) {
                System.arraycopy(bArr, i + 1, bArr2, 0, 20);
                System.arraycopy(bArr2, 4, bArr3, 0, 16);
                this.f5470sn = BufferUtil.INSTANCE.toHexString(bArr3).replace(" ", "");
                Pdlog.m3273d(TAG, "parseDevice serialNumber=" + this.f5470sn);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void controlFSM() {
        Pdlog.m3275i(TAG, "controlFSM step=" + this.step);
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            int i = C47454.$SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ControlStep[this.step.ordinal()];
            if (i == 1) {
                Pdlog.m3273d(TAG, "controlFSM START_DTR");
                this.timestamp = 0L;
                this.isReceiveResponse = false;
                this.parserStep = ParserStep.START_SCAN_HEAD_A5;
                lidarInterface.setDTR(false);
                this.step = ControlStep.START_MOTOR;
                this.controlHandler.postDelayed(this.controlRunnable, 100L);
                return;
            }
            if (i == 2) {
                Pdlog.m3273d(TAG, "controlFSM START_MOTOR");
                lidarInterface.send(new byte[]{-91, -126, 5, 0, 0, 0, 0, 0, 34});
                this.step = ControlStep.SCAN;
                this.controlHandler.postDelayed(this.controlRunnable, 200L);
                return;
            }
            if (i == 3) {
                Pdlog.m3273d(TAG, "controlFSM SCAN");
                lidarInterface.send(new byte[]{-91, -88, 2, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 2, -18});
                lidarInterface.onStepScan();
                this.step = ControlStep.IDLE;
                return;
            }
            if (i == 4) {
                Pdlog.m3273d(TAG, "controlFSM STOP");
                lidarInterface.send(new byte[]{-91, 37});
                this.step = ControlStep.STOP_DTR;
                this.controlHandler.postDelayed(this.controlRunnable, 500L);
                return;
            }
            if (i == 5) {
                lidarInterface.setDTR(true);
                this.step = ControlStep.IDLE;
            } else {
                Pdlog.m3273d(TAG, "s1Lidar controlFSM default do nothing");
            }
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3273d(TAG, "startScan s1");
        this.isStartFlag = true;
        this.last = -1;
        this.isRunning = true;
        this.crcErrorCount = 0;
        reset();
        getLidarInterface().send(new byte[]{-91, 80});
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidars1.RPLidarS1.2
            @Override // java.lang.Runnable
            public void run() {
                if (RPLidarS1.this.step == ControlStep.IDLE || RPLidarS1.this.step == ControlStep.STOP || RPLidarS1.this.step == ControlStep.STOP_DTR) {
                    RPLidarS1.this.controlHandler.removeCallbacks(RPLidarS1.this.controlRunnable);
                    RPLidarS1.this.startScanTimestamp = SystemClock.elapsedRealtime();
                    RPLidarS1.this.step = ControlStep.START_DTR;
                    RPLidarS1.this.controlFSM();
                }
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3273d(TAG, "stopScan s1");
        this.isStartFlag = true;
        this.last = -1;
        this.crcErrorCount = 0;
        this.isRunning = false;
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidars1.RPLidarS1.3
            @Override // java.lang.Runnable
            public void run() {
                if (RPLidarS1.this.step == ControlStep.IDLE || !(RPLidarS1.this.step == ControlStep.STOP || RPLidarS1.this.step == ControlStep.STOP_DTR)) {
                    RPLidarS1.this.controlHandler.removeCallbacks(RPLidarS1.this.controlRunnable);
                    RPLidarS1.this.step = ControlStep.STOP;
                    RPLidarS1.this.controlFSM();
                }
            }
        });
    }

    private void reset() {
        this.receiveScanResponseCount = 0;
        this.lidarSrcDataCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pudutech.lidar.rplidars1.RPLidarS1$4 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C47454 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ControlStep;
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ParserStep = new int[ParserStep.values().length];

        static {
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ParserStep[ParserStep.START_SCAN_HEAD_A5.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ParserStep[ParserStep.START_SCAN_HEAD_5A.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ParserStep[ParserStep.START_SCAN_PYLOAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ParserStep[ParserStep.PARSER_HEAD_0A.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ParserStep[ParserStep.PARSER_HEAD_05.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ParserStep[ParserStep.PARSER_PYLOAD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ControlStep = new int[ControlStep.values().length];
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ControlStep[ControlStep.START_DTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ControlStep[ControlStep.START_MOTOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ControlStep[ControlStep.SCAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ControlStep[ControlStep.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ControlStep[ControlStep.STOP_DTR.ordinal()] = 5;
            } catch (NoSuchFieldError unused11) {
            }
            f5471xaf99e09c = new int[ParseDeviceStep.values().length];
            try {
                f5471xaf99e09c[ParseDeviceStep.HEAD_A5.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f5471xaf99e09c[ParseDeviceStep.HEAD_5A.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f5471xaf99e09c[ParseDeviceStep.HEAD_14.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f5471xaf99e09c[ParseDeviceStep.HEAD_001.ordinal()] = 4;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f5471xaf99e09c[ParseDeviceStep.HEAD_002.ordinal()] = 5;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f5471xaf99e09c[ParseDeviceStep.HEAD_003.ordinal()] = 6;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f5471xaf99e09c[ParseDeviceStep.HEAD_04.ordinal()] = 7;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }

    private boolean checkReceiveScanResponse(byte[] bArr) {
        boolean z = false;
        for (byte b : bArr) {
            int i = C47454.$SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ParserStep[this.parserStep.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        byte[] bArr2 = this.receiveScanResponse;
                        int i2 = this.receiveScanResponseCount;
                        bArr2[i2] = b;
                        this.receiveScanResponseCount = i2 + 1;
                        if (this.receiveScanResponseCount == 5) {
                            z = checkBytesSame(bArr2, SCAN_RESPONSE_BEGIN);
                            if (z) {
                                this.parserStep = ParserStep.PARSER_HEAD_0A;
                            } else {
                                this.parserStep = ParserStep.START_SCAN_HEAD_A5;
                            }
                            reset();
                        }
                    }
                } else if (b != 90) {
                    this.parserStep = ParserStep.START_SCAN_HEAD_A5;
                } else {
                    Pdlog.m3275i(TAG, "response 5A OK");
                    this.parserStep = ParserStep.START_SCAN_PYLOAD;
                }
            } else if (b == -91) {
                Pdlog.m3275i(TAG, "response A5 OK");
                this.parserStep = ParserStep.START_SCAN_HEAD_5A;
            }
        }
        return z;
    }

    private void parseData(byte[] bArr) {
        for (byte b : bArr) {
            int i = C47454.$SwitchMap$com$pudutech$lidar$rplidars1$RPLidarS1$ParserStep[this.parserStep.ordinal()];
            if (i != 4) {
                if (i != 5) {
                    if (i == 6) {
                        byte[] bArr2 = this.lidarSrcData;
                        int i2 = this.lidarSrcDataCount;
                        bArr2[i2] = b;
                        this.lidarSrcDataCount = i2 + 1;
                        if (this.lidarSrcDataCount == bArr2.length) {
                            byte[] bArr3 = this.now;
                            if (!checkOnePackageSum((byte) ((bArr3[0] & 15) | ((bArr3[1] & 15) << 4)), bArr2)) {
                                this.sumErrCnt++;
                                this.isOneFrameComplete = false;
                                Arrays.fill(this.pre, (byte) 0);
                                this.parserStep = ParserStep.PARSER_HEAD_0A;
                                reset();
                            } else {
                                System.arraycopy(this.lidarSrcData, 0, this.now, 2, 82);
                                parseNode();
                                byte[] bArr4 = this.now;
                                System.arraycopy(bArr4, 0, this.pre, 0, bArr4.length);
                                this.isOneFrameComplete = true;
                                this.parserStep = ParserStep.PARSER_HEAD_0A;
                                reset();
                            }
                        }
                    }
                } else if (((b & 240) >> 4) != 5) {
                    Pdlog.m3277w(TAG, "protocol error, sync2 must be 0x05, byte2=" + HexData.hexToString(new byte[]{b}));
                    this.isOneFrameComplete = false;
                    Arrays.fill(this.pre, (byte) 0);
                    this.continuouslyDataHeadErrorCount = this.continuouslyDataHeadErrorCount + 1;
                    if (this.continuouslyDataHeadErrorCount > 20) {
                        Pdlog.m3274e(TAG, "data head has appeared over more times");
                        LidarListener listener = getListener();
                        if (listener != null) {
                            listener.onProtocolError("protocol error, checkSum has occur " + this.crcErrorCount);
                        }
                        this.continuouslyDataHeadErrorCount = 0;
                        this.parserStep = ParserStep.PARSER_HEAD_0A;
                    }
                } else {
                    this.now[1] = b;
                    this.parserStep = ParserStep.PARSER_PYLOAD;
                }
            } else if (((b & 240) >> 4) != 10) {
                Pdlog.m3277w(TAG, "protocol error, sync1 must be 0x0A, byte1=" + HexData.hexToString(new byte[]{b}));
                this.isOneFrameComplete = false;
                Arrays.fill(this.pre, (byte) 0);
                this.continuouslyDataHeadErrorCount = this.continuouslyDataHeadErrorCount + 1;
                if (this.continuouslyDataHeadErrorCount > 20) {
                    Pdlog.m3274e(TAG, "data head has appeared over more times");
                    LidarListener listener2 = getListener();
                    if (listener2 != null) {
                        listener2.onProtocolError("protocol error, checkSum has occur " + this.crcErrorCount);
                    }
                    this.continuouslyDataHeadErrorCount = 0;
                }
            } else {
                this.now[0] = b;
                this.parserStep = ParserStep.PARSER_HEAD_05;
            }
        }
    }

    private void parseNode() {
        if (this.isOneFrameComplete) {
            Pdlog.m3273d(TAG, "OneFrameComplete");
            ArrayList<RPLidarS1Protocol.Node> test = this.rpLidarS1Protocol.test(this.pre, this.now);
            int i = -1;
            Iterator<RPLidarS1Protocol.Node> it = test.iterator();
            while (it.hasNext()) {
                RPLidarS1Protocol.Node next = it.next();
                LidarNode obtain = LidarNode.obtain();
                obtain.dist = (int) (next.distMm / 4);
                obtain.angle = (next.angle * 90) / 16384;
                obtain.distanceM = (next.distMm / 4.0d) / 1000.0d;
                obtain.angleInRad = Math.toRadians((next.angle * 90) / 16384.0d) - 3.141592653589793d;
                if (obtain.angleInRad < 0.0d) {
                    obtain.angleInRad += 6.283185307179586d;
                }
                this.nodes.add(obtain);
                int i2 = obtain.angle - 180;
                if (i2 > 360) {
                    i2 -= 360;
                }
                if (i2 < 0) {
                    i2 += 360;
                }
                next.recycle();
                i = i2;
            }
            ((ListNodePool) test).recycle();
            if (i <= 0 || !checkOneFrameComplete(i)) {
                return;
            }
            oneFrameComplete();
        }
    }

    private void oneFrameComplete() {
        LidarListener listener = getListener();
        if (listener != null) {
            if (this.nodes.isEmpty()) {
                Pdlog.m3277w(TAG, "empty frame");
                listener.onProtocolError("empty frame");
            } else {
                Pdlog.m3273d(TAG, "send one frame , nodes size = " + this.nodes.size());
                Pdlog.m3273d(TAG, "current Thread name :" + Thread.currentThread().getName() + ",tid = " + Thread.currentThread().getId());
                listener.onOneFrameComplete(this.nodes);
            }
        } else {
            Pdlog.m3277w(TAG, "lidarCallback is null");
        }
        this.nodes = ListLidarNodePool.obtain();
    }

    private boolean checkOneFrameComplete(int i) {
        Pdlog.m3273d(TAG, "checkOneFrameComplete endAngle = " + i);
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
        Pdlog.m3277w(TAG, "protocol error, checkSum[" + ((int) b2) + "] != receiveCheckSum[" + ((int) b) + "] errCnt is " + this.sumErrCnt + "," + HexData.hexToString(bArr));
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
            Pdlog.m3274e(TAG, "not same. receives= " + HexData.hexToString(bArr) + " wants=" + HexData.hexToString(bArr2));
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                Pdlog.m3274e(TAG, "not same. receives= " + HexData.hexToString(bArr) + " wants=" + HexData.hexToString(bArr2));
                return false;
            }
        }
        return true;
    }

    public LidarDeviceInfo getLidarInfo() {
        return new LidarDeviceInfo(LidarVersion.RPLIDAR_S1.name(), this.f5470sn);
    }
}
