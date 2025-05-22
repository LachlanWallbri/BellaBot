package com.pudutech.mirsdk.hardware;

import com.pudutech.rgbdlib.RGBDSensor;
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
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: RGBDInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$setParkingMode$2", m3970f = "RGBDInterfaceImpl.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class RGBDInterfaceImpl$setParkingMode$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $enable;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5940p$;
    final /* synthetic */ RGBDInterfaceImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RGBDInterfaceImpl$setParkingMode$2(RGBDInterfaceImpl rGBDInterfaceImpl, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = rGBDInterfaceImpl;
        this.$enable = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RGBDInterfaceImpl$setParkingMode$2 rGBDInterfaceImpl$setParkingMode$2 = new RGBDInterfaceImpl$setParkingMode$2(this.this$0, this.$enable, completion);
        rGBDInterfaceImpl$setParkingMode$2.f5940p$ = (CoroutineScope) obj;
        return rGBDInterfaceImpl$setParkingMode$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RGBDInterfaceImpl$setParkingMode$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RGBDSensor rGBDSensor;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        rGBDSensor = this.this$0.rgbdService;
        rGBDSensor.setParkingMode(this.$enable);
        this.this$0.getContainer().getStateListeners().notify(new Function2<IRgbdStatus, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$setParkingMode$2.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IRgbdStatus iRgbdStatus, String str) {
                invoke2(iRgbdStatus, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IRgbdStatus l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onParkingMode(RGBDInterfaceImpl$setParkingMode$2.this.$enable);
            }
        });
        this.this$0.setParkingModeJob = (Job) null;
        return Unit.INSTANCE;
    }
}
