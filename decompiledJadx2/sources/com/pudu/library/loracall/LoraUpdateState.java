package com.pudu.library.loracall;

import kotlin.Metadata;

/* compiled from: ReceiveMsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, m3961d2 = {"Lcom/pudu/library/loracall/LoraUpdateState;", "Lcom/pudu/library/loracall/ReceiveMsgType;", "type", "", "state", "(II)V", "getState", "()I", "getType", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LoraUpdateState extends ReceiveMsgType {
    private final int state;
    private final int type;

    public static /* synthetic */ LoraUpdateState copy$default(LoraUpdateState loraUpdateState, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = loraUpdateState.type;
        }
        if ((i3 & 2) != 0) {
            i2 = loraUpdateState.state;
        }
        return loraUpdateState.copy(i, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final int getState() {
        return this.state;
    }

    public final LoraUpdateState copy(int type, int state) {
        return new LoraUpdateState(type, state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoraUpdateState)) {
            return false;
        }
        LoraUpdateState loraUpdateState = (LoraUpdateState) other;
        return this.type == loraUpdateState.type && this.state == loraUpdateState.state;
    }

    public int hashCode() {
        return (Integer.hashCode(this.type) * 31) + Integer.hashCode(this.state);
    }

    public String toString() {
        return "LoraUpdateState(type=" + this.type + ", state=" + this.state + ")";
    }

    public LoraUpdateState(int i, int i2) {
        super(null);
        this.type = i;
        this.state = i2;
    }

    public final int getState() {
        return this.state;
    }

    public final int getType() {
        return this.type;
    }
}
