package com.pudutech.lidar.rplidar_a3;

import android.os.SystemClock;
import com.felhr.utils.HexData;
import com.pudutech.base.FilePath;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import com.pudutech.lidar.rplidar_a3.RPLIDAR_A3_PROTOCOL;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes.dex */
public class RPLIDAR_A3 extends SerialLidar {
    private static final String MODE_KEY = "mode";
    private static final byte[] SCAN_RESPONSE_BEGIN = {Constans.CAN_REV_DIS_MODE_SWITCH, 0, 0, 64, Constans.CAN_REV_DIS_MODE_SWITCH};
    private static final String TAG = "Lidar";
    private Thread parseDataThread;
    private RPLIDAR_A3_WORK_MODE curMode = RPLIDAR_A3_WORK_MODE.MODE_INDOOR;
    private boolean isReceiveResponse = false;
    private boolean isRunning = false;
    private byte[] pre = null;
    private byte[] now = new byte[132];
    private RPLIDAR_A3_PROTOCOL RPLIDARA3PROTOCAL = new RPLIDAR_A3_PROTOCOL();
    private ArrayBlockingQueue<Byte> receive = new ArrayBlockingQueue<>(1024);
    private List<LidarNode> oneFrameNodes = null;
    private List<LidarNode> nodes = new ArrayList();
    private long sumErrCnt = 0;
    private ControlStep step = ControlStep.IDLE;
    private Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.rplidar_a3.RPLIDAR_A3.1
        @Override // java.lang.Runnable
        public void run() {
            RPLIDAR_A3.this.controlFSM();
        }
    };
    private boolean isStartFlag = true;
    private int last = -1;
    private Runnable runnable = new Runnable() { // from class: com.pudutech.lidar.rplidar_a3.RPLIDAR_A3.4
        private long timestamp = 0;

        @Override // java.lang.Runnable
        public void run() {
            while (RPLIDAR_A3.this.isRunning) {
                if (RPLIDAR_A3.this.isReceiveResponse) {
                    RPLIDAR_A3.this.parseData();
                } else {
                    RPLIDAR_A3 rplidar_a3 = RPLIDAR_A3.this;
                    rplidar_a3.isReceiveResponse = rplidar_a3.checkReceiveScanResponse();
                    if (this.timestamp == 0) {
                        this.timestamp = SystemClock.elapsedRealtime();
                    }
                    if (SystemClock.elapsedRealtime() - this.timestamp > 1000) {
                        Pdlog.m3277w("Lidar", "no more wait scan response protocol head");
                        RPLIDAR_A3.this.isReceiveResponse = true;
                    }
                }
            }
        }
    };

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

    private int getBitFromByte(byte b, int i) {
        return (b & (1 << i)) == 0 ? 0 : 1;
    }

    @Override // com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 256000;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] bArr) {
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
            int i = C47265.$SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3$ControlStep[this.step.ordinal()];
            if (i == 1) {
                lidarInterface.setDTR(false);
                this.step = ControlStep.START_MOTOR;
                this.controlHandler.postDelayed(this.controlRunnable, 500L);
                return;
            }
            if (i == 2) {
                lidarInterface.send(new byte[]{-91, -16, 2, -108, 2, -63});
                this.step = ControlStep.SCAN;
                this.controlHandler.postDelayed(this.controlRunnable, 200L);
                return;
            }
            if (i == 3) {
                loadConfig();
                if (this.curMode == RPLIDAR_A3_WORK_MODE.MODE_INDOOR) {
                    lidarInterface.send(new byte[]{-91, -126, 5, 3, 0, 0, 0, 0, BinaryMemcacheOpcodes.SASL_AUTH});
                }
                if (this.curMode == RPLIDAR_A3_WORK_MODE.MODE_OUTDOOR) {
                    lidarInterface.send(new byte[]{-91, -126, 5, 4, 0, 0, 0, 0, 38});
                }
                this.step = ControlStep.IDLE;
                return;
            }
            if (i == 4) {
                lidarInterface.send(new byte[]{-91, -16, 2, 0, 0, 87});
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

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3276v("Lidar", "startScan A3");
        this.isRunning = true;
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidar_a3.RPLIDAR_A3.2
            @Override // java.lang.Runnable
            public void run() {
                if (RPLIDAR_A3.this.step == ControlStep.IDLE || RPLIDAR_A3.this.step == ControlStep.STOP || RPLIDAR_A3.this.step == ControlStep.STOP_DTR) {
                    RPLIDAR_A3.this.controlHandler.removeCallbacks(RPLIDAR_A3.this.controlRunnable);
                    RPLIDAR_A3.this.step = ControlStep.START_DTR;
                    RPLIDAR_A3.this.controlFSM();
                }
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3276v("Lidar", "stopScan A3");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidar_a3.RPLIDAR_A3.3
            @Override // java.lang.Runnable
            public void run() {
                if (RPLIDAR_A3.this.step == ControlStep.IDLE || !(RPLIDAR_A3.this.step == ControlStep.STOP || RPLIDAR_A3.this.step == ControlStep.STOP_DTR)) {
                    RPLIDAR_A3.this.controlHandler.removeCallbacks(RPLIDAR_A3.this.controlRunnable);
                    RPLIDAR_A3.this.step = ControlStep.STOP;
                    RPLIDAR_A3.this.controlFSM();
                }
            }
        });
    }

    public RPLIDAR_A3_WORK_MODE getMode() {
        return this.curMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* renamed from: com.pudutech.lidar.rplidar_a3.RPLIDAR_A3$5 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C47265 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3$ControlStep;
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3_WORK_MODE;

        static {
            int[] iArr = new int[RPLIDAR_A3_WORK_MODE.values().length];
            $SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3_WORK_MODE = iArr;
            try {
                iArr[RPLIDAR_A3_WORK_MODE.MODE_INDOOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3_WORK_MODE[RPLIDAR_A3_WORK_MODE.MODE_OUTDOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[ControlStep.values().length];
            $SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3$ControlStep = iArr2;
            try {
                iArr2[ControlStep.START_DTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3$ControlStep[ControlStep.START_MOTOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3$ControlStep[ControlStep.SCAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3$ControlStep[ControlStep.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3$ControlStep[ControlStep.STOP_DTR.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public boolean switchMode(RPLIDAR_A3_WORK_MODE rplidar_a3_work_mode) {
        int i = C47265.$SwitchMap$com$pudutech$lidar$rplidar_a3$RPLIDAR_A3_WORK_MODE[rplidar_a3_work_mode.ordinal()];
        boolean z = false;
        if (i == 1 || i == 2) {
            z = saveConfig(rplidar_a3_work_mode.ordinal());
        } else {
            Pdlog.m3274e("Lidar", "wrong mode param!");
        }
        if (z) {
            this.curMode = rplidar_a3_work_mode;
            stopScan();
            startScan();
        }
        return z;
    }

    protected boolean loadConfig() {
        File file = new File(FilePath.RPLIDAR_A3_CONFIG);
        if (!file.exists()) {
            return false;
        }
        try {
            FileReader fileReader = new FileReader(file);
            char[] cArr = new char[(int) file.length()];
            fileReader.read(cArr);
            JSONObject jSONObject = new JSONObject(new String(cArr));
            Pdlog.m3276v("Lidar", "load " + jSONObject.toString());
            this.curMode = RPLIDAR_A3_WORK_MODE.values()[jSONObject.getInt("mode")];
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e("Lidar", "load error=" + e.toString());
            return false;
        }
    }

    protected boolean saveConfig(int i) {
        try {
            FileUtil.createOrExistsFile(FilePath.RPLIDAR_A3_CONFIG);
            FileWriter fileWriter = new FileWriter(FilePath.RPLIDAR_A3_CONFIG, false);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mode", i);
            fileWriter.write(jSONObject.toString());
            fileWriter.close();
            Pdlog.m3273d("Lidar", "save " + jSONObject.toString() + " set=" + i + " mode=" + this.curMode.ordinal());
            return loadConfig() && this.curMode.ordinal() == i;
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e("Lidar", "save error=" + e.toString());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkReceiveScanResponse() {
        return readOneBytesFromReceive() == -91 && readOneBytesFromReceive() == 90 && checkBytesSame(readBytesFromReceive(SCAN_RESPONSE_BEGIN.length), SCAN_RESPONSE_BEGIN);
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
        byte[] bArr = this.pre;
        if (bArr != null) {
            int i2 = -1;
            Iterator<RPLIDAR_A3_PROTOCOL.Node> it = this.RPLIDARA3PROTOCAL.test(bArr, this.now).iterator();
            while (it.hasNext()) {
                RPLIDAR_A3_PROTOCOL.Node next = it.next();
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
            Pdlog.m3276v("Lidar", "one package endAngle=" + i2);
            if (i2 > 0 && checkOneFrameComplete(i2)) {
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
                this.nodes = new ArrayList();
            }
        }
        this.pre = this.now;
        this.now = new byte[132];
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
            return true;
        }
        this.sumErrCnt++;
        Pdlog.m3277w("Lidar", "checkSum[" + ((int) b2) + "] != receiveCheckSum[" + ((int) b) + "] errCnt is " + this.sumErrCnt);
        return false;
    }

    private boolean checkBytesSame(byte[] bArr, byte[] bArr2) {
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

    private byte readOneBytesFromReceive() {
        try {
            return this.receive.take().byteValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Pdlog.m3274e("Lidar", "receive one fail: " + e.toString());
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
            }
        }
        return bArr;
    }
}
