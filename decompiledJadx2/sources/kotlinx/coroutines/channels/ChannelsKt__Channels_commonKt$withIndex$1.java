package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import com.hoho.android.usbserial.driver.UsbId;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m3961d2 = {"<anonymous>", "", ExifInterface.LONGITUDE_EAST, "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/collections/IndexedValue;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt$withIndex$1", m3970f = "Channels.common.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {1658, UsbId.VENDOR_PROLIFIC}, m3973m = "invokeSuspend", m3974n = {"$this$produce", "index", "$this$produce", "index", C3898x.f4338g}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$1"})
/* loaded from: classes2.dex */
public final class ChannelsKt__Channels_commonKt$withIndex$1<E> extends SuspendLambda implements Function2<ProducerScope<? super IndexedValue<? extends E>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $this_withIndex;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private ProducerScope f8817p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelsKt__Channels_commonKt$withIndex$1(ReceiveChannel receiveChannel, Continuation continuation) {
        super(2, continuation);
        this.$this_withIndex = receiveChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChannelsKt__Channels_commonKt$withIndex$1 channelsKt__Channels_commonKt$withIndex$1 = new ChannelsKt__Channels_commonKt$withIndex$1(this.$this_withIndex, continuation);
        channelsKt__Channels_commonKt$withIndex$1.f8817p$ = (ProducerScope) obj;
        return channelsKt__Channels_commonKt$withIndex$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((ChannelsKt__Channels_commonKt$withIndex$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0084  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x007f -> B:6:0x0045). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope;
        int i;
        ChannelIterator<E> it;
        ChannelsKt__Channels_commonKt$withIndex$1<E> channelsKt__Channels_commonKt$withIndex$1;
        ChannelsKt__Channels_commonKt$withIndex$1<E> channelsKt__Channels_commonKt$withIndex$12;
        ProducerScope producerScope2;
        int i2;
        ChannelIterator<E> channelIterator;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            producerScope = this.f8817p$;
            i = 0;
            it = this.$this_withIndex.iterator();
            channelsKt__Channels_commonKt$withIndex$1 = this;
        } else if (i3 == 1) {
            channelIterator = (ChannelIterator) this.L$1;
            i2 = this.I$0;
            producerScope2 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            channelsKt__Channels_commonKt$withIndex$12 = this;
            if (!((Boolean) obj).booleanValue()) {
                E next = channelIterator.next();
                int i4 = i2 + 1;
                IndexedValue indexedValue = new IndexedValue(i2, next);
                channelsKt__Channels_commonKt$withIndex$12.L$0 = producerScope2;
                channelsKt__Channels_commonKt$withIndex$12.I$0 = i4;
                channelsKt__Channels_commonKt$withIndex$12.L$1 = next;
                channelsKt__Channels_commonKt$withIndex$12.L$2 = channelIterator;
                channelsKt__Channels_commonKt$withIndex$12.label = 2;
                if (producerScope2.send(indexedValue, channelsKt__Channels_commonKt$withIndex$12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                it = channelIterator;
                producerScope = producerScope2;
                channelsKt__Channels_commonKt$withIndex$1 = channelsKt__Channels_commonKt$withIndex$12;
                i = i4;
            } else {
                return Unit.INSTANCE;
            }
        } else {
            if (i3 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$2;
            int i5 = this.I$0;
            ProducerScope producerScope3 = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            producerScope = producerScope3;
            channelsKt__Channels_commonKt$withIndex$1 = this;
            it = channelIterator2;
            i = i5;
        }
        channelsKt__Channels_commonKt$withIndex$1.L$0 = producerScope;
        channelsKt__Channels_commonKt$withIndex$1.I$0 = i;
        channelsKt__Channels_commonKt$withIndex$1.L$1 = it;
        channelsKt__Channels_commonKt$withIndex$1.label = 1;
        Object hasNext = it.hasNext(channelsKt__Channels_commonKt$withIndex$1);
        if (hasNext == coroutine_suspended) {
            return coroutine_suspended;
        }
        ChannelsKt__Channels_commonKt$withIndex$1<E> channelsKt__Channels_commonKt$withIndex$13 = channelsKt__Channels_commonKt$withIndex$1;
        producerScope2 = producerScope;
        obj = hasNext;
        channelsKt__Channels_commonKt$withIndex$12 = channelsKt__Channels_commonKt$withIndex$13;
        ChannelIterator<E> channelIterator3 = it;
        i2 = i;
        channelIterator = channelIterator3;
        if (!((Boolean) obj).booleanValue()) {
        }
    }
}
