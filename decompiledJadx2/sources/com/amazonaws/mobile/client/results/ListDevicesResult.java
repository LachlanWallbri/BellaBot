package com.amazonaws.mobile.client.results;

import java.util.List;

/* loaded from: classes.dex */
public class ListDevicesResult {
    private List<Device> devices;
    private String paginationToken;

    public ListDevicesResult(List<Device> list, String str) {
        this.devices = list;
        this.paginationToken = str;
    }

    public List<Device> getDevices() {
        return this.devices;
    }

    public String getPaginationToken() {
        return this.paginationToken;
    }
}
