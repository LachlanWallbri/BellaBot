package com.pudutech.lidar.rplidar_50c;

import android.os.Handler;
import android.os.SystemClock;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.base.LidarListener;
import com.pudutech.lidar.base.SerialLidar;
import com.pudutech.lidar.base.SerialLidarInterface;
import com.pudutech.remotemaintenance.config.IoTConfig;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.apache.http.HttpStatus;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LDS_50C.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0016\u0018\u0000 =2\u00020\u0001:\u0001=B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u0002002\u0006\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u000200H\u0002J\u0010\u00105\u001a\u0002002\u0006\u00106\u001a\u00020\u000bH\u0016J\u0012\u00107\u001a\u0004\u0018\u00010\u000b2\u0006\u00108\u001a\u00020\u0004H\u0002J\b\u00109\u001a\u00020*H\u0002J\b\u0010:\u001a\u000200H\u0002J\b\u0010;\u001a\u000200H\u0016J\b\u0010<\u001a\u000200H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u001cj\b\u0012\u0004\u0012\u00020\u001d`\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"8V@TX\u0096\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010+\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"8V@TX\u0096\u000e¢\u0006\f\u001a\u0004\b,\u0010%\"\u0004\b-\u0010'R\u000e\u0010.\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, m3961d2 = {"Lcom/pudutech/lidar/rplidar_50c/LDS_50C;", "Lcom/pudutech/lidar/base/SerialLidar;", "()V", "baudRate", "", "getBaudRate", "()I", "controlRunnable", "Ljava/lang/Runnable;", "crcErrorCount", "dataHead", "", "getDataHead", "()[B", "setDataHead", "([B)V", "fanSpan", "isAdjustAngle", "", "()Z", "setAdjustAngle", "(Z)V", "isJobStart", "isRunning", "last", "mAngle", "mControlStep", "nodes", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/LidarNode;", "Lkotlin/collections/ArrayList;", "parserJob", "Lkotlinx/coroutines/Job;", ES6Iterator.VALUE_PROPERTY, "", "powerOnDelay_ms", "getPowerOnDelay_ms", "()J", "setPowerOnDelay_ms", "(J)V", "receive", "Ljava/util/concurrent/ArrayBlockingQueue;", "", "scanCMDValidInterval_ms", "getScanCMDValidInterval_ms", "setScanCMDValidInterval_ms", "startScanTimestamp", "controlFSM", "", "onReadFail", IoTConfig.PARAM_ERROR_MSG, "", "parseData", "parser", "src", "readBytesFromReceive", "num", "readOneBytesFromReceive", "startParserJob", "startScan", "stopScan", "Companion", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public class LDS_50C extends SerialLidar {
    public static final int CONTROL_STEP_IDLE = 0;
    public static final int CONTROL_STEP_MOTOR = 2;
    public static final int CONTROL_STEP_SCAN = 3;
    public static final int CONTROL_STEP_START_DTR = 1;
    public static final int CONTROL_STEP_STOP = 4;
    public static final int CONTROL_STEP_STOP_DTR = 5;
    public static final String TAG = "LDS_50C";
    private int crcErrorCount;
    private boolean isAdjustAngle;
    private boolean isJobStart;
    private boolean isRunning;
    private int mAngle;
    private int mControlStep;
    private Job parserJob;
    private byte[] dataHead = {(byte) HttpStatus.SC_PARTIAL_CONTENT, (byte) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION};
    private final Runnable controlRunnable = new Runnable() { // from class: com.pudutech.lidar.rplidar_50c.LDS_50C$controlRunnable$1
        @Override // java.lang.Runnable
        public final void run() {
            LDS_50C.this.controlFSM();
        }
    };
    private int last = -1;
    private long startScanTimestamp = -1;
    private ArrayBlockingQueue<Byte> receive = new ArrayBlockingQueue<>(1024);
    private ArrayList<LidarNode> nodes = new ArrayList<>();
    private int fanSpan = 360;

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
        return 100L;
    }

    protected byte[] getDataHead() {
        return this.dataHead;
    }

    protected void setDataHead(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.dataHead = bArr;
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
        Pdlog.m3275i(TAG, "controlFSM step=" + this.mControlStep);
        SerialLidarInterface lidarInterface = getLidarInterface();
        if (lidarInterface != null) {
            int i = this.mControlStep;
            if (i == 1) {
                lidarInterface.setDTR(false);
                this.mControlStep = 2;
                getControlHandler().postDelayed(this.controlRunnable, 100L);
                return;
            }
            if (i == 2) {
                lidarInterface.send(new byte[]{(byte) 165, (byte) 130, 5, 0, 0, 0, 0, 0, 34});
                this.mControlStep = 3;
                getControlHandler().postDelayed(this.controlRunnable, 200L);
            } else if (i == 3) {
                byte b = (byte) 2;
                lidarInterface.send(new byte[]{(byte) 165, (byte) 168, b, (byte) 88, b, (byte) 238});
                this.mControlStep = 0;
            } else if (i == 4) {
                lidarInterface.send(new byte[]{(byte) 165, (byte) 37});
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
            startParserJob();
            for (byte b : src) {
                if (7 < src.length && src.length > 8 && src[0] == ((byte) 83) && src[1] == ((byte) 84) && src[6] == ((byte) 69) && src[7] == ((byte) 68)) {
                    byte b2 = src[2];
                    this.fanSpan = 360;
                    if ((b2 & 16) > 0) {
                        this.fanSpan = 180;
                    }
                    if ((b2 & 32) > 0) {
                        this.fanSpan = 90;
                    }
                    Pdlog.m3273d(TAG, "find fanSpan=" + this.fanSpan);
                }
                try {
                    this.receive.offer(Byte.valueOf(b), 10L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Pdlog.m3274e(TAG, "parser error: " + e);
                }
            }
        }
    }

    private final void startParserJob() {
        Job launch$default;
        if (this.isJobStart) {
            return;
        }
        this.isJobStart = true;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LDS_50C$startParserJob$1(this, null), 3, null);
        this.parserJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void parseData() {
        if (readOneBytesFromReceive() == getDataHead()[0] && readOneBytesFromReceive() == getDataHead()[1]) {
            int readOneBytesFromReceive = (readOneBytesFromReceive() & 255) | ((readOneBytesFromReceive() & 255) << 8);
            int i = readOneBytesFromReceive * 3;
            int readOneBytesFromReceive2 = (readOneBytesFromReceive() & 255) | ((readOneBytesFromReceive() & 255) << 8);
            if (readOneBytesFromReceive2 % 90 != 0) {
                Pdlog.m3274e(getTAG(), "not 90");
                return;
            }
            this.mAngle = readOneBytesFromReceive2;
            int readOneBytesFromReceive3 = getIsAdjustAngle() ? (readOneBytesFromReceive() & 255) | ((readOneBytesFromReceive() & 255) << 8) : 0;
            byte[] readBytesFromReceive = readBytesFromReceive(i);
            int readOneBytesFromReceive4 = (readOneBytesFromReceive() & 255) | ((readOneBytesFromReceive() & 255) << 8);
            int i2 = readOneBytesFromReceive2 + readOneBytesFromReceive + readOneBytesFromReceive3;
            this.fanSpan = readOneBytesFromReceive2 == 3420 ? this.fanSpan * 2 : this.fanSpan;
            if (!getIsAdjustAngle()) {
                readOneBytesFromReceive3 = this.fanSpan;
            }
            for (int i3 = 0; i3 < i; i3 += 3) {
                LidarNode lidarNode = new LidarNode();
                if (readBytesFromReceive == null) {
                    Intrinsics.throwNpe();
                }
                int i4 = ((readBytesFromReceive[i3 + 2] & 255) << 8) | (readBytesFromReceive[i3 + 1] & 255);
                i2 += readBytesFromReceive[i3] + i4;
                lidarNode.quality = readBytesFromReceive[i3];
                lidarNode.distance_m = i4 / 1000.0d;
                lidarNode.angleInRad = ((readOneBytesFromReceive2 + ((readOneBytesFromReceive3 * (i3 / 3.0d)) / readOneBytesFromReceive)) * 3.141592653589793d) / 1800.0d;
                this.nodes.add(lidarNode);
            }
            int i5 = 65535 & i2;
            if (readOneBytesFromReceive4 != i5) {
                Pdlog.m3277w(TAG, "crc error please check ,checksum=" + readOneBytesFromReceive4 + ",sum = " + i5);
                int i6 = this.crcErrorCount + 1;
                this.crcErrorCount = i6;
                if (i6 >= 10) {
                    LidarListener listener = getListener();
                    if (listener != null) {
                        listener.onProtocolError("protocol error, checkSum has occur " + this.crcErrorCount);
                    }
                    this.crcErrorCount = 0;
                    return;
                }
                return;
            }
            this.crcErrorCount = 0;
            if (this.mAngle + this.fanSpan != 1800) {
                return;
            }
            LidarListener listener2 = getListener();
            if (listener2 != null) {
                if (this.nodes.isEmpty()) {
                    Pdlog.m3277w(TAG, "empty frame");
                    listener2.onProtocolError("empty frame");
                    return;
                } else {
                    listener2.onOneFrameComplete(this.nodes);
                    this.nodes = new ArrayList<>();
                    return;
                }
            }
            Pdlog.m3277w(TAG, "lidarCallback is null");
            this.nodes.clear();
        }
    }

    private final byte[] readBytesFromReceive(int num) {
        byte[] bArr = new byte[num];
        for (int i = 0; i < num; i++) {
            try {
                Byte take = this.receive.take();
                Intrinsics.checkExpressionValueIsNotNull(take, "receive.take()");
                bArr[i] = take.byteValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Pdlog.m3274e(TAG, "receive " + num + " fail: " + e);
                onReadFail("readBytesFromReceive " + num + " fail: " + e.getMessage());
            }
        }
        return bArr;
    }

    private final byte readOneBytesFromReceive() {
        try {
            Byte take = this.receive.take();
            Intrinsics.checkExpressionValueIsNotNull(take, "receive.take()");
            return take.byteValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "receive one fail: " + e);
            onReadFail("readOneBytesFromReceive fail: " + e.getMessage());
            return (byte) 0;
        }
    }

    private final void onReadFail(String errorMsg) {
        LidarListener listener = getListener();
        if (listener != null) {
            listener.onProtocolError(errorMsg);
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void startScan() {
        Pdlog.m3273d(TAG, "startScan s1");
        this.last = -1;
        this.isRunning = true;
        this.crcErrorCount = 0;
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.rplidar_50c.LDS_50C$startScan$1
            @Override // java.lang.Runnable
            public final void run() {
                int i;
                Runnable runnable;
                int i2;
                int i3;
                i = LDS_50C.this.mControlStep;
                if (i != 0) {
                    i2 = LDS_50C.this.mControlStep;
                    if (i2 != 4) {
                        i3 = LDS_50C.this.mControlStep;
                        if (i3 != 5) {
                            return;
                        }
                    }
                }
                Handler controlHandler = LDS_50C.this.getControlHandler();
                runnable = LDS_50C.this.controlRunnable;
                controlHandler.removeCallbacks(runnable);
                LDS_50C.this.startScanTimestamp = SystemClock.elapsedRealtime();
                LDS_50C.this.mControlStep = 1;
                LDS_50C.this.controlFSM();
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    public void stopScan() {
        Pdlog.m3273d(TAG, "stopScan s1");
        this.isRunning = false;
        this.last = -1;
        this.crcErrorCount = 0;
        if (this.isJobStart) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new LDS_50C$stopScan$1(this, null), 3, null);
        }
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.rplidar_50c.LDS_50C$stopScan$2
            @Override // java.lang.Runnable
            public final void run() {
                int i;
                Runnable runnable;
                int i2;
                int i3;
                i = LDS_50C.this.mControlStep;
                if (i != 0) {
                    i2 = LDS_50C.this.mControlStep;
                    if (i2 == 4) {
                        return;
                    }
                    i3 = LDS_50C.this.mControlStep;
                    if (i3 == 5) {
                        return;
                    }
                }
                Handler controlHandler = LDS_50C.this.getControlHandler();
                runnable = LDS_50C.this.controlRunnable;
                controlHandler.removeCallbacks(runnable);
                LDS_50C.this.mControlStep = 4;
                LDS_50C.this.controlFSM();
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    protected void setPowerOnDelay_ms(long j) {
        setPowerOnDelay_ms(2000L);
    }

    @Override // com.pudutech.lidar.base.BaseLidar
    protected void setScanCMDValidInterval_ms(long j) {
        setScanCMDValidInterval_ms(100L);
    }
}
