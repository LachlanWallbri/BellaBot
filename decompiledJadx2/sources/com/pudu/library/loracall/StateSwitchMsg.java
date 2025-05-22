package com.pudu.library.loracall;

import com.pudu.library.loracall.bean.LoRaDeviceState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SendMsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudu/library/loracall/StateSwitchMsg;", "Lcom/pudu/library/loracall/BaseMsg;", "deviceState", "Lcom/pudu/library/loracall/bean/LoRaDeviceState;", "(Lcom/pudu/library/loracall/bean/LoRaDeviceState;)V", "data", "", "getData", "()[B", "getDeviceState", "()Lcom/pudu/library/loracall/bean/LoRaDeviceState;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class StateSwitchMsg extends BaseMsg {
    private final byte[] data;
    private final LoRaDeviceState deviceState;

    public static /* synthetic */ StateSwitchMsg copy$default(StateSwitchMsg stateSwitchMsg, LoRaDeviceState loRaDeviceState, int i, Object obj) {
        if ((i & 1) != 0) {
            loRaDeviceState = stateSwitchMsg.deviceState;
        }
        return stateSwitchMsg.copy(loRaDeviceState);
    }

    /* renamed from: component1, reason: from getter */
    public final LoRaDeviceState getDeviceState() {
        return this.deviceState;
    }

    public final StateSwitchMsg copy(LoRaDeviceState deviceState) {
        Intrinsics.checkParameterIsNotNull(deviceState, "deviceState");
        return new StateSwitchMsg(deviceState);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof StateSwitchMsg) && Intrinsics.areEqual(this.deviceState, ((StateSwitchMsg) other).deviceState);
        }
        return true;
    }

    public int hashCode() {
        LoRaDeviceState loRaDeviceState = this.deviceState;
        if (loRaDeviceState != null) {
            return loRaDeviceState.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "StateSwitchMsg(deviceState=" + this.deviceState + ")";
    }

    public final LoRaDeviceState getDeviceState() {
        return this.deviceState;
    }

    public StateSwitchMsg(LoRaDeviceState deviceState) {
        Intrinsics.checkParameterIsNotNull(deviceState, "deviceState");
        this.deviceState = deviceState;
        this.data = new byte[]{(byte) 128, 1, (byte) this.deviceState.getState()};
    }

    @Override // com.pudu.library.loracall.BaseMsg
    public byte[] getData() {
        return this.data;
    }
}
