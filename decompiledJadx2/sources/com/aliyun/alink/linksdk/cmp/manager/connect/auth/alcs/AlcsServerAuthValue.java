package com.aliyun.alink.linksdk.cmp.manager.connect.auth.alcs;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsServerAuthValue {
    public String prefix;
    public String secret;

    public boolean checkValid() {
        return (TextUtils.isEmpty(this.prefix) || TextUtils.isEmpty(this.secret)) ? false : true;
    }

    public Map<String, String> getMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("PREFIX", this.prefix);
        hashMap.put("SECRET", this.secret);
        return hashMap;
    }
}
