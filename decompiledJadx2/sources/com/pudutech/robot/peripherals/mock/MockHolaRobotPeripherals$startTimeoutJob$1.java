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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MockHolaRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.mock.MockHolaRobotPeripherals$startTimeoutJob$1", m3970f = "MockHolaRobotPeripherals.kt", m3971i = {0, 0}, m3972l = {65}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "currentTimeMillis"}, m3975s = {"L$0", "J$0"})
/* loaded from: classes6.dex */
public final class MockHolaRobotPeripherals$startTimeoutJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7371p$;
    final /* synthetic */ MockHolaRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MockHolaRobotPeripherals$startTimeoutJob$1(MockHolaRobotPeripherals mockHolaRobotPeripherals, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mockHolaRobotPeripherals;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MockHolaRobotPeripherals$startTimeoutJob$1 mockHolaRobotPeripherals$startTimeoutJob$1 = new MockHolaRobotPeripherals$startTimeoutJob$1(this.this$0, completion);
        mockHolaRobotPeripherals$startTimeoutJob$1.f7371p$ = (CoroutineScope) obj;
        return mockHolaRobotPeripherals$startTimeoutJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MockHolaRobotPeripherals$startTimeoutJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x003a -> B:5:0x003d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        long j;
        CoroutineScope coroutineScope;
        MockHolaRobotPeripherals$startTimeoutJob$1 mockHolaRobotPeripherals$startTimeoutJob$1;
        ArrayList arrayList;
        boolean z;
        boolean z2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            j = 0;
            coroutineScope = this.f7371p$;
            mockHolaRobotPeripherals$startTimeoutJob$1 = this;
            if (j < 3000) {
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j = this.J$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            mockHolaRobotPeripherals$startTimeoutJob$1 = this;
            j += 1000;
            if (j < 3000) {
                mockHolaRobotPeripherals$startTimeoutJob$1.L$0 = coroutineScope;
                mockHolaRobotPeripherals$startTimeoutJob$1.J$0 = j;
                mockHolaRobotPeripherals$startTimeoutJob$1.label = 1;
                if (DelayKt.delay(1000L, mockHolaRobotPeripherals$startTimeoutJob$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                j += 1000;
                if (j < 3000) {
                    arrayList = mockHolaRobotPeripherals$startTimeoutJob$1.this$0.currentControlHatchs;
                    if (arrayList != null) {
                        z = mockHolaRobotPeripherals$startTimeoutJob$1.this$0.isOpen;
                        if (z) {
                            MockHolaRobotPeripherals mockHolaRobotPeripherals = mockHolaRobotPeripherals$startTimeoutJob$1.this$0;
                            z2 = mockHolaRobotPeripherals.isOpen;
                            mockHolaRobotPeripherals.callbackHatchsControlState(arrayList, z2 ? HatchesStatus.OpenFailed : HatchesStatus.CloseFailed);
                        }
                    }
                    mockHolaRobotPeripherals$startTimeoutJob$1.this$0.stopTimeoutJob();
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
