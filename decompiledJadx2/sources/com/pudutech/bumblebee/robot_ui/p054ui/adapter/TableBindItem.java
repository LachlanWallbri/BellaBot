package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TableBindAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\b\"\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TableBindItem;", "", "name", "", "isBind", "", "isSelect", "(Ljava/lang/String;ZZ)V", "()Z", "setBind", "(Z)V", "setSelect", "getName", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class TableBindItem {
    private boolean isBind;
    private boolean isSelect;
    private final String name;

    public static /* synthetic */ TableBindItem copy$default(TableBindItem tableBindItem, String str, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tableBindItem.name;
        }
        if ((i & 2) != 0) {
            z = tableBindItem.isBind;
        }
        if ((i & 4) != 0) {
            z2 = tableBindItem.isSelect;
        }
        return tableBindItem.copy(str, z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsBind() {
        return this.isBind;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final TableBindItem copy(String name, boolean isBind, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new TableBindItem(name, isBind, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TableBindItem)) {
            return false;
        }
        TableBindItem tableBindItem = (TableBindItem) other;
        return Intrinsics.areEqual(this.name, tableBindItem.name) && this.isBind == tableBindItem.isBind && this.isSelect == tableBindItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isBind;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.isSelect;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        return i2 + i3;
    }

    public String toString() {
        return "TableBindItem(name=" + this.name + ", isBind=" + this.isBind + ", isSelect=" + this.isSelect + ")";
    }

    public TableBindItem(String name, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.isBind = z;
        this.isSelect = z2;
    }

    public /* synthetic */ TableBindItem(String str, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2);
    }

    public final String getName() {
        return this.name;
    }

    public final boolean isBind() {
        return this.isBind;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setBind(boolean z) {
        this.isBind = z;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
