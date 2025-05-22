package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import kotlin.Metadata;

/* compiled from: SelectTimeAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectTimeItem;", "", "timeMillis", "", "isSelect", "", "(JZ)V", "()Z", "setSelect", "(Z)V", "getTimeMillis", "()J", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class SelectTimeItem {
    private boolean isSelect;
    private final long timeMillis;

    public static /* synthetic */ SelectTimeItem copy$default(SelectTimeItem selectTimeItem, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            j = selectTimeItem.timeMillis;
        }
        if ((i & 2) != 0) {
            z = selectTimeItem.isSelect;
        }
        return selectTimeItem.copy(j, z);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTimeMillis() {
        return this.timeMillis;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final SelectTimeItem copy(long timeMillis, boolean isSelect) {
        return new SelectTimeItem(timeMillis, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectTimeItem)) {
            return false;
        }
        SelectTimeItem selectTimeItem = (SelectTimeItem) other;
        return this.timeMillis == selectTimeItem.timeMillis && this.isSelect == selectTimeItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = Long.hashCode(this.timeMillis) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "SelectTimeItem(timeMillis=" + this.timeMillis + ", isSelect=" + this.isSelect + ")";
    }

    public SelectTimeItem(long j, boolean z) {
        this.timeMillis = j;
        this.isSelect = z;
    }

    public final long getTimeMillis() {
        return this.timeMillis;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
