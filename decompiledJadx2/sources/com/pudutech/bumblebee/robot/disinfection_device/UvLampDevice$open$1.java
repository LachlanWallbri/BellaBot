package com.pudutech.bumblebee.robot.disinfection_device;

import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import java.util.Arrays;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: UvLampDevice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.disinfection_device.UvLampDevice$open$1", m3970f = "UvLampDevice.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class UvLampDevice$open$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $openUvLamp;
    final /* synthetic */ boolean $openUvLampDevice;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4776p$;
    final /* synthetic */ UvLampDevice this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UvLampDevice$open$1(UvLampDevice uvLampDevice, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uvLampDevice;
        this.$openUvLampDevice = z;
        this.$openUvLamp = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UvLampDevice$open$1 uvLampDevice$open$1 = new UvLampDevice$open$1(this.this$0, this.$openUvLampDevice, this.$openUvLamp, completion);
        uvLampDevice$open$1.f4776p$ = (CoroutineScope) obj;
        return uvLampDevice$open$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UvLampDevice$open$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        HardwareInterface hardwareInterface;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        byte b = this.$openUvLampDevice;
        byte b2 = this.$openUvLamp;
        hardwareInterface = this.this$0.hardware;
        if (hardwareInterface != null) {
            byte[] copyOf = Arrays.copyOf(new byte[]{Constans.CAN_REV_UV_SYS_RESULT, 8, 2, b, b2, 0, 0}, 7);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
            hardwareInterface.sendCAN(copyOf);
        }
        return Unit.INSTANCE;
    }
}
