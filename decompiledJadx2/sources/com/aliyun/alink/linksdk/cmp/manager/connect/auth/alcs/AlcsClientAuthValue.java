package com.aliyun.alink.linksdk.cmp.manager.connect.auth.alcs;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsClientAuthValue {
    public String accessKey;
    public String accessToken;
    public String deviceName;
    public String iotId;
    public String productKey;

    public boolean checkValid() {
        return (TextUtils.isEmpty(this.iotId) || TextUtils.isEmpty(this.productKey) || TextUtils.isEmpty(this.deviceName) || TextUtils.isEmpty(this.accessKey) || TextUtils.isEmpty(this.accessToken)) ? false : true;
    }

    public Map<String, String> getMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("PK", this.productKey);
        hashMap.put("DN", this.deviceName);
        hashMap.put("KEY", this.accessKey);
        hashMap.put("TOKEN", this.accessToken);
        hashMap.put("ID", this.iotId);
        return hashMap;
    }
}
