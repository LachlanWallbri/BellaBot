package com.pudutech.lidar.rplidar50c;

import android.os.Handler;
import android.os.SystemClock;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.felhr.utils.HexData;
import com.iflytek.cloud.SpeechEvent;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.LidarDeviceInfo;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import com.pudutech.lidar.rplidar_50c.LDS_50C;
import com.pudutech.lidar.util.NumberExtKt;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import com.pudutech.remotemaintenance.config.IoTConfig;
import java.nio.ByteBuffer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.http.HttpStatus;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Lds50c.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0016\u0018\u0000 Y2\u00020\u0001:\u0001YB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0012H\u0002J\u0010\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020%H\u0002J\b\u0010E\u001a\u00020AH\u0002J\b\u0010F\u001a\u00020AH\u0002J\b\u0010G\u001a\u00020HH\u0016J\u0010\u0010I\u001a\u00020A2\u0006\u0010J\u001a\u00020\u0004H\u0002J\b\u0010K\u001a\u00020AH\u0002J\u0010\u0010L\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0012H\u0002J\u0010\u0010M\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0012H\u0016J\u0010\u0010N\u001a\u00020A2\u0006\u0010O\u001a\u00020\fH\u0002J:\u0010P\u001a\u00020\b2\u0006\u0010Q\u001a\u00020\b2\b\u0010R\u001a\u0004\u0018\u00010\u00122\u0006\u0010S\u001a\u00020\b2\u0006\u0010T\u001a\u00020\b2\u0006\u0010U\u001a\u00020\b2\u0006\u0010V\u001a\u00020\bH\u0014J\b\u0010W\u001a\u00020AH\u0016J\b\u0010X\u001a\u00020AH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0012X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u000e\u0010\u001a\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0016\u0010*\u001a\n +*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020/X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00106\u001a\u0002052\u0006\u00104\u001a\u0002058V@TX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R$\u0010;\u001a\u0002052\u0006\u00104\u001a\u0002058V@TX\u0096\u000e¢\u0006\f\u001a\u0004\b<\u00108\"\u0004\b=\u0010:R\u000e\u0010>\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, m3961d2 = {"Lcom/pudutech/lidar/rplidar50c/Lds50c;", "Lcom/pudutech/lidar/base/SerialLidar;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "baudRate", "", "getBaudRate", "()I", "checkBuffer", "Ljava/nio/ByteBuffer;", "continuouslyDataHeadErrorCount", "controlRunnable", "Ljava/lang/Runnable;", "crcErrorCount", "dataHead", "", "getDataHead", "()[B", "setDataHead", "([B)V", "errorFrameHead", "getErrorFrameHead", "setErrorFrameHead", "fanSpan", "isAdjustAngle", "", "()Z", "setAdjustAngle", "(Z)V", "isErrorFrame", "isGettingSN", "isRunning", "last", "lastAngle", "", "getLastAngle", "()D", "setLastAngle", "(D)V", "lidarDataBuffer", "kotlin.jvm.PlatformType", "mAngle", "mControlStep", "nodes", "Lcom/pudutech/lidar/ListLidarNodePool;", "getNodes", "()Lcom/pudutech/lidar/ListLidarNodePool;", "setNodes", "(Lcom/pudutech/lidar/ListLidarNodePool;)V", ES6Iterator.VALUE_PROPERTY, "", "powerOnDelay_ms", "getPowerOnDelay_ms", "()J", "setPowerOnDelay_ms", "(J)V", "scanCMDValidInterval_ms", "getScanCMDValidInterval_ms", "setScanCMDValidInterval_ms", "snCurrentTime", "startScanTimestamp", "checkGettingSNResponse", "", "src", "checkOneFrameComplete", "degree", "controlFSM", "fixReceiveState", "getLidarInfo", "Lcom/pudutech/lidar/base/LidarDeviceInfo;", "onReadFail", IoTConfig.PARAM_ERROR_MSG, "oneFrameComplete", "parseLidarData", "parser", "reLoadData", SpeechEvent.KEY_EVENT_TTS_BUFFER, "relParser", "lidarLen", "data", "sum", "angle", "span", "len", "startScan", "stopScan", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public class Lds50c extends SerialLidar {
    private static final int CAPACITY = 1024;
    public static final int CONTROL_STEP_IDLE = 0;
    public static final int CONTROL_STEP_MOTOR = 2;
    public static final int CONTROL_STEP_SCAN = 3;
    public static final int CONTROL_STEP_START_DTR = 1;
    public static final int CONTROL_STEP_STOP = 4;
    public static final int CONTROL_STEP_STOP_DTR = 5;
    public static final long GET_SN_TIME_OUT = 5000;
    private static final int HEAD_LENGTH = 4;
    private static final int MAX_HEADER_ERROR_TIMES = 20;
    private static final int PAGE_SIZE = 512;
    private static final long SCAN_DELAY_TIME = 1000;
    private static final long START_MOTOR_DELAY_TIME = 100;
    private static final long STOP_MOTOR_DELAY_TIME = 500;
    private static int snBodyCount;
    private ByteBuffer checkBuffer;
    private int continuouslyDataHeadErrorCount;
    private final Runnable controlRunnable;
    private int crcErrorCount;
    private byte[] dataHead;
    private byte[] errorFrameHead;
    private int fanSpan;
    private boolean isAdjustAngle;
    private boolean isErrorFrame;
    private boolean isGettingSN;
    private boolean isRunning;
    private int last;
    private double lastAngle;
    private final ByteBuffer lidarDataBuffer;
    private int mAngle;
    private int mControlStep;
    private ListLidarNodePool nodes;
    private long snCurrentTime;
    private long startScanTimestamp;
    private static final int SN_BODY_LONG = 11;
    private static final byte[] snBody = new byte[SN_BODY_LONG];

    /* renamed from: sn */
    private static String f5465sn = "";
    private static final byte[] SN_COMMAND = {TarConstants.LF_GNUTYPE_LONGNAME, 85, 85, PrinterUtils.BarCode.CODE128, 68, PrinterUtils.BarCode.CODE93};
    private static final byte[] CHECK_HEAD = {(byte) 80, (byte) 82, (byte) 79, (byte) 68};

    @Override // com.pudutech.lidar.base.SerialLidar
    public int getBaudRate() {
        return 500000;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public long getPowerOnDelay_ms() {
        return 2000L;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public long getScanCMDValidInterval_ms() {
        return START_MOTOR_DELAY_TIME;
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public String getTAG() {
        return LDS_50C.TAG;
    }

    public Lds50c() {
        byte b = (byte) HttpStatus.SC_PARTIAL_CONTENT;
        this.dataHead = new byte[]{b, (byte) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION};
        this.errorFrameHead = new byte[]{b, b};
        this.controlRunnable = new Runnable() { // from class: com.pudutech.lidar.rplidar50c.Lds50c$controlRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                Lds50c.this.controlFSM();
            }
        };
        this.last = -1;
        this.startScanTimestamp = -1L;
        this.nodes = ListLidarNodePool.INSTANCE.obtain();
        this.fanSpan = 360;
        this.lidarDataBuffer = ByteBuffer.allocate(1024);
        this.lastAngle = -1.0d;
    }

    protected byte[] getDataHead() {
        return this.dataHead;
    }

    protected void setDataHead(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.dataHead = bArr;
    }

    protected byte[] getErrorFrameHead() {
        return this.errorFrameHead;
    }

    protected void setErrorFrameHead(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.errorFrameHead = bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ListLidarNodePool getNodes() {
        return this.nodes;
    }

    protected final void setNodes(ListLidarNodePool listLidarNodePool) {
        Intrinsics.checkParameterIsNotNull(listLidarNodePool, "<set-?>");
        this.nodes = listLidarNodePool;
    }

    /* renamed from: isAdjustAngle, reason: from getter */
    protected boolean getIsAdjustAngle() {
        return this.isAdjustAngle;
    }

    protected void setAdjustAngle(boolean z) {
        this.isAdjustAngle = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void controlFSM() {
        Pdlog.m3275i(getTAG(), "controlFSM step=" + this.mControlStep);
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            int i = this.mControlStep;
            if (i == 1) {
                lidarInterface.setDTR(false);
                this.mControlStep = 2;
                Pdlog.m3275i(getTAG(), "setup serial finish");
                getControlHandler().postDelayed(this.controlRunnable, START_MOTOR_DELAY_TIME);
                return;
            }
            if (i == 2) {
                lidarInterface.send(new byte[]{(byte) 165, (byte) 130, 5, 0, 0, 0, 0, 0, 34});
                this.mControlStep = 3;
                getControlHandler().postDelayed(this.controlRunnable, 1000L);
                return;
            }
            if (i == 3) {
                Pdlog.m3275i(getTAG(), "start motor");
                lidarInterface.send(new byte[]{TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_GNUTYPE_SPARSE, 84, 65, 82, PrinterUtils.BarCode.CODE93});
                lidarInterface.onStepScan();
                this.mControlStep = 0;
                return;
            }
            if (i == 4) {
                lidarInterface.send(new byte[]{TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_GNUTYPE_SPARSE, 84, 79, 80, PrinterUtils.BarCode.CODE93});
                this.mControlStep = 5;
                getControlHandler().postDelayed(this.controlRunnable, 500L);
            } else {
                if (i != 5) {
                    return;
                }
                lidarInterface.setDTR(true);
                this.mControlStep = 0;
            }
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void parser(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        if (this.isRunning) {
            int i = 0;
            if (src.length > 8) {
                if (src[0] == ((byte) 83) && src[1] == ((byte) 84) && src[6] == ((byte) 69) && src[7] == ((byte) 68)) {
                    byte b = src[2];
                    this.fanSpan = 360;
                    if ((b & 16) > 0) {
                        this.fanSpan = 180;
                    }
                    if ((b & 32) > 0) {
                        this.fanSpan = 90;
                    }
                    Pdlog.m3273d(getTAG(), "find fanSpan=" + this.fanSpan);
                }
            }
            int length = src.length;
            while (length > 0) {
                int i2 = i * 512;
                byte[] copyOfRange = (i != 0 || length >= 512) ? ArraysKt.copyOfRange(src, i2, Math.min(512, length) + i2) : src;
                length -= 512;
                i++;
                if (this.isGettingSN) {
                    checkGettingSNResponse(copyOfRange);
                } else {
                    parseLidarData(copyOfRange);
                }
            }
        }
    }

    private final void checkGettingSNResponse(byte[] src) {
        if (this.checkBuffer == null) {
            Pdlog.m3273d(getTAG(), "start checkGettingSNResponse");
            this.checkBuffer = ByteBuffer.allocate(1024);
            this.snCurrentTime = 0L;
        }
        if (this.snCurrentTime == 0) {
            this.snCurrentTime = SystemClock.elapsedRealtime();
        }
        ByteBuffer byteBuffer = this.checkBuffer;
        if (byteBuffer != null) {
            byteBuffer.put(src);
            byteBuffer.flip();
            if (byteBuffer.remaining() < 4) {
                byteBuffer.position(byteBuffer.limit());
                byteBuffer.limit(byteBuffer.capacity());
                fixReceiveState();
                return;
            }
            while (true) {
                if (byteBuffer.remaining() < 4) {
                    break;
                }
                int i = 0;
                while (i < 4 && CHECK_HEAD[i] == byteBuffer.get()) {
                    i++;
                }
                if (i != 4) {
                    if (byteBuffer.hasRemaining()) {
                        reLoadData(byteBuffer);
                        byteBuffer.flip();
                        fixReceiveState();
                    } else {
                        byteBuffer.clear();
                        fixReceiveState();
                        return;
                    }
                } else {
                    if (byteBuffer.hasRemaining() && byteBuffer.remaining() >= 18) {
                        byteBuffer.get(new byte[7]);
                        byte[] bArr = new byte[11];
                        byteBuffer.get(bArr);
                        setSerialNum(new String(bArr, Charsets.UTF_8));
                        Pdlog.m3273d(getTAG(), "serialNum=" + getSerialNum());
                        f5465sn = getSerialNum();
                        this.isGettingSN = false;
                        fixReceiveState();
                        return;
                    }
                    byteBuffer.position(0);
                }
            }
            reLoadData(byteBuffer);
        }
    }

    private final void reLoadData(ByteBuffer buffer) {
        if (buffer.remaining() == 0) {
            buffer.clear();
            return;
        }
        byte[] bArr = new byte[buffer.remaining()];
        buffer.get(bArr);
        buffer.position(0);
        buffer.limit(buffer.capacity());
        buffer.put(new byte[buffer.capacity()]);
        buffer.position(0);
        buffer.limit(buffer.capacity());
        buffer.put(bArr);
    }

    private final void fixReceiveState() {
        if (SystemClock.elapsedRealtime() - this.snCurrentTime > 5000) {
            Pdlog.m3273d(getTAG(), "get sn time out");
            this.isGettingSN = false;
        } else {
            Pdlog.m3273d(getTAG(), "get sn time " + (SystemClock.elapsedRealtime() - this.snCurrentTime));
        }
        if (this.isGettingSN) {
            return;
        }
        this.lidarDataBuffer.clear();
        if (!this.nodes.isEmpty()) {
            this.nodes.clearAllNode();
        }
        this.checkBuffer = (ByteBuffer) null;
    }

    private final void parseLidarData(byte[] src) {
        ByteBuffer byteBuffer = this.lidarDataBuffer;
        if (byteBuffer != null) {
            if (src.length > 2 && src[0] == getDataHead()[0] && src[1] == getDataHead()[1] && byteBuffer.position() < 2) {
                byteBuffer.clear();
            }
            byteBuffer.put(src);
            byteBuffer.flip();
            if (byteBuffer.remaining() < 2) {
                byteBuffer.position(byteBuffer.limit());
                byteBuffer.limit(byteBuffer.capacity());
                return;
            }
            boolean z = false;
            while (byteBuffer.remaining() >= 2) {
                byte b = byteBuffer.get();
                if (b != getDataHead()[0]) {
                    if (!z) {
                        Pdlog.m3277w(getTAG(), "protocol error, sync1 must be " + HexData.hexToString(new byte[]{getDataHead()[0]}) + ", byte1=" + HexData.hexToString(new byte[]{b}));
                    }
                    this.continuouslyDataHeadErrorCount++;
                    if (this.continuouslyDataHeadErrorCount > 8) {
                        z = false;
                    }
                    if (this.continuouslyDataHeadErrorCount > 20) {
                        Pdlog.m3274e(getTAG(), "data head has appeared over more times");
                        LidarListener listener = getListener();
                        if (listener != null) {
                            listener.onProtocolError("data head error");
                        }
                        this.continuouslyDataHeadErrorCount = 0;
                    }
                    if (byteBuffer.hasRemaining()) {
                        reLoadData(byteBuffer);
                        byteBuffer.flip();
                    } else {
                        byteBuffer.clear();
                        return;
                    }
                } else {
                    byte b2 = byteBuffer.get();
                    if (b2 == getErrorFrameHead()[1]) {
                        this.isErrorFrame = true;
                    } else if (b2 == getDataHead()[1]) {
                        this.isErrorFrame = false;
                    } else {
                        if (!z) {
                            Pdlog.m3277w(getTAG(), "protocol error, sync2 must be " + HexData.hexToString(new byte[]{getDataHead()[1]}) + ", byte2=" + HexData.hexToString(new byte[]{b2}));
                        }
                        this.continuouslyDataHeadErrorCount++;
                        if (this.continuouslyDataHeadErrorCount > 20) {
                            Pdlog.m3274e(getTAG(), "data head has appeared over more times");
                            LidarListener listener2 = getListener();
                            if (listener2 != null) {
                                listener2.onProtocolError("data head error");
                            }
                            this.continuouslyDataHeadErrorCount = 0;
                        }
                        if (byteBuffer.hasRemaining()) {
                            reLoadData(byteBuffer);
                            byteBuffer.flip();
                        } else {
                            byteBuffer.clear();
                            return;
                        }
                    }
                    if (this.isErrorFrame) {
                        if (byteBuffer.remaining() < 4) {
                            byteBuffer.position(byteBuffer.limit());
                            byteBuffer.limit(byteBuffer.capacity());
                            return;
                        }
                        byte[] bArr = new byte[4];
                        byteBuffer.get(bArr);
                        Pdlog.m3274e(getTAG(), "get ErrorFrame " + NumberExtKt.toHexString(bArr));
                        this.isErrorFrame = false;
                        reLoadData(byteBuffer);
                        byteBuffer.flip();
                    } else {
                        if (this.continuouslyDataHeadErrorCount > 20) {
                            Pdlog.m3274e(getTAG(), "data head has appeared over more times");
                            LidarListener listener3 = getListener();
                            if (listener3 != null) {
                                listener3.onProtocolError("data head error");
                            }
                            this.continuouslyDataHeadErrorCount = 0;
                        }
                        if (byteBuffer.hasRemaining() && byteBuffer.remaining() >= 2) {
                            int i = ((byteBuffer.get() & 255) << 8) | (byteBuffer.get() & 255);
                            int i2 = i * (getIsAdjustAngle() ? 2 : 3);
                            if (i == 0 || i > 150) {
                                Pdlog.m3277w(getTAG(), "protocol error,len is " + i);
                                if (byteBuffer.hasRemaining()) {
                                    reLoadData(byteBuffer);
                                    byteBuffer.flip();
                                } else {
                                    byteBuffer.clear();
                                    return;
                                }
                            } else if (byteBuffer.remaining() > 2) {
                                int i3 = ((byteBuffer.get() & 255) << 8) | (byteBuffer.get() & 255);
                                if (i3 % 90 != 0) {
                                    Pdlog.m3277w(getTAG(), "protocol error, angle = " + i3 + ", angle % 90 != 0");
                                    if (byteBuffer.hasRemaining()) {
                                        reLoadData(byteBuffer);
                                        byteBuffer.flip();
                                    } else {
                                        byteBuffer.clear();
                                        return;
                                    }
                                } else {
                                    this.mAngle = i3;
                                    int i4 = (!getIsAdjustAngle() || byteBuffer.remaining() < 2) ? 0 : (byteBuffer.get() & 255) | ((byteBuffer.get() & 255) << 8);
                                    int i5 = i2 + 2;
                                    if (byteBuffer.remaining() >= i5) {
                                        byte[] bArr2 = new byte[i2];
                                        byteBuffer.get(bArr2);
                                        int i6 = ((byteBuffer.get() & 255) << 8) | (byteBuffer.get() & 255);
                                        int i7 = i3 + i + i4;
                                        if (!getIsAdjustAngle()) {
                                            i4 = this.fanSpan;
                                        }
                                        int relParser = relParser(i2, bArr2, i7, i3, i4, i) & 65535;
                                        if (i6 != relParser) {
                                            Pdlog.m3277w(getTAG(), "crc error please check,startAngle = " + i3 + ",crcErrorCount = " + this.crcErrorCount + ",checksum= " + i6 + ",sum = " + relParser);
                                            this.crcErrorCount = this.crcErrorCount + 1;
                                            if (this.crcErrorCount >= 5) {
                                                LidarListener listener4 = getListener();
                                                if (listener4 != null) {
                                                    listener4.onProtocolError("crc error, checkSum has occur " + this.crcErrorCount);
                                                }
                                                this.crcErrorCount = 0;
                                            }
                                            if (byteBuffer.hasRemaining()) {
                                                reLoadData(byteBuffer);
                                                byteBuffer.flip();
                                            } else {
                                                byteBuffer.clear();
                                                return;
                                            }
                                        } else {
                                            this.crcErrorCount = 0;
                                            Pdlog.m3273d(getTAG(), "Last node " + ((LidarNode) CollectionsKt.last((List) this.nodes)).angleInRad);
                                            if (!checkOneFrameComplete(Math.toDegrees(((LidarNode) CollectionsKt.last((List) this.nodes)).angleInRad))) {
                                                if (byteBuffer.hasRemaining()) {
                                                    reLoadData(byteBuffer);
                                                    byteBuffer.flip();
                                                } else {
                                                    byteBuffer.clear();
                                                    return;
                                                }
                                            } else {
                                                oneFrameComplete();
                                                if (byteBuffer.hasRemaining()) {
                                                    reLoadData(byteBuffer);
                                                    byteBuffer.flip();
                                                } else {
                                                    byteBuffer.clear();
                                                    return;
                                                }
                                            }
                                            z = true;
                                        }
                                    } else {
                                        Pdlog.m3277w(getTAG(), "not over size of data " + i5);
                                    }
                                }
                            }
                        }
                        byteBuffer.position(0);
                        break;
                    }
                }
            }
            reLoadData(this.lidarDataBuffer);
        }
    }

    private final void oneFrameComplete() {
        LidarListener listener = getListener();
        if (listener != null) {
            if (this.nodes.isEmpty()) {
                Pdlog.m3277w(getTAG(), "empty frame");
                listener.onProtocolError("empty frame");
                return;
            }
            ListLidarNodePool listLidarNodePool = this.nodes;
            Pdlog.m3273d(getTAG(), "send one frame , nodes size = " + this.nodes.size());
            listener.onOneFrameComplete(listLidarNodePool);
            this.nodes = ListLidarNodePool.INSTANCE.obtain();
            this.continuouslyDataHeadErrorCount = 0;
            return;
        }
        Pdlog.m3277w(getTAG(), "lidarCallback is null");
        this.nodes.clearAllNode();
    }

    protected int relParser(int lidarLen, byte[] data, int sum, int angle, int span, int len) {
        int i = sum;
        for (int i2 = 0; i2 < lidarLen; i2 += 3) {
            LidarNode obtain = LidarNode.obtain();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            int i3 = ((data[i2 + 2] & 255) << 8) | (data[i2 + 1] & 255);
            i += (data[i2] & 255) + i3;
            obtain.reflectivity = data[i2] & 255;
            obtain.distanceM = i3 / 1000.0d;
            obtain.angleInRad = ((angle + ((span * (i2 / 3.0d)) / len)) * 3.141592653589793d) / 1800.0d;
            obtain.angleInRad = obtain.angleInRad >= 3.141592653589793d ? obtain.angleInRad - 3.141592653589793d : obtain.angleInRad + 3.141592653589793d;
            this.nodes.add(obtain);
        }
        return i;
    }

    public final double getLastAngle() {
        return this.lastAngle;
    }

    public final void setLastAngle(double d) {
        this.lastAngle = d;
    }

    private final boolean checkOneFrameComplete(double degree) {
        Pdlog.m3273d(getTAG(), "checkOneFrameComplete : degree " + degree + ", lastAngle " + this.lastAngle);
        boolean z = degree < this.lastAngle;
        this.lastAngle = degree;
        return z;
    }

    private final void onReadFail(String errorMsg) {
        LidarListener listener = getListener();
        if (listener != null) {
            listener.onProtocolError(errorMsg);
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        boolean z = true;
        Pdlog.m3273d(getTAG(), "startScan s1");
        this.isRunning = true;
        this.isGettingSN = true;
        this.last = -1;
        if (getSerialNum() != null) {
            if (!(getSerialNum().length() == 0)) {
                z = false;
                this.isGettingSN = z;
                this.crcErrorCount = 0;
                getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.rplidar50c.Lds50c$startScan$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        int i;
                        Runnable runnable;
                        int i2;
                        int i3;
                        i = Lds50c.this.mControlStep;
                        if (i != 0) {
                            i2 = Lds50c.this.mControlStep;
                            if (i2 != 4) {
                                i3 = Lds50c.this.mControlStep;
                                if (i3 != 5) {
                                    return;
                                }
                            }
                        }
                        Handler controlHandler = Lds50c.this.getControlHandler();
                        runnable = Lds50c.this.controlRunnable;
                        controlHandler.removeCallbacks(runnable);
                        Lds50c.this.startScanTimestamp = SystemClock.elapsedRealtime();
                        Lds50c.this.mControlStep = 1;
                        Lds50c.this.controlFSM();
                    }
                });
            }
        }
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            lidarInterface.send(SN_COMMAND);
        }
        this.isGettingSN = z;
        this.crcErrorCount = 0;
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.rplidar50c.Lds50c$startScan$1
            @Override // java.lang.Runnable
            public final void run() {
                int i;
                Runnable runnable;
                int i2;
                int i3;
                i = Lds50c.this.mControlStep;
                if (i != 0) {
                    i2 = Lds50c.this.mControlStep;
                    if (i2 != 4) {
                        i3 = Lds50c.this.mControlStep;
                        if (i3 != 5) {
                            return;
                        }
                    }
                }
                Handler controlHandler = Lds50c.this.getControlHandler();
                runnable = Lds50c.this.controlRunnable;
                controlHandler.removeCallbacks(runnable);
                Lds50c.this.startScanTimestamp = SystemClock.elapsedRealtime();
                Lds50c.this.mControlStep = 1;
                Lds50c.this.controlFSM();
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3273d(getTAG(), "stopScan s1");
        this.isRunning = false;
        this.last = -1;
        this.crcErrorCount = 0;
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.rplidar50c.Lds50c$stopScan$1
            @Override // java.lang.Runnable
            public final void run() {
                int i;
                Runnable runnable;
                int i2;
                int i3;
                i = Lds50c.this.mControlStep;
                if (i != 0) {
                    i2 = Lds50c.this.mControlStep;
                    if (i2 == 4) {
                        return;
                    }
                    i3 = Lds50c.this.mControlStep;
                    if (i3 == 5) {
                        return;
                    }
                }
                Handler controlHandler = Lds50c.this.getControlHandler();
                runnable = Lds50c.this.controlRunnable;
                controlHandler.removeCallbacks(runnable);
                Lds50c.this.mControlStep = 4;
                Lds50c.this.controlFSM();
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    protected void setPowerOnDelay_ms(long j) {
        setPowerOnDelay_ms(2000L);
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    protected void setScanCMDValidInterval_ms(long j) {
        setScanCMDValidInterval_ms(START_MOTOR_DELAY_TIME);
    }

    public LidarDeviceInfo getLidarInfo() {
        return new LidarDeviceInfo(LidarVersion.LDS_50C.name(), f5465sn);
    }
}
