package com.pudutech.bumblebee.robot.activity;

import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
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
  classes2.dex
 */
/* compiled from: RecycleRobotPeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.activity.RecycleRobotPeripheralsActivity$onCreate$4", m3970f = "RecycleRobotPeripheralsActivity.kt", m3971i = {0}, m3972l = {77}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class RecycleRobotPeripheralsActivity$onCreate$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4749p$;
    final /* synthetic */ RecycleRobotPeripheralsActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecycleRobotPeripheralsActivity$onCreate$4(RecycleRobotPeripheralsActivity recycleRobotPeripheralsActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = recycleRobotPeripheralsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RecycleRobotPeripheralsActivity$onCreate$4 recycleRobotPeripheralsActivity$onCreate$4 = new RecycleRobotPeripheralsActivity$onCreate$4(this.this$0, completion);
        recycleRobotPeripheralsActivity$onCreate$4.f4749p$ = (CoroutineScope) obj;
        return recycleRobotPeripheralsActivity$onCreate$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RecycleRobotPeripheralsActivity$onCreate$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4749p$;
            AIDLConnection<RobotInterface> connection = this.this$0.getConnection();
            if (connection == null) {
                Intrinsics.throwNpe();
            }
            RecycleRobotPeripheralsActivity recycleRobotPeripheralsActivity = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (AIDLConnection.connect$default(connection, recycleRobotPeripheralsActivity, null, this, 2, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
