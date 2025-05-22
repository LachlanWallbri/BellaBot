package com.aliyun.alink.linksdk.tmp.utils;

import com.aliyun.alink.linksdk.tools.ALog;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ResponseUtils {
    protected static final String TAG = "[Tmp]ResponseUtils";

    public static JSONObject getSuccessRspJson(JSONArray jSONArray) {
        return getRspJson(com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode.UNKNOWN_SUCCESS_CODE, "success", jSONArray);
    }

    public static String getSuccessRspJson(JSONObject jSONObject) {
        return getRspJson(com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode.UNKNOWN_SUCCESS_CODE, "success", jSONObject);
    }

    public static String getRspJson(String str, String str2, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", str);
            jSONObject2.put("message", str2);
            jSONObject2.put("data", jSONObject);
            ALog.m479d(TAG, "rsp bone json = " + jSONObject2.toString());
            return jSONObject2.toString();
        } catch (Exception e) {
            ALog.m479d(TAG, "getRspJson, e = " + e.toString());
            return null;
        }
    }

    public static JSONObject getRspJson(String str, String str2, JSONArray jSONArray) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", str);
            jSONObject.put("message", str2);
            jSONObject.put("data", jSONArray);
            return jSONObject;
        } catch (Exception e) {
            ALog.m479d(TAG, "getRspJson, e = " + e.toString());
            return null;
        }
    }

    public static String getSuccessRspJson(com.alibaba.fastjson.JSONObject jSONObject) {
        return getRspJson(com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode.UNKNOWN_SUCCESS_CODE, "success", jSONObject);
    }

    public static String getRspJson(String str, String str2, com.alibaba.fastjson.JSONObject jSONObject) {
        try {
            com.alibaba.fastjson.JSONObject jSONObject2 = new com.alibaba.fastjson.JSONObject();
            jSONObject2.put("code", (Object) str);
            jSONObject2.put("message", (Object) str2);
            jSONObject2.put("data", (Object) jSONObject);
            ALog.m479d(TAG, "rsp bone json = " + jSONObject2.toString());
            return jSONObject2.toString();
        } catch (Exception e) {
            ALog.m479d(TAG, "getRspJson, e = " + e.toString());
            return null;
        }
    }
}
