package com.pudutech.pd_network.storage.minio;

import android.content.Context;
import android.util.Log;
import com.pudutech.pd_network.IOSSClient;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.StorageComponentKt;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.credentials.Credentials;
import io.minio.credentials.Provider;
import io.minio.messages.ResponseDate;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MinioOssClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\u0016\u001a\u00020\u0013H\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/pd_network/storage/minio/MinioOssClient;", "Lcom/pudutech/pd_network/IOSSClient;", "context", "Landroid/content/Context;", "ossConfig", "Lcom/pudutech/pd_network/bean/StorageConfig;", "uploadConfig", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "(Landroid/content/Context;Lcom/pudutech/pd_network/bean/StorageConfig;Lcom/pudutech/pd_network/bean/PdUploadConfig;)V", "TAG", "", "callback", "Lcom/pudutech/pd_network/OssCallback;", "client", "Lio/minio/MinioClient;", "kotlin.jvm.PlatformType", "currentState", "Lcom/pudutech/pd_network/OssState;", "pause", "", "resume", "setCallback", "start", "stop", "upload", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MinioOssClient implements IOSSClient {
    private final String TAG;
    private OssCallback callback;
    private final MinioClient client;
    private final Context context;
    private final StorageConfig ossConfig;
    private final PdUploadConfig uploadConfig;

    public MinioOssClient(Context context, StorageConfig ossConfig, PdUploadConfig pdUploadConfig) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(ossConfig, "ossConfig");
        this.context = context;
        this.ossConfig = ossConfig;
        this.uploadConfig = pdUploadConfig;
        this.TAG = "MinioClient";
        this.client = MinioClient.builder().region(this.ossConfig.getRegion()).endpoint(this.ossConfig.getOss_url()).credentialsProvider(new Provider() { // from class: com.pudutech.pd_network.storage.minio.MinioOssClient$client$1
            @Override // io.minio.credentials.Provider
            public final Credentials fetch() {
                StorageConfig storageConfig;
                StorageConfig storageConfig2;
                StorageConfig storageConfig3;
                storageConfig = MinioOssClient.this.ossConfig;
                String access_key = storageConfig.getAccess_key();
                storageConfig2 = MinioOssClient.this.ossConfig;
                String access_secret = storageConfig2.getAccess_secret();
                storageConfig3 = MinioOssClient.this.ossConfig;
                return new Credentials(access_key, access_secret, storageConfig3.getSecurity_token(), new ResponseDate());
            }
        }).build();
    }

    @Override // com.pudutech.pd_network.IOSSClient
    public void delete(String objectKey, OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(objectKey, "objectKey");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        IOSSClient.DefaultImpls.delete(this, objectKey, callback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void upload() {
        Log.i(this.TAG, "upload > ");
        PdUploadConfig pdUploadConfig = this.uploadConfig;
        if (pdUploadConfig == null) {
            Intrinsics.throwNpe();
        }
        File file = pdUploadConfig.getFile();
        if (file == null) {
            Intrinsics.throwNpe();
        }
        StorageComponentKt.toMain(new MinioOssClient$upload$1(this, (PutObjectArgs) ((PutObjectArgs.Builder) ((PutObjectArgs.Builder) PutObjectArgs.builder().bucket(this.ossConfig.getBucket())).object(this.uploadConfig.getKey())).stream(new ProgressInputStream(new BufferedInputStream(new FileInputStream(file.getAbsolutePath())), new UploadListener() { // from class: com.pudutech.pd_network.storage.minio.MinioOssClient$upload$pmis$1
            @Override // com.pudutech.pd_network.storage.minio.UploadListener
            public void onProgress(long crtSize, long totalSize) {
                OssCallback ossCallback;
                ossCallback = MinioOssClient.this.callback;
                if (ossCallback != null) {
                    ossCallback.onProgressChanged(crtSize, totalSize);
                }
            }
        }), r1.available(), -1L).build(), null));
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    /* renamed from: currentState */
    public OssState getCrtState() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void start() {
        try {
            if (this.uploadConfig == null) {
                throw new IllegalArgumentException("cant upload PdUploadConfig==null");
            }
            upload();
        } catch (Exception e) {
            NetWorkLog.INSTANCE.mo3280i(this.TAG, "start.error " + e.getMessage());
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
    public void stop() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void resume() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void pause() {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void setCallback(OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }
}
