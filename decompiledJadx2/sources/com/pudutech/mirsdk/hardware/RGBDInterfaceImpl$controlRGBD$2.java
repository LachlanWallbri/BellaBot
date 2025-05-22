package com.pudutech.mirsdk.hardware;

import android.os.ParcelFileDescriptor;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.rgbdlib.RGBDSensor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: RGBDInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$controlRGBD$2", m3970f = "RGBDInterfaceImpl.kt", m3971i = {0}, m3972l = {189}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class RGBDInterfaceImpl$controlRGBD$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $on;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5936p$;
    final /* synthetic */ RGBDInterfaceImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RGBDInterfaceImpl$controlRGBD$2(RGBDInterfaceImpl rGBDInterfaceImpl, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = rGBDInterfaceImpl;
        this.$on = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RGBDInterfaceImpl$controlRGBD$2 rGBDInterfaceImpl$controlRGBD$2 = new RGBDInterfaceImpl$controlRGBD$2(this.this$0, this.$on, completion);
        rGBDInterfaceImpl$controlRGBD$2.f5936p$ = (CoroutineScope) obj;
        return rGBDInterfaceImpl$controlRGBD$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RGBDInterfaceImpl$controlRGBD$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RGBDSensor rGBDSensor;
        RGBDSensor rGBDSensor2;
        MachineInfo.RGBDType rGBDType;
        int i;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function4;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function42;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function43;
        Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> function44;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5936p$;
            if (this.$on) {
                rGBDSensor2 = this.this$0.rgbdService;
                rGBDType = this.this$0.rgbdVersion;
                i = this.this$0.machineMode;
                function4 = this.this$0.leftRGBDSubscription;
                function42 = this.this$0.rightRGBDSubscription;
                function43 = this.this$0.centerRGBDSubscription;
                function44 = this.this$0.downRGBDSubscription;
                rGBDSensor2.start(rGBDType, i, function4, function42, function43, function44);
            } else {
                rGBDSensor = this.this$0.rgbdService;
                rGBDSensor.releaseRGBD();
            }
            this.this$0.rgbdState.set(false);
            if (!this.$on) {
                return Unit.INSTANCE;
            }
            RGBDInterfaceImpl$controlRGBD$2$result$1 rGBDInterfaceImpl$controlRGBD$2$result$1 = new RGBDInterfaceImpl$controlRGBD$2$result$1(this, null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(10000L, rGBDInterfaceImpl$controlRGBD$2$result$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
