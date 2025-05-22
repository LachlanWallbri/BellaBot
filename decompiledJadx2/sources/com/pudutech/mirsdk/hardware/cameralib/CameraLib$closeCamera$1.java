package com.pudutech.mirsdk.hardware.cameralib;

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
import kotlinx.coroutines.JobKt;

/* compiled from: CameraLib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.cameralib.CameraLib$closeCamera$1", m3970f = "CameraLib.kt", m3971i = {0}, m3972l = {162}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
final class CameraLib$closeCamera$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CameraInfo $cameraDevice;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6007p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CameraLib$closeCamera$1(CameraInfo cameraInfo, Continuation continuation) {
        super(2, continuation);
        this.$cameraDevice = cameraInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CameraLib$closeCamera$1 cameraLib$closeCamera$1 = new CameraLib$closeCamera$1(this.$cameraDevice, completion);
        cameraLib$closeCamera$1.f6007p$ = (CoroutineScope) obj;
        return cameraLib$closeCamera$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraLib$closeCamera$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job cameraJobLoop;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6007p$;
            CameraInfo cameraInfo = this.$cameraDevice;
            if ((cameraInfo != null ? cameraInfo.getCameraJobLoop() : null) != null) {
                CameraInfo cameraInfo2 = this.$cameraDevice;
                if (cameraInfo2 != null && (cameraJobLoop = cameraInfo2.getCameraJobLoop()) != null) {
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (JobKt.cancelAndJoin(cameraJobLoop, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            CameraNative.INSTANCE.closeMarker();
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CameraInfo cameraInfo3 = this.$cameraDevice;
        if (cameraInfo3 != null) {
            cameraInfo3.setCameraJobLoop((Job) null);
        }
        CameraNative.INSTANCE.closeMarker();
        return Unit.INSTANCE;
    }
}
