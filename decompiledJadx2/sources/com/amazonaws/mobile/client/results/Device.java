package com.amazonaws.mobile.client.results;

import java.util.Date;
import java.util.Map;

/* loaded from: classes.dex */
public class Device {
    private final Map<String, String> attributes;
    private final Date createDate;
    private final String deviceKey;
    private final Date lastAuthenticatedDate;
    private final Date lastModifiedDate;

    public Device(String str, Map<String, String> map, Date date, Date date2, Date date3) {
        this.deviceKey = str;
        this.attributes = map;
        this.createDate = date;
        this.lastModifiedDate = date2;
        this.lastAuthenticatedDate = date3;
    }

    public String getDeviceKey() {
        return this.deviceKey;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public Date getLastAuthenticatedDate() {
        return this.lastAuthenticatedDate;
    }
}
