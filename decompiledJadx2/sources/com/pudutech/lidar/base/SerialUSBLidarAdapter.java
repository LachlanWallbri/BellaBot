package com.pudutech.lidar.base;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarAdapterCallback;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.lidar.port.SerialUSB;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: SerialUSBLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010!\u001a\u00020\nH\u0016J\b\u0010\"\u001a\u00020#H\u0002J\u001f\u0010$\u001a\u00020#2\u0012\u0010%\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001d0&\"\u00020\u001d¢\u0006\u0002\u0010'J\b\u0010(\u001a\u00020#H\u0016J\b\u0010)\u001a\u00020#H\u0016J\b\u0010*\u001a\u00020#H\u0016J\b\u0010+\u001a\u00020#H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u001cj\b\u0012\u0004\u0012\u00020\u001d`\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006,"}, m3961d2 = {"Lcom/pudutech/lidar/base/SerialUSBLidarAdapter;", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "context", "Landroid/content/Context;", "version", "Lcom/pudutech/lidar/LidarVersion;", "callback", "Lcom/pudutech/lidar/LidarAdapterCallback;", "(Landroid/content/Context;Lcom/pudutech/lidar/LidarVersion;Lcom/pudutech/lidar/LidarAdapterCallback;)V", "isLidarConnect", "", "lastStartTimestamp", "", "lidar", "Lcom/pudutech/lidar/base/SerialLidar;", "getLidar", "()Lcom/pudutech/lidar/base/SerialLidar;", "setLidar", "(Lcom/pudutech/lidar/base/SerialLidar;)V", "parseHandler", "Landroid/os/Handler;", "getParseHandler", "()Landroid/os/Handler;", "serialUSB", "Lcom/pudutech/lidar/port/SerialUSB;", "startDelayRunnable", "Ljava/lang/Runnable;", "supportSerialModel", "Ljava/util/ArrayList;", "Lcom/pudutech/lidar/port/SerialUSB$SerialModel;", "Lkotlin/collections/ArrayList;", "getSupportSerialModel", "()Ljava/util/ArrayList;", "checkLidarServiceOK", "removeRunnable", "", "setSerialModel", "model", "", "([Lcom/pudutech/lidar/port/SerialUSB$SerialModel;)V", "startLidarService", "startScan", "stopLidarService", "stopScan", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class SerialUSBLidarAdapter extends BaseLidarAdapter {
    private boolean isLidarConnect;
    private long lastStartTimestamp;
    public SerialLidar lidar;
    private final Handler parseHandler;
    private SerialUSB serialUSB;
    private final Runnable startDelayRunnable;
    private final ArrayList<SerialUSB.SerialModel> supportSerialModel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialUSBLidarAdapter(Context context, LidarVersion version, LidarAdapterCallback callback) {
        super(context, version, callback);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.supportSerialModel = new ArrayList<>();
        this.startDelayRunnable = new Runnable() { // from class: com.pudutech.lidar.base.SerialUSBLidarAdapter$startDelayRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                SerialUSBLidarAdapter.this.getLidar().startScan();
                SerialUSBLidarAdapter.this.removeRunnable();
            }
        };
        this.parseHandler = new Handler(getParseDataThread().getLooper(), new Handler.Callback() { // from class: com.pudutech.lidar.base.SerialUSBLidarAdapter$parseHandler$1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                if (msg == null) {
                    return false;
                }
                if (msg.what == SerialUSB.INSTANCE.getMESSAGE_FROM_SERIAL_PORT()) {
                    Object obj = msg.obj;
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
                    }
                    byte[] bArr = (byte[]) obj;
                    SerialUSBLidarAdapter.this.getLidar().parser(bArr);
                    Pdlog.m3276v(SerialUSBLidarAdapter.this.getTAG(), "receive size=" + bArr.length + " from usb serial ");
                } else {
                    Pdlog.m3276v(SerialUSBLidarAdapter.this.getTAG(), "no need parse data. msg.what=" + msg.what);
                }
                return true;
            }
        });
    }

    public final SerialLidar getLidar() {
        SerialLidar serialLidar = this.lidar;
        if (serialLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        return serialLidar;
    }

    public final void setLidar(SerialLidar serialLidar) {
        Intrinsics.checkParameterIsNotNull(serialLidar, "<set-?>");
        this.lidar = serialLidar;
    }

    public final ArrayList<SerialUSB.SerialModel> getSupportSerialModel() {
        return this.supportSerialModel;
    }

    public final void setSerialModel(SerialUSB.SerialModel... model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        this.supportSerialModel.clear();
        for (SerialUSB.SerialModel serialModel : model) {
            this.supportSerialModel.add(serialModel);
        }
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void startLidarService() {
        Pdlog.m3275i(getTAG(), "startLidarService");
        getLidarAdapterCallback().onPowerRequest(true);
        SerialLidar serialLidar = this.lidar;
        if (serialLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        serialLidar.setLidarListener(getBaseLidarListener());
        SerialUSB serialUSB = new SerialUSB(getMContext(), this.supportSerialModel);
        this.serialUSB = serialUSB;
        if (serialUSB == null) {
            Intrinsics.throwNpe();
        }
        serialUSB.setReceiveHandler(this.parseHandler);
        SerialUSB serialUSB2 = this.serialUSB;
        if (serialUSB2 == null) {
            Intrinsics.throwNpe();
        }
        SerialLidar serialLidar2 = this.lidar;
        if (serialLidar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        serialUSB2.openDevice(serialLidar2.getBaudRate(), new SerialUSBLidarAdapter$startLidarService$1(this));
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void stopLidarService() {
        Pdlog.m3275i(getTAG(), "stopLidarService");
        stopScan();
        SerialUSB serialUSB = this.serialUSB;
        if (serialUSB != null) {
            serialUSB.setReceiveHandler((Handler) null);
        }
        this.serialUSB = (SerialUSB) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeRunnable() {
        getControlHandler().removeCallbacks(this.startDelayRunnable);
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void startScan() {
        Pdlog.m3275i(getTAG(), "startScan. " + getLidarAdapterCallback());
        if (!this.isLidarConnect) {
            Pdlog.m3277w(getTAG(), "lidar not connect yet. try it later");
            return;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.lastStartTimestamp;
        SerialLidar serialLidar = this.lidar;
        if (serialLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        if (elapsedRealtime < serialLidar.getScanCMDValidInterval_ms()) {
            this.lastStartTimestamp = SystemClock.elapsedRealtime();
            String tag = getTAG();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("start scan cmd interval is too short < ");
            SerialLidar serialLidar2 = this.lidar;
            if (serialLidar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            sb.append(serialLidar2.getScanCMDValidInterval_ms());
            sb.append(". not allow it. try it later");
            objArr[0] = sb.toString();
            Pdlog.m3277w(tag, objArr);
            return;
        }
        this.lastStartTimestamp = SystemClock.elapsedRealtime();
        removeRunnable();
        getLidarAdapterCallback().onPowerRequest(true);
        Handler controlHandler = getControlHandler();
        Runnable runnable = this.startDelayRunnable;
        SerialLidar serialLidar3 = this.lidar;
        if (serialLidar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        controlHandler.postDelayed(runnable, serialLidar3.getPowerOnDelay_ms());
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    public void stopScan() {
        Pdlog.m3275i(getTAG(), "stopScan. " + getLidarAdapterCallback());
        this.lastStartTimestamp = 0L;
        getControlHandler().post(new Runnable() { // from class: com.pudutech.lidar.base.SerialUSBLidarAdapter$stopScan$1
            @Override // java.lang.Runnable
            public final void run() {
                SerialUSBLidarAdapter.this.removeRunnable();
                SerialUSBLidarAdapter.this.getLidar().stopScan();
                SerialUSBLidarAdapter.this.getLidarAdapterCallback().onPowerRequest(false);
            }
        });
    }

    @Override // com.pudutech.lidar.base.BaseLidarAdapter
    /* renamed from: checkLidarServiceOK, reason: from getter */
    public boolean getIsLidarConnect() {
        return this.isLidarConnect;
    }

    public final Handler getParseHandler() {
        return this.parseHandler;
    }
}
