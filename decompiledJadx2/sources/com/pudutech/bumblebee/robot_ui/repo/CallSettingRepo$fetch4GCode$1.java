package com.pudutech.bumblebee.robot_ui.repo;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CallSettingRepo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u0086@"}, m3961d2 = {"fetch4GCode", "", "shopId", "", "continuation", "Lkotlin/coroutines/Continuation;", "Lcom/pudutech/disinfect/baselib/network/response/G4CodeData;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo", m3970f = "CallSettingRepo.kt", m3971i = {0, 0}, m3972l = {98}, m3973m = "fetch4GCode", m3974n = {"this", "shopId"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class CallSettingRepo$fetch4GCode$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CallSettingRepo this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallSettingRepo$fetch4GCode$1(CallSettingRepo callSettingRepo, Continuation continuation) {
        super(continuation);
        this.this$0 = callSettingRepo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.fetch4GCode(null, this);
    }
}
