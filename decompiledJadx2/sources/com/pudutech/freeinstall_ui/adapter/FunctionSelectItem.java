package com.pudutech.freeinstall_ui.adapter;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.freeinstall_ui.utils.BusinessFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FunctionSelectAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\fJ\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\t\u0010$\u001a\u00020\nHÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003JE\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\nHÆ\u0001J\u0013\u0010'\u001a\u00020\n2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0006HÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0016\"\u0004\b\u001d\u0010\u0018R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0012\"\u0004\b\u001f\u0010\u0014¨\u0006+"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/FunctionSelectItem;", "", "name", "", AIUIConstant.KEY_CONTENT, "imageResource", "", "businessFunction", "Lcom/pudutech/freeinstall_ui/utils/BusinessFunction;", "isSelect", "", "hasSetting", "(Ljava/lang/String;Ljava/lang/String;ILcom/pudutech/freeinstall_ui/utils/BusinessFunction;ZZ)V", "getBusinessFunction", "()Lcom/pudutech/freeinstall_ui/utils/BusinessFunction;", "setBusinessFunction", "(Lcom/pudutech/freeinstall_ui/utils/BusinessFunction;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getHasSetting", "()Z", "setHasSetting", "(Z)V", "getImageResource", "()I", "setImageResource", "(I)V", "setSelect", "getName", "setName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class FunctionSelectItem {
    private BusinessFunction businessFunction;
    private String content;
    private boolean hasSetting;
    private int imageResource;
    private boolean isSelect;
    private String name;

    public static /* synthetic */ FunctionSelectItem copy$default(FunctionSelectItem functionSelectItem, String str, String str2, int i, BusinessFunction businessFunction, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = functionSelectItem.name;
        }
        if ((i2 & 2) != 0) {
            str2 = functionSelectItem.content;
        }
        String str3 = str2;
        if ((i2 & 4) != 0) {
            i = functionSelectItem.imageResource;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            businessFunction = functionSelectItem.businessFunction;
        }
        BusinessFunction businessFunction2 = businessFunction;
        if ((i2 & 16) != 0) {
            z = functionSelectItem.isSelect;
        }
        boolean z3 = z;
        if ((i2 & 32) != 0) {
            z2 = functionSelectItem.hasSetting;
        }
        return functionSelectItem.copy(str, str3, i3, businessFunction2, z3, z2);
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
    public final BusinessFunction getBusinessFunction() {
        return this.businessFunction;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getHasSetting() {
        return this.hasSetting;
    }

    public final FunctionSelectItem copy(String name, String content, int imageResource, BusinessFunction businessFunction, boolean isSelect, boolean hasSetting) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(businessFunction, "businessFunction");
        return new FunctionSelectItem(name, content, imageResource, businessFunction, isSelect, hasSetting);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FunctionSelectItem)) {
            return false;
        }
        FunctionSelectItem functionSelectItem = (FunctionSelectItem) other;
        return Intrinsics.areEqual(this.name, functionSelectItem.name) && Intrinsics.areEqual(this.content, functionSelectItem.content) && this.imageResource == functionSelectItem.imageResource && Intrinsics.areEqual(this.businessFunction, functionSelectItem.businessFunction) && this.isSelect == functionSelectItem.isSelect && this.hasSetting == functionSelectItem.hasSetting;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.content;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.imageResource)) * 31;
        BusinessFunction businessFunction = this.businessFunction;
        int hashCode3 = (hashCode2 + (businessFunction != null ? businessFunction.hashCode() : 0)) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode3 + i) * 31;
        boolean z2 = this.hasSetting;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        return i2 + i3;
    }

    public String toString() {
        return "FunctionSelectItem(name=" + this.name + ", content=" + this.content + ", imageResource=" + this.imageResource + ", businessFunction=" + this.businessFunction + ", isSelect=" + this.isSelect + ", hasSetting=" + this.hasSetting + ")";
    }

    public FunctionSelectItem(String name, String content, int i, BusinessFunction businessFunction, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(businessFunction, "businessFunction");
        this.name = name;
        this.content = content;
        this.imageResource = i;
        this.businessFunction = businessFunction;
        this.isSelect = z;
        this.hasSetting = z2;
    }

    public /* synthetic */ FunctionSelectItem(String str, String str2, int i, BusinessFunction businessFunction, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, businessFunction, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2);
    }

    public final BusinessFunction getBusinessFunction() {
        return this.businessFunction;
    }

    public final String getContent() {
        return this.content;
    }

    public final boolean getHasSetting() {
        return this.hasSetting;
    }

    public final int getImageResource() {
        return this.imageResource;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setBusinessFunction(BusinessFunction businessFunction) {
        Intrinsics.checkParameterIsNotNull(businessFunction, "<set-?>");
        this.businessFunction = businessFunction;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final void setHasSetting(boolean z) {
        this.hasSetting = z;
    }

    public final void setImageResource(int i) {
        this.imageResource = i;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
