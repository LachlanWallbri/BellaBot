package com.pudutech.robot.peripherals.hola;

import com.pudutech.robot.peripherals.config.LightBeltAnimationFrame;
import com.pudutech.robot.peripherals.config.LightBeltType;
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

/* compiled from: HolaBotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.hola.HolaBotPeripherals$playBreathing$1", m3970f = "HolaBotPeripherals.kt", m3971i = {0, 0, 0, 0}, m3972l = {377}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$forEach$iv", "element$iv", "it"}, m3975s = {"L$0", "L$1", "L$3", "L$4"})
/* loaded from: classes6.dex */
final class HolaBotPeripherals$playBreathing$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $f */
    final /* synthetic */ LightBeltAnimationFrame f7361$f;
    final /* synthetic */ LightBeltType[] $lightBelts;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7362p$;
    final /* synthetic */ HolaBotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HolaBotPeripherals$playBreathing$1(HolaBotPeripherals holaBotPeripherals, LightBeltType[] lightBeltTypeArr, LightBeltAnimationFrame lightBeltAnimationFrame, Continuation continuation) {
        super(2, continuation);
        this.this$0 = holaBotPeripherals;
        this.$lightBelts = lightBeltTypeArr;
        this.f7361$f = lightBeltAnimationFrame;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HolaBotPeripherals$playBreathing$1 holaBotPeripherals$playBreathing$1 = new HolaBotPeripherals$playBreathing$1(this.this$0, this.$lightBelts, this.f7361$f, completion);
        holaBotPeripherals$playBreathing$1.f7362p$ = (CoroutineScope) obj;
        return holaBotPeripherals$playBreathing$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HolaBotPeripherals$playBreathing$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0040  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x005e -> B:5:0x0061). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int length;
        CoroutineScope coroutineScope;
        LightBeltType[] lightBeltTypeArr;
        HolaBotPeripherals$playBreathing$1 holaBotPeripherals$playBreathing$1;
        int i;
        LightBeltType[] lightBeltTypeArr2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f7362p$;
            LightBeltType[] lightBeltTypeArr3 = this.$lightBelts;
            length = lightBeltTypeArr3.length;
            coroutineScope = coroutineScope2;
            lightBeltTypeArr = lightBeltTypeArr3;
            holaBotPeripherals$playBreathing$1 = this;
            i = 0;
            lightBeltTypeArr2 = lightBeltTypeArr;
            if (i < length) {
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$1;
            length = this.I$0;
            lightBeltTypeArr2 = (LightBeltType[]) this.L$2;
            lightBeltTypeArr = (LightBeltType[]) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            holaBotPeripherals$playBreathing$1 = this;
            i++;
            if (i < length) {
                LightBeltType lightBeltType = lightBeltTypeArr2[i];
                HolaBotPeripherals holaBotPeripherals = holaBotPeripherals$playBreathing$1.this$0;
                byte value = lightBeltType.getValue();
                LightBeltAnimationFrame lightBeltAnimationFrame = holaBotPeripherals$playBreathing$1.f7361$f;
                holaBotPeripherals$playBreathing$1.L$0 = coroutineScope;
                holaBotPeripherals$playBreathing$1.L$1 = lightBeltTypeArr;
                holaBotPeripherals$playBreathing$1.L$2 = lightBeltTypeArr2;
                holaBotPeripherals$playBreathing$1.I$0 = length;
                holaBotPeripherals$playBreathing$1.I$1 = i;
                holaBotPeripherals$playBreathing$1.L$3 = lightBeltType;
                holaBotPeripherals$playBreathing$1.L$4 = lightBeltType;
                holaBotPeripherals$playBreathing$1.label = 1;
                if (holaBotPeripherals.m4497sendLightCan225hgefk(value, lightBeltAnimationFrame, holaBotPeripherals$playBreathing$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i++;
                if (i < length) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
