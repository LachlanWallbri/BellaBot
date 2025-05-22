package com.pudutech.antichannelconflict.upgrade;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.apache.http.HttpStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PeriodOf4GManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086@"}, m3961d2 = {"checkPeriodVersion", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.antichannelconflict.upgrade.PeriodOf4GManager", m3970f = "PeriodOf4GManager.kt", m3971i = {0, 0, 0}, m3972l = {HttpStatus.SC_GONE}, m3973m = "checkPeriodVersion", m3974n = {"this", "locale", "language"}, m3975s = {"L$0", "L$1", "L$2"})
/* loaded from: classes4.dex */
public final class PeriodOf4GManager$checkPeriodVersion$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PeriodOf4GManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeriodOf4GManager$checkPeriodVersion$1(PeriodOf4GManager periodOf4GManager, Continuation continuation) {
        super(continuation);
        this.this$0 = periodOf4GManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.checkPeriodVersion(this);
    }
}
