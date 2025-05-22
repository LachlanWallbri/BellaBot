package com.pudutech.bumblebee.presenter.rotation_task;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.rotation_task.RotationContract;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: RotationPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00009\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0001\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0006H\u0016J\f\u0010\u0018\u001a\u00020\u0006*\u00020\u0006H\u0002J\f\u0010\u0019\u001a\u00020\u0006*\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/rotation_task/RotationPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/rotation_task/RotationContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/rotation_task/RotationContract$Presenter;", "()V", "FINISH_THRESHOLD", "", "M_2PI", "TAG", "", "getTAG", "()Ljava/lang/String;", "THRESHOLD", "isClockwise", "", "listener", "com/pudutech/bumblebee/presenter/rotation_task/RotationPresenter$listener$1", "Lcom/pudutech/bumblebee/presenter/rotation_task/RotationPresenter$listener$1;", TypedValues.Attributes.S_TARGET, "cancel", "", "endListenerAndTimer", "rotate", "rad", "toAbsWithinPI", "toWithin2PI", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RotationPresenter extends BaseOneViewPresenter<RotationContract.ViewInterface> implements RotationContract.Presenter {
    private boolean isClockwise;
    private double target;
    private final String TAG = "RotationPresenter";
    private final double THRESHOLD = Math.toRadians(30.0d);
    private final double FINISH_THRESHOLD = Math.toRadians(5.0d);
    private final double M_2PI = 6.283185307179586d;
    private final RotationPresenter$listener$1 listener = new RotationPresenter$listener$1(this);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    private final double toAbsWithinPI(double d) {
        while (d > 3.141592653589793d) {
            d -= this.M_2PI;
        }
        while (d < -3.141592653589793d) {
            d += this.M_2PI;
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double toWithin2PI(double d) {
        while (true) {
            double d2 = this.M_2PI;
            if (d <= d2) {
                break;
            }
            d -= d2;
        }
        while (d < 0) {
            d += this.M_2PI;
        }
        return d;
    }

    @Override // com.pudutech.bumblebee.presenter.rotation_task.RotationContract.Presenter
    public void rotate(double rad) {
        Pdlog.m3273d(getTAG(), "rotate rad=" + rad + " toDegree=" + Math.toDegrees(rad));
        double yaw = SDK.INSTANCE.getRobotPose2D().getYaw();
        this.target = toAbsWithinPI(yaw + rad);
        Pdlog.m3273d(getTAG(), "rotate. now=" + Math.toDegrees(yaw) + " target=" + Math.toDegrees(this.target));
        double absWithinPI = toAbsWithinPI(rad);
        this.isClockwise = absWithinPI < ((double) 0);
        if (Math.abs(absWithinPI) < this.THRESHOLD) {
            runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.rotation_task.RotationPresenter$rotate$1
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
                    RotationContract.ViewInterface theView;
                    theView = RotationPresenter.this.getTheView();
                    if (theView != null) {
                        theView.showRotateEvent(RotationContract.ViewEvent.UNNECESSARY_ROTATE);
                    }
                }
            });
            Pdlog.m3273d(getTAG(), "rotate 角度太小了：diff = " + absWithinPI);
            return;
        }
        runOnBusinessThread(new RotationPresenter$rotate$2(this, absWithinPI));
    }

    @Override // com.pudutech.bumblebee.presenter.rotation_task.RotationContract.Presenter
    public void cancel() {
        Pdlog.m3273d(getTAG(), "cancel ");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.rotation_task.RotationPresenter$cancel$1
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
                RotationPresenter.this.endListenerAndTimer();
                RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
                if (moveAction != null) {
                    moveAction.rotate(0.0d);
                }
                RotationPresenter.this.runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.rotation_task.RotationPresenter$cancel$1.1
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
                        RotationContract.ViewInterface theView;
                        theView = RotationPresenter.this.getTheView();
                        if (theView != null) {
                            theView.showRotateEvent(RotationContract.ViewEvent.CANCEL);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endListenerAndTimer() {
        SDK.INSTANCE.getPoseListeners().removeListener(this.listener);
        TimerThread.INSTANCE.remove(toString(), getTAG());
    }
}
