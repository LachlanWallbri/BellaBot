package com.aliyun.alink.linksdk.tools.log;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public enum LogLevel {
    DEBUG("DBG"),
    INFO("INF"),
    WRAN("WAR"),
    ERROR("ERR");

    private String tag;

    LogLevel(String str) {
        this.tag = null;
        this.tag = str;
    }

    public String getTag() {
        return this.tag;
    }
}
