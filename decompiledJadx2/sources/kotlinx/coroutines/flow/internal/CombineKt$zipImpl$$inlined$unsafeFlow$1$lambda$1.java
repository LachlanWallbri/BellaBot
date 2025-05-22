package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Combine.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m3961d2 = {"<anonymous>", "", "T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/internal/CombineKt$zipImpl$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $this_unsafeFlow;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
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
    private CoroutineScope f8877p$;
    final /* synthetic */ CombineKt$zipImpl$$inlined$unsafeFlow$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1(FlowCollector flowCollector, Continuation continuation, CombineKt$zipImpl$$inlined$unsafeFlow$1 combineKt$zipImpl$$inlined$unsafeFlow$1) {
        super(2, continuation);
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = combineKt$zipImpl$$inlined$unsafeFlow$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = new CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1(this.$this_unsafeFlow, continuation, this.this$0);
        combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.f8877p$ = (CoroutineScope) obj;
        return combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0195 A[Catch: all -> 0x0281, TRY_LEAVE, TryCatch #0 {all -> 0x0281, blocks: (B:19:0x018d, B:21:0x0195, B:60:0x0269), top: B:18:0x018d }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01d5 A[Catch: all -> 0x0263, TryCatch #2 {all -> 0x0263, blocks: (B:26:0x01c7, B:29:0x01d5, B:32:0x01e6, B:35:0x01f1), top: B:25:0x01c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x024d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0269 A[Catch: all -> 0x0281, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0281, blocks: (B:19:0x018d, B:21:0x0195, B:60:0x0269), top: B:18:0x018d }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x01cf -> B:12:0x0160). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        AbortFlowException abortFlowException;
        ReceiveChannel receiveChannel;
        final ReceiveChannel receiveChannel2;
        Object obj2;
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
        ChannelIterator channelIterator;
        ReceiveChannel receiveChannel3;
        ReceiveChannel receiveChannel4;
        Throwable th;
        ReceiveChannel receiveChannel5;
        CoroutineScope coroutineScope;
        ChannelIterator channelIterator2;
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12;
        ReceiveChannel receiveChannel6;
        Object obj3;
        Object obj4;
        CoroutineScope coroutineScope2;
        ReceiveChannel receiveChannel7;
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13;
        Object obj5;
        ChannelIterator channelIterator3;
        CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
        ReceiveChannel receiveChannel8;
        ReceiveChannel receiveChannel9;
        ChannelIterator channelIterator4;
        Throwable th2;
        ReceiveChannel receiveChannel10;
        FlowCollector flowCollector;
        ReceiveChannel receiveChannel11;
        ReceiveChannel receiveChannel12;
        ReceiveChannel receiveChannel13;
        Object obj6;
        Object obj7;
        CoroutineScope coroutineScope3;
        ReceiveChannel receiveChannel14;
        ChannelIterator channelIterator5;
        ReceiveChannel receiveChannel15;
        Throwable th3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            try {
            } catch (AbortFlowException e) {
                e = e;
            }
            try {
                if (i != 0) {
                    try {
                        if (i == 1) {
                            ChannelIterator channelIterator6 = (ChannelIterator) this.L$9;
                            ReceiveChannel receiveChannel16 = (ReceiveChannel) this.L$8;
                            Throwable th4 = (Throwable) this.L$7;
                            receiveChannel2 = (ReceiveChannel) this.L$6;
                            CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$15 = (CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) this.L$5;
                            ReceiveChannel receiveChannel17 = (ReceiveChannel) this.L$4;
                            ChannelIterator channelIterator7 = (ChannelIterator) this.L$3;
                            receiveChannel = (ReceiveChannel) this.L$2;
                            ReceiveChannel receiveChannel18 = (ReceiveChannel) this.L$1;
                            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            obj2 = obj;
                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = this;
                            channelIterator = channelIterator6;
                            receiveChannel3 = receiveChannel18;
                            receiveChannel4 = receiveChannel17;
                            th = th4;
                            receiveChannel5 = receiveChannel16;
                            coroutineScope = coroutineScope4;
                            channelIterator2 = channelIterator7;
                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$15;
                            try {
                                if (!((Boolean) obj2).booleanValue()) {
                                    obj4 = channelIterator.next();
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel3;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel4;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel5;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$10 = obj4;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$11 = obj4;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = 2;
                                    obj3 = channelIterator2.hasNext(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1);
                                    if (obj3 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    receiveChannel7 = receiveChannel;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1;
                                    receiveChannel6 = receiveChannel3;
                                    coroutineScope2 = coroutineScope;
                                    channelIterator3 = channelIterator2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12;
                                    receiveChannel8 = receiveChannel2;
                                    receiveChannel9 = receiveChannel5;
                                    channelIterator4 = channelIterator;
                                    th2 = th;
                                    receiveChannel10 = receiveChannel4;
                                    obj5 = obj4;
                                    if (((Boolean) obj3).booleanValue()) {
                                    }
                                } else {
                                    Unit unit = Unit.INSTANCE;
                                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                                    if (!receiveChannel.isClosedForReceive()) {
                                        abortFlowException = new AbortFlowException(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.$this_unsafeFlow);
                                        receiveChannel.cancel((CancellationException) abortFlowException);
                                    }
                                    return Unit.INSTANCE;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                th3 = th;
                                throw th3;
                            }
                        } else if (i == 2) {
                            Object obj8 = this.L$11;
                            Object obj9 = this.L$10;
                            ChannelIterator channelIterator8 = (ChannelIterator) this.L$9;
                            ReceiveChannel receiveChannel19 = (ReceiveChannel) this.L$8;
                            Throwable th6 = (Throwable) this.L$7;
                            ReceiveChannel receiveChannel20 = (ReceiveChannel) this.L$6;
                            CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$16 = (CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) this.L$5;
                            ReceiveChannel receiveChannel21 = (ReceiveChannel) this.L$4;
                            ChannelIterator channelIterator9 = (ChannelIterator) this.L$3;
                            ReceiveChannel receiveChannel22 = (ReceiveChannel) this.L$2;
                            receiveChannel6 = (ReceiveChannel) this.L$1;
                            CoroutineScope coroutineScope5 = (CoroutineScope) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            obj3 = obj;
                            obj4 = obj8;
                            coroutineScope2 = coroutineScope5;
                            receiveChannel7 = receiveChannel22;
                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13 = this;
                            obj5 = obj9;
                            channelIterator3 = channelIterator9;
                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$16;
                            receiveChannel8 = receiveChannel20;
                            receiveChannel9 = receiveChannel19;
                            channelIterator4 = channelIterator8;
                            th2 = th6;
                            receiveChannel10 = receiveChannel21;
                            try {
                                if (((Boolean) obj3).booleanValue()) {
                                    receiveChannel15 = receiveChannel9;
                                    receiveChannel2 = receiveChannel6;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13;
                                    receiveChannel = receiveChannel7;
                                } else {
                                    FlowCollector flowCollector2 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.$this_unsafeFlow;
                                    Function3 function3 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.this$0.$transform$inlined;
                                    Object obj10 = coroutine_suspended;
                                    Object obj11 = obj4 == NullSurrogateKt.NULL ? null : obj4;
                                    Symbol symbol = NullSurrogateKt.NULL;
                                    Object next = channelIterator3.next();
                                    if (next == symbol) {
                                        next = null;
                                    }
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$0 = coroutineScope2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$1 = receiveChannel6;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$2 = receiveChannel7;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$3 = channelIterator3;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$4 = receiveChannel10;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$6 = receiveChannel8;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$7 = th2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$8 = receiveChannel9;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$9 = channelIterator4;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$10 = obj5;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$11 = obj4;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.L$12 = flowCollector2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13.label = 3;
                                    Object obj12 = obj11;
                                    CoroutineScope coroutineScope6 = coroutineScope2;
                                    obj7 = function3.invoke(obj12, next, combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13);
                                    obj6 = obj10;
                                    if (obj7 == obj6) {
                                        return obj6;
                                    }
                                    flowCollector = flowCollector2;
                                    receiveChannel12 = receiveChannel6;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$13;
                                    receiveChannel11 = receiveChannel10;
                                    receiveChannel14 = receiveChannel7;
                                    channelIterator5 = channelIterator3;
                                    coroutineScope3 = coroutineScope6;
                                    ReceiveChannel receiveChannel23 = receiveChannel9;
                                    receiveChannel2 = receiveChannel8;
                                    receiveChannel13 = receiveChannel23;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope3;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel12;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel14;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator5;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel11;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel13;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator4;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$10 = obj5;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$11 = obj4;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = 4;
                                    if (flowCollector.emit(obj7, combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) != obj6) {
                                    }
                                }
                            } catch (Throwable th7) {
                                th3 = th7;
                                receiveChannel2 = receiveChannel8;
                                throw th3;
                            }
                        } else if (i == 3) {
                            flowCollector = (FlowCollector) this.L$12;
                            Object obj13 = this.L$11;
                            Object obj14 = this.L$10;
                            ChannelIterator channelIterator10 = (ChannelIterator) this.L$9;
                            ReceiveChannel receiveChannel24 = (ReceiveChannel) this.L$8;
                            Throwable th8 = (Throwable) this.L$7;
                            ReceiveChannel receiveChannel25 = (ReceiveChannel) this.L$6;
                            CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$17 = (CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) this.L$5;
                            receiveChannel11 = (ReceiveChannel) this.L$4;
                            ChannelIterator channelIterator11 = (ChannelIterator) this.L$3;
                            ReceiveChannel receiveChannel26 = (ReceiveChannel) this.L$2;
                            ReceiveChannel receiveChannel27 = (ReceiveChannel) this.L$1;
                            CoroutineScope coroutineScope7 = (CoroutineScope) this.L$0;
                            try {
                                ResultKt.throwOnFailure(obj);
                                receiveChannel12 = receiveChannel27;
                                channelIterator4 = channelIterator10;
                                obj5 = obj14;
                                receiveChannel13 = receiveChannel24;
                                receiveChannel2 = receiveChannel25;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = this;
                                obj6 = coroutine_suspended;
                                obj7 = obj;
                                obj4 = obj13;
                                coroutineScope3 = coroutineScope7;
                                receiveChannel14 = receiveChannel26;
                                th2 = th8;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$17;
                                channelIterator5 = channelIterator11;
                                try {
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope3;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel12;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel14;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator5;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel11;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th2;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel13;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator4;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$10 = obj5;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$11 = obj4;
                                    combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = 4;
                                    if (flowCollector.emit(obj7, combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) != obj6) {
                                        return obj6;
                                    }
                                    coroutine_suspended = obj6;
                                    coroutineScope2 = coroutineScope3;
                                    ReceiveChannel receiveChannel28 = receiveChannel13;
                                    receiveChannel8 = receiveChannel2;
                                    channelIterator3 = channelIterator5;
                                    receiveChannel2 = receiveChannel12;
                                    ReceiveChannel receiveChannel29 = receiveChannel11;
                                    receiveChannel = receiveChannel14;
                                    receiveChannel10 = receiveChannel29;
                                    receiveChannel15 = receiveChannel28;
                                } catch (Throwable th9) {
                                    th3 = th9;
                                    try {
                                        throw th3;
                                    } catch (Throwable th10) {
                                        ChannelsKt.cancelConsumed(receiveChannel2, th3);
                                        throw th10;
                                    }
                                }
                            } catch (Throwable th11) {
                                th = th11;
                                receiveChannel2 = receiveChannel25;
                                th3 = th;
                                throw th3;
                            }
                        } else {
                            if (i != 4) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ChannelIterator channelIterator12 = (ChannelIterator) this.L$9;
                            ReceiveChannel receiveChannel30 = (ReceiveChannel) this.L$8;
                            Throwable th12 = (Throwable) this.L$7;
                            ReceiveChannel receiveChannel31 = (ReceiveChannel) this.L$6;
                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = (CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1) this.L$5;
                            ReceiveChannel receiveChannel32 = (ReceiveChannel) this.L$4;
                            ChannelIterator channelIterator13 = (ChannelIterator) this.L$3;
                            receiveChannel = (ReceiveChannel) this.L$2;
                            ReceiveChannel receiveChannel33 = (ReceiveChannel) this.L$1;
                            CoroutineScope coroutineScope8 = (CoroutineScope) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            channelIterator4 = channelIterator12;
                            coroutineScope2 = coroutineScope8;
                            th2 = th12;
                            receiveChannel10 = receiveChannel32;
                            channelIterator3 = channelIterator13;
                            combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = this;
                            receiveChannel8 = receiveChannel31;
                            receiveChannel2 = receiveChannel33;
                            receiveChannel15 = receiveChannel30;
                        }
                    } catch (Throwable th13) {
                        th = th13;
                    }
                } else {
                    ResultKt.throwOnFailure(obj);
                    coroutineScope2 = this.f8877p$;
                    receiveChannel2 = CombineKt.asChannel(coroutineScope2, this.this$0.$flow$inlined);
                    receiveChannel = CombineKt.asChannel(coroutineScope2, this.this$0.$flow2$inlined);
                    if (receiveChannel != null) {
                        ((SendChannel) receiveChannel).invokeOnClose(new Function1<Throwable, Unit>() { // from class: kotlinx.coroutines.flow.internal.CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Throwable th14) {
                                invoke2(th14);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Throwable th14) {
                                if (receiveChannel2.isClosedForReceive()) {
                                    return;
                                }
                                receiveChannel2.cancel((CancellationException) new AbortFlowException(CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.this.$this_unsafeFlow));
                            }
                        });
                        channelIterator3 = receiveChannel.iterator();
                        try {
                            th2 = (Throwable) null;
                            try {
                                channelIterator4 = receiveChannel2.iterator();
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14 = this;
                                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                                receiveChannel10 = receiveChannel2;
                                receiveChannel8 = receiveChannel10;
                                receiveChannel15 = receiveChannel8;
                            } catch (Throwable th14) {
                                th = th14;
                                th3 = th;
                                throw th3;
                            }
                        } catch (AbortFlowException e2) {
                            e = e2;
                            FlowExceptions_commonKt.checkOwnership(e, this.$this_unsafeFlow);
                            if (!receiveChannel.isClosedForReceive()) {
                                abortFlowException = new AbortFlowException(this.$this_unsafeFlow);
                                receiveChannel.cancel((CancellationException) abortFlowException);
                            }
                            return Unit.INSTANCE;
                        } catch (Throwable th15) {
                            th = th15;
                            if (!receiveChannel.isClosedForReceive()) {
                                receiveChannel.cancel((CancellationException) new AbortFlowException(this.$this_unsafeFlow));
                            }
                            throw th;
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.channels.SendChannel<*>");
                    }
                }
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$0 = coroutineScope2;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$1 = receiveChannel2;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$2 = receiveChannel;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$3 = channelIterator3;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$4 = receiveChannel10;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$5 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$6 = receiveChannel8;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$7 = th2;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$8 = receiveChannel15;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.L$9 = channelIterator4;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1.label = 1;
                obj2 = channelIterator4.hasNext(combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                ChannelIterator channelIterator14 = channelIterator3;
                coroutineScope = coroutineScope2;
                receiveChannel3 = receiveChannel2;
                receiveChannel2 = receiveChannel8;
                combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$12 = combineKt$zipImpl$$inlined$unsafeFlow$1$lambda$14;
                channelIterator2 = channelIterator14;
                ReceiveChannel receiveChannel34 = receiveChannel10;
                th = th2;
                channelIterator = channelIterator4;
                receiveChannel5 = receiveChannel15;
                receiveChannel4 = receiveChannel34;
                if (!((Boolean) obj2).booleanValue()) {
                }
            } catch (Throwable th16) {
                th3 = th16;
                receiveChannel2 = receiveChannel8;
                throw th3;
            }
        } catch (Throwable th17) {
            th = th17;
        }
    }
}
