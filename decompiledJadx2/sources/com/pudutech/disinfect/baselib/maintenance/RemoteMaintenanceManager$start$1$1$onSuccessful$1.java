package com.pudutech.disinfect.baselib.maintenance;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$start$1;
import com.pudutech.disinfect.baselib.util.FileUtil;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RemoteMaintenanceManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.disinfect.baselib.maintenance.RemoteMaintenanceManager$start$1$1$onSuccessful$1", m3970f = "RemoteMaintenanceManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class RemoteMaintenanceManager$start$1$1$onSuccessful$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $filePath;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5046p$;
    final /* synthetic */ RemoteMaintenanceManager$start$1.C44431 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoteMaintenanceManager$start$1$1$onSuccessful$1(RemoteMaintenanceManager$start$1.C44431 c44431, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = c44431;
        this.$filePath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RemoteMaintenanceManager$start$1$1$onSuccessful$1 remoteMaintenanceManager$start$1$1$onSuccessful$1 = new RemoteMaintenanceManager$start$1$1$onSuccessful$1(this.this$0, this.$filePath, completion);
        remoteMaintenanceManager$start$1$1$onSuccessful$1.f5046p$ = (CoroutineScope) obj;
        return remoteMaintenanceManager$start$1$1$onSuccessful$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteMaintenanceManager$start$1$1$onSuccessful$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        File file;
        String remote_maintenance_file_directory;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5046p$;
        try {
            file = new File(this.$filePath);
        } catch (Exception e) {
            Pdlog.m3274e("RemoteMaintenanceManager", "onSuccessful : " + Log.getStackTraceString(e));
        }
        if (file.exists() && file.isFile()) {
            Pdlog.m3273d("RemoteMaintenanceManager", "serverMD5 = " + this.this$0.$md5 + ", fileMD5 = " + FileUtil.INSTANCE.getFileMD5(file));
            if (!(!Intrinsics.areEqual(r2, this.this$0.$md5))) {
                RemoteMaintenanceManager.INSTANCE.registerAppInstallReceiver(this.this$0.$fileName);
                PackageUtil packageUtil = PackageUtil.INSTANCE;
                remote_maintenance_file_directory = RemoteMaintenanceManager.INSTANCE.getREMOTE_MAINTENANCE_FILE_DIRECTORY();
                packageUtil.silentInstallApp((remote_maintenance_file_directory + File.separator) + this.this$0.$fileName);
                return Unit.INSTANCE;
            }
            Pdlog.m3277w("RemoteMaintenanceManager", "安装失败，文件MD5不匹配");
            return Unit.INSTANCE;
        }
        Pdlog.m3277w("RemoteMaintenanceManager", "安装失败，file isn't exists or isn't file.");
        return Unit.INSTANCE;
    }
}
