package com.pudutech.pd_network.storage.task;

import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.log.NetWorkLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UploadTaskController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0014\u0010\u0006\u001a\u00020\u00032\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"}, m3961d2 = {"com/pudutech/pd_network/storage/task/UploadTaskController$mCallback$1", "Lcom/pudutech/pd_network/OssCallback;", "onCompleted", "", "url", "", "onError", "ex", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onProgressChanged", "bytesCurrent", "", "bytesTotal", "onStateChanged", "state", "Lcom/pudutech/pd_network/OssState;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class UploadTaskController$mCallback$1 implements OssCallback {
    final /* synthetic */ UploadTaskController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UploadTaskController$mCallback$1(UploadTaskController uploadTaskController) {
        this.this$0 = uploadTaskController;
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onStateChanged(OssState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        NetWorkLog.INSTANCE.mo3280i(this.this$0.TAG, "onStateChanged > state:" + state + ' ');
        this.this$0.setCrtState(state);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = r2.this$0.callback;
     */
    @Override // com.pudutech.pd_network.OssCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onProgressChanged(long bytesCurrent, long bytesTotal) {
        OssState ossState;
        OssCallback ossCallback;
        ossState = this.this$0.crtState;
        if (ossState != OssState.IN_PROGRESS || ossCallback == null) {
            return;
        }
        ossCallback.onProgressChanged(bytesCurrent, bytesTotal);
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onCompleted(String url) {
        OssCallback ossCallback;
        Intrinsics.checkParameterIsNotNull(url, "url");
        NetWorkLog.INSTANCE.mo3280i(this.this$0.TAG, "onCompleted > url:" + url + ' ');
        ossCallback = this.this$0.callback;
        if (ossCallback != null) {
            ossCallback.onCompleted(url);
        }
    }

    @Override // com.pudutech.pd_network.OssCallback
    public void onError(Exception ex) {
        OssCallback ossCallback;
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        NetWorkLog.INSTANCE.mo3280i(this.this$0.TAG, "onError > ex:" + ex + ' ');
        ossCallback = this.this$0.callback;
        if (ossCallback != null) {
            ossCallback.onError(ex);
        }
    }
}
