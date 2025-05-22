package com.pudutech.mirsdk.charge;

import com.pudutech.mirsdk.charge.ChargeUpdateTask;
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
/* compiled from: ChargeUpdateTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.charge.ChargeUpdateTask$doCheckVersionAfterTask$2", m3970f = "ChargeUpdateTask.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class ChargeUpdateTask$doCheckVersionAfterTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5746p$;
    final /* synthetic */ ChargeUpdateTask this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeUpdateTask$doCheckVersionAfterTask$2(ChargeUpdateTask chargeUpdateTask, Continuation continuation) {
        super(2, continuation);
        this.this$0 = chargeUpdateTask;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ChargeUpdateTask$doCheckVersionAfterTask$2 chargeUpdateTask$doCheckVersionAfterTask$2 = new ChargeUpdateTask$doCheckVersionAfterTask$2(this.this$0, completion);
        chargeUpdateTask$doCheckVersionAfterTask$2.f5746p$ = (CoroutineScope) obj;
        return chargeUpdateTask$doCheckVersionAfterTask$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChargeUpdateTask$doCheckVersionAfterTask$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        byte[] bArr;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5746p$;
        this.this$0.resetIdle();
        StringBuffer stringBuffer = new StringBuffer();
        bArr = this.this$0.targetVersion;
        if (bArr != null) {
            int i = 0;
            int length = bArr.length;
            while (i < length) {
                StringBuilder sb = new StringBuilder();
                sb.append(bArr[i] & 255);
                sb.append(i != bArr.length + (-1) ? "." : "");
                stringBuffer.append(sb.toString());
                i++;
            }
        }
        ChargeUpdateTask.onChargePileUpdateListener chargePileUpdateListener = this.this$0.getChargePileUpdateListener();
        if (chargePileUpdateListener != null) {
            String mac = this.this$0.getMac();
            String stringBuffer2 = stringBuffer.toString();
            Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "versionSb.toString()");
            chargePileUpdateListener.onUpdateSuccess(mac, stringBuffer2);
        }
        return Unit.INSTANCE;
    }
}
