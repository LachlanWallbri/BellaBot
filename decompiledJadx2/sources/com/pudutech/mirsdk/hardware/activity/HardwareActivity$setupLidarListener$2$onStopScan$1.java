package com.pudutech.mirsdk.hardware.activity;

import android.widget.TextView;
import com.pudutech.mirsdk.hardware.library.C5193R;
import com.pudutech.mirsdk.hardware.serialize.LidarStopReason;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$setupLidarListener$2$onStopScan$1", m3970f = "HardwareActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes2.dex */
final class HardwareActivity$setupLidarListener$2$onStopScan$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LidarStopReason $p0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5976p$;
    final /* synthetic */ HardwareActivity$setupLidarListener$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareActivity$setupLidarListener$2$onStopScan$1(HardwareActivity$setupLidarListener$2 hardwareActivity$setupLidarListener$2, LidarStopReason lidarStopReason, Continuation continuation) {
        super(2, continuation);
        this.this$0 = hardwareActivity$setupLidarListener$2;
        this.$p0 = lidarStopReason;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareActivity$setupLidarListener$2$onStopScan$1 hardwareActivity$setupLidarListener$2$onStopScan$1 = new HardwareActivity$setupLidarListener$2$onStopScan$1(this.this$0, this.$p0, completion);
        hardwareActivity$setupLidarListener$2$onStopScan$1.f5976p$ = (CoroutineScope) obj;
        return hardwareActivity$setupLidarListener$2$onStopScan$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareActivity$setupLidarListener$2$onStopScan$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        TextView textView_LidarState = (TextView) this.this$0.this$0._$_findCachedViewById(C5193R.id.textView_LidarState);
        Intrinsics.checkExpressionValueIsNotNull(textView_LidarState, "textView_LidarState");
        textView_LidarState.setText("onStopScan reason:" + this.$p0);
        return Unit.INSTANCE;
    }
}
