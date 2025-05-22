package com.pudutech.event_tracking.component;

import android.content.Context;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.event_tracking.WorkRuler;
import com.pudutech.event_tracking._EventTrackingManagerBuilder;
import com.pudutech.event_tracking.bean.TrackEvent;
import com.pudutech.event_tracking.bean.UpLoadReq;
import com.pudutech.event_tracking.p055db.TrackEventDao;
import com.pudutech.event_tracking.p055db.TrackEventDb;
import com.pudutech.event_tracking.upload.Api;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.log.NetWorkLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import retrofit2.Response;

/* compiled from: TrackingCoreComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u001e\u001a\u00020\u001fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010 J\u0011\u0010!\u001a\u00020\u001fH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010 J\b\u0010\"\u001a\u00020\u001fH\u0002J\u000e\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u0017J\u001f\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0019\u0010)\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u0017H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010*R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u0015\u001a&\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00170\u0017 \u0018*\u0012\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00170\u0017\u0018\u00010\u00190\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, m3961d2 = {"Lcom/pudutech/event_tracking/component/TrackingCoreComponent;", "", "context", "Landroid/content/Context;", "config", "Lcom/pudutech/event_tracking/_EventTrackingManagerBuilder;", "(Landroid/content/Context;Lcom/pudutech/event_tracking/_EventTrackingManagerBuilder;)V", "TAG", "", "api", "Lcom/pudutech/event_tracking/upload/Api;", "getContext", "()Landroid/content/Context;", "insertJob", "Lkotlinx/coroutines/Job;", "intervalJob", "isHandlerRemove", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isHandlerReport", "scope", "Lkotlinx/coroutines/CoroutineScope;", "tempEventList", "", "Lcom/pudutech/event_tracking/bean/TrackEvent;", "kotlin.jvm.PlatformType", "", "trackEventDao", "Lcom/pudutech/event_tracking/db/TrackEventDao;", "workRuler", "Lcom/pudutech/event_tracking/WorkRuler;", "deleteOldData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handlerDelete", "intervalReport", "onEvent", "event", "report", "", AIUIConstant.KEY_CONTENT, "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadNow", "(Lcom/pudutech/event_tracking/bean/TrackEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TrackingCoreComponent {
    private final String TAG;
    private final Api api;
    private final _EventTrackingManagerBuilder config;
    private final Context context;
    private Job insertJob;
    private Job intervalJob;
    private AtomicBoolean isHandlerRemove;
    private AtomicBoolean isHandlerReport;
    private final CoroutineScope scope;
    private final List<TrackEvent> tempEventList;
    private TrackEventDao trackEventDao;
    private final WorkRuler workRuler;

    public TrackingCoreComponent(Context context, _EventTrackingManagerBuilder config) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(config, "config");
        this.context = context;
        this.config = config;
        this.TAG = "TrackingCoreComponent";
        this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
        this.isHandlerRemove = new AtomicBoolean(false);
        this.isHandlerReport = new AtomicBoolean(false);
        this.trackEventDao = TrackEventDb.INSTANCE.getInstance(this.context).trackEventDao();
        this.workRuler = this.config.getWorkRuler();
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C44581(null), 3, null);
        this.api = (Api) PdNetworkManager.f10310INSTANCE.createService(Api.class);
        this.tempEventList = Collections.synchronizedList(new ArrayList());
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TrackingCoreComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.event_tracking.component.TrackingCoreComponent$1", m3970f = "TrackingCoreComponent.kt", m3971i = {0}, m3972l = {32}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
    /* renamed from: com.pudutech.event_tracking.component.TrackingCoreComponent$1 */
    /* loaded from: classes5.dex */
    public static final class C44581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5123p$;

        C44581(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C44581 c44581 = new C44581(completion);
            c44581.f5123p$ = (CoroutineScope) obj;
            return c44581;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C44581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5123p$;
                long initialDelay = TrackingCoreComponent.this.workRuler.getInitialDelay();
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(initialDelay, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope = coroutineScope2;
            }
            TrackingCoreComponent.this.intervalReport();
            CoroutineScope coroutineScope3 = coroutineScope;
            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass1(null), 3, null);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, new AnonymousClass2(null), 3, null);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: TrackingCoreComponent.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.event_tracking.component.TrackingCoreComponent$1$1", m3970f = "TrackingCoreComponent.kt", m3971i = {0, 0}, m3972l = {218}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$collect$iv"}, m3975s = {"L$0", "L$1"})
        /* renamed from: com.pudutech.event_tracking.component.TrackingCoreComponent$1$1, reason: invalid class name */
        /* loaded from: classes5.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            Object L$1;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f5124p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f5124p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Flow<Integer> countFlow;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5124p$;
                        TrackEventDao trackEventDao = TrackingCoreComponent.this.trackEventDao;
                        if (trackEventDao != null && (countFlow = trackEventDao.getCountFlow()) != null) {
                            TrackingCoreComponent$1$1$invokeSuspend$$inlined$collect$1 trackingCoreComponent$1$1$invokeSuspend$$inlined$collect$1 = new TrackingCoreComponent$1$1$invokeSuspend$$inlined$collect$1(this);
                            this.L$0 = coroutineScope;
                            this.L$1 = countFlow;
                            this.label = 1;
                            if (countFlow.collect(trackingCoreComponent$1$1$invokeSuspend$$inlined$collect$1, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                } catch (Exception e) {
                    if (e instanceof SQLiteDatabaseCorruptException) {
                        TrackingCoreComponent.this.trackEventDao = (TrackEventDao) null;
                    }
                    NetWorkLog.INSTANCE.mo3280i(TrackingCoreComponent.this.TAG, "getCountFlow error " + e);
                    e.printStackTrace();
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: TrackingCoreComponent.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.event_tracking.component.TrackingCoreComponent$1$2", m3970f = "TrackingCoreComponent.kt", m3971i = {0, 0}, m3972l = {218}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$collect$iv"}, m3975s = {"L$0", "L$1"})
        /* renamed from: com.pudutech.event_tracking.component.TrackingCoreComponent$1$2, reason: invalid class name */
        /* loaded from: classes5.dex */
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            Object L$1;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f5125p$;

            AnonymousClass2(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(completion);
                anonymousClass2.f5125p$ = (CoroutineScope) obj;
                return anonymousClass2;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Flow<Integer> countFlowByUpload;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5125p$;
                        TrackEventDao trackEventDao = TrackingCoreComponent.this.trackEventDao;
                        if (trackEventDao != null && (countFlowByUpload = trackEventDao.getCountFlowByUpload(0)) != null) {
                            FlowCollector<Integer> flowCollector = new FlowCollector<Integer>() { // from class: com.pudutech.event_tracking.component.TrackingCoreComponent$1$2$invokeSuspend$$inlined$collect$1
                                @Override // kotlinx.coroutines.flow.FlowCollector
                                public Object emit(Integer num, Continuation continuation) {
                                    AtomicBoolean atomicBoolean;
                                    int intValue = num.intValue();
                                    if (intValue > TrackingCoreComponent.this.workRuler.getMaxUnUploadCount()) {
                                        atomicBoolean = TrackingCoreComponent.this.isHandlerReport;
                                        if (!atomicBoolean.get()) {
                                            NetWorkLog.INSTANCE.mo3280i(TrackingCoreComponent.this.TAG, "getCount UN_UPLOAD " + intValue + ' ');
                                            TrackingCoreComponent.this.intervalReport();
                                        }
                                    }
                                    return Unit.INSTANCE;
                                }
                            };
                            this.L$0 = coroutineScope;
                            this.L$1 = countFlowByUpload;
                            this.label = 1;
                            if (countFlowByUpload.collect(flowCollector, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                } catch (Exception e) {
                    if (e instanceof SQLiteDatabaseCorruptException) {
                        TrackingCoreComponent.this.trackEventDao = (TrackEventDao) null;
                    }
                    NetWorkLog.INSTANCE.mo3280i(TrackingCoreComponent.this.TAG, "getCountFlowByUpload error " + e);
                    e.printStackTrace();
                }
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void intervalReport() {
        Job launch$default;
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("intervalReport ");
        Job job = this.intervalJob;
        sb.append(job != null ? Boolean.valueOf(job.isActive()) : null);
        netWorkLog.mo3280i(str, sb.toString());
        Job job2 = this.intervalJob;
        if (job2 == null || !job2.isActive()) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, ThreadPoolDispatcherKt.newSingleThreadContext("TrackingCoreReportThread"), null, new TrackingCoreComponent$intervalReport$1(this, null), 2, null);
            this.intervalJob = launch$default;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(16:1|(2:3|(13:5|6|7|(1:(1:(1:(7:12|13|14|15|16|17|18)(2:28|29))(7:30|31|32|(4:34|(1:36)|37|(1:39))|16|17|18))(2:41|42))(2:49|(2:51|52)(6:53|54|55|(1:57)|58|(1:60)(1:61)))|43|(1:45)|46|(1:48)|32|(0)|16|17|18))|68|6|7|(0)(0)|43|(0)|46|(0)|32|(0)|16|17|18|(1:(0))) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e4 A[Catch: all -> 0x005e, Exception -> 0x0105, TryCatch #1 {Exception -> 0x0105, blocks: (B:31:0x0052, B:32:0x00af, B:34:0x00e4, B:36:0x00e8, B:37:0x00eb, B:42:0x005a, B:43:0x009d, B:45:0x00a1, B:46:0x00a4), top: B:7:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a1 A[Catch: all -> 0x005e, Exception -> 0x0105, TryCatch #1 {Exception -> 0x0105, blocks: (B:31:0x0052, B:32:0x00af, B:34:0x00e4, B:36:0x00e8, B:37:0x00eb, B:42:0x005a, B:43:0x009d, B:45:0x00a1, B:46:0x00a4), top: B:7:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ae A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    /* JADX WARN: Type inference failed for: r4v11, types: [com.pudutech.event_tracking.component.TrackingCoreComponent, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.pudutech.event_tracking.component.TrackingCoreComponent] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object handlerDelete(Continuation<? super Unit> continuation) {
        TrackingCoreComponent$handlerDelete$1 trackingCoreComponent$handlerDelete$1;
        Object obj;
        Object coroutine_suspended;
        TrackingCoreComponent trackingCoreComponent;
        TrackingCoreComponent trackingCoreComponent2;
        TrackingCoreComponent trackingCoreComponent3;
        TrackEventDao trackEventDao;
        double maxNum;
        try {
            if (continuation instanceof TrackingCoreComponent$handlerDelete$1) {
                trackingCoreComponent$handlerDelete$1 = (TrackingCoreComponent$handlerDelete$1) continuation;
                if ((trackingCoreComponent$handlerDelete$1.label & Integer.MIN_VALUE) != 0) {
                    trackingCoreComponent$handlerDelete$1.label -= Integer.MIN_VALUE;
                    obj = trackingCoreComponent$handlerDelete$1.result;
                    coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    trackingCoreComponent = trackingCoreComponent$handlerDelete$1.label;
                    if (trackingCoreComponent != 0) {
                        ResultKt.throwOnFailure(obj);
                        NetWorkLog.INSTANCE.mo3280i(this.TAG, "handlerDelete start " + this.trackEventDao);
                        if (this.trackEventDao == null) {
                            return Unit.INSTANCE;
                        }
                        this.isHandlerRemove.set(true);
                        try {
                            TrackEventDao trackEventDao2 = this.trackEventDao;
                            if (trackEventDao2 == null) {
                                Intrinsics.throwNpe();
                            }
                            trackingCoreComponent$handlerDelete$1.L$0 = this;
                            trackingCoreComponent$handlerDelete$1.label = 1;
                            if (trackEventDao2.deleteAllUpload(trackingCoreComponent$handlerDelete$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            trackingCoreComponent3 = this;
                        } catch (Exception unused) {
                            trackingCoreComponent = this;
                            NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent.TAG, "handlerDelete.error " + trackingCoreComponent.trackEventDao);
                            trackingCoreComponent2 = trackingCoreComponent;
                            trackingCoreComponent2.isHandlerRemove.set(false);
                            return Unit.INSTANCE;
                        } catch (Throwable th) {
                            th = th;
                            trackingCoreComponent = this;
                            trackingCoreComponent.isHandlerRemove.set(false);
                            throw th;
                        }
                    } else {
                        if (trackingCoreComponent != 1) {
                            if (trackingCoreComponent != 2) {
                                if (trackingCoreComponent == 3) {
                                    double d = trackingCoreComponent$handlerDelete$1.D$0;
                                    int i = trackingCoreComponent$handlerDelete$1.I$0;
                                    TrackingCoreComponent trackingCoreComponent4 = (TrackingCoreComponent) trackingCoreComponent$handlerDelete$1.L$0;
                                    try {
                                        ResultKt.throwOnFailure(obj);
                                        trackingCoreComponent2 = trackingCoreComponent4;
                                    } catch (Exception unused2) {
                                        trackingCoreComponent = trackingCoreComponent4;
                                        NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent.TAG, "handlerDelete.error " + trackingCoreComponent.trackEventDao);
                                        trackingCoreComponent2 = trackingCoreComponent;
                                        trackingCoreComponent2.isHandlerRemove.set(false);
                                        return Unit.INSTANCE;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        trackingCoreComponent = trackingCoreComponent4;
                                        trackingCoreComponent.isHandlerRemove.set(false);
                                        throw th;
                                    }
                                    trackingCoreComponent2.isHandlerRemove.set(false);
                                    return Unit.INSTANCE;
                                }
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            TrackingCoreComponent trackingCoreComponent5 = (TrackingCoreComponent) trackingCoreComponent$handlerDelete$1.L$0;
                            ResultKt.throwOnFailure(obj);
                            trackingCoreComponent = trackingCoreComponent5;
                            int intValue = ((Number) obj).intValue();
                            NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent.TAG, "handlerDelete " + intValue);
                            maxNum = ((double) intValue) - (((double) trackingCoreComponent.workRuler.getMaxNum()) * (((double) 1) - trackingCoreComponent.workRuler.getMinAvailableSpace()));
                            trackingCoreComponent2 = trackingCoreComponent;
                            if (maxNum > 0) {
                                TrackEventDao trackEventDao3 = trackingCoreComponent.trackEventDao;
                                if (trackEventDao3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                trackingCoreComponent$handlerDelete$1.L$0 = trackingCoreComponent;
                                trackingCoreComponent$handlerDelete$1.I$0 = intValue;
                                trackingCoreComponent$handlerDelete$1.D$0 = maxNum;
                                trackingCoreComponent$handlerDelete$1.label = 3;
                                trackingCoreComponent2 = trackingCoreComponent;
                                if (trackEventDao3.deleteOldest((long) maxNum, trackingCoreComponent$handlerDelete$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                            trackingCoreComponent2.isHandlerRemove.set(false);
                            return Unit.INSTANCE;
                        }
                        TrackingCoreComponent trackingCoreComponent6 = (TrackingCoreComponent) trackingCoreComponent$handlerDelete$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        trackingCoreComponent3 = trackingCoreComponent6;
                    }
                    trackEventDao = trackingCoreComponent3.trackEventDao;
                    if (trackEventDao == null) {
                        Intrinsics.throwNpe();
                    }
                    trackingCoreComponent$handlerDelete$1.L$0 = trackingCoreComponent3;
                    trackingCoreComponent$handlerDelete$1.label = 2;
                    obj = trackEventDao.getCount(trackingCoreComponent$handlerDelete$1);
                    trackingCoreComponent = trackingCoreComponent3;
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    int intValue2 = ((Number) obj).intValue();
                    NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent.TAG, "handlerDelete " + intValue2);
                    maxNum = ((double) intValue2) - (((double) trackingCoreComponent.workRuler.getMaxNum()) * (((double) 1) - trackingCoreComponent.workRuler.getMinAvailableSpace()));
                    trackingCoreComponent2 = trackingCoreComponent;
                    if (maxNum > 0) {
                    }
                    trackingCoreComponent2.isHandlerRemove.set(false);
                    return Unit.INSTANCE;
                }
            }
            if (trackingCoreComponent != 0) {
            }
            trackEventDao = trackingCoreComponent3.trackEventDao;
            if (trackEventDao == null) {
            }
            trackingCoreComponent$handlerDelete$1.L$0 = trackingCoreComponent3;
            trackingCoreComponent$handlerDelete$1.label = 2;
            obj = trackEventDao.getCount(trackingCoreComponent$handlerDelete$1);
            trackingCoreComponent = trackingCoreComponent3;
            if (obj == coroutine_suspended) {
            }
            int intValue22 = ((Number) obj).intValue();
            NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent.TAG, "handlerDelete " + intValue22);
            maxNum = ((double) intValue22) - (((double) trackingCoreComponent.workRuler.getMaxNum()) * (((double) 1) - trackingCoreComponent.workRuler.getMinAvailableSpace()));
            trackingCoreComponent2 = trackingCoreComponent;
            if (maxNum > 0) {
            }
            trackingCoreComponent2.isHandlerRemove.set(false);
            return Unit.INSTANCE;
        } catch (Throwable th3) {
            th = th3;
        }
        trackingCoreComponent$handlerDelete$1 = new TrackingCoreComponent$handlerDelete$1(this, continuation);
        obj = trackingCoreComponent$handlerDelete$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        trackingCoreComponent = trackingCoreComponent$handlerDelete$1.label;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x011b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object report(List<TrackEvent> list, Continuation<? super Boolean> continuation) {
        TrackingCoreComponent$report$1 trackingCoreComponent$report$1;
        int i;
        List<TrackEvent> list2;
        TrackingCoreComponent trackingCoreComponent;
        Response response;
        boolean z;
        if (continuation instanceof TrackingCoreComponent$report$1) {
            trackingCoreComponent$report$1 = (TrackingCoreComponent$report$1) continuation;
            if ((trackingCoreComponent$report$1.label & Integer.MIN_VALUE) != 0) {
                trackingCoreComponent$report$1.label -= Integer.MIN_VALUE;
                Object obj = trackingCoreComponent$report$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = trackingCoreComponent$report$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    NetWorkLog.INSTANCE.mo3280i(this.TAG, "report " + list.size());
                    if (list.isEmpty()) {
                        return Boxing.boxBoolean(false);
                    }
                    Api api = this.api;
                    UpLoadReq upLoadReq = new UpLoadReq(list, 0L, null, 6, null);
                    trackingCoreComponent$report$1.L$0 = this;
                    trackingCoreComponent$report$1.L$1 = list;
                    trackingCoreComponent$report$1.label = 1;
                    obj = api.upload(upLoadReq, trackingCoreComponent$report$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    list2 = list;
                    trackingCoreComponent = this;
                } else {
                    if (i != 1) {
                        if (i != 2) {
                            if (i == 3) {
                                boolean z2 = trackingCoreComponent$report$1.Z$0;
                                ResultKt.throwOnFailure(obj);
                                z = z2;
                                return Boxing.boxBoolean(z);
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        boolean z3 = trackingCoreComponent$report$1.Z$0;
                        Response response2 = (Response) trackingCoreComponent$report$1.L$2;
                        List<TrackEvent> list3 = (List) trackingCoreComponent$report$1.L$1;
                        TrackingCoreComponent trackingCoreComponent2 = (TrackingCoreComponent) trackingCoreComponent$report$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        response = response2;
                        trackingCoreComponent = trackingCoreComponent2;
                        z = z3;
                        list2 = list3;
                        trackingCoreComponent$report$1.L$0 = trackingCoreComponent;
                        trackingCoreComponent$report$1.L$1 = list2;
                        trackingCoreComponent$report$1.L$2 = response;
                        trackingCoreComponent$report$1.Z$0 = z;
                        trackingCoreComponent$report$1.label = 3;
                        if (trackingCoreComponent.deleteOldData(trackingCoreComponent$report$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Boxing.boxBoolean(z);
                    }
                    list2 = (List) trackingCoreComponent$report$1.L$1;
                    trackingCoreComponent = (TrackingCoreComponent) trackingCoreComponent$report$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                response = (Response) obj;
                NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent.TAG, "report res " + response);
                z = response.code() < 500;
                if (z) {
                    Iterator<T> it = list2.iterator();
                    while (it.hasNext()) {
                        ((TrackEvent) it.next()).setUpload(1);
                    }
                    TrackEventDao trackEventDao = trackingCoreComponent.trackEventDao;
                    if (trackEventDao != null) {
                        trackingCoreComponent$report$1.L$0 = trackingCoreComponent;
                        trackingCoreComponent$report$1.L$1 = list2;
                        trackingCoreComponent$report$1.L$2 = response;
                        trackingCoreComponent$report$1.Z$0 = z;
                        trackingCoreComponent$report$1.label = 2;
                        if (trackEventDao.update(list2, trackingCoreComponent$report$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    trackingCoreComponent$report$1.L$0 = trackingCoreComponent;
                    trackingCoreComponent$report$1.L$1 = list2;
                    trackingCoreComponent$report$1.L$2 = response;
                    trackingCoreComponent$report$1.Z$0 = z;
                    trackingCoreComponent$report$1.label = 3;
                    if (trackingCoreComponent.deleteOldData(trackingCoreComponent$report$1) == coroutine_suspended) {
                    }
                }
                return Boxing.boxBoolean(z);
            }
        }
        trackingCoreComponent$report$1 = new TrackingCoreComponent$report$1(this, continuation);
        Object obj2 = trackingCoreComponent$report$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = trackingCoreComponent$report$1.label;
        if (i != 0) {
        }
        response = (Response) obj2;
        NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent.TAG, "report res " + response);
        if (response.code() < 500) {
        }
        if (z) {
        }
        return Boxing.boxBoolean(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object deleteOldData(Continuation<? super Unit> continuation) {
        TrackingCoreComponent$deleteOldData$1 trackingCoreComponent$deleteOldData$1;
        int i;
        if (continuation instanceof TrackingCoreComponent$deleteOldData$1) {
            trackingCoreComponent$deleteOldData$1 = (TrackingCoreComponent$deleteOldData$1) continuation;
            if ((trackingCoreComponent$deleteOldData$1.label & Integer.MIN_VALUE) != 0) {
                trackingCoreComponent$deleteOldData$1.label -= Integer.MIN_VALUE;
                Object obj = trackingCoreComponent$deleteOldData$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = trackingCoreComponent$deleteOldData$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    long currentTimeMillis = System.currentTimeMillis() - this.workRuler.getDataSaveTime();
                    TrackEventDao trackEventDao = this.trackEventDao;
                    if (trackEventDao != null) {
                        trackingCoreComponent$deleteOldData$1.L$0 = this;
                        trackingCoreComponent$deleteOldData$1.J$0 = currentTimeMillis;
                        trackingCoreComponent$deleteOldData$1.label = 1;
                        if (trackEventDao.delete(currentTimeMillis, trackingCoreComponent$deleteOldData$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    long j = trackingCoreComponent$deleteOldData$1.J$0;
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
        trackingCoreComponent$deleteOldData$1 = new TrackingCoreComponent$deleteOldData$1(this, continuation);
        Object obj2 = trackingCoreComponent$deleteOldData$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = trackingCoreComponent$deleteOldData$1.label;
        if (i != 0) {
        }
        return Unit.INSTANCE;
    }

    public final synchronized void onEvent(TrackEvent event) {
        Job launch$default;
        Job job;
        Intrinsics.checkParameterIsNotNull(event, "event");
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "onEvent " + event + ' ' + this.trackEventDao);
        if (!this.config.getReportData()) {
            NetWorkLog.INSTANCE.mo3280i(this.TAG, "onEvent config.reportData : " + this.config.getReportData() + ' ');
            return;
        }
        if (this.trackEventDao == null) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new TrackingCoreComponent$onEvent$1(this, event, null), 3, null);
            return;
        }
        if (event.getPriority() != 0) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new TrackingCoreComponent$onEvent$3(this, event, null), 3, null);
        } else {
            this.tempEventList.add(event);
            if (this.insertJob == null || ((job = this.insertJob) != null && !job.isActive())) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new TrackingCoreComponent$onEvent$2(this, null), 3, null);
                this.insertJob = launch$default;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(16:1|(2:3|(13:5|6|7|(1:(1:(5:11|12|13|14|15)(2:18|19))(3:20|21|22))(4:38|39|40|(1:42)(1:43))|23|(1:25)(1:35)|(1:27)|29|30|(2:32|(1:34))|13|14|15))|49|6|7|(0)(0)|23|(0)(0)|(0)|29|30|(0)|13|14|15|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0036, code lost:
    
        r14 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00c0, code lost:
    
        r13.trackEventDao = (com.pudutech.event_tracking.p055db.TrackEventDao) null;
        com.pudutech.pd_network.log.NetWorkLog.INSTANCE.mo3280i(r13.TAG, "uploadNow error " + r14);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0080 A[Catch: Exception -> 0x004d, TRY_LEAVE, TryCatch #0 {Exception -> 0x004d, blocks: (B:22:0x0049, B:23:0x0071, B:27:0x0080), top: B:21:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b3 A[Catch: Exception -> 0x0036, TRY_LEAVE, TryCatch #1 {Exception -> 0x0036, blocks: (B:12:0x0031, B:30:0x00af, B:32:0x00b3), top: B:7:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.pudutech.event_tracking.bean.TrackEvent, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v1, types: [com.pudutech.event_tracking.component.TrackingCoreComponent] */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v12, types: [com.pudutech.event_tracking.bean.TrackEvent] */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v22 */
    /* JADX WARN: Type inference failed for: r13v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v9, types: [com.pudutech.event_tracking.bean.TrackEvent] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.StringBuilder] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object uploadNow(TrackEvent trackEvent, Continuation<? super Unit> continuation) {
        TrackingCoreComponent$uploadNow$1 trackingCoreComponent$uploadNow$1;
        Object obj;
        int i;
        TrackingCoreComponent trackingCoreComponent;
        TrackEventDao trackEventDao;
        if (continuation instanceof TrackingCoreComponent$uploadNow$1) {
            trackingCoreComponent$uploadNow$1 = (TrackingCoreComponent$uploadNow$1) continuation;
            if ((trackingCoreComponent$uploadNow$1.label & Integer.MIN_VALUE) != 0) {
                trackingCoreComponent$uploadNow$1.label -= Integer.MIN_VALUE;
                obj = trackingCoreComponent$uploadNow$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = trackingCoreComponent$uploadNow$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        Api api = this.api;
                        UpLoadReq upLoadReq = new UpLoadReq(CollectionsKt.listOf(trackEvent), 0L, null, 6, null);
                        trackingCoreComponent$uploadNow$1.L$0 = this;
                        trackingCoreComponent$uploadNow$1.L$1 = trackEvent;
                        trackingCoreComponent$uploadNow$1.label = 1;
                        obj = api.upload(upLoadReq, trackingCoreComponent$uploadNow$1);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        trackingCoreComponent = this;
                        trackEvent = trackEvent;
                    } catch (Exception e) {
                        e = e;
                        trackingCoreComponent = this;
                        e.printStackTrace();
                        NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent.TAG, "uploadNow.error > event:" + trackEvent + " e:" + e.getMessage());
                        TrackEvent trackEvent2 = trackEvent;
                        TrackingCoreComponent trackingCoreComponent2 = trackingCoreComponent;
                        trackEventDao = trackingCoreComponent2.trackEventDao;
                        trackEvent = trackingCoreComponent2;
                        if (trackEventDao != null) {
                        }
                        return Unit.INSTANCE;
                    }
                } else {
                    if (i != 1) {
                        if (i == 2) {
                            TrackingCoreComponent trackingCoreComponent3 = (TrackingCoreComponent) trackingCoreComponent$uploadNow$1.L$0;
                            ResultKt.throwOnFailure(obj);
                            trackEvent = trackingCoreComponent3;
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    trackEvent = (TrackEvent) trackingCoreComponent$uploadNow$1.L$1;
                    trackingCoreComponent = (TrackingCoreComponent) trackingCoreComponent$uploadNow$1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        trackEvent = trackEvent;
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        NetWorkLog.INSTANCE.mo3280i(trackingCoreComponent.TAG, "uploadNow.error > event:" + trackEvent + " e:" + e.getMessage());
                        TrackEvent trackEvent22 = trackEvent;
                        TrackingCoreComponent trackingCoreComponent22 = trackingCoreComponent;
                        trackEventDao = trackingCoreComponent22.trackEventDao;
                        trackEvent = trackingCoreComponent22;
                        if (trackEventDao != null) {
                        }
                        return Unit.INSTANCE;
                    }
                }
                if (((Response) obj).code() >= 500) {
                    trackEvent.setUpload(1);
                }
                TrackEvent trackEvent222 = trackEvent;
                TrackingCoreComponent trackingCoreComponent222 = trackingCoreComponent;
                trackEventDao = trackingCoreComponent222.trackEventDao;
                trackEvent = trackingCoreComponent222;
                if (trackEventDao != null) {
                    trackingCoreComponent$uploadNow$1.L$0 = trackingCoreComponent222;
                    trackingCoreComponent$uploadNow$1.L$1 = trackEvent222;
                    trackingCoreComponent$uploadNow$1.label = 2;
                    Object insert = trackEventDao.insert(trackEvent222, trackingCoreComponent$uploadNow$1);
                    trackEvent = insert;
                    if (insert == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
        }
        trackingCoreComponent$uploadNow$1 = new TrackingCoreComponent$uploadNow$1(this, continuation);
        obj = trackingCoreComponent$uploadNow$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = trackingCoreComponent$uploadNow$1.label;
        if (i != 0) {
        }
        if (((Response) obj).code() >= 500) {
        }
        TrackEvent trackEvent2222 = trackEvent;
        TrackingCoreComponent trackingCoreComponent2222 = trackingCoreComponent;
        trackEventDao = trackingCoreComponent2222.trackEventDao;
        trackEvent = trackingCoreComponent2222;
        if (trackEventDao != null) {
        }
        return Unit.INSTANCE;
    }
}
