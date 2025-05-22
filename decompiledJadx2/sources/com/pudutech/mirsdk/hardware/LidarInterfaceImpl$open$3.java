package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarVersion;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: LidarInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.LidarInterfaceImpl$open$3", m3970f = "LidarInterfaceImpl.kt", m3971i = {0, 0}, m3972l = {331}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "timeout"}, m3975s = {"L$0", "J$0"})
/* loaded from: classes.dex */
public final class LidarInterfaceImpl$open$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5932p$;
    final /* synthetic */ LidarInterfaceImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LidarInterfaceImpl$open$3(LidarInterfaceImpl lidarInterfaceImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lidarInterfaceImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LidarInterfaceImpl$open$3 lidarInterfaceImpl$open$3 = new LidarInterfaceImpl$open$3(this.this$0, completion);
        lidarInterfaceImpl$open$3.f5932p$ = (CoroutineScope) obj;
        return lidarInterfaceImpl$open$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LidarInterfaceImpl$open$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5932p$;
            this.this$0.dataTimestamps = CollectionsKt.arrayListOf(Boxing.boxLong(0L), Boxing.boxLong(0L), Boxing.boxLong(0L), Boxing.boxLong(0L), Boxing.boxLong(0L));
            this.this$0.dataArrived = false;
            this.this$0.wasOpened = false;
            LidarInterfaceImpl.access$getLidar$p(this.this$0).startScan();
            if (this.this$0.getNeedStartSecondLidar()) {
                LidarInterfaceImpl.access$getSecondLidar$p(this.this$0).startScan();
            }
            long j = LidarInterfaceImpl.access$getLidar$p(this.this$0).getVersion() == LidarVersion.EAI_TG30_SLAMWARE ? HardwareConfig.SlamLidarOpenTimeOut : 30000L;
            LidarInterfaceImpl$open$3$reallyStarted$1 lidarInterfaceImpl$open$3$reallyStarted$1 = new LidarInterfaceImpl$open$3$reallyStarted$1(this, null);
            this.L$0 = coroutineScope;
            this.J$0 = j;
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(j, lidarInterfaceImpl$open$3$reallyStarted$1, this);
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
        if (Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
            str3 = this.this$0.TAG;
            Pdlog.m3275i(str3, "lidar open success");
            this.this$0.wasOpened = true;
            str = "";
        } else {
            str = "lidar open timeout";
            str2 = this.this$0.TAG;
            Pdlog.m3275i(str2, "lidar open timeout");
        }
        this.this$0.onLidarStart(Intrinsics.areEqual(bool, Boxing.boxBoolean(true)), str);
        return Unit.INSTANCE;
    }
}
