package com.aliyun.alink.linksdk.channel.core.persistent.mqtt;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentInitParams;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MqttInitParams extends PersistentInitParams {
    public String deviceName;
    public String deviceSecret;
    public String productKey;
    public String productSecret;
    public boolean receiveOfflineMsg;
    public int secureMode;

    public MqttInitParams(String str, String str2, String str3) {
        this.secureMode = 2;
        this.receiveOfflineMsg = false;
        this.productKey = str;
        this.deviceName = str2;
        this.deviceSecret = str3;
    }

    public MqttInitParams(String str, String str2, String str3, String str4, int i) {
        this.secureMode = 2;
        this.receiveOfflineMsg = false;
        this.productKey = str;
        this.productSecret = str2;
        this.deviceName = str3;
        this.deviceSecret = str4;
        this.secureMode = i;
    }

    public boolean checkValid() {
        return (TextUtils.isEmpty(this.productKey) || TextUtils.isEmpty(this.deviceName)) ? false : true;
    }
}
