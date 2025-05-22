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
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: MarkerCameraImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.CameraImpl$openMonocularCamera$2", m3970f = "MarkerCameraImpl.kt", m3971i = {0, 0, 0}, m3972l = {246}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "openCameraResult", "receivedPhoto"}, m3975s = {"L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
final class CameraImpl$openMonocularCamera$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5891p$;
    final /* synthetic */ CameraImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraImpl$openMonocularCamera$2(CameraImpl cameraImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cameraImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CameraImpl$openMonocularCamera$2 cameraImpl$openMonocularCamera$2 = new CameraImpl$openMonocularCamera$2(this.this$0, completion);
        cameraImpl$openMonocularCamera$2.f5891p$ = (CoroutineScope) obj;
        return cameraImpl$openMonocularCamera$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraImpl$openMonocularCamera$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: MarkerCameraImpl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraState;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.CameraImpl$openMonocularCamera$2$3 */
    /* loaded from: classes.dex */
    static final class C49653 extends Lambda implements Function2<IMarkerCameraState, String, Unit> {
        public static final C49653 INSTANCE = new C49653();

        C49653() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(IMarkerCameraState iMarkerCameraState, String str) {
            invoke2(iMarkerCameraState, str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(IMarkerCameraState it, String str) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
            it.onOpened(true);
        }
    }

    /* compiled from: MarkerCameraImpl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraState;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.CameraImpl$openMonocularCamera$2$4 */
    /* loaded from: classes.dex */
    static final class C49664 extends Lambda implements Function2<IMarkerCameraState, String, Unit> {
        public static final C49664 INSTANCE = new C49664();

        C49664() {
            super(2);
        }

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
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Function6<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Long, Unit> function6;
        String str;
        ThreadSafeListener threadSafeListener;
        ThreadSafeListener threadSafeListener2;
        String str2;
        AtomicBoolean atomicBoolean;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5891p$;
            CameraLib cameraLib = CameraLib.INSTANCE;
            function6 = this.this$0.monocularFrameDistributor;
            CameraLib.Result openMonocularCamera = cameraLib.openMonocularCamera(function6, this.this$0.getDevVid());
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "MonocularCamera.open result:" + openMonocularCamera);
            if (!openMonocularCamera.isSuccess()) {
                return Unit.INSTANCE;
            }
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = false;
            threadSafeListener = this.this$0.monocularListeners;
            threadSafeListener.add("open_monocular_camera", new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.hardware.CameraImpl$openMonocularCamera$2.1
                @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
                public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
                    Ref.BooleanRef.this.element = true;
                }
            });
            CameraImpl$openMonocularCamera$2$result$1 cameraImpl$openMonocularCamera$2$result$1 = new CameraImpl$openMonocularCamera$2$result$1(booleanRef, null);
            this.L$0 = coroutineScope;
            this.L$1 = openMonocularCamera;
            this.L$2 = booleanRef;
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(30000L, cameraImpl$openMonocularCamera$2$result$1, this);
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
        threadSafeListener2 = this.this$0.monocularListeners;
        threadSafeListener2.remove("open_monocular_camera");
        str2 = this.this$0.TAG;
        Pdlog.m3275i(str2, "camera check at least one frame:" + bool);
        if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
            atomicBoolean = this.this$0.monocularCameraOpened;
            atomicBoolean.set(true);
        }
        return Unit.INSTANCE;
    }

    /* compiled from: MarkerCameraImpl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"com/pudutech/mirsdk/hardware/CameraImpl$openMonocularCamera$2$2", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData$Stub;", "onFrame", "", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "", "p2", "p3", "p4", "p5", "", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.CameraImpl$openMonocularCamera$2$2 */
    /* loaded from: classes.dex */
    public static final class BinderC49642 extends IMarkerCameraData.Stub {
        final /* synthetic */ Ref.BooleanRef $receivedPhoto;

        BinderC49642(Ref.BooleanRef booleanRef) {
            this.$receivedPhoto = booleanRef;
        }

        @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
        public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
            this.$receivedPhoto.element = true;
        }
    }
}
