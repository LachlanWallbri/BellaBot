package com.pudutech.robot.peripherals.disinfection;

import com.pudutech.bumblebee.robot.Constans;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$calibrationSpray$1", m3970f = "DisinfectRobotPeripherals.kt", m3971i = {0, 0, 0, 0}, m3972l = {359}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "byte1", "byte2", "data"}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes6.dex */
final class DisinfectRobotPeripherals$calibrationSpray$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $fog;
    final /* synthetic */ int $water;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7316p$;
    final /* synthetic */ DisinfectRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisinfectRobotPeripherals$calibrationSpray$1(DisinfectRobotPeripherals disinfectRobotPeripherals, int i, int i2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = disinfectRobotPeripherals;
        this.$water = i;
        this.$fog = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DisinfectRobotPeripherals$calibrationSpray$1 disinfectRobotPeripherals$calibrationSpray$1 = new DisinfectRobotPeripherals$calibrationSpray$1(this.this$0, this.$water, this.$fog, completion);
        disinfectRobotPeripherals$calibrationSpray$1.f7316p$ = (CoroutineScope) obj;
        return disinfectRobotPeripherals$calibrationSpray$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DisinfectRobotPeripherals$calibrationSpray$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7316p$;
            byte[] array = ByteBuffer.allocate(2).putShort((short) this.$water).array();
            byte[] array2 = ByteBuffer.allocate(2).putShort((short) this.$fog).array();
            byte[] bArr = {Constans.CAN_REV_SPRAY_SYS_RESULT, 7, 15, UByte.m4528constructorimpl(array[1]), UByte.m4528constructorimpl(array[0]), UByte.m4528constructorimpl(array2[1]), UByte.m4528constructorimpl(array2[0])};
            DisinfectRobotPeripherals disinfectRobotPeripherals = this.this$0;
            this.L$0 = coroutineScope;
            this.L$1 = array;
            this.L$2 = array2;
            this.L$3 = bArr;
            this.label = 1;
            if (disinfectRobotPeripherals.m4476sendDatab7CxX8A(bArr, this) == coroutine_suspended) {
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
