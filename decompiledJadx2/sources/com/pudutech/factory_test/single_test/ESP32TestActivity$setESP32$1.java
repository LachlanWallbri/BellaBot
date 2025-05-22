package com.pudutech.factory_test.single_test;

import com.iflytek.cloud.SpeechUtility;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ESP32TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086@"}, m3961d2 = {"setESP32", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.ESP32TestActivity", m3970f = "ESP32TestActivity.kt", m3971i = {0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2}, m3972l = {311, 322, 327}, m3973m = "setESP32", m3974n = {"this", "espVersion", "this", "espVersion", SpeechUtility.TAG_RESOURCE_RESULT, "response", "it", "this", "espVersion", SpeechUtility.TAG_RESOURCE_RESULT, "response", "it"}, m3975s = {"L$0", "I$0", "L$0", "I$0", "L$1", "L$2", "I$3", "L$0", "I$0", "L$1", "L$2", "I$3"})
/* loaded from: classes.dex */
public final class ESP32TestActivity$setESP32$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ESP32TestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESP32TestActivity$setESP32$1(ESP32TestActivity eSP32TestActivity, Continuation continuation) {
        super(continuation);
        this.this$0 = eSP32TestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.setESP32(this);
    }
}
