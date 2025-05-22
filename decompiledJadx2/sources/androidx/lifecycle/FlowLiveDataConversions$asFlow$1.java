package androidx.lifecycle;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowCollector;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: FlowLiveData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 15})
@DebugMetadata(m3969c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1", m3970f = "FlowLiveData.kt", m3971i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 2}, m3972l = {91, 95, 96}, m3973m = "invokeSuspend", m3974n = {"$this$flow", "channel", "observer", "$this$flow", "channel", "observer", "$this$flow", "channel", "observer", ES6Iterator.VALUE_PROPERTY}, m3975s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes.dex */
public final class FlowLiveDataConversions$asFlow$1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ LiveData $this_asFlow;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private FlowCollector f109p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowLiveDataConversions$asFlow$1(LiveData liveData, Continuation continuation) {
        super(2, continuation);
        this.$this_asFlow = liveData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FlowLiveDataConversions$asFlow$1 flowLiveDataConversions$asFlow$1 = new FlowLiveDataConversions$asFlow$1(this.$this_asFlow, completion);
        flowLiveDataConversions$asFlow$1.f109p$ = (FlowCollector) obj;
        return flowLiveDataConversions$asFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((FlowLiveDataConversions$asFlow$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00ab A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ba A[Catch: all -> 0x00f7, TRY_LEAVE, TryCatch #0 {all -> 0x00f7, blocks: (B:17:0x00b2, B:19:0x00ba), top: B:16:0x00b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00d6  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.lifecycle.Observer] */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.lifecycle.Observer, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v6, types: [kotlinx.coroutines.flow.FlowCollector, java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00d1 -> B:10:0x009b). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        FlowLiveDataConversions$asFlow$1<T> flowLiveDataConversions$asFlow$1;
        FlowCollector flowCollector;
        final Channel Channel;
        Observer<T> observer;
        Object obj2;
        Channel channel;
        FlowLiveDataConversions$asFlow$1<T> flowLiveDataConversions$asFlow$12;
        Object obj3;
        ?? r7;
        Channel channel2;
        ChannelIterator channelIterator;
        ChannelIterator channelIterator2;
        Object hasNext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        ?? r4 = 1;
        try {
        } catch (Throwable th) {
            th = th;
            flowLiveDataConversions$asFlow$1 = this;
        }
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = this.f109p$;
                Channel = ChannelKt.Channel(-1);
                observer = new Observer<T>() { // from class: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$observer$1
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(T t) {
                        Channel.this.offer(t);
                    }
                };
                MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
                C04421 c04421 = new C04421(observer, null);
                this.L$0 = flowCollector;
                this.L$1 = Channel;
                this.L$2 = observer;
                this.label = 1;
                if (BuildersKt.withContext(immediate, c04421, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                observer = (Observer) this.L$2;
                Channel channel3 = (Channel) this.L$1;
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
                Channel = channel3;
            } else if (i == 2) {
                ChannelIterator channelIterator3 = (ChannelIterator) this.L$3;
                Observer observer2 = (Observer) this.L$2;
                channel2 = (Channel) this.L$1;
                FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj3 = coroutine_suspended;
                flowLiveDataConversions$asFlow$1 = this;
                channelIterator = channelIterator3;
                r4 = observer2;
                r7 = flowCollector2;
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ChannelIterator channelIterator4 = (ChannelIterator) this.L$4;
                Object obj4 = this.L$3;
                Observer observer3 = (Observer) this.L$2;
                Channel channel4 = (Channel) this.L$1;
                FlowCollector flowCollector3 = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
                flowLiveDataConversions$asFlow$12 = this;
                obj2 = coroutine_suspended;
                channel = channel4;
                flowCollector = flowCollector3;
                channelIterator2 = channelIterator4;
                r4 = observer3;
                flowLiveDataConversions$asFlow$12.L$0 = flowCollector;
                flowLiveDataConversions$asFlow$12.L$1 = channel;
                flowLiveDataConversions$asFlow$12.L$2 = r4;
                flowLiveDataConversions$asFlow$12.L$3 = channelIterator2;
                flowLiveDataConversions$asFlow$12.label = 2;
                hasNext = channelIterator2.hasNext(flowLiveDataConversions$asFlow$12);
                if (hasNext == obj2) {
                    return obj2;
                }
                Channel channel5 = channel;
                flowLiveDataConversions$asFlow$1 = flowLiveDataConversions$asFlow$12;
                obj = hasNext;
                obj3 = obj2;
                r7 = flowCollector;
                channel2 = channel5;
                channelIterator = channelIterator2;
                r4 = r4;
                try {
                    if (((Boolean) obj).booleanValue()) {
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C04432(r4, null), 2, null);
                        return Unit.INSTANCE;
                    }
                    Object next = channelIterator.next();
                    flowLiveDataConversions$asFlow$1.L$0 = r7;
                    flowLiveDataConversions$asFlow$1.L$1 = channel2;
                    flowLiveDataConversions$asFlow$1.L$2 = r4;
                    flowLiveDataConversions$asFlow$1.L$3 = next;
                    flowLiveDataConversions$asFlow$1.L$4 = channelIterator;
                    flowLiveDataConversions$asFlow$1.label = 3;
                    if (r7.emit(next, flowLiveDataConversions$asFlow$1) == obj3) {
                        return obj3;
                    }
                    flowLiveDataConversions$asFlow$12 = flowLiveDataConversions$asFlow$1;
                    channel = channel2;
                    flowCollector = r7;
                    obj2 = obj3;
                    channelIterator2 = channelIterator;
                    r4 = r4;
                    flowLiveDataConversions$asFlow$12.L$0 = flowCollector;
                    flowLiveDataConversions$asFlow$12.L$1 = channel;
                    flowLiveDataConversions$asFlow$12.L$2 = r4;
                    flowLiveDataConversions$asFlow$12.L$3 = channelIterator2;
                    flowLiveDataConversions$asFlow$12.label = 2;
                    hasNext = channelIterator2.hasNext(flowLiveDataConversions$asFlow$12);
                    if (hasNext == obj2) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C04432(r4, null), 2, null);
                    throw th;
                }
            }
            flowLiveDataConversions$asFlow$12.L$0 = flowCollector;
            flowLiveDataConversions$asFlow$12.L$1 = channel;
            flowLiveDataConversions$asFlow$12.L$2 = r4;
            flowLiveDataConversions$asFlow$12.L$3 = channelIterator2;
            flowLiveDataConversions$asFlow$12.label = 2;
            hasNext = channelIterator2.hasNext(flowLiveDataConversions$asFlow$12);
            if (hasNext == obj2) {
            }
        } catch (Throwable th3) {
            flowLiveDataConversions$asFlow$1 = flowLiveDataConversions$asFlow$12;
            th = th3;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain().getImmediate(), null, new C04432(r4, null), 2, null);
            throw th;
        }
        r4 = observer;
        ChannelIterator it = Channel.iterator();
        obj2 = coroutine_suspended;
        channel = Channel;
        flowLiveDataConversions$asFlow$12 = this;
        channelIterator2 = it;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: FlowLiveData.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 15})
    @DebugMetadata(m3969c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1", m3970f = "FlowLiveData.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$1 */
    /* loaded from: classes.dex */
    public static final class C04421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Observer $observer;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f110p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04421(Observer observer, Continuation continuation) {
            super(2, continuation);
            this.$observer = observer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04421 c04421 = new C04421(this.$observer, completion);
            c04421.f110p$ = (CoroutineScope) obj;
            return c04421;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f110p$;
            FlowLiveDataConversions$asFlow$1.this.$this_asFlow.observeForever(this.$observer);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: FlowLiveData.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 15})
    @DebugMetadata(m3969c = "androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2", m3970f = "FlowLiveData.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: androidx.lifecycle.FlowLiveDataConversions$asFlow$1$2 */
    /* loaded from: classes.dex */
    public static final class C04432 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Observer $observer;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f111p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04432(Observer observer, Continuation continuation) {
            super(2, continuation);
            this.$observer = observer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04432 c04432 = new C04432(this.$observer, completion);
            c04432.f111p$ = (CoroutineScope) obj;
            return c04432;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04432) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f111p$;
            FlowLiveDataConversions$asFlow$1.this.$this_asFlow.removeObserver(this.$observer);
            return Unit.INSTANCE;
        }
    }
}
