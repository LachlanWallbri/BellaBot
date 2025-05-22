package com.pudutech.lidar.echox;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EchoxFpgaUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0082@"}, m3961d2 = {"sendFpgaData", "", "upgradePackageList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lidar.echox.EchoxFpgaUpgrade", m3970f = "EchoxFpgaUpgrade.kt", m3971i = {0, 0, 1, 1, 2, 2}, m3972l = {136, 136, 136}, m3973m = "sendFpgaData", m3974n = {"this", "upgradePackageList", "this", "upgradePackageList", "this", "upgradePackageList"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class EchoxFpgaUpgrade$sendFpgaData$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ EchoxFpgaUpgrade this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EchoxFpgaUpgrade$sendFpgaData$1(EchoxFpgaUpgrade echoxFpgaUpgrade, Continuation continuation) {
        super(continuation);
        this.this$0 = echoxFpgaUpgrade;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendFpgaData(null, this);
    }
}
