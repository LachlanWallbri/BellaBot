package com.pudutech.remotemaintenance.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.aidl.IRemoteMaintenanceListener;
import com.pudutech.remotemaintenance.listener.ConnectStateCallback;
import com.pudutech.remotemaintenance.utils.PackageUtil;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: RemoteMaintenanceService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 %2\u00020\u00012\u00020\u0002:\u0001%B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0016J\b\u0010 \u001a\u00020\u0019H\u0016J\"\u0010!\u001a\u00020\u00072\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u0007H\u0016J\b\u0010$\u001a\u00020\u0019H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006&"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/service/RemoteMaintenanceService;", "Landroid/app/Service;", "Lcom/pudutech/remotemaintenance/listener/ConnectStateCallback;", "()V", "bind", "Lcom/pudutech/remotemaintenance/service/MyBind;", "connectState", "", "getConnectState", "()I", "setConnectState", "(I)V", "errCode", "getErrCode", "()Ljava/lang/Integer;", "setErrCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "errMsg", "", "getErrMsg", "()Ljava/lang/String;", "setErrMsg", "(Ljava/lang/String;)V", "checkOldIoTAppExistsAndUninstall", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onConnectFailure", "onConnected", "onConnecting", "onStartCommand", "flags", "startId", "startIoT", "Companion", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RemoteMaintenanceService extends Service implements ConnectStateCallback {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "RMService";
    private MyBind bind;
    private int connectState;
    private Integer errCode;
    private String errMsg;

    public final int getConnectState() {
        return this.connectState;
    }

    public final void setConnectState(int i) {
        this.connectState = i;
    }

    public final Integer getErrCode() {
        return this.errCode;
    }

    public final void setErrCode(Integer num) {
        this.errCode = num;
    }

    public final String getErrMsg() {
        return this.errMsg;
    }

    public final void setErrMsg(String str) {
        this.errMsg = str;
    }

    /* compiled from: RemoteMaintenanceService.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/service/RemoteMaintenanceService$Companion;", "", "()V", "TAG", "", "start", "", "context", "Landroid/content/Context;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void start(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            ContextCompat.startForegroundService(context, new Intent(context, (Class<?>) RemoteMaintenanceService.class));
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        MyBind myBind = new MyBind(this);
        this.bind = myBind;
        return myBind;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand intent = " + intent + ", flags = " + flags + ", startId = " + startId);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("RemoteMaintenanceServiceChannel", "RemoteMaintenance Service Channel", 2);
            notificationChannel.setSound(Uri.parse(("android.resource://" + getPackageName()) + "/raw/notification_sound.mp3"), null);
            notificationChannel.enableVibration(false);
            Object systemService = getSystemService(NotificationManager.class);
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
            }
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
        }
        RemoteMaintenanceService remoteMaintenanceService = this;
        startForeground(1, new NotificationCompat.Builder(remoteMaintenanceService, "RemoteMaintenanceServiceChannel").setContentTitle("普渡远程维护助手正在后台运行").setContentText("RemoteMaintenanceService").setSmallIcon(2131099738).setContentIntent(PendingIntent.getBroadcast(remoteMaintenanceService, 0, new Intent(), 0)).build());
        checkOldIoTAppExistsAndUninstall();
        startIoT();
        return 2;
    }

    private final void checkOldIoTAppExistsAndUninstall() {
        RemoteMaintenanceService remoteMaintenanceService = this;
        if (PackageUtil.INSTANCE.isAppInstalled(remoteMaintenanceService, "com.pudutech.iot")) {
            boolean uninstallApp = PackageUtil.INSTANCE.uninstallApp(remoteMaintenanceService, "com.pudutech.iot");
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("IoTApp卸载");
            sb.append(uninstallApp ? "成功" : "失败");
            objArr[0] = sb.toString();
            Pdlog.m3273d(TAG, objArr);
            return;
        }
        Pdlog.m3273d(TAG, "IoTApp卸载失败，不存在");
    }

    private final void startIoT() {
        Pdlog.m3273d(TAG, "startIoT()");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceService$startIoT$1(this, null), 3, null);
    }

    @Override // com.pudutech.remotemaintenance.listener.ConnectStateCallback
    public void onConnecting() {
        IRemoteMaintenanceListener remoteMaintenanceListener;
        this.connectState = 0;
        MyBind myBind = this.bind;
        if (myBind == null || (remoteMaintenanceListener = myBind.getRemoteMaintenanceListener()) == null) {
            return;
        }
        remoteMaintenanceListener.onConnecting();
    }

    @Override // com.pudutech.remotemaintenance.listener.ConnectStateCallback
    public void onConnected() {
        IRemoteMaintenanceListener remoteMaintenanceListener;
        this.connectState = 1;
        MyBind myBind = this.bind;
        if (myBind == null || (remoteMaintenanceListener = myBind.getRemoteMaintenanceListener()) == null) {
            return;
        }
        remoteMaintenanceListener.onConnected();
    }

    @Override // com.pudutech.remotemaintenance.listener.ConnectStateCallback
    public void onConnectFailure(int errCode, String errMsg) {
        IRemoteMaintenanceListener remoteMaintenanceListener;
        Intrinsics.checkParameterIsNotNull(errMsg, "errMsg");
        this.connectState = -1;
        this.errCode = Integer.valueOf(errCode);
        this.errMsg = errMsg;
        MyBind myBind = this.bind;
        if (myBind == null || (remoteMaintenanceListener = myBind.getRemoteMaintenanceListener()) == null) {
            return;
        }
        remoteMaintenanceListener.onConnectFailure(errCode, errMsg);
    }
}
