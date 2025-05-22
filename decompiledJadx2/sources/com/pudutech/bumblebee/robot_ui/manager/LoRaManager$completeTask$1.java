package com.pudutech.bumblebee.robot_ui.manager;

import com.pudu.library.loracall.ArriveCancelMsg;
import com.pudu.library.loracall.bean.LoraReceiveCall;
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
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoRaManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.manager.LoRaManager$completeTask$1", m3970f = "LoRaManager.kt", m3971i = {0, 0}, m3972l = {457}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "count"}, m3975s = {"L$0", "I$0"})
/* loaded from: classes3.dex */
public final class LoRaManager$completeTask$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4848p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoRaManager$completeTask$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LoRaManager$completeTask$1 loRaManager$completeTask$1 = new LoRaManager$completeTask$1(completion);
        loRaManager$completeTask$1.f4848p$ = (CoroutineScope) obj;
        return loRaManager$completeTask$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoRaManager$completeTask$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x005a -> B:8:0x005d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        int i;
        CoroutineScope coroutineScope;
        LoRaManager$completeTask$1 loRaManager$completeTask$1;
        LoraReceiveCall loraReceiveCall;
        LoraReceiveCall loraReceiveCall2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            i = 6;
            coroutineScope = this.f4848p$;
            loRaManager$completeTask$1 = this;
            if (i > 0) {
            }
            LoRaManager loRaManager = LoRaManager.INSTANCE;
            LoRaManager.crtCall = (LoraReceiveCall) null;
            return Unit.INSTANCE;
        }
        if (i2 == 1) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                loRaManager$completeTask$1 = this;
                i--;
                if (i > 0) {
                    LoRaManager loRaManager2 = LoRaManager.INSTANCE;
                    loraReceiveCall = LoRaManager.crtCall;
                    if (loraReceiveCall != null) {
                        LoRaManager loRaManager3 = LoRaManager.INSTANCE;
                        LoRaManager loRaManager4 = LoRaManager.INSTANCE;
                        loraReceiveCall2 = LoRaManager.crtCall;
                        if (loraReceiveCall2 == null) {
                            Intrinsics.throwNpe();
                        }
                        loRaManager3.sendMsg(new ArriveCancelMsg(true, loraReceiveCall2.getDevAddr()));
                        loRaManager$completeTask$1.L$0 = coroutineScope;
                        loRaManager$completeTask$1.I$0 = i;
                        loRaManager$completeTask$1.label = 1;
                        if (DelayKt.delay(10000L, loRaManager$completeTask$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i--;
                        if (i > 0) {
                        }
                    }
                }
                LoRaManager loRaManager5 = LoRaManager.INSTANCE;
                LoRaManager.crtCall = (LoraReceiveCall) null;
                return Unit.INSTANCE;
            } catch (Exception e) {
                LoRaManager loRaManager6 = LoRaManager.INSTANCE;
                LoRaManager.crtCall = (LoraReceiveCall) null;
                throw e;
            }
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
