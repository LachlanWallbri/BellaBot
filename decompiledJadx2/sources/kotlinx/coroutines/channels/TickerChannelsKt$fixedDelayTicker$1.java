package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: TickerChannels.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0082@"}, m3961d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "", "continuation", "Lkotlin/coroutines/Continuation;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.channels.TickerChannelsKt", m3970f = "TickerChannels.kt", m3971i = {0, 0, 0, 1, 1, 1, 2, 2, 2}, m3972l = {106, 108, 109}, m3973m = "fixedDelayTicker", m3974n = {"delayMillis", "initialDelayMillis", "channel", "delayMillis", "initialDelayMillis", "channel", "delayMillis", "initialDelayMillis", "channel"}, m3975s = {"J$0", "J$1", "L$0", "J$0", "J$1", "L$0", "J$0", "J$1", "L$0"})
/* loaded from: classes2.dex */
public final class TickerChannelsKt$fixedDelayTicker$1 extends ContinuationImpl {
    long J$0;
    long J$1;
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TickerChannelsKt$fixedDelayTicker$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TickerChannelsKt.fixedDelayTicker(0L, 0L, null, this);
    }
}
