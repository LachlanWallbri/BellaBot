package com.pudutech.freeinstall_ui.adapter;

import com.pudutech.mirsdk.aidl.serialize.DockerResult;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddChargePileAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\f\"\u0004\b\u000f\u0010\u000e¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/adapter/ChargeListItem;", "", "dockerResult", "Lcom/pudutech/mirsdk/aidl/serialize/DockerResult;", "isSelect", "", "isDelete", "(Lcom/pudutech/mirsdk/aidl/serialize/DockerResult;ZZ)V", "getDockerResult", "()Lcom/pudutech/mirsdk/aidl/serialize/DockerResult;", "setDockerResult", "(Lcom/pudutech/mirsdk/aidl/serialize/DockerResult;)V", "()Z", "setDelete", "(Z)V", "setSelect", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class ChargeListItem {
    private DockerResult dockerResult;
    private boolean isDelete;
    private boolean isSelect;

    public static /* synthetic */ ChargeListItem copy$default(ChargeListItem chargeListItem, DockerResult dockerResult, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            dockerResult = chargeListItem.dockerResult;
        }
        if ((i & 2) != 0) {
            z = chargeListItem.isSelect;
        }
        if ((i & 4) != 0) {
            z2 = chargeListItem.isDelete;
        }
        return chargeListItem.copy(dockerResult, z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final DockerResult getDockerResult() {
        return this.dockerResult;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelect() {
        return this.isSelect;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsDelete() {
        return this.isDelete;
    }

    public final ChargeListItem copy(DockerResult dockerResult, boolean isSelect, boolean isDelete) {
        Intrinsics.checkParameterIsNotNull(dockerResult, "dockerResult");
        return new ChargeListItem(dockerResult, isSelect, isDelete);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChargeListItem)) {
            return false;
        }
        ChargeListItem chargeListItem = (ChargeListItem) other;
        return Intrinsics.areEqual(this.dockerResult, chargeListItem.dockerResult) && this.isSelect == chargeListItem.isSelect && this.isDelete == chargeListItem.isDelete;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        DockerResult dockerResult = this.dockerResult;
        int hashCode = (dockerResult != null ? dockerResult.hashCode() : 0) * 31;
        boolean z = this.isSelect;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.isDelete;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        return i2 + i3;
    }

    public String toString() {
        return "ChargeListItem(dockerResult=" + this.dockerResult + ", isSelect=" + this.isSelect + ", isDelete=" + this.isDelete + ")";
    }

    public ChargeListItem(DockerResult dockerResult, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(dockerResult, "dockerResult");
        this.dockerResult = dockerResult;
        this.isSelect = z;
        this.isDelete = z2;
    }

    public /* synthetic */ ChargeListItem(DockerResult dockerResult, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(dockerResult, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2);
    }

    public final DockerResult getDockerResult() {
        return this.dockerResult;
    }

    public final boolean isDelete() {
        return this.isDelete;
    }

    public final boolean isSelect() {
        return this.isSelect;
    }

    public final void setDelete(boolean z) {
        this.isDelete = z;
    }

    public final void setDockerResult(DockerResult dockerResult) {
        Intrinsics.checkParameterIsNotNull(dockerResult, "<set-?>");
        this.dockerResult = dockerResult;
    }

    public final void setSelect(boolean z) {
        this.isSelect = z;
    }
}
