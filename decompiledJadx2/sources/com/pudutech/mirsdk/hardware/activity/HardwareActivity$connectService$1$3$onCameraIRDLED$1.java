package com.pudutech.mirsdk.hardware.activity;

import android.widget.Button;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.amazonaws.services.p048s3.model.BucketVersioningConfiguration;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$connectService$1$3$onCameraIRDLED$1", m3970f = "HardwareActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
final class HardwareActivity$connectService$1$3$onCameraIRDLED$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $p0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5947p$;
    final /* synthetic */ HardwareActivity$connectService$1.BinderC50833 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareActivity$connectService$1$3$onCameraIRDLED$1(HardwareActivity$connectService$1.BinderC50833 binderC50833, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = binderC50833;
        this.$p0 = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareActivity$connectService$1$3$onCameraIRDLED$1 hardwareActivity$connectService$1$3$onCameraIRDLED$1 = new HardwareActivity$connectService$1$3$onCameraIRDLED$1(this.this$0, this.$p0, completion);
        hardwareActivity$connectService$1$3$onCameraIRDLED$1.f5947p$ = (CoroutineScope) obj;
        return hardwareActivity$connectService$1$3$onCameraIRDLED$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareActivity$connectService$1$3$onCameraIRDLED$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (this.$p0) {
            HardwareActivity hardwareActivity = HardwareActivity$connectService$1.this.this$0;
            CardView cardView_IRLight = (CardView) HardwareActivity$connectService$1.this.this$0._$_findCachedViewById(C5193R.id.cardView_IRLight);
            Intrinsics.checkExpressionValueIsNotNull(cardView_IRLight, "cardView_IRLight");
            hardwareActivity.lightCardview(cardView_IRLight, true);
            TextView textView_IRLight = (TextView) HardwareActivity$connectService$1.this.this$0._$_findCachedViewById(C5193R.id.textView_IRLight);
            Intrinsics.checkExpressionValueIsNotNull(textView_IRLight, "textView_IRLight");
            textView_IRLight.setText("On");
        } else {
            HardwareActivity hardwareActivity2 = HardwareActivity$connectService$1.this.this$0;
            CardView cardView_IRLight2 = (CardView) HardwareActivity$connectService$1.this.this$0._$_findCachedViewById(C5193R.id.cardView_IRLight);
            Intrinsics.checkExpressionValueIsNotNull(cardView_IRLight2, "cardView_IRLight");
            hardwareActivity2.lightCardview(cardView_IRLight2, false);
            TextView textView_IRLight2 = (TextView) HardwareActivity$connectService$1.this.this$0._$_findCachedViewById(C5193R.id.textView_IRLight);
            Intrinsics.checkExpressionValueIsNotNull(textView_IRLight2, "textView_IRLight");
            textView_IRLight2.setText(BucketVersioningConfiguration.OFF);
        }
        HardwareActivity$connectService$1.this.this$0.IRLightOn = this.$p0;
        z = HardwareActivity$connectService$1.this.this$0.IRLightOn;
        if (z) {
            Button button_IRLight = (Button) HardwareActivity$connectService$1.this.this$0._$_findCachedViewById(C5193R.id.button_IRLight);
            Intrinsics.checkExpressionValueIsNotNull(button_IRLight, "button_IRLight");
            button_IRLight.setText(BucketVersioningConfiguration.OFF);
        } else {
            Button button_IRLight2 = (Button) HardwareActivity$connectService$1.this.this$0._$_findCachedViewById(C5193R.id.button_IRLight);
            Intrinsics.checkExpressionValueIsNotNull(button_IRLight2, "button_IRLight");
            button_IRLight2.setText("On");
        }
        return Unit.INSTANCE;
    }
}
