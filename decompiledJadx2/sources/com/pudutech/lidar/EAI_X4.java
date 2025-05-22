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
public class EAI_X4 extends SerialLidar {
    public static final byte[] COMMAND_START_SCAN = {-91, 96};
    public static final byte[] COMMAND_STOP_SCAN = {-91, 101};
    public static final String TAG = "Lidar";
    private short CTLSN;
    private short FSA;
    private short LSA;

    /* renamed from: PH */
    private short f5445PH;
    private double angleBase;
    private short angleEnd;
    private double angleResolution;
    private short angleStart;
    private short disEach;
    private byte[] last;
    private long timestamp;
    private ControlStep step = ControlStep.IDLE;
    private Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.EAI_X4.1
        @Override // java.lang.Runnable
        public void run() {
            EAI_X4.this.controlFSM();
        }
    };
    private int len = 0;
    private int disCnt = 0;
    private short receiveCheckSum = 0;
    private short checkSum = 0;
    private boolean isFirstPackOfFrame = false;
    private List<LidarNode> oneFrameNodes = new ArrayList();
    private List<LidarNode> nodes = new ArrayList();
    private State state = State.SearchStartLow;
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
        return 128000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void controlFSM() {
        Pdlog.m3275i("Lidar", "controlFSM step=" + this.step);
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            int i = C46884.$SwitchMap$com$pudutech$lidar$EAI_X4$ControlStep[this.step.ordinal()];
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
        Pdlog.m3275i("Lidar", "start scan");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.EAI_X4.2
            @Override // java.lang.Runnable
            public void run() {
                if (EAI_X4.this.step == ControlStep.IDLE || EAI_X4.this.step == ControlStep.STOP || EAI_X4.this.step == ControlStep.STOP_DTR) {
                    EAI_X4.this.controlHandler.removeCallbacks(EAI_X4.this.controlRunnable);
                    EAI_X4.this.step = ControlStep.START_DTR;
                    EAI_X4.this.controlFSM();
                }
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3275i("Lidar", "stop scan");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.EAI_X4.3
            @Override // java.lang.Runnable
            public void run() {
                if (EAI_X4.this.step == ControlStep.IDLE || !(EAI_X4.this.step == ControlStep.STOP || EAI_X4.this.step == ControlStep.STOP_DTR)) {
                    EAI_X4.this.controlHandler.removeCallbacks(EAI_X4.this.controlRunnable);
                    EAI_X4.this.step = ControlStep.STOP_DTR;
                    EAI_X4.this.controlFSM();
                }
            }
        });
    }

    private void parseData(byte[] bArr) {
        ArrayList arrayList;
        int i;
        byte[] bArr2 = bArr;
        Pdlog.m3276v("Lidar", "receive data len " + bArr2.length + " state " + this.state);
        ArrayList arrayList2 = new ArrayList();
        if (this.last != null) {
            Pdlog.m3276v("Lidar", "last time left " + this.last.length);
            byte[] bArr3 = this.last;
            byte[] bArr4 = new byte[bArr3.length + bArr2.length];
            System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
            System.arraycopy(bArr2, 0, bArr4, this.last.length, bArr2.length);
            bArr2 = bArr4;
        }
        int i2 = 0;
        int i3 = 0;
        for (int length = bArr2.length; i2 < length; length = i) {
            byte b = bArr2[i2];
            i3++;
            switch (C46884.$SwitchMap$com$pudutech$lidar$EAI_X4$State[this.state.ordinal()]) {
                case 1:
                    arrayList = arrayList2;
                    i = length;
                    this.f5445PH = b;
                    this.state = b == -86 ? State.SearchStartHigh : State.SearchStartLow;
                    continue;
                case 2:
                    arrayList = arrayList2;
                    i = length;
                    this.f5445PH = (short) (((b & 255) << 8) | (this.f5445PH & 255));
                    this.state = b == 85 ? State.PackType : State.SearchStartLow;
                    continue;
                case 3:
                    arrayList = arrayList2;
                    i = length;
                    this.CTLSN = b;
                    boolean z = b != 0;
                    this.isFirstPackOfFrame = z;
                    if (z) {
                        Pdlog.m3276v("Lidar", "isFirstPackOfFrame=" + this.isFirstPackOfFrame);
                    }
                    this.state = State.FrameLength;
                    continue;
                case 4:
                    arrayList = arrayList2;
                    i = length;
                    int i4 = b & 255;
                    this.CTLSN = (short) ((i4 << 8) | (this.CTLSN & 255));
                    this.len = i4;
                    this.disCnt = 0;
                    this.state = State.StartAngleLow;
                    if (this.len == 0) {
                        int i5 = i3 - 3;
                        int length2 = bArr2.length;
                        if (i5 > 0) {
                            length2 -= i5;
                        }
                        byte[] bArr5 = new byte[length2];
                        System.arraycopy(bArr2, bArr2.length - length2, bArr5, 0, length2);
                        Pdlog.m3277w("Lidar", " len=0 " + HexData.hexToString(bArr5));
                        this.state = State.SearchStartLow;
                        break;
                    }
                    break;
                case 5:
                    arrayList = arrayList2;
                    i = length;
                    this.angleStart = b;
                    this.state = State.StartAngleHigh;
                    break;
                case 6:
                    arrayList = arrayList2;
                    i = length;
                    short s = (short) (((b & 255) << 8) | (this.angleStart & 255));
                    this.angleStart = s;
                    this.FSA = s;
                    this.angleStart = (short) ((s >> 1) & 32767);
                    this.state = State.EndAngleLow;
                    break;
                case 7:
                    arrayList = arrayList2;
                    i = length;
                    this.angleEnd = b;
                    this.state = State.EndAngleHigh;
                    break;
                case 8:
                    arrayList = arrayList2;
                    i = length;
                    short s2 = (short) (((b & 255) << 8) | (this.angleEnd & 255));
                    this.angleEnd = s2;
                    this.LSA = s2;
                    short s3 = (short) ((s2 >> 1) & 32767);
                    this.angleEnd = s3;
                    short s4 = this.angleStart;
                    this.angleBase = s4 / 64.0d;
                    this.angleResolution = s3 < s4 ? (((s3 / 64.0d) + 360.0d) - (s4 / 64.0d)) / this.len : ((s3 - s4) / 64.0d) / this.len;
                    this.state = State.ChecksumLow;
                    Pdlog.m3276v("Lidar", "angle " + this.angleBase + "  resolution " + this.angleResolution + " END " + (this.angleEnd / 64.0d));
                    break;
                case 9:
                    arrayList = arrayList2;
                    i = length;
                    this.receiveCheckSum = b;
                    this.state = State.ChecksumHigh;
                    break;
                case 10:
                    arrayList = arrayList2;
                    i = length;
                    this.receiveCheckSum = (short) (((b & 255) << 8) | (this.receiveCheckSum & 255));
                    this.checkSum = (short) (this.f5445PH ^ this.FSA);
                    this.state = State.DistanceLow;
                    break;
                case 11:
                    arrayList = arrayList2;
                    i = length;
                    this.disEach = b;
                    this.state = State.DistanceHigh;
                    break;
                case 12:
                    short s5 = (short) (((b & 255) << 8) | (this.disEach & 255));
                    this.disEach = s5;
                    this.checkSum = (short) (this.checkSum ^ s5);
                    double d = s5 / 4.0d;
                    double degrees = s5 == 0 ? 0.0d : Math.toDegrees(Math.atan((((155.3d - d) * 21.8d) / 155.3d) / d));
                    LidarNode lidarNode = new LidarNode();
                    lidarNode.dist = this.disEach / 4;
                    i = length;
                    lidarNode.distance_m = (this.disEach / 4.0d) / 1000.0d;
                    ArrayList arrayList3 = arrayList2;
                    lidarNode.angle = (int) (this.angleBase + (this.disCnt * this.angleResolution));
                    double d2 = this.angleBase + (this.disCnt * this.angleResolution) + degrees;
                    if (d2 >= 360.0d) {
                        d2 -= 360.0d;
                    }
                    lidarNode.angleInRad = Math.toRadians(d2);
                    arrayList = arrayList3;
                    arrayList.add(lidarNode);
                    this.nodes.add(lidarNode);
                    int i6 = this.disCnt + 1;
                    this.disCnt = i6;
                    if (i6 == this.len) {
                        short s6 = (short) ((this.checkSum ^ this.CTLSN) ^ this.LSA);
                        this.checkSum = s6;
                        if (s6 == this.receiveCheckSum) {
                            this.oneFrameNodes.addAll(this.nodes);
                        } else {
                            Pdlog.m3277w("Lidar", "checkSum " + ((int) this.checkSum) + "  " + ((int) this.receiveCheckSum));
                        }
                        this.nodes = new ArrayList();
                        if (checkOneFrameComplete(d2)) {
                            oneComplete();
                        }
                        this.state = State.SearchStartLow;
                        break;
                    } else {
                        this.state = State.DistanceLow;
                        break;
                    }
                default:
                    arrayList = arrayList2;
                    i = length;
                    continue;
            }
            i2++;
            arrayList2 = arrayList;
        }
        if (i3 != bArr2.length) {
            int length3 = bArr2.length - i3;
            byte[] bArr6 = new byte[length3];
            this.last = bArr6;
            System.arraycopy(bArr2, i3, bArr6, 0, length3);
            return;
        }
        this.last = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: com.pudutech.lidar.EAI_X4$4 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C46884 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$EAI_X4$ControlStep;
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$EAI_X4$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$pudutech$lidar$EAI_X4$State = iArr;
            try {
                iArr[State.SearchStartLow.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.SearchStartHigh.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.PackType.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.FrameLength.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.StartAngleLow.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.StartAngleHigh.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.EndAngleLow.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.EndAngleHigh.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.ChecksumLow.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.ChecksumHigh.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.DistanceLow.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$State[State.DistanceHigh.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr2 = new int[ControlStep.values().length];
            $SwitchMap$com$pudutech$lidar$EAI_X4$ControlStep = iArr2;
            try {
                iArr2[ControlStep.START_DTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$ControlStep[ControlStep.SCAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$ControlStep[ControlStep.STOP_DTR.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$EAI_X4$ControlStep[ControlStep.STOP.ordinal()] = 4;
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
            Pdlog.m3277w("Lidar", "empty frame");
            return;
        }
        LidarListener listener = getListener();
        if (listener != null) {
            Pdlog.m3276v("Lidar", "on One Frame Complete");
            listener.onOneFrameComplete(this.oneFrameNodes);
        } else {
            Pdlog.m3277w("Lidar", "lidarCallback is null");
        }
        if (SystemClock.elapsedRealtime() - this.timestamp > 200) {
            Pdlog.m3277w("Lidar", "one frame complete too long " + (SystemClock.elapsedRealtime() - this.timestamp));
        }
        this.timestamp = SystemClock.elapsedRealtime();
        this.oneFrameNodes = new ArrayList();
    }
}
