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

/* compiled from: FirefoxRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.firefox.FirefoxRobotPeripherals$controlHatch$1", m3970f = "FirefoxRobotPeripherals.kt", m3971i = {0, 0, 0, 0, 0}, m3972l = {65}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$forEach$iv", "element$iv", "hatch", "data"}, m3975s = {"L$0", "L$1", "L$3", "L$4", "L$5"})
/* loaded from: classes6.dex */
final class FirefoxRobotPeripherals$controlHatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList $hatchs;
    final /* synthetic */ boolean $isOpen;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7355p$;
    final /* synthetic */ FirefoxRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirefoxRobotPeripherals$controlHatch$1(FirefoxRobotPeripherals firefoxRobotPeripherals, ArrayList arrayList, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = firefoxRobotPeripherals;
        this.$hatchs = arrayList;
        this.$isOpen = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FirefoxRobotPeripherals$controlHatch$1 firefoxRobotPeripherals$controlHatch$1 = new FirefoxRobotPeripherals$controlHatch$1(this.this$0, this.$hatchs, this.$isOpen, completion);
        firefoxRobotPeripherals$controlHatch$1.f7355p$ = (CoroutineScope) obj;
        return firefoxRobotPeripherals$controlHatch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FirefoxRobotPeripherals$controlHatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        FirefoxRobotPeripherals$controlHatch$1 firefoxRobotPeripherals$controlHatch$1;
        Iterable iterable;
        Iterator it;
        byte convertHatchNumber;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f7355p$;
            ArrayList arrayList = this.$hatchs;
            coroutineScope = coroutineScope2;
            firefoxRobotPeripherals$controlHatch$1 = this;
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
            firefoxRobotPeripherals$controlHatch$1 = this;
        }
        while (it.hasNext()) {
            Object next = it.next();
            Hatch hatch = (Hatch) next;
            byte[] bArr = new byte[7];
            bArr[0] = CANConfig.INSTANCE.getCAN_CMD_HEAD_8B_PROTOCOL_CONTROL_HATCH();
            bArr[1] = UByte.m4528constructorimpl((byte) 1);
            bArr[2] = firefoxRobotPeripherals$controlHatch$1.$isOpen ? FirefoxRobotPeripherals.CONTROL_HATCH_OPEN : FirefoxRobotPeripherals.CONTROL_HATCH_CLOSE;
            convertHatchNumber = firefoxRobotPeripherals$controlHatch$1.this$0.convertHatchNumber(hatch);
            bArr[3] = convertHatchNumber;
            byte b = (byte) 0;
            bArr[4] = UByte.m4528constructorimpl(b);
            bArr[5] = UByte.m4528constructorimpl(b);
            bArr[6] = UByte.m4528constructorimpl(b);
            FirefoxRobotPeripherals firefoxRobotPeripherals = firefoxRobotPeripherals$controlHatch$1.this$0;
            firefoxRobotPeripherals$controlHatch$1.L$0 = coroutineScope;
            firefoxRobotPeripherals$controlHatch$1.L$1 = iterable;
            firefoxRobotPeripherals$controlHatch$1.L$2 = it;
            firefoxRobotPeripherals$controlHatch$1.L$3 = next;
            firefoxRobotPeripherals$controlHatch$1.L$4 = hatch;
            firefoxRobotPeripherals$controlHatch$1.L$5 = bArr;
            firefoxRobotPeripherals$controlHatch$1.label = 1;
            if (firefoxRobotPeripherals.m4476sendDatab7CxX8A(bArr, firefoxRobotPeripherals$controlHatch$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
