package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Delay.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/channels/ProducerScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1", m3970f = "Delay.kt", m3971i = {0, 0}, m3972l = {137}, m3973m = "invokeSuspend", m3974n = {"$this$produce", "$this$collect$iv"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes2.dex */
final class FlowKt__DelayKt$sample$2$values$1 extends SuspendLambda implements Function2<ProducerScope<? super Object>, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private ProducerScope f8827p$;
    final /* synthetic */ FlowKt__DelayKt$sample$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__DelayKt$sample$2$values$1(FlowKt__DelayKt$sample$2 flowKt__DelayKt$sample$2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = flowKt__DelayKt$sample$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowKt__DelayKt$sample$2$values$1 flowKt__DelayKt$sample$2$values$1 = new FlowKt__DelayKt$sample$2$values$1(this.this$0, continuation);
        flowKt__DelayKt$sample$2$values$1.f8827p$ = (ProducerScope) obj;
        return flowKt__DelayKt$sample$2$values$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super Object> producerScope, Continuation<? super Unit> continuation) {
        return ((FlowKt__DelayKt$sample$2$values$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = this.f8827p$;
            Flow flow = this.this$0.$this_sample;
            FlowCollector flowCollector = new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.FlowKt__DelayKt$sample$2$values$1$invokeSuspend$$inlined$collect$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public Object emit(Object obj2, Continuation continuation) {
                    ProducerScope producerScope2 = ProducerScope.this;
                    if (obj2 == null) {
                        obj2 = NullSurrogateKt.NULL;
                    }
                    Object send = producerScope2.send(obj2, continuation);
                    return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
                }
            };
            this.L$0 = producerScope;
            this.L$1 = flow;
            this.label = 1;
            if (flow.collect(flowCollector, this) == coroutine_suspended) {
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
