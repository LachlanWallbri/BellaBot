package com.pudutech.pd_network.bean;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: gateway.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003JU\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0001J\u0013\u0010!\u001a\u00020\t2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0005HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010¨\u0006%"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/ServiceGatewayConfig;", "", "cluster_name", "", "count", "", "isp", "cluster_id", "is_inland_china", "", "region_id", "list", "", "Lcom/pudutech/pd_network/bean/GatewayBean;", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/util/List;)V", "getCluster_id", "()Ljava/lang/String;", "getCluster_name", "getCount", "()I", "()Z", "getIsp", "getList", "()Ljava/util/List;", "getRegion_id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class ServiceGatewayConfig {
    private final String cluster_id;
    private final String cluster_name;
    private final int count;
    private final boolean is_inland_china;
    private final String isp;
    private final List<GatewayBean> list;
    private final String region_id;

    public static /* synthetic */ ServiceGatewayConfig copy$default(ServiceGatewayConfig serviceGatewayConfig, String str, int i, String str2, String str3, boolean z, String str4, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = serviceGatewayConfig.cluster_name;
        }
        if ((i2 & 2) != 0) {
            i = serviceGatewayConfig.count;
        }
        int i3 = i;
        if ((i2 & 4) != 0) {
            str2 = serviceGatewayConfig.isp;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            str3 = serviceGatewayConfig.cluster_id;
        }
        String str6 = str3;
        if ((i2 & 16) != 0) {
            z = serviceGatewayConfig.is_inland_china;
        }
        boolean z2 = z;
        if ((i2 & 32) != 0) {
            str4 = serviceGatewayConfig.region_id;
        }
        String str7 = str4;
        if ((i2 & 64) != 0) {
            list = serviceGatewayConfig.list;
        }
        return serviceGatewayConfig.copy(str, i3, str5, str6, z2, str7, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCluster_name() {
        return this.cluster_name;
    }

    /* renamed from: component2, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    /* renamed from: component3, reason: from getter */
    public final String getIsp() {
        return this.isp;
    }

    /* renamed from: component4, reason: from getter */
    public final String getCluster_id() {
        return this.cluster_id;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getIs_inland_china() {
        return this.is_inland_china;
    }

    /* renamed from: component6, reason: from getter */
    public final String getRegion_id() {
        return this.region_id;
    }

    public final List<GatewayBean> component7() {
        return this.list;
    }

    public final ServiceGatewayConfig copy(String cluster_name, int count, String isp, String cluster_id, boolean is_inland_china, String region_id, List<GatewayBean> list) {
        Intrinsics.checkParameterIsNotNull(cluster_name, "cluster_name");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        Intrinsics.checkParameterIsNotNull(cluster_id, "cluster_id");
        Intrinsics.checkParameterIsNotNull(region_id, "region_id");
        Intrinsics.checkParameterIsNotNull(list, "list");
        return new ServiceGatewayConfig(cluster_name, count, isp, cluster_id, is_inland_china, region_id, list);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceGatewayConfig)) {
            return false;
        }
        ServiceGatewayConfig serviceGatewayConfig = (ServiceGatewayConfig) other;
        return Intrinsics.areEqual(this.cluster_name, serviceGatewayConfig.cluster_name) && this.count == serviceGatewayConfig.count && Intrinsics.areEqual(this.isp, serviceGatewayConfig.isp) && Intrinsics.areEqual(this.cluster_id, serviceGatewayConfig.cluster_id) && this.is_inland_china == serviceGatewayConfig.is_inland_china && Intrinsics.areEqual(this.region_id, serviceGatewayConfig.region_id) && Intrinsics.areEqual(this.list, serviceGatewayConfig.list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.cluster_name;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.count)) * 31;
        String str2 = this.isp;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.cluster_id;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        boolean z = this.is_inland_china;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode3 + i) * 31;
        String str4 = this.region_id;
        int hashCode4 = (i2 + (str4 != null ? str4.hashCode() : 0)) * 31;
        List<GatewayBean> list = this.list;
        return hashCode4 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "ServiceGatewayConfig(cluster_name=" + this.cluster_name + ", count=" + this.count + ", isp=" + this.isp + ", cluster_id=" + this.cluster_id + ", is_inland_china=" + this.is_inland_china + ", region_id=" + this.region_id + ", list=" + this.list + ")";
    }

    public ServiceGatewayConfig(String cluster_name, int i, String isp, String cluster_id, boolean z, String region_id, List<GatewayBean> list) {
        Intrinsics.checkParameterIsNotNull(cluster_name, "cluster_name");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        Intrinsics.checkParameterIsNotNull(cluster_id, "cluster_id");
        Intrinsics.checkParameterIsNotNull(region_id, "region_id");
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.cluster_name = cluster_name;
        this.count = i;
        this.isp = isp;
        this.cluster_id = cluster_id;
        this.is_inland_china = z;
        this.region_id = region_id;
        this.list = list;
    }

    public final String getCluster_name() {
        return this.cluster_name;
    }

    public final int getCount() {
        return this.count;
    }

    public final String getIsp() {
        return this.isp;
    }

    public final String getCluster_id() {
        return this.cluster_id;
    }

    public final boolean is_inland_china() {
        return this.is_inland_china;
    }

    public final String getRegion_id() {
        return this.region_id;
    }

    public final List<GatewayBean> getList() {
        return this.list;
    }
}
