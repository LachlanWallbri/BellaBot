package com.pudutech.mirsdk.hardware;

import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.cameralib.CameraLib;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: MarkerCameraImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.CameraImpl$openMarkerCamera$3", m3970f = "MarkerCameraImpl.kt", m3971i = {0, 0, 0}, m3972l = {129}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "openCameraResult", "receivedPhoto"}, m3975s = {"L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
final class CameraImpl$openMarkerCamera$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5887p$;
    final /* synthetic */ CameraImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraImpl$openMarkerCamera$3(CameraImpl cameraImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CameraImpl$openMarkerCamera$3 cameraImpl$openMarkerCamera$3 = new CameraImpl$openMarkerCamera$3(this.this$0, completion);
        cameraImpl$openMarkerCamera$3.f5887p$ = (CoroutineScope) obj;
        return cameraImpl$openMarkerCamera$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraImpl$openMarkerCamera$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Function6<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Long, Unit> function6;
        String str2;
        ThreadSafeListener threadSafeListener;
        ThreadSafeListener threadSafeListener2;
        String str3;
        AtomicBoolean atomicBoolean;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5887p$;
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "start open marker camera");
            CameraLib cameraLib = CameraLib.INSTANCE;
            function6 = this.this$0.frameDistributor;
            CameraLib.Result openMarkerCamera = cameraLib.openMarkerCamera(function6);
            str2 = this.this$0.TAG;
            Pdlog.m3275i(str2, "MarkerCamera.open result:" + openMarkerCamera);
            if (!openMarkerCamera.isSuccess()) {
                this.this$0.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$openMarkerCamera$3.1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str4) {
                        invoke2(iMarkerCameraState, str4);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IMarkerCameraState it, String str4) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                        it.onOpened(false);
                    }
                });
                return Unit.INSTANCE;
            }
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = false;
            threadSafeListener = this.this$0.listeners;
            threadSafeListener.add("open_camera", new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$openMarkerCamera$3.2
                @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
                public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
                    Ref.BooleanRef.this.element = true;
                }
            });
            CameraImpl$openMarkerCamera$3$result$1 cameraImpl$openMarkerCamera$3$result$1 = new CameraImpl$openMarkerCamera$3$result$1(booleanRef, null);
            this.L$0 = coroutineScope;
            this.L$1 = openMarkerCamera;
            this.L$2 = booleanRef;
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(30000L, cameraImpl$openMarkerCamera$3$result$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Boolean bool = (Boolean) obj;
        threadSafeListener2 = this.this$0.listeners;
        threadSafeListener2.remove("open_camera");
        str3 = this.this$0.TAG;
        Pdlog.m3275i(str3, "camera check at least one frame:" + bool);
        if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
            this.this$0.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$openMarkerCamera$3.3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str4) {
                    invoke2(iMarkerCameraState, str4);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str4) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                    it.onOpened(true);
                }
            });
            atomicBoolean = this.this$0.opened;
            atomicBoolean.set(true);
        } else {
            this.this$0.statelisteners.notify(new Function2<IMarkerCameraState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$openMarkerCamera$3.4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str4) {
                    invoke2(iMarkerCameraState, str4);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IMarkerCameraState it, String str4) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str4, "<anonymous parameter 1>");
                    it.onOpened(false);
                }
            });
        }
        return Unit.INSTANCE;
    }
}
