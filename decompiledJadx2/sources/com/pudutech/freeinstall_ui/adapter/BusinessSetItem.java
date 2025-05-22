package com.pudutech.freeinstall_ui.adapter;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BusinessSetAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001f\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0001'B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003JE\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010#\u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0006HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u0014\"\u0004\b\u0017\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013¨\u0006("}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/BusinessSetItem;", "", "name", "", AIUIConstant.KEY_CONTENT, "imageResource", "", "pointType", "isSetting", "", "isSelect", "(Ljava/lang/String;Ljava/lang/String;IIZZ)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getImageResource", "()I", "setImageResource", "(I)V", "()Z", "setSelect", "(Z)V", "setSetting", "getName", "setName", "getPointType", "setPointType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class BusinessSetItem {
    public static final int TYPE_CHARGE_DILL = 6;
    public static final int TYPE_CREATE_MAP_EXPAND = 7;
    public static final int TYPE_CRUISE = 4;
    public static final int TYPE_DOOR = 2;
    public static final int TYPE_MEAL = 1;
    public static final int TYPE_STATION = 5;
    public static final int TYPE_TABLE = 3;
    private String content;
    private int imageResource;
    private boolean isSelect;
    private boolean isSetting;
    private String name;
    private int pointType;

    public static /* synthetic */ BusinessSetItem copy$default(BusinessSetItem businessSetItem, String str, String str2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = businessSetItem.name;
        }
        if ((i3 & 2) != 0) {
            str2 = businessSetItem.content;
        }
        String str3 = str2;
        if ((i3 & 4) != 0) {
            i = businessSetItem.imageResource;
        }
        int i4 = i;
        if ((i3 & 8) != 0) {
            i2 = businessSetItem.pointType;
        }
        int i5 = i2;
        if ((i3 & 16) != 0) {
            z = businessSetItem.isSetting;
        }
        boolean z3 = z;
        if ((i3 & 32) != 0) {
            z2 = businessSetItem.isSelect;
        }
        return businessSetItem.copy(str, str3, i4, i5, z3, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component3, reason: from getter */
    public final int getImageResource() {
        return this.imageResource;
    }

    /* renamed from: component4, reason: from getter */
    public final int getPointType() {
        return this.pointType;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsSetting() {
        return this.isSetting;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    public final BusinessSetItem copy(String name, String content, int imageResource, int pointType, boolean isSetting, boolean isSelect) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(content, "content");
        return new BusinessSetItem(name, content, imageResource, pointType, isSetting, isSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BusinessSetItem)) {
            return false;
        }
        BusinessSetItem businessSetItem = (BusinessSetItem) other;
        return Intrinsics.areEqual(this.name, businessSetItem.name) && Intrinsics.areEqual(this.content, businessSetItem.content) && this.imageResource == businessSetItem.imageResource && this.pointType == businessSetItem.pointType && this.isSetting == businessSetItem.isSetting && this.isSelect == businessSetItem.isSelect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.content;
        int hashCode2 = (((((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.imageResource)) * 31) + Integer.hashCode(this.pointType)) * 31;
        boolean z = this.isSetting;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z2 = this.isSelect;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        return i2 + i3;
    }

    public String toString() {
        return "BusinessSetItem(name=" + this.name + ", content=" + this.content + ", imageResource=" + this.imageResource + ", pointType=" + this.pointType + ", isSetting=" + this.isSetting + ", isSelect=" + this.isSelect + ")";
    }

    public BusinessSetItem(String name, String content, int i, int i2, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.name = name;
        this.content = content;
        this.imageResource = i;
        this.pointType = i2;
        this.isSetting = z;
        this.isSelect = z2;
    }

    public final String getContent() {
        return this.content;
    }

    public final int getImageResource() {
        return this.imageResource;
    }

    public final String getName() {
        return this.name;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final void setImageResource(int i) {
        this.imageResource = i;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public /* synthetic */ BusinessSetItem(String str, String str2, int i, int i2, boolean z, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, i2, (i3 & 16) != 0 ? false : z, (i3 & 32) != 0 ? false : z2);
    }

    public final int getPointType() {
        return this.pointType;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final boolean isSetting() {
        return this.isSetting;
    }

    public final void setPointType(int i) {
        this.pointType = i;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }

    public final void setSetting(boolean z) {
        this.isSetting = z;
    }
}
