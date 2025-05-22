package com.pudutech.bumblebee.robot_ui.manager;

import com.pudu.library.loracall.bean.LoRaConfigParam;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: lora_bean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\r\u0010\u001f\u001a\u00020 H\u0000¢\u0006\u0002\b!J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\n¨\u0006#"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/GatewayConfig;", "", "receivePL", "", "networkP1", "networkP2", "networkId", "broadCastTime", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBroadCastTime", "()Ljava/lang/String;", "setBroadCastTime", "(Ljava/lang/String;)V", "getNetworkId", "setNetworkId", "getNetworkP1", "setNetworkP1", "getNetworkP2", "setNetworkP2", "getReceivePL", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toLoRaConfig", "Lcom/pudu/library/loracall/bean/LoRaConfigParam;", "toLoRaConfig$robot_ui_robotRelease", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final /* data */ class GatewayConfig {
    private String broadCastTime;
    private String networkId;
    private String networkP1;
    private String networkP2;
    private final String receivePL;

    public static /* synthetic */ GatewayConfig copy$default(GatewayConfig gatewayConfig, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gatewayConfig.receivePL;
        }
        if ((i & 2) != 0) {
            str2 = gatewayConfig.networkP1;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = gatewayConfig.networkP2;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = gatewayConfig.networkId;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = gatewayConfig.broadCastTime;
        }
        return gatewayConfig.copy(str, str6, str7, str8, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getReceivePL() {
        return this.receivePL;
    }

    /* renamed from: component2, reason: from getter */
    public final String getNetworkP1() {
        return this.networkP1;
    }

    /* renamed from: component3, reason: from getter */
    public final String getNetworkP2() {
        return this.networkP2;
    }

    /* renamed from: component4, reason: from getter */
    public final String getNetworkId() {
        return this.networkId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getBroadCastTime() {
        return this.broadCastTime;
    }

    public final GatewayConfig copy(String receivePL, String networkP1, String networkP2, String networkId, String broadCastTime) {
        Intrinsics.checkParameterIsNotNull(receivePL, "receivePL");
        Intrinsics.checkParameterIsNotNull(networkP1, "networkP1");
        Intrinsics.checkParameterIsNotNull(networkP2, "networkP2");
        Intrinsics.checkParameterIsNotNull(networkId, "networkId");
        Intrinsics.checkParameterIsNotNull(broadCastTime, "broadCastTime");
        return new GatewayConfig(receivePL, networkP1, networkP2, networkId, broadCastTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GatewayConfig)) {
            return false;
        }
        GatewayConfig gatewayConfig = (GatewayConfig) other;
        return Intrinsics.areEqual(this.receivePL, gatewayConfig.receivePL) && Intrinsics.areEqual(this.networkP1, gatewayConfig.networkP1) && Intrinsics.areEqual(this.networkP2, gatewayConfig.networkP2) && Intrinsics.areEqual(this.networkId, gatewayConfig.networkId) && Intrinsics.areEqual(this.broadCastTime, gatewayConfig.broadCastTime);
    }

    public int hashCode() {
        String str = this.receivePL;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.networkP1;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.networkP2;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.networkId;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.broadCastTime;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "GatewayConfig(receivePL=" + this.receivePL + ", networkP1=" + this.networkP1 + ", networkP2=" + this.networkP2 + ", networkId=" + this.networkId + ", broadCastTime=" + this.broadCastTime + ")";
    }

    public GatewayConfig(String receivePL, String networkP1, String networkP2, String networkId, String broadCastTime) {
        Intrinsics.checkParameterIsNotNull(receivePL, "receivePL");
        Intrinsics.checkParameterIsNotNull(networkP1, "networkP1");
        Intrinsics.checkParameterIsNotNull(networkP2, "networkP2");
        Intrinsics.checkParameterIsNotNull(networkId, "networkId");
        Intrinsics.checkParameterIsNotNull(broadCastTime, "broadCastTime");
        this.receivePL = receivePL;
        this.networkP1 = networkP1;
        this.networkP2 = networkP2;
        this.networkId = networkId;
        this.broadCastTime = broadCastTime;
    }

    public final String getReceivePL() {
        return this.receivePL;
    }

    public final String getNetworkP1() {
        return this.networkP1;
    }

    public final void setNetworkP1(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.networkP1 = str;
    }

    public final String getNetworkP2() {
        return this.networkP2;
    }

    public final void setNetworkP2(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.networkP2 = str;
    }

    public final String getNetworkId() {
        return this.networkId;
    }

    public final void setNetworkId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.networkId = str;
    }

    public final String getBroadCastTime() {
        return this.broadCastTime;
    }

    public final void setBroadCastTime(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.broadCastTime = str;
    }

    public final LoRaConfigParam toLoRaConfig$robot_ui_robotRelease() {
        LoRaConfigParam loRaConfigParam = new LoRaConfigParam(new byte[0]);
        loRaConfigParam.setReceivePL(this.receivePL);
        loRaConfigParam.setNetworkP1(this.networkP1);
        loRaConfigParam.setNetworkP2(this.networkP2);
        loRaConfigParam.setNetworkId(this.networkId);
        loRaConfigParam.setBroadCastTime(this.broadCastTime);
        return loRaConfigParam;
    }
}
