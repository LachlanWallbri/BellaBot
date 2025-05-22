package com.pudutech.lidar.echox;

import android.util.Log;
import com.pudutech.lidar.LidarUpgradeListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* compiled from: EchoxFpgaUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lidar.echox.EchoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1", m3970f = "EchoxFpgaUpgrade.kt", m3971i = {0, 0, 1, 1, 2, 2}, m3972l = {80, 92, 97}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "sendFpgaDataRetryTime", "$this$launch", "sendFpgaDataRetryTime", "$this$launch", "sendFpgaDataRetryTime"}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes5.dex */
final class EchoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5457p$;
    final /* synthetic */ EchoxFpgaUpgrade$startFpgaUpgrade$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EchoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1(EchoxFpgaUpgrade$startFpgaUpgrade$1 echoxFpgaUpgrade$startFpgaUpgrade$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = echoxFpgaUpgrade$startFpgaUpgrade$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EchoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1 echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1 = new EchoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1(this.this$0, completion);
        echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.f5457p$ = (CoroutineScope) obj;
        return echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EchoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00da  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00d8 -> B:8:0x006a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x011b -> B:8:0x006a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0128 -> B:7:0x0137). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0134 -> B:7:0x0137). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        AtomicInteger atomicInteger;
        CoroutineScope coroutineScope;
        int i;
        EchoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1 echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1;
        AtomicInteger atomicInteger2;
        String str2;
        AtomicInteger atomicInteger3;
        AtomicInteger atomicInteger4;
        String str3;
        LidarUpgradeListener lidarUpgradeListener;
        AtomicBoolean atomicBoolean;
        Job job;
        AtomicBoolean atomicBoolean2;
        Job job2;
        AtomicBoolean atomicBoolean3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5457p$;
            str = this.this$0.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("waitForFpgaResponseJob start waitForFpgaResponseTime = ");
            atomicInteger = this.this$0.this$0.waitForFpgaResponseTime;
            sb.append(atomicInteger);
            Log.d(str, sb.toString());
            coroutineScope = coroutineScope2;
            i = 0;
            echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1 = this;
            while (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1 = this;
            atomicInteger2 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.waitForFpgaResponseTime;
            atomicInteger2.incrementAndGet();
            str2 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("waitForFpgaResponseJob waitForFpgaResponseTime = ");
            atomicInteger3 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.waitForFpgaResponseTime;
            sb2.append(atomicInteger3);
            Log.d(str2, sb2.toString());
            atomicInteger4 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.waitForFpgaResponseTime;
            if (atomicInteger4.get() > 20) {
            }
            while (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 == 2) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1 = this;
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
            while (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 == 3) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1 = this;
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
            while (CoroutineScopeKt.isActive(coroutineScope)) {
                atomicBoolean2 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.isKeepRunning;
                if (!atomicBoolean2.get()) {
                    job2 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.fpgaUpgradeJob;
                    if (job2 != null) {
                        echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.L$0 = coroutineScope;
                        echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.I$0 = i;
                        echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.label = 3;
                        if (JobKt.cancelAndJoin(job2, echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                    while (CoroutineScopeKt.isActive(coroutineScope)) {
                    }
                } else {
                    atomicBoolean3 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.hasSendNewPacket;
                    if (atomicBoolean3.get()) {
                        echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.L$0 = coroutineScope;
                        echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.I$0 = i;
                        echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.label = 1;
                        if (DelayKt.delay(1000L, echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        atomicInteger2 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.waitForFpgaResponseTime;
                        atomicInteger2.incrementAndGet();
                        str2 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.TAG;
                        StringBuilder sb22 = new StringBuilder();
                        sb22.append("waitForFpgaResponseJob waitForFpgaResponseTime = ");
                        atomicInteger3 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.waitForFpgaResponseTime;
                        sb22.append(atomicInteger3);
                        Log.d(str2, sb22.toString());
                        atomicInteger4 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.waitForFpgaResponseTime;
                        if (atomicInteger4.get() > 20) {
                            str3 = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.TAG;
                            Log.d(str3, "receive response package timeout");
                            echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.socketClose();
                            lidarUpgradeListener = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.lidarUpgralistener;
                            lidarUpgradeListener.onLidarUpgradeResult(false);
                            atomicBoolean = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.isKeepRunning;
                            atomicBoolean.set(false);
                            job = echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.this$0.this$0.fpgaUpgradeJob;
                            if (job != null) {
                                echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.L$0 = coroutineScope;
                                echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.I$0 = i;
                                echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1.label = 2;
                                if (JobKt.cancelAndJoin(job, echoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                        }
                        while (CoroutineScopeKt.isActive(coroutineScope)) {
                        }
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
