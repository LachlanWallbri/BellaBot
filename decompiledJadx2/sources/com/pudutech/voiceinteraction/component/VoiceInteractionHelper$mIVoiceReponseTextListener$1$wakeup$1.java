package com.pudutech.voiceinteraction.component;

import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.listener.IVoiceReponseTextListener;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.VoiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1", m3970f = "VoiceInteractionHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class VoiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $data;
    final /* synthetic */ int $random;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7546p$;
    final /* synthetic */ VoiceInteractionHelper$mIVoiceReponseTextListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1(VoiceInteractionHelper$mIVoiceReponseTextListener$1 voiceInteractionHelper$mIVoiceReponseTextListener$1, int i, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = voiceInteractionHelper$mIVoiceReponseTextListener$1;
        this.$random = i;
        this.$data = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        VoiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1 voiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1 = new VoiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1(this.this$0, this.$random, this.$data, completion);
        voiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1.f7546p$ = (CoroutineScope) obj;
        return voiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VoiceInteractionHelper$mIVoiceReponseTextListener$1$wakeup$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p;
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p2;
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7546p$;
        if (VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0.this$0) != null) {
            VoiceInteractionDialog access$getVoiceInteractionDialog$p = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0.this$0);
            Boolean boxBoolean = access$getVoiceInteractionDialog$p != null ? Boxing.boxBoolean(access$getVoiceInteractionDialog$p.isShowing()) : null;
            if (boxBoolean == null) {
                Intrinsics.throwNpe();
            }
            if (boxBoolean.booleanValue()) {
                Pdlog.m3273d(VoiceInteractionHelper.TAG, "reset");
                VoiceInteractionDialog access$getVoiceInteractionDialog$p2 = VoiceInteractionHelper.access$getVoiceInteractionDialog$p(this.this$0.this$0);
                if (access$getVoiceInteractionDialog$p2 != null) {
                    access$getVoiceInteractionDialog$p2.reset(this.$random);
                }
                access$getIVoiceReponseTextListener$p = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0.this$0);
                if (access$getIVoiceReponseTextListener$p != null) {
                    access$getIVoiceReponseTextListener$p.volumeChange(true);
                }
                access$getIVoiceReponseTextListener$p2 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0.this$0);
                if (access$getIVoiceReponseTextListener$p2 != null) {
                    access$getIVoiceReponseTextListener$p2.reWakeup(false);
                }
                str = this.$data;
                if (!(str != null || str.length() == 0)) {
                    try {
                        JSONObject jSONObject = new JSONObject(this.$data);
                        if (!jSONObject.has("angle")) {
                            return Unit.INSTANCE;
                        }
                        int i = jSONObject.getInt("angle");
                        if (i > 180) {
                            i -= 360;
                        }
                        Pdlog.m3273d(VoiceInteractionHelper.TAG, "onWakeChanged() angle=" + i);
                        Function1 access$getOnWakeAngleChangedListener$p = VoiceInteractionHelper.access$getOnWakeAngleChangedListener$p(this.this$0.this$0);
                        if (access$getOnWakeAngleChangedListener$p != null) {
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return Unit.INSTANCE;
            }
        }
        Pdlog.m3273d(VoiceInteractionHelper.TAG, "show");
        IVoiceReponseTextListener access$getIVoiceReponseTextListener$p3 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0.this$0);
        if (access$getIVoiceReponseTextListener$p3 != null) {
            access$getIVoiceReponseTextListener$p3.showDialog(this.$random);
        }
        access$getIVoiceReponseTextListener$p = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0.this$0);
        if (access$getIVoiceReponseTextListener$p != null) {
        }
        access$getIVoiceReponseTextListener$p2 = VoiceInteractionHelper.access$getIVoiceReponseTextListener$p(this.this$0.this$0);
        if (access$getIVoiceReponseTextListener$p2 != null) {
        }
        str = this.$data;
        if (!(str != null || str.length() == 0)) {
        }
        return Unit.INSTANCE;
    }
}
