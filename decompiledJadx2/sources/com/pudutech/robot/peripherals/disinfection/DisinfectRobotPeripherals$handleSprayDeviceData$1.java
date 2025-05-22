package com.pudutech.robot.peripherals.disinfection;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
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
/* compiled from: DisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$handleSprayDeviceData$1", m3970f = "DisinfectRobotPeripherals.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class DisinfectRobotPeripherals$handleSprayDeviceData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $bytes;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7329p$;
    final /* synthetic */ DisinfectRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisinfectRobotPeripherals$handleSprayDeviceData$1(DisinfectRobotPeripherals disinfectRobotPeripherals, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = disinfectRobotPeripherals;
        this.$bytes = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DisinfectRobotPeripherals$handleSprayDeviceData$1 disinfectRobotPeripherals$handleSprayDeviceData$1 = new DisinfectRobotPeripherals$handleSprayDeviceData$1(this.this$0, this.$bytes, completion);
        disinfectRobotPeripherals$handleSprayDeviceData$1.f7329p$ = (CoroutineScope) obj;
        return disinfectRobotPeripherals$handleSprayDeviceData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DisinfectRobotPeripherals$handleSprayDeviceData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Function1 function1;
        Function1 function12;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7329p$;
        function1 = this.this$0.onSprayDeviceOpenListener;
        if (function1 != null) {
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("current spray device open status ");
            byte b = (byte) 0;
            sb.append(UByte.m4528constructorimpl(this.$bytes[3]) != UByte.m4528constructorimpl(b));
            objArr[0] = sb.toString();
            Pdlog.m3273d("DisinfectRobotPeripherals", objArr);
            function12 = this.this$0.onSprayDeviceOpenListener;
            if (function12 != null) {
            }
        }
        return Unit.INSTANCE;
    }
}
