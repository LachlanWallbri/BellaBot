package com.pudutech.lib_update.module.model;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SwitchVersionResult.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0007J \u0010\u000b\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\fJ\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/lib_update/module/model/SwitchVersionResult;", "", "version_list", "", "Lcom/pudutech/lib_update/module/model/Version;", "([Lcom/pudutech/lib_update/module/model/Version;)V", "getVersion_list", "()[Lcom/pudutech/lib_update/module/model/Version;", "setVersion_list", "[Lcom/pudutech/lib_update/module/model/Version;", "component1", "copy", "([Lcom/pudutech/lib_update/module/model/Version;)Lcom/pudutech/lib_update/module/model/SwitchVersionResult;", "equals", "", "other", "hashCode", "", "toString", "", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class SwitchVersionResult {
    private Version[] version_list;

    public SwitchVersionResult() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ SwitchVersionResult copy$default(SwitchVersionResult switchVersionResult, Version[] versionArr, int i, Object obj) {
        if ((i & 1) != 0) {
            versionArr = switchVersionResult.version_list;
        }
        return switchVersionResult.copy(versionArr);
    }

    /* renamed from: component1, reason: from getter */
    public final Version[] getVersion_list() {
        return this.version_list;
    }

    public final SwitchVersionResult copy(Version[] version_list) {
        return new SwitchVersionResult(version_list);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof SwitchVersionResult) && Intrinsics.areEqual(this.version_list, ((SwitchVersionResult) other).version_list);
        }
        return true;
    }

    public int hashCode() {
        Version[] versionArr = this.version_list;
        if (versionArr != null) {
            return Arrays.hashCode(versionArr);
        }
        return 0;
    }

    public String toString() {
        return "SwitchVersionResult(version_list=" + Arrays.toString(this.version_list) + ")";
    }

    public SwitchVersionResult(Version[] versionArr) {
        this.version_list = versionArr;
    }

    public /* synthetic */ SwitchVersionResult(Version[] versionArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Version[]) null : versionArr);
    }

    public final Version[] getVersion_list() {
        return this.version_list;
    }

    public final void setVersion_list(Version[] versionArr) {
        this.version_list = versionArr;
    }
}
