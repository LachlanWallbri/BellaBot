package com.pudutech.bumblebee.robot.disinfection_device;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.Constans;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import java.util.Arrays;
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
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: SprayDevice.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.disinfection_device.SprayDevice$open$1", m3970f = "SprayDevice.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class SprayDevice$open$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $b */
    final /* synthetic */ boolean f4770$b;
    final /* synthetic */ boolean $b1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4771p$;
    final /* synthetic */ SprayDevice this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SprayDevice$open$1(SprayDevice sprayDevice, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sprayDevice;
        this.f4770$b = z;
        this.$b1 = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SprayDevice$open$1 sprayDevice$open$1 = new SprayDevice$open$1(this.this$0, this.f4770$b, this.$b1, completion);
        sprayDevice$open$1.f4771p$ = (CoroutineScope) obj;
        return sprayDevice$open$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SprayDevice$open$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        HardwareInterface hardwareInterface;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        byte b = this.f4770$b;
        byte b2 = this.$b1;
        str = this.this$0.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("open device ");
        sb.append(UByte.m4563toStringimpl(b));
        sb.append(" extract liquid ");
        sb.append(UByte.m4563toStringimpl(b2));
        sb.append(' ');
        byte[] copyOf = Arrays.copyOf(new byte[]{Constans.CAN_REV_SPRAY_SYS_RESULT, 7, 2, b, b2, 0, 0}, 7);
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
        sb.append(CommonKt.toHexString(copyOf));
        Pdlog.m3273d(str, sb.toString());
        hardwareInterface = this.this$0.hardware;
        if (hardwareInterface != null) {
            byte[] copyOf2 = Arrays.copyOf(new byte[]{Constans.CAN_REV_SPRAY_SYS_RESULT, 7, 2, b, b2, 0, 0}, 7);
            Intrinsics.checkExpressionValueIsNotNull(copyOf2, "java.util.Arrays.copyOf(this, size)");
            hardwareInterface.sendCAN(copyOf2);
        }
        return Unit.INSTANCE;
    }
}
