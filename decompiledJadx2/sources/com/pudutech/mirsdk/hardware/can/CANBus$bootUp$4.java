package com.pudutech.mirsdk.hardware.can;

import com.pudutech.mirsdk.SolicitService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ReceiveChannel;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
 */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$bootUp$4", m3970f = "CANBus.kt", m3971i = {0, 1, 1}, m3972l = {154, 156}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "event"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes2.dex */
public final class CANBus$bootUp$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $tickerChannel;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6019p$;
    final /* synthetic */ CANBus this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CANBus$bootUp$4(CANBus cANBus, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cANBus;
        this.$tickerChannel = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CANBus$bootUp$4 cANBus$bootUp$4 = new CANBus$bootUp$4(this.this$0, this.$tickerChannel, completion);
        cANBus$bootUp$4.f6019p$ = (CoroutineScope) obj;
        return cANBus$bootUp$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CANBus$bootUp$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0051 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x007f -> B:7:0x0045). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ChannelIterator it;
        CANBus$bootUp$4 cANBus$bootUp$4;
        Object hasNext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6019p$;
            it = ((ReceiveChannel) this.$tickerChannel.element).iterator();
        } else if (i == 1) {
            it = (ChannelIterator) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = coroutineScope2;
            cANBus$bootUp$4 = this;
            if (!((Boolean) obj).booleanValue()) {
                Unit unit = (Unit) it.next();
                cANBus$bootUp$4.this$0.m4425sendGBYM_sE(new byte[]{0, 9, 0, 0, 0, 0, 0});
                cANBus$bootUp$4.L$0 = coroutineScope3;
                cANBus$bootUp$4.L$1 = unit;
                cANBus$bootUp$4.L$2 = it;
                cANBus$bootUp$4.label = 2;
                if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, cANBus$bootUp$4) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope3;
                cANBus$bootUp$4.L$0 = coroutineScope;
                cANBus$bootUp$4.L$1 = it;
                cANBus$bootUp$4.label = 1;
                hasNext = it.hasNext(cANBus$bootUp$4);
                if (hasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope3 = coroutineScope;
                obj = hasNext;
                if (!((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) this.L$2;
            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope4;
        }
        cANBus$bootUp$4 = this;
        cANBus$bootUp$4.L$0 = coroutineScope;
        cANBus$bootUp$4.L$1 = it;
        cANBus$bootUp$4.label = 1;
        hasNext = it.hasNext(cANBus$bootUp$4);
        if (hasNext != coroutine_suspended) {
        }
    }
}
