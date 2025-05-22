package com.pudutech.bumblebee.robot.disinfection_device;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
/* compiled from: SprayDevice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.disinfection_device.SprayDevice$handleSpringReceiveData$1", m3970f = "SprayDevice.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class SprayDevice$handleSpringReceiveData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $bytes;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4765p$;
    final /* synthetic */ SprayDevice this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SprayDevice$handleSpringReceiveData$1(SprayDevice sprayDevice, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sprayDevice;
        this.$bytes = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SprayDevice$handleSpringReceiveData$1 sprayDevice$handleSpringReceiveData$1 = new SprayDevice$handleSpringReceiveData$1(this.this$0, this.$bytes, completion);
        sprayDevice$handleSpringReceiveData$1.f4765p$ = (CoroutineScope) obj;
        return sprayDevice$handleSpringReceiveData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SprayDevice$handleSpringReceiveData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Function1<Boolean, Unit> onSpraySpringOpenStatusListener$Robot_release = this.this$0.getOnSpraySpringOpenStatusListener$Robot_release();
        if (onSpraySpringOpenStatusListener$Robot_release != null) {
            onSpraySpringOpenStatusListener$Robot_release.invoke(Boxing.boxBoolean(UByteArray.m4577getimpl(this.$bytes, 3) == UByte.m4528constructorimpl((byte) 1)));
        }
        return Unit.INSTANCE;
    }
}
