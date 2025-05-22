package com.pudutech.lib_update;

import com.pudutech.lib_update.listener.SystemDownloadCallback;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.lib_update.util.SystemCmdUtils;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0014\u0010\u0006\u001a\u00020\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, m3961d2 = {"com/pudutech/lib_update/PuduSystemVersionManager$downloadSystem$1", "Lcom/pudutech/pd_network/OssCallback;", "onCompleted", "", "url", "", "onError", "ex", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onProgressChanged", "bytesCurrent", "", "bytesTotal", "onStateChanged", "state", "Lcom/pudutech/pd_network/OssState;", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PuduSystemVersionManager$downloadSystem$1 implements OssCallback {
    final /* synthetic */ SystemDownloadCallback $callback;
    final /* synthetic */ File $outputFile;
    final /* synthetic */ Version $version;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PuduSystemVersionManager$downloadSystem$1(File file, Version version, SystemDownloadCallback systemDownloadCallback) {
        this.$outputFile = file;
        this.$version = version;
        this.$callback = systemDownloadCallback;
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onCompleted(String url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        NetWorkLog.INSTANCE.mo3280i("PuduSystemVersionManager", "onCompleted > url:" + url + ' ');
        PuduSystemVersionManager.INSTANCE.checkFile(this.$outputFile, this.$version, new PuduSystemVersionManager$downloadSystem$1$onCompleted$1(this));
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onError(Exception ex) {
        CoroutineScope coroutineScope;
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        NetWorkLog.INSTANCE.mo3280i("PuduSystemVersionManager", "onError > ex:" + ex + ' ');
        boolean delete = FileUtils.delete(this.$outputFile);
        NetWorkLog.INSTANCE.mo3279e("PuduSystemVersionManager", "onError delete " + this.$outputFile.getAbsoluteFile() + " = " + delete);
        if (!delete) {
            SystemCmdUtils.INSTANCE.execCommand("rm " + this.$outputFile.getAbsoluteFile(), true);
        }
        PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
        coroutineScope = PuduSystemVersionManager.scope;
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new PuduSystemVersionManager$downloadSystem$1$onError$1(this, ex, null), 2, null);
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onProgressChanged(long bytesCurrent, long bytesTotal) {
        CoroutineScope coroutineScope;
        NetWorkLog.INSTANCE.mo3280i("PuduSystemVersionManager", "onProgressChanged > bytesCurrent:" + bytesCurrent + " bytesTotal:" + bytesTotal + ' ');
        PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
        coroutineScope = PuduSystemVersionManager.scope;
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new PuduSystemVersionManager$downloadSystem$1$onProgressChanged$1(this, bytesCurrent, bytesTotal, null), 2, null);
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onStateChanged(OssState state) {
        CoroutineScope coroutineScope;
        Intrinsics.checkParameterIsNotNull(state, "state");
        NetWorkLog.INSTANCE.mo3280i("PuduSystemVersionManager", "onStateChanged > state:" + state + ' ');
        if (state == OssState.IN_PROGRESS) {
            PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
            coroutineScope = PuduSystemVersionManager.scope;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new PuduSystemVersionManager$downloadSystem$1$onStateChanged$1(this, null), 2, null);
        }
    }
}
