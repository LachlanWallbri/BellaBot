package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
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
import org.apache.commons.compress.archivers.tar.TarConstants;

/* compiled from: RGBDInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$controlRGBD$4", m3970f = "RGBDInterfaceImpl.kt", m3971i = {0}, m3972l = {TarConstants.VERSION_OFFSET}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class RGBDInterfaceImpl$controlRGBD$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5939p$;
    final /* synthetic */ RGBDInterfaceImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RGBDInterfaceImpl$controlRGBD$4(RGBDInterfaceImpl rGBDInterfaceImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = rGBDInterfaceImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RGBDInterfaceImpl$controlRGBD$4 rGBDInterfaceImpl$controlRGBD$4 = new RGBDInterfaceImpl$controlRGBD$4(this.this$0, completion);
        rGBDInterfaceImpl$controlRGBD$4.f5939p$ = (CoroutineScope) obj;
        return rGBDInterfaceImpl$controlRGBD$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RGBDInterfaceImpl$controlRGBD$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5939p$;
            job = this.this$0.controlJob;
            if (job != null) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (job.join(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.controlJob = (Job) null;
        Pdlog.m3273d(this.this$0.TAG, "notify onRGBDOpened " + this.this$0.rgbdState.get());
        this.this$0.getContainer().getStateListeners().notify(new Function2<IRgbdStatus, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.RGBDInterfaceImpl$controlRGBD$4.1
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
                l.onRGBDOpened(RGBDInterfaceImpl$controlRGBD$4.this.this$0.rgbdState.get());
            }
        });
        return Unit.INSTANCE;
    }
}
