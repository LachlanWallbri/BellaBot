package com.pudutech.pd_network.ability;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SupportAbilityComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/pd_network/ability/AbilityReq;", "", "mac", "", "softver", "abilities", "", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getAbilities", "()Ljava/util/List;", "getMac", "()Ljava/lang/String;", "getSoftver", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class AbilityReq {
    private final List<Integer> abilities;
    private final String mac;
    private final String softver;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AbilityReq copy$default(AbilityReq abilityReq, String str, String str2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = abilityReq.mac;
        }
        if ((i & 2) != 0) {
            str2 = abilityReq.softver;
        }
        if ((i & 4) != 0) {
            list = abilityReq.abilities;
        }
        return abilityReq.copy(str, str2, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSoftver() {
        return this.softver;
    }

    public final List<Integer> component3() {
        return this.abilities;
    }

    public final AbilityReq copy(String mac, String softver, List<Integer> abilities) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(softver, "softver");
        Intrinsics.checkParameterIsNotNull(abilities, "abilities");
        return new AbilityReq(mac, softver, abilities);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AbilityReq)) {
            return false;
        }
        AbilityReq abilityReq = (AbilityReq) other;
        return Intrinsics.areEqual(this.mac, abilityReq.mac) && Intrinsics.areEqual(this.softver, abilityReq.softver) && Intrinsics.areEqual(this.abilities, abilityReq.abilities);
    }

    public int hashCode() {
        String str = this.mac;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.softver;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        List<Integer> list = this.abilities;
        return hashCode2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "AbilityReq(mac=" + this.mac + ", softver=" + this.softver + ", abilities=" + this.abilities + ")";
    }

    public AbilityReq(String mac, String softver, List<Integer> abilities) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(softver, "softver");
        Intrinsics.checkParameterIsNotNull(abilities, "abilities");
        this.mac = mac;
        this.softver = softver;
        this.abilities = abilities;
    }

    public final List<Integer> getAbilities() {
        return this.abilities;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getSoftver() {
        return this.softver;
    }
}
