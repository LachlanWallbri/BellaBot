package com.pudutech.mirsdk.activity;

import android.widget.EditText;
import com.pudutech.mirsdk.config.SDKConfig;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$initFallLevelListener$2", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class MirSDKActivity$initFallLevelListener$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5636p$;
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$initFallLevelListener$2(MirSDKActivity mirSDKActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$initFallLevelListener$2 mirSDKActivity$initFallLevelListener$2 = new MirSDKActivity$initFallLevelListener$2(this.this$0, completion);
        mirSDKActivity$initFallLevelListener$2.f5636p$ = (CoroutineScope) obj;
        return mirSDKActivity$initFallLevelListener$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$initFallLevelListener$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5636p$;
        float f = SDKConfig.INSTANCE.getPreferences().getFloat("speedLimit", 0.6f);
        String string = SDKConfig.INSTANCE.getPreferences().getString("distancelimit", "5000");
        String string2 = SDKConfig.INSTANCE.getPreferences().getString("distancebrake", "5000");
        ((EditText) this.this$0._$_findCachedViewById(C4946R.id.speed_limit)).setText(String.valueOf(f));
        ((EditText) this.this$0._$_findCachedViewById(C4946R.id.distance_limit)).setText(string.toString());
        ((EditText) this.this$0._$_findCachedViewById(C4946R.id.distance_brake)).setText(string2.toString());
        float f2 = SDKConfig.INSTANCE.getPreferences().getFloat("inBrakeDist", 1.0f);
        float f3 = SDKConfig.INSTANCE.getPreferences().getFloat("followLineDist", 1.5f);
        float f4 = SDKConfig.INSTANCE.getPreferences().getFloat("slowDownDist", 2.0f);
        float f5 = SDKConfig.INSTANCE.getPreferences().getFloat("outBrakeDist", 1.2f);
        ((EditText) this.this$0._$_findCachedViewById(C4946R.id.in_brake_dist)).setText(String.valueOf(f2));
        ((EditText) this.this$0._$_findCachedViewById(C4946R.id.follow_line_dist)).setText(String.valueOf(f3));
        ((EditText) this.this$0._$_findCachedViewById(C4946R.id.slow_down_dist)).setText(String.valueOf(f4));
        ((EditText) this.this$0._$_findCachedViewById(C4946R.id.out_brake_dist)).setText(String.valueOf(f5));
        return Unit.INSTANCE;
    }
}
