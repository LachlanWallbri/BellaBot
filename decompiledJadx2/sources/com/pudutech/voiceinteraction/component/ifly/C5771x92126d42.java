package com.pudutech.voiceinteraction.component.ifly;

import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: IFlyVoiceInteractionKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/voiceinteraction/component/ifly/IFlyVoiceInteractionKit$httpTextForCloudPlatform$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$httpTextForCloudPlatform$$inlined$let$lambda$1 */
/* loaded from: classes7.dex */
public final class C5771x92126d42 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $asw$inlined;
    final /* synthetic */ String $it;
    final /* synthetic */ String $result$inlined;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7579p$;
    final /* synthetic */ IFlyVoiceInteractionKit this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5771x92126d42(String str, Continuation continuation, IFlyVoiceInteractionKit iFlyVoiceInteractionKit, String str2, String str3) {
        super(2, continuation);
        this.$it = str;
        this.this$0 = iFlyVoiceInteractionKit;
        this.$asw$inlined = str2;
        this.$result$inlined = str3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5771x92126d42 c5771x92126d42 = new C5771x92126d42(this.$it, completion, this.this$0, this.$asw$inlined, this.$result$inlined);
        c5771x92126d42.f7579p$ = (CoroutineScope) obj;
        return c5771x92126d42;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5771x92126d42) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7579p$;
            Pdlog.m3273d("IFlyVoiceInteractionKit", "httpText() postCloudPlatform  launch");
            OkHttpUtils okHttpUtils = OkHttpUtils.INSTANCE;
            String str = this.$it;
            String str2 = this.$asw$inlined;
            OkHttpUtils.HttpCallbackInterface httpCallbackInterface = new OkHttpUtils.HttpCallbackInterface() { // from class: com.pudutech.voiceinteraction.component.ifly.IFlyVoiceInteractionKit$httpTextForCloudPlatform$$inlined$let$lambda$1.1
                @Override // com.pudutech.voiceinteraction.component.utils.OkHttpUtils.HttpCallbackInterface
                public void onSuccessCallback(String str3, String str4, int i2) {
                    IFlyVoiceInteractionKit iFlyVoiceInteractionKit = C5771x92126d42.this.this$0;
                    if (str3 == null && (str3 = C5771x92126d42.this.$result$inlined) == null) {
                        str3 = "";
                    }
                    iFlyVoiceInteractionKit.finishAnswer = str3;
                    C5771x92126d42.this.this$0.processAnswerAndSkill();
                }

                @Override // com.pudutech.voiceinteraction.component.utils.OkHttpUtils.HttpCallbackInterface
                public void onErrorCallback(String error) {
                    Intrinsics.checkParameterIsNotNull(error, "error");
                    IFlyVoiceInteractionKit iFlyVoiceInteractionKit = C5771x92126d42.this.this$0;
                    String str3 = C5771x92126d42.this.$result$inlined;
                    if (str3 == null) {
                        str3 = "";
                    }
                    iFlyVoiceInteractionKit.finishAnswer = str3;
                    C5771x92126d42.this.this$0.processAnswerAndSkill();
                }
            };
            this.L$0 = coroutineScope;
            this.label = 1;
            if (okHttpUtils.postCloudPlatform(str, str2, httpCallbackInterface, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
