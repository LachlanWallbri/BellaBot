package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TablesClassifyAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TablesClassifyItem;", "", "name", "", "isSelect", "", "(Ljava/lang/String;Z)V", "()Z", "setSelect", "(Z)V", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class TablesClassifyItem {
    private boolean isSelect;
    private final String name;

    public static /* synthetic */ TablesClassifyItem copy$default(TablesClassifyItem tablesClassifyItem, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tablesClassifyItem.name;
        }
        if ((i & 2) != 0) {
            z = tablesClassifyItem.isSelect;
        }
        return tablesClassifyItem.copy(str, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final TablesClassifyItem copy(String name, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new TablesClassifyItem(name, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TablesClassifyItem)) {
            return false;
        }
        TablesClassifyItem tablesClassifyItem = (TablesClassifyItem) other;
        return Intrinsics.areEqual(this.name, tablesClassifyItem.name) && this.isSelect == tablesClassifyItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "TablesClassifyItem(name=" + this.name + ", isSelect=" + this.isSelect + ")";
    }

    public TablesClassifyItem(String name, boolean z) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.name = name;
        this.isSelect = z;
    }

    public /* synthetic */ TablesClassifyItem(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z);
    }

    public final String getName() {
        return this.name;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
