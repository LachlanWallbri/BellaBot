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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$solicitListener$1$onPersonDetection$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class MirSDKActivity$solicitListener$1$onPersonDetection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $degree;
    final /* synthetic */ double $distance;
    final /* synthetic */ int $result;
    final /* synthetic */ int $roteStatus;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5697p$;
    final /* synthetic */ MirSDKActivity$solicitListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$solicitListener$1$onPersonDetection$1(MirSDKActivity$solicitListener$1 mirSDKActivity$solicitListener$1, int i, double d, double d2, int i2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity$solicitListener$1;
        this.$result = i;
        this.$degree = d;
        this.$distance = d2;
        this.$roteStatus = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$solicitListener$1$onPersonDetection$1 mirSDKActivity$solicitListener$1$onPersonDetection$1 = new MirSDKActivity$solicitListener$1$onPersonDetection$1(this.this$0, this.$result, this.$degree, this.$distance, this.$roteStatus, completion);
        mirSDKActivity$solicitListener$1$onPersonDetection$1.f5697p$ = (CoroutineScope) obj;
        return mirSDKActivity$solicitListener$1$onPersonDetection$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$solicitListener$1$onPersonDetection$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5697p$;
        String str = this.$result == 1 ? "检测到有行人" : "没有检测到行人";
        TextView tx_solicit_result = (TextView) this.this$0.this$0._$_findCachedViewById(C4946R.id.tx_solicit_result);
        Intrinsics.checkExpressionValueIsNotNull(tx_solicit_result, "tx_solicit_result");
        tx_solicit_result.setText(str + ",角度：" + this.$degree + ",距离：" + this.$distance + ",设备旋转状态：" + this.$roteStatus);
        return Unit.INSTANCE;
    }
}
