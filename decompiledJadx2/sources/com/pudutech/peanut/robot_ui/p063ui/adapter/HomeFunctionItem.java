package com.pudutech.peanut.robot_ui.p063ui.adapter;

import com.pudutech.peanut.robot_ui.p063ui.fragment.HomeFuncFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeFuncAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/adapter/HomeFunctionItem;", "", "type", "Lcom/pudutech/peanut/robot_ui/ui/fragment/HomeFuncFragment$FunctionType;", "icon", "", "title", "", "(Lcom/pudutech/peanut/robot_ui/ui/fragment/HomeFuncFragment$FunctionType;ILjava/lang/String;)V", "getIcon", "()I", "getTitle", "()Ljava/lang/String;", "getType", "()Lcom/pudutech/peanut/robot_ui/ui/fragment/HomeFuncFragment$FunctionType;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class HomeFunctionItem {
    private final int icon;
    private final String title;
    private final HomeFuncFragment.FunctionType type;

    public static /* synthetic */ HomeFunctionItem copy$default(HomeFunctionItem homeFunctionItem, HomeFuncFragment.FunctionType functionType, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            functionType = homeFunctionItem.type;
        }
        if ((i2 & 2) != 0) {
            i = homeFunctionItem.icon;
        }
        if ((i2 & 4) != 0) {
            str = homeFunctionItem.title;
        }
        return homeFunctionItem.copy(functionType, i, str);
    }

    /* renamed from: component1, reason: from getter */
    public final HomeFuncFragment.FunctionType getType() {
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

    public final HomeFunctionItem copy(HomeFuncFragment.FunctionType type, int icon, String title) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(title, "title");
        return new HomeFunctionItem(type, icon, title);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeFunctionItem)) {
            return false;
        }
        HomeFunctionItem homeFunctionItem = (HomeFunctionItem) other;
        return Intrinsics.areEqual(this.type, homeFunctionItem.type) && this.icon == homeFunctionItem.icon && Intrinsics.areEqual(this.title, homeFunctionItem.title);
    }

    public int hashCode() {
        HomeFuncFragment.FunctionType functionType = this.type;
        int hashCode = (((functionType != null ? functionType.hashCode() : 0) * 31) + Integer.hashCode(this.icon)) * 31;
        String str = this.title;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "HomeFunctionItem(type=" + this.type + ", icon=" + this.icon + ", title=" + this.title + ")";
    }

    public HomeFunctionItem(HomeFuncFragment.FunctionType type, int i, String title) {
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

    public final HomeFuncFragment.FunctionType getType() {
        return this.type;
    }
}
