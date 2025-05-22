package com.aliyun.alink.linksdk.cmp.manager.connect.auth.mqtt;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MobileTripleValue {
    public String deviceName;
    public String deviceSecret;
    public String productKey;

    public MobileTripleValue(String str, String str2, String str3) {
        this.productKey = str;
        this.deviceName = str2;
        this.deviceSecret = str3;
    }

    public boolean checkValid() {
        return (TextUtils.isEmpty(this.productKey) || TextUtils.isEmpty(this.deviceName) || TextUtils.isEmpty(this.deviceSecret)) ? false : true;
    }

    public Map<String, String> getMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("PK", this.productKey);
        hashMap.put("DN", this.deviceName);
        hashMap.put("DS", this.deviceSecret);
        return hashMap;
    }
}
