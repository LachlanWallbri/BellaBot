package com.pudutech.disinfect.baselib.maintenance;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.KtxKt;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.download.CDownloadFileManager;
import com.pudutech.disinfect.baselib.network.download.OnDownloadFileListener;
import com.pudutech.disinfect.baselib.network.req.FileUpdateReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.FileUpdateResponse;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import com.pudutech.remotemaintenance.BuildConfig;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RemoteMaintenanceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$start$1", m3970f = "RemoteMaintenanceManager.kt", m3971i = {0, 0, 0, 0}, m3972l = {50}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "versionCode", "reqData", "i"}, m3975s = {"L$0", "I$0", "L$1", "I$1"})
/* loaded from: classes4.dex */
public final class RemoteMaintenanceManager$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5045p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemoteMaintenanceManager$start$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RemoteMaintenanceManager$start$1 remoteMaintenanceManager$start$1 = new RemoteMaintenanceManager$start$1(completion);
        remoteMaintenanceManager$start$1.f5045p$ = (CoroutineScope) obj;
        return remoteMaintenanceManager$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteMaintenanceManager$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:4|(3:5|6|7)|8|9|(2:13|(2:15|16)(2:18|(4:20|21|22|23)(2:24|25)))|26|27|28|29) */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0148, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0149, code lost:
    
        r5 = 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00c4 A[Catch: Exception -> 0x014b, TryCatch #0 {Exception -> 0x014b, blocks: (B:8:0x0091, B:13:0x00be, B:15:0x00c4, B:18:0x00d0, B:20:0x00eb, B:35:0x0078, B:27:0x013c), top: B:34:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00d0 A[Catch: Exception -> 0x014b, TryCatch #0 {Exception -> 0x014b, blocks: (B:8:0x0091, B:13:0x00be, B:15:0x00c4, B:18:0x00d0, B:20:0x00eb, B:35:0x0078, B:27:0x013c), top: B:34:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x008e -> B:8:0x0091). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x014b -> B:32:0x014c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        int i;
        int i2;
        RemoteMaintenanceManager$start$1 remoteMaintenanceManager$start$1;
        FileUpdateReq fileUpdateReq;
        int i3;
        Object fileUpdate;
        int code;
        FileUpdateResponse fileUpdateResponse;
        String remote_maintenance_file_directory;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        int i5 = 1;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5045p$;
            int versionCode = PackageUtil.INSTANCE.getVersionCode(KtxKt.getAppContext(), BuildConfig.APPLICATION_ID);
            Pdlog.m3273d("RemoteMaintenanceManager", "packageName = com.pudutech.remotemaintenance, versionCode = " + versionCode);
            FileUpdateReq fileUpdateReq2 = new FileUpdateReq();
            fileUpdateReq2.setName("remote_maintenance");
            fileUpdateReq2.setVersionCode(versionCode);
            coroutineScope = coroutineScope2;
            obj2 = coroutine_suspended;
            i = versionCode;
            i2 = 3;
            remoteMaintenanceManager$start$1 = this;
            fileUpdateReq = fileUpdateReq2;
            i3 = 0;
            if (i3 <= i2) {
                try {
                } catch (Exception e) {
                    e = e;
                    Object[] objArr = new Object[i5];
                    objArr[0] = "request : " + Log.getStackTraceString(e);
                    Pdlog.m3274e("RemoteMaintenanceManager", objArr);
                    i3 += i5;
                    if (i3 <= i2) {
                    }
                    return Unit.INSTANCE;
                }
                NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
                remoteMaintenanceManager$start$1.L$0 = coroutineScope;
                remoteMaintenanceManager$start$1.I$0 = i;
                remoteMaintenanceManager$start$1.L$1 = fileUpdateReq;
                remoteMaintenanceManager$start$1.I$1 = i3;
                remoteMaintenanceManager$start$1.I$2 = i2;
                remoteMaintenanceManager$start$1.label = i5;
                fileUpdate = cloudApi.getFileUpdate(fileUpdateReq, remoteMaintenanceManager$start$1);
                if (fileUpdate == obj2) {
                    return obj2;
                }
                ApiResponse apiResponse = (ApiResponse) fileUpdate;
                Object[] objArr2 = new Object[i5];
                objArr2[0] = "请求成功，respData = " + apiResponse;
                Pdlog.m3273d("RemoteMaintenanceManager", objArr2);
                code = apiResponse.getCode();
                apiResponse.getMsg();
                fileUpdateResponse = (FileUpdateResponse) apiResponse.getData();
                if (code == 0) {
                    if (fileUpdateResponse.getVersion_code() != i) {
                    }
                }
                Object[] objArr3 = new Object[1];
                objArr3[0] = "数据返回异常，更新失败";
                Pdlog.m3277w("RemoteMaintenanceManager", objArr3);
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        if (i4 == 1) {
            i2 = this.I$2;
            i3 = this.I$1;
            fileUpdateReq = (FileUpdateReq) this.L$1;
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                fileUpdate = obj;
                obj2 = coroutine_suspended;
                remoteMaintenanceManager$start$1 = this;
            } catch (Exception e2) {
                e = e2;
                obj2 = coroutine_suspended;
                remoteMaintenanceManager$start$1 = this;
                Object[] objArr4 = new Object[i5];
                objArr4[0] = "request : " + Log.getStackTraceString(e);
                Pdlog.m3274e("RemoteMaintenanceManager", objArr4);
                i3 += i5;
                if (i3 <= i2) {
                }
                return Unit.INSTANCE;
            }
            ApiResponse apiResponse2 = (ApiResponse) fileUpdate;
            Object[] objArr22 = new Object[i5];
            objArr22[0] = "请求成功，respData = " + apiResponse2;
            Pdlog.m3273d("RemoteMaintenanceManager", objArr22);
            code = apiResponse2.getCode();
            apiResponse2.getMsg();
            fileUpdateResponse = (FileUpdateResponse) apiResponse2.getData();
            if (code == 0 && fileUpdateResponse != null) {
                if (fileUpdateResponse.getVersion_code() != i) {
                    Object[] objArr5 = new Object[i5];
                    objArr5[0] = "版本号相同，无需更新";
                    Pdlog.m3273d("RemoteMaintenanceManager", objArr5);
                    return Unit.INSTANCE;
                }
                String md5 = fileUpdateResponse.getMd5();
                String url = fileUpdateResponse.getUrl();
                int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) url, "/", 0, false, 6, (Object) null) + i5;
                if (url == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String substring = url.substring(lastIndexOf$default);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                Object[] objArr6 = new Object[i5];
                StringBuilder sb = new StringBuilder();
                sb.append("准备开始下载远程维护助手，md5 = ");
                sb.append(md5);
                sb.append(", url = ");
                sb.append(url);
                sb.append(", fileName = ");
                sb.append(substring);
                objArr6[0] = sb.toString();
                Pdlog.m3273d("RemoteMaintenanceManager", objArr6);
                CDownloadFileManager instance = CDownloadFileManager.INSTANCE.getINSTANCE();
                remote_maintenance_file_directory = RemoteMaintenanceManager.INSTANCE.getREMOTE_MAINTENANCE_FILE_DIRECTORY();
                instance.downloadFile(url, substring, remote_maintenance_file_directory, new C44431(md5, substring));
                return Unit.INSTANCE;
            }
            Object[] objArr32 = new Object[1];
            objArr32[0] = "数据返回异常，更新失败";
            Pdlog.m3277w("RemoteMaintenanceManager", objArr32);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: RemoteMaintenanceManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016J\u001a\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/disinfect/baselib/maintenance/RemoteMaintenanceManager$start$1$1", "Lcom/pudutech/disinfect/baselib/network/download/OnDownloadFileListener;", "onFailure", "", "fileUrl", "", "errMsg", "onProgress", "progress", "", "onStart", "onSuccessful", "filePath", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$start$1$1 */
    /* loaded from: classes4.dex */
    public static final class C44431 implements OnDownloadFileListener {
        final /* synthetic */ String $fileName;
        final /* synthetic */ String $md5;

        C44431(String str, String str2) {
            this.$md5 = str;
            this.$fileName = str2;
        }

        @Override // com.pudutech.disinfect.baselib.network.download.OnDownloadFileListener
        public void onStart(String fileUrl) {
            Pdlog.m3273d("RemoteMaintenanceManager", "开始下载远程维护助手，fileUrl = " + fileUrl);
        }

        @Override // com.pudutech.disinfect.baselib.network.download.OnDownloadFileListener
        public void onProgress(String fileUrl, int progress) {
            Pdlog.m3273d("RemoteMaintenanceManager", "远程维护助手下载进度，fileUrl = " + fileUrl + ", progress = " + progress + '%');
        }

        @Override // com.pudutech.disinfect.baselib.network.download.OnDownloadFileListener
        public void onSuccessful(String filePath, String fileUrl) {
            Pdlog.m3273d("RemoteMaintenanceManager", "远程维护助手下载成功，fileUrl = " + fileUrl + ", filePath = " + filePath);
            String str = filePath;
            if (!(str == null || str.length() == 0)) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new RemoteMaintenanceManager$start$1$1$onSuccessful$1(this, filePath, null), 3, null);
            } else {
                Pdlog.m3277w("RemoteMaintenanceManager", "安装失败，filePath is null or empty.");
            }
        }

        @Override // com.pudutech.disinfect.baselib.network.download.OnDownloadFileListener
        public void onFailure(String fileUrl, String errMsg) {
            Pdlog.m3273d("RemoteMaintenanceManager", "远程维护助手下载失败，fileUrl = " + fileUrl + ", errMsg = " + errMsg);
        }
    }
}
