package com.aliyun.alink.linksdk.tmp.data.service;

import com.aliyun.alink.linksdk.tmp.utils.TextHelper;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DevTripleInfo {
    public String deviceName;
    public String deviceSecret;
    public String productKey;

    public String getId() {
        return TextHelper.combineStr(this.productKey, this.deviceName);
    }
}
