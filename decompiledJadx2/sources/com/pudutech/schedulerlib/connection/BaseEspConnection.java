package com.pudutech.schedulerlib.connection;

import androidx.core.app.NotificationCompat;
import com.pudutech.schedulerlib.ScheduleController;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: BaseEspConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0003H&J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H&J\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bH&J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u000bH&J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0005H&J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u000bH&J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001aH&¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/BaseEspConnection;", "", "bytesToHexString", "", "src", "", "closeESPDevice", "", "espIsRunning", "", "getCurrentChannel", "", "getESPErrorCode", "getEspVersion", "openESPDevice", "connectStateListener", "Lcom/pudutech/schedulerlib/ScheduleController$OnConnectStateListener;", "high_version", "resetChannel", "chl", "sendMsg", NotificationCompat.CATEGORY_MESSAGE, "setBaudRate", "baud", "setMsgCallBack", "callback", "Lcom/pudutech/schedulerlib/connection/ScheduleMsgReceiveInterface;", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public interface BaseEspConnection {
    String bytesToHexString(byte[] src);

    void closeESPDevice();

    /* renamed from: espIsRunning */
    boolean getIsEspRunning();

    /* renamed from: getCurrentChannel */
    int getChannel();

    String getESPErrorCode();

    String getEspVersion();

    void openESPDevice(ScheduleController.OnConnectStateListener connectStateListener, int high_version);

    void resetChannel(int chl);

    int sendMsg(byte[] msg);

    void setBaudRate(int baud);

    void setMsgCallBack(ScheduleMsgReceiveInterface callback);

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* compiled from: BaseEspConnection.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static String bytesToHexString(BaseEspConnection baseEspConnection, byte[] bArr) {
            StringBuilder sb = new StringBuilder("");
            if (bArr == null) {
                return null;
            }
            if (bArr.length == 0) {
                return null;
            }
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() < 2) {
                    sb.append(0);
                }
                sb.append(hexString);
            }
            return sb.toString();
        }
    }
}
