package com.pudutech.rgbdlib;

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
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: RGBDSensor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.rgbdlib.RGBDSensor$releaseRGBD$1", m3970f = "RGBDSensor.kt", m3971i = {0, 1, 2, 3}, m3972l = {979, 980, 981, 982}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "$this$runBlocking", "$this$runBlocking", "$this$runBlocking"}, m3975s = {"L$0", "L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class RGBDSensor$releaseRGBD$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7130p$;
    final /* synthetic */ RGBDSensor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RGBDSensor$releaseRGBD$1(RGBDSensor rGBDSensor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = rGBDSensor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RGBDSensor$releaseRGBD$1 rGBDSensor$releaseRGBD$1 = new RGBDSensor$releaseRGBD$1(this.this$0, completion);
        rGBDSensor$releaseRGBD$1.f7130p$ = (CoroutineScope) obj;
        return rGBDSensor$releaseRGBD$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RGBDSensor$releaseRGBD$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0070  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        Job job2;
        Job job3;
        Job job4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7130p$;
            job = this.this$0.readLeftJob;
            if (job != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    job3 = this.this$0.readCenterJob;
                    if (job3 != null) {
                        this.L$0 = coroutineScope;
                        this.label = 3;
                        if (JobKt.cancelAndJoin(job3, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    job4 = this.this$0.readDownJob;
                    if (job4 != null) {
                    }
                    this.this$0.closeRGBD();
                    this.this$0.finishInit = false;
                    return Unit.INSTANCE;
                }
                if (i != 3) {
                    if (i == 4) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.closeRGBD();
                        this.this$0.finishInit = false;
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                job4 = this.this$0.readDownJob;
                if (job4 != null) {
                    this.L$0 = coroutineScope;
                    this.label = 4;
                    if (JobKt.cancelAndJoin(job4, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                this.this$0.closeRGBD();
                this.this$0.finishInit = false;
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        job2 = this.this$0.readRightJob;
        if (job2 != null) {
            this.L$0 = coroutineScope;
            this.label = 2;
            if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        job3 = this.this$0.readCenterJob;
        if (job3 != null) {
        }
        job4 = this.this$0.readDownJob;
        if (job4 != null) {
        }
        this.this$0.closeRGBD();
        this.this$0.finishInit = false;
        return Unit.INSTANCE;
    }
}
