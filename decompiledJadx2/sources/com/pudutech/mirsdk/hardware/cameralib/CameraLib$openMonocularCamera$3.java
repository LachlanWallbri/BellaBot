package com.pudutech.mirsdk.hardware.cameralib;

import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.util.Log;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.cameralib.CameraLib;
import java.io.IOException;
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
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CameraLib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.cameralib.CameraLib$openMonocularCamera$3", m3970f = "CameraLib.kt", m3971i = {0, 0, 0, 0, 0, 0, 0, 0}, m3972l = {409}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "parcelFileDescriptor", "rows", "cols", "memorySize", "elementSize", SpeechUtility.TAG_RESOURCE_RESULT, "cameraParamReopen"}, m3975s = {"L$0", "L$1", "I$0", "I$1", "I$2", "I$3", "I$4", "L$2"})
/* loaded from: classes5.dex */
final class CameraLib$openMonocularCamera$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function6 $dataCallback;
    final /* synthetic */ Ref.IntRef $ret;
    final /* synthetic */ int $rotateAngle;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6016p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CameraLib$openMonocularCamera$3(Ref.IntRef intRef, Function6 function6, int i, Continuation continuation) {
        super(2, continuation);
        this.$ret = intRef;
        this.$dataCallback = function6;
        this.$rotateAngle = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CameraLib$openMonocularCamera$3 cameraLib$openMonocularCamera$3 = new CameraLib$openMonocularCamera$3(this.$ret, this.$dataCallback, this.$rotateAngle, completion);
        cameraLib$openMonocularCamera$3.f6016p$ = (CoroutineScope) obj;
        return cameraLib$openMonocularCamera$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraLib$openMonocularCamera$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c2, code lost:
    
        r13 = com.pudutech.mirsdk.hardware.cameralib.CameraLib.INSTANCE;
        r13 = com.pudutech.mirsdk.hardware.cameralib.CameraLib.TAG;
        com.pudutech.base.Pdlog.m3277w(r13, "monocular read frame null, try reopen, result is " + r12);
        r3 = com.pudutech.mirsdk.hardware.cameralib.CameraLib.getCameraParam$default(com.pudutech.mirsdk.hardware.cameralib.CameraLib.INSTANCE, "/sdcard/pudu/config/Monocular_camera.config", true, false, 4, null);
        r0.L$0 = r11;
        r0.L$1 = r6;
        r0.I$0 = r9;
        r0.I$1 = r8;
        r0.I$2 = r7;
        r0.I$3 = r2;
        r0.I$4 = r12;
        r0.L$2 = r3;
        r0.label = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0108, code lost:
    
        if (kotlinx.coroutines.DelayKt.delay(1000, r0) != r10) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x010a, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x010b, code lost:
    
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006a, code lost:
    
        r0 = com.pudutech.mirsdk.hardware.cameralib.CameraLib.INSTANCE;
        r0 = com.pudutech.mirsdk.hardware.cameralib.CameraLib.TAG;
        com.pudutech.base.Pdlog.m3273d(r0, "Monocular Camera is not active");
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005e A[Catch: all -> 0x002d, IOException -> 0x0030, TryCatch #0 {IOException -> 0x0030, blocks: (B:6:0x0024, B:8:0x0110, B:10:0x011e, B:13:0x0058, B:15:0x005e, B:33:0x006a, B:30:0x007d, B:24:0x00c2), top: B:5:0x0024, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0143 A[EDGE_INSN: B:38:0x0143->B:34:0x0143 BREAK  A[LOOP:0: B:13:0x0058->B:22:0x0058], SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x010b -> B:8:0x0110). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ParcelFileDescriptor parcelFileDescriptor;
        int i;
        Object obj2;
        CameraLib$openMonocularCamera$3 cameraLib$openMonocularCamera$3;
        CoroutineScope coroutineScope;
        int i2;
        int i3;
        int i4;
        String str;
        String str2;
        String str3;
        String str4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = this.label;
        if (i5 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f6016p$;
            parcelFileDescriptor = ParcelFileDescriptor.fromFd(this.$ret.element);
            i = 960;
            obj2 = coroutine_suspended;
            cameraLib$openMonocularCamera$3 = this;
            coroutineScope = coroutineScope2;
            i2 = 1;
            i3 = 540;
            i4 = 518416;
            while (true) {
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
            }
        } else if (i5 == 1) {
            CameraLib.CameraParam cameraParam = (CameraLib.CameraParam) this.L$2;
            int i6 = this.I$4;
            int i7 = this.I$3;
            i4 = this.I$2;
            i = this.I$1;
            i3 = this.I$0;
            ParcelFileDescriptor parcelFileDescriptor2 = (ParcelFileDescriptor) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                try {
                    ResultKt.throwOnFailure(obj);
                    int i8 = i7;
                    parcelFileDescriptor = parcelFileDescriptor2;
                    obj2 = coroutine_suspended;
                    cameraLib$openMonocularCamera$3 = this;
                    int openMonocular = CameraNative.INSTANCE.openMonocular(cameraParam.toString(), cameraLib$openMonocularCamera$3.$rotateAngle);
                    if (openMonocular > 0) {
                        parcelFileDescriptor = ParcelFileDescriptor.fromFd(openMonocular);
                        CameraLib cameraLib = CameraLib.INSTANCE;
                        str4 = CameraLib.TAG;
                        Pdlog.m3277w(str4, "monocular reopen fileDes is : " + parcelFileDescriptor);
                    }
                    i2 = i8;
                    while (true) {
                        if (!CoroutineScopeKt.isActive(coroutineScope)) {
                            int monocularFrame = CameraNative.INSTANCE.getMonocularFrame();
                            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                                break;
                            }
                            if (monocularFrame == 0) {
                                CameraLib cameraLib2 = CameraLib.INSTANCE;
                                str2 = CameraLib.TAG;
                                Pdlog.m3273d(str2, "Monocular Camera image dataCallback begin");
                                Function6 function6 = cameraLib$openMonocularCamera$3.$dataCallback;
                                Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                                function6.invoke(parcelFileDescriptor, Boxing.boxInt(i3), Boxing.boxInt(i), Boxing.boxInt(i4), Boxing.boxInt(i2), Boxing.boxLong(SystemClock.elapsedRealtime()));
                                CameraLib cameraLib3 = CameraLib.INSTANCE;
                                str3 = CameraLib.TAG;
                                Pdlog.m3273d(str3, "Monocular Camera image dataCallback end");
                            } else if (monocularFrame != 1) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } catch (IOException e) {
                    CameraLib cameraLib4 = CameraLib.INSTANCE;
                    str = CameraLib.TAG;
                    Pdlog.m3274e(str, "monocular loop exception " + Log.getStackTraceString(e));
                    return Unit.INSTANCE;
                }
            } finally {
                CameraLib cameraLib5 = CameraLib.INSTANCE;
                CameraLib.MonocularLoop = null;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
