package com.pudutech.bumblebee.robot.activity;

import android.widget.ImageView;
import android.widget.TextView;
import com.pudutech.bumblebee.robot.C4144R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot/activity/PeripheralsActivity$onCreate$9$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
final class PeripheralsActivity$onCreate$9$$special$$inlined$apply$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isChecked$inlined;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4741p$;
    final /* synthetic */ PeripheralsActivity$onCreate$9 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeripheralsActivity$onCreate$9$$special$$inlined$apply$lambda$1(Continuation continuation, PeripheralsActivity$onCreate$9 peripheralsActivity$onCreate$9, boolean z) {
        super(2, continuation);
        this.this$0 = peripheralsActivity$onCreate$9;
        this.$isChecked$inlined = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PeripheralsActivity$onCreate$9$$special$$inlined$apply$lambda$1 peripheralsActivity$onCreate$9$$special$$inlined$apply$lambda$1 = new PeripheralsActivity$onCreate$9$$special$$inlined$apply$lambda$1(completion, this.this$0, this.$isChecked$inlined);
        peripheralsActivity$onCreate$9$$special$$inlined$apply$lambda$1.f4741p$ = (CoroutineScope) obj;
        return peripheralsActivity$onCreate$9$$special$$inlined$apply$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PeripheralsActivity$onCreate$9$$special$$inlined$apply$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f4741p$;
            this.label = 1;
            if (DelayKt.delay(500L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        ((ImageView) this.this$0.this$0._$_findCachedViewById(C4144R.id.imageView)).setImageBitmap(null);
        TextView tv_qrcode_result = (TextView) this.this$0.this$0._$_findCachedViewById(C4144R.id.tv_qrcode_result);
        Intrinsics.checkExpressionValueIsNotNull(tv_qrcode_result, "tv_qrcode_result");
        tv_qrcode_result.setText((CharSequence) null);
        return Unit.INSTANCE;
    }
}
