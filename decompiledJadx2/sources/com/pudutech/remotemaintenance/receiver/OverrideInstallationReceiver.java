package com.pudutech.remotemaintenance.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.MainActivity;
import com.pudutech.remotemaintenance.config.CConfig;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* compiled from: OverrideInstallationReceiver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/receiver/OverrideInstallationReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class OverrideInstallationReceiver extends BroadcastReceiver {
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ff  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onReceive(Context context, Intent intent) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        int hashCode = action.hashCode();
        if (hashCode != -810471698) {
            if (hashCode != 525384130) {
                if (hashCode == 1544582882 && action.equals("android.intent.action.PACKAGE_ADDED")) {
                    Uri data = intent.getData();
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("安装了新包");
                    sb.append(data != null ? data.getSchemeSpecificPart() : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d("OverrideInstallationReceiver", objArr);
                    return;
                }
                return;
            }
            if (action.equals("android.intent.action.PACKAGE_REMOVED")) {
                Uri data2 = intent.getData();
                Object[] objArr2 = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append("卸载了");
                sb2.append(data2 != null ? data2.getSchemeSpecificPart() : null);
                objArr2[0] = sb2.toString();
                Pdlog.m3273d("OverrideInstallationReceiver", objArr2);
                return;
            }
            return;
        }
        if (action.equals("android.intent.action.PACKAGE_REPLACED")) {
            Pdlog.m3273d("OverrideInstallationReceiver", "覆盖安装，重新启动");
            Uri data3 = intent.getData();
            Pdlog.m3273d("OverrideInstallationReceiver", "localPkgName = " + context.getPackageName() + ", installedPkgName = " + (data3 != null ? data3.getSchemeSpecificPart() : null));
            if (!Intrinsics.areEqual(r0, r3)) {
                return;
            }
            Pdlog.m3273d("OverrideInstallationReceiver", "远程维护助手安装成功");
            File file = new File(CConfig.INSTANCE.getREMOTE_MAINTENANCE_FILE_DIRECTORY());
            if (file.exists() && file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    if (!(listFiles.length == 0)) {
                        z = false;
                        if (!z) {
                            for (File file2 : listFiles) {
                                file2.delete();
                            }
                        }
                    }
                }
                z = true;
                if (!z) {
                }
            }
            Intent intent2 = new Intent(context, (Class<?>) MainActivity.class);
            intent2.setFlags(ClientDefaults.MAX_MSG_SIZE);
            intent2.setAction("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.addCategory("android.intent.category.DEFAULT");
            context.startActivity(intent2);
            Pdlog.m3273d("OverrideInstallationReceiver", "重启");
        }
    }
}
