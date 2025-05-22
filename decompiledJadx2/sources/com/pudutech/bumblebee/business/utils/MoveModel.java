package com.pudutech.bumblebee.business.utils;

import kotlin.Metadata;

/* compiled from: MoveModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/utils/MoveModel;", "", "()V", "BRAKE_DIFF", "", "FORWARD_DIFF", "TAG", "", "TURN_DIFF", "lastCenterSpeed_mps", "state", "Lcom/pudutech/bumblebee/business/utils/MoveModel$State;", "updateFSM", "center", "rotate", "State", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MoveModel {
    private double lastCenterSpeed_mps;
    private final String TAG = "MoveModel";
    private final double TURN_DIFF = 0.5d;
    private final double FORWARD_DIFF = 0.3d;
    private final double BRAKE_DIFF = 0.08d;
    private State state = State.STOP;

    /* compiled from: MoveModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/utils/MoveModel$State;", "", "(Ljava/lang/String;I)V", "STOP", "FORWARD", "BRAKE", "TURN_LEFT", "TURN_RIGHT", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum State {
        STOP,
        FORWARD,
        BRAKE,
        TURN_LEFT,
        TURN_RIGHT
    }

    public final State updateFSM(double center, double rotate) {
        double d = this.TURN_DIFF;
        if (rotate > d) {
            this.state = State.TURN_LEFT;
        } else if (rotate < (-d)) {
            this.state = State.TURN_RIGHT;
        } else if (center > 0.01d && this.lastCenterSpeed_mps - center > this.BRAKE_DIFF) {
            this.state = State.BRAKE;
        } else {
            if (this.state == State.BRAKE) {
                this.state = State.FORWARD;
            }
            if (Math.abs(rotate) < this.FORWARD_DIFF) {
                this.state = State.FORWARD;
            }
        }
        this.lastCenterSpeed_mps = center;
        return this.state;
    }
}
