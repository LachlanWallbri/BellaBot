package com.pudutech.pd_network.storage;

import android.content.Context;
import com.pudutech.pd_network.IOSSClient;
import com.pudutech.pd_network.IOssTaskController;
import com.pudutech.pd_network.IStorage;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.BaseResponse;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageBucketType;
import com.pudutech.pd_network.bean.StorageConfig;
import com.pudutech.pd_network.bean.StorageIsp;
import com.pudutech.pd_network.bean.StorageRequest;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.ali.AliOssClient;
import com.pudutech.pd_network.storage.aws.AwsS3Client;
import com.pudutech.pd_network.storage.minio.MinioOssClient;
import com.pudutech.pd_network.utils.NetDataUtils;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: ext.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000i\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001#\u001a+\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001aJ\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00102+\b\u0002\u0010\u0013\u001a%\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0014j\u0004\u0018\u0001`\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\"\u0010\u000f\u001a\u00020\u001b*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f\u001aR\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001d2+\b\u0002\u0010 \u001a%\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0014j\u0004\u0018\u0001`\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!\u001aD\u0010\"\u001a\u00020#*\b\u0012\u0004\u0012\u00020\u00100$2+\b\u0002\u0010%\u001a%\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0014j\u0004\u0018\u0001`\u0019H\u0002¢\u0006\u0002\u0010&\u001a\u001a\u0010'\u001a\u00020\u001b*\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001f\u001aJ\u0010'\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\f\u001a\u00020\r2+\b\u0002\u0010\u0013\u001a%\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0014j\u0004\u0018\u0001`\u0019H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010(\"\u001b\u0010\u0000\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003*@\u0010)\"\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00180\u00142\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00180\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, m3961d2 = {"api", "Lcom/pudutech/pd_network/storage/Api;", "getApi", "()Lcom/pudutech/pd_network/storage/Api;", "api$delegate", "Lkotlin/Lazy;", "getStorageClient", "Lcom/pudutech/pd_network/IOSSClient;", "context", "Landroid/content/Context;", "bucketType", "Lcom/pudutech/pd_network/bean/StorageBucketType;", "config", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "(Landroid/content/Context;Lcom/pudutech/pd_network/bean/StorageBucketType;Lcom/pudutech/pd_network/bean/PdUploadConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "download", "", "Lcom/pudutech/pd_network/IStorage;", "url", "progress", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "", "Lcom/pudutech/pd_network/storage/ProgressCB;", "(Lcom/pudutech/pd_network/IStorage;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/pudutech/pd_network/IOssTaskController;", "downloadFile", "Ljava/io/File;", "ossCallback", "Lcom/pudutech/pd_network/OssCallback;", "progressCB", "(Lcom/pudutech/pd_network/IStorage;Ljava/lang/String;Ljava/io/File;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newCallback", "com/pudutech/pd_network/storage/ExtKt$newCallback$1", "Lkotlinx/coroutines/CancellableContinuation;", "callback", "(Lkotlinx/coroutines/CancellableContinuation;Lkotlin/jvm/functions/Function1;)Lcom/pudutech/pd_network/storage/ExtKt$newCallback$1;", "upload", "(Lcom/pudutech/pd_network/IStorage;Lcom/pudutech/pd_network/bean/PdUploadConfig;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ProgressCB", "pd_network_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ExtKt {
    private static final Lazy api$delegate = LazyKt.lazy(new Function0<Api>() { // from class: com.pudutech.pd_network.storage.ExtKt$api$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Api invoke() {
            return (Api) PdNetworkManager.f10310INSTANCE.createService(Api.class);
        }
    });

    private static final Api getApi() {
        return (Api) api$delegate.getValue();
    }

    public static final IOssTaskController upload(IStorage upload, PdUploadConfig config, OssCallback ossCallback) {
        Intrinsics.checkParameterIsNotNull(upload, "$this$upload");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Intrinsics.checkParameterIsNotNull(ossCallback, "ossCallback");
        IOssTaskController upload2 = upload.upload(config);
        upload2.setCallback(ossCallback);
        upload2.start();
        return upload2;
    }

    public static /* synthetic */ Object download$default(IStorage iStorage, String str, File file, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        return download(iStorage, str, file, function1, continuation);
    }

    public static final IOssTaskController download(IStorage download, String url, File downloadFile, OssCallback ossCallback) {
        Intrinsics.checkParameterIsNotNull(download, "$this$download");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(downloadFile, "downloadFile");
        Intrinsics.checkParameterIsNotNull(ossCallback, "ossCallback");
        IOssTaskController download2 = download.download(url, downloadFile);
        download2.setCallback(ossCallback);
        download2.start();
        return download2;
    }

    public static /* synthetic */ Object download$default(IStorage iStorage, String str, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        return download(iStorage, str, (Function1<? super Integer, Unit>) function1, (Continuation<? super String>) continuation);
    }

    public static final Object download(IStorage iStorage, String str, Function1<? super Integer, Unit> function1, Continuation<? super String> continuation) {
        String str2;
        String str3 = str;
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) str3, "/", 0, false, 6, (Object) null) + 1;
        int lastIndexOf$default2 = StringsKt.lastIndexOf$default((CharSequence) str3, "?", 0, false, 6, (Object) null);
        if (lastIndexOf$default2 < 0) {
            lastIndexOf$default2 = str.length();
        }
        if (lastIndexOf$default >= lastIndexOf$default2) {
            str2 = "";
        } else {
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            str2 = str.substring(lastIndexOf$default, lastIndexOf$default2);
            Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        }
        if (str2.length() == 0) {
            str2 = String.valueOf(System.currentTimeMillis()) + "pd.unnamed";
        }
        File file = new File(PdNetConfig.INSTANCE.getDownloadDir(), str2);
        while (file.exists()) {
            str2 = String.valueOf(System.currentTimeMillis()) + str2;
            file = new File(PdNetConfig.INSTANCE.getDownloadDir(), str2);
        }
        return download(iStorage, str, file, function1, continuation);
    }

    public static /* synthetic */ Object upload$default(IStorage iStorage, PdUploadConfig pdUploadConfig, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        return upload(iStorage, pdUploadConfig, function1, continuation);
    }

    static /* synthetic */ ExtKt$newCallback$1 newCallback$default(CancellableContinuation cancellableContinuation, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        return newCallback(cancellableContinuation, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.pudutech.pd_network.storage.ExtKt$newCallback$1] */
    public static final ExtKt$newCallback$1 newCallback(final CancellableContinuation<? super String> cancellableContinuation, final Function1<? super Integer, Unit> function1) {
        return new OssCallback() { // from class: com.pudutech.pd_network.storage.ExtKt$newCallback$1
            @Override // com.pudutech.pd_network.OssCallback
            public void onStateChanged(OssState state) {
                Intrinsics.checkParameterIsNotNull(state, "state");
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onProgressChanged(long bytesCurrent, long bytesTotal) {
                int i = (int) ((((float) bytesCurrent) / ((float) bytesTotal)) * 100);
                Function1 function12 = function1;
                if (function12 != null) {
                }
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onCompleted(String url) {
                Intrinsics.checkParameterIsNotNull(url, "url");
                CancellableContinuation cancellableContinuation2 = cancellableContinuation;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation2.resumeWith(Result.m4510constructorimpl(url));
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onError(Exception ex) {
                Intrinsics.checkParameterIsNotNull(ex, "ex");
                CancellableContinuation cancellableContinuation2 = cancellableContinuation;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation2.resumeWith(Result.m4510constructorimpl(ResultKt.createFailure(ex)));
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object getStorageClient(Context context, StorageBucketType storageBucketType, PdUploadConfig pdUploadConfig, Continuation<? super IOSSClient> continuation) {
        ExtKt$getStorageClient$1 extKt$getStorageClient$1;
        int i;
        BaseResponse baseResponse;
        if (continuation instanceof ExtKt$getStorageClient$1) {
            extKt$getStorageClient$1 = (ExtKt$getStorageClient$1) continuation;
            if ((extKt$getStorageClient$1.label & Integer.MIN_VALUE) != 0) {
                extKt$getStorageClient$1.label -= Integer.MIN_VALUE;
                Object obj = extKt$getStorageClient$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = extKt$getStorageClient$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetWorkLog.INSTANCE.mo3280i("getStorageClient", "getStorageClient > context:" + context + " bucketType:" + storageBucketType + " config:" + pdUploadConfig + ' ');
                    Api api = getApi();
                    StorageRequest storageRequest = new StorageRequest(NetDataUtils.INSTANCE.mac(), storageBucketType.getType(), "");
                    extKt$getStorageClient$1.L$0 = context;
                    extKt$getStorageClient$1.L$1 = storageBucketType;
                    extKt$getStorageClient$1.L$2 = pdUploadConfig;
                    extKt$getStorageClient$1.label = 1;
                    obj = api.getStorageConfig(storageRequest, extKt$getStorageClient$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    pdUploadConfig = (PdUploadConfig) extKt$getStorageClient$1.L$2;
                    context = (Context) extKt$getStorageClient$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                baseResponse = (BaseResponse) obj;
                if (baseResponse.getCode() == 0 || baseResponse.getData() == null) {
                    throw new Exception("OSS鉴权失败:" + baseResponse);
                }
                StorageIsp.Companion companion = StorageIsp.INSTANCE;
                Object data = baseResponse.getData();
                if (data == null) {
                    Intrinsics.throwNpe();
                }
                StorageIsp fromIsp = companion.fromIsp(((StorageConfig) data).getIsp());
                if (Intrinsics.areEqual(fromIsp, StorageIsp.OSS.INSTANCE)) {
                    Object data2 = baseResponse.getData();
                    if (data2 == null) {
                        Intrinsics.throwNpe();
                    }
                    return new AliOssClient(context, (StorageConfig) data2, pdUploadConfig);
                }
                if (Intrinsics.areEqual(fromIsp, StorageIsp.C5473S3.INSTANCE)) {
                    Object data3 = baseResponse.getData();
                    if (data3 == null) {
                        Intrinsics.throwNpe();
                    }
                    return new AwsS3Client(context, (StorageConfig) data3, pdUploadConfig);
                }
                if (Intrinsics.areEqual(fromIsp, StorageIsp.C5472PD.INSTANCE)) {
                    Object data4 = baseResponse.getData();
                    if (data4 == null) {
                        Intrinsics.throwNpe();
                    }
                    return new MinioOssClient(context, (StorageConfig) data4, pdUploadConfig);
                }
                throw new Exception("不支持的storage类型 " + fromIsp.getIsp());
            }
        }
        extKt$getStorageClient$1 = new ExtKt$getStorageClient$1(continuation);
        Object obj2 = extKt$getStorageClient$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = extKt$getStorageClient$1.label;
        if (i != 0) {
        }
        baseResponse = (BaseResponse) obj2;
        if (baseResponse.getCode() == 0) {
        }
        throw new Exception("OSS鉴权失败:" + baseResponse);
    }

    public static final Object download(final IStorage iStorage, final String str, final File file, final Function1<? super Integer, Unit> function1, Continuation<? super String> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        IOssTaskController download = iStorage.download(str, file);
        download.setCallback(new OssCallback() { // from class: com.pudutech.pd_network.storage.ExtKt$download$$inlined$suspendCancellableCoroutine$lambda$1
            @Override // com.pudutech.pd_network.OssCallback
            public void onStateChanged(OssState state) {
                Intrinsics.checkParameterIsNotNull(state, "state");
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onProgressChanged(long j, long j2) {
                int i = (int) ((((float) j) / ((float) j2)) * 100);
                Function1 function12 = function1;
                if (function12 != null) {
                }
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onCompleted(String url) {
                Intrinsics.checkParameterIsNotNull(url, "url");
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(url));
            }

            @Override // com.pudutech.pd_network.OssCallback
            public void onError(Exception ex) {
                Intrinsics.checkParameterIsNotNull(ex, "ex");
                CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m4510constructorimpl(ResultKt.createFailure(ex)));
            }
        });
        download.start();
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final Object upload(IStorage iStorage, PdUploadConfig pdUploadConfig, Function1<? super Integer, Unit> function1, Continuation<? super String> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        upload(iStorage, pdUploadConfig, newCallback(cancellableContinuationImpl, function1));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
