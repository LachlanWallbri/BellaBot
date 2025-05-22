package com.pudutech.lidar.echox;

import android.util.Log;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EchoxFpgaUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lidar.echox.EchoxFpgaUpgrade$startFpgaUpgrade$1", m3970f = "EchoxFpgaUpgrade.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
public final class EchoxFpgaUpgrade$startFpgaUpgrade$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList $upgradePackageList;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5454p$;
    final /* synthetic */ EchoxFpgaUpgrade this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EchoxFpgaUpgrade$startFpgaUpgrade$1(EchoxFpgaUpgrade echoxFpgaUpgrade, ArrayList arrayList, Continuation continuation) {
        super(2, continuation);
        this.this$0 = echoxFpgaUpgrade;
        this.$upgradePackageList = arrayList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EchoxFpgaUpgrade$startFpgaUpgrade$1 echoxFpgaUpgrade$startFpgaUpgrade$1 = new EchoxFpgaUpgrade$startFpgaUpgrade$1(this.this$0, this.$upgradePackageList, completion);
        echoxFpgaUpgrade$startFpgaUpgrade$1.f5454p$ = (CoroutineScope) obj;
        return echoxFpgaUpgrade$startFpgaUpgrade$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EchoxFpgaUpgrade$startFpgaUpgrade$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5454p$;
        str = this.this$0.TAG;
        Log.d(str, "startFpgaUpgrade");
        this.this$0.socketInit();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EchoxFpgaUpgrade$startFpgaUpgrade$1$fpgaSendJob$1(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EchoxFpgaUpgrade$startFpgaUpgrade$1$fpgaReceiveJob$1(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EchoxFpgaUpgrade$startFpgaUpgrade$1$waitForFpgaResponseJob$1(this, null), 3, null);
        return Unit.INSTANCE;
    }
}
