package com.pudutech.lidar.rplidara2m7;

import android.os.SystemClock;
import com.felhr.utils.HexData;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import com.pudutech.lidar.rplidara2m7.RPLidarA2M7Protocol;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class RPLidarA2M7 extends SerialLidar {
    private static final String TAG = "Lidar";
    Thread parseDataThread;
    protected static final byte[] COMMAND_START_SCAN = {-91, -126, 5, 3, 0, 0, 0, 0, BinaryMemcacheOpcodes.SASL_AUTH};
    protected static final byte[] COMMAND_START_MOTOR = {-91, -16, 2, -108, 2, -63};
    protected static final byte[] COMMAND_STOP_MOTOR_SCAN = {-91, -16, 2, 0, 0, 87};
    static final byte[] SCAN_RESPONSE_BEGIN = {Constans.CAN_REV_DIS_MODE_SWITCH, 0, 0, 64, Constans.CAN_REV_DIS_MODE_SWITCH};
    private long startScanTimestamp = -1;
    private ControlStep step = ControlStep.IDLE;
    private Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.rplidara2m7.RPLidarA2M7.1
        @Override // java.lang.Runnable
        public void run() {
            RPLidarA2M7.this.controlFSM();
        }
    };
    private boolean isReceiveResponse = false;
    boolean isRunning = false;
    Runnable runnable = new Runnable() { // from class: com.pudutech.lidar.rplidara2m7.RPLidarA2M7.4
        private boolean checkReceiveScanResponse() {
            if (RPLidarA2M7.this.readOneBytesFromReceive() != -91) {
                return false;
            }
            Pdlog.m3275i("Lidar", "response A5 OK");
            if (RPLidarA2M7.this.readOneBytesFromReceive() != 90) {
                return false;
            }
            Pdlog.m3275i("Lidar", "response 5A OK");
            RPLidarA2M7 rPLidarA2M7 = RPLidarA2M7.this;
            return rPLidarA2M7.checkBytesSame(rPLidarA2M7.readBytesFromReceive(RPLidarA2M7.SCAN_RESPONSE_BEGIN.length), RPLidarA2M7.SCAN_RESPONSE_BEGIN);
        }

        @Override // java.lang.Runnable
        public void run() {
            while (RPLidarA2M7.this.isRunning) {
                if (RPLidarA2M7.this.isReceiveResponse) {
                    RPLidarA2M7.this.parseData();
                } else {
                    Pdlog.m3276v("Lidar", "finding protocol head");
                    RPLidarA2M7.this.isReceiveResponse = checkReceiveScanResponse();
                }
            }
        }
    };
    byte[] pre = null;
    byte[] now = new byte[132];
    RPLidarA2M7Protocol rpLidarA2M7Protocol = new RPLidarA2M7Protocol();
    ArrayBlockingQueue<Byte> receive = new ArrayBlockingQueue<>(1024);
    List<LidarNode> oneFrameNodes = null;
    List<LidarNode> nodes = ListLidarNodePool.obtain();
    private long sumErrCnt = 0;
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

    @Override // com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 256000;
    }

    int getBitFromByte(byte b, int i) {
        return ((b & 255) & (1 << i)) == 0 ? 0 : 1;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public long getPowerOnDelay_ms() {
        return 2000L;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] bArr) {
        if (this.startScanTimestamp == -1) {
            Pdlog.m3276v("Lidar", "receive data len " + bArr.length);
        } else if (SystemClock.elapsedRealtime() - this.startScanTimestamp < 5000) {
            Pdlog.m3273d("Lidar", "receive data len " + bArr.length + HexData.hexToString(bArr));
        } else {
            this.startScanTimestamp = -1L;
        }
        if (this.isRunning) {
            if (this.parseDataThread == null) {
                this.receive.clear();
                this.parseDataThread = new Thread(this.runnable);
                this.parseDataThread.start();
            }
            for (byte b : bArr) {
                try {
                    this.receive.offer(Byte.valueOf(b), 10L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    Pdlog.m3274e("Lidar", "parser error: " + e.toString());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void controlFSM() {
        Pdlog.m3275i("Lidar", "controlFSM step=" + this.step);
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            int i = C47365.f5469x8678faaa[this.step.ordinal()];
            if (i == 1) {
                lidarInterface.setDTR(false);
                this.step = ControlStep.START_MOTOR;
                this.controlHandler.postDelayed(this.controlRunnable, 500L);
                return;
            }
            if (i == 2) {
                lidarInterface.send(COMMAND_START_MOTOR);
                this.step = ControlStep.SCAN;
                this.controlHandler.postDelayed(this.controlRunnable, 200L);
            } else if (i == 3) {
                lidarInterface.send(COMMAND_START_SCAN);
                this.step = ControlStep.IDLE;
            } else if (i == 4) {
                lidarInterface.send(COMMAND_STOP_MOTOR_SCAN);
                this.step = ControlStep.STOP_DTR;
                this.controlHandler.postDelayed(this.controlRunnable, 500L);
            } else if (i == 5) {
                lidarInterface.setDTR(true);
                this.step = ControlStep.IDLE;
            } else {
                Pdlog.m3273d("Lidar", "controlFSM default do nothing");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pudutech.lidar.rplidara2m7.RPLidarA2M7$5 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C47365 {

        /* renamed from: $SwitchMap$com$pudutech$lidar$rplidara2m7$RPLidarA2M7$ControlStep */
        static final /* synthetic */ int[] f5469x8678faaa = new int[ControlStep.values().length];

        static {
            try {
                f5469x8678faaa[ControlStep.START_DTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5469x8678faaa[ControlStep.START_MOTOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5469x8678faaa[ControlStep.SCAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5469x8678faaa[ControlStep.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5469x8678faaa[ControlStep.STOP_DTR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3276v("Lidar", "startScan A2M7");
        this.isRunning = true;
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidara2m7.RPLidarA2M7.2
            @Override // java.lang.Runnable
            public void run() {
                if (RPLidarA2M7.this.step == ControlStep.IDLE || RPLidarA2M7.this.step == ControlStep.STOP || RPLidarA2M7.this.step == ControlStep.STOP_DTR) {
                    RPLidarA2M7.this.controlHandler.removeCallbacks(RPLidarA2M7.this.controlRunnable);
                    RPLidarA2M7.this.startScanTimestamp = SystemClock.elapsedRealtime();
                    RPLidarA2M7.this.step = ControlStep.START_DTR;
                    RPLidarA2M7.this.controlFSM();
                }
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3276v("Lidar", "stopScan A2M7");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidara2m7.RPLidarA2M7.3
            @Override // java.lang.Runnable
            public void run() {
                if (RPLidarA2M7.this.step == ControlStep.IDLE || !(RPLidarA2M7.this.step == ControlStep.STOP || RPLidarA2M7.this.step == ControlStep.STOP_DTR)) {
                    RPLidarA2M7.this.controlHandler.removeCallbacks(RPLidarA2M7.this.controlRunnable);
                    RPLidarA2M7.this.step = ControlStep.STOP;
                    RPLidarA2M7.this.controlFSM();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean parseData() {
        byte readOneBytesFromReceive = readOneBytesFromReceive();
        if (((readOneBytesFromReceive & 240) >> 4) != 10) {
            this.pre = null;
            return false;
        }
        this.now[0] = readOneBytesFromReceive;
        byte readOneBytesFromReceive2 = readOneBytesFromReceive();
        if (((readOneBytesFromReceive2 & 240) >> 4) != 5) {
            this.pre = null;
            return false;
        }
        this.now[1] = readOneBytesFromReceive2;
        int i = readOneBytesFromReceive & 15;
        byte[] readBytesFromReceive = readBytesFromReceive(130);
        if (!checkOnePackageSum((byte) (i | ((readOneBytesFromReceive2 & 15) << 4)), readBytesFromReceive)) {
            this.pre = null;
            return false;
        }
        System.arraycopy(readBytesFromReceive, 0, this.now, 2, 130);
        parseNode();
        this.pre = this.now;
        this.now = new byte[132];
        return true;
    }

    private void parseNode() {
        byte[] bArr = this.pre;
        if (bArr != null) {
            int i = -1;
            Iterator<RPLidarA2M7Protocol.Node> it = this.rpLidarA2M7Protocol.test(bArr, this.now).iterator();
            while (it.hasNext()) {
                RPLidarA2M7Protocol.Node next = it.next();
                LidarNode obtain = LidarNode.obtain();
                obtain.dist = (int) (next.distMMQ2U32 / 4);
                obtain.angle = (next.angleQ14U16 * 90) / 16384;
                obtain.distanceM = (next.distMMQ2U32 / 4.0d) / 1000.0d;
                obtain.angleInRad = Math.toRadians((next.angleQ14U16 * 90) / 16384.0d) - 3.141592653589793d;
                if (obtain.angleInRad < 0.0d) {
                    obtain.angleInRad += 6.283185307179586d;
                }
                this.nodes.add(obtain);
                i = obtain.angle - 180;
                if (i > 360) {
                    i -= 360;
                }
                if (i < 0) {
                    i += 360;
                }
            }
            Pdlog.m3276v("Lidar", "one package endAngle=" + i);
            if (i <= 0 || !checkOneFrameComplete(i)) {
                return;
            }
            oneFrameComplete();
        }
    }

    private void oneFrameComplete() {
        if (this.oneFrameNodes != null) {
            LidarListener listener = getListener();
            if (listener != null) {
                Pdlog.m3276v("Lidar", "on One Frame Complete");
                if (this.oneFrameNodes.isEmpty()) {
                    Pdlog.m3277w("Lidar", "empty frame");
                } else {
                    listener.onOneFrameComplete(this.oneFrameNodes);
                }
            } else {
                Pdlog.m3277w("Lidar", "lidarCallback is null");
            }
        }
        this.oneFrameNodes = this.nodes;
        this.nodes = ListLidarNodePool.obtain();
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
            return true;
        }
        this.sumErrCnt++;
        Pdlog.m3277w("Lidar", "protocol error, checkSum[" + ((int) b2) + "] != receiveCheckSum[" + ((int) b) + "] errCnt is " + this.sumErrCnt);
        return false;
    }

    boolean checkBytesSame(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            Pdlog.m3277w("Lidar", "not same. receives= " + HexData.hexToString(bArr) + " wants=" + HexData.hexToString(bArr2));
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                Pdlog.m3277w("Lidar", "not same. receives= " + HexData.hexToString(bArr) + " wants=" + HexData.hexToString(bArr2));
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte readOneBytesFromReceive() {
        try {
            return this.receive.take().byteValue();
        } catch (InterruptedException e) {
            Pdlog.m3274e("Lidar", "receive one fail: " + e.toString());
            Thread.currentThread().interrupt();
            return (byte) 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] readBytesFromReceive(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            try {
                bArr[i2] = this.receive.take().byteValue();
            } catch (InterruptedException e) {
                Pdlog.m3274e("Lidar", "receive " + i + " fail: " + e.toString());
                Thread.currentThread().interrupt();
            }
        }
        return bArr;
    }
}
