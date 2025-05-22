package com.pudutech.lib_update;

import com.pudutech.lib_update.config.ConfigContant;
import com.pudutech.lib_update.listener.IShowInstallProgress;
import com.pudutech.lib_update.listener.IShowProgress2;
import com.pudutech.lib_update.util.PackageUtils;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5425p$;
    final /* synthetic */ UpdateManager$downloadApkAndInstallSilent2$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2(UpdateManager$downloadApkAndInstallSilent2$1 updateManager$downloadApkAndInstallSilent2$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = updateManager$downloadApkAndInstallSilent2$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2 updateManager$downloadApkAndInstallSilent2$1$onCompleted$2 = new UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2(this.this$0, completion);
        updateManager$downloadApkAndInstallSilent2$1$onCompleted$2.f5425p$ = (CoroutineScope) obj;
        return updateManager$downloadApkAndInstallSilent2$1$onCompleted$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
        CoroutineScope coroutineScope3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope4 = this.f5425p$;
        try {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            StringBuilder sb = new StringBuilder();
            sb.append("监测安装接口 ");
            sb.append(this.this$0.$showInstallProgress == null);
            sb.append("  ");
            sb.append(this.this$0.$mVersionCode);
            sb.append(' ');
            sb.append(this.this$0.$mVersionName);
            netWorkLog.mo3280i("UpdateManager", sb.toString());
            IShowInstallProgress iShowInstallProgress = this.this$0.$showInstallProgress;
            if (iShowInstallProgress != null) {
                iShowInstallProgress.onStartInstall(this.this$0.$mVersionCode, this.this$0.$mVersionName);
            }
            NetWorkLog.INSTANCE.mo3280i("UpdateManager", "onStartInstall 接口监听start");
            File file = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, "update.apk");
            NetWorkLog.INSTANCE.mo3280i("UpdateManager", "静默安装----》apk路径" + file.getAbsolutePath());
            NetWorkLog.INSTANCE.mo3280i("UpdateManager", "onStartInstall 接口监听end");
            int installSilent = PackageUtils.installSilent(AppUpdateContext.context, file.getAbsolutePath());
            NetWorkLog.INSTANCE.mo3278d("UpdateManager", "静默安装----》result " + installSilent);
            if (installSilent != 1) {
                UpdateManager updateManager = UpdateManager.INSTANCE;
                coroutineScope3 = UpdateManager.scope;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope3, Dispatchers.getMain(), null, new C46621(null), 2, null);
            } else {
                UpdateManager updateManager2 = UpdateManager.INSTANCE;
                coroutineScope2 = UpdateManager.scope;
                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, Dispatchers.getMain(), null, new C46632(null), 2, null);
            }
        } catch (IOException e) {
            NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
            String localizedMessage = e.getLocalizedMessage();
            Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "e.localizedMessage");
            netWorkLog2.mo3279e("UpdateManager", localizedMessage);
            UpdateManager updateManager3 = UpdateManager.INSTANCE;
            coroutineScope = UpdateManager.scope;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new C46643(e, null), 2, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2$1", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2$1 */
    /* loaded from: classes5.dex */
    public static final class C46621 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5426p$;

        C46621(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46621 c46621 = new C46621(completion);
            c46621.f5426p$ = (CoroutineScope) obj;
            return c46621;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46621) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5426p$;
            IShowInstallProgress iShowInstallProgress = UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$showInstallProgress;
            if (iShowInstallProgress != null) {
                iShowInstallProgress.onFailInstall(UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionCode, UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionName);
            }
            IShowProgress2 iShowProgress2 = UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$showDownFileProgress2;
            if (iShowProgress2 != null) {
                iShowProgress2.onFail(new Exception(), UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionCode, UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionName);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2$2", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2$2 */
    /* loaded from: classes5.dex */
    public static final class C46632 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5427p$;

        C46632(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46632 c46632 = new C46632(completion);
            c46632.f5427p$ = (CoroutineScope) obj;
            return c46632;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46632) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5427p$;
            IShowInstallProgress iShowInstallProgress = UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$showInstallProgress;
            if (iShowInstallProgress != null) {
                iShowInstallProgress.onFinishInstall(UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionCode, UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionName);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2$3", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2$3 */
    /* loaded from: classes5.dex */
    public static final class C46643 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $e */
        final /* synthetic */ IOException f5428$e;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5429p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C46643(IOException iOException, Continuation continuation) {
            super(2, continuation);
            this.f5428$e = iOException;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46643 c46643 = new C46643(this.f5428$e, completion);
            c46643.f5429p$ = (CoroutineScope) obj;
            return c46643;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46643) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5429p$;
            IShowProgress2 iShowProgress2 = UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$showDownFileProgress2;
            if (iShowProgress2 != null) {
                iShowProgress2.onFail(this.f5428$e, UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionCode, UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionName);
            }
            IShowInstallProgress iShowInstallProgress = UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$showInstallProgress;
            if (iShowInstallProgress != null) {
                iShowInstallProgress.onFailInstall(UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionCode, UpdateManager$downloadApkAndInstallSilent2$1$onCompleted$2.this.this$0.$mVersionName);
            }
            return Unit.INSTANCE;
        }
    }
}
