package com.pudutech.pd_network.storage;

import android.content.Context;
import android.os.Build;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.OkDownloadProvider;
import com.liulishuo.okdownload.core.connection.DownloadOkHttp3Connection;
import com.pudutech.pd_network.IOssTaskController;
import com.pudutech.pd_network.IStorage;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageBucketType;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.task.DownloadTaskController;
import com.pudutech.pd_network.storage.task.UploadTaskController;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* compiled from: StorageComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/pd_network/storage/StorageComponent;", "Lcom/pudutech/pd_network/IStorage;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getScope$pd_network_release", "()Lkotlinx/coroutines/CoroutineScope;", RequestParameters.SUBRESOURCE_DELETE, "", "bucketType", "Lcom/pudutech/pd_network/bean/StorageBucketType;", "objectKey", "callback", "Lcom/pudutech/pd_network/OssCallback;", "download", "Lcom/pudutech/pd_network/IOssTaskController;", "url", "downloadFile", "Ljava/io/File;", "init", "upload", "config", "Lcom/pudutech/pd_network/bean/PdUploadConfig;", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class StorageComponent implements IStorage {
    public static final StorageComponent INSTANCE;
    private static final String TAG;
    private static Context context;
    private static final CoroutineScope scope;

    static {
        StorageComponent storageComponent = new StorageComponent();
        INSTANCE = storageComponent;
        TAG = storageComponent.getClass().getSimpleName();
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
    }

    private StorageComponent() {
    }

    public static final /* synthetic */ Context access$getContext$p(StorageComponent storageComponent) {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final CoroutineScope getScope$pd_network_release() {
        return scope;
    }

    public final void init(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        context = context2;
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "init > ");
        OkDownloadProvider.context = context2;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (Build.VERSION.SDK_INT >= 29) {
            Interceptor.Companion companion = Interceptor.Companion;
            builder.addInterceptor(new Interceptor() { // from class: com.pudutech.pd_network.storage.StorageComponent$$special$$inlined$-addInterceptor$1
                @Override // okhttp3.Interceptor
                public Response intercept(Interceptor.Chain chain) {
                    Intrinsics.checkParameterIsNotNull(chain, "chain");
                    return chain.proceed(chain.request().newBuilder().addHeader("Connection", "close").addHeader("Transfer-Encoding", "chunked").build());
                }
            });
        }
        builder.retryOnConnectionFailure(true);
        OkDownload.Builder builder2 = new OkDownload.Builder(context2);
        DownloadOkHttp3Connection.Factory factory = new DownloadOkHttp3Connection.Factory();
        factory.setBuilder(builder);
        try {
            OkDownload.setSingletonInstance(builder2.connectionFactory(factory).build());
        } catch (Exception e) {
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
            netWorkLog2.mo3280i(TAG3, "init > " + e.getMessage());
        }
    }

    @Override // com.pudutech.pd_network.IStorage
    public IOssTaskController upload(PdUploadConfig config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "upload > config:" + config + ' ');
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return new UploadTaskController(context2, config);
    }

    @Override // com.pudutech.pd_network.IStorage
    public IOssTaskController download(String url, File downloadFile) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(downloadFile, "downloadFile");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "download > url:" + url + " downloadFile:" + downloadFile + ' ');
        return new DownloadTaskController(url, downloadFile);
    }

    @Override // com.pudutech.pd_network.IStorage
    public void delete(StorageBucketType bucketType, String objectKey, OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(bucketType, "bucketType");
        Intrinsics.checkParameterIsNotNull(objectKey, "objectKey");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
        netWorkLog.mo3280i(TAG2, "delete > objectKey:" + objectKey + " callback:" + callback + ' ');
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new StorageComponent$delete$1(bucketType, objectKey, callback, null), 3, null);
    }
}
