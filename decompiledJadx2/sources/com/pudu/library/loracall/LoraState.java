package com.pudu.library.loracall;

import kotlin.Metadata;

/* compiled from: ReceiveMsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudu/library/loracall/LoraState;", "Lcom/pudu/library/loracall/ReceiveMsgType;", "state", "", "(I)V", "getState", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LoraState extends ReceiveMsgType {
    private final int state;

    public static /* synthetic */ LoraState copy$default(LoraState loraState, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = loraState.state;
        }
        return loraState.copy(i);
    }

    /* renamed from: component1, reason: from getter */
    public final int getState() {
        return this.state;
    }

    public final LoraState copy(int state) {
        return new LoraState(state);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LoraState) && this.state == ((LoraState) other).state;
        }
        return true;
    }

    public int hashCode() {
        return Integer.hashCode(this.state);
    }

    public String toString() {
        return "LoraState(state=" + this.state + ")";
    }

    public LoraState(int i) {
        super(null);
        this.state = i;
    }

    public final int getState() {
        return this.state;
    }
}
