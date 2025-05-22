package com.aliyun.alink.apiclient.biz;

import com.aliyun.alink.apiclient.CommonRequest;
import com.aliyun.alink.apiclient.constants.Constants;
import com.aliyun.alink.apiclient.constants.MethodType;
import com.aliyun.alink.apiclient.constants.Schema;
import com.aliyun.alink.apiclient.utils.StringUtils;
import com.http.utils.LogUtils;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class RequestHelper {
    private static final String TAG = "RequestHelper";

    public static String getBaseUrl(CommonRequest commonRequest, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (commonRequest.getSchema() == Schema.HTTP) {
            stringBuffer.append("http://");
        } else {
            stringBuffer.append(OSSConfig.PREFIX_HTTPS);
        }
        if (StringUtils.isEmptyString(commonRequest.getDomain())) {
            stringBuffer.append(Constants.IOT_DOMAIN_DEFAULT);
        } else {
            stringBuffer.append(commonRequest.getDomain());
        }
        if (commonRequest.getMethod() == MethodType.GET) {
            LogUtils.error(TAG, "Get Not Support yet.");
        }
        if (str == null) {
            stringBuffer.append(commonRequest.getPath());
        } else {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public static Map<String, String> convert(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry != null && !StringUtils.isEmptyString(entry.getKey()) && entry.getValue() != null) {
                    if (entry.getValue() instanceof String) {
                        hashMap.put(entry.getKey(), (String) entry.getValue());
                    } else {
                        hashMap.put(entry.getKey(), entry.getValue().toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public static String getFormMapString(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        for (String str : map.keySet()) {
            if (i > 0) {
                stringBuffer.append("&");
            }
            stringBuffer.append(String.format("%s=%s", str, map.get(str)));
            i++;
        }
        return stringBuffer.toString();
    }
}
