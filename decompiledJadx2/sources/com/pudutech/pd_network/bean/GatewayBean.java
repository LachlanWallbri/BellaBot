package com.pudutech.pd_network.bean;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: gateway.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\n¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayBean;", "", TmpConstant.SERVICE_DESC, "", "external", "host", "name", "auth_host", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuth_host", "()Ljava/lang/String;", "setAuth_host", "(Ljava/lang/String;)V", "getDesc", "getExternal", "getHost", "setHost", "getName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class GatewayBean {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String auth_host;
    private final String desc;
    private final String external;
    private String host;
    private final String name;

    public static /* synthetic */ GatewayBean copy$default(GatewayBean gatewayBean, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gatewayBean.desc;
        }
        if ((i & 2) != 0) {
            str2 = gatewayBean.external;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = gatewayBean.host;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = gatewayBean.name;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = gatewayBean.auth_host;
        }
        return gatewayBean.copy(str, str6, str7, str8, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* renamed from: component2, reason: from getter */
    public final String getExternal() {
        return this.external;
    }

    /* renamed from: component3, reason: from getter */
    public final String getHost() {
        return this.host;
    }

    /* renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAuth_host() {
        return this.auth_host;
    }

    public final GatewayBean copy(String desc, String external, String host, String name, String auth_host) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        Intrinsics.checkParameterIsNotNull(external, "external");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(name, "name");
        return new GatewayBean(desc, external, host, name, auth_host);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GatewayBean)) {
            return false;
        }
        GatewayBean gatewayBean = (GatewayBean) other;
        return Intrinsics.areEqual(this.desc, gatewayBean.desc) && Intrinsics.areEqual(this.external, gatewayBean.external) && Intrinsics.areEqual(this.host, gatewayBean.host) && Intrinsics.areEqual(this.name, gatewayBean.name) && Intrinsics.areEqual(this.auth_host, gatewayBean.auth_host);
    }

    public int hashCode() {
        String str = this.desc;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.external;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.host;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.name;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.auth_host;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "GatewayBean(desc=" + this.desc + ", external=" + this.external + ", host=" + this.host + ", name=" + this.name + ", auth_host=" + this.auth_host + ")";
    }

    public GatewayBean(String desc, String external, String host, String name, String str) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        Intrinsics.checkParameterIsNotNull(external, "external");
        Intrinsics.checkParameterIsNotNull(host, "host");
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.desc = desc;
        this.external = external;
        this.host = host;
        this.name = name;
        this.auth_host = str;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getExternal() {
        return this.external;
    }

    public final String getHost() {
        return this.host;
    }

    public final void setHost(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.host = str;
    }

    public final String getName() {
        return this.name;
    }

    public final String getAuth_host() {
        return this.auth_host;
    }

    public final void setAuth_host(String str) {
        this.auth_host = str;
    }

    /* compiled from: gateway.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GatewayBean$Companion;", "", "()V", "fromHost", "Lcom/pudutech/pd_network/bean/GatewayBean;", "name", "", "host", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final GatewayBean fromHost(String name, String host) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(host, "host");
            return new GatewayBean("default", "", host, name, host);
        }
    }
}
