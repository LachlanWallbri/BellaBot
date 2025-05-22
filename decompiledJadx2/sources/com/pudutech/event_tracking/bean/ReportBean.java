package com.pudutech.event_tracking.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0003\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J;\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\tHÖ\u0001J\t\u0010!\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000e\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\f¨\u0006\""}, m3961d2 = {"Lcom/pudutech/event_tracking/bean/ReportBean;", "", "id", "", "timeStamp", "data", "", "url", "upload", "", "(JJLjava/lang/String;Ljava/lang/String;I)V", "getData", "()Ljava/lang/String;", "getId", "()J", "getTimeStamp", "setTimeStamp", "(J)V", "getUpload", "()I", "setUpload", "(I)V", "getUrl", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class ReportBean {
    private final String data;
    private final long id;
    private long timeStamp;
    private int upload;
    private final String url;

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final long getTimeStamp() {
        return this.timeStamp;
    }

    /* renamed from: component3, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component5, reason: from getter */
    public final int getUpload() {
        return this.upload;
    }

    public final ReportBean copy(long id, long timeStamp, String data, String url, int upload) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return new ReportBean(id, timeStamp, data, url, upload);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReportBean)) {
            return false;
        }
        ReportBean reportBean = (ReportBean) other;
        return this.id == reportBean.id && this.timeStamp == reportBean.timeStamp && Intrinsics.areEqual(this.data, reportBean.data) && Intrinsics.areEqual(this.url, reportBean.url) && this.upload == reportBean.upload;
    }

    public int hashCode() {
        int hashCode = ((Long.hashCode(this.id) * 31) + Long.hashCode(this.timeStamp)) * 31;
        String str = this.data;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.url;
        return ((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.upload);
    }

    public String toString() {
        return "ReportBean(id=" + this.id + ", timeStamp=" + this.timeStamp + ", data=" + this.data + ", url=" + this.url + ", upload=" + this.upload + ")";
    }

    public ReportBean(long j, long j2, String data, String url, int i) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Intrinsics.checkParameterIsNotNull(url, "url");
        this.id = j;
        this.timeStamp = j2;
        this.data = data;
        this.url = url;
        this.upload = i;
    }

    public final long getId() {
        return this.id;
    }

    public /* synthetic */ ReportBean(long j, long j2, String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : j, (i2 & 2) != 0 ? System.currentTimeMillis() : j2, str, str2, (i2 & 16) != 0 ? 0 : i);
    }

    public final long getTimeStamp() {
        return this.timeStamp;
    }

    public final void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public final String getData() {
        return this.data;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getUpload() {
        return this.upload;
    }

    public final void setUpload(int i) {
        this.upload = i;
    }
}
