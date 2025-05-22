package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
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
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$onConnectedChargingPile$1", m3970f = "MoveAction.kt", m3971i = {0, 1}, m3972l = {1416, 1417}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes5.dex */
public final class MoveAction$onConnectedChargingPile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5546p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$onConnectedChargingPile$1(MoveAction moveAction, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$onConnectedChargingPile$1 moveAction$onConnectedChargingPile$1 = new MoveAction$onConnectedChargingPile$1(this.this$0, completion);
        moveAction$onConnectedChargingPile$1.f5546p$ = (CoroutineScope) obj;
        return moveAction$onConnectedChargingPile$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$onConnectedChargingPile$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(1:(1:(8:5|6|7|8|(1:10)(1:16)|11|12|13)(2:19|20))(1:21))(2:27|(2:29|(1:31)))|22|(2:24|(1:26))|6|7|8|(0)(0)|11|12|13) */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0084, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0085, code lost:
    
        r1 = r5.this$0.TAG;
        com.pudutech.base.Pdlog.m3273d(r1, "onConnectedChargingPile Exception " + android.util.Log.getStackTraceString(r0));
        r5.this$0.notifyChargingPile(false);
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x005a A[Catch: Exception -> 0x0084, TryCatch #0 {Exception -> 0x0084, blocks: (B:8:0x0052, B:10:0x005a, B:16:0x006f), top: B:7:0x0052 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006f A[Catch: Exception -> 0x0084, TRY_LEAVE, TryCatch #0 {Exception -> 0x0084, blocks: (B:8:0x0052, B:10:0x005a, B:16:0x006f), top: B:7:0x0052 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        boolean isGoChargingTask;
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5546p$;
            job = this.this$0.errorJob;
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
                    ResultKt.throwOnFailure(obj);
                    isGoChargingTask = this.this$0.isGoChargingTask();
                    if (!isGoChargingTask) {
                        str2 = this.this$0.TAG;
                        Pdlog.m3273d(str2, "onConnectedChargingPile GoCharging --> Arrive");
                        this.this$0.notifyChargingPile(true);
                    } else {
                        str = this.this$0.TAG;
                        Pdlog.m3273d(str, "onConnectedChargingPile no in GoCharging Task");
                        this.this$0.notifyChargingPile(false);
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Job job2 = this.this$0.moveJob;
        if (job2 != null) {
            this.L$0 = coroutineScope;
            this.label = 2;
            if (JobKt.cancelAndJoin(job2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        isGoChargingTask = this.this$0.isGoChargingTask();
        if (!isGoChargingTask) {
        }
        return Unit.INSTANCE;
    }
}
