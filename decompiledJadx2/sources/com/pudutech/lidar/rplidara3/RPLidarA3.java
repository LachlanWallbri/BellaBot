package com.pudutech.lidar.rplidara3;

import android.os.SystemClock;
import com.felhr.utils.HexData;
import com.pudutech.base.FilePath;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import com.pudutech.lidar.rplidara3.RPLidarA3Protocol;
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

/* loaded from: classes5.dex */
public class RPLidarA3 extends SerialLidar {
    private static final String MODE_KEY = "mode";
    private static final byte[] SCAN_RESPONSE_BEGIN = {Constans.CAN_REV_DIS_MODE_SWITCH, 0, 0, 64, Constans.CAN_REV_DIS_MODE_SWITCH};
    private static final String TAG = "Lidar";
    private Thread parseDataThread;
    private RPLidarA3WorkMode curMode = RPLidarA3WorkMode.MODE_INDOOR;
    private boolean isReceiveResponse = false;
    private boolean isRunning = false;
    private byte[] pre = null;
    private byte[] now = new byte[132];
    private RPLidarA3Protocol rpLidarA3Protocol = new RPLidarA3Protocol();
    private ArrayBlockingQueue<Byte> receive = new ArrayBlockingQueue<>(1024);
    private List<LidarNode> oneFrameNodes = null;
    private List<LidarNode> nodes = new ArrayList();
    private long sumErrCnt = 0;
    private ControlStep step = ControlStep.IDLE;
    private final Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.rplidara3.RPLidarA3.1
        @Override // java.lang.Runnable
        public void run() {
            RPLidarA3.this.controlFSM();
        }
    };
    private boolean isStartFlag = true;
    private int last = -1;
    private final Runnable runnable = new Runnable() { // from class: com.pudutech.lidar.rplidara3.RPLidarA3.4
        private long timestamp = 0;

        private boolean checkReceiveScanResponse() {
            if (RPLidarA3.this.readOneBytesFromReceive() == -91 && RPLidarA3.this.readOneBytesFromReceive() == 90) {
                return checkBytesSame(RPLidarA3.this.readBytesFromReceive(RPLidarA3.SCAN_RESPONSE_BEGIN.length), RPLidarA3.SCAN_RESPONSE_BEGIN);
            }
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

        @Override // java.lang.Runnable
        public void run() {
            while (RPLidarA3.this.isRunning) {
                if (RPLidarA3.this.isReceiveResponse) {
                    RPLidarA3.this.parseData();
                } else {
                    RPLidarA3.this.isReceiveResponse = checkReceiveScanResponse();
                    if (this.timestamp == 0) {
                        this.timestamp = SystemClock.elapsedRealtime();
                    }
                    if (SystemClock.elapsedRealtime() - this.timestamp > 1000) {
                        Pdlog.m3277w("Lidar", "no more wait scan response protocol head");
                        RPLidarA3.this.isReceiveResponse = true;
                    }
                }
            }
        }
    };

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

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] bArr) {
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
            int i = C47415.$SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3$ControlStep[this.step.ordinal()];
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
                if (this.curMode == RPLidarA3WorkMode.MODE_INDOOR) {
                    lidarInterface.send(new byte[]{-91, -126, 5, 3, 0, 0, 0, 0, BinaryMemcacheOpcodes.SASL_AUTH});
                }
                if (this.curMode == RPLidarA3WorkMode.MODE_OUTDOOR) {
                    lidarInterface.send(new byte[]{-91, -126, 5, 4, 0, 0, 0, 0, 38});
                }
                this.step = ControlStep.IDLE;
                return;
            }
            if (i == 4) {
                lidarInterface.send(new byte[]{-91, -16, 2, 0, 0, 87});
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

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3276v("Lidar", "startScan A3");
        this.isRunning = true;
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidara3.RPLidarA3.2
            @Override // java.lang.Runnable
            public void run() {
                if (RPLidarA3.this.step == ControlStep.IDLE || RPLidarA3.this.step == ControlStep.STOP || RPLidarA3.this.step == ControlStep.STOP_DTR) {
                    RPLidarA3.this.controlHandler.removeCallbacks(RPLidarA3.this.controlRunnable);
                    RPLidarA3.this.step = ControlStep.START_DTR;
                    RPLidarA3.this.controlFSM();
                }
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3276v("Lidar", "stopScan A3");
        this.controlHandler.post(new Runnable() { // from class: com.pudutech.lidar.rplidara3.RPLidarA3.3
            @Override // java.lang.Runnable
            public void run() {
                if (RPLidarA3.this.step == ControlStep.IDLE || !(RPLidarA3.this.step == ControlStep.STOP || RPLidarA3.this.step == ControlStep.STOP_DTR)) {
                    RPLidarA3.this.controlHandler.removeCallbacks(RPLidarA3.this.controlRunnable);
                    RPLidarA3.this.step = ControlStep.STOP;
                    RPLidarA3.this.controlFSM();
                }
            }
        });
    }

    public RPLidarA3WorkMode getMode() {
        return this.curMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.pudutech.lidar.rplidara3.RPLidarA3$5 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class C47415 {
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3$ControlStep;
        static final /* synthetic */ int[] $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3WorkMode = new int[RPLidarA3WorkMode.values().length];

        static {
            try {
                $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3WorkMode[RPLidarA3WorkMode.MODE_INDOOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3WorkMode[RPLidarA3WorkMode.MODE_OUTDOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3$ControlStep = new int[ControlStep.values().length];
            try {
                $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3$ControlStep[ControlStep.START_DTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3$ControlStep[ControlStep.START_MOTOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3$ControlStep[ControlStep.SCAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3$ControlStep[ControlStep.STOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3$ControlStep[ControlStep.STOP_DTR.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public boolean switchMode(RPLidarA3WorkMode rPLidarA3WorkMode) {
        int i = C47415.$SwitchMap$com$pudutech$lidar$rplidara3$RPLidarA3WorkMode[rPLidarA3WorkMode.ordinal()];
        boolean z = false;
        if (i == 1 || i == 2) {
            z = saveConfig(rPLidarA3WorkMode.ordinal());
        } else {
            Pdlog.m3274e("Lidar", "wrong mode param!");
        }
        if (z) {
            this.curMode = rPLidarA3WorkMode;
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
            try {
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                JSONObject jSONObject = new JSONObject(new String(cArr));
                Pdlog.m3276v("Lidar", "load " + jSONObject.toString());
                this.curMode = RPLidarA3WorkMode.values()[jSONObject.getInt("mode")];
                fileReader.close();
                return true;
            } finally {
            }
        } catch (Exception e) {
            Pdlog.m3274e("Lidar", "load error=" + e.toString());
            return false;
        }
    }

    protected boolean saveConfig(int i) {
        try {
            FileWriter fileWriter = new FileWriter(FilePath.RPLIDAR_A3_CONFIG, false);
            try {
                FileUtil.createOrExistsFile(FilePath.RPLIDAR_A3_CONFIG);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("mode", i);
                fileWriter.write(jSONObject.toString());
                Pdlog.m3273d("Lidar", "save " + jSONObject.toString() + " set=" + i + " mode=" + this.curMode.ordinal());
                fileWriter.close();
                return loadConfig() && this.curMode.ordinal() == i;
            } finally {
            }
        } catch (Exception e) {
            Pdlog.m3274e("Lidar", "save error=" + e.toString());
            return false;
        }
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
            Iterator<RPLidarA3Protocol.Node> it = this.rpLidarA3Protocol.test(bArr, this.now).iterator();
            while (it.hasNext()) {
                RPLidarA3Protocol.Node next = it.next();
                LidarNode obtain = LidarNode.obtain();
                obtain.dist = (int) (next.distanceMm / 4);
                obtain.angle = (next.angle * 90) / 16384;
                obtain.distanceM = (next.distanceMm / 4.0d) / 1000.0d;
                obtain.angleInRad = Math.toRadians((next.angle * 90) / 16384.0d) - 3.141592653589793d;
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
        this.nodes = new ArrayList();
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
