package com.alibaba.sdk.android.oss.model;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class OSSResult {
    private Long clientCRC;
    private String requestId;
    private Map<String, String> responseHeader;
    private Long serverCRC;
    private int statusCode;

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public Map<String, String> getResponseHeader() {
        return this.responseHeader;
    }

    public void setResponseHeader(Map<String, String> map) {
        this.responseHeader = map;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public Long getClientCRC() {
        return this.clientCRC;
    }

    public void setClientCRC(Long l) {
        if (l == null || l.longValue() == 0) {
            return;
        }
        this.clientCRC = l;
    }

    public Long getServerCRC() {
        return this.serverCRC;
    }

    public void setServerCRC(Long l) {
        if (l == null || l.longValue() == 0) {
            return;
        }
        this.serverCRC = l;
    }
}
