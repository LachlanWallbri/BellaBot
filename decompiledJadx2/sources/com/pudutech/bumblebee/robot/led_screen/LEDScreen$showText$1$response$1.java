package com.pudutech.bumblebee.robot.led_screen;

import com.pudutech.bumblebee.robot.protocol_utils.ProtocolUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LEDScreen.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "Lkotlin/UByteArray;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.led_screen.LEDScreen$showText$1$response$1", m3970f = "LEDScreen.kt", m3971i = {0}, m3972l = {73}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class LEDScreen$showText$1$response$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super UByteArray>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4783p$;
    final /* synthetic */ LEDScreen$showText$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LEDScreen$showText$1$response$1(LEDScreen$showText$1 lEDScreen$showText$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lEDScreen$showText$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LEDScreen$showText$1$response$1 lEDScreen$showText$1$response$1 = new LEDScreen$showText$1$response$1(this.this$0, completion);
        lEDScreen$showText$1$response$1.f4783p$ = (CoroutineScope) obj;
        return lEDScreen$showText$1$response$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super UByteArray> continuation) {
        return ((LEDScreen$showText$1$response$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ProtocolUtils protocolUtils;
        byte[] bArr;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4783p$;
            protocolUtils = this.this$0.this$0.getProtocolUtils();
            bArr = this.this$0.this$0.config;
            byte protocolHead = LEDScreen.INSTANCE.getProtocolHead();
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = protocolUtils.m4331sendCANAndWaitFaKdhUs(bArr, protocolHead, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
