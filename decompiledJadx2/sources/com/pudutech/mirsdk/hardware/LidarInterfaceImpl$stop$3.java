package com.pudutech.mirsdk.hardware;

import com.pudutech.mirsdk.hardware.serialize.LidarStopReason;
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
  classes4.dex
 */
/* compiled from: LidarInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.LidarInterfaceImpl$stop$3", m3970f = "LidarInterfaceImpl.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
final class LidarInterfaceImpl$stop$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5934p$;
    final /* synthetic */ LidarInterfaceImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LidarInterfaceImpl$stop$3(LidarInterfaceImpl lidarInterfaceImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lidarInterfaceImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LidarInterfaceImpl$stop$3 lidarInterfaceImpl$stop$3 = new LidarInterfaceImpl$stop$3(this.this$0, completion);
        lidarInterfaceImpl$stop$3.f5934p$ = (CoroutineScope) obj;
        return lidarInterfaceImpl$stop$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LidarInterfaceImpl$stop$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LidarInterfaceImpl.access$getLidar$p(this.this$0).stopScan();
        if (this.this$0.getNeedStartSecondLidar()) {
            LidarInterfaceImpl.access$getSecondLidar$p(this.this$0).stopScan();
        }
        this.this$0.wasOpened = false;
        this.this$0.onLidarStop(LidarStopReason.Manual);
        return Unit.INSTANCE;
    }
}
