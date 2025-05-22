package com.pudutech.aiuicae.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class JsonParser {
    public static String parseIatResult(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            JSONArray jSONArray = new JSONObject(new JSONTokener(str)).getJSONArray("ws");
            for (int i = 0; i < jSONArray.length(); i++) {
                stringBuffer.append(jSONArray.getJSONObject(i).getJSONArray("cw").getJSONObject(0).getString("w"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public static String parseGrammarResult(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            JSONArray jSONArray = new JSONObject(new JSONTokener(str)).getJSONArray("ws");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray jSONArray2 = jSONArray.getJSONObject(i).getJSONArray("cw");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                    if (jSONObject.getString("w").contains("nomatch")) {
                        stringBuffer.append("没有匹配结果.");
                        return stringBuffer.toString();
                    }
                    stringBuffer.append("【结果】" + jSONObject.getString("w"));
                    stringBuffer.append("【置信度】" + jSONObject.getInt("sc"));
                    stringBuffer.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            stringBuffer.append("没有匹配结果.");
        }
        return stringBuffer.toString();
    }

    public static String parseLocalGrammarResult(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            JSONObject jSONObject = new JSONObject(new JSONTokener(str));
            JSONArray jSONArray = jSONObject.getJSONArray("ws");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray jSONArray2 = jSONArray.getJSONObject(i).getJSONArray("cw");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                    if (jSONObject2.getString("w").contains("nomatch")) {
                        stringBuffer.append("没有匹配结果.");
                        return stringBuffer.toString();
                    }
                    stringBuffer.append("【结果】" + jSONObject2.getString("w"));
                    stringBuffer.append("\n");
                }
            }
            stringBuffer.append("【置信度】" + jSONObject.optInt("sc"));
        } catch (Exception e) {
            e.printStackTrace();
            stringBuffer.append("没有匹配结果.");
        }
        return stringBuffer.toString();
    }
}
