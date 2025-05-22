package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import com.pudutech.mirsdk.function.C4946R;
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

/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$personDetectListener$1$onPersonDetection$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class MirSDKActivity$personDetectListener$1$onPersonDetection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $degree;
    final /* synthetic */ double $distance;
    final /* synthetic */ int $id;
    final /* synthetic */ int $result;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5695p$;
    final /* synthetic */ MirSDKActivity$personDetectListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$personDetectListener$1$onPersonDetection$1(MirSDKActivity$personDetectListener$1 mirSDKActivity$personDetectListener$1, int i, int i2, double d, double d2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity$personDetectListener$1;
        this.$result = i;
        this.$id = i2;
        this.$degree = d;
        this.$distance = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$personDetectListener$1$onPersonDetection$1 mirSDKActivity$personDetectListener$1$onPersonDetection$1 = new MirSDKActivity$personDetectListener$1$onPersonDetection$1(this.this$0, this.$result, this.$id, this.$degree, this.$distance, completion);
        mirSDKActivity$personDetectListener$1$onPersonDetection$1.f5695p$ = (CoroutineScope) obj;
        return mirSDKActivity$personDetectListener$1$onPersonDetection$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$personDetectListener$1$onPersonDetection$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5695p$;
        TextView tx_detect_result = (TextView) this.this$0.this$0._$_findCachedViewById(C4946R.id.tx_detect_result);
        Intrinsics.checkExpressionValueIsNotNull(tx_detect_result, "tx_detect_result");
        tx_detect_result.setText("检测结果：" + this.$result + ",id=" + this.$id + ",degree=" + this.$degree + ",distance=" + this.$distance);
        return Unit.INSTANCE;
    }
}
