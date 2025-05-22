package com.pudutech.mirsdk.mircore.p057ui;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.ReloadMapResultListener;
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

/* compiled from: TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.TestActivity$autoChargeSignal$1", m3970f = "TestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class TestActivity$autoChargeSignal$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6283p$;
    final /* synthetic */ TestActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TestActivity$autoChargeSignal$1(TestActivity testActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = testActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TestActivity$autoChargeSignal$1 testActivity$autoChargeSignal$1 = new TestActivity$autoChargeSignal$1(this.this$0, completion);
        testActivity$autoChargeSignal$1.f6283p$ = (CoroutineScope) obj;
        return testActivity$autoChargeSignal$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TestActivity$autoChargeSignal$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        MirCoreInterface mirCoreInterface;
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6283p$;
        aIDLConnection = this.this$0.coreService;
        if (aIDLConnection != null && (mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface()) != null) {
            str = this.this$0.mapFile;
            mirCoreInterface.reloadPdmap(0, str, null, new ReloadMapResultListener.Stub() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$autoChargeSignal$1.1
                @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
                public void reloadMapSuccess() {
                    String str2;
                    str2 = TestActivity$autoChargeSignal$1.this.this$0.TAG;
                    Pdlog.m3273d(str2, "reloadPdmap sucess");
                }

                @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
                public void reloadMapFail() {
                    String str2;
                    str2 = TestActivity$autoChargeSignal$1.this.this$0.TAG;
                    Pdlog.m3273d(str2, "reloadPdmap fail");
                }
            });
        }
        return Unit.INSTANCE;
    }
}
