package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Broadcast.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.channels.BroadcastKt$broadcast$1", m3970f = "Broadcast.kt", m3971i = {0, 1, 1}, m3972l = {30, 31}, m3973m = "invokeSuspend", m3974n = {"$this$broadcast", "$this$broadcast", C3898x.f4338g}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes2.dex */
final class BroadcastKt$broadcast$1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_broadcast;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f8803p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BroadcastKt$broadcast$1(ReceiveChannel receiveChannel, Continuation continuation) {
        super(2, continuation);
        this.$this_broadcast = receiveChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BroadcastKt$broadcast$1 broadcastKt$broadcast$1 = new BroadcastKt$broadcast$1(this.$this_broadcast, continuation);
        broadcastKt$broadcast$1.f8803p$ = (ProducerScope) obj;
        return broadcastKt$broadcast$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((BroadcastKt$broadcast$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0048 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0068 -> B:7:0x003c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope;
        ChannelIterator<E> it;
        BroadcastKt$broadcast$1<E> broadcastKt$broadcast$1;
        Object hasNext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = this.f8803p$;
            it = this.$this_broadcast.iterator();
        } else if (i == 1) {
            it = (ChannelIterator) this.L$1;
            ProducerScope producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            BroadcastKt$broadcast$1<E> broadcastKt$broadcast$12 = this;
            if (!((Boolean) obj).booleanValue()) {
                E next = it.next();
                broadcastKt$broadcast$12.L$0 = producerScope2;
                broadcastKt$broadcast$12.L$1 = next;
                broadcastKt$broadcast$12.L$2 = it;
                broadcastKt$broadcast$12.label = 2;
                if (producerScope2.send(next, broadcastKt$broadcast$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                producerScope = producerScope2;
                broadcastKt$broadcast$1 = broadcastKt$broadcast$12;
                broadcastKt$broadcast$1.L$0 = producerScope;
                broadcastKt$broadcast$1.L$1 = it;
                broadcastKt$broadcast$1.label = 1;
                hasNext = it.hasNext(broadcastKt$broadcast$1);
                if (hasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                BroadcastKt$broadcast$1<E> broadcastKt$broadcast$13 = broadcastKt$broadcast$1;
                producerScope2 = producerScope;
                obj = hasNext;
                broadcastKt$broadcast$12 = broadcastKt$broadcast$13;
                if (!((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) this.L$2;
            ProducerScope producerScope3 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope = producerScope3;
        }
        broadcastKt$broadcast$1 = this;
        broadcastKt$broadcast$1.L$0 = producerScope;
        broadcastKt$broadcast$1.L$1 = it;
        broadcastKt$broadcast$1.label = 1;
        hasNext = it.hasNext(broadcastKt$broadcast$1);
        if (hasNext != coroutine_suspended) {
        }
    }
}
