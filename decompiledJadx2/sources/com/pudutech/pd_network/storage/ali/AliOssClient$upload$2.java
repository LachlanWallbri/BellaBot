package com.pudutech.pd_network.storage.ali;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.StorageComponentKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AliOssClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J&\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001c\u0010\u000b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/pd_network/storage/ali/AliOssClient$upload$2", "Lcom/alibaba/sdk/android/oss/callback/OSSCompletedCallback;", "Lcom/alibaba/sdk/android/oss/model/ResumableUploadRequest;", "Lcom/alibaba/sdk/android/oss/model/ResumableUploadResult;", "onFailure", "", "request", "clientException", "Lcom/alibaba/sdk/android/oss/ClientException;", "serviceException", "Lcom/alibaba/sdk/android/oss/ServiceException;", "onSuccess", SpeechUtility.TAG_RESOURCE_RESULT, "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AliOssClient$upload$2 implements OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> {
    final /* synthetic */ AliOssClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AliOssClient$upload$2(AliOssClient aliOssClient) {
        this.this$0 = aliOssClient;
    }

    @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
    public void onSuccess(ResumableUploadRequest request, ResumableUploadResult result) {
        String TAG;
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        TAG = this.this$0.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3280i(TAG, "onSuccess > request:" + request + " result:" + result + "  ");
        StorageComponentKt.toMain(new AliOssClient$upload$2$onSuccess$1(this, null));
    }

    @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
    public void onFailure(ResumableUploadRequest request, ClientException clientException, ServiceException serviceException) {
        String TAG;
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        TAG = this.this$0.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3280i(TAG, "onFailure > request:" + request + " clientException:" + clientException + " serviceException:" + serviceException + "  ");
        StorageComponentKt.toMain(new AliOssClient$upload$2$onFailure$1(this, clientException, serviceException, null));
    }
}
