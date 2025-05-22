package com.pudutech.factory_test.single_test;

import com.pudutech.factory_test.esp32.ESP32Lib;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ESP32TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/factory_test/single_test/ESP32TestActivity$setESP32$2$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ESP32TestActivity$setESP32$$inlined$repeat$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ Ref.ObjectRef $response$inlined;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5172p$;
    final /* synthetic */ ESP32TestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESP32TestActivity$setESP32$$inlined$repeat$lambda$1(Continuation continuation, ESP32TestActivity eSP32TestActivity, Ref.ObjectRef objectRef, Continuation continuation2) {
        super(2, continuation);
        this.this$0 = eSP32TestActivity;
        this.$response$inlined = objectRef;
        this.$continuation$inlined = continuation2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ESP32TestActivity$setESP32$$inlined$repeat$lambda$1 eSP32TestActivity$setESP32$$inlined$repeat$lambda$1 = new ESP32TestActivity$setESP32$$inlined$repeat$lambda$1(completion, this.this$0, this.$response$inlined, this.$continuation$inlined);
        eSP32TestActivity$setESP32$$inlined$repeat$lambda$1.f5172p$ = (CoroutineScope) obj;
        return eSP32TestActivity$setESP32$$inlined$repeat$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((ESP32TestActivity$setESP32$$inlined$repeat$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5172p$;
            ESP32Lib esp32 = this.this$0.getEsp32();
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = esp32.checkResponse(this);
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
