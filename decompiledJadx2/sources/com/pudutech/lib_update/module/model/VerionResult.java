package com.pudutech.lib_update.module.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: VerionResult.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/lib_update/module/model/VerionResult;", "", "available", "", "version", "Lcom/pudutech/lib_update/module/model/Version;", "(ZLcom/pudutech/lib_update/module/model/Version;)V", "getAvailable", "()Z", "setAvailable", "(Z)V", "getVersion", "()Lcom/pudutech/lib_update/module/model/Version;", "setVersion", "(Lcom/pudutech/lib_update/module/model/Version;)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class VerionResult {
    private boolean available;
    private Version version;

    public VerionResult() {
        this(false, null, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ VerionResult copy$default(VerionResult verionResult, boolean z, Version version, int i, Object obj) {
        if ((i & 1) != 0) {
            z = verionResult.available;
        }
        if ((i & 2) != 0) {
            version = verionResult.version;
        }
        return verionResult.copy(z, version);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getAvailable() {
        return this.available;
    }

    /* renamed from: component2, reason: from getter */
    public final Version getVersion() {
        return this.version;
    }

    public final VerionResult copy(boolean available, Version version) {
        return new VerionResult(available, version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VerionResult)) {
            return false;
        }
        VerionResult verionResult = (VerionResult) other;
        return this.available == verionResult.available && Intrinsics.areEqual(this.version, verionResult.version);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.available;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        Version version = this.version;
        return i + (version != null ? version.hashCode() : 0);
    }

    public String toString() {
        return "VerionResult(available=" + this.available + ", version=" + this.version + ")";
    }

    public VerionResult(boolean z, Version version) {
        this.available = z;
        this.version = version;
    }

    public /* synthetic */ VerionResult(boolean z, Version version, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? (Version) null : version);
    }

    public final boolean getAvailable() {
        return this.available;
    }

    public final Version getVersion() {
        return this.version;
    }

    public final void setAvailable(boolean z) {
        this.available = z;
    }

    public final void setVersion(Version version) {
        this.version = version;
    }
}
