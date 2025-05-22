package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.map_switch_task.MapUpdatePresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ConfirmTipDialog;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SupportAsync.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, m3961d2 = {"<anonymous>", "", "run", "org/jetbrains/anko/support/v4/SupportAsyncKt$runOnUiThread$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class MultiMapFragment$onError$$inlined$runOnUiThread$1 implements Runnable {
    final /* synthetic */ MultiMapFragment this$0;

    public MultiMapFragment$onError$$inlined$runOnUiThread$1(MultiMapFragment multiMapFragment) {
        this.this$0 = multiMapFragment;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConfirmTipDialog confirmTipDialog;
        ConfirmTipDialog confirmTipDialog2;
        this.this$0.dismissProgressDialog();
        confirmTipDialog = this.this$0.errorDialog;
        if (confirmTipDialog == null) {
            MultiMapFragment multiMapFragment = this.this$0;
            Context requireContext = multiMapFragment.requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            final ConfirmTipDialog confirmTipDialog3 = new ConfirmTipDialog(requireContext, true);
            String string = this.this$0.getString(C4188R.string.failed_sync_map_try_again);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.failed_sync_map_try_again)");
            confirmTipDialog3.setMsg(string);
            confirmTipDialog3.setTitle(CommonExtKt.getSting(C4188R.string.pdStr5_1));
            String string2 = this.this$0.getString(C4188R.string.pdStr8_4);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr8_4)");
            confirmTipDialog3.setBtn1Text(string2);
            String string3 = this.this$0.getString(C4188R.string.pdStr1_11);
            Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr1_11)");
            confirmTipDialog3.setBtn2Text(string3);
            confirmTipDialog3.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$onError$$inlined$runOnUiThread$1$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    ConfirmTipDialog.this.dismiss();
                }
            });
            confirmTipDialog3.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$onError$$inlined$runOnUiThread$1$lambda$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: MultiMapFragment.kt */
                @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot_ui/module/setting/ui/MultiMapFragment$onError$1$1$1$2$1", "com/pudutech/bumblebee/robot_ui/module/setting/ui/MultiMapFragment$$special$$inlined$apply$lambda$1$1", "com/pudutech/bumblebee/robot_ui/module/setting/ui/MultiMapFragment$$special$$inlined$let$lambda$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
                /* renamed from: com.pudutech.bumblebee.robot_ui.module.setting.ui.MultiMapFragment$onError$$inlined$runOnUiThread$1$lambda$2$1 */
                /* loaded from: classes3.dex */
                public static final class C42491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    Object L$0;
                    int label;

                    /* renamed from: p$ */
                    private CoroutineScope f4880p$;

                    C42491(Continuation continuation) {
                        super(2, continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        C42491 c42491 = new C42491(completion);
                        c42491.f4880p$ = (CoroutineScope) obj;
                        return c42491;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C42491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        MapUpdatePresenter mapUpdatePresenter;
                        CoroutineScope coroutineScope;
                        String str;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope2 = this.f4880p$;
                            mapUpdatePresenter = this.this$0.getMapUpdatePresenter();
                            coroutineScope = this.this$0.coroutineScope;
                            this.L$0 = coroutineScope2;
                            this.label = 1;
                            if (mapUpdatePresenter.updateAll(coroutineScope, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        this.this$0.showProgress("0");
                        str = this.this$0.TAG;
                        Pdlog.m3273d(str, "onBtn2Click showProgress(0)");
                        return Unit.INSTANCE;
                    }
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    CoroutineScope coroutineScope;
                    ConfirmTipDialog.this.dismiss();
                    coroutineScope = this.this$0.coroutineScope;
                    if (coroutineScope != null) {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C42491(null), 3, null);
                    }
                }
            });
            multiMapFragment.errorDialog = confirmTipDialog3;
            Unit unit = Unit.INSTANCE;
        }
        confirmTipDialog2 = this.this$0.errorDialog;
        if (confirmTipDialog2 == null || confirmTipDialog2.isShowing()) {
            return;
        }
        confirmTipDialog2.show();
    }
}
