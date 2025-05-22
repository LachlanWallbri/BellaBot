package com.pudutech.bumblebee.robot_ui.manager;

import com.pudu.library.loracall.BaseMsg;
import kotlin.Metadata;

/* compiled from: lora_bean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/LoRaMsgRobotState;", "Lcom/pudu/library/loracall/BaseMsg;", "isBusy", "", "(Z)V", "data", "", "getData", "()[B", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class LoRaMsgRobotState extends BaseMsg {
    private final boolean isBusy;
    private final byte[] data = {(byte) 128, (byte) 1, this.isBusy ? 1 : 0};

    public static /* synthetic */ LoRaMsgRobotState copy$default(LoRaMsgRobotState loRaMsgRobotState, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = loRaMsgRobotState.isBusy;
        }
        return loRaMsgRobotState.copy(z);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsBusy() {
        return this.isBusy;
    }

    public final LoRaMsgRobotState copy(boolean isBusy) {
        return new LoRaMsgRobotState(isBusy);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LoRaMsgRobotState) && this.isBusy == ((LoRaMsgRobotState) other).isBusy;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.isBusy;
        if (z) {
            return 1;
        }
        return z ? 1 : 0;
    }

    public String toString() {
        return "LoRaMsgRobotState(isBusy=" + this.isBusy + ")";
    }

    public LoRaMsgRobotState(boolean z) {
        this.isBusy = z;
    }

    public final boolean isBusy() {
        return this.isBusy;
    }

    @Override // com.pudu.library.loracall.BaseMsg
    public byte[] getData() {
        return this.data;
    }
}
