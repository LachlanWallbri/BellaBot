package com.pudutech.lib_update;

import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.listener.SystemDownloadCallback;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.pd_network.log.NetWorkLog;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PuduSystemVersionManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$1", m3970f = "PuduSystemVersionManager.kt", m3971i = {0}, m3972l = {103}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class PuduSystemVersionManager$checkSystemUpdate$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $app_version_name;
    final /* synthetic */ String $channel_name;
    final /* synthetic */ IShowProgress $checkSystemListener;
    final /* synthetic */ String $mac;
    final /* synthetic */ String $product_name;
    final /* synthetic */ String $request_ver_code;
    final /* synthetic */ String $request_ver_name;
    final /* synthetic */ String $sys_build_id;
    final /* synthetic */ String $sys_ver_code;
    final /* synthetic */ String $sys_ver_name;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5396p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PuduSystemVersionManager$checkSystemUpdate$1(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, IShowProgress iShowProgress, Continuation continuation) {
        super(2, continuation);
        this.$sys_ver_name = str;
        this.$sys_ver_code = str2;
        this.$mac = str3;
        this.$product_name = str4;
        this.$request_ver_name = str5;
        this.$request_ver_code = str6;
        this.$app_version_name = str7;
        this.$sys_build_id = str8;
        this.$channel_name = str9;
        this.$checkSystemListener = iShowProgress;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PuduSystemVersionManager$checkSystemUpdate$1 puduSystemVersionManager$checkSystemUpdate$1 = new PuduSystemVersionManager$checkSystemUpdate$1(this.$sys_ver_name, this.$sys_ver_code, this.$mac, this.$product_name, this.$request_ver_name, this.$request_ver_code, this.$app_version_name, this.$sys_build_id, this.$channel_name, this.$checkSystemListener, completion);
        puduSystemVersionManager$checkSystemUpdate$1.f5396p$ = (CoroutineScope) obj;
        return puduSystemVersionManager$checkSystemUpdate$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PuduSystemVersionManager$checkSystemUpdate$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object checkNewVersion;
        Version version;
        CoroutineScope coroutineScope2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope3 = this.f5396p$;
                PuduSystemVersionManager puduSystemVersionManager = PuduSystemVersionManager.INSTANCE;
                String str = this.$sys_ver_name;
                String str2 = this.$sys_ver_code;
                String str3 = this.$mac;
                String str4 = this.$product_name;
                String str5 = this.$request_ver_name;
                String str6 = this.$request_ver_code;
                String str7 = this.$app_version_name;
                String str8 = this.$sys_build_id;
                String str9 = this.$channel_name;
                this.L$0 = coroutineScope3;
                this.label = 1;
                checkNewVersion = puduSystemVersionManager.checkNewVersion(str3, str, str2, str4, str5, str6, str7, str8, str9, this);
                if (checkNewVersion == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                checkNewVersion = obj;
            }
            version = (Version) checkNewVersion;
            NetWorkLog.INSTANCE.mo3280i("PuduSystemVersionManager", "checkSystemUpdate > " + version);
        } catch (Exception e) {
            PuduSystemVersionManager puduSystemVersionManager2 = PuduSystemVersionManager.INSTANCE;
            PuduSystemVersionManager.checkSystemUpdateRunning = false;
            NetWorkLog.INSTANCE.mo3279e("PuduSystemVersionManager", "checkSystemUpdate error " + e);
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            String localizedMessage = e.getLocalizedMessage();
            Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "e.localizedMessage");
            netWorkLog.mo3279e("PuduSystemVersionManager", localizedMessage);
            PuduSystemVersionManager puduSystemVersionManager3 = PuduSystemVersionManager.INSTANCE;
            coroutineScope = PuduSystemVersionManager.scope;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new C46513(e, null), 2, null);
        }
        if (version == null) {
            PuduSystemVersionManager puduSystemVersionManager4 = PuduSystemVersionManager.INSTANCE;
            coroutineScope2 = PuduSystemVersionManager.scope;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope2, Dispatchers.getMain(), null, new C46491(null), 2, null);
            NetWorkLog.INSTANCE.mo3278d("PuduSystemVersionManager", "res 有问题");
            return Unit.INSTANCE;
        }
        PuduSystemVersionManager.INSTANCE.downloadSystem(version, new SystemDownloadCallback() { // from class: com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$1.2
            @Override // com.pudutech.lib_update.listener.SystemDownloadCallback
            public void onStartProgress() {
                IShowProgress iShowProgress = PuduSystemVersionManager$checkSystemUpdate$1.this.$checkSystemListener;
                if (iShowProgress != null) {
                    iShowProgress.onStartProgress();
                }
            }

            @Override // com.pudutech.lib_update.listener.SystemDownloadCallback
            public void onProgress(long crtSize, long totalSize) {
                IShowProgress iShowProgress = PuduSystemVersionManager$checkSystemUpdate$1.this.$checkSystemListener;
                if (iShowProgress != null) {
                    iShowProgress.onProgress(crtSize, totalSize);
                }
            }

            @Override // com.pudutech.lib_update.listener.SystemDownloadCallback
            public void onFail(Throwable e2) {
                Intrinsics.checkParameterIsNotNull(e2, "e");
                PuduSystemVersionManager puduSystemVersionManager5 = PuduSystemVersionManager.INSTANCE;
                PuduSystemVersionManager.checkSystemUpdateRunning = false;
                IShowProgress iShowProgress = PuduSystemVersionManager$checkSystemUpdate$1.this.$checkSystemListener;
                if (iShowProgress != null) {
                    iShowProgress.onFail(e2);
                }
            }

            @Override // com.pudutech.lib_update.listener.SystemDownloadCallback
            public void onFinish(File outputFile) {
                Intrinsics.checkParameterIsNotNull(outputFile, "outputFile");
                PuduSystemVersionManager puduSystemVersionManager5 = PuduSystemVersionManager.INSTANCE;
                PuduSystemVersionManager.checkSystemUpdateRunning = true;
                IShowProgress iShowProgress = PuduSystemVersionManager$checkSystemUpdate$1.this.$checkSystemListener;
                if (iShowProgress != null) {
                    iShowProgress.onFinish();
                }
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PuduSystemVersionManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$1$1", m3970f = "PuduSystemVersionManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$1$1 */
    /* loaded from: classes5.dex */
    public static final class C46491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5397p$;

        C46491(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46491 c46491 = new C46491(completion);
            c46491.f5397p$ = (CoroutineScope) obj;
            return c46491;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5397p$;
            IShowProgress iShowProgress = PuduSystemVersionManager$checkSystemUpdate$1.this.$checkSystemListener;
            if (iShowProgress != null) {
                iShowProgress.onFinish();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PuduSystemVersionManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$1$3", m3970f = "PuduSystemVersionManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.PuduSystemVersionManager$checkSystemUpdate$1$3 */
    /* loaded from: classes5.dex */
    public static final class C46513 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $e */
        final /* synthetic */ Exception f5398$e;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5399p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C46513(Exception exc, Continuation continuation) {
            super(2, continuation);
            this.f5398$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46513 c46513 = new C46513(this.f5398$e, completion);
            c46513.f5399p$ = (CoroutineScope) obj;
            return c46513;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46513) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5399p$;
            IShowProgress iShowProgress = PuduSystemVersionManager$checkSystemUpdate$1.this.$checkSystemListener;
            if (iShowProgress != null) {
                iShowProgress.onFail(this.f5398$e);
            }
            return Unit.INSTANCE;
        }
    }
}
