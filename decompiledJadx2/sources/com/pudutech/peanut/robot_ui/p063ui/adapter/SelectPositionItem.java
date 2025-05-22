package com.pudutech.peanut.robot_ui.p063ui.adapter;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectPositionAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectPositionItem;", "", "id", "", "isSelect", "", "(Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "()Z", "setSelect", "(Z)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class SelectPositionItem {
    private String id;
    private boolean isSelect;

    public static /* synthetic */ SelectPositionItem copy$default(SelectPositionItem selectPositionItem, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = selectPositionItem.id;
        }
        if ((i & 2) != 0) {
            z = selectPositionItem.isSelect;
        }
        return selectPositionItem.copy(str, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final SelectPositionItem copy(String id, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        return new SelectPositionItem(id, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectPositionItem)) {
            return false;
        }
        SelectPositionItem selectPositionItem = (SelectPositionItem) other;
        return Intrinsics.areEqual(this.id, selectPositionItem.id) && this.isSelect == selectPositionItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "SelectPositionItem(id=" + this.id + ", isSelect=" + this.isSelect + ")";
    }

    public SelectPositionItem(String id, boolean z) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        this.id = id;
        this.isSelect = z;
    }

    public final String getId() {
        return this.id;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
