package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Delay.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3", m3970f = "Delay.kt", m3971i = {0, 1, 2}, m3972l = {129, 131, 132}, m3973m = "invokeSuspend", m3974n = {"$this$produce", "$this$produce", "$this$produce"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes2.dex */
public final class FlowKt__DelayKt$fixedPeriodTicker$3 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $delayMillis;
    final /* synthetic */ long $initialDelayMillis;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private ProducerScope f8825p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__DelayKt$fixedPeriodTicker$3(long j, long j2, Continuation continuation) {
        super(2, continuation);
        this.$initialDelayMillis = j;
        this.$delayMillis = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FlowKt__DelayKt$fixedPeriodTicker$3 flowKt__DelayKt$fixedPeriodTicker$3 = new FlowKt__DelayKt$fixedPeriodTicker$3(this.$initialDelayMillis, this.$delayMillis, continuation);
        flowKt__DelayKt$fixedPeriodTicker$3.f8825p$ = (ProducerScope) obj;
        return flowKt__DelayKt$fixedPeriodTicker$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ProducerScope<? super Unit> producerScope, Continuation<? super Unit> continuation) {
        return ((FlowKt__DelayKt$fixedPeriodTicker$3) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005a A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0058 -> B:12:0x003d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L2a
            if (r1 == r4) goto L11
            if (r1 == r3) goto L21
            if (r1 != r2) goto L19
        L11:
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L3c
        L19:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L21:
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.channels.ProducerScope r1 = (kotlinx.coroutines.channels.ProducerScope) r1
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r7
            goto L4e
        L2a:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ProducerScope r1 = r7.f8825p$
            long r5 = r7.$initialDelayMillis
            r7.L$0 = r1
            r7.label = r4
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r5, r7)
            if (r8 != r0) goto L3c
            return r0
        L3c:
            r8 = r7
        L3d:
            kotlinx.coroutines.channels.SendChannel r4 = r1.getChannel()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            r8.L$0 = r1
            r8.label = r3
            java.lang.Object r4 = r4.send(r5, r8)
            if (r4 != r0) goto L4e
            return r0
        L4e:
            long r4 = r8.$delayMillis
            r8.L$0 = r1
            r8.label = r2
            java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r8)
            if (r4 != r0) goto L3d
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DelayKt$fixedPeriodTicker$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
