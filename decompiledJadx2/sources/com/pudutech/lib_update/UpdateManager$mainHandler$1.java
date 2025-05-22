package com.pudutech.lib_update;

import android.os.Handler;
import android.os.Message;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.config.ConfigContant;
import com.pudutech.lib_update.listener.IShowInstallProgress;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.util.PackageUtils;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/os/Message;", "kotlin.jvm.PlatformType", "handleMessage"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class UpdateManager$mainHandler$1 implements Handler.Callback {
    public static final UpdateManager$mainHandler$1 INSTANCE = new UpdateManager$mainHandler$1();

    UpdateManager$mainHandler$1() {
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v3, types: [T, java.lang.String] */
    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message it) {
        if (it.what == 101) {
            new Thread(new Runnable() { // from class: com.pudutech.lib_update.UpdateManager$mainHandler$1.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        File file = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, "update.apk");
                        Pdlog.m3275i("UpdateManager", "静默安装----》apk路径" + file.getAbsolutePath());
                        int installSilent = PackageUtils.installSilent(AppUpdateContext.context, file.getAbsolutePath());
                        Pdlog.m3273d("UpdateManager", "静默安装----》result " + installSilent);
                        if (installSilent != 1) {
                            UpdateManager.access$sendInstallFailed(UpdateManager.INSTANCE, new IOException("installSilent failed = " + installSilent));
                        } else {
                            UpdateManager.access$setShowDownFileProgress$p(UpdateManager.INSTANCE, (IShowProgress) null);
                        }
                    } catch (IOException e) {
                        Pdlog.m3274e("UpdateManager", e.getLocalizedMessage());
                        UpdateManager.access$sendInstallFailed(UpdateManager.INSTANCE, e);
                    }
                }
            }).start();
        }
        if (it.arg1 != 102) {
            return true;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        objectRef.element = it.getData().getString("versionName");
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = it.getData().getString("versionCode");
        new Thread(new Runnable() { // from class: com.pudutech.lib_update.UpdateManager$mainHandler$1.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    IShowInstallProgress access$getShowInstall = UpdateManager.access$getShowInstall(UpdateManager.INSTANCE);
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("监测安装接口 ");
                    sb.append(access$getShowInstall == null);
                    sb.append("  ");
                    sb.append((String) Ref.ObjectRef.this.element);
                    sb.append(' ');
                    sb.append((String) objectRef.element);
                    objArr[0] = sb.toString();
                    Pdlog.m3275i("UpdateManager", objArr);
                    if (access$getShowInstall == null) {
                        Intrinsics.throwNpe();
                    }
                    String versionCode = (String) Ref.ObjectRef.this.element;
                    Intrinsics.checkExpressionValueIsNotNull(versionCode, "versionCode");
                    String versionName = (String) objectRef.element;
                    Intrinsics.checkExpressionValueIsNotNull(versionName, "versionName");
                    access$getShowInstall.onStartInstall(versionCode, versionName);
                    Pdlog.m3275i("UpdateManager", "onStartInstall 接口监听start");
                    File file = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, "update.apk");
                    Pdlog.m3275i("UpdateManager", "静默安装----》apk路径" + file.getAbsolutePath());
                    Pdlog.m3275i("UpdateManager", "onStartInstall 接口监听end");
                    int installSilent = PackageUtils.installSilent(AppUpdateContext.context, file.getAbsolutePath());
                    Pdlog.m3273d("UpdateManager", "静默安装----》result " + installSilent);
                    if (installSilent != 1) {
                        UpdateManager.access$sendInstallFailed(UpdateManager.INSTANCE, new IOException("installSilent failed = " + installSilent));
                        String versionCode2 = (String) Ref.ObjectRef.this.element;
                        Intrinsics.checkExpressionValueIsNotNull(versionCode2, "versionCode");
                        String versionName2 = (String) objectRef.element;
                        Intrinsics.checkExpressionValueIsNotNull(versionName2, "versionName");
                        access$getShowInstall.onFailInstall(versionCode2, versionName2);
                        return;
                    }
                    UpdateManager.access$setShowDownFileProgress$p(UpdateManager.INSTANCE, (IShowProgress) null);
                    UpdateManager.access$setShowInstall(UpdateManager.INSTANCE, null);
                    String versionCode3 = (String) Ref.ObjectRef.this.element;
                    Intrinsics.checkExpressionValueIsNotNull(versionCode3, "versionCode");
                    String versionName3 = (String) objectRef.element;
                    Intrinsics.checkExpressionValueIsNotNull(versionName3, "versionName");
                    access$getShowInstall.onFinishInstall(versionCode3, versionName3);
                } catch (IOException e) {
                    Pdlog.m3274e("UpdateManager", e.getLocalizedMessage());
                    UpdateManager.access$sendInstallFailed(UpdateManager.INSTANCE, e);
                    IShowInstallProgress access$getShowInstallProgress$p = UpdateManager.access$getShowInstallProgress$p(UpdateManager.INSTANCE);
                    if (access$getShowInstallProgress$p == null) {
                        Intrinsics.throwNpe();
                    }
                    String versionCode4 = (String) Ref.ObjectRef.this.element;
                    Intrinsics.checkExpressionValueIsNotNull(versionCode4, "versionCode");
                    String versionName4 = (String) objectRef.element;
                    Intrinsics.checkExpressionValueIsNotNull(versionName4, "versionName");
                    access$getShowInstallProgress$p.onFailInstall(versionCode4, versionName4);
                }
            }
        }).start();
        return true;
    }
}
