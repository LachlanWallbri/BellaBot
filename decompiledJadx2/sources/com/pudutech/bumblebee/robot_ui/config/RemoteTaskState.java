package com.pudutech.bumblebee.robot_ui.config;

import kotlin.Metadata;

/* compiled from: RemoteTaskState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/config/RemoteTaskState;", "", "stateId", "", "(Ljava/lang/String;II)V", "getStateId", "()I", "PENDING", "MOVING", "ARRIVED", "CANCELED", "FINISHED", "CALLPOINTILLEGAL", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public enum RemoteTaskState {
    PENDING(1),
    MOVING(2),
    ARRIVED(3),
    CANCELED(4),
    FINISHED(5),
    CALLPOINTILLEGAL(101);

    private final int stateId;

    RemoteTaskState(int i) {
        this.stateId = i;
    }

    public final int getStateId() {
        return this.stateId;
    }
}
