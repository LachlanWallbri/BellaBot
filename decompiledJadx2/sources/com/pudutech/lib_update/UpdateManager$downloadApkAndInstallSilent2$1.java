package com.pudutech.lib_update;

import com.pudutech.lib_update.listener.IShowInstallProgress;
import com.pudutech.lib_update.listener.IShowProgress2;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.lib_update.util.FileUtil;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0014\u0010\u0006\u001a\u00020\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, m3961d2 = {"com/pudutech/lib_update/UpdateManager$downloadApkAndInstallSilent2$1", "Lcom/pudutech/pd_network/OssCallback;", "onCompleted", "", "url", "", "onError", "ex", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onProgressChanged", "bytesCurrent", "", "bytesTotal", "onStateChanged", "state", "Lcom/pudutech/pd_network/OssState;", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class UpdateManager$downloadApkAndInstallSilent2$1 implements OssCallback {
    final /* synthetic */ String $mVersionCode;
    final /* synthetic */ String $mVersionName;
    final /* synthetic */ File $outputFile;
    final /* synthetic */ IShowProgress2 $showDownFileProgress2;
    final /* synthetic */ IShowInstallProgress $showInstallProgress;
    final /* synthetic */ VerionResult $vr;

    @Override // com.pudutech.pd_network.OssCallback
    public void onProgressChanged(long bytesCurrent, long bytesTotal) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UpdateManager$downloadApkAndInstallSilent2$1(File file, VerionResult verionResult, IShowProgress2 iShowProgress2, String str, String str2, IShowInstallProgress iShowInstallProgress) {
        this.$outputFile = file;
        this.$vr = verionResult;
        this.$showDownFileProgress2 = iShowProgress2;
        this.$mVersionCode = str;
        this.$mVersionName = str2;
        this.$showInstallProgress = iShowInstallProgress;
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onCompleted(String url) {
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
        Intrinsics.checkParameterIsNotNull(url, "url");
        String calculateFileMD5 = FileUtil.INSTANCE.calculateFileMD5(this.$outputFile);
        Version version = this.$vr.getVersion();
        String md5 = version != null ? version.getMd5() : null;
        if (!Intrinsics.areEqual(md5, calculateFileMD5)) {
            NetWorkLog.INSTANCE.mo3279e("UpdateManager", "download error file check http md5: " + md5 + " & file md5 : " + calculateFileMD5);
            UpdateManager updateManager = UpdateManager.INSTANCE;
            coroutineScope2 = UpdateManager.scope;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, Dispatchers.getMain(), null, new UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$1(this, null), 2, null);
            return;
        }
        IShowProgress2 iShowProgress2 = this.$showDownFileProgress2;
        if (iShowProgress2 != null) {
            iShowProgress2.onFinish(this.$mVersionCode, this.$mVersionName);
        }
        UpdateManager updateManager2 = UpdateManager.INSTANCE;
        coroutineScope = UpdateManager.scope;
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2(this, null), 3, null);
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onError(Exception ex) {
        CoroutineScope coroutineScope;
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        UpdateManager updateManager = UpdateManager.INSTANCE;
        coroutineScope = UpdateManager.scope;
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new UpdateManager$downloadApkAndInstallSilent2$1$onError$1(this, ex, null), 2, null);
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onStateChanged(OssState state) {
        CoroutineScope coroutineScope;
        Intrinsics.checkParameterIsNotNull(state, "state");
        if (state == OssState.IN_PROGRESS) {
            UpdateManager updateManager = UpdateManager.INSTANCE;
            coroutineScope = UpdateManager.scope;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new UpdateManager$downloadApkAndInstallSilent2$1$onStateChanged$1(this, null), 2, null);
        }
    }
}
