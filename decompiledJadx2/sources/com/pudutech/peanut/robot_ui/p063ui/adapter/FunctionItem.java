package com.pudutech.peanut.robot_ui.p063ui.adapter;

import com.pudutech.peanut.robot_ui.p063ui.dialog.HomeSettingDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FunctionDialogAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/FunctionItem;", "", "type", "Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog$FunctionType;", "icon", "", "title", "", "(Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog$FunctionType;ILjava/lang/String;)V", "getIcon", "()I", "getTitle", "()Ljava/lang/String;", "getType", "()Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog$FunctionType;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class FunctionItem {
    private final int icon;
    private final String title;
    private final HomeSettingDialog.FunctionType type;

    public static /* synthetic */ FunctionItem copy$default(FunctionItem functionItem, HomeSettingDialog.FunctionType functionType, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            functionType = functionItem.type;
        }
        if ((i2 & 2) != 0) {
            i = functionItem.icon;
        }
        if ((i2 & 4) != 0) {
            str = functionItem.title;
        }
        return functionItem.copy(functionType, i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final HomeSettingDialog.FunctionType getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final int getIcon() {
        return this.icon;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final FunctionItem copy(HomeSettingDialog.FunctionType type, int icon, String title) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(title, "title");
        return new FunctionItem(type, icon, title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunctionItem)) {
            return false;
        }
        FunctionItem functionItem = (FunctionItem) other;
        return Intrinsics.areEqual(this.type, functionItem.type) && this.icon == functionItem.icon && Intrinsics.areEqual(this.title, functionItem.title);
    }

    public int hashCode() {
        HomeSettingDialog.FunctionType functionType = this.type;
        int hashCode = (((functionType != null ? functionType.hashCode() : 0) * 31) + Integer.hashCode(this.icon)) * 31;
        String str = this.title;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "FunctionItem(type=" + this.type + ", icon=" + this.icon + ", title=" + this.title + ")";
    }

    public FunctionItem(HomeSettingDialog.FunctionType type, int i, String title) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(title, "title");
        this.type = type;
        this.icon = i;
        this.title = title;
    }

    public final int getIcon() {
        return this.icon;
    }

    public final String getTitle() {
        return this.title;
    }

    public final HomeSettingDialog.FunctionType getType() {
        return this.type;
    }
}
