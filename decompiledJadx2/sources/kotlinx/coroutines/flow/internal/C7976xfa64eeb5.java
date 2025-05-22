package kotlinx.coroutines.flow.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Collect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1 */
/* loaded from: classes2.dex */
public final class C7976xfa64eeb5<T> implements FlowCollector<T> {
    final /* synthetic */ Ref.ObjectRef $previousFlow$inlined;
    final /* synthetic */ CoroutineScope $this_flowScope$inlined;
    final /* synthetic */ ChannelFlowTransformLatest$flowCollect$3 this$0;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\u0007"}, m3961d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1", m3970f = "Merge.kt", m3971i = {0, 0, 0, 0, 0}, m3972l = {139}, m3973m = "emit", m3974n = {"this", ES6Iterator.VALUE_PROPERTY, "continuation", ES6Iterator.VALUE_PROPERTY, "$this$apply"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$5"})
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return C7976xfa64eeb5.this.emit(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(Object obj, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        int i;
        C7976xfa64eeb5<T> c7976xfa64eeb5;
        Job launch$default;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
                Object obj2 = anonymousClass1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = anonymousClass1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj2);
                    AnonymousClass1 anonymousClass12 = anonymousClass1;
                    Job job = (Job) this.$previousFlow$inlined.element;
                    if (job != null) {
                        job.cancel((CancellationException) new ChildCancelledException());
                        anonymousClass1.L$0 = this;
                        anonymousClass1.L$1 = obj;
                        anonymousClass1.L$2 = anonymousClass12;
                        anonymousClass1.L$3 = obj;
                        anonymousClass1.L$4 = job;
                        anonymousClass1.L$5 = job;
                        anonymousClass1.label = 1;
                        if (job.join(anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    c7976xfa64eeb5 = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    obj = anonymousClass1.L$3;
                    Object obj3 = anonymousClass1.L$1;
                    c7976xfa64eeb5 = (C7976xfa64eeb5) anonymousClass1.L$0;
                    ResultKt.throwOnFailure(obj2);
                }
                Ref.ObjectRef objectRef = c7976xfa64eeb5.$previousFlow$inlined;
                launch$default = BuildersKt__Builders_commonKt.launch$default(c7976xfa64eeb5.$this_flowScope$inlined, null, CoroutineStart.UNDISPATCHED, new C7977xeca2eba3(obj, null, c7976xfa64eeb5), 1, null);
                objectRef.element = (T) launch$default;
                return Unit.INSTANCE;
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj22 = anonymousClass1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = anonymousClass1.label;
        if (i != 0) {
        }
        Ref.ObjectRef objectRef2 = c7976xfa64eeb5.$previousFlow$inlined;
        launch$default = BuildersKt__Builders_commonKt.launch$default(c7976xfa64eeb5.$this_flowScope$inlined, null, CoroutineStart.UNDISPATCHED, new C7977xeca2eba3(obj, null, c7976xfa64eeb5), 1, null);
        objectRef2.element = (T) launch$default;
        return Unit.INSTANCE;
    }

    public C7976xfa64eeb5(ChannelFlowTransformLatest$flowCollect$3 channelFlowTransformLatest$flowCollect$3, CoroutineScope coroutineScope, Ref.ObjectRef objectRef) {
        this.this$0 = channelFlowTransformLatest$flowCollect$3;
        this.$this_flowScope$inlined = coroutineScope;
        this.$previousFlow$inlined = objectRef;
    }
}
