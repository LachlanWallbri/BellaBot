package com.pudutech.factory_test.single_test;

import com.pudutech.factory_test.esp32.ESP32Lib;
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
/* compiled from: ESP32TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.ESP32TestActivity$setESP32$result$1", m3970f = "ESP32TestActivity.kt", m3971i = {0}, m3972l = {312}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class ESP32TestActivity$setESP32$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ int $espVersion;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5176p$;
    final /* synthetic */ ESP32TestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESP32TestActivity$setESP32$result$1(ESP32TestActivity eSP32TestActivity, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = eSP32TestActivity;
        this.$espVersion = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ESP32TestActivity$setESP32$result$1 eSP32TestActivity$setESP32$result$1 = new ESP32TestActivity$setESP32$result$1(this.this$0, this.$espVersion, completion);
        eSP32TestActivity$setESP32$result$1.f5176p$ = (CoroutineScope) obj;
        return eSP32TestActivity$setESP32$result$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((ESP32TestActivity$setESP32$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5176p$;
            ESP32Lib esp32 = this.this$0.getEsp32();
            ESP32TestActivity eSP32TestActivity = this.this$0;
            int i2 = this.$espVersion;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = esp32.open(eSP32TestActivity, i2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
