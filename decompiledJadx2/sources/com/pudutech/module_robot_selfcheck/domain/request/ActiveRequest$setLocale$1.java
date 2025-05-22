package com.pudutech.module_robot_selfcheck.domain.request;

import android.content.Context;
import com.pudutech.resources.language.LanguageUtils;
import com.pudutech.resources.language.Option;
import com.pudutech.robot.module.voice.RobotVoicePlayer;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ActiveRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.module_robot_selfcheck.domain.request.ActiveRequest$setLocale$1", m3970f = "ActiveRequest.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class ActiveRequest$setLocale$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ Option $op;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6741p$;
    final /* synthetic */ ActiveRequest this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActiveRequest$setLocale$1(ActiveRequest activeRequest, Context context, Option option, Continuation continuation) {
        super(2, continuation);
        this.this$0 = activeRequest;
        this.$context = context;
        this.$op = option;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ActiveRequest$setLocale$1 activeRequest$setLocale$1 = new ActiveRequest$setLocale$1(this.this$0, this.$context, this.$op, completion);
        activeRequest$setLocale$1.f6741p$ = (CoroutineScope) obj;
        return activeRequest$setLocale$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ActiveRequest$setLocale$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LanguageUtils languageUtils;
        LanguageUtils languageUtils2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6741p$;
        languageUtils = this.this$0.languageUtils;
        if (languageUtils == null) {
            this.this$0.languageUtils = new LanguageUtils(this.$context);
            Unit unit = Unit.INSTANCE;
        }
        languageUtils2 = this.this$0.languageUtils;
        if (languageUtils2 != null) {
            LanguageUtils.setLocale$default(languageUtils2, this.$context, this.$op, false, 4, null);
        }
        RobotVoicePlayer.INSTANCE.switchDefResources(this.$op.getLocale());
        this.this$0.getSetLanguageLD().postValue(Boxing.boxBoolean(true));
        return Unit.INSTANCE;
    }
}
