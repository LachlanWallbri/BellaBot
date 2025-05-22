package com.pudutech.rgbdlib.activity;

import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.rgbdlib.RGBDDataCatcher;
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

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: CheckToolActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.CheckToolActivity$onDestroy$1", m3970f = "CheckToolActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
final class CheckToolActivity$onDestroy$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7134p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CheckToolActivity$onDestroy$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CheckToolActivity$onDestroy$1 checkToolActivity$onDestroy$1 = new CheckToolActivity$onDestroy$1(completion);
        checkToolActivity$onDestroy$1.f7134p$ = (CoroutineScope) obj;
        return checkToolActivity$onDestroy$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CheckToolActivity$onDestroy$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ThreadSafeListener<RGBDDataCatcher> checkRGBDListener;
        ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        RGBDSensor rgbdSensor = CheckToolActivity.INSTANCE.getRgbdSensor();
        if (rgbdSensor != null && (centernRGBDListeners = rgbdSensor.getCenternRGBDListeners()) != null) {
            centernRGBDListeners.remove("rgbdTool");
        }
        RGBDSensor rgbdSensor2 = CheckToolActivity.INSTANCE.getRgbdSensor();
        if (rgbdSensor2 != null && (checkRGBDListener = rgbdSensor2.getCheckRGBDListener()) != null) {
            checkRGBDListener.remove("rgbdToolResult");
        }
        return Unit.INSTANCE;
    }
}
