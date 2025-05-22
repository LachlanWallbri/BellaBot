package com.pudutech.lidar.base;

import android.os.Handler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: BaseLidar.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H&J\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u000eJ\b\u0010#\u001a\u00020\u001eH&J\b\u0010$\u001a\u00020\u001eH&R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@TX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@TX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019¨\u0006%"}, m3961d2 = {"Lcom/pudutech/lidar/base/BaseLidar;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "controlHandler", "Landroid/os/Handler;", "getControlHandler", "()Landroid/os/Handler;", "setControlHandler", "(Landroid/os/Handler;)V", "listener", "Lcom/pudutech/lidar/base/LidarListener;", "getListener", "()Lcom/pudutech/lidar/base/LidarListener;", "setListener", "(Lcom/pudutech/lidar/base/LidarListener;)V", "<set-?>", "", "powerOnDelay_ms", "getPowerOnDelay_ms", "()J", "setPowerOnDelay_ms", "(J)V", "scanCMDValidInterval_ms", "getScanCMDValidInterval_ms", "setScanCMDValidInterval_ms", "parser", "", "src", "", "setLidarListener", "lidarListener", "startScan", "stopScan", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public abstract class BaseLidar {
    public Handler controlHandler;
    private LidarListener listener;
    private final String TAG = "Lidar";
    private long powerOnDelay_ms = 50;
    private long scanCMDValidInterval_ms = 5000;

    public abstract void parser(byte[] src);

    public abstract void startScan();

    public abstract void stopScan();

    public String getTAG() {
        return this.TAG;
    }

    public long getPowerOnDelay_ms() {
        return this.powerOnDelay_ms;
    }

    protected void setPowerOnDelay_ms(long j) {
        this.powerOnDelay_ms = j;
    }

    public long getScanCMDValidInterval_ms() {
        return this.scanCMDValidInterval_ms;
    }

    protected void setScanCMDValidInterval_ms(long j) {
        this.scanCMDValidInterval_ms = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LidarListener getListener() {
        return this.listener;
    }

    protected final void setListener(LidarListener lidarListener) {
        this.listener = lidarListener;
    }

    public final void setLidarListener(LidarListener lidarListener) {
        Intrinsics.checkParameterIsNotNull(lidarListener, "lidarListener");
        this.listener = lidarListener;
    }

    public final Handler getControlHandler() {
        Handler handler = this.controlHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("controlHandler");
        }
        return handler;
    }

    public final void setControlHandler(Handler handler) {
        Intrinsics.checkParameterIsNotNull(handler, "<set-?>");
        this.controlHandler = handler;
    }
}
