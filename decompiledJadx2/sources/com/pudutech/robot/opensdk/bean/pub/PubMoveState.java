package com.pudutech.robot.opensdk.bean.pub;

import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import kotlin.Metadata;

/* compiled from: MoveState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/pub/PubMoveState;", "", "id", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getId", "()Ljava/lang/String;", "Approaching", "Arrive", "Avoid", "Moving", "Pause", "Resume", "Stuck", MoveError.LEVEL_ERROR, "Idle", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public enum PubMoveState {
    Approaching("Approaching"),
    Arrive("Arrive"),
    Avoid("Avoid"),
    Moving("Moving"),
    Pause("Pause"),
    Resume("Resume"),
    Stuck("Stuck"),
    Error(MoveError.LEVEL_ERROR),
    Idle("Idle");

    private final String id;

    PubMoveState(String str) {
        this.id = str;
    }

    public final String getId() {
        return this.id;
    }
}
