package com.pudutech.bumblebee.robot.led_screen;

import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.bumblebee.robot.protocol_utils.UByteArrayUtilsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LEDScreen.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.led_screen.LEDScreen$showAnimation$1", m3970f = "LEDScreen.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class LEDScreen$showAnimation$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LEDScreenMode $mode;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4781p$;
    final /* synthetic */ LEDScreen this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LEDScreen$showAnimation$1(LEDScreen lEDScreen, LEDScreenMode lEDScreenMode, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lEDScreen;
        this.$mode = lEDScreenMode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LEDScreen$showAnimation$1 lEDScreen$showAnimation$1 = new LEDScreen$showAnimation$1(this.this$0, this.$mode, completion);
        lEDScreen$showAnimation$1.f4781p$ = (CoroutineScope) obj;
        return lEDScreen$showAnimation$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LEDScreen$showAnimation$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        bArr = this.this$0.modeAnimation;
        UByteArray.m4582setVurrAj0(bArr, 1, this.$mode.getValue());
        bArr2 = this.this$0.modeAnimation;
        bArr3 = this.this$0.modeAnimation;
        UByteArray.m4582setVurrAj0(bArr2, 7, UByteArrayUtilsKt.m4332canXORChecksumGBYM_sE(bArr3));
        Function1<UByteArray, Unit> sender = this.this$0.getSender();
        if (sender != null) {
            bArr4 = this.this$0.modeAnimation;
            sender.invoke(UByteArray.m4570boximpl(bArr4));
        }
        return Unit.INSTANCE;
    }
}
