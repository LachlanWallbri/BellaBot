package com.pudutech.mirsdk.mircore.p057ui;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.InitServiceListener;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import com.pudutech.mirsdk.mircore.p057ui.TestActivity;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.TestActivity$autoChargePause$1", m3970f = "TestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class TestActivity$autoChargePause$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6282p$;
    final /* synthetic */ TestActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TestActivity$autoChargePause$1(TestActivity testActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = testActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TestActivity$autoChargePause$1 testActivity$autoChargePause$1 = new TestActivity$autoChargePause$1(this.this$0, completion);
        testActivity$autoChargePause$1.f6282p$ = (CoroutineScope) obj;
        return testActivity$autoChargePause$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TestActivity$autoChargePause$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        CoroutineScope coroutineScope = this.f6282p$;
        aIDLConnection = this.this$0.coreService;
        if (aIDLConnection != null && (mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface()) != null) {
            str = this.this$0.mapFile;
            mirCoreInterface.loadTopoMap(0, str, null, new BinderC52631());
        }
        return Unit.INSTANCE;
    }

    /* compiled from: TestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/mircore/ui/TestActivity$autoChargePause$1$1", "Lcom/pudutech/mirsdk/mircore/InitServiceListener$Stub;", "initCoreServiceState", "", "step", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitStep;", "state", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "description", "", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.TestActivity$autoChargePause$1$1 */
    /* loaded from: classes4.dex */
    public static final class BinderC52631 extends InitServiceListener.Stub {
        BinderC52631() {
        }

        @Override // com.pudutech.mirsdk.mircore.InitServiceListener
        public void initCoreServiceState(CoreInitStep step, CoreInitState state, final String description) {
            String str;
            String str2;
            String str3;
            str = TestActivity$autoChargePause$1.this.this$0.TAG;
            Pdlog.m3273d(str, "core init step " + step + " state " + state + " description " + description);
            if (step != null && TestActivity.WhenMappings.$EnumSwitchMapping$8[step.ordinal()] == 1) {
                if (state != null && TestActivity.WhenMappings.$EnumSwitchMapping$6[state.ordinal()] == 1) {
                    str3 = TestActivity$autoChargePause$1.this.this$0.TAG;
                    Pdlog.m3273d(str3, "CoreInitState Succeed");
                    TestActivity$autoChargePause$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$autoChargePause$1$1$initCoreServiceState$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            TextView status_text = (TextView) TestActivity$autoChargePause$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                            Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                            status_text.setText("Success");
                        }
                    });
                    return;
                } else {
                    str2 = TestActivity$autoChargePause$1.this.this$0.TAG;
                    Pdlog.m3273d(str2, "CoreInitState Failed");
                    TestActivity$autoChargePause$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$autoChargePause$1$1$initCoreServiceState$2
                        @Override // java.lang.Runnable
                        public final void run() {
                            TextView status_text = (TextView) TestActivity$autoChargePause$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                            Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                            status_text.setText("Failed");
                        }
                    });
                    return;
                }
            }
            if (state != null && TestActivity.WhenMappings.$EnumSwitchMapping$7[state.ordinal()] == 1) {
                TestActivity$autoChargePause$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.TestActivity$autoChargePause$1$1$initCoreServiceState$3
                    @Override // java.lang.Runnable
                    public final void run() {
                        TextView status_text = (TextView) TestActivity$autoChargePause$1.this.this$0._$_findCachedViewById(C5224R.id.status_text);
                        Intrinsics.checkExpressionValueIsNotNull(status_text, "status_text");
                        status_text.setText(description);
                    }
                });
            }
        }
    }
}
