package com.pudu.library.loracall.utils;

import com.pudutech.tts_sdk.TtsConfig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoraResetUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086@"}, m3961d2 = {"loraReset", "", "pathName", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudu.library.loracall.utils.LoraResetUtils", m3970f = "LoraResetUtils.kt", m3971i = {0, 0, 0}, m3972l = {24}, m3973m = "loraReset", m3974n = {"this", "pathName", TtsConfig.CACHE_VERSION}, m3975s = {"L$0", "L$1", "Z$0"})
/* loaded from: classes4.dex */
public final class LoraResetUtils$loraReset$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LoraResetUtils this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoraResetUtils$loraReset$1(LoraResetUtils loraResetUtils, Continuation continuation) {
        super(continuation);
        this.this$0 = loraResetUtils;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.loraReset(null, this);
    }
}
