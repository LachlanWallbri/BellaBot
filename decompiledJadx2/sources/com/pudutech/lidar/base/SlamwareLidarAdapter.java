package com.pudutech.lidar.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarAdapterCallback;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.lidar.base.SlamwareLidarAdapter;
import com.slamtec.slamware.AbstractSlamwarePlatform;
import com.slamtec.slamware.discovery.DeviceManager;
import com.slamtec.slamware.robot.LaserPoint;
import com.slamtec.slamware.robot.LaserScan;
import java.util.Vector;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: SlamwareLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001-B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020&H\u0016J\b\u0010(\u001a\u00020&H\u0016J\b\u0010)\u001a\u00020&H\u0016J\b\u0010*\u001a\u00020&H\u0016J\u0010\u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020\u000eH\u0002R\u0014\u0010\t\u001a\u00020\nX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\b\u0018\u00010\u001cR\u00020\u0000X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/lidar/base/SlamwareLidarAdapter;", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "context", "Landroid/content/Context;", "version", "Lcom/pudutech/lidar/LidarVersion;", "callback", "Lcom/pudutech/lidar/LidarAdapterCallback;", "(Landroid/content/Context;Lcom/pudutech/lidar/LidarVersion;Lcom/pudutech/lidar/LidarAdapterCallback;)V", "MESSAGE_FROM_SLAMWARE", "", "getMESSAGE_FROM_SLAMWARE", "()I", "lastStartTimestamp", "", "lidar", "Lcom/pudutech/lidar/base/SlamwareLidar;", "getLidar", "()Lcom/pudutech/lidar/base/SlamwareLidar;", "setLidar", "(Lcom/pudutech/lidar/base/SlamwareLidar;)V", "mPlatform", "Lcom/slamtec/slamware/AbstractSlamwarePlatform;", "parseHandler", "Landroid/os/Handler;", "getParseHandler", "()Landroid/os/Handler;", "slamwareThread", "Lcom/pudutech/lidar/base/SlamwareLidarAdapter$SlamwareThread;", "getSlamwareThread", "()Lcom/pudutech/lidar/base/SlamwareLidarAdapter$SlamwareThread;", "setSlamwareThread", "(Lcom/pudutech/lidar/base/SlamwareLidarAdapter$SlamwareThread;)V", "startDelayRunnable", "Ljava/lang/Runnable;", "checkLidarServiceOK", "", "removeRunnable", "", "startLidarService", "startScan", "stopLidarService", "stopScan", "threadSleep", "ms", "SlamwareThread", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SlamwareLidarAdapter extends BaseLidarAdapter {
    private final int MESSAGE_FROM_SLAMWARE;
    private long lastStartTimestamp;
    public SlamwareLidar lidar;
    private AbstractSlamwarePlatform mPlatform;
    private final Handler parseHandler;
    private SlamwareThread slamwareThread;
    private final Runnable startDelayRunnable;

    public static final /* synthetic */ AbstractSlamwarePlatform access$getMPlatform$p(SlamwareLidarAdapter slamwareLidarAdapter) {
        AbstractSlamwarePlatform abstractSlamwarePlatform = slamwareLidarAdapter.mPlatform;
        if (abstractSlamwarePlatform == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPlatform");
        }
        return abstractSlamwarePlatform;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SlamwareLidarAdapter(Context context, LidarVersion version, LidarAdapterCallback lidarAdapterCallback) {
        super(context, version, lidarAdapterCallback);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(version, "version");
        this.startDelayRunnable = new Runnable() { // from class: com.pudutech.lidar.base.SlamwareLidarAdapter$startDelayRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                SlamwareLidarAdapter.this.getLidar().startScan();
                SlamwareLidarAdapter.this.removeRunnable();
            }
        };
        this.MESSAGE_FROM_SLAMWARE = 256;
        this.parseHandler = new Handler(getParseDataThread().getLooper(), new Handler.Callback() { // from class: com.pudutech.lidar.base.SlamwareLidarAdapter$parseHandler$1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                if (msg == null) {
                    return false;
                }
                if (msg.what == SlamwareLidarAdapter.this.getMESSAGE_FROM_SLAMWARE()) {
                    Object obj = msg.obj;
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.util.Vector<com.slamtec.slamware.robot.LaserPoint>");
                    }
                    Vector<LaserPoint> vector = (Vector) obj;
                    SlamwareLidarAdapter.this.getLidar().slamwareParser(vector);
                    Pdlog.m3276v(SlamwareLidarAdapter.this.getTAG(), "receive size=" + vector.size() + " from slamware");
                } else {
                    Pdlog.m3276v(SlamwareLidarAdapter.this.getTAG(), "no need parse data. msg.what=" + msg.what);
                }
                return true;
            }
        });
    }

    public final SlamwareLidar getLidar() {
        SlamwareLidar slamwareLidar = this.lidar;
        if (slamwareLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        return slamwareLidar;
    }

    public final void setLidar(SlamwareLidar slamwareLidar) {
        Intrinsics.checkParameterIsNotNull(slamwareLidar, "<set-?>");
        this.lidar = slamwareLidar;
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void startLidarService() {
        Pdlog.m3275i(getTAG(), "startLidarService");
        if (this.slamwareThread != null) {
            Pdlog.m3277w(getTAG(), "lidar service has been started");
        }
        SlamwareThread slamwareThread = new SlamwareThread();
        this.slamwareThread = slamwareThread;
        if (slamwareThread != null) {
            slamwareThread.setName("ethernetThread");
        }
        SlamwareThread slamwareThread2 = this.slamwareThread;
        if (slamwareThread2 == null) {
            Intrinsics.throwNpe();
        }
        slamwareThread2.start();
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void stopLidarService() {
        Pdlog.m3275i(getTAG(), "stopLidarService");
        try {
            stopScan();
            SlamwareThread slamwareThread = this.slamwareThread;
            if (slamwareThread != null) {
                slamwareThread.setRunning(false);
            }
            SlamwareThread slamwareThread2 = this.slamwareThread;
            if (slamwareThread2 != null) {
                slamwareThread2.interrupt();
            }
        } catch (Exception e) {
            Pdlog.m3274e(getTAG(), String.valueOf(e));
        }
        this.slamwareThread = (SlamwareThread) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeRunnable() {
        getControlHandler().removeCallbacks(this.startDelayRunnable);
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void startScan() {
        Pdlog.m3275i(getTAG(), "startScan. " + getLidarAdapterCallback());
        SlamwareThread slamwareThread = this.slamwareThread;
        if (slamwareThread == null || !slamwareThread.getIsDeviceConnected()) {
            String tag = getTAG();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("lidar device not connect yet. try it later. ");
            SlamwareThread slamwareThread2 = this.slamwareThread;
            sb.append(slamwareThread2 != null ? Boolean.valueOf(slamwareThread2.getIsDeviceConnected()) : null);
            objArr[0] = sb.toString();
            Pdlog.m3277w(tag, objArr);
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.lastStartTimestamp;
        SlamwareLidar slamwareLidar = this.lidar;
        if (slamwareLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        if (elapsedRealtime < slamwareLidar.getScanCMDValidInterval_ms()) {
            this.lastStartTimestamp = SystemClock.elapsedRealtime();
            String tag2 = getTAG();
            Object[] objArr2 = new Object[1];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("start scan cmd interval is too short < ");
            SlamwareLidar slamwareLidar2 = this.lidar;
            if (slamwareLidar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            sb2.append(slamwareLidar2.getScanCMDValidInterval_ms());
            sb2.append(". not allow it. try it later");
            objArr2[0] = sb2.toString();
            Pdlog.m3277w(tag2, objArr2);
            return;
        }
        this.lastStartTimestamp = SystemClock.elapsedRealtime();
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.base.SlamwareLidarAdapter$startScan$1
            @Override // java.lang.Runnable
            public final void run() {
                Runnable runnable;
                SlamwareLidarAdapter.SlamwareThread slamwareThread3 = SlamwareLidarAdapter.this.getSlamwareThread();
                if (slamwareThread3 != null) {
                    slamwareThread3.setKeepScan(true);
                }
                SlamwareLidarAdapter.this.getLidarAdapterCallback().onPowerRequest(true);
                Handler controlHandler = SlamwareLidarAdapter.this.getControlHandler();
                runnable = SlamwareLidarAdapter.this.startDelayRunnable;
                controlHandler.postDelayed(runnable, SlamwareLidarAdapter.this.getLidar().getPowerOnDelay_ms());
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void stopScan() {
        Pdlog.m3275i(getTAG(), "stopScan. " + getLidarAdapterCallback());
        this.lastStartTimestamp = 0L;
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.base.SlamwareLidarAdapter$stopScan$1
            @Override // java.lang.Runnable
            public final void run() {
                Runnable runnable;
                SlamwareLidarAdapter.SlamwareThread slamwareThread = SlamwareLidarAdapter.this.getSlamwareThread();
                if (slamwareThread != null) {
                    slamwareThread.setKeepScan(false);
                }
                Handler controlHandler = SlamwareLidarAdapter.this.getControlHandler();
                runnable = SlamwareLidarAdapter.this.startDelayRunnable;
                controlHandler.removeCallbacks(runnable);
                SlamwareLidarAdapter.this.getLidar().stopScan();
                SlamwareLidarAdapter.this.getLidarAdapterCallback().onPowerRequest(false);
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    /* renamed from: checkLidarServiceOK */
    public boolean getIsLidarConnect() {
        SlamwareThread slamwareThread = this.slamwareThread;
        if (slamwareThread != null) {
            return slamwareThread.getIsDeviceConnected();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final SlamwareThread getSlamwareThread() {
        return this.slamwareThread;
    }

    protected final void setSlamwareThread(SlamwareThread slamwareThread) {
        this.slamwareThread = slamwareThread;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: SlamwareLidarAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\b\u0084\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R$\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/lidar/base/SlamwareLidarAdapter$SlamwareThread;", "Ljava/lang/Thread;", "(Lcom/pudutech/lidar/base/SlamwareLidarAdapter;)V", "isDeviceConnected", "", "()Z", "setDeviceConnected", "(Z)V", ES6Iterator.VALUE_PROPERTY, "isRunning", "setRunning", "keepScan", "getKeepScan", "setKeepScan", "run", "", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    protected final class SlamwareThread extends Thread {
        private boolean isDeviceConnected;
        private boolean keepScan = true;
        private boolean isRunning = true;

        public SlamwareThread() {
        }

        /* renamed from: isDeviceConnected, reason: from getter */
        public final boolean getIsDeviceConnected() {
            return this.isDeviceConnected;
        }

        public final void setDeviceConnected(boolean z) {
            this.isDeviceConnected = z;
        }

        public final boolean getKeepScan() {
            return this.keepScan;
        }

        public final void setKeepScan(boolean z) {
            this.keepScan = z;
            Pdlog.m3275i(SlamwareLidarAdapter.this.getTAG(), "set keepScan " + z);
        }

        /* renamed from: isRunning, reason: from getter */
        public final boolean getIsRunning() {
            return this.isRunning;
        }

        public final void setRunning(boolean z) {
            this.isRunning = z;
            Pdlog.m3275i(SlamwareLidarAdapter.this.getTAG(), "set EthernetThread " + z);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            while (!this.isDeviceConnected && this.isRunning) {
                try {
                    Pdlog.m3273d(SlamwareLidarAdapter.this.getTAG(), "Slamware init");
                    SlamwareLidarAdapter slamwareLidarAdapter = SlamwareLidarAdapter.this;
                    AbstractSlamwarePlatform connect = DeviceManager.connect("192.168.11.1", 1445);
                    Intrinsics.checkExpressionValueIsNotNull(connect, "DeviceManager.connect(\"192.168.11.1\", 1445)");
                    slamwareLidarAdapter.mPlatform = connect;
                    if (SlamwareLidarAdapter.access$getMPlatform$p(SlamwareLidarAdapter.this) == null) {
                        Pdlog.m3274e(SlamwareLidarAdapter.this.getTAG(), "slamware init fail");
                    }
                    Pdlog.m3273d(SlamwareLidarAdapter.this.getTAG(), "Slamware init finish");
                    SlamwareLidarAdapter.this.getLidar().setLidarListener(SlamwareLidarAdapter.this.getBaseLidarListener());
                    this.isDeviceConnected = true;
                    break;
                } catch (Exception e) {
                    Log.getStackTraceString(e);
                    Pdlog.m3274e(SlamwareLidarAdapter.this.getTAG(), "Slamware init exception try again");
                }
            }
            while (this.isRunning) {
                if (!this.keepScan) {
                    SlamwareLidarAdapter.this.threadSleep(50L);
                } else {
                    Pdlog.m3273d(SlamwareLidarAdapter.this.getTAG(), "get laserscan");
                    LaserScan laserScan = SlamwareLidarAdapter.access$getMPlatform$p(SlamwareLidarAdapter.this).getLaserScan();
                    Intrinsics.checkExpressionValueIsNotNull(laserScan, "mPlatform.getLaserScan()");
                    Vector<LaserPoint> laserPoints = laserScan.getLaserPoints();
                    Intrinsics.checkExpressionValueIsNotNull(laserPoints, "laserScan.getLaserPoints()");
                    SlamwareLidarAdapter.this.getParseHandler().obtainMessage(SlamwareLidarAdapter.this.getMESSAGE_FROM_SLAMWARE(), laserPoints).sendToTarget();
                    SlamwareLidarAdapter.this.threadSleep(100L);
                }
            }
            try {
                SlamwareLidarAdapter.access$getMPlatform$p(SlamwareLidarAdapter.this).disconnect();
            } catch (Exception unused) {
            }
        }
    }

    public final int getMESSAGE_FROM_SLAMWARE() {
        return this.MESSAGE_FROM_SLAMWARE;
    }

    public final Handler getParseHandler() {
        return this.parseHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void threadSleep(long ms) {
        Pdlog.m3273d(getTAG(), "threadSleep " + ms);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException unused) {
            Pdlog.m3273d(getTAG(), "interrupt threadSleep");
        }
    }

    /* compiled from: SlamwareLidarAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/lidar/base/SlamwareLidarAdapter$Companion;", "", "()V", "HOST_ADDRESS", "", "HOST_PORT", "", "MESSAGE_FROM_SLAMWARE", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
