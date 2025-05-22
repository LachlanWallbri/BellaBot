package com.aliyun.alink.dm.api;

import android.text.TextUtils;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.util.Arrays;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SignUtils {
    private static final String TAG = "SignUtils";

    public static String hmacSign(Map<String, String> map, String str) {
        if (map != null && !TextUtils.isEmpty(str)) {
            String toSignString = getToSignString(map);
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("utf-8"), MqttConfigure.SIGN_METHOD);
                Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
                mac.init(secretKeySpec);
                return bytesToHexString(mac.doFinal(toSignString.getBytes("utf-8")));
            } catch (Exception e) {
                C0859a.m135d(TAG, "hmacSign error, e" + e.toString());
                e.printStackTrace();
            }
        }
        return null;
    }

    private static final String bytesToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    public static String getToSignString(Map<String, String> map) {
        try {
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            Arrays.sort(strArr);
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                if (!TmpConstant.KEY_SIGN_VALUE.equalsIgnoreCase(str)) {
                    sb.append(str);
                    sb.append(map.get(str));
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
