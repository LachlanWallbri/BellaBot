package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.EventLoop_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.TimeSource;
import kotlinx.coroutines.TimeSourceKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: TickerChannels.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a/\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a4\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m3961d2 = {"fixedDelayTicker", "", "delayMillis", "", "initialDelayMillis", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedPeriodTicker", "ticker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "mode", "Lkotlinx/coroutines/channels/TickerMode;", "kotlinx-coroutines-core"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class TickerChannelsKt {

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes7.dex
      classes8.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TickerMode.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[TickerMode.FIXED_PERIOD.ordinal()] = 1;
            $EnumSwitchMapping$0[TickerMode.FIXED_DELAY.ordinal()] = 2;
        }
    }

    public static /* synthetic */ ReceiveChannel ticker$default(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        if ((i & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j, j2, coroutineContext, tickerMode);
    }

    public static final ReceiveChannel<Unit> ticker(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode) {
        if (!(j >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + j + " ms").toString());
        }
        if (!(j2 >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + j2 + " ms").toString());
        }
        return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(coroutineContext), 0, new TickerChannelsKt$ticker$3(tickerMode, j, j2, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0174 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0175 -> B:13:0x0148). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object fixedPeriodTicker(long j, long j2, SendChannel<? super Unit> sendChannel, Continuation<? super Unit> continuation) {
        TickerChannelsKt$fixedPeriodTicker$1 tickerChannelsKt$fixedPeriodTicker$1;
        Object coroutine_suspended;
        int i;
        SendChannel sendChannel2;
        long j3;
        long j4;
        Long boxLong;
        long delayToNanos;
        long j5;
        long j6;
        long j7;
        SendChannel sendChannel3;
        long j8;
        long nanoTime;
        TickerChannelsKt$fixedPeriodTicker$1 tickerChannelsKt$fixedPeriodTicker$12;
        long j9;
        long coerceAtLeast;
        long delayNanosToMillis;
        char c;
        Long boxLong2;
        Unit unit;
        long j10 = j2;
        if (continuation instanceof TickerChannelsKt$fixedPeriodTicker$1) {
            tickerChannelsKt$fixedPeriodTicker$1 = (TickerChannelsKt$fixedPeriodTicker$1) continuation;
            if ((tickerChannelsKt$fixedPeriodTicker$1.label & Integer.MIN_VALUE) != 0) {
                tickerChannelsKt$fixedPeriodTicker$1.label -= Integer.MIN_VALUE;
                Object obj = tickerChannelsKt$fixedPeriodTicker$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = tickerChannelsKt$fixedPeriodTicker$1.label;
                int i2 = 2;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    TimeSource timeSource = TimeSourceKt.getTimeSource();
                    long nanoTime2 = ((timeSource == null || (boxLong = Boxing.boxLong(timeSource.nanoTime())) == null) ? System.nanoTime() : boxLong.longValue()) + EventLoop_commonKt.delayToNanos(j2);
                    tickerChannelsKt$fixedPeriodTicker$1.J$0 = j;
                    tickerChannelsKt$fixedPeriodTicker$1.J$1 = j10;
                    sendChannel2 = sendChannel;
                    tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel2;
                    tickerChannelsKt$fixedPeriodTicker$1.J$2 = nanoTime2;
                    tickerChannelsKt$fixedPeriodTicker$1.label = 1;
                    if (DelayKt.delay(j10, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    j3 = nanoTime2;
                    j4 = j;
                } else if (i == 1) {
                    long j11 = tickerChannelsKt$fixedPeriodTicker$1.J$2;
                    SendChannel sendChannel4 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                    long j12 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                    j4 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    sendChannel2 = sendChannel4;
                    j10 = j12;
                    j3 = j11;
                } else if (i != 2) {
                    if (i == 3) {
                        long j13 = tickerChannelsKt$fixedPeriodTicker$1.J$6;
                        long j14 = tickerChannelsKt$fixedPeriodTicker$1.J$5;
                        long j15 = tickerChannelsKt$fixedPeriodTicker$1.J$4;
                        j8 = tickerChannelsKt$fixedPeriodTicker$1.J$3;
                        j3 = tickerChannelsKt$fixedPeriodTicker$1.J$2;
                        sendChannel3 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                        j6 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                        j5 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                        ResultKt.throwOnFailure(obj);
                        c = 4;
                    } else {
                        if (i != 4) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        long j16 = tickerChannelsKt$fixedPeriodTicker$1.J$5;
                        long j17 = tickerChannelsKt$fixedPeriodTicker$1.J$4;
                        j8 = tickerChannelsKt$fixedPeriodTicker$1.J$3;
                        j3 = tickerChannelsKt$fixedPeriodTicker$1.J$2;
                        sendChannel3 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                        j6 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                        j5 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                        ResultKt.throwOnFailure(obj);
                        c = 4;
                    }
                    long j18 = j6;
                    j4 = j5;
                    delayToNanos = j8;
                    j10 = j18;
                    sendChannel2 = sendChannel3;
                    i2 = 2;
                    j7 = j3 + delayToNanos;
                    unit = Unit.INSTANCE;
                    tickerChannelsKt$fixedPeriodTicker$1.J$0 = j4;
                    tickerChannelsKt$fixedPeriodTicker$1.J$1 = j10;
                    tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel2;
                    tickerChannelsKt$fixedPeriodTicker$1.J$2 = j7;
                    tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
                    tickerChannelsKt$fixedPeriodTicker$1.label = i2;
                    if (sendChannel2.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sendChannel3 = sendChannel2;
                    long j19 = j10;
                    j8 = delayToNanos;
                    j5 = j4;
                    j6 = j19;
                    TimeSource timeSource2 = TimeSourceKt.getTimeSource();
                    nanoTime = (timeSource2 != null || (boxLong2 = Boxing.boxLong(timeSource2.nanoTime())) == null) ? System.nanoTime() : boxLong2.longValue();
                    tickerChannelsKt$fixedPeriodTicker$12 = tickerChannelsKt$fixedPeriodTicker$1;
                    j9 = j7;
                    coerceAtLeast = RangesKt.coerceAtLeast(j7 - nanoTime, 0L);
                    if (coerceAtLeast != 0 && j8 != 0) {
                        long j20 = j8 - ((nanoTime - j9) % j8);
                        long j21 = nanoTime + j20;
                        long delayNanosToMillis2 = EventLoop_commonKt.delayNanosToMillis(j20);
                        tickerChannelsKt$fixedPeriodTicker$12.J$0 = j5;
                        tickerChannelsKt$fixedPeriodTicker$12.J$1 = j6;
                        tickerChannelsKt$fixedPeriodTicker$12.L$0 = sendChannel3;
                        tickerChannelsKt$fixedPeriodTicker$12.J$2 = j21;
                        tickerChannelsKt$fixedPeriodTicker$12.J$3 = j8;
                        tickerChannelsKt$fixedPeriodTicker$12.J$4 = nanoTime;
                        tickerChannelsKt$fixedPeriodTicker$12.J$5 = coerceAtLeast;
                        tickerChannelsKt$fixedPeriodTicker$12.J$6 = j20;
                        tickerChannelsKt$fixedPeriodTicker$12.label = 3;
                        if (DelayKt.delay(delayNanosToMillis2, tickerChannelsKt$fixedPeriodTicker$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        tickerChannelsKt$fixedPeriodTicker$1 = tickerChannelsKt$fixedPeriodTicker$12;
                        j3 = j21;
                        c = 4;
                    } else {
                        delayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(coerceAtLeast);
                        tickerChannelsKt$fixedPeriodTicker$12.J$0 = j5;
                        tickerChannelsKt$fixedPeriodTicker$12.J$1 = j6;
                        tickerChannelsKt$fixedPeriodTicker$12.L$0 = sendChannel3;
                        long j22 = j6;
                        tickerChannelsKt$fixedPeriodTicker$12.J$2 = j9;
                        tickerChannelsKt$fixedPeriodTicker$12.J$3 = j8;
                        tickerChannelsKt$fixedPeriodTicker$12.J$4 = nanoTime;
                        tickerChannelsKt$fixedPeriodTicker$12.J$5 = coerceAtLeast;
                        c = 4;
                        tickerChannelsKt$fixedPeriodTicker$12.label = 4;
                        if (DelayKt.delay(delayNanosToMillis, tickerChannelsKt$fixedPeriodTicker$12) != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        tickerChannelsKt$fixedPeriodTicker$1 = tickerChannelsKt$fixedPeriodTicker$12;
                        j3 = j9;
                        j6 = j22;
                    }
                    long j182 = j6;
                    j4 = j5;
                    delayToNanos = j8;
                    j10 = j182;
                    sendChannel2 = sendChannel3;
                    i2 = 2;
                    j7 = j3 + delayToNanos;
                    unit = Unit.INSTANCE;
                    tickerChannelsKt$fixedPeriodTicker$1.J$0 = j4;
                    tickerChannelsKt$fixedPeriodTicker$1.J$1 = j10;
                    tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel2;
                    tickerChannelsKt$fixedPeriodTicker$1.J$2 = j7;
                    tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
                    tickerChannelsKt$fixedPeriodTicker$1.label = i2;
                    if (sendChannel2.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                    }
                } else {
                    j8 = tickerChannelsKt$fixedPeriodTicker$1.J$3;
                    j7 = tickerChannelsKt$fixedPeriodTicker$1.J$2;
                    sendChannel3 = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                    j6 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                    j5 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    TimeSource timeSource22 = TimeSourceKt.getTimeSource();
                    nanoTime = (timeSource22 != null || (boxLong2 = Boxing.boxLong(timeSource22.nanoTime())) == null) ? System.nanoTime() : boxLong2.longValue();
                    tickerChannelsKt$fixedPeriodTicker$12 = tickerChannelsKt$fixedPeriodTicker$1;
                    j9 = j7;
                    coerceAtLeast = RangesKt.coerceAtLeast(j7 - nanoTime, 0L);
                    if (coerceAtLeast != 0) {
                    }
                    delayNanosToMillis = EventLoop_commonKt.delayNanosToMillis(coerceAtLeast);
                    tickerChannelsKt$fixedPeriodTicker$12.J$0 = j5;
                    tickerChannelsKt$fixedPeriodTicker$12.J$1 = j6;
                    tickerChannelsKt$fixedPeriodTicker$12.L$0 = sendChannel3;
                    long j222 = j6;
                    tickerChannelsKt$fixedPeriodTicker$12.J$2 = j9;
                    tickerChannelsKt$fixedPeriodTicker$12.J$3 = j8;
                    tickerChannelsKt$fixedPeriodTicker$12.J$4 = nanoTime;
                    tickerChannelsKt$fixedPeriodTicker$12.J$5 = coerceAtLeast;
                    c = 4;
                    tickerChannelsKt$fixedPeriodTicker$12.label = 4;
                    if (DelayKt.delay(delayNanosToMillis, tickerChannelsKt$fixedPeriodTicker$12) != coroutine_suspended) {
                    }
                }
                delayToNanos = EventLoop_commonKt.delayToNanos(j4);
                j7 = j3 + delayToNanos;
                unit = Unit.INSTANCE;
                tickerChannelsKt$fixedPeriodTicker$1.J$0 = j4;
                tickerChannelsKt$fixedPeriodTicker$1.J$1 = j10;
                tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel2;
                tickerChannelsKt$fixedPeriodTicker$1.J$2 = j7;
                tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
                tickerChannelsKt$fixedPeriodTicker$1.label = i2;
                if (sendChannel2.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                }
            }
        }
        tickerChannelsKt$fixedPeriodTicker$1 = new TickerChannelsKt$fixedPeriodTicker$1(continuation);
        Object obj2 = tickerChannelsKt$fixedPeriodTicker$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = tickerChannelsKt$fixedPeriodTicker$1.label;
        int i22 = 2;
        if (i != 0) {
        }
        delayToNanos = EventLoop_commonKt.delayToNanos(j4);
        j7 = j3 + delayToNanos;
        unit = Unit.INSTANCE;
        tickerChannelsKt$fixedPeriodTicker$1.J$0 = j4;
        tickerChannelsKt$fixedPeriodTicker$1.J$1 = j10;
        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel2;
        tickerChannelsKt$fixedPeriodTicker$1.J$2 = j7;
        tickerChannelsKt$fixedPeriodTicker$1.J$3 = delayToNanos;
        tickerChannelsKt$fixedPeriodTicker$1.label = i22;
        if (sendChannel2.send(unit, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0092 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0090 -> B:12:0x0037). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Object fixedDelayTicker(long j, long j2, SendChannel<? super Unit> sendChannel, Continuation<? super Unit> continuation) {
        TickerChannelsKt$fixedDelayTicker$1 tickerChannelsKt$fixedDelayTicker$1;
        Object coroutine_suspended;
        int i;
        long j3;
        long j4;
        SendChannel<? super Unit> sendChannel2;
        Unit unit;
        if (continuation instanceof TickerChannelsKt$fixedDelayTicker$1) {
            tickerChannelsKt$fixedDelayTicker$1 = (TickerChannelsKt$fixedDelayTicker$1) continuation;
            if ((tickerChannelsKt$fixedDelayTicker$1.label & Integer.MIN_VALUE) != 0) {
                tickerChannelsKt$fixedDelayTicker$1.label -= Integer.MIN_VALUE;
                Object obj = tickerChannelsKt$fixedDelayTicker$1.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = tickerChannelsKt$fixedDelayTicker$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    tickerChannelsKt$fixedDelayTicker$1.J$0 = j;
                    tickerChannelsKt$fixedDelayTicker$1.J$1 = j2;
                    tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel;
                    tickerChannelsKt$fixedDelayTicker$1.label = 1;
                    if (DelayKt.delay(j2, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i == 1) {
                    sendChannel = (SendChannel) tickerChannelsKt$fixedDelayTicker$1.L$0;
                    j2 = tickerChannelsKt$fixedDelayTicker$1.J$1;
                    j = tickerChannelsKt$fixedDelayTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                } else if (i == 2) {
                    sendChannel2 = (SendChannel) tickerChannelsKt$fixedDelayTicker$1.L$0;
                    j4 = tickerChannelsKt$fixedDelayTicker$1.J$1;
                    j3 = tickerChannelsKt$fixedDelayTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    tickerChannelsKt$fixedDelayTicker$1.J$0 = j3;
                    tickerChannelsKt$fixedDelayTicker$1.J$1 = j4;
                    tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel2;
                    tickerChannelsKt$fixedDelayTicker$1.label = 3;
                    if (DelayKt.delay(j3, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    sendChannel = sendChannel2;
                    j2 = j4;
                    j = j3;
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    sendChannel2 = (SendChannel) tickerChannelsKt$fixedDelayTicker$1.L$0;
                    j4 = tickerChannelsKt$fixedDelayTicker$1.J$1;
                    j3 = tickerChannelsKt$fixedDelayTicker$1.J$0;
                    ResultKt.throwOnFailure(obj);
                    sendChannel = sendChannel2;
                    j2 = j4;
                    j = j3;
                }
                unit = Unit.INSTANCE;
                tickerChannelsKt$fixedDelayTicker$1.J$0 = j;
                tickerChannelsKt$fixedDelayTicker$1.J$1 = j2;
                tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel;
                tickerChannelsKt$fixedDelayTicker$1.label = 2;
                if (sendChannel.send(unit, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                long j5 = j;
                sendChannel2 = sendChannel;
                j4 = j2;
                j3 = j5;
                tickerChannelsKt$fixedDelayTicker$1.J$0 = j3;
                tickerChannelsKt$fixedDelayTicker$1.J$1 = j4;
                tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel2;
                tickerChannelsKt$fixedDelayTicker$1.label = 3;
                if (DelayKt.delay(j3, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                }
                sendChannel = sendChannel2;
                j2 = j4;
                j = j3;
                unit = Unit.INSTANCE;
                tickerChannelsKt$fixedDelayTicker$1.J$0 = j;
                tickerChannelsKt$fixedDelayTicker$1.J$1 = j2;
                tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel;
                tickerChannelsKt$fixedDelayTicker$1.label = 2;
                if (sendChannel.send(unit, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
                }
            }
        }
        tickerChannelsKt$fixedDelayTicker$1 = new TickerChannelsKt$fixedDelayTicker$1(continuation);
        Object obj2 = tickerChannelsKt$fixedDelayTicker$1.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = tickerChannelsKt$fixedDelayTicker$1.label;
        if (i != 0) {
        }
        unit = Unit.INSTANCE;
        tickerChannelsKt$fixedDelayTicker$1.J$0 = j;
        tickerChannelsKt$fixedDelayTicker$1.J$1 = j2;
        tickerChannelsKt$fixedDelayTicker$1.L$0 = sendChannel;
        tickerChannelsKt$fixedDelayTicker$1.label = 2;
        if (sendChannel.send(unit, tickerChannelsKt$fixedDelayTicker$1) == coroutine_suspended) {
        }
    }
}
