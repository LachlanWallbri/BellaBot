package com.pudutech.lib_update;

import com.loc.C3898x;
import com.pudutech.lib_update.config.ConfigContant;
import com.pudutech.lib_update.listener.IShowProgress;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2", m3970f = "UpdateManager.kt", m3971i = {0, 1, 2, 2, 2, 3, 3}, m3972l = {149, 152, 162, 168}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch", "upgradeFile", "installSilent", "$this$launch", C3898x.f4338g}, m3975s = {"L$0", "L$0", "L$0", "L$1", "I$0", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5415p$;
    final /* synthetic */ UpdateManager$downloadApkAndInstallSilent$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2(UpdateManager$downloadApkAndInstallSilent$1 updateManager$downloadApkAndInstallSilent$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = updateManager$downloadApkAndInstallSilent$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2 updateManager$downloadApkAndInstallSilent$1$onCompleted$2 = new UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2(this.this$0, completion);
        updateManager$downloadApkAndInstallSilent$1$onCompleted$2.f5415p$ = (CoroutineScope) obj;
        return updateManager$downloadApkAndInstallSilent$1$onCompleted$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2$1", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2$1 */
    /* loaded from: classes5.dex */
    public static final class C46591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5416p$;

        C46591(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46591 c46591 = new C46591(completion);
            c46591.f5416p$ = (CoroutineScope) obj;
            return c46591;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5416p$;
            IShowProgress iShowProgress = UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2.this.this$0.$showDownFileProgress;
            if (iShowProgress == null) {
                return null;
            }
            iShowProgress.onFinish();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c1 A[Catch: IOException -> 0x003b, TRY_LEAVE, TryCatch #0 {IOException -> 0x003b, blocks: (B:15:0x0036, B:19:0x007c, B:21:0x00c1), top: B:2:0x000d }] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v5 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
        int installSilent;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object obj2 = this.label;
        try {
        } catch (IOException e) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String localizedMessage = e.getLocalizedMessage();
            Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "e.localizedMessage");
            netWorkLog.mo3279e("UpdateManager", localizedMessage);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C46613 c46613 = new C46613(e, null);
            this.L$0 = obj2;
            this.L$1 = e;
            this.label = 4;
            if (BuildersKt.withContext(main, c46613, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        if (obj2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = this.f5415p$;
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            C46591 c46591 = new C46591(null);
            this.L$0 = coroutineScope3;
            this.label = 1;
            coroutineScope = coroutineScope3;
            if (BuildersKt.withContext(main2, c46591, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (obj2 != 1) {
                if (obj2 != 2) {
                    if (obj2 != 3) {
                        if (obj2 == 4) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    int i = this.I$0;
                    CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj2 = coroutineScope4;
                    return Unit.INSTANCE;
                }
                CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope2 = coroutineScope5;
                File file = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, "update.apk");
                NetWorkLog.INSTANCE.mo3280i("UpdateManager", "静默安装----》apk路径" + file.getAbsolutePath());
                installSilent = PackageUtils.installSilent(AppUpdateContext.context, file.getAbsolutePath());
                NetWorkLog.INSTANCE.mo3278d("UpdateManager", "静默安装----》result " + installSilent);
                obj2 = coroutineScope2;
                if (installSilent != 1) {
                    MainCoroutineDispatcher main3 = Dispatchers.getMain();
                    C46602 c46602 = new C46602(installSilent, null);
                    this.L$0 = coroutineScope2;
                    this.L$1 = file;
                    this.I$0 = installSilent;
                    this.label = 3;
                    obj2 = coroutineScope2;
                    if (BuildersKt.withContext(main3, c46602, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
            CoroutineScope coroutineScope6 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope6;
        }
        long installSilentDelayed = UpdateManager.INSTANCE.getInstallSilentDelayed();
        this.L$0 = coroutineScope;
        this.label = 2;
        coroutineScope2 = coroutineScope;
        if (DelayKt.delay(installSilentDelayed, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        File file2 = new File(ConfigContant.DEFAULT_SAVE_FILE_PATH, "update.apk");
        NetWorkLog.INSTANCE.mo3280i("UpdateManager", "静默安装----》apk路径" + file2.getAbsolutePath());
        installSilent = PackageUtils.installSilent(AppUpdateContext.context, file2.getAbsolutePath());
        NetWorkLog.INSTANCE.mo3278d("UpdateManager", "静默安装----》result " + installSilent);
        obj2 = coroutineScope2;
        if (installSilent != 1) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2$2", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2$2 */
    /* loaded from: classes5.dex */
    public static final class C46602 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $installSilent;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5417p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C46602(int i, Continuation continuation) {
            super(2, continuation);
            this.$installSilent = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46602 c46602 = new C46602(this.$installSilent, completion);
            c46602.f5417p$ = (CoroutineScope) obj;
            return c46602;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46602) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5417p$;
            IShowProgress iShowProgress = UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2.this.this$0.$showDownFileProgress;
            if (iShowProgress == null) {
                return null;
            }
            iShowProgress.onFail(new IOException("installSilent failed = " + this.$installSilent));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2$3", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2$3 */
    /* loaded from: classes5.dex */
    public static final class C46613 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $e */
        final /* synthetic */ IOException f5418$e;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5419p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C46613(IOException iOException, Continuation continuation) {
            super(2, continuation);
            this.f5418$e = iOException;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46613 c46613 = new C46613(this.f5418$e, completion);
            c46613.f5419p$ = (CoroutineScope) obj;
            return c46613;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46613) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5419p$;
            IShowProgress iShowProgress = UpdateManager$downloadApkAndInstallSilent$1$onCompleted$2.this.this$0.$showDownFileProgress;
            if (iShowProgress == null) {
                return null;
            }
            iShowProgress.onFail(this.f5418$e);
            return Unit.INSTANCE;
        }
    }
}
