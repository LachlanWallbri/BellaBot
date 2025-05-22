package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;

/* compiled from: TrackConstant.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/TrayState;", "", "layer", "", "state", "", "(IZ)V", "getLayer", "()I", "getState", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class TrayState {
    private final int layer;
    private final boolean state;

    public static /* synthetic */ TrayState copy$default(TrayState trayState, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = trayState.layer;
        }
        if ((i2 & 2) != 0) {
            z = trayState.state;
        }
        return trayState.copy(i, z);
    }

    /* renamed from: component1, reason: from getter */
    public final int getLayer() {
        return this.layer;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getState() {
        return this.state;
    }

    public final TrayState copy(int layer, boolean state) {
        return new TrayState(layer, state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrayState)) {
            return false;
        }
        TrayState trayState = (TrayState) other;
        return this.layer == trayState.layer && this.state == trayState.state;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int i = this.layer * 31;
        boolean z = this.state;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        return i + i2;
    }

    public String toString() {
        return "TrayState(layer=" + this.layer + ", state=" + this.state + ")";
    }

    public TrayState(int i, boolean z) {
        this.layer = i;
        this.state = z;
    }

    public final int getLayer() {
        return this.layer;
    }

    public final boolean getState() {
        return this.state;
    }
}
