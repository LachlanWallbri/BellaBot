package com.aliyun.alink.linksdk.cmp.manager.connect.auth.mqtt;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import com.alibaba.wireless.security.open.SecurityGuardParamContext;
import com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent;
import com.aliyun.alink.linksdk.cmp.connect.apigw.ApiGatewayRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.util.RandomStringUtil;
import com.aliyun.alink.linksdk.cmp.manager.connect.ConnectManager;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.alink.p022h2.api.Constraint;
import com.ut.device.UTDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MobileAuthHttpRequest {
    private static final String TAG = "MobileAuthHttpRequest";
    private static String apiVersion = "1.0.0";
    private static String appkey = null;
    private static String authCode = "";
    private static String clientId = null;
    private static Context context = null;
    private static String deviceSn = null;
    private static final String path = "/app/aepauth/handle";

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface IAuthHttpRequestCallback {
        void onFailed(String str);

        void onSuceess(MobileTripleValue mobileTripleValue);
    }

    public static void request(Context context2, String str, String str2, final IAuthHttpRequestCallback iAuthHttpRequestCallback) {
        ALog.m479d(TAG, "request()");
        context = context2;
        appkey = str;
        authCode = str2;
        if (deviceSn == null) {
            String utdid = UTDevice.getUtdid(context2);
            ALog.m479d(TAG, "request(), utdid = " + utdid);
            if (TextUtils.isEmpty(utdid)) {
                deviceSn = RandomStringUtil.getRandomString(32);
            } else {
                String replace = utdid.replace("/", "").replace("+", "").replace("=", "");
                deviceSn = replace + RandomStringUtil.getRandomString(32 - replace.length());
            }
        }
        ALog.m479d(TAG, "request(), deviceSn = " + deviceSn);
        if (clientId == null) {
            clientId = RandomStringUtil.getRandomString(8);
        }
        HashMap hashMap = new HashMap();
        String str3 = System.currentTimeMillis() + "";
        hashMap.put("appKey", appkey);
        hashMap.put("timestamp", str3);
        hashMap.put(TmpConstant.KEY_CLIENT_ID, clientId);
        hashMap.put("deviceSn", deviceSn);
        String sign = sign(hashMap);
        ALog.m479d(TAG, "signed str = " + sign);
        if (!TextUtils.isEmpty(sign)) {
            hashMap.put(TmpConstant.KEY_SIGN_VALUE, sign);
        }
        hashMap.remove("appKey");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("authInfo", hashMap);
        try {
            ConnectManager.getInstance().getApiGatewayConnect().send(ApiGatewayRequest.build(path, apiVersion, hashMap2), new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.cmp.manager.connect.auth.mqtt.MobileAuthHttpRequest.1
                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onResponse(),rsp = ");
                    sb.append((aResponse == null || aResponse.data == null) ? "" : (String) aResponse.data);
                    ALog.m479d(MobileAuthHttpRequest.TAG, sb.toString());
                    try {
                        JSONObject parseObject = JSONObject.parseObject((String) aResponse.data);
                        String string = parseObject.getString(NotificationCompat.CATEGORY_MESSAGE);
                        if (parseObject.getIntValue("code") == 200) {
                            String string2 = parseObject.getJSONObject("data").getString("deviceName");
                            String string3 = parseObject.getJSONObject("data").getString(Constraint.PARAM_DEVICE_SECRET);
                            String string4 = parseObject.getJSONObject("data").getString("productKey");
                            if (TextUtils.isEmpty(string4) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                                return;
                            }
                            IAuthHttpRequestCallback.this.onSuceess(new MobileTripleValue(string4, string2, string3));
                            return;
                        }
                        IAuthHttpRequestCallback.this.onFailed(string);
                    } catch (Exception e) {
                        ALog.m480e(MobileAuthHttpRequest.TAG, "onResponse(), error = " + e.toString());
                        IAuthHttpRequestCallback.this.onFailed(e.toString());
                    }
                }

                @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                public void onFailure(ARequest aRequest, AError aError) {
                    ALog.m480e(MobileAuthHttpRequest.TAG, "onErrorResponse(), error = " + aError.getMsg());
                    IAuthHttpRequestCallback.this.onFailed(aError.getCode() + "," + aError.getMsg());
                }
            });
        } catch (Exception e) {
            ALog.m479d(TAG, "request error, e = " + e.toString());
            e.printStackTrace();
            iAuthHttpRequestCallback.onFailed(e.toString());
        }
    }

    private static String sign(Map<String, Object> map) {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("appKey");
        arrayList.add(TmpConstant.KEY_CLIENT_ID);
        arrayList.add("deviceSn");
        arrayList.add("timestamp");
        String str = "";
        for (String str2 : arrayList) {
            str = appendStrs(str, str2, map.get(str2));
        }
        ALog.m479d(TAG, "sign(), toSignStr = " + str);
        try {
            ISecureSignatureComponent secureSignatureComp = SecurityGuardManager.getInstance(context).getSecureSignatureComp();
            HashMap hashMap = new HashMap();
            hashMap.put("INPUT", str);
            SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
            securityGuardParamContext.appKey = appkey;
            securityGuardParamContext.paramMap = hashMap;
            securityGuardParamContext.requestType = 3;
            try {
                return secureSignatureComp.signRequest(securityGuardParamContext, authCode);
            } catch (SecException e) {
                ALog.m479d(TAG, "sign(),signe req error,e" + e.toString());
                e.printStackTrace();
                return null;
            }
        } catch (SecException e2) {
            ALog.m479d(TAG, "sign(), create sg manager error, e" + e2.toString());
            e2.printStackTrace();
            return null;
        }
    }

    private static String appendStrs(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            stringBuffer.append(obj != null ? obj.toString() : "");
        }
        return stringBuffer.toString();
    }
}
