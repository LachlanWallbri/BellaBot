package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SleepSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u001e\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00040\u00040\u0001*\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m3961d2 = {"<anonymous>", "Lcom/bumptech/glide/request/target/ViewTarget;", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "Landroid/graphics/drawable/Drawable;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot_ui/module/setting/ui/SleepSettingFragment$previewVideo$job$1$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.bumblebee.robot_ui.module.setting.ui.SleepSettingFragment$previewVideo$job$1$invokeSuspend$$inlined$let$lambda$1 */
/* loaded from: classes3.dex */
public final class C4255xba5a59ca extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ViewTarget<ImageView, Drawable>>, Object> {
    final /* synthetic */ Ref.ObjectRef $bm$inlined;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4884p$;
    final /* synthetic */ SleepSettingFragment$previewVideo$job$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4255xba5a59ca(Continuation continuation, SleepSettingFragment$previewVideo$job$1 sleepSettingFragment$previewVideo$job$1, Ref.ObjectRef objectRef) {
        super(2, continuation);
        this.this$0 = sleepSettingFragment$previewVideo$job$1;
        this.$bm$inlined = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4255xba5a59ca c4255xba5a59ca = new C4255xba5a59ca(completion, this.this$0, this.$bm$inlined);
        c4255xba5a59ca.f4884p$ = (CoroutineScope) obj;
        return c4255xba5a59ca;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ViewTarget<ImageView, Drawable>> continuation) {
        return ((C4255xba5a59ca) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4884p$;
        return Glide.with(this.this$0.this$0).load((Bitmap) this.$bm$inlined.element).into(this.this$0.$target);
    }
}
