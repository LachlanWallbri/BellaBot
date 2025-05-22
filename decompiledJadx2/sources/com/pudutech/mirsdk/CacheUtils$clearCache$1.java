package com.pudutech.mirsdk;

import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.utils.CommandUtils;
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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* compiled from: CacheUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.CacheUtils$clearCache$1", m3970f = "CacheUtils.kt", m3971i = {0}, m3972l = {23}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
final class CacheUtils$clearCache$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5515p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CacheUtils$clearCache$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CacheUtils$clearCache$1 cacheUtils$clearCache$1 = new CacheUtils$clearCache$1(completion);
        cacheUtils$clearCache$1.f5515p$ = (CoroutineScope) obj;
        return cacheUtils$clearCache$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CacheUtils$clearCache$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5515p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            CommandUtils.INSTANCE.executeCommand(InvokeServiceData.CALL_TYPE_SYNC);
            CommandUtils.INSTANCE.executeCommand("echo 1 > /proc/sys/vm/drop_caches");
            CommandUtils.INSTANCE.executeCommand("echo 2 > /proc/sys/vm/drop_caches");
            CommandUtils.INSTANCE.executeCommand("echo 3 > /proc/sys/vm/drop_caches");
            Pdlog.m3273d("clearBuffers", "run clear cached job");
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(1200000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
