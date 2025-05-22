package androidx.room;

import androidx.room.InvalidationTracker;
import com.iflytek.cloud.SpeechUtility;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: CoroutinesRoom.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0010\u0012\f\u0012\n \u0004*\u0004\u0018\u0001H\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 15})
@DebugMetadata(m3969c = "androidx.room.CoroutinesRoom$Companion$createFlow$1", m3970f = "CoroutinesRoom.kt", m3971i = {0, 0, 0, 0, 0}, m3972l = {75}, m3973m = "invokeSuspend", m3974n = {"$this$flow", "observerChannel", "observer", "flowContext", "queryContext"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes.dex */
public final class CoroutinesRoom$Companion$createFlow$1<R> extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Callable $callable;
    final /* synthetic */ RoomDatabase $db;
    final /* synthetic */ boolean $inTransaction;
    final /* synthetic */ String[] $tableNames;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private FlowCollector f138p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutinesRoom$Companion$createFlow$1(String[] strArr, boolean z, RoomDatabase roomDatabase, Callable callable, Continuation continuation) {
        super(2, continuation);
        this.$tableNames = strArr;
        this.$inTransaction = z;
        this.$db = roomDatabase;
        this.$callable = callable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CoroutinesRoom$Companion$createFlow$1 coroutinesRoom$Companion$createFlow$1 = new CoroutinesRoom$Companion$createFlow$1(this.$tableNames, this.$inTransaction, this.$db, this.$callable, completion);
        coroutinesRoom$Companion$createFlow$1.f138p$ = (FlowCollector) obj;
        return coroutinesRoom$Companion$createFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((CoroutinesRoom$Companion$createFlow$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Object, androidx.room.CoroutinesRoom$Companion$createFlow$1$observer$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.f138p$;
            final Channel Channel = ChannelKt.Channel(-1);
            final String[] strArr = this.$tableNames;
            ?? r12 = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.CoroutinesRoom$Companion$createFlow$1$observer$1
                @Override // androidx.room.InvalidationTracker.Observer
                public void onInvalidated(Set<String> tables) {
                    Intrinsics.checkParameterIsNotNull(tables, "tables");
                    Channel.offer(Unit.INSTANCE);
                }
            };
            Channel.offer(Unit.INSTANCE);
            CoroutineContext context = getContext();
            CoroutineDispatcher transactionDispatcher = this.$inTransaction ? CoroutinesRoomKt.getTransactionDispatcher(this.$db) : CoroutinesRoomKt.getQueryDispatcher(this.$db);
            C05961 c05961 = new C05961(flowCollector, r12, Channel, context, null);
            this.L$0 = flowCollector;
            this.L$1 = Channel;
            this.L$2 = r12;
            this.L$3 = context;
            this.L$4 = transactionDispatcher;
            this.label = 1;
            if (BuildersKt.withContext(transactionDispatcher, c05961, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CoroutinesRoom.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 15})
    @DebugMetadata(m3969c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1", m3970f = "CoroutinesRoom.kt", m3971i = {0, 1, 1, 1}, m3972l = {80, 82}, m3973m = "invokeSuspend", m3974n = {"$this$withContext", "$this$withContext", "signal", SpeechUtility.TAG_RESOURCE_RESULT}, m3975s = {"L$0", "L$0", "L$1", "L$3"})
    /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1 */
    /* loaded from: classes.dex */
    public static final class C05961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineContext $flowContext;
        final /* synthetic */ CoroutinesRoom$Companion$createFlow$1$observer$1 $observer;
        final /* synthetic */ Channel $observerChannel;
        final /* synthetic */ FlowCollector $this_flow;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f139p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05961(FlowCollector flowCollector, CoroutinesRoom$Companion$createFlow$1$observer$1 coroutinesRoom$Companion$createFlow$1$observer$1, Channel channel, CoroutineContext coroutineContext, Continuation continuation) {
            super(2, continuation);
            this.$this_flow = flowCollector;
            this.$observer = coroutinesRoom$Companion$createFlow$1$observer$1;
            this.$observerChannel = channel;
            this.$flowContext = coroutineContext;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C05961 c05961 = new C05961(this.$this_flow, this.$observer, this.$observerChannel, this.$flowContext, completion);
            c05961.f139p$ = (CoroutineScope) obj;
            return c05961;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05961) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0071 A[Catch: all -> 0x00b0, TRY_LEAVE, TryCatch #0 {all -> 0x00b0, blocks: (B:16:0x0069, B:18:0x0071), top: B:15:0x0069 }] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x009e  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x009a -> B:9:0x0057). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            C05961 c05961;
            Object obj2;
            CoroutineScope coroutineScope;
            C05961 c059612;
            Object obj3;
            CoroutineScope coroutineScope2;
            ChannelIterator channelIterator;
            ChannelIterator channelIterator2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Throwable th) {
                th = th;
                c05961 = this;
            }
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope3 = this.f139p$;
                    CoroutinesRoom$Companion$createFlow$1.this.$db.getInvalidationTracker().addObserver(this.$observer);
                    obj2 = coroutine_suspended;
                    coroutineScope = coroutineScope3;
                    c059612 = this;
                    channelIterator2 = this.$observerChannel.iterator();
                } else if (i == 1) {
                    ChannelIterator channelIterator3 = (ChannelIterator) this.L$1;
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    obj3 = coroutine_suspended;
                    c05961 = this;
                    channelIterator = channelIterator3;
                    try {
                        if (!((Boolean) obj).booleanValue()) {
                            Unit unit = (Unit) channelIterator.next();
                            Object call = CoroutinesRoom$Companion$createFlow$1.this.$callable.call();
                            CoroutineContext coroutineContext = c05961.$flowContext;
                            AnonymousClass1 anonymousClass1 = new AnonymousClass1(call, null);
                            c05961.L$0 = coroutineScope2;
                            c05961.L$1 = unit;
                            c05961.L$2 = channelIterator;
                            c05961.L$3 = call;
                            c05961.label = 2;
                            if (BuildersKt.withContext(coroutineContext, anonymousClass1, c05961) == obj3) {
                                return obj3;
                            }
                            c059612 = c05961;
                            coroutineScope = coroutineScope2;
                            obj2 = obj3;
                            channelIterator2 = channelIterator;
                        } else {
                            CoroutinesRoom$Companion$createFlow$1.this.$db.getInvalidationTracker().removeObserver(c05961.$observer);
                            return Unit.INSTANCE;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        CoroutinesRoom$Companion$createFlow$1.this.$db.getInvalidationTracker().removeObserver(c05961.$observer);
                        throw th;
                    }
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    Object obj4 = this.L$3;
                    ChannelIterator channelIterator4 = (ChannelIterator) this.L$2;
                    CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    c059612 = this;
                    obj2 = coroutine_suspended;
                    coroutineScope = coroutineScope4;
                    channelIterator2 = channelIterator4;
                }
                c059612.L$0 = coroutineScope;
                c059612.L$1 = channelIterator2;
                c059612.label = 1;
                Object hasNext = channelIterator2.hasNext(c059612);
                if (hasNext == obj2) {
                    return obj2;
                }
                CoroutineScope coroutineScope5 = coroutineScope;
                c05961 = c059612;
                obj = hasNext;
                obj3 = obj2;
                coroutineScope2 = coroutineScope5;
                channelIterator = channelIterator2;
                if (!((Boolean) obj).booleanValue()) {
                }
            } catch (Throwable th3) {
                c05961 = c059612;
                th = th3;
                CoroutinesRoom$Companion$createFlow$1.this.$db.getInvalidationTracker().removeObserver(c05961.$observer);
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: CoroutinesRoom.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 15})
        @DebugMetadata(m3969c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1", m3970f = "CoroutinesRoom.kt", m3971i = {0}, m3972l = {82}, m3973m = "invokeSuspend", m3974n = {"$this$withContext"}, m3975s = {"L$0"})
        /* renamed from: androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Object $result;
            Object L$0;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f140p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Object obj, Continuation continuation) {
                super(2, continuation);
                this.$result = obj;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$result, completion);
                anonymousClass1.f140p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f140p$;
                    FlowCollector flowCollector = C05961.this.$this_flow;
                    Object obj2 = this.$result;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (flowCollector.emit(obj2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }
}
