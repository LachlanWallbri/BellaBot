package com.pudutech.lidar.eaitg30;

import android.os.SystemClock;
import com.felhr.utils.HexData;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import java.util.List;

/* loaded from: classes5.dex */
public class EaiTg30 extends SerialLidar {
    protected static final byte[] COMMAND_START_SCAN = {-91, -48, -91, 11, -91, 11, -91, 11, -91, 11, -91, 11, -91, 11, -91, 12, -91, 12, -91, 96};
    protected static final byte[] COMMAND_STOP_SCAN = {-91, 101};
    public static final String TAG = "Lidar";
    private double angleBase;
    private short angleEnd;
    private double angleResolution;
    private short angleStart;
    private short disEach;
    private byte[] last;
    private short mCTLsn;
    private short mFsa;
    private short mLsa;
    private short mPH;
    private long timestamp;
    private ControlStep step = ControlStep.IDLE;
    private final Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.eaitg30.EaiTg30.1
        @Override // java.lang.Runnable
        public void run() {
            EaiTg30.this.controlFSM();
        }
    };
    private int mLsn = 0;
    private int disCnt = 0;
    private short receiveCheckSum = 0;
    private short checkSum = 0;
    private List<LidarNode> oneFrameNodes = ListLidarNodePool.obtain();
    private List<LidarNode> nodes = ListLidarNodePool.obtain();
    private State state = State.SEARCH_START_LOW;
    private long sumErrCnt = 0;
    private long last7BytesTimestamp = 0;
    private long rebootCnt = 0;
    private long startScanTimestamp = -1;
    private boolean isStartFlag = true;
    private double lastDegree = -1.0d;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public enum ControlStep {
        START_DTR,
        SCAN,
        STOP,
        STOP_DTR,
        IDLE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public enum State {
        SEARCH_START_LOW,
        SEARCH_START_HIGH,
        PACK_TYPE,
        FRAME_LENGTH,
        START_ANGLE_LOW,
        START_ANGLE_HIGH,
        END_ANGLE_LOW,
        END_ANGLE_HIGH,
        DISTANCE_LOW,
        DISTANCE_HIGH,
        CHECK_SUM_LOW,
        CHECK_SUM_HIGH
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
        Pdlog.m3275i("Lidar", "controlFSM step=" + this.step);
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            int i = C47134.$SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$ControlStep[this.step.ordinal()];
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
            } else if (i == 4) {
                lidarInterface.send(COMMAND_STOP_SCAN);
                this.step = ControlStep.IDLE;
            } else {
                Pdlog.m3273d("Lidar", "default do nothing");
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
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.eaitg30.EaiTg30.2
            @Override // java.lang.Runnable
            public void run() {
                if (EaiTg30.this.step == ControlStep.IDLE || EaiTg30.this.step == ControlStep.STOP || EaiTg30.this.step == ControlStep.STOP_DTR) {
                    EaiTg30.this.controlHandler.removeCallbacks(EaiTg30.this.controlRunnable);
                    EaiTg30.this.startScanTimestamp = SystemClock.elapsedRealtime();
                    EaiTg30.this.step = ControlStep.START_DTR;
                    EaiTg30.this.controlFSM();
                }
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3275i("Lidar", "stop scan");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.eaitg30.EaiTg30.3
            @Override // java.lang.Runnable
            public void run() {
                if (EaiTg30.this.step == ControlStep.IDLE || !(EaiTg30.this.step == ControlStep.STOP || EaiTg30.this.step == ControlStep.STOP_DTR)) {
                    EaiTg30.this.controlHandler.removeCallbacks(EaiTg30.this.controlRunnable);
                    EaiTg30.this.step = ControlStep.STOP_DTR;
                    EaiTg30.this.controlFSM();
                }
            }
        });
    }

    private void checkRebootWrong(byte[] bArr) {
        if (bArr.length == 7 && bArr[0] == -91 && bArr[1] == 90) {
            Pdlog.m3277w("Lidar", "checkRebootWrong " + HexData.hexToString(bArr));
            if (SystemClock.elapsedRealtime() - this.last7BytesTimestamp < 5000) {
                this.rebootCnt++;
                if (this.rebootCnt > 5) {
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
        checkRebootWrong(bArr);
        if (this.startScanTimestamp == -1) {
            Pdlog.m3276v("Lidar", "receive data len " + bArr.length + " state " + this.state);
        } else if (SystemClock.elapsedRealtime() - this.startScanTimestamp < 5000) {
            Pdlog.m3273d("Lidar", "receive data len " + bArr.length + " state " + this.state + " " + HexData.hexToString(bArr));
        } else {
            this.startScanTimestamp = -1L;
        }
        realParse(getBytes(bArr));
    }

    private void realParse(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i++;
            switch (this.state) {
                case SEARCH_START_LOW:
                    parseSearchStartLow(b);
                    break;
                case SEARCH_START_HIGH:
                    parseSearchStartHigh(b);
                    break;
                case PACK_TYPE:
                    parsePackType(b);
                    break;
                case FRAME_LENGTH:
                    parseFrameLength(bArr, i, b);
                    break;
                case START_ANGLE_LOW:
                    parseStartAngleLow(b);
                    break;
                case START_ANGLE_HIGH:
                    parseStartAngleHigh(b);
                    break;
                case END_ANGLE_LOW:
                    parseEndAngleLow(b);
                    break;
                case END_ANGLE_HIGH:
                    parseEndAngleHigh(b);
                    break;
                case CHECK_SUM_LOW:
                    parseCheckSumLow(b);
                    break;
                case CHECK_SUM_HIGH:
                    parseCheckSumHigh(b);
                    break;
                case DISTANCE_LOW:
                    parseDistanceLow(b);
                    break;
                case DISTANCE_HIGH:
                    parseDistanceHigh(b);
                    break;
            }
        }
        if (i != bArr.length) {
            int length = bArr.length - i;
            this.last = new byte[length];
            System.arraycopy(bArr, i, this.last, 0, length);
            return;
        }
        this.last = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pudutech.lidar.eaitg30.EaiTg30$4 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C47134 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$ControlStep;

        static {
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.SEARCH_START_LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.SEARCH_START_HIGH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.PACK_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.FRAME_LENGTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.START_ANGLE_LOW.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.START_ANGLE_HIGH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.END_ANGLE_LOW.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.END_ANGLE_HIGH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.CHECK_SUM_LOW.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.CHECK_SUM_HIGH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.DISTANCE_LOW.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$State[State.DISTANCE_HIGH.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$ControlStep = new int[ControlStep.values().length];
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$ControlStep[ControlStep.START_DTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$ControlStep[ControlStep.SCAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$ControlStep[ControlStep.STOP_DTR.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$eaitg30$EaiTg30$ControlStep[ControlStep.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    private void parseDistanceHigh(byte b) {
        this.disEach = (short) (((b & 255) << 8) | (this.disEach & 255));
        short s = this.checkSum;
        short s2 = this.disEach;
        this.checkSum = (short) (s ^ s2);
        double d = s2;
        LidarNode obtain = LidarNode.obtain();
        obtain.dist = (int) d;
        obtain.distanceM = d / 1000.0d;
        double d2 = this.angleBase + (this.disCnt * this.angleResolution);
        obtain.angle = (int) d2;
        if (d2 >= 360.0d) {
            d2 -= 360.0d;
        }
        obtain.angleInRad = Math.toRadians(d2);
        this.nodes.add(obtain);
        this.disCnt++;
        if (this.disCnt == this.mLsn) {
            this.checkSum = (short) ((this.checkSum ^ this.mCTLsn) ^ this.mLsa);
            if (this.checkSum == this.receiveCheckSum) {
                this.oneFrameNodes.addAll(this.nodes);
            } else {
                this.sumErrCnt++;
                Pdlog.m3277w("Lidar", "checkSum[" + ((int) this.checkSum) + "] != receiveCheckSum[" + ((int) this.receiveCheckSum) + "] errCnt is " + this.sumErrCnt);
            }
            this.nodes = ListLidarNodePool.obtain();
            if (checkOneFrameComplete(d2)) {
                oneComplete();
            }
            this.state = State.SEARCH_START_LOW;
            return;
        }
        this.state = State.DISTANCE_LOW;
    }

    private void parseDistanceLow(byte b) {
        this.disEach = b;
        this.state = State.DISTANCE_HIGH;
    }

    private void parseCheckSumHigh(byte b) {
        this.receiveCheckSum = (short) (((b & 255) << 8) | (this.receiveCheckSum & 255));
        this.checkSum = (short) (this.mPH ^ this.mFsa);
        this.state = State.DISTANCE_LOW;
    }

    private void parseCheckSumLow(byte b) {
        this.receiveCheckSum = b;
        this.state = State.CHECK_SUM_HIGH;
    }

    private void parseEndAngleHigh(byte b) {
        this.angleEnd = (short) (((b & 255) << 8) | (this.angleEnd & 255));
        short s = this.angleEnd;
        this.mLsa = s;
        this.angleEnd = (short) ((s >> 1) & 32767);
        short s2 = this.angleEnd;
        double d = s2 / 64.0d;
        short s3 = this.angleStart;
        this.angleBase = s3 / 64.0d;
        if (this.mLsn <= 1) {
            this.angleResolution = 0.0d;
        } else if (s2 < s3) {
            this.angleResolution = ((360.0d + d) - this.angleBase) / (r3 - 1);
        } else {
            this.angleResolution = (d - this.angleBase) / (r3 - 1);
        }
        this.state = State.CHECK_SUM_LOW;
        Pdlog.m3276v("Lidar", "angle " + this.angleBase + "  resolution " + this.angleResolution + " END " + d + " mLsn:" + this.mLsn);
    }

    private void parseEndAngleLow(byte b) {
        this.angleEnd = b;
        this.state = State.END_ANGLE_HIGH;
    }

    private void parseStartAngleHigh(byte b) {
        this.angleStart = (short) (((b & 255) << 8) | (this.angleStart & 255));
        short s = this.angleStart;
        this.mFsa = s;
        this.angleStart = (short) ((s >> 1) & 32767);
        this.state = State.END_ANGLE_LOW;
    }

    private void parseStartAngleLow(byte b) {
        this.angleStart = b;
        this.state = State.START_ANGLE_HIGH;
    }

    private void parseFrameLength(byte[] bArr, int i, byte b) {
        int i2 = b & 255;
        this.mCTLsn = (short) ((i2 << 8) | (this.mCTLsn & 255));
        this.mLsn = i2;
        this.disCnt = 0;
        this.state = State.START_ANGLE_LOW;
        if (this.mLsn == 0) {
            int i3 = i - 3;
            int length = bArr.length;
            if (i3 > 0) {
                length -= i3;
            }
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, bArr.length - length, bArr2, 0, length);
            Pdlog.m3277w("Lidar", " mLsn=0 " + HexData.hexToString(bArr2));
            this.state = State.SEARCH_START_LOW;
        }
    }

    private void parsePackType(byte b) {
        this.mCTLsn = b;
        boolean z = b != 0;
        if (z) {
            Pdlog.m3276v("Lidar", "isFirstPackOfFrame=" + z);
        }
        this.state = State.FRAME_LENGTH;
    }

    private void parseSearchStartHigh(byte b) {
        this.mPH = (short) (((b & 255) << 8) | (this.mPH & 255));
        this.state = b == 85 ? State.PACK_TYPE : State.SEARCH_START_LOW;
    }

    private void parseSearchStartLow(byte b) {
        this.mPH = b;
        this.state = b == -86 ? State.SEARCH_START_HIGH : State.SEARCH_START_LOW;
    }

    private byte[] getBytes(byte[] bArr) {
        if (this.last == null) {
            return bArr;
        }
        Pdlog.m3276v("Lidar", "last time left " + this.last.length);
        byte[] bArr2 = this.last;
        byte[] bArr3 = new byte[bArr2.length + bArr.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(bArr, 0, bArr3, this.last.length, bArr.length);
        return bArr3;
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
        this.oneFrameNodes = ListLidarNodePool.obtain();
    }
}
