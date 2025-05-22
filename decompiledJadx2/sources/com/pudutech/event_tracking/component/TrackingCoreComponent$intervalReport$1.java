package com.pudutech.event_tracking.component;

import android.database.sqlite.SQLiteDatabaseCorruptException;
import com.pudutech.event_tracking.bean.TrackEvent;
import com.pudutech.event_tracking.p055db.TrackEventDao;
import com.pudutech.pd_network.log.NetWorkLog;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TrackingCoreComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.event_tracking.component.TrackingCoreComponent$intervalReport$1", m3970f = "TrackingCoreComponent.kt", m3971i = {0, 1, 2, 2, 3}, m3972l = {83, 89, 90, 100}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$launch", "newestList", "$this$launch"}, m3975s = {"L$0", "L$0", "L$0", "L$1", "L$0"})
/* loaded from: classes5.dex */
public final class TrackingCoreComponent$intervalReport$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5126p$;
    final /* synthetic */ TrackingCoreComponent this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrackingCoreComponent$intervalReport$1(TrackingCoreComponent trackingCoreComponent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = trackingCoreComponent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TrackingCoreComponent$intervalReport$1 trackingCoreComponent$intervalReport$1 = new TrackingCoreComponent$intervalReport$1(this.this$0, completion);
        trackingCoreComponent$intervalReport$1.f5126p$ = (CoroutineScope) obj;
        return trackingCoreComponent$intervalReport$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TrackingCoreComponent$intervalReport$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x0094 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0151 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fc A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x010d A[Catch: all -> 0x0132, TryCatch #1 {all -> 0x0132, blocks: (B:27:0x00bc, B:29:0x00cd, B:34:0x00db, B:37:0x00ee, B:46:0x0109, B:48:0x010d, B:49:0x0115, B:53:0x00e7), top: B:45:0x0109 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0096  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0061 -> B:5:0x008a). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        TrackingCoreComponent$intervalReport$1 trackingCoreComponent$intervalReport$1;
        Object obj3;
        Object obj4;
        List<TrackEvent> emptyList;
        Throwable th;
        AtomicBoolean atomicBoolean;
        Exception e;
        CoroutineScope coroutineScope2;
        AtomicBoolean atomicBoolean2;
        CoroutineScope coroutineScope3;
        TrackingCoreComponent trackingCoreComponent;
        AtomicBoolean atomicBoolean3;
        CoroutineScope coroutineScope4;
        long repeatInterval;
        AtomicBoolean atomicBoolean4;
        AtomicBoolean atomicBoolean5;
        AtomicBoolean atomicBoolean6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5126p$;
        } else if (r1 == 1) {
            CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj3 = coroutine_suspended;
            trackingCoreComponent$intervalReport$1 = this;
            r1 = coroutineScope5;
            while (atomicBoolean4.get()) {
            }
            atomicBoolean5 = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
            coroutineScope4 = r1;
            if (!atomicBoolean5.get()) {
            }
            repeatInterval = trackingCoreComponent$intervalReport$1.this$0.workRuler.getRepeatInterval();
            trackingCoreComponent$intervalReport$1.L$0 = coroutineScope4;
            trackingCoreComponent$intervalReport$1.label = 4;
            if (DelayKt.delay(repeatInterval, trackingCoreComponent$intervalReport$1) != obj3) {
            }
        } else {
            try {
            } catch (Exception e2) {
                e = e2;
                obj3 = coroutine_suspended;
                trackingCoreComponent$intervalReport$1 = this;
                try {
                    if (e instanceof SQLiteDatabaseCorruptException) {
                    }
                    NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent$intervalReport$1.this$0.TAG, "intervalReport error " + e);
                    coroutineScope2 = r1;
                    atomicBoolean2 = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                    atomicBoolean2.set(false);
                    coroutineScope4 = coroutineScope2;
                    repeatInterval = trackingCoreComponent$intervalReport$1.this$0.workRuler.getRepeatInterval();
                    trackingCoreComponent$intervalReport$1.L$0 = coroutineScope4;
                    trackingCoreComponent$intervalReport$1.label = 4;
                    if (DelayKt.delay(repeatInterval, trackingCoreComponent$intervalReport$1) != obj3) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    atomicBoolean = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                    atomicBoolean.set(false);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                trackingCoreComponent$intervalReport$1 = this;
                atomicBoolean = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                atomicBoolean.set(false);
                throw th;
            }
            if (r1 == 2) {
                CoroutineScope coroutineScope6 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj4 = coroutine_suspended;
                trackingCoreComponent$intervalReport$1 = this;
                r1 = coroutineScope6;
                emptyList = (List) obj;
                coroutineScope3 = r1;
                if (emptyList == null) {
                }
                trackingCoreComponent = trackingCoreComponent$intervalReport$1.this$0;
                trackingCoreComponent$intervalReport$1.L$0 = coroutineScope3;
                trackingCoreComponent$intervalReport$1.L$1 = emptyList;
                trackingCoreComponent$intervalReport$1.label = 3;
                if (trackingCoreComponent.report(emptyList, trackingCoreComponent$intervalReport$1) == obj4) {
                }
            } else if (r1 == 3) {
                CoroutineScope coroutineScope7 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj3 = coroutine_suspended;
                trackingCoreComponent$intervalReport$1 = this;
                coroutineScope2 = coroutineScope7;
                atomicBoolean2 = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                atomicBoolean2.set(false);
                coroutineScope4 = coroutineScope2;
                repeatInterval = trackingCoreComponent$intervalReport$1.this$0.workRuler.getRepeatInterval();
                trackingCoreComponent$intervalReport$1.L$0 = coroutineScope4;
                trackingCoreComponent$intervalReport$1.label = 4;
                if (DelayKt.delay(repeatInterval, trackingCoreComponent$intervalReport$1) != obj3) {
                    return obj3;
                }
                CoroutineScope coroutineScope8 = coroutineScope4;
                obj2 = obj3;
                coroutineScope = coroutineScope8;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                    String str = trackingCoreComponent$intervalReport$1.this$0.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("intervalReport.start report ");
                    atomicBoolean3 = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                    sb.append(atomicBoolean3.get());
                    netWorkLog.mo3280i(str, sb.toString());
                    Object obj5 = obj2;
                    r1 = coroutineScope;
                    obj3 = obj5;
                    while (atomicBoolean4.get()) {
                        NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent$intervalReport$1.this$0.TAG, "intervalReport.remove");
                        trackingCoreComponent$intervalReport$1.L$0 = r1;
                        trackingCoreComponent$intervalReport$1.label = 1;
                        if (DelayKt.delay(500L, trackingCoreComponent$intervalReport$1) == obj3) {
                            return obj3;
                        }
                    }
                    atomicBoolean5 = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                    coroutineScope4 = r1;
                    if (!atomicBoolean5.get()) {
                        try {
                        } catch (Exception e3) {
                            e = e3;
                            if (e instanceof SQLiteDatabaseCorruptException) {
                                trackingCoreComponent$intervalReport$1.this$0.trackEventDao = (TrackEventDao) null;
                            }
                            NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent$intervalReport$1.this$0.TAG, "intervalReport error " + e);
                            coroutineScope2 = r1;
                            atomicBoolean2 = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                            atomicBoolean2.set(false);
                            coroutineScope4 = coroutineScope2;
                            repeatInterval = trackingCoreComponent$intervalReport$1.this$0.workRuler.getRepeatInterval();
                            trackingCoreComponent$intervalReport$1.L$0 = coroutineScope4;
                            trackingCoreComponent$intervalReport$1.label = 4;
                            if (DelayKt.delay(repeatInterval, trackingCoreComponent$intervalReport$1) != obj3) {
                            }
                        }
                        atomicBoolean6 = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                        atomicBoolean6.set(true);
                        TrackEventDao trackEventDao = trackingCoreComponent$intervalReport$1.this$0.trackEventDao;
                        CoroutineScope coroutineScope9 = r1;
                        if (trackEventDao != null) {
                            trackingCoreComponent$intervalReport$1.L$0 = r1;
                            trackingCoreComponent$intervalReport$1.label = 2;
                            Object newestList = trackEventDao.getNewestList(trackingCoreComponent$intervalReport$1);
                            if (newestList == obj3) {
                                return obj3;
                            }
                            obj4 = obj3;
                            obj = newestList;
                            r1 = r1;
                            try {
                            } catch (Exception e4) {
                                Object obj6 = obj4;
                                e = e4;
                                obj3 = obj6;
                                if (e instanceof SQLiteDatabaseCorruptException) {
                                }
                                NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent$intervalReport$1.this$0.TAG, "intervalReport error " + e);
                                coroutineScope2 = r1;
                                atomicBoolean2 = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                                atomicBoolean2.set(false);
                                coroutineScope4 = coroutineScope2;
                                repeatInterval = trackingCoreComponent$intervalReport$1.this$0.workRuler.getRepeatInterval();
                                trackingCoreComponent$intervalReport$1.L$0 = coroutineScope4;
                                trackingCoreComponent$intervalReport$1.label = 4;
                                if (DelayKt.delay(repeatInterval, trackingCoreComponent$intervalReport$1) != obj3) {
                                }
                            }
                            emptyList = (List) obj;
                            coroutineScope3 = r1;
                            if (emptyList == null) {
                                obj3 = obj4;
                                coroutineScope9 = r1;
                            }
                            trackingCoreComponent = trackingCoreComponent$intervalReport$1.this$0;
                            trackingCoreComponent$intervalReport$1.L$0 = coroutineScope3;
                            trackingCoreComponent$intervalReport$1.L$1 = emptyList;
                            trackingCoreComponent$intervalReport$1.label = 3;
                            if (trackingCoreComponent.report(emptyList, trackingCoreComponent$intervalReport$1) == obj4) {
                                return obj4;
                            }
                            obj3 = obj4;
                            coroutineScope2 = coroutineScope3;
                            atomicBoolean2 = trackingCoreComponent$intervalReport$1.this$0.isHandlerReport;
                            atomicBoolean2.set(false);
                            coroutineScope4 = coroutineScope2;
                        }
                        obj4 = obj3;
                        emptyList = CollectionsKt.emptyList();
                        coroutineScope3 = coroutineScope9;
                        trackingCoreComponent = trackingCoreComponent$intervalReport$1.this$0;
                        trackingCoreComponent$intervalReport$1.L$0 = coroutineScope3;
                        trackingCoreComponent$intervalReport$1.L$1 = emptyList;
                        trackingCoreComponent$intervalReport$1.label = 3;
                        if (trackingCoreComponent.report(emptyList, trackingCoreComponent$intervalReport$1) == obj4) {
                        }
                    }
                    repeatInterval = trackingCoreComponent$intervalReport$1.this$0.workRuler.getRepeatInterval();
                    trackingCoreComponent$intervalReport$1.L$0 = coroutineScope4;
                    trackingCoreComponent$intervalReport$1.label = 4;
                    if (DelayKt.delay(repeatInterval, trackingCoreComponent$intervalReport$1) != obj3) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                if (r1 != 4) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                CoroutineScope coroutineScope10 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope = coroutineScope10;
            }
        }
        obj2 = coroutine_suspended;
        trackingCoreComponent$intervalReport$1 = this;
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
        }
    }
}
