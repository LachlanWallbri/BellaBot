package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Channels.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "R", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$mapIndexed$1", m3970f = "Channels.common.kt", m3971i = {0, 0, 1, 1, 1, 2, 2, 2}, m3972l = {1423, 1424, 1424}, m3973m = "invokeSuspend", m3974n = {"$this$produce", "index", "$this$produce", "index", C3898x.f4338g, "$this$produce", "index", C3898x.f4338g}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$1", "L$0", "I$0", "L$1"})
/* loaded from: classes2.dex */
public final class ChannelsKt__Channels_commonKt$mapIndexed$1<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_mapIndexed;
    final /* synthetic */ Function3 $transform;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private ProducerScope f8813p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$mapIndexed$1(ReceiveChannel receiveChannel, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_mapIndexed = receiveChannel;
        this.$transform = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$mapIndexed$1 channelsKt__Channels_commonKt$mapIndexed$1 = new ChannelsKt__Channels_commonKt$mapIndexed$1(this.$this_mapIndexed, this.$transform, continuation);
        channelsKt__Channels_commonKt$mapIndexed$1.f8813p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$mapIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$mapIndexed$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00b1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b7  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00b2 -> B:7:0x0061). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope;
        ChannelsKt__Channels_commonKt$mapIndexed$1<R> channelsKt__Channels_commonKt$mapIndexed$1;
        int i;
        ChannelIterator it;
        ChannelsKt__Channels_commonKt$mapIndexed$1<R> channelsKt__Channels_commonKt$mapIndexed$12;
        Object obj2;
        int i2;
        ProducerScope producerScope2;
        ChannelIterator channelIterator;
        ProducerScope producerScope3;
        ChannelsKt__Channels_commonKt$mapIndexed$1<R> channelsKt__Channels_commonKt$mapIndexed$13;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = this.f8813p$;
            channelsKt__Channels_commonKt$mapIndexed$1 = this;
            i = 0;
            it = this.$this_mapIndexed.iterator();
        } else if (i3 == 1) {
            it = (ChannelIterator) this.L$1;
            i = this.I$0;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__Channels_commonKt$mapIndexed$13 = this;
            if (!((Boolean) obj).booleanValue()) {
                Object next = it.next();
                Function3 function3 = channelsKt__Channels_commonKt$mapIndexed$13.$transform;
                Integer boxInt = Boxing.boxInt(i);
                int i4 = i + 1;
                channelsKt__Channels_commonKt$mapIndexed$13.L$0 = producerScope2;
                channelsKt__Channels_commonKt$mapIndexed$13.I$0 = i4;
                channelsKt__Channels_commonKt$mapIndexed$13.L$1 = next;
                channelsKt__Channels_commonKt$mapIndexed$13.L$2 = it;
                channelsKt__Channels_commonKt$mapIndexed$13.L$3 = producerScope2;
                channelsKt__Channels_commonKt$mapIndexed$13.label = 2;
                Object invoke = function3.invoke(boxInt, next, channelsKt__Channels_commonKt$mapIndexed$13);
                if (invoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                channelsKt__Channels_commonKt$mapIndexed$12 = channelsKt__Channels_commonKt$mapIndexed$13;
                i2 = i4;
                channelIterator = it;
                producerScope3 = producerScope2;
                obj2 = next;
                obj = invoke;
                channelsKt__Channels_commonKt$mapIndexed$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$mapIndexed$12.I$0 = i2;
                channelsKt__Channels_commonKt$mapIndexed$12.L$1 = obj2;
                channelsKt__Channels_commonKt$mapIndexed$12.L$2 = channelIterator;
                channelsKt__Channels_commonKt$mapIndexed$12.label = 3;
                if (producerScope3.send(obj, channelsKt__Channels_commonKt$mapIndexed$12) != coroutine_suspended) {
                }
            } else {
                return Unit.INSTANCE;
            }
        } else if (i3 == 2) {
            producerScope3 = (ProducerScope) this.L$3;
            channelIterator = (ChannelIterator) this.L$2;
            Object obj3 = this.L$1;
            i2 = this.I$0;
            ProducerScope producerScope4 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__Channels_commonKt$mapIndexed$12 = this;
            obj2 = obj3;
            producerScope2 = producerScope4;
            channelsKt__Channels_commonKt$mapIndexed$12.L$0 = producerScope2;
            channelsKt__Channels_commonKt$mapIndexed$12.I$0 = i2;
            channelsKt__Channels_commonKt$mapIndexed$12.L$1 = obj2;
            channelsKt__Channels_commonKt$mapIndexed$12.L$2 = channelIterator;
            channelsKt__Channels_commonKt$mapIndexed$12.label = 3;
            if (producerScope3.send(obj, channelsKt__Channels_commonKt$mapIndexed$12) != coroutine_suspended) {
                return coroutine_suspended;
            }
            it = channelIterator;
            producerScope = producerScope2;
            i = i2;
            channelsKt__Channels_commonKt$mapIndexed$1 = channelsKt__Channels_commonKt$mapIndexed$12;
        } else {
            if (i3 != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) this.L$2;
            i = this.I$0;
            ProducerScope producerScope5 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope = producerScope5;
            channelsKt__Channels_commonKt$mapIndexed$1 = this;
        }
        channelsKt__Channels_commonKt$mapIndexed$1.L$0 = producerScope;
        channelsKt__Channels_commonKt$mapIndexed$1.I$0 = i;
        channelsKt__Channels_commonKt$mapIndexed$1.L$1 = it;
        channelsKt__Channels_commonKt$mapIndexed$1.label = 1;
        Object hasNext = it.hasNext(channelsKt__Channels_commonKt$mapIndexed$1);
        if (hasNext == coroutine_suspended) {
            return coroutine_suspended;
        }
        ChannelsKt__Channels_commonKt$mapIndexed$1<R> channelsKt__Channels_commonKt$mapIndexed$14 = channelsKt__Channels_commonKt$mapIndexed$1;
        producerScope2 = producerScope;
        obj = hasNext;
        channelsKt__Channels_commonKt$mapIndexed$13 = channelsKt__Channels_commonKt$mapIndexed$14;
        if (!((Boolean) obj).booleanValue()) {
        }
    }
}
