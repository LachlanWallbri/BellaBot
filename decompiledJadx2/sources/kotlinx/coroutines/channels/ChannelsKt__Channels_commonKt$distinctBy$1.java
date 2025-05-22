package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
import java.util.HashSet;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "K", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$distinctBy$1", m3970f = "Channels.common.kt", m3971i = {0, 0, 1, 1, 1, 2, 2, 2, 2}, m3972l = {1700, 1701, 1703}, m3973m = "invokeSuspend", m3974n = {"$this$produce", "keys", "$this$produce", "keys", C3898x.f4338g, "$this$produce", "keys", C3898x.f4338g, "k"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$4"})
/* loaded from: classes2.dex */
public final class ChannelsKt__Channels_commonKt$distinctBy$1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $selector;
    final /* synthetic */ ReceiveChannel $this_distinctBy;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private ProducerScope f8805p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$distinctBy$1(ReceiveChannel receiveChannel, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_distinctBy = receiveChannel;
        this.$selector = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$distinctBy$1 channelsKt__Channels_commonKt$distinctBy$1 = new ChannelsKt__Channels_commonKt$distinctBy$1(this.$this_distinctBy, this.$selector, continuation);
        channelsKt__Channels_commonKt$distinctBy$1.f8805p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$distinctBy$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$distinctBy$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0080 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d9  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00c5 -> B:7:0x00c7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00d3 -> B:8:0x0072). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope;
        ChannelsKt__Channels_commonKt$distinctBy$1<E> channelsKt__Channels_commonKt$distinctBy$1;
        HashSet hashSet;
        ChannelIterator<E> it;
        ProducerScope producerScope2;
        HashSet hashSet2;
        E e;
        ChannelIterator<E> channelIterator;
        Object obj2;
        ChannelsKt__Channels_commonKt$distinctBy$1<E> channelsKt__Channels_commonKt$distinctBy$12;
        ProducerScope producerScope3;
        HashSet hashSet3;
        Object hasNext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope4 = this.f8805p$;
            HashSet hashSet4 = new HashSet();
            producerScope = producerScope4;
            channelsKt__Channels_commonKt$distinctBy$1 = this;
            hashSet = hashSet4;
            it = this.$this_distinctBy.iterator();
            channelsKt__Channels_commonKt$distinctBy$1.L$0 = producerScope;
            channelsKt__Channels_commonKt$distinctBy$1.L$1 = hashSet;
            channelsKt__Channels_commonKt$distinctBy$1.L$2 = it;
            channelsKt__Channels_commonKt$distinctBy$1.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$distinctBy$1);
            if (hasNext != coroutine_suspended) {
            }
        } else if (i == 1) {
            ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$2;
            HashSet hashSet5 = (HashSet) this.L$1;
            ProducerScope producerScope5 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope3 = producerScope5;
            hashSet3 = hashSet5;
            channelIterator = channelIterator2;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$distinctBy$12 = this;
            if (!((Boolean) obj).booleanValue()) {
            }
        } else if (i == 2) {
            ChannelIterator<E> channelIterator3 = (ChannelIterator) this.L$3;
            Object obj3 = this.L$2;
            HashSet hashSet6 = (HashSet) this.L$1;
            ProducerScope producerScope6 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope2 = producerScope6;
            hashSet2 = hashSet6;
            e = obj3;
            channelIterator = channelIterator3;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$distinctBy$12 = this;
            if (hashSet2.contains(obj)) {
            }
        } else if (i == 3) {
            Object obj4 = this.L$4;
            channelIterator = (ChannelIterator) this.L$3;
            HashSet hashSet7 = (HashSet) this.L$1;
            ProducerScope producerScope7 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj = obj4;
            obj2 = coroutine_suspended;
            channelsKt__Channels_commonKt$distinctBy$12 = this;
            hashSet7.add(obj);
            channelsKt__Channels_commonKt$distinctBy$1 = channelsKt__Channels_commonKt$distinctBy$12;
            coroutine_suspended = obj2;
            it = channelIterator;
            hashSet = hashSet7;
            producerScope = producerScope7;
            channelsKt__Channels_commonKt$distinctBy$1.L$0 = producerScope;
            channelsKt__Channels_commonKt$distinctBy$1.L$1 = hashSet;
            channelsKt__Channels_commonKt$distinctBy$1.L$2 = it;
            channelsKt__Channels_commonKt$distinctBy$1.label = 1;
            hasNext = it.hasNext(channelsKt__Channels_commonKt$distinctBy$1);
            if (hasNext != coroutine_suspended) {
                return coroutine_suspended;
            }
            Object obj5 = coroutine_suspended;
            channelsKt__Channels_commonKt$distinctBy$12 = channelsKt__Channels_commonKt$distinctBy$1;
            obj = hasNext;
            producerScope3 = producerScope;
            hashSet3 = hashSet;
            channelIterator = it;
            obj2 = obj5;
            if (!((Boolean) obj).booleanValue()) {
                E next = channelIterator.next();
                Function2 function2 = channelsKt__Channels_commonKt$distinctBy$12.$selector;
                channelsKt__Channels_commonKt$distinctBy$12.L$0 = producerScope3;
                channelsKt__Channels_commonKt$distinctBy$12.L$1 = hashSet3;
                channelsKt__Channels_commonKt$distinctBy$12.L$2 = next;
                channelsKt__Channels_commonKt$distinctBy$12.L$3 = channelIterator;
                channelsKt__Channels_commonKt$distinctBy$12.label = 2;
                Object invoke = function2.invoke(next, channelsKt__Channels_commonKt$distinctBy$12);
                if (invoke == obj2) {
                    return obj2;
                }
                HashSet hashSet8 = hashSet3;
                e = next;
                obj = invoke;
                producerScope2 = producerScope3;
                hashSet2 = hashSet8;
                if (hashSet2.contains(obj)) {
                    channelsKt__Channels_commonKt$distinctBy$12.L$0 = producerScope2;
                    channelsKt__Channels_commonKt$distinctBy$12.L$1 = hashSet2;
                    channelsKt__Channels_commonKt$distinctBy$12.L$2 = e;
                    channelsKt__Channels_commonKt$distinctBy$12.L$3 = channelIterator;
                    channelsKt__Channels_commonKt$distinctBy$12.L$4 = obj;
                    channelsKt__Channels_commonKt$distinctBy$12.label = 3;
                    if (producerScope2.send(e, channelsKt__Channels_commonKt$distinctBy$12) == obj2) {
                        return obj2;
                    }
                    hashSet7 = hashSet2;
                    producerScope7 = producerScope2;
                    hashSet7.add(obj);
                    channelsKt__Channels_commonKt$distinctBy$1 = channelsKt__Channels_commonKt$distinctBy$12;
                    coroutine_suspended = obj2;
                    it = channelIterator;
                    hashSet = hashSet7;
                    producerScope = producerScope7;
                    channelsKt__Channels_commonKt$distinctBy$1.L$0 = producerScope;
                    channelsKt__Channels_commonKt$distinctBy$1.L$1 = hashSet;
                    channelsKt__Channels_commonKt$distinctBy$1.L$2 = it;
                    channelsKt__Channels_commonKt$distinctBy$1.label = 1;
                    hasNext = it.hasNext(channelsKt__Channels_commonKt$distinctBy$1);
                    if (hasNext != coroutine_suspended) {
                    }
                } else {
                    channelsKt__Channels_commonKt$distinctBy$1 = channelsKt__Channels_commonKt$distinctBy$12;
                    coroutine_suspended = obj2;
                    it = channelIterator;
                    hashSet = hashSet2;
                    producerScope = producerScope2;
                    channelsKt__Channels_commonKt$distinctBy$1.L$0 = producerScope;
                    channelsKt__Channels_commonKt$distinctBy$1.L$1 = hashSet;
                    channelsKt__Channels_commonKt$distinctBy$1.L$2 = it;
                    channelsKt__Channels_commonKt$distinctBy$1.label = 1;
                    hasNext = it.hasNext(channelsKt__Channels_commonKt$distinctBy$1);
                    if (hasNext != coroutine_suspended) {
                    }
                }
            } else {
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
