package com.pudutech.mirsdk.hardware.activity;

import android.widget.TextView;
import com.pudutech.base.CommonKt;
import com.pudutech.mirsdk.hardware.Encoder;
import com.pudutech.mirsdk.hardware.activity.HardwareActivity$connectService$1;
import com.pudutech.mirsdk.hardware.library.C5193R;
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

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$connectService$1$4$onEncoder$1", m3970f = "HardwareActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
final class HardwareActivity$connectService$1$4$onEncoder$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $p0;
    final /* synthetic */ double $p1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5954p$;
    final /* synthetic */ HardwareActivity$connectService$1.BinderC50844 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareActivity$connectService$1$4$onEncoder$1(HardwareActivity$connectService$1.BinderC50844 binderC50844, double d, double d2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = binderC50844;
        this.$p0 = d;
        this.$p1 = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareActivity$connectService$1$4$onEncoder$1 hardwareActivity$connectService$1$4$onEncoder$1 = new HardwareActivity$connectService$1$4$onEncoder$1(this.this$0, this.$p0, this.$p1, completion);
        hardwareActivity$connectService$1$4$onEncoder$1.f5954p$ = (CoroutineScope) obj;
        return hardwareActivity$connectService$1$4$onEncoder$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareActivity$connectService$1$4$onEncoder$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Encoder.DoubleWheel doubleWheel;
        Encoder.DoubleWheel doubleWheel2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        TextView textView_Encoder = (TextView) HardwareActivity$connectService$1.this.this$0._$_findCachedViewById(C5193R.id.textView_Encoder);
        Intrinsics.checkExpressionValueIsNotNull(textView_Encoder, "textView_Encoder");
        StringBuilder sb = new StringBuilder();
        sb.append("left:");
        sb.append(CommonKt.format(this.$p0, 2));
        sb.append(" right:");
        sb.append(CommonKt.format(this.$p1, 2));
        sb.append(" accumulate left:");
        doubleWheel = HardwareActivity$connectService$1.this.this$0.encoderAccumulate;
        sb.append(CommonKt.format(doubleWheel.getLeft(), 2));
        sb.append(" right:");
        doubleWheel2 = HardwareActivity$connectService$1.this.this$0.encoderAccumulate;
        sb.append(CommonKt.format(doubleWheel2.getRight(), 2));
        textView_Encoder.setText(sb.toString());
        return Unit.INSTANCE;
    }
}
