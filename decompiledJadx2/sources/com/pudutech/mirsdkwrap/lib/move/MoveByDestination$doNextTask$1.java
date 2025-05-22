package com.pudutech.mirsdkwrap.lib.move;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.apache.http.HttpStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByDestination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, m3961d2 = {"doNextTask", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByDestination", m3970f = "MoveByDestination.kt", m3971i = {0, 0}, m3972l = {HttpStatus.SC_RESET_CONTENT}, m3973m = "doNextTask", m3974n = {"this", LinkFormat.DOMAIN}, m3975s = {"L$0", "L$1"})
/* loaded from: classes6.dex */
public final class MoveByDestination$doNextTask$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MoveByDestination this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByDestination$doNextTask$1(MoveByDestination moveByDestination, Continuation continuation) {
        super(continuation);
        this.this$0 = moveByDestination;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doNextTask(this);
    }
}
