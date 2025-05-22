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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [E] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Channels.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$take$1", m3970f = "Channels.common.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {994, 995}, m3973m = "invokeSuspend", m3974n = {"$this$produce", "remaining", "$this$produce", "remaining", C3898x.f4338g}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$1"})
/* loaded from: classes2.dex */
public final class ChannelsKt__Channels_commonKt$take$1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {

    /* renamed from: $n */
    final /* synthetic */ int f8814$n;
    final /* synthetic */ ReceiveChannel $this_take;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f8815p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$take$1(ReceiveChannel receiveChannel, int i, Continuation continuation) {
        super(2, continuation);
        this.$this_take = receiveChannel;
        this.f8814$n = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$take$1 channelsKt__Channels_commonKt$take$1 = new ChannelsKt__Channels_commonKt$take$1(this.$this_take, this.f8814$n, continuation);
        channelsKt__Channels_commonKt$take$1.f8815p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$take$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$take$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x008b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0085 -> B:6:0x0087). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope;
        int i;
        ChannelIterator<E> it;
        ChannelsKt__Channels_commonKt$take$1<E> channelsKt__Channels_commonKt$take$1;
        ChannelsKt__Channels_commonKt$take$1<E> channelsKt__Channels_commonKt$take$12;
        ProducerScope producerScope2;
        int i2;
        ChannelIterator<E> channelIterator;
        Object hasNext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = this.f8815p$;
            int i4 = this.f8814$n;
            if (i4 == 0) {
                return Unit.INSTANCE;
            }
            if (!(i4 >= 0)) {
                throw new IllegalArgumentException(("Requested element count " + this.f8814$n + " is less than zero.").toString());
            }
            i = this.f8814$n;
            it = this.$this_take.iterator();
            channelsKt__Channels_commonKt$take$1 = this;
            channelsKt__Channels_commonKt$take$1.L$0 = producerScope;
            channelsKt__Channels_commonKt$take$1.I$0 = i;
            channelsKt__Channels_commonKt$take$1.L$1 = it;
            channelsKt__Channels_commonKt$take$1.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$take$1);
            if (hasNext != coroutine_suspended) {
            }
        } else if (i3 == 1) {
            channelIterator = (ChannelIterator) this.L$1;
            i2 = this.I$0;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__Channels_commonKt$take$12 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else {
            if (i3 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            channelIterator = (ChannelIterator) this.L$2;
            i2 = this.I$0;
            ProducerScope producerScope3 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope = producerScope3;
            channelsKt__Channels_commonKt$take$1 = this;
            int i5 = i2 - 1;
            if (i5 != 0) {
                return Unit.INSTANCE;
            }
            it = channelIterator;
            i = i5;
            channelsKt__Channels_commonKt$take$1.L$0 = producerScope;
            channelsKt__Channels_commonKt$take$1.I$0 = i;
            channelsKt__Channels_commonKt$take$1.L$1 = it;
            channelsKt__Channels_commonKt$take$1.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$take$1);
            if (hasNext != coroutine_suspended) {
                return coroutine_suspended;
            }
            ChannelsKt__Channels_commonKt$take$1<E> channelsKt__Channels_commonKt$take$13 = channelsKt__Channels_commonKt$take$1;
            producerScope2 = producerScope;
            obj = hasNext;
            channelsKt__Channels_commonKt$take$12 = channelsKt__Channels_commonKt$take$13;
            ChannelIterator<E> channelIterator2 = it;
            i2 = i;
            channelIterator = channelIterator2;
            if (!((Boolean) obj).booleanValue()) {
                E next = channelIterator.next();
                channelsKt__Channels_commonKt$take$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$take$12.I$0 = i2;
                channelsKt__Channels_commonKt$take$12.L$1 = next;
                channelsKt__Channels_commonKt$take$12.L$2 = channelIterator;
                channelsKt__Channels_commonKt$take$12.label = 2;
                if (producerScope2.send(next, channelsKt__Channels_commonKt$take$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                producerScope = producerScope2;
                channelsKt__Channels_commonKt$take$1 = channelsKt__Channels_commonKt$take$12;
                int i52 = i2 - 1;
                if (i52 != 0) {
                }
            } else {
                return Unit.INSTANCE;
            }
        }
    }
}
