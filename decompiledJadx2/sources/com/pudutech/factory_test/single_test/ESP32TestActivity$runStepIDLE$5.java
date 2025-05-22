package com.pudutech.factory_test.single_test;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.single_test.ESP32TestActivity;
import com.pudutech.factory_test.test_pack.TestStatus;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ESP32TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.ESP32TestActivity$runStepIDLE$5", m3970f = "ESP32TestActivity.kt", m3971i = {0}, m3972l = {135}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class ESP32TestActivity$runStepIDLE$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5175p$;
    final /* synthetic */ ESP32TestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESP32TestActivity$runStepIDLE$5(ESP32TestActivity eSP32TestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = eSP32TestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ESP32TestActivity$runStepIDLE$5 eSP32TestActivity$runStepIDLE$5 = new ESP32TestActivity$runStepIDLE$5(this.this$0, completion);
        eSP32TestActivity$runStepIDLE$5.f5175p$ = (CoroutineScope) obj;
        return eSP32TestActivity$runStepIDLE$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ESP32TestActivity$runStepIDLE$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5175p$;
            AppCompatTextView tvGuide = (AppCompatTextView) this.this$0._$_findCachedViewById(C4491R.id.tvGuide);
            Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
            tvGuide.setText("初始化ESP32中，请稍等");
            ESP32TestActivity eSP32TestActivity = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = eSP32TestActivity.setESP32(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (!((Boolean) obj).booleanValue()) {
            AppCompatTextView tvGuide2 = (AppCompatTextView) this.this$0._$_findCachedViewById(C4491R.id.tvGuide);
            Intrinsics.checkExpressionValueIsNotNull(tvGuide2, "tvGuide");
            tvGuide2.setText("初始化ESP32失败，请退出重进该测试项尝试");
            ((LinearLayoutCompat) this.this$0._$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
            ESP32TestActivity eSP32TestActivity2 = this.this$0;
            eSP32TestActivity2.showKeyInfo(eSP32TestActivity2.toStr(eSP32TestActivity2.getKeyInfo()));
            return Unit.INSTANCE;
        }
        AppCompatTextView tvGuide3 = (AppCompatTextView) this.this$0._$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide3, "tvGuide");
        tvGuide3.setText("调度通信测试：\n该测试需要另外一台机器作为服务端");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) this.this$0._$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this.this$0, CollectionsKt.arrayListOf("作为服务端", "开始测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepIDLE$5.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESP32TestActivity$runStepIDLE$5.this.this$0.setStep(ESP32TestActivity.Step.SERVER);
                ESP32TestActivity$runStepIDLE$5.this.this$0.FSM();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.ESP32TestActivity$runStepIDLE$5.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESP32TestActivity$runStepIDLE$5.this.this$0.getMTestItem().setStatus(TestStatus.TESTING);
                ESP32TestActivity$runStepIDLE$5.this.this$0.setStep(ESP32TestActivity.Step.CHECKING);
                ESP32TestActivity$runStepIDLE$5.this.this$0.FSM();
            }
        });
        return Unit.INSTANCE;
    }
}
