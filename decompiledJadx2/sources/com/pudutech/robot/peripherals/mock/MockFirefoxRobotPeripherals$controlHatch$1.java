package com.pudutech.robot.peripherals.mock;

import com.pudutech.robot.peripherals.firefox.HatchesStatus;
import java.util.ArrayList;
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

/* compiled from: MockFirefoxRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.mock.MockFirefoxRobotPeripherals$controlHatch$1", m3970f = "MockFirefoxRobotPeripherals.kt", m3971i = {0}, m3972l = {43}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
final class MockFirefoxRobotPeripherals$controlHatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList $hatchs;
    final /* synthetic */ boolean $isOpen;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7368p$;
    final /* synthetic */ MockFirefoxRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MockFirefoxRobotPeripherals$controlHatch$1(MockFirefoxRobotPeripherals mockFirefoxRobotPeripherals, ArrayList arrayList, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mockFirefoxRobotPeripherals;
        this.$hatchs = arrayList;
        this.$isOpen = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MockFirefoxRobotPeripherals$controlHatch$1 mockFirefoxRobotPeripherals$controlHatch$1 = new MockFirefoxRobotPeripherals$controlHatch$1(this.this$0, this.$hatchs, this.$isOpen, completion);
        mockFirefoxRobotPeripherals$controlHatch$1.f7368p$ = (CoroutineScope) obj;
        return mockFirefoxRobotPeripherals$controlHatch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MockFirefoxRobotPeripherals$controlHatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f7368p$;
            this.label = 1;
            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.callbackHatchsControlState(this.$hatchs, this.$isOpen ? HatchesStatus.Opened : HatchesStatus.Closed);
        return Unit.INSTANCE;
    }
}
