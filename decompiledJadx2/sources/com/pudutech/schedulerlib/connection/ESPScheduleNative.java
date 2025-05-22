package com.pudutech.schedulerlib.connection;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ESPScheduleNative.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0086 J\t\u0010\u0005\u001a\u00020\u0006H\u0086 J\t\u0010\u0007\u001a\u00020\u0004H\u0086 J\t\u0010\b\u001a\u00020\tH\u0086 J\t\u0010\n\u001a\u00020\tH\u0086 J\t\u0010\u000b\u001a\u00020\tH\u0086 J\t\u0010\f\u001a\u00020\u0004H\u0086 J\u0019\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0086 J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0086 J\u0011\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\tH\u0086 J\u0011\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0011H\u0086 ¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/ESPScheduleNative;", "", "()V", "checkHardwareHandshake", "", "closeESP", "", "connectStatus", "getChannel", "", "getErrorCode", "getEspFirmwareVersion", "hasHardwareResponsed", "openESP", "baud_rate", "high_version", "readMsg", "", "resetChannel", "chl", "sendMsg", NotificationCompat.CATEGORY_MESSAGE, "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ESPScheduleNative {
    public static final ESPScheduleNative INSTANCE = new ESPScheduleNative();

    public final native boolean checkHardwareHandshake();

    public final native void closeESP();

    public final native boolean connectStatus();

    public final native int getChannel();

    public final native int getErrorCode();

    public final native int getEspFirmwareVersion();

    public final native boolean hasHardwareResponsed();

    public final native boolean openESP(int baud_rate, int high_version);

    public final native byte[] readMsg();

    public final native boolean resetChannel(int chl);

    public final native int sendMsg(byte[] msg);

    static {
        System.loadLibrary("esp32-uart");
    }

    private ESPScheduleNative() {
    }
}
