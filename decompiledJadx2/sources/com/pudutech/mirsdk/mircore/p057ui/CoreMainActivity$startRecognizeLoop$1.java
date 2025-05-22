package com.pudutech.mirsdk.mircore.p057ui;

import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.mircore.MirCoreScopeKt;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CoreMainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.CoreMainActivity$startRecognizeLoop$1", m3970f = "CoreMainActivity.kt", m3971i = {0}, m3972l = {478}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class CoreMainActivity$startRecognizeLoop$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6267p$;
    final /* synthetic */ CoreMainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreMainActivity$startRecognizeLoop$1(CoreMainActivity coreMainActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = coreMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CoreMainActivity$startRecognizeLoop$1 coreMainActivity$startRecognizeLoop$1 = new CoreMainActivity$startRecognizeLoop$1(this.this$0, completion);
        coreMainActivity$startRecognizeLoop$1.f6267p$ = (CoroutineScope) obj;
        return coreMainActivity$startRecognizeLoop$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CoreMainActivity$startRecognizeLoop$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
    
        r1 = r6.this$0.recJob;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Job job;
        Job job2;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6267p$;
            job = this.this$0.recJob;
            if (job != null && job.isActive() && job2 != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        CoreMainActivity coreMainActivity = this.this$0;
        launch$default = BuildersKt__Builders_commonKt.launch$default(MirCoreScopeKt.getMirCoreScope(), null, null, new C52531(null), 3, null);
        coreMainActivity.recJob = launch$default;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: CoreMainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.CoreMainActivity$startRecognizeLoop$1$1", m3970f = "CoreMainActivity.kt", m3971i = {0, 0}, m3972l = {486}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "analysis_result"}, m3975s = {"L$0", "L$1"})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$startRecognizeLoop$1$1 */
    /* loaded from: classes6.dex */
    public static final class C52531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6268p$;

        C52531(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52531 c52531 = new C52531(completion);
            c52531.f6268p$ = (CoroutineScope) obj;
            return c52531;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            boolean z;
            boolean z2;
            final Vector2d analysisData;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f6268p$;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                z = CoreMainActivity$startRecognizeLoop$1.this.this$0.connect_status;
                if (!z) {
                    break;
                }
                z2 = CoreMainActivity$startRecognizeLoop$1.this.this$0.begin_recognize;
                if (!z2) {
                    break;
                }
                analysisData = CoreMainActivity$startRecognizeLoop$1.this.this$0.getAnalysisData();
                CoreMainActivity$startRecognizeLoop$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity.startRecognizeLoop.1.1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        CoreMainActivity$startRecognizeLoop$1.this.this$0.updateRecognizationRate(analysisData.getX(), analysisData.getY());
                    }
                });
                this.L$0 = coroutineScope;
                this.L$1 = analysisData;
                this.label = 1;
                if (DelayKt.delay(100L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }
}
