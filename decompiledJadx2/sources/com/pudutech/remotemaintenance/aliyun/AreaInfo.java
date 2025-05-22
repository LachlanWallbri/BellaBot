package com.pudutech.remotemaintenance.aliyun;

import com.alibaba.fastjson.annotation.JSONField;
import kotlin.Metadata;

/* compiled from: AreaInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\u0004H\u0016R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/AreaInfo;", "", "()V", "region_id", "", "getRegion_id", "()Ljava/lang/String;", "setRegion_id", "(Ljava/lang/String;)V", "url", "getUrl", "setUrl", "toString", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AreaInfo {

    @JSONField(name = "region_id")
    private String region_id;

    @JSONField(name = "url")
    private String url;

    public final String getRegion_id() {
        return this.region_id;
    }

    public final void setRegion_id(String str) {
        this.region_id = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "AreaInfo(region_id=" + this.region_id + ", url=" + this.url;
    }
}
