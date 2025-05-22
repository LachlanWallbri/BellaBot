package com.pudutech.voiceinteraction.component.utils;

import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: OkHttpUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086@"}, m3961d2 = {"postCloudPlatform", "", "mac", "", "text", "callback", "Lcom/pudutech/voiceinteraction/component/utils/OkHttpUtils$HttpCallbackInterface;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.voiceinteraction.component.utils.OkHttpUtils", m3970f = "OkHttpUtils.kt", m3971i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3}, m3972l = {300, 304, 313, 322}, m3973m = "postCloudPlatform", m3974n = {"this", "mac", "text", "callback", "aiVoiceService", "aiCloudPlatformReq", "$this$runCatching", "this", "mac", "text", "callback", "aiVoiceService", "aiCloudPlatformReq", "it", "answer", "this", "mac", "text", "callback", "aiVoiceService", "aiCloudPlatformReq", "it", C3898x.f4338g, "this", "mac", "text", "callback", "aiVoiceService", "aiCloudPlatformReq", "it"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7"})
/* loaded from: classes7.dex */
public final class OkHttpUtils$postCloudPlatform$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OkHttpUtils this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkHttpUtils$postCloudPlatform$1(OkHttpUtils okHttpUtils, Continuation continuation) {
        super(continuation);
        this.this$0 = okHttpUtils;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.postCloudPlatform(null, null, null, this);
    }
}
