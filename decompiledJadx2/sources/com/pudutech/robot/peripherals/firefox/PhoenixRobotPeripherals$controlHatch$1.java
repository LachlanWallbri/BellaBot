package com.pudutech.robot.peripherals.firefox;

import com.pudutech.robot.peripherals.manager.CANConfig;
import java.util.ArrayList;
import java.util.Iterator;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PhoenixRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.firefox.PhoenixRobotPeripherals$controlHatch$1", m3970f = "PhoenixRobotPeripherals.kt", m3971i = {0, 0, 0, 0, 0}, m3972l = {153}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$forEach$iv", "element$iv", "hatch", "data"}, m3975s = {"L$0", "L$1", "L$3", "L$4", "L$5"})
/* loaded from: classes6.dex */
public final class PhoenixRobotPeripherals$controlHatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList $hatchs;
    final /* synthetic */ byte $operation;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7358p$;
    final /* synthetic */ PhoenixRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PhoenixRobotPeripherals$controlHatch$1(PhoenixRobotPeripherals phoenixRobotPeripherals, ArrayList arrayList, byte b, Continuation continuation) {
        super(2, continuation);
        this.this$0 = phoenixRobotPeripherals;
        this.$hatchs = arrayList;
        this.$operation = b;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PhoenixRobotPeripherals$controlHatch$1 phoenixRobotPeripherals$controlHatch$1 = new PhoenixRobotPeripherals$controlHatch$1(this.this$0, this.$hatchs, this.$operation, completion);
        phoenixRobotPeripherals$controlHatch$1.f7358p$ = (CoroutineScope) obj;
        return phoenixRobotPeripherals$controlHatch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PhoenixRobotPeripherals$controlHatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        PhoenixRobotPeripherals$controlHatch$1 phoenixRobotPeripherals$controlHatch$1;
        Iterable iterable;
        Iterator it;
        byte convertHatchNumber;
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f7358p$;
            ArrayList arrayList = this.$hatchs;
            coroutineScope = coroutineScope2;
            phoenixRobotPeripherals$controlHatch$1 = this;
            iterable = arrayList;
            it = arrayList.iterator();
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj2 = this.L$3;
            it = (Iterator) this.L$2;
            iterable = (Iterable) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            phoenixRobotPeripherals$controlHatch$1 = this;
        }
        while (it.hasNext()) {
            Object next = it.next();
            Hatch hatch = (Hatch) next;
            byte[] bArr = new byte[8];
            bArr[0] = CANConfig.INSTANCE.getCAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH();
            bArr[1] = phoenixRobotPeripherals$controlHatch$1.$operation == PhoenixRobotPeripherals.INSTANCE.getCONTROL_HATCH_OPERATION_INIT() ? PhoenixRobotPeripherals.INSTANCE.getCONTROL_HATCH_OPERATION_ASK() : phoenixRobotPeripherals$controlHatch$1.$operation;
            convertHatchNumber = phoenixRobotPeripherals$controlHatch$1.this$0.convertHatchNumber(hatch);
            bArr[2] = convertHatchNumber;
            z = phoenixRobotPeripherals$controlHatch$1.this$0.isOpen;
            bArr[3] = z ? PhoenixRobotPeripherals.CONTROL_HATCH_OPEN : PhoenixRobotPeripherals.CONTROL_HATCH_CLOSE;
            byte b = (byte) 0;
            bArr[4] = UByte.m4528constructorimpl(b);
            bArr[5] = UByte.m4528constructorimpl(b);
            bArr[6] = UByte.m4528constructorimpl(b);
            bArr[7] = UByte.m4528constructorimpl(b);
            PhoenixRobotPeripherals phoenixRobotPeripherals = phoenixRobotPeripherals$controlHatch$1.this$0;
            phoenixRobotPeripherals$controlHatch$1.L$0 = coroutineScope;
            phoenixRobotPeripherals$controlHatch$1.L$1 = iterable;
            phoenixRobotPeripherals$controlHatch$1.L$2 = it;
            phoenixRobotPeripherals$controlHatch$1.L$3 = next;
            phoenixRobotPeripherals$controlHatch$1.L$4 = hatch;
            phoenixRobotPeripherals$controlHatch$1.L$5 = bArr;
            phoenixRobotPeripherals$controlHatch$1.label = 1;
            if (phoenixRobotPeripherals.m4476sendDatab7CxX8A(bArr, phoenixRobotPeripherals$controlHatch$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
