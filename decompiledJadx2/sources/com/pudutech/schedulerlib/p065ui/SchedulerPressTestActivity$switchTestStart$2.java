package com.pudutech.schedulerlib.p065ui;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.C5725R;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SchedulerPressTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerPressTestActivity$switchTestStart$2", m3970f = "SchedulerPressTestActivity.kt", m3971i = {0}, m3972l = {127}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes7.dex */
public final class SchedulerPressTestActivity$switchTestStart$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7487p$;
    final /* synthetic */ SchedulerPressTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerPressTestActivity$switchTestStart$2(SchedulerPressTestActivity schedulerPressTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerPressTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerPressTestActivity$switchTestStart$2 schedulerPressTestActivity$switchTestStart$2 = new SchedulerPressTestActivity$switchTestStart$2(this.this$0, completion);
        schedulerPressTestActivity$switchTestStart$2.f7487p$ = (CoroutineScope) obj;
        return schedulerPressTestActivity$switchTestStart$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SchedulerPressTestActivity$switchTestStart$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        String str;
        AtomicLong atomicLong5;
        AtomicLong atomicLong6;
        String str2;
        AtomicLong atomicLong7;
        AtomicLong atomicLong8;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7487p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (!z) {
            TextView open_cnt = (TextView) this.this$0._$_findCachedViewById(C5725R.id.open_cnt);
            Intrinsics.checkExpressionValueIsNotNull(open_cnt, "open_cnt");
            StringBuilder sb = new StringBuilder();
            sb.append("打开： ");
            atomicLong5 = this.this$0.switchOpenCnt;
            sb.append(atomicLong5);
            sb.append(" 次");
            open_cnt.setText(sb.toString());
            TextView open_success_cnt = (TextView) this.this$0._$_findCachedViewById(C5725R.id.open_success_cnt);
            Intrinsics.checkExpressionValueIsNotNull(open_success_cnt, "open_success_cnt");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("成功： ");
            atomicLong6 = this.this$0.switchOpenSuccess;
            sb2.append(atomicLong6);
            sb2.append(" 次");
            open_success_cnt.setText(sb2.toString());
            str2 = this.this$0.TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("开关打开次数：");
            atomicLong7 = this.this$0.switchOpenCnt;
            sb3.append(atomicLong7);
            sb3.append(", 成功次数：");
            atomicLong8 = this.this$0.switchOpenSuccess;
            sb3.append(atomicLong8);
            Pdlog.m3273d(str2, sb3.toString());
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(500L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        atomicLong = this.this$0.switchOpenCnt;
        atomicLong.set(0L);
        atomicLong2 = this.this$0.switchOpenSuccess;
        atomicLong2.set(0L);
        TextView open_cnt2 = (TextView) this.this$0._$_findCachedViewById(C5725R.id.open_cnt);
        Intrinsics.checkExpressionValueIsNotNull(open_cnt2, "open_cnt");
        StringBuilder sb4 = new StringBuilder();
        sb4.append("打开： ");
        atomicLong3 = this.this$0.switchOpenCnt;
        sb4.append(atomicLong3);
        sb4.append(" 次");
        open_cnt2.setText(sb4.toString());
        TextView open_success_cnt2 = (TextView) this.this$0._$_findCachedViewById(C5725R.id.open_success_cnt);
        Intrinsics.checkExpressionValueIsNotNull(open_success_cnt2, "open_success_cnt");
        StringBuilder sb5 = new StringBuilder();
        sb5.append("成功： ");
        atomicLong4 = this.this$0.switchOpenSuccess;
        sb5.append(atomicLong4);
        sb5.append(" 次");
        open_success_cnt2.setText(sb5.toString());
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "开关测试launch代码结束：switchTest end...");
        return Unit.INSTANCE;
    }
}
