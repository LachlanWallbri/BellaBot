package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.charge.ChargeMessageCenter;
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
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ChargeSetActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.ChargeSetActivity$startVersionReq$1", m3970f = "ChargeSetActivity.kt", m3971i = {0, 0}, m3972l = {297}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "count"}, m3975s = {"L$0", "I$0"})
/* loaded from: classes5.dex */
public final class ChargeSetActivity$startVersionReq$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5607p$;
    final /* synthetic */ ChargeSetActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeSetActivity$startVersionReq$1(ChargeSetActivity chargeSetActivity, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = chargeSetActivity;
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ChargeSetActivity$startVersionReq$1 chargeSetActivity$startVersionReq$1 = new ChargeSetActivity$startVersionReq$1(this.this$0, this.$mac, completion);
        chargeSetActivity$startVersionReq$1.f5607p$ = (CoroutineScope) obj;
        return chargeSetActivity$startVersionReq$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChargeSetActivity$startVersionReq$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x003d -> B:5:0x0040). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        CoroutineScope coroutineScope;
        ChargeSetActivity$startVersionReq$1 chargeSetActivity$startVersionReq$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            i = 0;
            coroutineScope = this.f5607p$;
            chargeSetActivity$startVersionReq$1 = this;
            if (chargeSetActivity$startVersionReq$1.this$0.getIsRecMessage()) {
            }
            chargeSetActivity$startVersionReq$1.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$startVersionReq$1.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (ChargeSetActivity$startVersionReq$1.this.this$0.getIsRecMessage()) {
                        ChargeSetActivity$startVersionReq$1.this.this$0.addChargePile(ChargeSetActivity$startVersionReq$1.this.$mac);
                    } else {
                        TextView tv_docker = (TextView) ChargeSetActivity$startVersionReq$1.this.this$0._$_findCachedViewById(C4946R.id.tv_docker);
                        Intrinsics.checkExpressionValueIsNotNull(tv_docker, "tv_docker");
                        tv_docker.setText("bluetooth not reply data");
                    }
                    BluetoothBleHelper.INSTANCE.disconnectDevice(ChargeSetActivity$startVersionReq$1.this.$mac);
                }
            });
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            chargeSetActivity$startVersionReq$1 = this;
            ChargeMessageCenter.INSTANCE.sendVersionReq(chargeSetActivity$startVersionReq$1.$mac);
            i++;
            if (chargeSetActivity$startVersionReq$1.this$0.getIsRecMessage() && i < 5) {
                chargeSetActivity$startVersionReq$1.L$0 = coroutineScope;
                chargeSetActivity$startVersionReq$1.I$0 = i;
                chargeSetActivity$startVersionReq$1.label = 1;
                if (DelayKt.delay(1000L, chargeSetActivity$startVersionReq$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                ChargeMessageCenter.INSTANCE.sendVersionReq(chargeSetActivity$startVersionReq$1.$mac);
                i++;
                if (chargeSetActivity$startVersionReq$1.this$0.getIsRecMessage()) {
                }
                chargeSetActivity$startVersionReq$1.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$startVersionReq$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        if (ChargeSetActivity$startVersionReq$1.this.this$0.getIsRecMessage()) {
                            ChargeSetActivity$startVersionReq$1.this.this$0.addChargePile(ChargeSetActivity$startVersionReq$1.this.$mac);
                        } else {
                            TextView tv_docker = (TextView) ChargeSetActivity$startVersionReq$1.this.this$0._$_findCachedViewById(C4946R.id.tv_docker);
                            Intrinsics.checkExpressionValueIsNotNull(tv_docker, "tv_docker");
                            tv_docker.setText("bluetooth not reply data");
                        }
                        BluetoothBleHelper.INSTANCE.disconnectDevice(ChargeSetActivity$startVersionReq$1.this.$mac);
                    }
                });
                return Unit.INSTANCE;
            }
            chargeSetActivity$startVersionReq$1.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.activity.ChargeSetActivity$startVersionReq$1.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (ChargeSetActivity$startVersionReq$1.this.this$0.getIsRecMessage()) {
                        ChargeSetActivity$startVersionReq$1.this.this$0.addChargePile(ChargeSetActivity$startVersionReq$1.this.$mac);
                    } else {
                        TextView tv_docker = (TextView) ChargeSetActivity$startVersionReq$1.this.this$0._$_findCachedViewById(C4946R.id.tv_docker);
                        Intrinsics.checkExpressionValueIsNotNull(tv_docker, "tv_docker");
                        tv_docker.setText("bluetooth not reply data");
                    }
                    BluetoothBleHelper.INSTANCE.disconnectDevice(ChargeSetActivity$startVersionReq$1.this.$mac);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
