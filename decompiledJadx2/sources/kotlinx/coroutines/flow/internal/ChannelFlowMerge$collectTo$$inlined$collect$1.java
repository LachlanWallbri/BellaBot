package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.sync.Semaphore;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Collect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ChannelFlowMerge$collectTo$$inlined$collect$1<T> implements FlowCollector<Flow<? extends T>> {
    final /* synthetic */ SendingCollector $collector$inlined;
    final /* synthetic */ Job $job$inlined;
    final /* synthetic */ ProducerScope $scope$inlined;
    final /* synthetic */ Semaphore $semaphore$inlined;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\u0007"}, m3961d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$$inlined$collect$1", m3970f = "Merge.kt", m3971i = {0, 0, 0, 0}, m3972l = {138}, m3973m = "emit", m3974n = {"this", ES6Iterator.VALUE_PROPERTY, "continuation", "inner"}, m3975s = {"L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$$inlined$collect$1$1 */
    /* loaded from: classes2.dex */
    public static final class C79751 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C79751(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelFlowMerge$collectTo$$inlined$collect$1.this.emit(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(Object obj, Continuation continuation) {
        C79751 c79751;
        int i;
        ChannelFlowMerge$collectTo$$inlined$collect$1<T> channelFlowMerge$collectTo$$inlined$collect$1;
        Flow flow;
        if (continuation instanceof C79751) {
            c79751 = (C79751) continuation;
            if ((c79751.label & Integer.MIN_VALUE) != 0) {
                c79751.label -= Integer.MIN_VALUE;
                Object obj2 = c79751.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c79751.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    C79751 c797512 = c79751;
                    Flow flow2 = (Flow) obj;
                    Job job = this.$job$inlined;
                    if (job != null) {
                        JobKt.ensureActive(job);
                    }
                    Semaphore semaphore = this.$semaphore$inlined;
                    c79751.L$0 = this;
                    c79751.L$1 = obj;
                    c79751.L$2 = c797512;
                    c79751.L$3 = flow2;
                    c79751.label = 1;
                    if (semaphore.acquire(c79751) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    channelFlowMerge$collectTo$$inlined$collect$1 = this;
                    flow = flow2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    flow = (Flow) c79751.L$3;
                    Object obj3 = c79751.L$1;
                    channelFlowMerge$collectTo$$inlined$collect$1 = (ChannelFlowMerge$collectTo$$inlined$collect$1) c79751.L$0;
                    ResultKt.throwOnFailure(obj2);
                }
                BuildersKt__Builders_commonKt.launch$default(channelFlowMerge$collectTo$$inlined$collect$1.$scope$inlined, null, null, new ChannelFlowMerge$collectTo$$inlined$collect$1$lambda$1(flow, null, channelFlowMerge$collectTo$$inlined$collect$1), 3, null);
                return Unit.INSTANCE;
            }
        }
        c79751 = new C79751(continuation);
        Object obj22 = c79751.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c79751.label;
        if (i != 0) {
        }
        BuildersKt__Builders_commonKt.launch$default(channelFlowMerge$collectTo$$inlined$collect$1.$scope$inlined, null, null, new ChannelFlowMerge$collectTo$$inlined$collect$1$lambda$1(flow, null, channelFlowMerge$collectTo$$inlined$collect$1), 3, null);
        return Unit.INSTANCE;
    }

    public ChannelFlowMerge$collectTo$$inlined$collect$1(Job job, Semaphore semaphore, ProducerScope producerScope, SendingCollector sendingCollector) {
        this.$job$inlined = job;
        this.$semaphore$inlined = semaphore;
        this.$scope$inlined = producerScope;
        this.$collector$inlined = sendingCollector;
    }
}
