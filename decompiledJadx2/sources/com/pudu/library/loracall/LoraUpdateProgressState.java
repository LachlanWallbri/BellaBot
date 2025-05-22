package com.pudu.library.loracall;

import kotlin.Metadata;

/* compiled from: ReceiveMsgType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\u0006\u0010\r\u001a\u00020\u000eJ\t\u0010\u000f\u001a\u00020\u000eHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, m3961d2 = {"Lcom/pudu/library/loracall/LoraUpdateProgressState;", "Lcom/pudu/library/loracall/ReceiveMsgType;", "progress", "", "(F)V", "getProgress", "()F", "component1", "copy", "equals", "", "other", "", "getShowProgress", "", "hashCode", "toString", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LoraUpdateProgressState extends ReceiveMsgType {
    private final float progress;

    public static /* synthetic */ LoraUpdateProgressState copy$default(LoraUpdateProgressState loraUpdateProgressState, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            f = loraUpdateProgressState.progress;
        }
        return loraUpdateProgressState.copy(f);
    }

    /* renamed from: component1, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    public final LoraUpdateProgressState copy(float progress) {
        return new LoraUpdateProgressState(progress);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof LoraUpdateProgressState) && Float.compare(this.progress, ((LoraUpdateProgressState) other).progress) == 0;
        }
        return true;
    }

    public int hashCode() {
        return Float.hashCode(this.progress);
    }

    public String toString() {
        return "LoraUpdateProgressState(progress=" + this.progress + ")";
    }

    public LoraUpdateProgressState(float f) {
        super(null);
        this.progress = f;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final int getShowProgress() {
        return (int) (this.progress * 100);
    }
}
