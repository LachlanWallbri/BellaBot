package com.aliyun.alink.linksdk.alcs.lpbs.listener;

import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface PalConnectListener {
    public static final int PAL_CONN_AUTHLISTEMPTY = 504;
    public static final int PAL_CONN_FAIL_UNKNOWN = 1;
    public static final int PAL_CONN_ILLEGALSIGN = 506;
    public static final int PAL_CONN_INVALIDPARAM = 503;
    public static final int PAL_CONN_OK = 0;
    public static final int PAL_CONN_REVOCATE = 501;
    public static final int PAL_CONN_UNMATCHPREFIX = 502;
    public static final int PAL_CONN_VERNOTSUPPORT = 505;

    void onLoad(int i, Map<String, Object> map, PalDeviceInfo palDeviceInfo);
}
