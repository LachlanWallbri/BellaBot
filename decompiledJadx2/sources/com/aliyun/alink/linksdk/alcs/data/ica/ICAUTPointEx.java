package com.aliyun.alink.linksdk.alcs.data.ica;

import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICAUTPointEx {
    private static final String TAG = "[alcs_coap_sdk]ICAUTPointEx";
    public static final int UNKNOWN_ERRORCODE = -1;
    private static final String UTEVENT_ALCSCONNECTED = "alcsConnected";
    private static final String UTKEY_DEVICENAME = "deviceName";
    private static final String UTKEY_PRODUCTKEY = "productKey";
    public static final String UTVALUE_ALCS = "alcs";
    protected Map<String, String> mUtProperties = new HashMap();

    public ICAUTPointEx(String str, String str2) {
        ALog.m479d(TAG, "ICAUTPointEx productKey:" + str + " deviceName:" + str2);
        this.mUtProperties.put("productKey", str);
        this.mUtProperties.put("deviceName", str2);
        this.mUtProperties.put(AUserTrack.UTKEY_MODULE, UTVALUE_ALCS);
    }

    public void trackStart() {
        this.mUtProperties.put(AUserTrack.UTKEY_START_TIME, String.valueOf(System.currentTimeMillis()));
    }

    public void trackEnd(int i) {
        this.mUtProperties.put(AUserTrack.UTKEY_END_TIME, String.valueOf(System.currentTimeMillis()));
        this.mUtProperties.put(AUserTrack.UTKEY_ERROR_CODE, String.valueOf(i));
        AUserTrack.record(UTEVENT_ALCSCONNECTED, this.mUtProperties);
    }
}
