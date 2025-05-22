package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HeyPuduWakeViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.viewmodel.HeyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1", m3970f = "HeyPuduWakeViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class HeyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $data;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7061p$;
    final /* synthetic */ HeyPuduWakeViewModel$mIVoiceReponseTextListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1(HeyPuduWakeViewModel$mIVoiceReponseTextListener$1 heyPuduWakeViewModel$mIVoiceReponseTextListener$1, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = heyPuduWakeViewModel$mIVoiceReponseTextListener$1;
        this.$data = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HeyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1 heyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1 = new HeyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1(this.this$0, this.$data, completion);
        heyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1.f7061p$ = (CoroutineScope) obj;
        return heyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HeyPuduWakeViewModel$mIVoiceReponseTextListener$1$wakeup$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7061p$;
        String str2 = this.$data;
        if (!(str2 == null || str2.length() == 0)) {
            try {
                JSONObject jSONObject = new JSONObject(this.$data);
                if (!jSONObject.has("angle")) {
                    return Unit.INSTANCE;
                }
                int i = jSONObject.getInt("angle");
                if (i > 180) {
                    i -= 360;
                }
                str = this.this$0.this$0.TAG;
                Pdlog.m3273d(str, "onWakeChanged() angle=" + i);
                MutableLiveData<Double> wakeUpAngle = this.this$0.this$0.getWakeUpAngle();
                if (wakeUpAngle != null) {
                    wakeUpAngle.postValue(Boxing.boxDouble(Math.toRadians(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return Unit.INSTANCE;
    }
}
