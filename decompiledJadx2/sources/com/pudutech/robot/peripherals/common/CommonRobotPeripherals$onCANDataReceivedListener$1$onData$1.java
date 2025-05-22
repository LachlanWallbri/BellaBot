package com.pudutech.robot.peripherals.common;

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

/* compiled from: CommonRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.common.CommonRobotPeripherals$onCANDataReceivedListener$1$onData$1", m3970f = "CommonRobotPeripherals.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class CommonRobotPeripherals$onCANDataReceivedListener$1$onData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7301p$;
    final /* synthetic */ CommonRobotPeripherals$onCANDataReceivedListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommonRobotPeripherals$onCANDataReceivedListener$1$onData$1(CommonRobotPeripherals$onCANDataReceivedListener$1 commonRobotPeripherals$onCANDataReceivedListener$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = commonRobotPeripherals$onCANDataReceivedListener$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CommonRobotPeripherals$onCANDataReceivedListener$1$onData$1 commonRobotPeripherals$onCANDataReceivedListener$1$onData$1 = new CommonRobotPeripherals$onCANDataReceivedListener$1$onData$1(this.this$0, completion);
        commonRobotPeripherals$onCANDataReceivedListener$1$onData$1.f7301p$ = (CoroutineScope) obj;
        return commonRobotPeripherals$onCANDataReceivedListener$1$onData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CommonRobotPeripherals$onCANDataReceivedListener$1$onData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7301p$;
        Pdlog.m3273d("CommonRobotPeripherals", "onCANDataReceivedListener SHUTDOWN_EVENT");
        this.this$0.this$0.handleShutdownEvent();
        return Unit.INSTANCE;
    }
}
