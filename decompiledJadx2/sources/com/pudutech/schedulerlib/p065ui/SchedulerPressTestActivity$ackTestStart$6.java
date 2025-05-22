package com.pudutech.schedulerlib.p065ui;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.utils.LimitLinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.apache.http.HttpStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SchedulerPressTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerPressTestActivity$ackTestStart$6", m3970f = "SchedulerPressTestActivity.kt", m3971i = {0}, m3972l = {HttpStatus.SC_LENGTH_REQUIRED}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes7.dex */
public final class SchedulerPressTestActivity$ackTestStart$6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7475p$;
    final /* synthetic */ SchedulerPressTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerPressTestActivity$ackTestStart$6(SchedulerPressTestActivity schedulerPressTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerPressTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerPressTestActivity$ackTestStart$6 schedulerPressTestActivity$ackTestStart$6 = new SchedulerPressTestActivity$ackTestStart$6(this.this$0, completion);
        schedulerPressTestActivity$ackTestStart$6.f7475p$ = (CoroutineScope) obj;
        return schedulerPressTestActivity$ackTestStart$6;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SchedulerPressTestActivity$ackTestStart$6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x0033 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        AtomicLong atomicLong;
        AtomicLong atomicLong2;
        AtomicLong atomicLong3;
        AtomicLong atomicLong4;
        AtomicLong atomicLong5;
        AtomicLong atomicLong6;
        LimitLinkedBlockingDeque limitLinkedBlockingDeque;
        String str;
        boolean z2;
        String str2;
        AtomicLong atomicLong7;
        AtomicLong atomicLong8;
        String str3;
        AtomicLong atomicLong9;
        AtomicLong atomicLong10;
        String str4;
        AtomicLong atomicLong11;
        AtomicLong atomicLong12;
        String str5;
        AtomicLong atomicLong13;
        AtomicLong atomicLong14;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7475p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (!z) {
            z2 = this.this$0.ackModeSelectButtonFlag;
            if (z2) {
                str4 = this.this$0.TAG;
                atomicLong11 = this.this$0.sendMsgs;
                Pdlog.m3273d(str4, "host ack send msgs amount is ", atomicLong11);
                TextView ack_send_cnt = (TextView) this.this$0._$_findCachedViewById(C5725R.id.ack_send_cnt);
                Intrinsics.checkExpressionValueIsNotNull(ack_send_cnt, "ack_send_cnt");
                StringBuilder sb = new StringBuilder();
                sb.append("发送： ");
                atomicLong12 = this.this$0.sendMsgs;
                sb.append(atomicLong12);
                sb.append(" 次");
                ack_send_cnt.setText(sb.toString());
                str5 = this.this$0.TAG;
                atomicLong13 = this.this$0.recvMsgs;
                Pdlog.m3273d(str5, "host ack recv msgs amount is ", atomicLong13);
                TextView ack_recv_cnt = (TextView) this.this$0._$_findCachedViewById(C5725R.id.ack_recv_cnt);
                Intrinsics.checkExpressionValueIsNotNull(ack_recv_cnt, "ack_recv_cnt");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("应答： ");
                atomicLong14 = this.this$0.recvMsgs;
                sb2.append(atomicLong14);
                sb2.append(" 次");
                ack_recv_cnt.setText(sb2.toString());
            } else {
                str2 = this.this$0.TAG;
                atomicLong7 = this.this$0.recvMsgs;
                Pdlog.m3273d(str2, "slave recv msgs amount is ", atomicLong7);
                TextView ack_send_cnt2 = (TextView) this.this$0._$_findCachedViewById(C5725R.id.ack_send_cnt);
                Intrinsics.checkExpressionValueIsNotNull(ack_send_cnt2, "ack_send_cnt");
                StringBuilder sb3 = new StringBuilder();
                sb3.append("接收： ");
                atomicLong8 = this.this$0.recvMsgs;
                sb3.append(atomicLong8);
                sb3.append(" 次");
                ack_send_cnt2.setText(sb3.toString());
                str3 = this.this$0.TAG;
                atomicLong9 = this.this$0.sendMsgs;
                Pdlog.m3273d(str3, "slave send msgs amount is ", atomicLong9);
                TextView ack_recv_cnt2 = (TextView) this.this$0._$_findCachedViewById(C5725R.id.ack_recv_cnt);
                Intrinsics.checkExpressionValueIsNotNull(ack_recv_cnt2, "ack_recv_cnt");
                StringBuilder sb4 = new StringBuilder();
                sb4.append("回应： ");
                atomicLong10 = this.this$0.sendMsgs;
                sb4.append(atomicLong10);
                sb4.append(" 次");
                ack_recv_cnt2.setText(sb4.toString());
            }
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(300L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        this.this$0.closeEspForPressTest();
        atomicLong = this.this$0.sendMsgs;
        atomicLong.set(0L);
        atomicLong2 = this.this$0.recvMsgs;
        atomicLong2.set(0L);
        atomicLong3 = this.this$0.sendMsgId;
        atomicLong3.set(0L);
        atomicLong4 = this.this$0.recvMsgId;
        atomicLong4.set(0L);
        TextView ack_send_cnt3 = (TextView) this.this$0._$_findCachedViewById(C5725R.id.ack_send_cnt);
        Intrinsics.checkExpressionValueIsNotNull(ack_send_cnt3, "ack_send_cnt");
        StringBuilder sb5 = new StringBuilder();
        sb5.append("发送： ");
        atomicLong5 = this.this$0.sendMsgs;
        sb5.append(atomicLong5);
        sb5.append(" 次");
        ack_send_cnt3.setText(sb5.toString());
        TextView ack_recv_cnt3 = (TextView) this.this$0._$_findCachedViewById(C5725R.id.ack_recv_cnt);
        Intrinsics.checkExpressionValueIsNotNull(ack_recv_cnt3, "ack_recv_cnt");
        StringBuilder sb6 = new StringBuilder();
        sb6.append("应答： ");
        atomicLong6 = this.this$0.recvMsgs;
        sb6.append(atomicLong6);
        sb6.append(" 次");
        ack_recv_cnt3.setText(sb6.toString());
        limitLinkedBlockingDeque = this.this$0.msgQueue;
        limitLinkedBlockingDeque.clear();
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "应答测试launch代码结束：ackTest end...");
        return Unit.INSTANCE;
    }
}
