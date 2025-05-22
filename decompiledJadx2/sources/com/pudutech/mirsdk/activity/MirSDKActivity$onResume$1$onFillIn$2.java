package com.pudutech.mirsdk.activity;

import android.widget.Button;
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

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onResume$1$onFillIn$2", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class MirSDKActivity$onResume$1$onFillIn$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5691p$;
    final /* synthetic */ MirSDKActivity$onResume$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$onResume$1$onFillIn$2(MirSDKActivity$onResume$1 mirSDKActivity$onResume$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity$onResume$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$onResume$1$onFillIn$2 mirSDKActivity$onResume$1$onFillIn$2 = new MirSDKActivity$onResume$1$onFillIn$2(this.this$0, completion);
        mirSDKActivity$onResume$1$onFillIn$2.f5691p$ = (CoroutineScope) obj;
        return mirSDKActivity$onResume$1$onFillIn$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$onResume$1$onFillIn$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5691p$;
        Button button_fillin = (Button) this.this$0.this$0._$_findCachedViewById(C4946R.id.button_fillin);
        Intrinsics.checkExpressionValueIsNotNull(button_fillin, "button_fillin");
        button_fillin.setEnabled(false);
        Button button_quitfillin = (Button) this.this$0.this$0._$_findCachedViewById(C4946R.id.button_quitfillin);
        Intrinsics.checkExpressionValueIsNotNull(button_quitfillin, "button_quitfillin");
        button_quitfillin.setEnabled(true);
        TextView tx_fillin = (TextView) this.this$0.this$0._$_findCachedViewById(C4946R.id.tx_fillin);
        Intrinsics.checkExpressionValueIsNotNull(tx_fillin, "tx_fillin");
        tx_fillin.setText("等待补位中");
        return Unit.INSTANCE;
    }
}
