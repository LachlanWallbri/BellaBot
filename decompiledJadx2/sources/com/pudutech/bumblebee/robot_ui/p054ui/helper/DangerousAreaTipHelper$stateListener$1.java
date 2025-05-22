package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.os.Handler;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DangerousAreaTipHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002:\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0001j\u0002`\tJ\u001d\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0096\u0002¨\u0006\r"}, m3961d2 = {"com/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper$stateListener$1", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "p0", "", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "invoke", "state", "description", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DangerousAreaTipHelper$stateListener$1 implements Function2<RobotState, String, Unit> {
    final /* synthetic */ DangerousAreaTipHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DangerousAreaTipHelper$stateListener$1(DangerousAreaTipHelper dangerousAreaTipHelper) {
        this.this$0 = dangerousAreaTipHelper;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
        invoke2(robotState, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(final RobotState state, String description) {
        Handler handler;
        handler = this.this$0.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.DangerousAreaTipHelper$stateListener$1$invoke$1
                @Override // java.lang.Runnable
                public final void run() {
                    RobotState robotState;
                    RobotState robotState2;
                    RobotState robotState3;
                    RobotState robotState4;
                    boolean z;
                    RobotState robotState5;
                    RobotState robotState6;
                    RobotState robotState7;
                    RobotState robotState8;
                    boolean z2;
                    RobotState robotState9;
                    boolean z3;
                    robotState = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                    String name = robotState.name();
                    RobotState robotState10 = state;
                    if (Intrinsics.areEqual(name, robotState10 != null ? robotState10.name() : null)) {
                        return;
                    }
                    if (state != null) {
                        DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState = state;
                    }
                    robotState2 = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                    if (!Intrinsics.areEqual(robotState2.name(), RobotState.Pause.name())) {
                        robotState6 = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                        if (!Intrinsics.areEqual(robotState6.name(), RobotState.Arrive.name())) {
                            robotState7 = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                            if (!Intrinsics.areEqual(robotState7.name(), RobotState.Error.name())) {
                                robotState8 = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                                if (Intrinsics.areEqual(robotState8.name(), RobotState.Moving.name())) {
                                    z2 = DangerousAreaTipHelper$stateListener$1.this.this$0.isNeedMove;
                                    if (z2) {
                                        DangerousAreaTipHelper$stateListener$1.this.this$0.startPlay();
                                        DangerousAreaTipHelper$stateListener$1.this.this$0.isNeedMove = false;
                                        String str = DangerousAreaTipHelper$stateListener$1.this.this$0.TAG;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("mRobotState startPlay mRobotState =");
                                        robotState9 = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                                        sb.append(robotState9);
                                        sb.append(" isNeedMove =");
                                        z3 = DangerousAreaTipHelper$stateListener$1.this.this$0.isNeedMove;
                                        sb.append(z3);
                                        Pdlog.m3273d(str, sb.toString());
                                    }
                                }
                                String str2 = DangerousAreaTipHelper$stateListener$1.this.this$0.TAG;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("mRobotState end ");
                                robotState5 = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                                sb2.append(robotState5);
                                Pdlog.m3273d(str2, sb2.toString());
                            }
                        }
                    }
                    DangerousAreaTipHelper$stateListener$1.this.this$0.isNeedMove = true;
                    DangerousAreaTipHelper$stateListener$1.this.this$0.stopPlay();
                    robotState3 = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                    if (Intrinsics.areEqual(robotState3.name(), RobotState.Pause.name())) {
                        VoicePlayer.INSTANCE.stop(DangerousAreaTipHelper$stateListener$1.this.this$0.mVoiceTask);
                    }
                    String str3 = DangerousAreaTipHelper$stateListener$1.this.this$0.TAG;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("mRobotState stopPlay mRobotState =");
                    robotState4 = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                    sb3.append(robotState4);
                    sb3.append(" isNeedMove =");
                    z = DangerousAreaTipHelper$stateListener$1.this.this$0.isNeedMove;
                    sb3.append(z);
                    Pdlog.m3273d(str3, sb3.toString());
                    String str22 = DangerousAreaTipHelper$stateListener$1.this.this$0.TAG;
                    StringBuilder sb22 = new StringBuilder();
                    sb22.append("mRobotState end ");
                    robotState5 = DangerousAreaTipHelper$stateListener$1.this.this$0.mRobotState;
                    sb22.append(robotState5);
                    Pdlog.m3273d(str22, sb22.toString());
                }
            });
        }
    }
}
