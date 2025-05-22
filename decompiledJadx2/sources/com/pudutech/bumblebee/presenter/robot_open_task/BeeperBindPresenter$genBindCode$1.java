package com.pudutech.bumblebee.presenter.robot_open_task;

import com.loc.C3898x;
import com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindContract;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: BeeperBindPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class BeeperBindPresenter$genBindCode$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ BeeperBindPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BeeperBindPresenter$genBindCode$1(BeeperBindPresenter beeperBindPresenter) {
        super(0);
        this.this$0 = beeperBindPresenter;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* compiled from: BeeperBindPresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, m3961d2 = {"com/pudutech/bumblebee/presenter/robot_open_task/BeeperBindPresenter$genBindCode$1$1", "Lcom/pudutech/robot/opensdk/interf/IGenBindCodeCallBack;", "onFailed", "", C3898x.f4338g, "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "code", "Lcom/pudutech/robot/opensdk/aliyun/bean/BindCodeData;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindPresenter$genBindCode$1$1 */
    /* loaded from: classes4.dex */
    public static final class C41051 implements IGenBindCodeCallBack {
        C41051() {
        }

        @Override // com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack
        public void onFailed(final Exception e) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            BeeperBindPresenter$genBindCode$1.this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindPresenter$genBindCode$1$1$onFailed$1
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

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BeeperBindContract.ViewInterface theView;
                    theView = BeeperBindPresenter$genBindCode$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.showBindCode(null, e);
                    }
                }
            });
        }

        @Override // com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack
        public void onSuccess(final BindCodeData code) {
            Intrinsics.checkParameterIsNotNull(code, "code");
            BeeperBindPresenter$genBindCode$1.this.this$0.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.BeeperBindPresenter$genBindCode$1$1$onSuccess$1
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

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BeeperBindContract.ViewInterface theView;
                    theView = BeeperBindPresenter$genBindCode$1.this.this$0.getTheView();
                    if (theView != null) {
                        theView.showBindCode(code, null);
                    }
                }
            });
        }
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        RobotOpenManager.INSTANCE.startBindBeeper(new C41051());
    }
}
