package com.pudutech.mirsdk.mircore.p057ui;

import android.widget.TextView;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
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

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CoreMainActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.CoreMainActivity$OpenCore$2", m3970f = "CoreMainActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class CoreMainActivity$OpenCore$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6263p$;
    final /* synthetic */ CoreMainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoreMainActivity$OpenCore$2(CoreMainActivity coreMainActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = coreMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CoreMainActivity$OpenCore$2 coreMainActivity$OpenCore$2 = new CoreMainActivity$OpenCore$2(this.this$0, completion);
        coreMainActivity$OpenCore$2.f6263p$ = (CoroutineScope) obj;
        return coreMainActivity$OpenCore$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CoreMainActivity$OpenCore$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AIDLConnection aIDLConnection;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6263p$;
        aIDLConnection = this.this$0.coreService;
        aIDLConnection.disconnect(this.this$0);
        this.this$0.connect_status = false;
        this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$OpenCore$2.1
            @Override // java.lang.Runnable
            public final void run() {
                if (!MirCoreImpl.INSTANCE.getInit_modules_status()) {
                    CoreMainActivity$OpenCore$2.this.this$0.invisible();
                }
                TextView core_linker_count = (TextView) CoreMainActivity$OpenCore$2.this.this$0._$_findCachedViewById(C5224R.id.core_linker_count);
                Intrinsics.checkExpressionValueIsNotNull(core_linker_count, "core_linker_count");
                core_linker_count.setText("LinkFail");
            }
        });
        return Unit.INSTANCE;
    }
}
