package com.pudutech.schedulerlib.p065ui;

import android.widget.Button;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.schedulerlib.C5725R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SchedulerPressTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerPressTestActivity$openShakeHandTest$1", m3970f = "SchedulerPressTestActivity.kt", m3971i = {0, 1}, m3972l = {475, 476}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes7.dex */
public final class SchedulerPressTestActivity$openShakeHandTest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7478p$;
    final /* synthetic */ SchedulerPressTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerPressTestActivity$openShakeHandTest$1(SchedulerPressTestActivity schedulerPressTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerPressTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerPressTestActivity$openShakeHandTest$1 schedulerPressTestActivity$openShakeHandTest$1 = new SchedulerPressTestActivity$openShakeHandTest$1(this.this$0, completion);
        schedulerPressTestActivity$openShakeHandTest$1.f7478p$ = (CoroutineScope) obj;
        return schedulerPressTestActivity$openShakeHandTest$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SchedulerPressTestActivity$openShakeHandTest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x005a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7478p$;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    if (!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
                        this.this$0.shakeHandButtonFlag = false;
                        ((Button) this.this$0._$_findCachedViewById(C5725R.id.shakeHandKey)).setText("握手测试(已打开)");
                        Toast.makeText(this.this$0, "握手测试按钮已经打开", 0).show();
                        str2 = this.this$0.TAG;
                        Pdlog.m3273d(str2, "握手测试按钮已经打开");
                        this.this$0.shakeHandTestStart();
                    } else {
                        Toast.makeText(this.this$0, "握手测试按钮打开失败，请再试一次", 0).show();
                        str = this.this$0.TAG;
                        Pdlog.m3273d(str, "握手测试按钮打开失败");
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        SchedulerPressTestActivity$openShakeHandTest$1$openResult$1 schedulerPressTestActivity$openShakeHandTest$1$openResult$1 = new SchedulerPressTestActivity$openShakeHandTest$1$openResult$1(this, null);
        this.L$0 = coroutineScope;
        this.label = 2;
        obj = TimeoutKt.withTimeoutOrNull(SolicitService.CAMERA_OPEN_TIME_OUT, schedulerPressTestActivity$openShakeHandTest$1$openResult$1, this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        if (!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
        }
        return Unit.INSTANCE;
    }
}
