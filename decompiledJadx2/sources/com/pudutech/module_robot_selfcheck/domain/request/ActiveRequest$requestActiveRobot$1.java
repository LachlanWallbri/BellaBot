package com.pudutech.module_robot_selfcheck.domain.request;

import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.RobotActiveStatusResp;
import com.pudutech.module_robot_selfcheck.repository.ActiveRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ActiveRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/disinfect/baselib/network/response/ApiResponse;", "Lcom/pudutech/disinfect/baselib/network/response/RobotActiveStatusResp;", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$requestActiveRobot$1", m3970f = "ActiveRequest.kt", m3971i = {}, m3972l = {121}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class ActiveRequest$requestActiveRobot$1 extends SuspendLambda implements Function1<Continuation<? super ApiResponse<RobotActiveStatusResp>>, Object> {
    int label;
    final /* synthetic */ ActiveRequest this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActiveRequest$requestActiveRobot$1(ActiveRequest activeRequest, Continuation continuation) {
        super(1, continuation);
        this.this$0 = activeRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        return new ActiveRequest$requestActiveRobot$1(this.this$0, completion);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super ApiResponse<RobotActiveStatusResp>> continuation) {
        return ((ActiveRequest$requestActiveRobot$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ActiveRepository selfCheckRepository;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            selfCheckRepository = this.this$0.getSelfCheckRepository();
            this.label = 1;
            obj = selfCheckRepository.getActiveStatusByNetwork(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
