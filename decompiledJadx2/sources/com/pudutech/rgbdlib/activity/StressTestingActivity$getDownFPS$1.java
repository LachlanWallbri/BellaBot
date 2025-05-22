package com.pudutech.rgbdlib.activity;

import android.widget.TextView;
import com.pudutech.rgbdlib.C5657R;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: StressTestingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.rgbdlib.activity.StressTestingActivity$getDownFPS$1", m3970f = "StressTestingActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class StressTestingActivity$getDownFPS$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $total;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7147p$;
    final /* synthetic */ StressTestingActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StressTestingActivity$getDownFPS$1(StressTestingActivity stressTestingActivity, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = stressTestingActivity;
        this.$total = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        StressTestingActivity$getDownFPS$1 stressTestingActivity$getDownFPS$1 = new StressTestingActivity$getDownFPS$1(this.this$0, this.$total, completion);
        stressTestingActivity$getDownFPS$1.f7147p$ = (CoroutineScope) obj;
        return stressTestingActivity$getDownFPS$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StressTestingActivity$getDownFPS$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AtomicInteger atomicInteger;
        int i;
        int i2;
        DecimalFormat decimalFormat;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7147p$;
        atomicInteger = this.this$0.downFrame;
        int andSet = atomicInteger.getAndSet(0);
        StressTestingActivity stressTestingActivity = this.this$0;
        i = stressTestingActivity.totalDownFrame;
        stressTestingActivity.totalDownFrame = i + andSet;
        i2 = this.this$0.totalDownFrame;
        TextView downText = (TextView) this.this$0._$_findCachedViewById(C5657R.id.downText);
        Intrinsics.checkExpressionValueIsNotNull(downText, "downText");
        StringBuilder sb = new StringBuilder();
        sb.append("Down: ");
        sb.append(andSet);
        sb.append(" Frame, ");
        decimalFormat = this.this$0.format;
        sb.append(decimalFormat.format((i2 * 1.0d) / this.$total));
        sb.append(" FPS");
        downText.setText(sb.toString());
        StressTestingActivity stressTestingActivity2 = this.this$0;
        TextView downText2 = (TextView) stressTestingActivity2._$_findCachedViewById(C5657R.id.downText);
        Intrinsics.checkExpressionValueIsNotNull(downText2, "downText");
        stressTestingActivity2.writeLog(downText2.getText().toString());
        return Unit.INSTANCE;
    }
}
