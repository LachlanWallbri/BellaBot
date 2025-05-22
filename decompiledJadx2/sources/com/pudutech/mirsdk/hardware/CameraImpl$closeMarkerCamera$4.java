package com.pudutech.mirsdk.hardware;

import com.pudutech.mirsdk.hardware.cameralib.CameraLib;
import java.util.concurrent.atomic.AtomicBoolean;
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

/* compiled from: MarkerCameraImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.CameraImpl$closeMarkerCamera$4", m3970f = "MarkerCameraImpl.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class CameraImpl$closeMarkerCamera$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5884p$;
    final /* synthetic */ CameraImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CameraImpl$closeMarkerCamera$4(CameraImpl cameraImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CameraImpl$closeMarkerCamera$4 cameraImpl$closeMarkerCamera$4 = new CameraImpl$closeMarkerCamera$4(this.this$0, completion);
        cameraImpl$closeMarkerCamera$4.f5884p$ = (CoroutineScope) obj;
        return cameraImpl$closeMarkerCamera$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraImpl$closeMarkerCamera$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AtomicBoolean atomicBoolean;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5884p$;
        CameraLib.INSTANCE.closeMarkerCamera();
        this.this$0.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$closeMarkerCamera$4.1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
                invoke2(iMarkerCameraState, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IMarkerCameraState it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.onOpened(false);
            }
        });
        atomicBoolean = this.this$0.opened;
        atomicBoolean.set(false);
        return Unit.INSTANCE;
    }
}
