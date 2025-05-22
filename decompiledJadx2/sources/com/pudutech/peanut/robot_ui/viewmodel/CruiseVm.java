package com.pudutech.peanut.robot_ui.viewmodel;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.util.FactoryTestHelper;
import com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener;
import com.pudutech.mirsdkwrap.lib.interf.RobotMoveEvent;
import com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener;
import com.pudutech.mirsdkwrap.lib.move.MoveCruise;
import com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveCruiseTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveErrorProcess;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.bean.PlayBean;
import com.pudutech.peanut.robot_ui.manager.ReportHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.CommonMotorDirectionListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.viewmodel.CruiseVm;
import com.pudutech.robot.module.report.task.ReportCruise;
import com.pudutech.robot.module.report.task.ReportCruiseTask;
import com.pudutech.robot.module.voice.data.PlayEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruiseVm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\u0006\u0010\u001d\u001a\u00020\u001bJ\b\u0010\u001e\u001a\u00020\u001bH\u0002J\u000e\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020\u001bH\u0014J\b\u0010#\u001a\u00020\u001bH\u0016J\b\u0010$\u001a\u00020\u001bH\u0002J\b\u0010%\u001a\u00020\u001bH\u0002J\u000e\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\rR\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/CruiseVm;", "Lcom/pudutech/peanut/robot_ui/viewmodel/BaseMoveViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "animationView", "Landroid/view/View;", "cruiseStatusState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/pudutech/peanut/robot_ui/viewmodel/CruiseVm$CruiseStatus;", "getCruiseStatusState", "()Landroidx/lifecycle/MutableLiveData;", "mCloseAction", "Landroid/view/animation/TranslateAnimation;", "mShowAction", "moveCruise", "Lcom/pudutech/mirsdkwrap/lib/move/MoveCruise;", "moveCruiseStateListener", "Lcom/pudutech/mirsdkwrap/lib/interf/MoveCruiseStateListener;", "playState", "Lcom/pudutech/peanut/robot_ui/bean/PlayBean;", "getPlayState", "reportCruise", "Lcom/pudutech/robot/module/report/task/ReportCruise;", "active", "", "cancel", "closeView", "initAnimation", "initMoveCruise", "id", "", "onCleared", "pause", "report", "setCruiseVoicePlay", "showView", "mView", "CruiseStatus", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CruiseVm extends BaseMoveViewModel {
    private View animationView;
    private TranslateAnimation mCloseAction;
    private TranslateAnimation mShowAction;
    private MoveCruise moveCruise;
    private final String TAG = "CruiseVm";
    private final MutableLiveData<CruiseStatus> cruiseStatusState = new MutableLiveData<>();
    private final MutableLiveData<PlayBean> playState = new MutableLiveData<>();
    private final MoveCruiseStateListener moveCruiseStateListener = new SingleMoveCruiseStateListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.CruiseVm$moveCruiseStateListener$1
        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
        public void onPause() {
            Pdlog.m3273d(CruiseVm.this.getTAG(), "onPause ");
            CruiseVm.this.onPause();
            CruiseVm.this.getCruiseStatusState().postValue(CruiseVm.CruiseStatus.Pause);
            ReportCruiseTask.INSTANCE.trackingCruiseEndEvent();
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
        public void onCancel() {
            Pdlog.m3273d(CruiseVm.this.getTAG(), "onCancel ");
            CruiseVm.this.onCancel();
            CruiseVm.this.report();
            CruiseVm.this.getCruiseStatusState().postValue(CruiseVm.CruiseStatus.Cancel);
            ReportCruiseTask.INSTANCE.trackingCruiseEndEvent();
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
        public void onStayPointArrive(String s) {
            Intrinsics.checkParameterIsNotNull(s, "s");
            Pdlog.m3273d(CruiseVm.this.getTAG(), "onStayPointArrive : s = " + s + "; ");
            CruiseVm.this.onArrive();
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener
        public void onGoalCruise() {
            Pdlog.m3273d(CruiseVm.this.getTAG(), "onGoalCruise ");
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onMoving(RobotMoveEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            CruiseVm.this.getCruiseStatusState().postValue(CruiseVm.CruiseStatus.Moving);
            Pdlog.m3273d(CruiseVm.this.getTAG(), "RobotMoveEvent = " + event + "; ");
            CruiseVm.this.onMoving(event);
            if (event != RobotMoveEvent.STUCK) {
                CruiseVm.this.setCruiseVoicePlay();
            }
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onError(MoveErrorHelper eh) {
            Intrinsics.checkParameterIsNotNull(eh, "eh");
            Pdlog.m3273d(CruiseVm.this.getTAG(), "onError : eh = " + eh.getCurrentErrorSuggestion() + "; ");
            ReportHelper.INSTANCE.reportMoveError(eh);
            if (eh.getCurrentErrorSuggestion() != MoveErrorProcess.NOTHING) {
                CruiseVm.this.getCruiseStatusState().postValue(CruiseVm.CruiseStatus.Pause);
                FactoryTestHelper.INSTANCE.recordError();
            }
            ReportCruiseTask.INSTANCE.trackingCruiseEndEvent();
            CruiseVm.this.onError(eh);
        }

        @Override // com.pudutech.mirsdkwrap.lib.interf.SingleMoveCruiseStateListener, com.pudutech.mirsdkwrap.lib.interf.BaseRobotMoveStateListener
        public void onAvoid() {
            CruiseVm.this.onAvoid();
        }
    };
    private ReportCruise reportCruise = new ReportCruise();

    /* compiled from: CruiseVm.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/viewmodel/CruiseVm$CruiseStatus;", "", "(Ljava/lang/String;I)V", "Moving", "Pause", "Cancel", "Finish", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum CruiseStatus {
        Moving,
        Pause,
        Cancel,
        Finish
    }

    public CruiseVm() {
        initAnimation();
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public String getTAG() {
        return this.TAG;
    }

    public final MutableLiveData<CruiseStatus> getCruiseStatusState() {
        return this.cruiseStatusState;
    }

    public final MutableLiveData<PlayBean> getPlayState() {
        return this.playState;
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void active() {
        super.active();
        Pdlog.m3273d(getTAG(), "active ");
        MoveCruise moveCruise = this.moveCruise;
        if (moveCruise != null) {
            moveCruise.active();
        }
        ReportCruiseTask.INSTANCE.trackingCruiseStartEvent();
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void pause() {
        super.pause();
        Pdlog.m3273d(getTAG(), "pause ");
        MoveCruise moveCruise = this.moveCruise;
        if (moveCruise != null) {
            moveCruise.pause();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel
    public void cancel() {
        super.cancel();
        Pdlog.m3273d(getTAG(), "cancel ");
        MoveCruise moveCruise = this.moveCruise;
        if (moveCruise != null) {
            moveCruise.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.peanut.robot_ui.viewmodel.BaseMoveViewModel, com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        FactoryTestHelper.INSTANCE.recordCruiseEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCruiseVoicePlay() {
        VoicePlayTasks.INSTANCE.playCruiseMoveVoiceTask(new Function3<PlayEvent, String, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.viewmodel.CruiseVm$setCruiseVoicePlay$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(PlayEvent playEvent, String str, Boolean bool) {
                invoke2(playEvent, str, bool);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PlayEvent it, String str, Boolean bool) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                    Pdlog.m3273d(CruiseVm.this.getTAG(), "state = " + it + "  content = " + str + "  ");
                    if (it == PlayEvent.OnceFinish) {
                        if (str != null) {
                            Pdlog.m3273d(CruiseVm.this.getTAG(), "OnceFinish = " + RobotContext.INSTANCE.getContext().getString(C5508R.string.voice51_1) + "  name = " + str + "   ");
                            if (Intrinsics.areEqual(str, RobotContext.INSTANCE.getContext().getString(C5508R.string.voice51_1))) {
                                CruiseVm.this.getPlayState().postValue(new PlayBean(str, it.name(), false));
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (str != null) {
                        Pdlog.m3273d(CruiseVm.this.getTAG(), "UnOnceFinish = " + RobotContext.INSTANCE.getContext().getString(C5508R.string.voice51_1) + "  name = " + str + "   ");
                        if (!Intrinsics.areEqual(str, RobotContext.INSTANCE.getContext().getString(C5508R.string.voice51_1))) {
                            CruiseVm.this.getPlayState().postValue(new PlayBean(str, it.name(), true));
                        }
                    }
                }
            }
        });
    }

    public final void initMoveCruise(int id) {
        Pdlog.m3273d(getTAG(), "initMoveCruise ");
        this.moveCruise = RobotMoveManager.INSTANCE.cruise(new MoveCruiseTask(id, null, null, 6, null));
        MoveCruise moveCruise = this.moveCruise;
        if (moveCruise != null) {
            moveCruise.setOnMoveStateListener(this.moveCruiseStateListener);
        }
        MoveCruise moveCruise2 = this.moveCruise;
        if (moveCruise2 != null) {
            moveCruise2.setOnMotorDirectionListener(new CommonMotorDirectionListener());
        }
        active();
        FactoryTestHelper.INSTANCE.recordCruiseStart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void report() {
        MoveCruise moveCruise;
        if (this.reportCruise == null || (moveCruise = this.moveCruise) == null) {
            return;
        }
        MoveReportData moveReportData = moveCruise.getMoveReport().getMoveReportData();
        ReportCruise reportCruise = this.reportCruise;
        if (reportCruise == null) {
            Intrinsics.throwNpe();
        }
        reportCruise.setAverage(moveReportData.getAverage()).setDuration(moveReportData.getAllTime() - moveReportData.getPauseTime()).setMileage(moveReportData.getMileage()).setPauseCount(moveReportData.getPauseCount()).setStatus(moveReportData.getCancel()).setPauseDuration(moveReportData.getPauseTime()).setTotalTime(moveReportData.getAllTime()).report();
        this.reportCruise = (ReportCruise) null;
    }

    private final void initAnimation() {
        this.mShowAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        TranslateAnimation translateAnimation = this.mShowAction;
        if (translateAnimation != null) {
            translateAnimation.setDuration(500L);
        }
        this.mCloseAction = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        TranslateAnimation translateAnimation2 = this.mCloseAction;
        if (translateAnimation2 != null) {
            translateAnimation2.setDuration(500L);
        }
        TranslateAnimation translateAnimation3 = this.mCloseAction;
        if (translateAnimation3 != null) {
            translateAnimation3.setAnimationListener(new Animation.AnimationListener() { // from class: com.pudutech.peanut.robot_ui.viewmodel.CruiseVm$initAnimation$1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation p0) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation p0) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation p0) {
                    View view;
                    view = CruiseVm.this.animationView;
                    if (view != null) {
                        view.setVisibility(8);
                    }
                }
            });
        }
    }

    public final void showView(View mView) {
        Intrinsics.checkParameterIsNotNull(mView, "mView");
        this.animationView = mView;
        View view = this.animationView;
        if (view != null) {
            view.setVisibility(0);
        }
        View view2 = this.animationView;
        if (view2 != null) {
            view2.startAnimation(this.mShowAction);
        }
    }

    public final void closeView() {
        View view = this.animationView;
        if (view != null) {
            if (!(view.getVisibility() == 0)) {
                return;
            }
        }
        View view2 = this.animationView;
        if (view2 != null) {
            view2.startAnimation(this.mCloseAction);
        }
    }
}
