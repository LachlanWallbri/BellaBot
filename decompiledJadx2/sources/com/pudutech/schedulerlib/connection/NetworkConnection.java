package com.pudutech.schedulerlib.connection;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.schedulerlib.ScheduleController;
import com.pudutech.schedulerlib.connection.MulticastConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: NetworkConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\r\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000eJ\u0015\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0015\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001aJ\u001d\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0000¢\u0006\u0002\b J\r\u0010!\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, m3961d2 = {"Lcom/pudutech/schedulerlib/connection/NetworkConnection;", "", "()V", "isRunning", "", "multicastConnection", "Lcom/pudutech/schedulerlib/connection/MulticastConnection;", "udpConnection", "Lcom/pudutech/schedulerlib/connection/UDPConnection;", "destroy", "", "destroy$schedulerlib_release", "getMulticastAddress", "", "getMulticastAddress$schedulerlib_release", "resetMulticastAddress", "id", "", "resetMulticastAddress$schedulerlib_release", "sendScheduleMsgFromUdp", NotificationCompat.CATEGORY_MESSAGE, "", "sendScheduleMsgFromUdp$schedulerlib_release", "setUdpMsgDecodeCallBack", "callback", "Lcom/pudutech/schedulerlib/connection/ScheduleMsgReceiveInterface;", "setUdpMsgDecodeCallBack$schedulerlib_release", "start", InternalConstant.KEY_APP, "Landroid/content/Context;", "connectStateListener", "Lcom/pudutech/schedulerlib/ScheduleController$OnConnectStateListener;", "start$schedulerlib_release", "udpIsRunning", "udpIsRunning$schedulerlib_release", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class NetworkConnection {
    private boolean isRunning;
    private final MulticastConnection multicastConnection = new MulticastConnection();
    private final UDPConnection udpConnection = new UDPConnection();

    public final void setUdpMsgDecodeCallBack$schedulerlib_release(ScheduleMsgReceiveInterface callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.udpConnection.setMsgDecodeCallBack$schedulerlib_release(callback);
    }

    public final void start$schedulerlib_release(Context app, ScheduleController.OnConnectStateListener connectStateListener) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(connectStateListener, "connectStateListener");
        if (!this.isRunning) {
            this.multicastConnection.setCallBack$schedulerlib_release(new MulticastConnection.MultiCallBack() { // from class: com.pudutech.schedulerlib.connection.NetworkConnection$start$1
                @Override // com.pudutech.schedulerlib.connection.MulticastConnection.MultiCallBack
                public void sendIP(String ip, long alive_time) {
                    UDPConnection uDPConnection;
                    Intrinsics.checkParameterIsNotNull(ip, "ip");
                    uDPConnection = NetworkConnection.this.udpConnection;
                    uDPConnection.addRemoteIp$schedulerlib_release(ip, alive_time);
                }

                @Override // com.pudutech.schedulerlib.connection.MulticastConnection.MultiCallBack
                public void clearIpList() {
                    UDPConnection uDPConnection;
                    uDPConnection = NetworkConnection.this.udpConnection;
                    uDPConnection.clearIpList$schedulerlib_release();
                }

                @Override // com.pudutech.schedulerlib.connection.MulticastConnection.MultiCallBack
                public void removeIP(String ip) {
                    UDPConnection uDPConnection;
                    Intrinsics.checkParameterIsNotNull(ip, "ip");
                    uDPConnection = NetworkConnection.this.udpConnection;
                    uDPConnection.removeIp$schedulerlib_release(ip);
                }
            });
            this.multicastConnection.start$schedulerlib_release(app, connectStateListener);
            this.udpConnection.startWorkingThread$schedulerlib_release();
        }
        this.isRunning = true;
    }

    public final void resetMulticastAddress$schedulerlib_release(int id) {
        this.multicastConnection.resetAddress$schedulerlib_release(id);
    }

    public final void sendScheduleMsgFromUdp$schedulerlib_release(byte[] msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        this.udpConnection.sendScheduleMsg$schedulerlib_release(msg);
    }

    public final void destroy$schedulerlib_release() {
        if (this.isRunning) {
            this.multicastConnection.closeMulticast$schedulerlib_release();
            this.udpConnection.closeUdpConnection$schedulerlib_release();
        }
        this.isRunning = false;
    }

    /* renamed from: udpIsRunning$schedulerlib_release, reason: from getter */
    public final boolean getIsRunning() {
        return this.isRunning;
    }

    public final String getMulticastAddress$schedulerlib_release() {
        return this.multicastConnection.getMULTICAST_ADDRESS();
    }
}
