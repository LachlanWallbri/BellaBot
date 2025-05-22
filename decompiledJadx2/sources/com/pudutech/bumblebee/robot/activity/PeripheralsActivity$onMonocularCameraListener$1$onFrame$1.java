package com.pudutech.bumblebee.robot.activity;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.pudutech.bumblebee.robot.C4144R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PeripheralsActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.activity.PeripheralsActivity$onMonocularCameraListener$1$onFrame$1", m3970f = "PeripheralsActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
final class PeripheralsActivity$onMonocularCameraListener$1$onFrame$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bitmap $bmp;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4745p$;
    final /* synthetic */ PeripheralsActivity$onMonocularCameraListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeripheralsActivity$onMonocularCameraListener$1$onFrame$1(PeripheralsActivity$onMonocularCameraListener$1 peripheralsActivity$onMonocularCameraListener$1, Bitmap bitmap, Continuation continuation) {
        super(2, continuation);
        this.this$0 = peripheralsActivity$onMonocularCameraListener$1;
        this.$bmp = bitmap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PeripheralsActivity$onMonocularCameraListener$1$onFrame$1 peripheralsActivity$onMonocularCameraListener$1$onFrame$1 = new PeripheralsActivity$onMonocularCameraListener$1$onFrame$1(this.this$0, this.$bmp, completion);
        peripheralsActivity$onMonocularCameraListener$1$onFrame$1.f4745p$ = (CoroutineScope) obj;
        return peripheralsActivity$onMonocularCameraListener$1$onFrame$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PeripheralsActivity$onMonocularCameraListener$1$onFrame$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ImageView imageView = (ImageView) this.this$0.this$0._$_findCachedViewById(C4144R.id.imageView);
            Intrinsics.checkExpressionValueIsNotNull(imageView, "imageView");
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            if (layoutParams == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            Bitmap bmp = this.$bmp;
            Intrinsics.checkExpressionValueIsNotNull(bmp, "bmp");
            layoutParams2.width = bmp.getWidth();
            Bitmap bmp2 = this.$bmp;
            Intrinsics.checkExpressionValueIsNotNull(bmp2, "bmp");
            layoutParams2.height = bmp2.getHeight();
            ImageView imageView2 = (ImageView) this.this$0.this$0._$_findCachedViewById(C4144R.id.imageView);
            Intrinsics.checkExpressionValueIsNotNull(imageView2, "imageView");
            imageView2.setLayoutParams(layoutParams2);
            ((ImageView) this.this$0.this$0._$_findCachedViewById(C4144R.id.imageView)).setImageBitmap(this.$bmp);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
