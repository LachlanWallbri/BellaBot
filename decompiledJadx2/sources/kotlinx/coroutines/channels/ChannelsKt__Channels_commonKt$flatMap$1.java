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
/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Channels.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$flatMap$1", m3970f = "Channels.common.kt", m3971i = {0, 1, 1, 2, 2}, m3972l = {1291, 1292, 1292}, m3973m = "invokeSuspend", m3974n = {"$this$produce", "$this$produce", C3898x.f4338g, "$this$produce", C3898x.f4338g}, m3975s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes2.dex */
public final class ChannelsKt__Channels_commonKt$flatMap$1<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_flatMap;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f8811p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$flatMap$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_flatMap = receiveChannel;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$flatMap$1 channelsKt__Channels_commonKt$flatMap$1 = new ChannelsKt__Channels_commonKt$flatMap$1(this.$this_flatMap, this.$transform, continuation);
        channelsKt__Channels_commonKt$flatMap$1.f8811p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$flatMap$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$flatMap$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x009b -> B:8:0x0054). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ChannelIterator it;
        ProducerScope producerScope;
        ProducerScope producerScope2;
        ChannelIterator channelIterator;
        Object obj2;
        ChannelsKt__Channels_commonKt$flatMap$1<R> channelsKt__Channels_commonKt$flatMap$1;
        ChannelsKt__Channels_commonKt$flatMap$1<R> channelsKt__Channels_commonKt$flatMap$12;
        Object hasNext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope3 = this.f8811p$;
            it = this.$this_flatMap.iterator();
            producerScope = producerScope3;
        } else if (i == 1) {
            ChannelIterator channelIterator2 = (ChannelIterator) this.L$1;
            ProducerScope producerScope4 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope2 = producerScope4;
            channelIterator = channelIterator2;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$flatMap$1 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else if (i == 2) {
            ChannelIterator channelIterator3 = (ChannelIterator) this.L$2;
            Object obj3 = this.L$1;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            Object obj4 = obj3;
            channelIterator = channelIterator3;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$flatMap$1 = this;
            channelsKt__Channels_commonKt$flatMap$1.L$0 = producerScope2;
            channelsKt__Channels_commonKt$flatMap$1.L$1 = obj4;
            channelsKt__Channels_commonKt$flatMap$1.L$2 = channelIterator;
            channelsKt__Channels_commonKt$flatMap$1.label = 3;
            if (ChannelsKt.toChannel((ReceiveChannel) obj, producerScope2, channelsKt__Channels_commonKt$flatMap$1) != obj2) {
                return obj2;
            }
            channelsKt__Channels_commonKt$flatMap$12 = channelsKt__Channels_commonKt$flatMap$1;
            coroutine_suspended = obj2;
            it = channelIterator;
            producerScope = producerScope2;
            channelsKt__Channels_commonKt$flatMap$12.L$0 = producerScope;
            channelsKt__Channels_commonKt$flatMap$12.L$1 = it;
            channelsKt__Channels_commonKt$flatMap$12.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$flatMap$12);
            if (hasNext != coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj5 = coroutine_suspended;
            channelsKt__Channels_commonKt$flatMap$1 = channelsKt__Channels_commonKt$flatMap$12;
            obj = hasNext;
            producerScope2 = producerScope;
            channelIterator = it;
            obj2 = obj5;
            if (!((Boolean) obj).booleanValue()) {
                Object next = channelIterator.next();
                Function2 function2 = channelsKt__Channels_commonKt$flatMap$1.$transform;
                channelsKt__Channels_commonKt$flatMap$1.L$0 = producerScope2;
                channelsKt__Channels_commonKt$flatMap$1.L$1 = next;
                channelsKt__Channels_commonKt$flatMap$1.L$2 = channelIterator;
                channelsKt__Channels_commonKt$flatMap$1.label = 2;
                Object invoke = function2.invoke(next, channelsKt__Channels_commonKt$flatMap$1);
                if (invoke == obj2) {
                    return obj2;
                }
                obj4 = next;
                obj = invoke;
                channelsKt__Channels_commonKt$flatMap$1.L$0 = producerScope2;
                channelsKt__Channels_commonKt$flatMap$1.L$1 = obj4;
                channelsKt__Channels_commonKt$flatMap$1.L$2 = channelIterator;
                channelsKt__Channels_commonKt$flatMap$1.label = 3;
                if (ChannelsKt.toChannel((ReceiveChannel) obj, producerScope2, channelsKt__Channels_commonKt$flatMap$1) != obj2) {
                }
            } else {
                return Unit.INSTANCE;
            }
        } else {
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) this.L$2;
            producerScope = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        channelsKt__Channels_commonKt$flatMap$12 = this;
        channelsKt__Channels_commonKt$flatMap$12.L$0 = producerScope;
        channelsKt__Channels_commonKt$flatMap$12.L$1 = it;
        channelsKt__Channels_commonKt$flatMap$12.label = 1;
        hasNext = it.hasNext(channelsKt__Channels_commonKt$flatMap$12);
        if (hasNext != coroutine_suspended) {
        }
    }
}
