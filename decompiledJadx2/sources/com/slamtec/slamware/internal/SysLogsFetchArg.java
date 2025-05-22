package com.slamtec.slamware.internal;

import java.util.AbstractMap;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class SysLogsFetchArg {
    private String deviceHost = "127.0.0.1";
    private long timeoutMS = 300000;
    private AbstractMap<String, String> reqParams = new TreeMap();

    public String getDeviceHost() {
        return this.deviceHost;
    }

    public void setDeviceHost(String str) {
        this.deviceHost = str;
    }

    public long getTimeoutMS() {
        return this.timeoutMS;
    }

    public void setTimeoutMS(long j) {
        this.timeoutMS = j;
    }

    public AbstractMap<String, String> getReqParams() {
        return this.reqParams;
    }

    public void setReqParams(AbstractMap<String, String> abstractMap) {
        this.reqParams = abstractMap;
    }
}
