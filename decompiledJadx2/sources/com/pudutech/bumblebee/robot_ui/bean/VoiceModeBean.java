package com.pudutech.bumblebee.robot_ui.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceModeBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\bHÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/bean/VoiceModeBean;", "", "isSelect", "", "name", "", "isUpdate", "viewType", "", "(ZLjava/lang/String;ZI)V", "()Z", "getName", "()Ljava/lang/String;", "getViewType", "()I", "setViewType", "(I)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class VoiceModeBean {
    private final boolean isSelect;
    private final boolean isUpdate;
    private final String name;
    private int viewType;

    public static /* synthetic */ VoiceModeBean copy$default(VoiceModeBean voiceModeBean, boolean z, String str, boolean z2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = voiceModeBean.isSelect;
        }
        if ((i2 & 2) != 0) {
            str = voiceModeBean.name;
        }
        if ((i2 & 4) != 0) {
            z2 = voiceModeBean.isUpdate;
        }
        if ((i2 & 8) != 0) {
            i = voiceModeBean.viewType;
        }
        return voiceModeBean.copy(z, str, z2, i);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsUpdate() {
        return this.isUpdate;
    }

    /* renamed from: component4, reason: from getter */
    public final int getViewType() {
        return this.viewType;
    }

    public final VoiceModeBean copy(boolean isSelect, String name, boolean isUpdate, int viewType) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new VoiceModeBean(isSelect, name, isUpdate, viewType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VoiceModeBean)) {
            return false;
        }
        VoiceModeBean voiceModeBean = (VoiceModeBean) other;
        return this.isSelect == voiceModeBean.isSelect && Intrinsics.areEqual(this.name, voiceModeBean.name) && this.isUpdate == voiceModeBean.isUpdate && this.viewType == voiceModeBean.viewType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public int hashCode() {
        boolean z = this.isSelect;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        String str = this.name;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        boolean z2 = this.isUpdate;
        return ((hashCode + (z2 ? 1 : z2 ? 1 : 0)) * 31) + Integer.hashCode(this.viewType);
    }

    public String toString() {
        return "VoiceModeBean(isSelect=" + this.isSelect + ", name=" + this.name + ", isUpdate=" + this.isUpdate + ", viewType=" + this.viewType + ")";
    }

    public VoiceModeBean(boolean z, String name, boolean z2, int i) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.isSelect = z;
        this.name = name;
        this.isUpdate = z2;
        this.viewType = i;
    }

    public final String getName() {
        return this.name;
    }

    public final int getViewType() {
        return this.viewType;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final boolean isUpdate() {
        return this.isUpdate;
    }

    public final void setViewType(int i) {
        this.viewType = i;
    }
}
