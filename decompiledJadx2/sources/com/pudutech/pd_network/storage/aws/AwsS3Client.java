package com.pudutech.pd_network.storage.aws;

import android.content.Context;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferUtility;
import com.amazonaws.services.p048s3.AmazonS3Client;
import com.amazonaws.services.p048s3.model.CannedAccessControlList;
import com.pudutech.pd_network.IOSSClient;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AwsS3Client.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000_\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000*\u0001\u0013\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010\u001f\u001a\u00020\u001cH\u0016J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010!\u001a\u00020\u001cH\u0016J\b\u0010\"\u001a\u00020\u001cH\u0016J\u0014\u0010#\u001a\u00020\u001c*\u00020\u00162\u0006\u0010$\u001a\u00020\nH\u0002J\f\u0010%\u001a\u00020\u001a*\u00020&H\u0002R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, m3961d2 = {"Lcom/pudutech/pd_network/storage/aws/AwsS3Client;", "Lcom/pudutech/pd_network/IOSSClient;", "context", "Landroid/content/Context;", "storageConfig", "Lcom/pudutech/pd_network/bean/StorageConfig;", "config", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "(Landroid/content/Context;Lcom/pudutech/pd_network/bean/StorageConfig;Lcom/pudutech/pd_network/bean/PdUploadConfig;)V", "TAG", "", "kotlin.jvm.PlatformType", "awsConfig", "Lcom/amazonaws/mobile/config/AWSConfiguration;", "callback", "Lcom/pudutech/pd_network/OssCallback;", "client", "Lcom/amazonaws/services/s3/AmazonS3Client;", "credentials", "com/pudutech/pd_network/storage/aws/AwsS3Client$credentials$1", "Lcom/pudutech/pd_network/storage/aws/AwsS3Client$credentials$1;", "crtTransferObserver", "Lcom/amazonaws/mobileconnectors/s3/transferutility/TransferObserver;", "transferUtility", "Lcom/amazonaws/mobileconnectors/s3/transferutility/TransferUtility;", "currentState", "Lcom/pudutech/pd_network/OssState;", RequestParameters.SUBRESOURCE_DELETE, "", "objectKey", "pause", "resume", "setCallback", "start", "stop", "setTransferListener", TransferTable.COLUMN_KEY, "toOssState", "Lcom/amazonaws/mobileconnectors/s3/transferutility/TransferState;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AwsS3Client implements IOSSClient {
    private final String TAG;
    private final AWSConfiguration awsConfig;
    private OssCallback callback;
    private final AmazonS3Client client;
    private final PdUploadConfig config;
    private final Context context;
    private final AwsS3Client$credentials$1 credentials;
    private TransferObserver crtTransferObserver;
    private final StorageConfig storageConfig;
    private final TransferUtility transferUtility;

    /* JADX WARN: Type inference failed for: r2v3, types: [com.pudutech.pd_network.storage.aws.AwsS3Client$credentials$1] */
    public AwsS3Client(Context context, StorageConfig storageConfig, PdUploadConfig pdUploadConfig) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(storageConfig, "storageConfig");
        this.context = context;
        this.storageConfig = storageConfig;
        this.config = pdUploadConfig;
        this.TAG = getClass().getSimpleName();
        this.credentials = new AWSSessionCredentials() { // from class: com.pudutech.pd_network.storage.aws.AwsS3Client$credentials$1
            @Override // com.amazonaws.auth.AWSSessionCredentials
            public String getSessionToken() {
                StorageConfig storageConfig2;
                storageConfig2 = AwsS3Client.this.storageConfig;
                return storageConfig2.getSecurity_token();
            }

            @Override // com.amazonaws.auth.AWSCredentials
            public String getAWSAccessKeyId() {
                StorageConfig storageConfig2;
                storageConfig2 = AwsS3Client.this.storageConfig;
                return storageConfig2.getAccess_key();
            }

            @Override // com.amazonaws.auth.AWSCredentials
            public String getAWSSecretKey() {
                StorageConfig storageConfig2;
                storageConfig2 = AwsS3Client.this.storageConfig;
                return storageConfig2.getAccess_secret();
            }
        };
        this.client = new AmazonS3Client(this.credentials);
        this.awsConfig = new AWSConfiguration(ExtKt.newAWSConfiguration(this.storageConfig.getRegion(), this.storageConfig.getBucket()));
        TransferUtility build = TransferUtility.builder().context(this.context).awsConfiguration(this.awsConfig).s3Client(this.client).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "TransferUtility.builder(…(client)\n        .build()");
        this.transferUtility = build;
    }

    @Override // com.pudutech.pd_network.IOSSClient
    public void delete(String objectKey, OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(objectKey, "objectKey");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        boolean doesObjectExist = this.client.doesObjectExist(this.storageConfig.getBucket(), objectKey);
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3280i(TAG, "delete > exit:" + doesObjectExist);
        if (!doesObjectExist) {
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            String TAG2 = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            netWorkLog2.mo3278d(TAG2, "object not exists " + objectKey);
            callback.onError(new IllegalArgumentException("object not exists objectKey:" + objectKey));
        }
        this.client.deleteObject(this.storageConfig.getBucket(), objectKey);
        callback.onStateChanged(OssState.COMPLETED);
        callback.onCompleted("");
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    /* renamed from: currentState */
    public OssState getCrtState() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void start() {
        try {
            if (this.config == null) {
                throw new IllegalArgumentException("cant upload PdUploadConfig==null");
            }
            String key = this.config.getKey();
            boolean doesObjectExist = this.client.doesObjectExist(this.storageConfig.getBucket(), key);
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3280i(TAG, "start > exit:" + doesObjectExist);
            if (doesObjectExist) {
                OssCallback ossCallback = this.callback;
                if (ossCallback != null) {
                    ossCallback.onStateChanged(OssState.COMPLETED);
                }
                String url = this.client.getResourceUrl(this.storageConfig.getBucket(), key);
                OssCallback ossCallback2 = this.callback;
                if (ossCallback2 != null) {
                    Intrinsics.checkExpressionValueIsNotNull(url, "url");
                    ossCallback2.onCompleted(url);
                    return;
                }
                return;
            }
            TransferObserver upload = this.transferUtility.upload(this.storageConfig.getBucket(), key, this.config.getFile(), CannedAccessControlList.PublicReadWrite);
            this.crtTransferObserver = upload;
            setTransferListener(upload, key);
        } catch (Exception e) {
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            String TAG2 = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            netWorkLog2.mo3280i(TAG2, "start.error " + e.getMessage());
            OssCallback ossCallback3 = this.callback;
            if (ossCallback3 != null) {
                ossCallback3.onStateChanged(OssState.FAILED);
            }
            OssCallback ossCallback4 = this.callback;
            if (ossCallback4 != null) {
                ossCallback4.onError(e);
            }
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void stop() {
        try {
            TransferUtility transferUtility = this.transferUtility;
            TransferObserver transferObserver = this.crtTransferObserver;
            if (transferObserver == null) {
                Intrinsics.throwNpe();
            }
            transferUtility.cancel(transferObserver.getId());
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3280i(TAG, "stop.error " + e.getMessage());
            OssCallback ossCallback = this.callback;
            if (ossCallback != null) {
                ossCallback.onStateChanged(OssState.FAILED);
            }
            OssCallback ossCallback2 = this.callback;
            if (ossCallback2 != null) {
                ossCallback2.onError(e);
            }
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void resume() {
        try {
            TransferUtility transferUtility = this.transferUtility;
            TransferObserver transferObserver = this.crtTransferObserver;
            if (transferObserver == null) {
                Intrinsics.throwNpe();
            }
            transferUtility.resume(transferObserver.getId());
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3280i(TAG, "stop.resume " + e.getMessage());
            OssCallback ossCallback = this.callback;
            if (ossCallback != null) {
                ossCallback.onStateChanged(OssState.FAILED);
            }
            OssCallback ossCallback2 = this.callback;
            if (ossCallback2 != null) {
                ossCallback2.onError(e);
            }
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void pause() {
        try {
            TransferUtility transferUtility = this.transferUtility;
            TransferObserver transferObserver = this.crtTransferObserver;
            if (transferObserver == null) {
                Intrinsics.throwNpe();
            }
            transferUtility.pause(transferObserver.getId());
        } catch (Exception e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3280i(TAG, "stop.resume " + e.getMessage());
            OssCallback ossCallback = this.callback;
            if (ossCallback != null) {
                ossCallback.onStateChanged(OssState.FAILED);
            }
            OssCallback ossCallback2 = this.callback;
            if (ossCallback2 != null) {
                ossCallback2.onError(e);
            }
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void setCallback(OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    private final void setTransferListener(TransferObserver transferObserver, String str) {
        transferObserver.setTransferListener(new AwsS3Client$setTransferListener$1(this, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OssState toOssState(TransferState transferState) {
        switch (transferState) {
            case WAITING:
                return OssState.WAITING;
            case IN_PROGRESS:
                return OssState.IN_PROGRESS;
            case PAUSED:
                return OssState.PAUSED;
            case RESUMED_WAITING:
                return OssState.WAITING;
            case COMPLETED:
                return OssState.COMPLETED;
            case CANCELED:
                return OssState.CANCELED;
            case FAILED:
                return OssState.FAILED;
            case WAITING_FOR_NETWORK:
                return OssState.WAITING;
            case PART_COMPLETED:
                return OssState.COMPLETED;
            case PENDING_CANCEL:
                return OssState.UNKNOWN;
            case PENDING_PAUSE:
                return OssState.UNKNOWN;
            case PENDING_NETWORK_DISCONNECT:
                return OssState.UNKNOWN;
            case UNKNOWN:
                return OssState.UNKNOWN;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
