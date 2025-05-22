package com.pudutech.mirsdk.mircore.module;

import android.os.SystemClock;
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
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: HardwareLinker.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.module.HardwareLinker$addLinker$1", m3970f = "HardwareLinker.kt", m3971i = {0}, m3972l = {97}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class HardwareLinker$addLinker$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6256p$;
    final /* synthetic */ HardwareLinker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareLinker$addLinker$1(HardwareLinker hardwareLinker, Continuation continuation) {
        super(2, continuation);
        this.this$0 = hardwareLinker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareLinker$addLinker$1 hardwareLinker$addLinker$1 = new HardwareLinker$addLinker$1(this.this$0, completion);
        hardwareLinker$addLinker$1.f6256p$ = (CoroutineScope) obj;
        return hardwareLinker$addLinker$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareLinker$addLinker$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
    
        r1 = r6.this$0.scheduleJob;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Job job;
        Job job2;
        ExecutorCoroutineDispatcher executorCoroutineDispatcher;
        Job launch$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6256p$;
            job = this.this$0.scheduleJob;
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
        HardwareLinker hardwareLinker = this.this$0;
        CoroutineScope mirCoreScope = MirCoreScopeKt.getMirCoreScope();
        executorCoroutineDispatcher = this.this$0.schCommContext;
        launch$default = BuildersKt__Builders_commonKt.launch$default(mirCoreScope, executorCoroutineDispatcher, null, new C52411(null), 2, null);
        hardwareLinker.scheduleJob = launch$default;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: HardwareLinker.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.module.HardwareLinker$addLinker$1$1", m3970f = "HardwareLinker.kt", m3971i = {0, 0, 0}, m3972l = {106}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "start", "pura"}, m3975s = {"L$0", "J$0", "J$1"})
    /* renamed from: com.pudutech.mirsdk.mircore.module.HardwareLinker$addLinker$1$1 */
    /* loaded from: classes6.dex */
    public static final class C52411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        long J$0;
        long J$1;
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6257p$;

        C52411(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C52411 c52411 = new C52411(completion);
            c52411.f6257p$ = (CoroutineScope) obj;
            return c52411;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C52411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x006e  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x006b  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x004f -> B:5:0x0065). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0054 -> B:5:0x0065). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0062 -> B:5:0x0065). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            C52411 c52411;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f6257p$;
                c52411 = this;
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else if (i == 1) {
                long j = this.J$1;
                long j2 = this.J$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                c52411 = this;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                if (CoroutineScopeKt.isActive(coroutineScope)) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    HardwareLinker$addLinker$1.this.this$0.sendScheduleInfo();
                    if (!CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                    long j3 = 150;
                    long elapsedRealtime2 = j3 - (SystemClock.elapsedRealtime() - elapsedRealtime);
                    if (1 <= elapsedRealtime2 && j3 >= elapsedRealtime2) {
                        c52411.L$0 = coroutineScope;
                        c52411.J$0 = elapsedRealtime;
                        c52411.J$1 = elapsedRealtime2;
                        c52411.label = 1;
                        if (DelayKt.delay(elapsedRealtime2, c52411) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    }
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        return Unit.INSTANCE;
                    }
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}
