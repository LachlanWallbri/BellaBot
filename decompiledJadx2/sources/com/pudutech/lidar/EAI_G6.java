package com.pudutech.lidar;

import android.os.SystemClock;
import com.felhr.utils.HexData;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes.dex */
public class EAI_G6 extends SerialLidar {
    public static final byte[] COMMAND_START_SCAN = {-91, -48, -91, 96, -91, 11, -91, 11, -91, 11, -91, 11, -91, 11, -91, 11, -91, 12, -91, 12};
    public static final byte[] COMMAND_STOP_SCAN = {-91, 101};
    public static String TAG = "Lidar";
    private short CTLSN;
    private short FSA;
    private short LSA;

    /* renamed from: PH */
    private short f5444PH;
    private double angleBase;
    private short angleEnd;
    private double angleResolution;
    private short angleStart;
    private short disEach;
    private byte[] last;
    private long timestamp;
    private ControlStep step = ControlStep.IDLE;
    private Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.EAI_G6.1
        @Override // java.lang.Runnable
        public void run() {
            EAI_G6.this.controlFSM();
        }
    };
    private int LSN = 0;
    private int disCnt = 0;
    private short receiveCheckSum = 0;
    private short checkSum = 0;
    private boolean isFirstPackOfFrame = false;
    private List<LidarNode> oneFrameNodes = new ArrayList();
    private List<LidarNode> nodes = new ArrayList();
    private State state = State.SearchStartLow;
    private long sumErrCnt = 0;
    private long last7BytesTimestamp = 0;
    private long rebootCnt = 0;
    private long startScanTimestamp = -1;
    private boolean isStartFlag = true;
    private double lastDegree = -1.0d;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes.dex */
    public enum ControlStep {
        START_DTR,
        SCAN,
        STOP,
        STOP_DTR,
        IDLE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* loaded from: classes.dex */
    public enum State {
        SearchStartLow,
        SearchStartHigh,
        PackType,
        FrameLength,
        StartAngleLow,
        StartAngleHigh,
        EndAngleLow,
        EndAngleHigh,
        DistanceLow,
        DistanceHigh,
        ChecksumLow,
        ChecksumHigh
    }

    @Override // com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 512000;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public long getPowerOnDelay_ms() {
        return 2000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void controlFSM() {
        Pdlog.m3275i(TAG, "controlFSM step=" + this.step);
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            int i = C46844.$SwitchMap$com$pudutech$lidar$EAI_G6$ControlStep[this.step.ordinal()];
            if (i == 1) {
                lidarInterface.setDTR(true);
                this.step = ControlStep.SCAN;
                this.controlHandler.postDelayed(this.controlRunnable, 500L);
            } else if (i == 2) {
                lidarInterface.send(COMMAND_START_SCAN);
                this.step = ControlStep.IDLE;
            } else if (i == 3) {
                lidarInterface.setDTR(false);
                this.step = ControlStep.STOP;
                this.controlHandler.postDelayed(this.controlRunnable, 500L);
            } else {
                if (i != 4) {
                    return;
                }
                lidarInterface.send(COMMAND_STOP_SCAN);
                this.step = ControlStep.IDLE;
            }
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] bArr) {
        parseData(bArr);
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3275i(TAG, "start scan");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.EAI_G6.2
            @Override // java.lang.Runnable
            public void run() {
                if (EAI_G6.this.step == ControlStep.IDLE || EAI_G6.this.step == ControlStep.STOP || EAI_G6.this.step == ControlStep.STOP_DTR) {
                    EAI_G6.this.controlHandler.removeCallbacks(EAI_G6.this.controlRunnable);
                    EAI_G6.this.startScanTimestamp = SystemClock.elapsedRealtime();
                    EAI_G6.this.step = ControlStep.START_DTR;
                    EAI_G6.this.controlFSM();
                }
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3275i(TAG, "stop scan");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.EAI_G6.3
            @Override // java.lang.Runnable
            public void run() {
                if (EAI_G6.this.step == ControlStep.IDLE || !(EAI_G6.this.step == ControlStep.STOP || EAI_G6.this.step == ControlStep.STOP_DTR)) {
                    EAI_G6.this.controlHandler.removeCallbacks(EAI_G6.this.controlRunnable);
                    EAI_G6.this.step = ControlStep.STOP_DTR;
                    EAI_G6.this.controlFSM();
                }
            }
        });
    }

    private void checkRebootWrong(byte[] bArr) {
        if (bArr.length == 7 && bArr[0] == -91 && bArr[1] == 90) {
            Pdlog.m3277w(TAG, "checkRebootWrong " + HexData.hexToString(bArr));
            if (SystemClock.elapsedRealtime() - this.last7BytesTimestamp < 5000) {
                long j = this.rebootCnt + 1;
                this.rebootCnt = j;
                if (j > 5) {
                    this.last7BytesTimestamp = SystemClock.elapsedRealtime();
                    this.rebootCnt = 0L;
                    LidarListener listener = getListener();
                    if (listener != null) {
                        listener.onProtocolError("lidar reboot repeatedly");
                        return;
                    }
                    return;
                }
                return;
            }
            this.last7BytesTimestamp = SystemClock.elapsedRealtime();
            this.rebootCnt = 0L;
        }
    }

    private void parseData(byte[] bArr) {
        byte[] bArr2 = bArr;
        checkRebootWrong(bArr);
        if (this.startScanTimestamp == -1) {
            Pdlog.m3276v(TAG, "receive data len " + bArr2.length + " state " + this.state);
        } else if (SystemClock.elapsedRealtime() - this.startScanTimestamp < 5000) {
            Pdlog.m3273d(TAG, "receive data len " + bArr2.length + " state " + this.state + " " + HexData.hexToString(bArr));
        } else {
            this.startScanTimestamp = -1L;
        }
        ArrayList arrayList = new ArrayList();
        if (this.last != null) {
            Pdlog.m3276v(TAG, "last time left " + this.last.length);
            byte[] bArr3 = this.last;
            byte[] bArr4 = new byte[bArr3.length + bArr2.length];
            System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
            System.arraycopy(bArr2, 0, bArr4, this.last.length, bArr2.length);
            bArr2 = bArr4;
        }
        int length = bArr2.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            byte b = bArr2[i2];
            i++;
            switch (C46844.$SwitchMap$com$pudutech$lidar$EAI_G6$State[this.state.ordinal()]) {
                case 1:
                    this.f5444PH = b;
                    this.state = b == -86 ? State.SearchStartHigh : State.SearchStartLow;
                    continue;
                case 2:
                    this.f5444PH = (short) (((b & 255) << 8) | (this.f5444PH & 255));
                    this.state = b == 85 ? State.PackType : State.SearchStartLow;
                    continue;
                case 3:
                    this.CTLSN = b;
                    boolean z = b != 0;
                    this.isFirstPackOfFrame = z;
                    if (z) {
                        Pdlog.m3276v(TAG, "isFirstPackOfFrame=" + this.isFirstPackOfFrame);
                    }
                    this.state = State.FrameLength;
                    continue;
                case 4:
                    int i3 = b & 255;
                    this.CTLSN = (short) ((i3 << 8) | (this.CTLSN & 255));
                    this.LSN = i3;
                    this.disCnt = 0;
                    this.state = State.StartAngleLow;
                    if (this.LSN == 0) {
                        int i4 = i - 3;
                        int length2 = bArr2.length;
                        if (i4 > 0) {
                            length2 -= i4;
                        }
                        byte[] bArr5 = new byte[length2];
                        System.arraycopy(bArr2, bArr2.length - length2, bArr5, 0, length2);
                        Pdlog.m3277w(TAG, " LSN=0 " + HexData.hexToString(bArr5));
                        this.state = State.SearchStartLow;
                        break;
                    }
                    break;
                case 5:
                    this.angleStart = b;
                    this.state = State.StartAngleHigh;
                    break;
                case 6:
                    short s = (short) (((b & 255) << 8) | (this.angleStart & 255));
                    this.angleStart = s;
                    this.FSA = s;
                    this.angleStart = (short) ((s >> 1) & 32767);
                    this.state = State.EndAngleLow;
                    break;
                case 7:
                    this.angleEnd = b;
                    this.state = State.EndAngleHigh;
                    break;
                case 8:
                    short s2 = (short) (((b & 255) << 8) | (this.angleEnd & 255));
                    this.angleEnd = s2;
                    this.LSA = s2;
                    short s3 = (short) ((s2 >> 1) & 32767);
                    this.angleEnd = s3;
                    double d = s3 / 64.0d;
                    short s4 = this.angleStart;
                    double d2 = s4 / 64.0d;
                    this.angleBase = d2;
                    if (this.LSN <= 1) {
                        this.angleResolution = 0.0d;
                    } else if (s3 < s4) {
                        this.angleResolution = ((360.0d + d) - d2) / (r9 - 1);
                    } else {
                        this.angleResolution = (d - d2) / (r9 - 1);
                    }
                    this.state = State.ChecksumLow;
                    Pdlog.m3276v(TAG, "angle " + this.angleBase + "  resolution " + this.angleResolution + " END " + d + " LSN:" + this.LSN);
                    break;
                case 9:
                    this.receiveCheckSum = b;
                    this.state = State.ChecksumHigh;
                    break;
                case 10:
                    this.receiveCheckSum = (short) (((b & 255) << 8) | (this.receiveCheckSum & 255));
                    this.checkSum = (short) (this.f5444PH ^ this.FSA);
                    this.state = State.DistanceLow;
                    break;
                case 11:
                    this.disEach = b;
                    this.state = State.DistanceHigh;
                    break;
                case 12:
                    short s5 = (short) (((b & 255) << 8) | (this.disEach & 255));
                    this.disEach = s5;
                    this.checkSum = (short) (this.checkSum ^ s5);
                    double d3 = s5 / 2.0d;
                    LidarNode lidarNode = new LidarNode();
                    lidarNode.dist = (int) d3;
                    lidarNode.distance_m = d3 / 1000.0d;
                    double degrees = this.disEach != 0 ? Math.toDegrees(Math.atan(((155.3d - d3) * 21.8d) / (d3 * 155.3d))) : 0.0d;
                    lidarNode.angle = (int) (this.angleBase + (this.disCnt * this.angleResolution) + degrees);
                    double d4 = this.angleBase + (this.disCnt * this.angleResolution) + degrees;
                    if (d4 >= 360.0d) {
                        d4 -= 360.0d;
                    }
                    lidarNode.angleInRad = Math.toRadians(d4);
                    arrayList.add(lidarNode);
                    this.nodes.add(lidarNode);
                    int i5 = this.disCnt + 1;
                    this.disCnt = i5;
                    if (i5 == this.LSN) {
                        short s6 = (short) ((this.checkSum ^ this.CTLSN) ^ this.LSA);
                        this.checkSum = s6;
                        if (s6 == this.receiveCheckSum) {
                            this.oneFrameNodes.addAll(this.nodes);
                        } else {
                            this.sumErrCnt++;
                            Pdlog.m3277w(TAG, "checkSum[" + ((int) this.checkSum) + "] != receiveCheckSum[" + ((int) this.receiveCheckSum) + "] errCnt is " + this.sumErrCnt);
                        }
                        this.nodes = new ArrayList();
                        if (checkOneFrameComplete(d4)) {
                            oneComplete();
                        }
                        this.state = State.SearchStartLow;
                        break;
                    } else {
                        this.state = State.DistanceLow;
                        break;
                    }
            }
        }
        if (i != bArr2.length) {
            int length3 = bArr2.length - i;
            byte[] bArr6 = new byte[length3];
            this.last = bArr6;
            System.arraycopy(bArr2, i, bArr6, 0, length3);
            return;
        }
        this.last = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: com.pudutech.lidar.EAI_G6$4 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C46844 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$EAI_G6$ControlStep;
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$EAI_G6$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$pudutech$lidar$EAI_G6$State = iArr;
            try {
                iArr[State.SearchStartLow.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.SearchStartHigh.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.PackType.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.FrameLength.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.StartAngleLow.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.StartAngleHigh.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.EndAngleLow.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.EndAngleHigh.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.ChecksumLow.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.ChecksumHigh.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.DistanceLow.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$State[State.DistanceHigh.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr2 = new int[ControlStep.values().length];
            $SwitchMap$com$pudutech$lidar$EAI_G6$ControlStep = iArr2;
            try {
                iArr2[ControlStep.START_DTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$ControlStep[ControlStep.SCAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$ControlStep[ControlStep.STOP_DTR.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_G6$ControlStep[ControlStep.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    private boolean checkOneFrameComplete(double d) {
        double d2 = this.lastDegree;
        this.lastDegree = d;
        if (d < d2) {
            return false;
        }
        if (d > 315.0d || d < 135.0d) {
            if (this.isStartFlag) {
                this.isStartFlag = false;
                return true;
            }
        } else {
            this.isStartFlag = true;
        }
        return false;
    }

    private void oneComplete() {
        if (this.oneFrameNodes.isEmpty()) {
            Pdlog.m3277w(TAG, "empty frame");
            return;
        }
        LidarListener listener = getListener();
        if (listener != null) {
            Pdlog.m3276v(TAG, "on One Frame Complete");
            listener.onOneFrameComplete(this.oneFrameNodes);
        } else {
            Pdlog.m3277w(TAG, "lidarCallback is null");
        }
        if (SystemClock.elapsedRealtime() - this.timestamp > 200) {
            Pdlog.m3277w(TAG, "one frame complete too long " + (SystemClock.elapsedRealtime() - this.timestamp));
        }
        this.timestamp = SystemClock.elapsedRealtime();
        this.oneFrameNodes = new ArrayList();
    }
}
