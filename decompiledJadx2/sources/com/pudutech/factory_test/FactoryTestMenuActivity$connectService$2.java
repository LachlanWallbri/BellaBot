package com.pudutech.factory_test;

import android.widget.TextView;
import android.widget.Toast;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.FactoryTestMenuActivity;
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
/* compiled from: FactoryTestMenuActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.FactoryTestMenuActivity$connectService$2", m3970f = "FactoryTestMenuActivity.kt", m3971i = {0}, m3972l = {436}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class FactoryTestMenuActivity$connectService$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5133p$;
    final /* synthetic */ FactoryTestMenuActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FactoryTestMenuActivity$connectService$2(FactoryTestMenuActivity factoryTestMenuActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = factoryTestMenuActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FactoryTestMenuActivity$connectService$2 factoryTestMenuActivity$connectService$2 = new FactoryTestMenuActivity$connectService$2(this.this$0, completion);
        factoryTestMenuActivity$connectService$2.f5133p$ = (CoroutineScope) obj;
        return factoryTestMenuActivity$connectService$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FactoryTestMenuActivity$connectService$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f5133p$;
            this.label = 1;
            if (DelayKt.delay(60000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (ServiceConnectionKt.getOnHardwareOpenDone() != null) {
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.FactoryTestMenuActivity$connectService$2.1
                @Override // java.lang.Runnable
                public final void run() {
                    String str;
                    Toast.makeText(FactoryTestMenuActivity$connectService$2.this.this$0, "初始化超时，可能有崩溃", 1).show();
                    TextView tvHardwareOpen = (TextView) FactoryTestMenuActivity$connectService$2.this.this$0._$_findCachedViewById(C4491R.id.tvHardwareOpen);
                    Intrinsics.checkExpressionValueIsNotNull(tvHardwareOpen, "tvHardwareOpen");
                    tvHardwareOpen.setText("初始化超时");
                    for (FactoryTestMenuActivity.InitState initState : FactoryTestMenuActivity$connectService$2.this.this$0.getStepStateList()) {
                        TextView tvHardwareOpen2 = (TextView) FactoryTestMenuActivity$connectService$2.this.this$0._$_findCachedViewById(C4491R.id.tvHardwareOpen);
                        Intrinsics.checkExpressionValueIsNotNull(tvHardwareOpen2, "tvHardwareOpen");
                        TextView tvHardwareOpen3 = (TextView) FactoryTestMenuActivity$connectService$2.this.this$0._$_findCachedViewById(C4491R.id.tvHardwareOpen);
                        Intrinsics.checkExpressionValueIsNotNull(tvHardwareOpen3, "tvHardwareOpen");
                        tvHardwareOpen2.setText(tvHardwareOpen3.getText().toString() + '\n' + initState.getStep() + ' ' + initState.getState() + ' ' + initState.getDescription());
                    }
                    str = FactoryTestMenuActivity$connectService$2.this.this$0.TAG;
                    Pdlog.m3274e(str, "初始化超时");
                }
            });
        }
        return Unit.INSTANCE;
    }
}
