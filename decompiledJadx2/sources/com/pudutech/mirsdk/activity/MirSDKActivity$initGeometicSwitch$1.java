package com.pudutech.mirsdk.activity;

import android.content.Context;
import android.widget.Switch;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdk.function.C4946R;
import com.pudutech.mirsdk.mapify.util.CamerConfigHelper;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$initGeometicSwitch$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class MirSDKActivity$initGeometicSwitch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5637p$;
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MirSDKActivity$initGeometicSwitch$1(MirSDKActivity mirSDKActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$initGeometicSwitch$1 mirSDKActivity$initGeometicSwitch$1 = new MirSDKActivity$initGeometicSwitch$1(this.this$0, completion);
        mirSDKActivity$initGeometicSwitch$1.f5637p$ = (CoroutineScope) obj;
        return mirSDKActivity$initGeometicSwitch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$initGeometicSwitch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5637p$;
        try {
            TextView geomagnetic_left = (TextView) this.this$0._$_findCachedViewById(C4946R.id.geomagnetic_left);
            Intrinsics.checkExpressionValueIsNotNull(geomagnetic_left, "geomagnetic_left");
            geomagnetic_left.setText("left:" + CamerConfigHelper.INSTANCE.getMagic_sensor_left());
            TextView geomagnetic_right = (TextView) this.this$0._$_findCachedViewById(C4946R.id.geomagnetic_right);
            Intrinsics.checkExpressionValueIsNotNull(geomagnetic_right, "geomagnetic_right");
            geomagnetic_right.setText("right:" + CamerConfigHelper.INSTANCE.getMagic_sensor_right());
            boolean z = SpUtils.get((Context) this.this$0, "mirhardware", "magic_enable", false);
            Switch geomagneticSwitch = (Switch) this.this$0._$_findCachedViewById(C4946R.id.geomagneticSwitch);
            Intrinsics.checkExpressionValueIsNotNull(geomagneticSwitch, "geomagneticSwitch");
            geomagneticSwitch.setChecked(z);
        } catch (Exception unused) {
            Switch geomagneticSwitch2 = (Switch) this.this$0._$_findCachedViewById(C4946R.id.geomagneticSwitch);
            Intrinsics.checkExpressionValueIsNotNull(geomagneticSwitch2, "geomagneticSwitch");
            geomagneticSwitch2.setChecked(false);
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "magneticConfig parse fail");
        }
        return Unit.INSTANCE;
    }
}
