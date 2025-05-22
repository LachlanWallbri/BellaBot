package com.pudutech.robot.peripherals.common;

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
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.common.CommonRobotPeripherals$onCANDataReceivedListener$1$onData$4", m3970f = "CommonRobotPeripherals.kt", m3971i = {0}, m3972l = {228}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
final class CommonRobotPeripherals$onCANDataReceivedListener$1$onData$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $data;
    final /* synthetic */ int $id;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7304p$;
    final /* synthetic */ CommonRobotPeripherals$onCANDataReceivedListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommonRobotPeripherals$onCANDataReceivedListener$1$onData$4(CommonRobotPeripherals$onCANDataReceivedListener$1 commonRobotPeripherals$onCANDataReceivedListener$1, int i, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = commonRobotPeripherals$onCANDataReceivedListener$1;
        this.$id = i;
        this.$data = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CommonRobotPeripherals$onCANDataReceivedListener$1$onData$4 commonRobotPeripherals$onCANDataReceivedListener$1$onData$4 = new CommonRobotPeripherals$onCANDataReceivedListener$1$onData$4(this.this$0, this.$id, this.$data, completion);
        commonRobotPeripherals$onCANDataReceivedListener$1$onData$4.f7304p$ = (CoroutineScope) obj;
        return commonRobotPeripherals$onCANDataReceivedListener$1$onData$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CommonRobotPeripherals$onCANDataReceivedListener$1$onData$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7304p$;
            CommonRobotPeripherals commonRobotPeripherals = this.this$0.this$0;
            int i2 = this.$id;
            byte[] bArr = this.$data;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (commonRobotPeripherals.parseData(i2, bArr, this) == coroutine_suspended) {
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
