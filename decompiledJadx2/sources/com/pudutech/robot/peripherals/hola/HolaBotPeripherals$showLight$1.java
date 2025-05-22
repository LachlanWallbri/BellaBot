package com.pudutech.robot.peripherals.hola;

import com.pudutech.robot.peripherals.config.LightBeltType;
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
/* compiled from: HolaBotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.hola.HolaBotPeripherals$showLight$1", m3970f = "HolaBotPeripherals.kt", m3971i = {0, 0}, m3972l = {335}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "data"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes6.dex */
public final class HolaBotPeripherals$showLight$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $headRGB;
    final /* synthetic */ LightBeltType $led;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7365p$;
    final /* synthetic */ HolaBotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HolaBotPeripherals$showLight$1(HolaBotPeripherals holaBotPeripherals, LightBeltType lightBeltType, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = holaBotPeripherals;
        this.$led = lightBeltType;
        this.$headRGB = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HolaBotPeripherals$showLight$1 holaBotPeripherals$showLight$1 = new HolaBotPeripherals$showLight$1(this.this$0, this.$led, this.$headRGB, completion);
        holaBotPeripherals$showLight$1.f7365p$ = (CoroutineScope) obj;
        return holaBotPeripherals$showLight$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HolaBotPeripherals$showLight$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7365p$;
            byte[] bArr = {-112, this.$led.getValue(), 0, LightMode.LIGHT_RGB.getValue(), UByte.m4528constructorimpl((byte) (this.$headRGB >>> 16)), UByte.m4528constructorimpl((byte) (this.$headRGB >>> 8)), UByte.m4528constructorimpl((byte) this.$headRGB)};
            HolaBotPeripherals holaBotPeripherals = this.this$0;
            this.L$0 = coroutineScope;
            this.L$1 = bArr;
            this.label = 1;
            if (holaBotPeripherals.m4476sendDatab7CxX8A(bArr, this) == coroutine_suspended) {
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
