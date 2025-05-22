package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [V] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Channels.common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "R", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$zip$2", m3970f = "Channels.common.kt", m3971i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, m3972l = {2201, 2191, 2193}, m3973m = "invokeSuspend", m3974n = {"$this$produce", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "$this$produce", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "element1", "$this$produce", "otherIterator", "$this$consumeEach$iv", "$this$consume$iv$iv", "cause$iv$iv", "$this$consume$iv", "e$iv", "element1", "element2"}, m3975s = {"L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$0", "L$1", "L$2", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10"})
/* loaded from: classes2.dex */
public final class ChannelsKt__Channels_commonKt$zip$2<V> extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $other;
    final /* synthetic */ ReceiveChannel $this_zip;
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;

    /* renamed from: p$ */
    private ProducerScope f8818p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$zip$2(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_zip = receiveChannel;
        this.$other = receiveChannel2;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$zip$2 channelsKt__Channels_commonKt$zip$2 = new ChannelsKt__Channels_commonKt$zip$2(this.$this_zip, this.$other, this.$transform, continuation);
        channelsKt__Channels_commonKt$zip$2.f8818p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$zip$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$zip$2) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00d6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00df A[Catch: all -> 0x014c, TryCatch #1 {all -> 0x014c, blocks: (B:8:0x0033, B:11:0x00be, B:15:0x00d7, B:17:0x00df, B:21:0x0105, B:24:0x0113, B:28:0x0144, B:48:0x009c, B:51:0x00b3), top: B:2:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0113 A[Catch: all -> 0x014c, TryCatch #1 {all -> 0x014c, blocks: (B:8:0x0033, B:11:0x00be, B:15:0x00d7, B:17:0x00df, B:21:0x0105, B:24:0x0113, B:28:0x0144, B:48:0x009c, B:51:0x00b3), top: B:2:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0144 A[Catch: all -> 0x014c, TRY_LEAVE, TryCatch #1 {all -> 0x014c, blocks: (B:8:0x0033, B:11:0x00be, B:15:0x00d7, B:17:0x00df, B:21:0x0105, B:24:0x0113, B:28:0x0144, B:48:0x009c, B:51:0x00b3), top: B:2:0x000b }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x010d -> B:11:0x00be). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x013c -> B:10:0x0140). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Throwable th;
        ReceiveChannel receiveChannel;
        Throwable th2;
        ChannelsKt__Channels_commonKt$zip$2<V> channelsKt__Channels_commonKt$zip$2;
        ProducerScope producerScope;
        ChannelIterator channelIterator;
        ReceiveChannel receiveChannel2;
        ReceiveChannel receiveChannel3;
        ChannelIterator it;
        ChannelsKt__Channels_commonKt$zip$2<V> channelsKt__Channels_commonKt$zip$22;
        Object obj2;
        Object obj3;
        ChannelIterator channelIterator2;
        ChannelsKt__Channels_commonKt$zip$2<V> channelsKt__Channels_commonKt$zip$23;
        Throwable th3;
        Object obj4;
        ChannelsKt__Channels_commonKt$zip$2<V> channelsKt__Channels_commonKt$zip$24;
        Object obj5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        int i2 = 2;
        int i3 = 1;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope2 = this.f8818p$;
                ChannelIterator it2 = this.$other.iterator();
                receiveChannel = this.$this_zip;
                th2 = (Throwable) null;
                channelsKt__Channels_commonKt$zip$2 = this;
                producerScope = producerScope2;
                channelIterator = it2;
                receiveChannel2 = receiveChannel;
                receiveChannel3 = receiveChannel2;
                it = receiveChannel.iterator();
                channelsKt__Channels_commonKt$zip$22 = channelsKt__Channels_commonKt$zip$2;
                channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                channelsKt__Channels_commonKt$zip$2.L$5 = th2;
                channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                channelsKt__Channels_commonKt$zip$2.L$7 = it;
                channelsKt__Channels_commonKt$zip$2.label = i3;
                obj5 = it.hasNext(channelsKt__Channels_commonKt$zip$22);
                if (obj5 == coroutine_suspended) {
                }
                if (((Boolean) obj5).booleanValue()) {
                }
            } else if (i == 1) {
                it = (ChannelIterator) this.L$7;
                receiveChannel2 = (ReceiveChannel) this.L$6;
                th2 = (Throwable) this.L$5;
                receiveChannel = (ReceiveChannel) this.L$4;
                channelsKt__Channels_commonKt$zip$22 = (ChannelsKt__Channels_commonKt$zip$2) this.L$3;
                receiveChannel3 = (ReceiveChannel) this.L$2;
                channelIterator = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj5 = obj;
                channelsKt__Channels_commonKt$zip$2 = this;
                if (((Boolean) obj5).booleanValue()) {
                }
            } else if (i == 2) {
                Object obj6 = this.L$9;
                Object obj7 = this.L$8;
                ChannelIterator channelIterator3 = (ChannelIterator) this.L$7;
                ReceiveChannel receiveChannel4 = (ReceiveChannel) this.L$6;
                th3 = (Throwable) this.L$5;
                ReceiveChannel receiveChannel5 = (ReceiveChannel) this.L$4;
                channelsKt__Channels_commonKt$zip$23 = (ChannelsKt__Channels_commonKt$zip$2) this.L$3;
                ReceiveChannel receiveChannel6 = (ReceiveChannel) this.L$2;
                channelIterator2 = (ChannelIterator) this.L$1;
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    obj2 = obj;
                    channelsKt__Channels_commonKt$zip$24 = this;
                    obj3 = obj6;
                    it = channelIterator3;
                    obj4 = obj7;
                    receiveChannel2 = receiveChannel4;
                    receiveChannel = receiveChannel5;
                    receiveChannel3 = receiveChannel6;
                    producerScope = producerScope3;
                    if (((Boolean) obj2).booleanValue()) {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    receiveChannel = receiveChannel5;
                    try {
                        throw th;
                    } catch (Throwable th5) {
                        ChannelsKt.cancelConsumed(receiveChannel, th);
                        throw th5;
                    }
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (ChannelIterator) this.L$7;
                receiveChannel2 = (ReceiveChannel) this.L$6;
                th2 = (Throwable) this.L$5;
                receiveChannel = (ReceiveChannel) this.L$4;
                channelsKt__Channels_commonKt$zip$22 = (ChannelsKt__Channels_commonKt$zip$2) this.L$3;
                receiveChannel3 = (ReceiveChannel) this.L$2;
                channelIterator = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                channelsKt__Channels_commonKt$zip$2 = this;
                i2 = 2;
                i3 = 1;
                channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                channelsKt__Channels_commonKt$zip$2.L$5 = th2;
                channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                channelsKt__Channels_commonKt$zip$2.L$7 = it;
                channelsKt__Channels_commonKt$zip$2.label = i3;
                obj5 = it.hasNext(channelsKt__Channels_commonKt$zip$22);
                if (obj5 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                if (((Boolean) obj5).booleanValue()) {
                    obj3 = it.next();
                    channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                    channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                    channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                    channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                    channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                    channelsKt__Channels_commonKt$zip$2.L$5 = th2;
                    channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                    channelsKt__Channels_commonKt$zip$2.L$7 = it;
                    channelsKt__Channels_commonKt$zip$2.L$8 = obj3;
                    channelsKt__Channels_commonKt$zip$2.L$9 = obj3;
                    channelsKt__Channels_commonKt$zip$2.label = i2;
                    obj2 = channelIterator.hasNext(channelsKt__Channels_commonKt$zip$2);
                    if (obj2 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    channelsKt__Channels_commonKt$zip$24 = channelsKt__Channels_commonKt$zip$2;
                    channelIterator2 = channelIterator;
                    channelsKt__Channels_commonKt$zip$23 = channelsKt__Channels_commonKt$zip$22;
                    th3 = th2;
                    obj4 = obj3;
                    if (((Boolean) obj2).booleanValue()) {
                        th2 = th3;
                        channelsKt__Channels_commonKt$zip$22 = channelsKt__Channels_commonKt$zip$23;
                        channelIterator = channelIterator2;
                        channelsKt__Channels_commonKt$zip$2 = channelsKt__Channels_commonKt$zip$24;
                        i2 = 2;
                        channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                        channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                        channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                        channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                        channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                        channelsKt__Channels_commonKt$zip$2.L$5 = th2;
                        channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                        channelsKt__Channels_commonKt$zip$2.L$7 = it;
                        channelsKt__Channels_commonKt$zip$2.label = i3;
                        obj5 = it.hasNext(channelsKt__Channels_commonKt$zip$22);
                        if (obj5 == coroutine_suspended) {
                        }
                        if (((Boolean) obj5).booleanValue()) {
                            Unit unit = Unit.INSTANCE;
                            ChannelsKt.cancelConsumed(receiveChannel, th2);
                            return Unit.INSTANCE;
                        }
                    } else {
                        Object next = channelIterator2.next();
                        Object invoke = channelsKt__Channels_commonKt$zip$24.$transform.invoke(obj3, next);
                        channelsKt__Channels_commonKt$zip$24.L$0 = producerScope;
                        channelsKt__Channels_commonKt$zip$24.L$1 = channelIterator2;
                        channelsKt__Channels_commonKt$zip$24.L$2 = receiveChannel3;
                        channelsKt__Channels_commonKt$zip$24.L$3 = channelsKt__Channels_commonKt$zip$23;
                        channelsKt__Channels_commonKt$zip$24.L$4 = receiveChannel;
                        channelsKt__Channels_commonKt$zip$24.L$5 = th3;
                        channelsKt__Channels_commonKt$zip$24.L$6 = receiveChannel2;
                        channelsKt__Channels_commonKt$zip$24.L$7 = it;
                        channelsKt__Channels_commonKt$zip$24.L$8 = obj4;
                        channelsKt__Channels_commonKt$zip$24.L$9 = obj3;
                        channelsKt__Channels_commonKt$zip$24.L$10 = next;
                        channelsKt__Channels_commonKt$zip$24.label = 3;
                        if (producerScope.send(invoke, channelsKt__Channels_commonKt$zip$24) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        th2 = th3;
                        channelsKt__Channels_commonKt$zip$22 = channelsKt__Channels_commonKt$zip$23;
                        channelIterator = channelIterator2;
                        channelsKt__Channels_commonKt$zip$2 = channelsKt__Channels_commonKt$zip$24;
                        i2 = 2;
                        i3 = 1;
                        channelsKt__Channels_commonKt$zip$2.L$0 = producerScope;
                        channelsKt__Channels_commonKt$zip$2.L$1 = channelIterator;
                        channelsKt__Channels_commonKt$zip$2.L$2 = receiveChannel3;
                        channelsKt__Channels_commonKt$zip$2.L$3 = channelsKt__Channels_commonKt$zip$22;
                        channelsKt__Channels_commonKt$zip$2.L$4 = receiveChannel;
                        channelsKt__Channels_commonKt$zip$2.L$5 = th2;
                        channelsKt__Channels_commonKt$zip$2.L$6 = receiveChannel2;
                        channelsKt__Channels_commonKt$zip$2.L$7 = it;
                        channelsKt__Channels_commonKt$zip$2.label = i3;
                        obj5 = it.hasNext(channelsKt__Channels_commonKt$zip$22);
                        if (obj5 == coroutine_suspended) {
                        }
                        if (((Boolean) obj5).booleanValue()) {
                        }
                    }
                }
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }
}
