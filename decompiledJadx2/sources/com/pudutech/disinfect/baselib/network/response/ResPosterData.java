package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: ResPosterData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/ResPosterData;", "", "tips", "", "url", "(Ljava/lang/String;Ljava/lang/String;)V", "getTips", "()Ljava/lang/String;", "setTips", "(Ljava/lang/String;)V", "getUrl", "setUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class ResPosterData {
    private String tips;
    private String url;

    public static /* synthetic */ ResPosterData copy$default(ResPosterData resPosterData, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = resPosterData.tips;
        }
        if ((i & 2) != 0) {
            str2 = resPosterData.url;
        }
        return resPosterData.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTips() {
        return this.tips;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final ResPosterData copy(String tips, String url) {
        return new ResPosterData(tips, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResPosterData)) {
            return false;
        }
        ResPosterData resPosterData = (ResPosterData) other;
        return Intrinsics.areEqual(this.tips, resPosterData.tips) && Intrinsics.areEqual(this.url, resPosterData.url);
    }

    public int hashCode() {
        String str = this.tips;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.url;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ResPosterData(tips=" + this.tips + ", url=" + this.url + ")";
    }

    public ResPosterData(String str, String str2) {
        this.tips = str;
        this.url = str2;
    }

    public final String getTips() {
        return this.tips;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setTips(String str) {
        this.tips = str;
    }

    public final void setUrl(String str) {
        this.url = str;
    }
}
