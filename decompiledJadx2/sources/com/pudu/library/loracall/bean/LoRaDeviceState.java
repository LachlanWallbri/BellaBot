package com.pudu.library.loracall.bean;

import kotlin.Metadata;

/* compiled from: LoRaDeviceState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m3961d2 = {"Lcom/pudu/library/loracall/bean/LoRaDeviceState;", "", "state", "", "(Ljava/lang/String;II)V", "getState", "()I", "IDLE", "BUSY", "BINDING", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum LoRaDeviceState {
    IDLE(0),
    BUSY(1),
    BINDING(2);

    private final int state;

    LoRaDeviceState(int i) {
        this.state = i;
    }

    public final int getState() {
        return this.state;
    }
}
