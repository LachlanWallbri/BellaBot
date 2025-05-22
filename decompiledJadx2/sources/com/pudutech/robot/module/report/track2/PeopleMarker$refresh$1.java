package com.pudutech.robot.module.report.track2;

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
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: robot_solicit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.module.report.track2.PeopleMarker$refresh$1", m3970f = "robot_solicit.kt", m3971i = {0, 0}, m3972l = {157}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "timeMillis"}, m3975s = {"L$0", "J$0"})
/* loaded from: classes7.dex */
public final class PeopleMarker$refresh$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7208p$;
    final /* synthetic */ PeopleMarker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeopleMarker$refresh$1(PeopleMarker peopleMarker, Continuation continuation) {
        super(2, continuation);
        this.this$0 = peopleMarker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PeopleMarker$refresh$1 peopleMarker$refresh$1 = new PeopleMarker$refresh$1(this.this$0, completion);
        peopleMarker$refresh$1.f7208p$ = (CoroutineScope) obj;
        return peopleMarker$refresh$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PeopleMarker$refresh$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x0030 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        long j;
        long j2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7208p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j3 = this.J$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (j > System.currentTimeMillis()) {
            j2 = this.this$0.releaseTime;
            long currentTimeMillis = j2 - System.currentTimeMillis();
            if (currentTimeMillis > 0) {
                this.L$0 = coroutineScope;
                this.J$0 = currentTimeMillis;
                this.label = 1;
                if (DelayKt.delay(currentTimeMillis, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        this.this$0.calc();
        return Unit.INSTANCE;
    }
}
