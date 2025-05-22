package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: SolicitCustomerRate.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006!"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/SolicitCustomerRate;", "", "()V", "data", "Lcom/pudutech/disinfect/baselib/network/req/SolicitCustomerReport;", "getData", "()Lcom/pudutech/disinfect/baselib/network/req/SolicitCustomerReport;", "setData", "(Lcom/pudutech/disinfect/baselib/network/req/SolicitCustomerReport;)V", "hardver", "", "getHardver", "()Ljava/lang/String;", "setHardver", "(Ljava/lang/String;)V", "mac", "getMac", "setMac", "report_number", "", "getReport_number", "()I", "setReport_number", "(I)V", "softver", "getSoftver", "setSoftver", "timestamp", "", "getTimestamp", "()J", "setTimestamp", "(J)V", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SolicitCustomerRate {
    private SolicitCustomerReport data;
    private int report_number;
    private long timestamp;
    private String mac = "";
    private String softver = "";
    private String hardver = "";

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        this.mac = str;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public final String getSoftver() {
        return this.softver;
    }

    public final void setSoftver(String str) {
        this.softver = str;
    }

    public final String getHardver() {
        return this.hardver;
    }

    public final void setHardver(String str) {
        this.hardver = str;
    }

    public final int getReport_number() {
        return this.report_number;
    }

    public final void setReport_number(int i) {
        this.report_number = i;
    }

    public final SolicitCustomerReport getData() {
        return this.data;
    }

    public final void setData(SolicitCustomerReport solicitCustomerReport) {
        this.data = solicitCustomerReport;
    }
}
