package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: GreeterMenuVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/viewmodel/GreeterMenuVM;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "_tableGroupLV", "Landroidx/lifecycle/MutableLiveData;", "", "tableGroupLV", "Landroidx/lifecycle/LiveData;", "getTableGroupLV", "()Landroidx/lifecycle/LiveData;", "loadTableGroup", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class GreeterMenuVM extends BaseViewModel {
    private final MutableLiveData<Integer> _tableGroupLV = new MutableLiveData<>();
    private final LiveData<Integer> tableGroupLV = VMExtKt.toLiveData(this._tableGroupLV);

    public final LiveData<Integer> getTableGroupLV() {
        return this.tableGroupLV;
    }

    public final void loadTableGroup() {
        VMExtKt.launchIO(this, new Function1<ViewModelLaunchBuild, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.viewmodel.GreeterMenuVM$loadTableGroup$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ViewModelLaunchBuild viewModelLaunchBuild) {
                invoke2(viewModelLaunchBuild);
                return Unit.INSTANCE;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: GreeterMenuVM.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.GreeterMenuVM$loadTableGroup$1$1", m3970f = "GreeterMenuVM.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
            /* renamed from: com.pudutech.bumblebee.robot_ui.viewmodel.GreeterMenuVM$loadTableGroup$1$1 */
            /* loaded from: classes4.dex */
            public static final class C44121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f4986p$;

                C44121(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    C44121 c44121 = new C44121(completion);
                    c44121.f4986p$ = (CoroutineScope) obj;
                    return c44121;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C44121) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    MutableLiveData mutableLiveData;
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = this.f4986p$;
                    mutableLiveData = GreeterMenuVM.this._tableGroupLV;
                    mutableLiveData.postValue(Boxing.boxInt(RobotMapManager.INSTANCE.getAllTableGroup().size()));
                    return Unit.INSTANCE;
                }
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ViewModelLaunchBuild receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.job(new C44121(null));
            }
        });
    }
}
