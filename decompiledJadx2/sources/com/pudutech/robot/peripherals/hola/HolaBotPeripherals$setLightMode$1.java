package com.pudutech.robot.peripherals.hola;

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
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.hola.HolaBotPeripherals$setLightMode$1", m3970f = "HolaBotPeripherals.kt", m3971i = {0, 0, 0, 0, 0}, m3972l = {361}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$forEach$iv", "element$iv", "it", "data"}, m3975s = {"L$0", "L$1", "L$3", "L$4", "L$5"})
/* loaded from: classes6.dex */
final class HolaBotPeripherals$setLightMode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LightColor $color;
    final /* synthetic */ LightBeltType[] $led;
    final /* synthetic */ LightMode $lightMode;
    final /* synthetic */ byte $params0;
    final /* synthetic */ byte $params1;
    final /* synthetic */ byte $params2;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7363p$;
    final /* synthetic */ HolaBotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HolaBotPeripherals$setLightMode$1(HolaBotPeripherals holaBotPeripherals, LightBeltType[] lightBeltTypeArr, LightColor lightColor, LightMode lightMode, byte b, byte b2, byte b3, Continuation continuation) {
        super(2, continuation);
        this.this$0 = holaBotPeripherals;
        this.$led = lightBeltTypeArr;
        this.$color = lightColor;
        this.$lightMode = lightMode;
        this.$params2 = b;
        this.$params1 = b2;
        this.$params0 = b3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HolaBotPeripherals$setLightMode$1 holaBotPeripherals$setLightMode$1 = new HolaBotPeripherals$setLightMode$1(this.this$0, this.$led, this.$color, this.$lightMode, this.$params2, this.$params1, this.$params0, completion);
        holaBotPeripherals$setLightMode$1.f7363p$ = (CoroutineScope) obj;
        return holaBotPeripherals$setLightMode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HolaBotPeripherals$setLightMode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0044  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x008c -> B:5:0x008f). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int length;
        CoroutineScope coroutineScope;
        LightBeltType[] lightBeltTypeArr;
        LightBeltType[] lightBeltTypeArr2;
        int i;
        HolaBotPeripherals$setLightMode$1 holaBotPeripherals$setLightMode$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f7363p$;
            LightBeltType[] lightBeltTypeArr3 = this.$led;
            length = lightBeltTypeArr3.length;
            coroutineScope = coroutineScope2;
            lightBeltTypeArr = lightBeltTypeArr3;
            lightBeltTypeArr2 = lightBeltTypeArr;
            i = 0;
            holaBotPeripherals$setLightMode$1 = this;
            if (i < length) {
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$1;
            length = this.I$0;
            lightBeltTypeArr = (LightBeltType[]) this.L$2;
            lightBeltTypeArr2 = (LightBeltType[]) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            holaBotPeripherals$setLightMode$1 = this;
            i++;
            if (i < length) {
                LightBeltType lightBeltType = lightBeltTypeArr[i];
                byte[] bArr = {-112, lightBeltType.getValue(), holaBotPeripherals$setLightMode$1.$color.getValue(), holaBotPeripherals$setLightMode$1.$lightMode.getValue(), holaBotPeripherals$setLightMode$1.$params2, holaBotPeripherals$setLightMode$1.$params1, holaBotPeripherals$setLightMode$1.$params0};
                HolaBotPeripherals holaBotPeripherals = holaBotPeripherals$setLightMode$1.this$0;
                holaBotPeripherals$setLightMode$1.L$0 = coroutineScope;
                holaBotPeripherals$setLightMode$1.L$1 = lightBeltTypeArr2;
                holaBotPeripherals$setLightMode$1.L$2 = lightBeltTypeArr;
                holaBotPeripherals$setLightMode$1.I$0 = length;
                holaBotPeripherals$setLightMode$1.I$1 = i;
                holaBotPeripherals$setLightMode$1.L$3 = lightBeltType;
                holaBotPeripherals$setLightMode$1.L$4 = lightBeltType;
                holaBotPeripherals$setLightMode$1.L$5 = bArr;
                holaBotPeripherals$setLightMode$1.label = 1;
                if (holaBotPeripherals.m4476sendDatab7CxX8A(bArr, holaBotPeripherals$setLightMode$1) == coroutine_suspended) {
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
