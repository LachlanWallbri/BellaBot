package com.pudutech.pd_network.bean;

import com.pudutech.pd_network.bean.NetEnvironment;
import com.pudutech.pd_network.log.ILog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\t\u001a\u0002032\u0017\u00104\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020305¢\u0006\u0002\b6J\b\u00107\u001a\u00020\u001cH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010'\"\u0004\b2\u0010)¨\u00068"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/PdNetworkManagerBuilder;", "", "()V", "ability", "", "getAbility", "()I", "setAbility", "(I)V", "client", "Lcom/pudutech/pd_network/bean/PdNetworkClientBuilder;", "getClient$pd_network_release", "()Lcom/pudutech/pd_network/bean/PdNetworkClientBuilder;", "setClient$pd_network_release", "(Lcom/pudutech/pd_network/bean/PdNetworkClientBuilder;)V", "deviceType", "Lcom/pudutech/pd_network/bean/DeviceType;", "getDeviceType", "()Lcom/pudutech/pd_network/bean/DeviceType;", "setDeviceType", "(Lcom/pudutech/pd_network/bean/DeviceType;)V", "environment", "Lcom/pudutech/pd_network/bean/NetEnvironment;", "getEnvironment", "()Lcom/pudutech/pd_network/bean/NetEnvironment;", "setEnvironment", "(Lcom/pudutech/pd_network/bean/NetEnvironment;)V", "forceBaseUrl", "", "getForceBaseUrl", "()Ljava/lang/String;", "setForceBaseUrl", "(Ljava/lang/String;)V", "forceMac", "getForceMac", "setForceMac", "handlerOlderReportData", "", "getHandlerOlderReportData", "()Z", "setHandlerOlderReportData", "(Z)V", "proxyLog", "Lcom/pudutech/pd_network/log/ILog;", "getProxyLog", "()Lcom/pudutech/pd_network/log/ILog;", "setProxyLog", "(Lcom/pudutech/pd_network/log/ILog;)V", "reportAbility", "getReportAbility", "setReportAbility", "", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PdNetworkManagerBuilder {
    private int ability;
    private ILog proxyLog;
    private PdNetworkClientBuilder client = new PdNetworkClientBuilder();
    private String forceMac = "";
    private String forceBaseUrl = "";
    private boolean handlerOlderReportData = true;
    private DeviceType deviceType = DeviceType.Robot;
    private boolean reportAbility = true;
    private NetEnvironment environment = NetEnvironment.Product.INSTANCE;

    /* renamed from: getClient$pd_network_release, reason: from getter */
    public final PdNetworkClientBuilder getClient() {
        return this.client;
    }

    public final void setClient$pd_network_release(PdNetworkClientBuilder pdNetworkClientBuilder) {
        Intrinsics.checkParameterIsNotNull(pdNetworkClientBuilder, "<set-?>");
        this.client = pdNetworkClientBuilder;
    }

    public final ILog getProxyLog() {
        return this.proxyLog;
    }

    public final void setProxyLog(ILog iLog) {
        this.proxyLog = iLog;
    }

    public final String getForceMac() {
        return this.forceMac;
    }

    public final void setForceMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.forceMac = str;
    }

    public final String getForceBaseUrl() {
        return this.forceBaseUrl;
    }

    public final void setForceBaseUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.forceBaseUrl = str;
    }

    public final int getAbility() {
        return this.ability;
    }

    public final void setAbility(int i) {
        this.ability = i;
    }

    public final boolean getHandlerOlderReportData() {
        return this.handlerOlderReportData;
    }

    public final void setHandlerOlderReportData(boolean z) {
        this.handlerOlderReportData = z;
    }

    public final DeviceType getDeviceType() {
        return this.deviceType;
    }

    public final void setDeviceType(DeviceType deviceType) {
        Intrinsics.checkParameterIsNotNull(deviceType, "<set-?>");
        this.deviceType = deviceType;
    }

    public final boolean getReportAbility() {
        return this.reportAbility;
    }

    public final void setReportAbility(boolean z) {
        this.reportAbility = z;
    }

    public final NetEnvironment getEnvironment() {
        return this.environment;
    }

    public final void setEnvironment(NetEnvironment netEnvironment) {
        Intrinsics.checkParameterIsNotNull(netEnvironment, "<set-?>");
        this.environment = netEnvironment;
    }

    public final void client(Function1<? super PdNetworkClientBuilder, Unit> builder) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        PdNetworkClientBuilder pdNetworkClientBuilder = new PdNetworkClientBuilder();
        builder.invoke(pdNetworkClientBuilder);
        this.client = pdNetworkClientBuilder;
    }

    public String toString() {
        return "PdNetworkManagerBuilder(client=" + this.client + ", proxyLog=" + this.proxyLog + ",forceMac='" + this.forceMac + "', forceBaseUrl='" + this.forceBaseUrl + "', ability=" + this.ability + ", handlerOlderReportData=" + this.handlerOlderReportData + ", deviceType=" + this.deviceType + ", reportAbility=" + this.reportAbility + ", environment=" + this.environment + ')';
    }
}
