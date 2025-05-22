package com.pudutech.peanut.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.ttsvoices.TtsCustomPkManager;
import com.pudutech.peanut.robot_ui.ttsvoices.TtsPkManager;
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
/* compiled from: TtsVoiceGenVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.viewmodel.TtsVoiceGenVm$check$1", m3970f = "TtsVoiceGenVm.kt", m3971i = {0, 1, 1}, m3972l = {20, 21}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "pkEmpty"}, m3975s = {"L$0", "L$0", "Z$0"})
/* loaded from: classes5.dex */
public final class TtsVoiceGenVm$check$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    boolean Z$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7070p$;
    final /* synthetic */ TtsVoiceGenVm this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TtsVoiceGenVm$check$1(TtsVoiceGenVm ttsVoiceGenVm, Continuation continuation) {
        super(2, continuation);
        this.this$0 = ttsVoiceGenVm;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TtsVoiceGenVm$check$1 ttsVoiceGenVm$check$1 = new TtsVoiceGenVm$check$1(this.this$0, completion);
        ttsVoiceGenVm$check$1.f7070p$ = (CoroutineScope) obj;
        return ttsVoiceGenVm$check$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TtsVoiceGenVm$check$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z2 = false;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7070p$;
            Pdlog.m3274e("TtsVoiceGenVm", "check");
            TtsPkManager ttsPkManager = TtsPkManager.INSTANCE;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = ttsPkManager.checkVoice(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    z = this.Z$0;
                    ResultKt.throwOnFailure(obj);
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    Pdlog.m3274e("TtsVoiceGenVm", Boxing.boxBoolean(z), Boxing.boxBoolean(booleanValue));
                    MutableLiveData<Boolean> needDown = this.this$0.getNeedDown();
                    if (z && booleanValue) {
                        z2 = true;
                    }
                    needDown.postValue(Boxing.boxBoolean(z2));
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        boolean booleanValue2 = ((Boolean) obj).booleanValue();
        TtsCustomPkManager ttsCustomPkManager = TtsCustomPkManager.INSTANCE;
        this.L$0 = coroutineScope;
        this.Z$0 = booleanValue2;
        this.label = 2;
        Object checkVoice = ttsCustomPkManager.checkVoice(this);
        if (checkVoice == coroutine_suspended) {
            return coroutine_suspended;
        }
        z = booleanValue2;
        obj = checkVoice;
        boolean booleanValue3 = ((Boolean) obj).booleanValue();
        Pdlog.m3274e("TtsVoiceGenVm", Boxing.boxBoolean(z), Boxing.boxBoolean(booleanValue3));
        MutableLiveData<Boolean> needDown2 = this.this$0.getNeedDown();
        if (z) {
            z2 = true;
        }
        needDown2.postValue(Boxing.boxBoolean(z2));
        return Unit.INSTANCE;
    }
}
