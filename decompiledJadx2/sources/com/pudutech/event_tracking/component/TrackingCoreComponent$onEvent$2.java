package com.pudutech.event_tracking.component;

import com.pudutech.event_tracking.bean.TrackEvent;
import com.pudutech.event_tracking.p055db.TrackEventDao;
import com.pudutech.pd_network.log.NetWorkLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TrackingCoreComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.event_tracking.component.TrackingCoreComponent$onEvent$2", m3970f = "TrackingCoreComponent.kt", m3971i = {0, 1, 1}, m3972l = {176, 180}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "saveList"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class TrackingCoreComponent$onEvent$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5128p$;
    final /* synthetic */ TrackingCoreComponent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrackingCoreComponent$onEvent$2(TrackingCoreComponent trackingCoreComponent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = trackingCoreComponent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TrackingCoreComponent$onEvent$2 trackingCoreComponent$onEvent$2 = new TrackingCoreComponent$onEvent$2(this.this$0, completion);
        trackingCoreComponent$onEvent$2.f5128p$ = (CoroutineScope) obj;
        return trackingCoreComponent$onEvent$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TrackingCoreComponent$onEvent$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:16|17|18|19|(2:21|(1:23))|9|10|(2:32|33)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0091, code lost:
    
        r5 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0082 A[Catch: Exception -> 0x0091, TRY_LEAVE, TryCatch #1 {Exception -> 0x0091, blocks: (B:19:0x007a, B:21:0x0082), top: B:18:0x007a }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c3 A[LOOP:0: B:27:0x00bd->B:29:0x00c3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d3  */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0080 -> B:9:0x008f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x008c -> B:9:0x008f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00cf -> B:9:0x008f). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        TrackingCoreComponent$onEvent$2 trackingCoreComponent$onEvent$2;
        ArrayList arrayList;
        CoroutineScope coroutineScope2;
        List tempEventList;
        List list;
        TrackEventDao trackEventDao;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5128p$;
            obj2 = coroutine_suspended;
            trackingCoreComponent$onEvent$2 = this;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i == 1) {
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope2 = coroutineScope3;
            obj2 = coroutine_suspended;
            trackingCoreComponent$onEvent$2 = this;
            arrayList = new ArrayList();
            tempEventList = trackingCoreComponent$onEvent$2.this$0.tempEventList;
            Intrinsics.checkExpressionValueIsNotNull(tempEventList, "tempEventList");
            arrayList.addAll(tempEventList);
            list = trackingCoreComponent$onEvent$2.this$0.tempEventList;
            list.clear();
            trackEventDao = trackingCoreComponent$onEvent$2.this$0.trackEventDao;
            if (trackEventDao != null) {
            }
            coroutineScope = coroutineScope2;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i == 2) {
            ?? r1 = (List) this.L$1;
            coroutineScope2 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                obj2 = coroutine_suspended;
                trackingCoreComponent$onEvent$2 = this;
            } catch (Exception e) {
                Exception e2 = e;
                arrayList = r1;
                obj2 = coroutine_suspended;
                trackingCoreComponent$onEvent$2 = this;
                NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent$onEvent$2.this$0.TAG, "onEvent error " + e2);
                trackingCoreComponent$onEvent$2.this$0.trackEventDao = (TrackEventDao) null;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    trackingCoreComponent$onEvent$2.this$0.onEvent((TrackEvent) it.next());
                }
                CoroutineScopeKt.cancel$default(coroutineScope2, null, 1, null);
                coroutineScope = coroutineScope2;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
            }
            coroutineScope = coroutineScope2;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                long nextLong = Random.INSTANCE.nextLong(10L, 5000L);
                trackingCoreComponent$onEvent$2.L$0 = coroutineScope;
                trackingCoreComponent$onEvent$2.label = 1;
                if (DelayKt.delay(nextLong, trackingCoreComponent$onEvent$2) == obj2) {
                    return obj2;
                }
                coroutineScope2 = coroutineScope;
                arrayList = new ArrayList();
                tempEventList = trackingCoreComponent$onEvent$2.this$0.tempEventList;
                Intrinsics.checkExpressionValueIsNotNull(tempEventList, "tempEventList");
                arrayList.addAll(tempEventList);
                list = trackingCoreComponent$onEvent$2.this$0.tempEventList;
                list.clear();
                trackEventDao = trackingCoreComponent$onEvent$2.this$0.trackEventDao;
                if (trackEventDao != null) {
                    trackingCoreComponent$onEvent$2.L$0 = coroutineScope2;
                    trackingCoreComponent$onEvent$2.L$1 = arrayList;
                    trackingCoreComponent$onEvent$2.label = 2;
                    if (trackEventDao.insert(arrayList, trackingCoreComponent$onEvent$2) == obj2) {
                        return obj2;
                    }
                }
                coroutineScope = coroutineScope2;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
