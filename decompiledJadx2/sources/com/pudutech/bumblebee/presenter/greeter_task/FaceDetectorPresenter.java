package com.pudutech.bumblebee.presenter.greeter_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.robotsdk.RobotSetting;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.FaceDetectListener;
import com.pudutech.bumblebee.presenter.greeter_task.FaceDetectorContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: FaceDetectorPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016J(\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\b\u0010\u0016\u001a\u00020\rH\u0014J\b\u0010\u0017\u001a\u00020\rH\u0014R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/FaceDetectorPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/greeter_task/FaceDetectorContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/greeter_task/FaceDetectorContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/robotsdk/sdk_listeners/FaceDetectListener;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "hasFaceDetector", "", "actionActive", "", "actionClose", "onFaceDetectResult", "p0", "", "p1", "", "p2", "p3", "onViewAttach", "onViewRemoved", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class FaceDetectorPresenter extends BaseOneViewPresenter<FaceDetectorContract.ViewInterface> implements FaceDetectorContract.PresenterInterface, FaceDetectListener {
    private final String TAG = "FaceDetectorPresenter";
    private boolean hasFaceDetector;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        SDK.INSTANCE.getFaceDetectListeners().addListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        SDK.INSTANCE.getFaceDetectListeners().removeListener(this);
        RobotSetting.INSTANCE.setFaceDetectorSwitch(false);
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.FaceDetectorContract.PresenterInterface
    public void actionActive() {
        Pdlog.m3273d(getTAG(), "actionActive ");
        this.hasFaceDetector = false;
        RobotSetting.INSTANCE.setFaceDetectorSwitch(true);
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.FaceDetectorContract.PresenterInterface
    public void actionClose() {
        Pdlog.m3273d(getTAG(), "actionClose ");
        RobotSetting.INSTANCE.setFaceDetectorSwitch(false);
    }

    @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.FaceDetectListener
    public void onFaceDetectResult(final int p0, final double p1, final double p2, final double p3) {
        if (p0 == 1 && p1 >= -20 && p1 <= 20 && p3 <= 1.25d && !this.hasFaceDetector) {
            Pdlog.m3273d(getTAG(), "onFaceDetectResult hasFaceDetector true : p0 = " + p0 + "; p1 = " + p1 + "; p2 = " + p2 + "; p3 = " + p3 + "; ");
            this.hasFaceDetector = true;
            runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.FaceDetectorPresenter$onFaceDetectResult$1
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
                    FaceDetectorContract.ViewInterface theView;
                    theView = FaceDetectorPresenter.this.getTheView();
                    if (theView != null) {
                        theView.onFaceDetectResult(p0, p1, p2, p3);
                    }
                }
            });
            return;
        }
        if (this.hasFaceDetector) {
            Pdlog.m3273d(getTAG(), "onFaceDetectResult hasFaceDetector false : p0 = " + p0 + "; p1 = " + p1 + "; p2 = " + p2 + "; p3 = " + p3 + "; ");
            this.hasFaceDetector = false;
            runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.FaceDetectorPresenter$onFaceDetectResult$2
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
                    FaceDetectorContract.ViewInterface theView;
                    theView = FaceDetectorPresenter.this.getTheView();
                    if (theView != null) {
                        theView.onFaceDetectResult(p0, p1, p2, p3);
                    }
                }
            });
        }
    }
}
