package com.pudutech.disinfect.baselib.maintenance;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RemoteMaintenanceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0007J\b\u0010\u001a\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/maintenance/RemoteMaintenanceManager;", "", "()V", "PACKAGE_NAME", "", "REMOTE_MAINTENANCE_FILE_DIRECTORY", "getREMOTE_MAINTENANCE_FILE_DIRECTORY", "()Ljava/lang/String;", "REMOTE_MAINTENANCE_FILE_DIRECTORY$delegate", "Lkotlin/Lazy;", "TAG", "appInstallReceiver", "Lcom/pudutech/disinfect/baselib/maintenance/RemoteMaintenanceManager$AppInstallReceiver;", "isConnection", "", "remoteMaintenanceService", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/remotemaintenance/aidl/RemoteMaintenanceInterface;", MqttServiceConstants.CONNECT_ACTION, "", "getRemoteInterface", "Lcom/pudutech/disinfect/baselib/maintenance/RemoteMaintenanceInterfaceWrap;", "registerAppInstallReceiver", "fileName", "runRemoteMaintenanceApp", "start", "unregisterAppInstallReceiver", "AppInstallReceiver", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RemoteMaintenanceManager {
    private static final String PACKAGE_NAME = "com.pudutech.remotemaintenance";
    private static final String TAG = "RemoteMaintenanceManager";
    private static AppInstallReceiver appInstallReceiver;
    private static volatile boolean isConnection;
    public static final RemoteMaintenanceManager INSTANCE = new RemoteMaintenanceManager();

    /* renamed from: REMOTE_MAINTENANCE_FILE_DIRECTORY$delegate, reason: from kotlin metadata */
    private static final Lazy REMOTE_MAINTENANCE_FILE_DIRECTORY = LazyKt.lazy(new Function0<String>() { // from class: com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$REMOTE_MAINTENANCE_FILE_DIRECTORY$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
            return externalStorageDirectory.getPath() + "/pudu/files/remotemaintenance";
        }
    });
    private static final AIDLConnection<RemoteMaintenanceInterface> remoteMaintenanceService = new AIDLConnection<>("com.pudutech.remotemaintenance.service.RemoteMaintenanceService", RemoteMaintenanceManager$remoteMaintenanceService$1.INSTANCE, "com.pudutech.remotemaintenance");

    /* JADX INFO: Access modifiers changed from: private */
    public final String getREMOTE_MAINTENANCE_FILE_DIRECTORY() {
        return (String) REMOTE_MAINTENANCE_FILE_DIRECTORY.getValue();
    }

    private RemoteMaintenanceManager() {
    }

    public final void start() {
        if (PackageUtil.INSTANCE.isAppInstalled(KtxKt.getAppContext(), "com.pudutech.remotemaintenance")) {
            Pdlog.m3273d(TAG, "已安装远程维护助手");
            runRemoteMaintenanceApp();
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceManager$start$1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerAppInstallReceiver(String fileName) {
        unregisterAppInstallReceiver();
        appInstallReceiver = new AppInstallReceiver(fileName);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        KtxKt.getAppContext().registerReceiver(appInstallReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unregisterAppInstallReceiver() {
        if (appInstallReceiver == null) {
            return;
        }
        try {
            KtxKt.getAppContext().unregisterReceiver(appInstallReceiver);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "unregisterAppInstallReceiver : " + Log.getStackTraceString(e));
        }
        appInstallReceiver = (AppInstallReceiver) null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: RemoteMaintenanceManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/maintenance/RemoteMaintenanceManager$AppInstallReceiver;", "Landroid/content/BroadcastReceiver;", "fileName", "", "(Ljava/lang/String;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class AppInstallReceiver extends BroadcastReceiver {
        private String fileName;

        public AppInstallReceiver(String str) {
            this.fileName = str;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            String action = intent != null ? intent.getAction() : null;
            if (action != null && action.hashCode() == 1544582882 && action.equals("android.intent.action.PACKAGE_ADDED")) {
                Uri data = intent.getData();
                String schemeSpecificPart = data != null ? data.getSchemeSpecificPart() : null;
                if (!Intrinsics.areEqual("com.pudutech.remotemaintenance", schemeSpecificPart)) {
                    return;
                }
                Pdlog.m3273d(RemoteMaintenanceManager.TAG, "远程维护助手安装成功，packageName = " + schemeSpecificPart);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceManager$AppInstallReceiver$onReceive$1(this, null), 3, null);
                RemoteMaintenanceManager.INSTANCE.unregisterAppInstallReceiver();
                RemoteMaintenanceManager.INSTANCE.runRemoteMaintenanceApp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runRemoteMaintenanceApp() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceManager$runRemoteMaintenanceApp$1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void connect() {
        Pdlog.m3273d(TAG, "connect()");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceManager$connect$1(null), 3, null);
    }

    public final RemoteMaintenanceInterfaceWrap getRemoteInterface() {
        return new RemoteMaintenanceInterfaceWrap() { // from class: com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$getRemoteInterface$1
            @Override // com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceInterfaceWrap, com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setCoordinateOrientation(double p0, double p1, double p2) {
                boolean z;
                RemoteMaintenanceManager remoteMaintenanceManager = RemoteMaintenanceManager.INSTANCE;
                z = RemoteMaintenanceManager.isConnection;
                if (z) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C4442x487fbab9(p0, p1, p2, null), 3, null);
                }
            }

            @Override // com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceInterfaceWrap, com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setMapPath(String p0) {
                boolean z;
                RemoteMaintenanceManager remoteMaintenanceManager = RemoteMaintenanceManager.INSTANCE;
                z = RemoteMaintenanceManager.isConnection;
                if (z) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceManager$getRemoteInterface$1$setMapPath$1(p0, null), 3, null);
                }
            }

            @Override // com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceInterfaceWrap, com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setPower(int p0) {
                boolean z;
                RemoteMaintenanceManager remoteMaintenanceManager = RemoteMaintenanceManager.INSTANCE;
                z = RemoteMaintenanceManager.isConnection;
                if (z) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceManager$getRemoteInterface$1$setPower$1(p0, null), 3, null);
                }
            }

            @Override // com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceInterfaceWrap, com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setSpeed(double p0) {
                boolean z;
                RemoteMaintenanceManager remoteMaintenanceManager = RemoteMaintenanceManager.INSTANCE;
                z = RemoteMaintenanceManager.isConnection;
                if (z) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceManager$getRemoteInterface$1$setSpeed$1(p0, null), 3, null);
                }
            }

            @Override // com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceInterfaceWrap, com.pudutech.remotemaintenance.aidl.RemoteMaintenanceInterface
            public void setTask(String p0) {
                boolean z;
                RemoteMaintenanceManager remoteMaintenanceManager = RemoteMaintenanceManager.INSTANCE;
                z = RemoteMaintenanceManager.isConnection;
                if (z) {
                    Pdlog.m3273d("RemoteMaintenanceManager", "setTask : p0 = " + p0 + "; ");
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceManager$getRemoteInterface$1$setTask$1(p0, null), 3, null);
                }
            }
        };
    }
}
