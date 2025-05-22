package com.pudutech.mirsdk.hardware.cameralib;

import com.iflytek.cloud.SpeechUtility;
import com.pudutech.mirsdk.hardware.cameralib.CameraLib;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: CameraLib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.cameralib.CameraLib$openCamera$2", m3970f = "CameraLib.kt", m3971i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, m3972l = {124, 138}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "parcelFileDescriptor", "memorySize", "elementSize", SpeechUtility.TAG_RESOURCE_RESULT, "cameraParamReopen", "$this$launch", "parcelFileDescriptor", "memorySize", "elementSize"}, m3975s = {"L$0", "L$1", "I$0", "I$1", "I$2", "L$2", "L$0", "L$1", "I$0", "I$1"})
/* loaded from: classes5.dex */
final class CameraLib$openCamera$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $bpp;
    final /* synthetic */ CameraInfo $cameraDevice;
    final /* synthetic */ CameraLib.CameraParam $cameraParam;
    final /* synthetic */ int $cols;
    final /* synthetic */ Function6 $dataCallback;
    final /* synthetic */ int $ret;
    final /* synthetic */ int $rows;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6011p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CameraLib$openCamera$2(int i, int i2, int i3, int i4, CameraInfo cameraInfo, Function6 function6, CameraLib.CameraParam cameraParam, Continuation continuation) {
        super(2, continuation);
        this.$ret = i;
        this.$rows = i2;
        this.$cols = i3;
        this.$bpp = i4;
        this.$cameraDevice = cameraInfo;
        this.$dataCallback = function6;
        this.$cameraParam = cameraParam;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CameraLib$openCamera$2 cameraLib$openCamera$2 = new CameraLib$openCamera$2(this.$ret, this.$rows, this.$cols, this.$bpp, this.$cameraDevice, this.$dataCallback, this.$cameraParam, completion);
        cameraLib$openCamera$2.f6011p$ = (CoroutineScope) obj;
        return cameraLib$openCamera$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CameraLib$openCamera$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00b9 A[Catch: IOException -> 0x01eb, all -> 0x0245, TryCatch #2 {all -> 0x0245, blocks: (B:12:0x00b3, B:14:0x00b9, B:16:0x00c3, B:41:0x00d5, B:19:0x00fd, B:25:0x0163, B:27:0x0178, B:29:0x0183, B:30:0x0186, B:35:0x01ac, B:37:0x01c8, B:49:0x01ed, B:56:0x0219), top: B:2:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x01c8 A[Catch: IOException -> 0x01eb, all -> 0x0245, TryCatch #2 {all -> 0x0245, blocks: (B:12:0x00b3, B:14:0x00b9, B:16:0x00c3, B:41:0x00d5, B:19:0x00fd, B:25:0x0163, B:27:0x0178, B:29:0x0183, B:30:0x0186, B:35:0x01ac, B:37:0x01c8, B:49:0x01ed, B:56:0x0219), top: B:2:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0208 A[EDGE_INSN: B:53:0x0208->B:42:0x0208 BREAK  A[LOOP:0: B:11:0x00b3->B:21:0x0160], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x01c6 -> B:9:0x0204). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x01c8 -> B:9:0x0204). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x01ff -> B:9:0x0204). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r27) {
        /*
            Method dump skipped, instructions count: 594
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pudutech.mirsdk.hardware.cameralib.CameraLib$openCamera$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
