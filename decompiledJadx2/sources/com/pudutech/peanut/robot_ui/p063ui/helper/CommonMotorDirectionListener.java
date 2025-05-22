package com.pudutech.peanut.robot_ui.p063ui.helper;

import com.pudutech.mirsdkwrap.lib.interf.MotorDirection;
import com.pudutech.mirsdkwrap.lib.interf.MotorDirectionListener;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CommonMotorDirectionListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/helper/CommonMotorDirectionListener;", "Lcom/pudutech/mirsdkwrap/lib/interf/MotorDirectionListener;", "()V", "TAG", "", "onMotorDirectionChange", "", "motorDirection", "Lcom/pudutech/mirsdkwrap/lib/interf/MotorDirection;", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CommonMotorDirectionListener implements MotorDirectionListener {
    private final String TAG = "CommonMotorDirectionListener";

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MotorDirection.values().length];

        static {
            $EnumSwitchMapping$0[MotorDirection.LEFT.ordinal()] = 1;
            $EnumSwitchMapping$0[MotorDirection.RIGHT.ordinal()] = 2;
            $EnumSwitchMapping$0[MotorDirection.BRAKE.ordinal()] = 3;
            $EnumSwitchMapping$0[MotorDirection.FORWARD.ordinal()] = 4;
            $EnumSwitchMapping$0[MotorDirection.STOP.ordinal()] = 5;
        }
    }

    @Override // com.pudutech.mirsdkwrap.lib.interf.MotorDirectionListener
    public void onMotorDirectionChange(MotorDirection motorDirection) {
        Intrinsics.checkParameterIsNotNull(motorDirection, "motorDirection");
        int i = WhenMappings.$EnumSwitchMapping$0[motorDirection.ordinal()];
        if (i == 1) {
            LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.LEFT);
            return;
        }
        if (i == 2) {
            LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.RIGHT);
            return;
        }
        if (i != 3 && i == 4) {
            LightPlayManager.INSTANCE.playMove(LightPlayManager.MoveEvent.FORWARD);
        }
    }
}
