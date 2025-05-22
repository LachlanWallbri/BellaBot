package com.pudutech.bumblebee.presenter.utils;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.C4029R;
import com.pudutech.bumblebee.presenter.utils.SoundPoolVoiceUtil;
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

/* compiled from: SoundPoolVoiceUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.utils.SoundPoolVoiceUtil$loadPoolRes$1", m3970f = "SoundPoolVoiceUtil.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class SoundPoolVoiceUtil$loadPoolRes$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4722p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SoundPoolVoiceUtil$loadPoolRes$1(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SoundPoolVoiceUtil$loadPoolRes$1 soundPoolVoiceUtil$loadPoolRes$1 = new SoundPoolVoiceUtil$loadPoolRes$1(this.$context, completion);
        soundPoolVoiceUtil$loadPoolRes$1.f4722p$ = (CoroutineScope) obj;
        return soundPoolVoiceUtil$loadPoolRes$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SoundPoolVoiceUtil$loadPoolRes$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        SoundPoolHelper soundPoolHelper;
        SoundPoolHelper soundPoolHelper2;
        SoundPoolHelper soundPoolHelper3;
        SoundPoolHelper soundPoolHelper4;
        String str2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4722p$;
        SoundPoolVoiceUtil soundPoolVoiceUtil = SoundPoolVoiceUtil.INSTANCE;
        str = SoundPoolVoiceUtil.TAG;
        Pdlog.m3273d(str, "load start");
        SoundPoolVoiceUtil soundPoolVoiceUtil2 = SoundPoolVoiceUtil.INSTANCE;
        soundPoolHelper = SoundPoolVoiceUtil.soundPoolHelper;
        if (soundPoolHelper == null) {
            Intrinsics.throwNpe();
        }
        soundPoolHelper.load(this.$context, SoundPoolVoiceUtil.Voice.CLICK_1.getVoiceName(), C4029R.raw.btn_click_1);
        SoundPoolVoiceUtil soundPoolVoiceUtil3 = SoundPoolVoiceUtil.INSTANCE;
        soundPoolHelper2 = SoundPoolVoiceUtil.soundPoolHelper;
        if (soundPoolHelper2 == null) {
            Intrinsics.throwNpe();
        }
        soundPoolHelper2.load(this.$context, SoundPoolVoiceUtil.Voice.EMPTY.getVoiceName(), C4029R.raw.empty);
        SoundPoolVoiceUtil soundPoolVoiceUtil4 = SoundPoolVoiceUtil.INSTANCE;
        soundPoolHelper3 = SoundPoolVoiceUtil.soundPoolHelper;
        if (soundPoolHelper3 == null) {
            Intrinsics.throwNpe();
        }
        soundPoolHelper3.load(this.$context, SoundPoolVoiceUtil.Voice.ERROR.getVoiceName(), C4029R.raw.error_tips_1);
        SoundPoolVoiceUtil soundPoolVoiceUtil5 = SoundPoolVoiceUtil.INSTANCE;
        soundPoolHelper4 = SoundPoolVoiceUtil.soundPoolHelper;
        if (soundPoolHelper4 == null) {
            Intrinsics.throwNpe();
        }
        soundPoolHelper4.load(this.$context, SoundPoolVoiceUtil.Voice.SCAN_FAILED.getVoiceName(), C4029R.raw.scan_failed);
        SoundPoolVoiceUtil soundPoolVoiceUtil6 = SoundPoolVoiceUtil.INSTANCE;
        str2 = SoundPoolVoiceUtil.TAG;
        Pdlog.m3273d(str2, "load end");
        return Unit.INSTANCE;
    }
}
