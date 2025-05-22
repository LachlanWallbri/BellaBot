package com.pudutech.voiceinteraction.component.utils;

import com.pudutech.pd_network.report.utils.GsonUtils;
import com.pudutech.voiceinteraction.component.utils.OkHttpUtils;
import java.util.Map;
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
/* compiled from: OkHttpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$CloudSkillRespData;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.utils.OkHttpUtils$post$2$test$1", m3970f = "OkHttpUtils.kt", m3971i = {0}, m3972l = {134}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes7.dex */
public final class OkHttpUtils$post$2$test$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super OkHttpUtils.CloudSkillRespData>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7588p$;
    final /* synthetic */ OkHttpUtils$post$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkHttpUtils$post$2$test$1(OkHttpUtils$post$2 okHttpUtils$post$2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = okHttpUtils$post$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        OkHttpUtils$post$2$test$1 okHttpUtils$post$2$test$1 = new OkHttpUtils$post$2$test$1(this.this$0, completion);
        okHttpUtils$post$2$test$1.f7588p$ = (CoroutineScope) obj;
        return okHttpUtils$post$2$test$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super OkHttpUtils.CloudSkillRespData> continuation) {
        return ((OkHttpUtils$post$2$test$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7588p$;
            OkHttpUtils.AiVoiceService aiVoiceService = this.this$0.$avoiceService;
            Map<String, Object> gsonToMaps = GsonUtils.gsonToMaps(this.this$0.$jsonBuild.toString());
            Intrinsics.checkExpressionValueIsNotNull(gsonToMaps, "GsonUtils.gsonToMaps(jsonBuild.toString())");
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = aiVoiceService.post(gsonToMaps, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        OkHttpUtils.CloudSkillResp cloudSkillResp = (OkHttpUtils.CloudSkillResp) obj;
        if (cloudSkillResp != null) {
            return cloudSkillResp.getData();
        }
        return null;
    }
}
