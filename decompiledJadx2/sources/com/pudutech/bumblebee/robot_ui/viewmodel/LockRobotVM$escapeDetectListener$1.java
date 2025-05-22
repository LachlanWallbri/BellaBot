package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.pudutech.antichannelconflict.escape.listener.EscapeDetectListener;
import com.pudutech.antichannelconflict.escape.util.EscapeStatus;
import com.pudutech.antichannelconflict.upgrade.util.UpgradeStatus;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LockRobotVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/viewmodel/LockRobotVM$escapeDetectListener$1", "Lcom/pudutech/antichannelconflict/escape/listener/EscapeDetectListener;", "onEscapeDetect", "", "status", "Lcom/pudutech/antichannelconflict/escape/util/EscapeStatus;", "onPeriodUpgrade", "Lcom/pudutech/antichannelconflict/upgrade/util/UpgradeStatus;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LockRobotVM$escapeDetectListener$1 implements EscapeDetectListener {
    final /* synthetic */ LockRobotVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LockRobotVM$escapeDetectListener$1(LockRobotVM lockRobotVM) {
        this.this$0 = lockRobotVM;
    }

    @Override // com.pudutech.antichannelconflict.escape.listener.EscapeDetectListener
    public void onEscapeDetect(final EscapeStatus status) {
        String str;
        Intrinsics.checkParameterIsNotNull(status, "status");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onEscapeDetect status: " + status);
        VMExtKt.launchMain(this.this$0, new Function1<ViewModelLaunchBuild, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.LockRobotVM$escapeDetectListener$1$onEscapeDetect$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewModelLaunchBuild viewModelLaunchBuild) {
                invoke2(viewModelLaunchBuild);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: LockRobotVM.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.LockRobotVM$escapeDetectListener$1$onEscapeDetect$1$1", m3970f = "LockRobotVM.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.robot_ui.viewmodel.LockRobotVM$escapeDetectListener$1$onEscapeDetect$1$1 */
            /* loaded from: classes4.dex */
            public static final class C44141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4987p$;

                C44141(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C44141 c44141 = new C44141(completion);
                    c44141.f4987p$ = (CoroutineScope) obj;
                    return c44141;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C44141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    MutableLiveData mutableLiveData;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4987p$;
                    mutableLiveData = LockRobotVM$escapeDetectListener$1.this.this$0._escapeStatusLD;
                    mutableLiveData.setValue(status);
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewModelLaunchBuild receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.job(new C44141(null));
            }
        });
    }

    @Override // com.pudutech.antichannelconflict.escape.listener.EscapeDetectListener
    public void onPeriodUpgrade(UpgradeStatus status) {
        String str;
        Intrinsics.checkParameterIsNotNull(status, "status");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onPeriodUpgrade status: " + status);
    }
}
