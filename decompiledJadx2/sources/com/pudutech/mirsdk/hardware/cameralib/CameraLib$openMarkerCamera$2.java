package com.pudutech.mirsdk.hardware.cameralib;

import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.SensorImageContainer;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: CameraLib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.cameralib.CameraLib$openMarkerCamera$2", m3970f = "CameraLib.kt", m3971i = {0, 0}, m3972l = {96}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "sensorContainer"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes2.dex */
public final class CameraLib$openMarkerCamera$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function6 $dataCallback;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6013p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CameraLib$openMarkerCamera$2(Function6 function6, Continuation continuation) {
        super(2, continuation);
        this.$dataCallback = function6;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CameraLib$openMarkerCamera$2 cameraLib$openMarkerCamera$2 = new CameraLib$openMarkerCamera$2(this.$dataCallback, completion);
        cameraLib$openMarkerCamera$2.f6013p$ = (CoroutineScope) obj;
        return cameraLib$openMarkerCamera$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraLib$openMarkerCamera$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6013p$;
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Exception e) {
                    CameraLib cameraLib = CameraLib.INSTANCE;
                    str = CameraLib.TAG;
                    Pdlog.m3274e(str, "loop exception " + Log.getStackTraceString(e));
                }
            } finally {
                CameraLib cameraLib2 = CameraLib.INSTANCE;
                CameraLib.jobLoop = (Job) null;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            SensorImageContainer markerFrame = CameraNative.getMarkerFrame();
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                break;
            }
            if (markerFrame != null) {
                ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.dup(markerFrame.getFileDescriptor());
                Function6 function6 = this.$dataCallback;
                Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                function6.invoke(parcelFileDescriptor, Boxing.boxInt(markerFrame.getRows()), Boxing.boxInt(markerFrame.getCols()), Boxing.boxInt(markerFrame.getMemorySize()), Boxing.boxInt(markerFrame.getElementSize()), Boxing.boxLong(SystemClock.elapsedRealtime()));
                parcelFileDescriptor.close();
            } else {
                CameraLib cameraLib3 = CameraLib.INSTANCE;
                str2 = CameraLib.TAG;
                Pdlog.m3277w(str2, "read frame null");
                this.L$0 = coroutineScope;
                this.L$1 = markerFrame;
                this.label = 1;
                if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
