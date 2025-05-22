package com.pudutech.lib_update;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.lib_update.config.ConfigContant;
import com.pudutech.lib_update.listener.IShowInstallProgress;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.listener.IShowProgress2;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.SwitchVersionResult;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.ExtKt;
import java.io.File;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J:\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00100\u00152\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0015J\u001a\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00162\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ&\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00162\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!J<\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00100\u00152\u0016\b\u0002\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0015H\u0007J\u001a\u0010%\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/lib_update/UpdateManager;", "", "()V", "TAG", "", "installSilentDelayed", "", "getInstallSilentDelayed", "()J", "setInstallSilentDelayed", "(J)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "updateJob", "Lkotlinx/coroutines/Job;", "cancelInstall", "", "checkVersion", "request", "Lcom/pudutech/lib_update/module/model/CheckUpdateRequestParams;", "callBack", "Lkotlin/Function1;", "Lcom/pudutech/lib_update/module/model/VerionResult;", "onError", "", "downloadApkAndInstallSilent", "vr", "showDownFileProgress", "Lcom/pudutech/lib_update/listener/IShowProgress;", "downloadApkAndInstallSilent2", "showDownFileProgress2", "Lcom/pudutech/lib_update/listener/IShowProgress2;", "showInstallProgress", "Lcom/pudutech/lib_update/listener/IShowInstallProgress;", "switchSoftWareVersion", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/lib_update/module/model/SwitchVersionResult;", "update", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class UpdateManager {
    private static final String TAG = "UpdateManager";
    private static Job updateJob;
    public static final UpdateManager INSTANCE = new UpdateManager();
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    private static long installSilentDelayed = 1500;

    private UpdateManager() {
    }

    public final long getInstallSilentDelayed() {
        return installSilentDelayed;
    }

    public final void setInstallSilentDelayed(long j) {
        installSilentDelayed = j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void checkVersion$default(UpdateManager updateManager, CheckUpdateRequestParams checkUpdateRequestParams, Function1 function1, Function1 function12, int i, Object obj) {
        if ((i & 4) != 0) {
            function12 = (Function1) null;
        }
        updateManager.checkVersion(checkUpdateRequestParams, function1, function12);
    }

    public final void checkVersion(CheckUpdateRequestParams request, Function1<? super VerionResult, Unit> callBack, Function1<? super Throwable, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new UpdateManager$checkVersion$1(request, callBack, onError, null), 3, null);
    }

    public static /* synthetic */ void update$default(UpdateManager updateManager, CheckUpdateRequestParams checkUpdateRequestParams, IShowProgress iShowProgress, int i, Object obj) {
        if ((i & 2) != 0) {
            iShowProgress = (IShowProgress) null;
        }
        updateManager.update(checkUpdateRequestParams, iShowProgress);
    }

    public final void update(CheckUpdateRequestParams request, IShowProgress showDownFileProgress) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(request, "request");
        launch$default = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new UpdateManager$update$1(request, showDownFileProgress, null), 3, null);
        updateJob = launch$default;
    }

    public static /* synthetic */ void switchSoftWareVersion$default(CheckUpdateRequestParams checkUpdateRequestParams, Function1 function1, Function1 function12, int i, Object obj) {
        if ((i & 4) != 0) {
            function12 = (Function1) null;
        }
        switchSoftWareVersion(checkUpdateRequestParams, function1, function12);
    }

    @JvmStatic
    public static final void switchSoftWareVersion(CheckUpdateRequestParams req, Function1<? super SwitchVersionResult, Unit> callBack, Function1<? super Throwable, Unit> onError) {
        Intrinsics.checkParameterIsNotNull(req, "req");
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new UpdateManager$switchSoftWareVersion$1(req, callBack, onError, null), 3, null);
    }

    public final void cancelInstall() {
        Job job = updateJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        updateJob = (Job) null;
    }

    public static /* synthetic */ void downloadApkAndInstallSilent$default(UpdateManager updateManager, VerionResult verionResult, IShowProgress iShowProgress, int i, Object obj) {
        if ((i & 2) != 0) {
            iShowProgress = (IShowProgress) null;
        }
        updateManager.downloadApkAndInstallSilent(verionResult, iShowProgress);
    }

    public final void downloadApkAndInstallSilent(VerionResult vr, IShowProgress showDownFileProgress) {
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        NetWorkLog.INSTANCE.mo3280i(TAG, "downloadApkAndInstallSilent > vr:" + vr + " showDownFileProgress:" + showDownFileProgress + ' ');
        if (vr.getVersion() != null) {
            File file = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, "update.apk");
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            PdNetworkManager pdNetworkManager = PdNetworkManager.f10310INSTANCE;
            Version version = vr.getVersion();
            if (version == null) {
                Intrinsics.throwNpe();
            }
            ExtKt.download(pdNetworkManager, version.getUrl(), file, new UpdateManager$downloadApkAndInstallSilent$1(file, vr, showDownFileProgress));
            return;
        }
        NetWorkLog.INSTANCE.mo3279e(TAG, "--downloadApkAndInstallSilent----->version is null ");
        BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getMain(), null, new UpdateManager$downloadApkAndInstallSilent$2(showDownFileProgress, null), 2, null);
    }

    public static /* synthetic */ void downloadApkAndInstallSilent2$default(UpdateManager updateManager, VerionResult verionResult, IShowProgress2 iShowProgress2, IShowInstallProgress iShowInstallProgress, int i, Object obj) {
        if ((i & 2) != 0) {
            iShowProgress2 = (IShowProgress2) null;
        }
        if ((i & 4) != 0) {
            iShowInstallProgress = (IShowInstallProgress) null;
        }
        updateManager.downloadApkAndInstallSilent2(verionResult, iShowProgress2, iShowInstallProgress);
    }

    public final void downloadApkAndInstallSilent2(VerionResult vr, IShowProgress2 showDownFileProgress2, IShowInstallProgress showInstallProgress) {
        Intrinsics.checkParameterIsNotNull(vr, "vr");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("downloadApkAndInstallSilent2  ");
        Version version = vr.getVersion();
        if (version == null) {
            Intrinsics.throwNpe();
        }
        sb.append(version.getVersion_name());
        sb.append("  ");
        Version version2 = vr.getVersion();
        if (version2 == null) {
            Intrinsics.throwNpe();
        }
        sb.append(version2.getVersion_code());
        netWorkLog.mo3278d(TAG, sb.toString());
        Version version3 = vr.getVersion();
        if (version3 == null) {
            Intrinsics.throwNpe();
        }
        String version_code = version3.getVersion_code();
        Version version4 = vr.getVersion();
        if (version4 == null) {
            Intrinsics.throwNpe();
        }
        String version_name = version4.getVersion_name();
        if (vr.getVersion() != null) {
            File file = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, "update.apk");
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            PdNetworkManager pdNetworkManager = PdNetworkManager.f10310INSTANCE;
            Version version5 = vr.getVersion();
            if (version5 == null) {
                Intrinsics.throwNpe();
            }
            ExtKt.download(pdNetworkManager, version5.getUrl(), file, new UpdateManager$downloadApkAndInstallSilent2$1(file, vr, showDownFileProgress2, version_code, version_name, showInstallProgress));
        }
    }
}
