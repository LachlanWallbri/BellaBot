package com.pudutech.pd_network.storage.aws;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferState;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.StorageComponentKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AwsS3Client.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0016J \u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, m3961d2 = {"com/pudutech/pd_network/storage/aws/AwsS3Client$setTransferListener$1", "Lcom/amazonaws/mobileconnectors/s3/transferutility/TransferListener;", "onError", "", "id", "", "ex", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onProgressChanged", "bytesCurrent", "", "bytesTotal", "onStateChanged", "state", "Lcom/amazonaws/mobileconnectors/s3/transferutility/TransferState;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AwsS3Client$setTransferListener$1 implements TransferListener {
    final /* synthetic */ String $key;
    final /* synthetic */ AwsS3Client this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AwsS3Client$setTransferListener$1(AwsS3Client awsS3Client, String str) {
        this.this$0 = awsS3Client;
        this.$key = str;
    }

    @Override // com.amazonaws.mobileconnectors.p047s3.transferutility.TransferListener
    public void onStateChanged(int id, TransferState state) {
        String TAG;
        Intrinsics.checkParameterIsNotNull(state, "state");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        TAG = this.this$0.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3280i(TAG, "onStateChanged > id:" + id + " state:" + state);
        StorageComponentKt.toMain(new AwsS3Client$setTransferListener$1$onStateChanged$1(this, state, null));
    }

    @Override // com.amazonaws.mobileconnectors.p047s3.transferutility.TransferListener
    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
        StorageComponentKt.toMain(new AwsS3Client$setTransferListener$1$onProgressChanged$1(this, bytesCurrent, bytesTotal, null));
    }

    @Override // com.amazonaws.mobileconnectors.p047s3.transferutility.TransferListener
    public void onError(int id, Exception ex) {
        String TAG;
        Intrinsics.checkParameterIsNotNull(ex, "ex");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        TAG = this.this$0.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3280i(TAG, "onError > id:" + id + " ex:" + ex + ' ');
        StorageComponentKt.toMain(new AwsS3Client$setTransferListener$1$onError$1(this, ex, null));
    }
}
