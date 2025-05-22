package com.pudutech.pdmqtt.service;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferService;
import com.pudutech.pdmqtt.MqttManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: PuduMqttService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\u0012\u0010\u0014\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0016J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/pdmqtt/service/PuduMqttService;", "Landroid/app/Service;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "binder", "Lcom/pudutech/pdmqtt/MqttManager$Stub;", "getBinder", "()Lcom/pudutech/pdmqtt/MqttManager$Stub;", "binder$delegate", "Lkotlin/Lazy;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onRebind", "onStartCommand", "", "flags", "startId", "onUnbind", "", "unbindService", "conn", "Landroid/content/ServiceConnection;", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PuduMqttService extends Service {
    private final String TAG = "PuduMqttService";

    /* renamed from: binder$delegate, reason: from kotlin metadata */
    private final Lazy binder = LazyKt.lazy(new Function0<MqttManagerImpl>() { // from class: com.pudutech.pdmqtt.service.PuduMqttService$binder$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MqttManagerImpl invoke() {
            Context baseContext = PuduMqttService.this.getBaseContext();
            Intrinsics.checkExpressionValueIsNotNull(baseContext, "baseContext");
            return new MqttManagerImpl(baseContext);
        }
    });

    private final MqttManager.Stub getBinder() {
        return (MqttManager.Stub) this.binder.getValue();
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override // android.app.Service
    public void onCreate() {
        MqttManagerImplKt.remoteLog(this.TAG, "onCreate");
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("com.pudutech.pdmqtt", MqttServiceConstants.WAKELOCK_NETWORK_INTENT, 2);
            Object systemService = getSystemService(TransferService.INTENT_KEY_NOTIFICATION);
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
            }
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
            Notification build = new Notification.Builder(this, "com.pudutech.pdmqtt").setSmallIcon(R.mipmap.sym_def_app_icon).setWhen(System.currentTimeMillis()).setContentText("MQTT服务正在运行").build();
            Intrinsics.checkExpressionValueIsNotNull(build, "Notification.Builder(thi…\n                .build()");
            startForeground(2, build);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        MqttManagerImplKt.remoteLog(this.TAG, "onDestroy");
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        MqttManagerImplKt.remoteLog(this.TAG, "onStartCommand");
        return 1;
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        MqttManagerImplKt.remoteLog(this.TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        MqttManagerImplKt.remoteLog(this.TAG, "onBind");
        return getBinder();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unbindService(ServiceConnection conn) {
        Intrinsics.checkParameterIsNotNull(conn, "conn");
        MqttManagerImplKt.remoteLog(this.TAG, "unbindService");
        super.unbindService(conn);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        MqttManagerImplKt.remoteLog(this.TAG, "onUnbind");
        return super.onUnbind(intent);
    }
}
