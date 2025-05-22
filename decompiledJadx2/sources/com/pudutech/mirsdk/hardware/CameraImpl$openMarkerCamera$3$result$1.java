package com.pudutech.mirsdk.hardware;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MarkerCameraImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.CameraImpl$openMarkerCamera$3$result$1", m3970f = "MarkerCameraImpl.kt", m3971i = {0}, m3972l = {131}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class CameraImpl$openMarkerCamera$3$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Ref.BooleanRef $receivedPhoto;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5888p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraImpl$openMarkerCamera$3$result$1(Ref.BooleanRef booleanRef, Continuation continuation) {
        super(2, continuation);
        this.$receivedPhoto = booleanRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CameraImpl$openMarkerCamera$3$result$1 cameraImpl$openMarkerCamera$3$result$1 = new CameraImpl$openMarkerCamera$3$result$1(this.$receivedPhoto, completion);
        cameraImpl$openMarkerCamera$3$result$1.f5888p$ = (CoroutineScope) obj;
        return cameraImpl$openMarkerCamera$3$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((CameraImpl$openMarkerCamera$3$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5888p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (!this.$receivedPhoto.element) {
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(10L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Boxing.boxBoolean(this.$receivedPhoto.element);
    }
}
