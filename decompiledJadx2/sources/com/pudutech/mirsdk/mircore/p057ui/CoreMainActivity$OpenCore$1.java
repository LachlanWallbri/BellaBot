package com.pudutech.mirsdk.mircore.p057ui;

import android.widget.TextView;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.mircore.C5224R;
import com.pudutech.mirsdk.mircore.InitServiceListener;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitState;
import com.pudutech.mirsdk.mircore.coreparcel.CoreInitStep;
import com.pudutech.mirsdk.mircore.p057ui.CoreMainActivity;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.mircore.ui.CoreMainActivity$OpenCore$1", m3970f = "CoreMainActivity.kt", m3971i = {0, 1}, m3972l = {227, 228}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
final class CoreMainActivity$OpenCore$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6262p$;
    final /* synthetic */ CoreMainActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoreMainActivity$OpenCore$1(CoreMainActivity coreMainActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = coreMainActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CoreMainActivity$OpenCore$1 coreMainActivity$OpenCore$1 = new CoreMainActivity$OpenCore$1(this.this$0, completion);
        coreMainActivity$OpenCore$1.f6262p$ = (CoroutineScope) obj;
        return coreMainActivity$OpenCore$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CoreMainActivity$OpenCore$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x006c  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        AIDLConnection aIDLConnection;
        AIDLConnection aIDLConnection2;
        AIDLConnection aIDLConnection3;
        MirCoreInterface mirCoreInterface;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6262p$;
            aIDLConnection = this.this$0.coreService;
            CoreMainActivity coreMainActivity = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = aIDLConnection.connect(coreMainActivity, null, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    aIDLConnection3 = this.this$0.coreService;
                    mirCoreInterface = (MirCoreInterface) aIDLConnection3.getInterface();
                    if (mirCoreInterface != null) {
                        mirCoreInterface.initModules(0, "/sdcard/PuduRobotMap/pudu.pdmap", null, new BinderC52461());
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            aIDLConnection2 = this.this$0.hardwareService;
            CoreMainActivity coreMainActivity2 = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 2;
            if (aIDLConnection2.connect(coreMainActivity2, null, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            aIDLConnection3 = this.this$0.coreService;
            mirCoreInterface = (MirCoreInterface) aIDLConnection3.getInterface();
            if (mirCoreInterface != null) {
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: CoreMainActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/mirsdk/mircore/ui/CoreMainActivity$OpenCore$1$1", "Lcom/pudutech/mirsdk/mircore/InitServiceListener$Stub;", "initCoreServiceState", "", "step", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitStep;", "state", "Lcom/pudutech/mirsdk/mircore/coreparcel/CoreInitState;", "description", "", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$OpenCore$1$1 */
    /* loaded from: classes6.dex */
    public static final class BinderC52461 extends InitServiceListener.Stub {
        BinderC52461() {
        }

        @Override // com.pudutech.mirsdk.mircore.InitServiceListener
        public void initCoreServiceState(CoreInitStep step, CoreInitState state, final String description) {
            if (step != null && CoreMainActivity.WhenMappings.$EnumSwitchMapping$3[step.ordinal()] == 1) {
                if (state != null && CoreMainActivity.WhenMappings.$EnumSwitchMapping$1[state.ordinal()] == 1) {
                    CoreMainActivity$OpenCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$OpenCore$1$1$initCoreServiceState$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            CoreMainActivity$OpenCore$1.this.this$0.connect_status = true;
                            TextView core_linker_count = (TextView) CoreMainActivity$OpenCore$1.this.this$0._$_findCachedViewById(C5224R.id.core_linker_count);
                            Intrinsics.checkExpressionValueIsNotNull(core_linker_count, "core_linker_count");
                            core_linker_count.setText("InitSuccess");
                            CoreMainActivity$OpenCore$1.this.this$0.show(true);
                        }
                    });
                    return;
                } else {
                    CoreMainActivity$OpenCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$OpenCore$1$1$initCoreServiceState$2
                        @Override // java.lang.Runnable
                        public final void run() {
                            CoreMainActivity$OpenCore$1.this.this$0.show(false);
                        }
                    });
                    return;
                }
            }
            if (state != null && CoreMainActivity.WhenMappings.$EnumSwitchMapping$2[state.ordinal()] == 1) {
                CoreMainActivity$OpenCore$1.this.this$0.connect_status = false;
                CoreMainActivity$OpenCore$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.mircore.ui.CoreMainActivity$OpenCore$1$1$initCoreServiceState$3
                    @Override // java.lang.Runnable
                    public final void run() {
                        TextView core_linker_count = (TextView) CoreMainActivity$OpenCore$1.this.this$0._$_findCachedViewById(C5224R.id.core_linker_count);
                        Intrinsics.checkExpressionValueIsNotNull(core_linker_count, "core_linker_count");
                        core_linker_count.setText(description);
                    }
                });
            }
        }
    }
}
