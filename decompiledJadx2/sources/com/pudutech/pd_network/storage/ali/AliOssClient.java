package com.pudutech.pd_network.storage.ali;

import android.content.Context;
import android.os.Environment;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.DeleteObjectRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.pudutech.pd_network.IOSSClient;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.StorageComponentKt;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AliOssClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n*\u0001\u0019\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u001d\u001a\u00020\u001cH\u0016J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\nH\u0002J\b\u0010#\u001a\u00020\u001fH\u0016J\b\u0010$\u001a\u00020\u001fH\u0016J\u0010\u0010%\u001a\u00020\u001f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010&\u001a\u00020\u001fH\u0016J\b\u0010'\u001a\u00020\u001fH\u0016J\f\u0010(\u001a\u0006\u0012\u0002\b\u00030\u000fH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \r*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, m3961d2 = {"Lcom/pudutech/pd_network/storage/ali/AliOssClient;", "Lcom/pudutech/pd_network/IOSSClient;", "context", "Landroid/content/Context;", "storageConfig", "Lcom/pudutech/pd_network/bean/StorageConfig;", "uploadConfig", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "(Landroid/content/Context;Lcom/pudutech/pd_network/bean/StorageConfig;Lcom/pudutech/pd_network/bean/PdUploadConfig;)V", "BREAKPOINT_RECORD_DIR_PATH", "", "OSS_ENDPOINT", "TAG", "kotlin.jvm.PlatformType", "asyncTask", "Lcom/alibaba/sdk/android/oss/internal/OSSAsyncTask;", "callback", "Lcom/pudutech/pd_network/OssCallback;", "canResume", "", "mOSSClient", "Lcom/alibaba/sdk/android/oss/OSSClient;", "ossConfig", "Lcom/alibaba/sdk/android/oss/ClientConfiguration;", "ossProvider", "com/pudutech/pd_network/storage/ali/AliOssClient$ossProvider$1", "Lcom/pudutech/pd_network/storage/ali/AliOssClient$ossProvider$1;", "ossState", "Lcom/pudutech/pd_network/OssState;", "currentState", RequestParameters.SUBRESOURCE_DELETE, "", "objectKey", "getTime", "time", "pause", "resume", "setCallback", "start", "stop", "upload", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AliOssClient implements IOSSClient {
    private final String BREAKPOINT_RECORD_DIR_PATH;
    private final String OSS_ENDPOINT;
    private final String TAG;
    private OSSAsyncTask<?> asyncTask;
    private OssCallback callback;
    private boolean canResume;
    private final Context context;
    private final OSSClient mOSSClient;
    private final ClientConfiguration ossConfig;
    private final AliOssClient$ossProvider$1 ossProvider;
    private OssState ossState;
    private StorageConfig storageConfig;
    private final PdUploadConfig uploadConfig;

    /* JADX WARN: Type inference failed for: r3v5, types: [com.pudutech.pd_network.storage.ali.AliOssClient$ossProvider$1] */
    public AliOssClient(Context context, StorageConfig storageConfig, PdUploadConfig pdUploadConfig) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(storageConfig, "storageConfig");
        this.context = context;
        this.storageConfig = storageConfig;
        this.uploadConfig = pdUploadConfig;
        this.TAG = getClass().getSimpleName();
        this.OSS_ENDPOINT = "http://" + this.storageConfig.getRegion() + ".aliyuncs.com";
        this.ossProvider = new OSSFederationCredentialProvider() { // from class: com.pudutech.pd_network.storage.ali.AliOssClient$ossProvider$1
            @Override // com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider, com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
            public OSSFederationToken getFederationToken() {
                String TAG;
                StorageConfig storageConfig2;
                StorageConfig storageConfig3;
                StorageConfig storageConfig4;
                StorageConfig storageConfig5;
                StorageConfig storageConfig6;
                String time;
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                TAG = AliOssClient.this.TAG;
                Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                StringBuilder sb = new StringBuilder();
                sb.append("getFederationToken > ");
                storageConfig2 = AliOssClient.this.storageConfig;
                sb.append(storageConfig2);
                netWorkLog.mo3280i(TAG, sb.toString());
                storageConfig3 = AliOssClient.this.storageConfig;
                String access_key = storageConfig3.getAccess_key();
                storageConfig4 = AliOssClient.this.storageConfig;
                String access_secret = storageConfig4.getAccess_secret();
                storageConfig5 = AliOssClient.this.storageConfig;
                String security_token = storageConfig5.getSecurity_token();
                AliOssClient aliOssClient = AliOssClient.this;
                storageConfig6 = aliOssClient.storageConfig;
                time = aliOssClient.getTime(String.valueOf(storageConfig6.getExpire_time()));
                return new OSSFederationToken(access_key, access_secret, security_token, time);
            }
        };
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(15000);
        clientConfiguration.setSocketTimeout(15000);
        clientConfiguration.setMaxConcurrentRequest(3);
        clientConfiguration.setMaxErrorRetry(0);
        this.ossConfig = clientConfiguration;
        this.mOSSClient = new OSSClient(this.context, this.OSS_ENDPOINT, this.ossProvider, this.ossConfig);
        this.ossState = OssState.WAITING;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Intrinsics.checkExpressionValueIsNotNull(externalStorageDirectory, "Environment.getExternalStorageDirectory()");
        this.BREAKPOINT_RECORD_DIR_PATH = externalStorageDirectory.getPath() + "/pudu/oss_record";
        this.canResume = true;
    }

    @Override // com.pudutech.pd_network.IOSSClient
    public void delete(String objectKey, OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(objectKey, "objectKey");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (!this.mOSSClient.doesObjectExist(this.storageConfig.getBucket(), objectKey)) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3278d(TAG, " object not exists");
            callback.onError(new IllegalArgumentException(" object not exists"));
            return;
        }
        this.mOSSClient.asyncDeleteObject(new DeleteObjectRequest(this.storageConfig.getBucket(), objectKey), new AliOssClient$delete$1(this, callback));
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
            boolean doesObjectExist = this.mOSSClient.doesObjectExist(this.storageConfig.getBucket(), this.uploadConfig.getKey());
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String TAG = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3280i(TAG, "objectExist > " + doesObjectExist);
            if (doesObjectExist) {
                StorageComponentKt.toMain(new AliOssClient$start$1(this, null));
            } else {
                this.asyncTask = upload();
            }
        } catch (Exception e) {
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            String TAG2 = this.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            netWorkLog2.mo3280i(TAG2, "start.error " + e.getMessage());
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
        this.canResume = false;
        OSSAsyncTask<?> oSSAsyncTask = this.asyncTask;
        if (oSSAsyncTask != null) {
            oSSAsyncTask.cancel();
        }
        this.asyncTask = (OSSAsyncTask) null;
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void resume() {
        this.asyncTask = upload();
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void pause() {
        OSSAsyncTask<?> oSSAsyncTask = this.asyncTask;
        if (oSSAsyncTask != null) {
            oSSAsyncTask.cancel();
        }
        this.asyncTask = (OSSAsyncTask) null;
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void setCallback(OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    private final OSSAsyncTask<?> upload() {
        String bucket = this.storageConfig.getBucket();
        PdUploadConfig pdUploadConfig = this.uploadConfig;
        if (pdUploadConfig == null) {
            Intrinsics.throwNpe();
        }
        String key = pdUploadConfig.getKey();
        File file = this.uploadConfig.getFile();
        if (file == null) {
            Intrinsics.throwNpe();
        }
        String path = file.getPath();
        String str = this.BREAKPOINT_RECORD_DIR_PATH;
        if (!new File(str).exists()) {
            new File(str).mkdirs();
        }
        ResumableUploadRequest resumableUploadRequest = new ResumableUploadRequest(bucket, key, path, str);
        resumableUploadRequest.setPartSize(3145728L);
        resumableUploadRequest.setDeleteUploadOnCancelling(false);
        resumableUploadRequest.setProgressCallback(new OSSProgressCallback<Object>() { // from class: com.pudutech.pd_network.storage.ali.AliOssClient$upload$1

            /* compiled from: AliOssClient.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.pd_network.storage.ali.AliOssClient$upload$1$1", m3970f = "AliOssClient.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.pd_network.storage.ali.AliOssClient$upload$1$1 */
            /* loaded from: classes6.dex */
            static final class C54821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ long $currentSize;
                final /* synthetic */ long $totalSize;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f6859p$;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C54821(long j, long j2, Continuation continuation) {
                    super(2, continuation);
                    this.$currentSize = j;
                    this.$totalSize = j2;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C54821 c54821 = new C54821(this.$currentSize, this.$totalSize, completion);
                    c54821.f6859p$ = (CoroutineScope) obj;
                    return c54821;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C54821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    OssState ossState;
                    OssCallback ossCallback;
                    OssCallback ossCallback2;
                    OssState ossState2;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f6859p$;
                    ossState = AliOssClient.this.ossState;
                    if (ossState == OssState.WAITING) {
                        AliOssClient.this.ossState = OssState.IN_PROGRESS;
                        ossCallback2 = AliOssClient.this.callback;
                        if (ossCallback2 != null) {
                            ossState2 = AliOssClient.this.ossState;
                            ossCallback2.onStateChanged(ossState2);
                        }
                    }
                    ossCallback = AliOssClient.this.callback;
                    if (ossCallback != null) {
                        ossCallback.onProgressChanged(this.$currentSize, this.$totalSize);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // com.alibaba.sdk.android.oss.callback.OSSProgressCallback
            public final void onProgress(Object obj, long j, long j2) {
                StorageComponentKt.toMain(new C54821(j, j2, null));
            }
        });
        OSSAsyncTask<ResumableUploadResult> asyncResumableUpload = this.mOSSClient.asyncResumableUpload(resumableUploadRequest, new AliOssClient$upload$2(this));
        Intrinsics.checkExpressionValueIsNotNull(asyncResumableUpload, "mOSSClient.asyncResumabl…         }\n            })");
        return asyncResumableUpload;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTime(String time) {
        Date date = new Date(StringsKt.toLongOrNull(time) == null ? System.currentTimeMillis() : System.currentTimeMillis() + (Long.parseLong(time) * 1000));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = simpleDateFormat.format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "simpleDateFormat.format(data)");
        return format;
    }
}
