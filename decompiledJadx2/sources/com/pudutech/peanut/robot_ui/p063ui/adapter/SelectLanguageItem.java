package com.pudutech.peanut.robot_ui.p063ui.adapter;

import com.pudutech.resources.language.Option;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectLanguageAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/SelectLanguageItem;", "", "op", "Lcom/pudutech/resources/language/Option;", "isSelect", "", "(Lcom/pudutech/resources/language/Option;Z)V", "()Z", "setSelect", "(Z)V", "getOp", "()Lcom/pudutech/resources/language/Option;", "setOp", "(Lcom/pudutech/resources/language/Option;)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class SelectLanguageItem {
    private boolean isSelect;
    private Option op;

    public static /* synthetic */ SelectLanguageItem copy$default(SelectLanguageItem selectLanguageItem, Option option, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            option = selectLanguageItem.op;
        }
        if ((i & 2) != 0) {
            z = selectLanguageItem.isSelect;
        }
        return selectLanguageItem.copy(option, z);
    }

    /* renamed from: component1, reason: from getter */
    public final Option getOp() {
        return this.op;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final SelectLanguageItem copy(Option op, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(op, "op");
        return new SelectLanguageItem(op, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectLanguageItem)) {
            return false;
        }
        SelectLanguageItem selectLanguageItem = (SelectLanguageItem) other;
        return Intrinsics.areEqual(this.op, selectLanguageItem.op) && this.isSelect == selectLanguageItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Option option = this.op;
        int hashCode = (option != null ? option.hashCode() : 0) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public String toString() {
        return "SelectLanguageItem(op=" + this.op + ", isSelect=" + this.isSelect + ")";
    }

    public SelectLanguageItem(Option op, boolean z) {
        Intrinsics.checkParameterIsNotNull(op, "op");
        this.op = op;
        this.isSelect = z;
    }

    public final Option getOp() {
        return this.op;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setOp(Option option) {
        Intrinsics.checkParameterIsNotNull(option, "<set-?>");
        this.op = option;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
