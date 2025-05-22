package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TableBindAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TableWatchBindItem;", "", "mac", "", "tableBindItems", "", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/TableBindItem;", "(Ljava/lang/String;Ljava/util/List;)V", "getMac", "()Ljava/lang/String;", "getTableBindItems", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class TableWatchBindItem {
    private final String mac;
    private final List<TableBindItem> tableBindItems;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TableWatchBindItem copy$default(TableWatchBindItem tableWatchBindItem, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tableWatchBindItem.mac;
        }
        if ((i & 2) != 0) {
            list = tableWatchBindItem.tableBindItems;
        }
        return tableWatchBindItem.copy(str, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    public final List<TableBindItem> component2() {
        return this.tableBindItems;
    }

    public final TableWatchBindItem copy(String mac, List<TableBindItem> tableBindItems) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(tableBindItems, "tableBindItems");
        return new TableWatchBindItem(mac, tableBindItems);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TableWatchBindItem)) {
            return false;
        }
        TableWatchBindItem tableWatchBindItem = (TableWatchBindItem) other;
        return Intrinsics.areEqual(this.mac, tableWatchBindItem.mac) && Intrinsics.areEqual(this.tableBindItems, tableWatchBindItem.tableBindItems);
    }

    public int hashCode() {
        String str = this.mac;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<TableBindItem> list = this.tableBindItems;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "TableWatchBindItem(mac=" + this.mac + ", tableBindItems=" + this.tableBindItems + ")";
    }

    public TableWatchBindItem(String mac, List<TableBindItem> tableBindItems) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(tableBindItems, "tableBindItems");
        this.mac = mac;
        this.tableBindItems = tableBindItems;
    }

    public final String getMac() {
        return this.mac;
    }

    public final List<TableBindItem> getTableBindItems() {
        return this.tableBindItems;
    }
}
