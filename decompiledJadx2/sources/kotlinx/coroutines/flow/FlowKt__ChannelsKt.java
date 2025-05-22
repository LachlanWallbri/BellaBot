package kotlinx.coroutines.flow;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BroadcastChannel;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.ValueOrClosed;
import kotlinx.coroutines.flow.internal.ChannelFlowKt;

/* compiled from: Channels.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a0\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\nH\u0007\u001a/\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a9\u0010\u0010\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a&\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\nH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, m3961d2 = {"asFlow", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/channels/BroadcastChannel;", "broadcastIn", "scope", "Lkotlinx/coroutines/CoroutineScope;", "start", "Lkotlinx/coroutines/CoroutineStart;", "consumeAsFlow", "Lkotlinx/coroutines/channels/ReceiveChannel;", "emitAll", "", "Lkotlinx/coroutines/flow/FlowCollector;", "channel", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitAllImpl", "consume", "", "emitAllImpl$FlowKt__ChannelsKt", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "produceIn", "receiveAsFlow", "kotlinx-coroutines-core"}, m3962k = 5, m3963mv = {1, 1, 16}, m3966xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes2.dex */
public final /* synthetic */ class FlowKt__ChannelsKt {
    public static final <T> Object emitAll(FlowCollector<? super T> flowCollector, ReceiveChannel<? extends T> receiveChannel, Continuation<? super Unit> continuation) {
        Object emitAllImpl$FlowKt__ChannelsKt = emitAllImpl$FlowKt__ChannelsKt(flowCollector, receiveChannel, true, continuation);
        return emitAllImpl$FlowKt__ChannelsKt == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emitAllImpl$FlowKt__ChannelsKt : Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008e A[Catch: all -> 0x0060, TRY_LEAVE, TryCatch #0 {all -> 0x0060, blocks: (B:12:0x0039, B:20:0x0082, B:22:0x008e, B:28:0x009c, B:30:0x009d, B:46:0x005c), top: B:7:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009d A[Catch: all -> 0x0060, TRY_LEAVE, TryCatch #0 {all -> 0x0060, blocks: (B:12:0x0039, B:20:0x0082, B:22:0x008e, B:28:0x009c, B:30:0x009d, B:46:0x005c), top: B:7:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* JADX WARN: Type inference failed for: r10v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r10v3, types: [kotlinx.coroutines.channels.ReceiveChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.flow.FlowCollector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r9v0, types: [kotlinx.coroutines.channels.ReceiveChannel<? extends T>] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00b1 -> B:13:0x003c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ <T> Object emitAllImpl$FlowKt__ChannelsKt(FlowCollector<? super T> flowCollector, ReceiveChannel<? extends T> receiveChannel, boolean z, Continuation<? super Unit> continuation) {
        FlowKt__ChannelsKt$emitAllImpl$1 flowKt__ChannelsKt$emitAllImpl$1;
        int i;
        Throwable th;
        Throwable th2;
        ?? r2;
        Object holder;
        boolean z2;
        ReceiveChannel receiveChannel2;
        Object receiveOrClosed;
        try {
            if (continuation instanceof FlowKt__ChannelsKt$emitAllImpl$1) {
                flowKt__ChannelsKt$emitAllImpl$1 = (FlowKt__ChannelsKt$emitAllImpl$1) continuation;
                if ((flowKt__ChannelsKt$emitAllImpl$1.label & Integer.MIN_VALUE) != 0) {
                    flowKt__ChannelsKt$emitAllImpl$1.label -= Integer.MIN_VALUE;
                    Object obj = flowKt__ChannelsKt$emitAllImpl$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i = flowKt__ChannelsKt$emitAllImpl$1.label;
                    if (i != 0) {
                        ResultKt.throwOnFailure(obj);
                        th = (Throwable) null;
                        receiveChannel2 = receiveChannel;
                        z2 = z;
                        flowKt__ChannelsKt$emitAllImpl$1.L$0 = flowCollector;
                        flowKt__ChannelsKt$emitAllImpl$1.L$1 = receiveChannel2;
                        flowKt__ChannelsKt$emitAllImpl$1.Z$0 = z2;
                        flowKt__ChannelsKt$emitAllImpl$1.L$2 = th;
                        flowKt__ChannelsKt$emitAllImpl$1.L$3 = flowCollector;
                        flowKt__ChannelsKt$emitAllImpl$1.label = 1;
                        receiveOrClosed = receiveChannel2.receiveOrClosed(flowKt__ChannelsKt$emitAllImpl$1);
                        if (receiveOrClosed == coroutine_suspended) {
                        }
                    } else if (i == 1) {
                        th2 = (Throwable) flowKt__ChannelsKt$emitAllImpl$1.L$2;
                        boolean z3 = (ReceiveChannel<? extends T>) flowKt__ChannelsKt$emitAllImpl$1.Z$0;
                        ReceiveChannel receiveChannel3 = (ReceiveChannel) flowKt__ChannelsKt$emitAllImpl$1.L$1;
                        FlowCollector flowCollector2 = (FlowCollector) flowKt__ChannelsKt$emitAllImpl$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        r2 = flowCollector2;
                        receiveChannel = z3;
                        z = receiveChannel3;
                        holder = ((ValueOrClosed) obj).getHolder();
                        if (!ValueOrClosed.m5543isClosedimpl(holder)) {
                        }
                    } else if (i == 2) {
                        Object obj2 = flowKt__ChannelsKt$emitAllImpl$1.L$3;
                        th2 = (Throwable) flowKt__ChannelsKt$emitAllImpl$1.L$2;
                        boolean z4 = (ReceiveChannel<? extends T>) flowKt__ChannelsKt$emitAllImpl$1.Z$0;
                        ReceiveChannel receiveChannel4 = (ReceiveChannel) flowKt__ChannelsKt$emitAllImpl$1.L$1;
                        FlowCollector<? super T> flowCollector3 = (FlowCollector) flowKt__ChannelsKt$emitAllImpl$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        FlowCollector<? super T> flowCollector4 = flowCollector3;
                        boolean z5 = z4;
                        ReceiveChannel receiveChannel5 = receiveChannel4;
                        th = th2;
                        flowCollector = flowCollector4;
                        ReceiveChannel receiveChannel6 = receiveChannel5;
                        z2 = z5;
                        receiveChannel2 = (ReceiveChannel<? extends T>) receiveChannel6;
                        try {
                            flowKt__ChannelsKt$emitAllImpl$1.L$0 = flowCollector;
                            flowKt__ChannelsKt$emitAllImpl$1.L$1 = receiveChannel2;
                            flowKt__ChannelsKt$emitAllImpl$1.Z$0 = z2;
                            flowKt__ChannelsKt$emitAllImpl$1.L$2 = th;
                            flowKt__ChannelsKt$emitAllImpl$1.L$3 = flowCollector;
                            flowKt__ChannelsKt$emitAllImpl$1.label = 1;
                            receiveOrClosed = receiveChannel2.receiveOrClosed(flowKt__ChannelsKt$emitAllImpl$1);
                            if (receiveOrClosed == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            r2 = flowCollector;
                            th2 = th;
                            obj = receiveOrClosed;
                            boolean z6 = z2;
                            z = receiveChannel2;
                            receiveChannel = (ReceiveChannel<? extends T>) (z6 ? 1 : 0);
                            holder = ((ValueOrClosed) obj).getHolder();
                            if (!ValueOrClosed.m5543isClosedimpl(holder)) {
                                Throwable m5539getCloseCauseimpl = ValueOrClosed.m5539getCloseCauseimpl(holder);
                                if (m5539getCloseCauseimpl != null) {
                                    throw m5539getCloseCauseimpl;
                                }
                                return Unit.INSTANCE;
                            }
                            Object m5540getValueimpl = ValueOrClosed.m5540getValueimpl(holder);
                            flowKt__ChannelsKt$emitAllImpl$1.L$0 = r2;
                            flowKt__ChannelsKt$emitAllImpl$1.L$1 = z;
                            flowKt__ChannelsKt$emitAllImpl$1.Z$0 = (boolean) receiveChannel;
                            flowKt__ChannelsKt$emitAllImpl$1.L$2 = th2;
                            flowKt__ChannelsKt$emitAllImpl$1.L$3 = holder;
                            flowKt__ChannelsKt$emitAllImpl$1.label = 2;
                            Object emit = r2.emit(m5540getValueimpl, flowKt__ChannelsKt$emitAllImpl$1);
                            flowCollector4 = r2;
                            z5 = receiveChannel;
                            receiveChannel5 = z;
                            if (emit == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            th = th2;
                            flowCollector = flowCollector4;
                            ReceiveChannel receiveChannel62 = receiveChannel5;
                            z2 = z5;
                            receiveChannel2 = (ReceiveChannel<? extends T>) receiveChannel62;
                            flowKt__ChannelsKt$emitAllImpl$1.L$0 = flowCollector;
                            flowKt__ChannelsKt$emitAllImpl$1.L$1 = receiveChannel2;
                            flowKt__ChannelsKt$emitAllImpl$1.Z$0 = z2;
                            flowKt__ChannelsKt$emitAllImpl$1.L$2 = th;
                            flowKt__ChannelsKt$emitAllImpl$1.L$3 = flowCollector;
                            flowKt__ChannelsKt$emitAllImpl$1.label = 1;
                            receiveOrClosed = receiveChannel2.receiveOrClosed(flowKt__ChannelsKt$emitAllImpl$1);
                            if (receiveOrClosed == coroutine_suspended) {
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            boolean z7 = z2;
                            z = receiveChannel2;
                            receiveChannel = z7;
                            try {
                                throw th;
                            } finally {
                                if (receiveChannel != 0) {
                                    ChannelsKt.cancelConsumed(z, th);
                                }
                            }
                        }
                    } else {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
            if (i != 0) {
            }
        } catch (Throwable th4) {
            th = th4;
        }
        flowKt__ChannelsKt$emitAllImpl$1 = new FlowKt__ChannelsKt$emitAllImpl$1(continuation);
        Object obj3 = flowKt__ChannelsKt$emitAllImpl$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = flowKt__ChannelsKt$emitAllImpl$1.label;
    }

    public static final <T> Flow<T> receiveAsFlow(ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow(receiveChannel, false, null, 0, 12, null);
    }

    public static final <T> Flow<T> consumeAsFlow(ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow(receiveChannel, true, null, 0, 12, null);
    }

    public static /* synthetic */ BroadcastChannel broadcastIn$default(Flow flow, CoroutineScope coroutineScope, CoroutineStart coroutineStart, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.LAZY;
        }
        return FlowKt.broadcastIn(flow, coroutineScope, coroutineStart);
    }

    public static final <T> BroadcastChannel<T> broadcastIn(Flow<? extends T> flow, CoroutineScope coroutineScope, CoroutineStart coroutineStart) {
        return ChannelFlowKt.asChannelFlow(flow).broadcastImpl(coroutineScope, coroutineStart);
    }

    public static final <T> ReceiveChannel<T> produceIn(Flow<? extends T> flow, CoroutineScope coroutineScope) {
        return ChannelFlowKt.asChannelFlow(flow).produceImpl(coroutineScope);
    }

    public static final <T> Flow<T> asFlow(final BroadcastChannel<T> broadcastChannel) {
        return new Flow<T>() { // from class: kotlinx.coroutines.flow.FlowKt__ChannelsKt$asFlow$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object emitAll = FlowKt.emitAll(flowCollector, BroadcastChannel.this.openSubscription(), (Continuation<? super Unit>) continuation);
                return emitAll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emitAll : Unit.INSTANCE;
            }
        };
    }
}
