package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.RequestListener;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.Config;
import com.iflytek.cloud.msc.util.DataUtil;
import com.iflytek.cloud.msc.util.Encrypter;
import com.iflytek.cloud.msc.util.FileDownloader;
import com.iflytek.cloud.msc.util.HttpRequest;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.util.FileDownloadListener;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* renamed from: com.iflytek.cloud.thirdparty.l */
/* loaded from: classes3.dex */
public class C3733l {

    /* renamed from: f */
    private static String f3289f = "http://wke.openspeech.cn/wakeup/";

    /* renamed from: b */
    private Context f3291b;

    /* renamed from: c */
    private HttpRequest f3292c;

    /* renamed from: e */
    private FileDownloader f3294e;

    /* renamed from: d */
    private RequestListener f3293d = null;

    /* renamed from: a */
    public HttpRequest.HttpRequestListener f3290a = new HttpRequest.HttpRequestListener() { // from class: com.iflytek.cloud.thirdparty.l.1
        @Override // com.iflytek.cloud.msc.util.HttpRequest.HttpRequestListener
        public void onResult(HttpRequest httpRequest, byte[] bArr) {
            try {
                String str = new String(bArr, "utf-8");
                DebugLog.LogS("updateInfo:" + str);
                JSONObject jSONObject = new JSONObject(str);
                try {
                    Config.getConfig(C3733l.this.f3291b).putInt("ivw_netval", Integer.valueOf((String) jSONObject.remove("netval")).intValue());
                } catch (Exception e) {
                    DebugLog.LogE(e);
                }
                int i = jSONObject.getInt("ret");
                if (i == 0) {
                    if (C3733l.this.f3293d != null) {
                        C3733l.this.f3293d.onBufferReceived(bArr);
                    }
                    onError(null);
                    return;
                }
                String string = jSONObject.getString("sid");
                DebugLog.LogD("query ivw res sid:" + string);
                if (C3733l.this.f3293d != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, string);
                    C3733l.this.f3293d.onEvent(20001, bundle);
                }
                onError(new SpeechError(i));
            } catch (Exception e2) {
                DebugLog.LogE(e2);
                onError(new SpeechError(20004));
            }
        }

        @Override // com.iflytek.cloud.msc.util.HttpRequest.HttpRequestListener
        public void onError(SpeechError speechError) {
            if (speechError != null) {
                DebugLog.LogD("query resource error. errorcode:" + speechError.getErrorCode());
            } else {
                DebugLog.LogS("query resource succeed");
            }
            if (C3733l.this.f3293d != null) {
                C3733l.this.f3293d.onCompleted(speechError);
            }
        }
    };

    public C3733l(Context context) {
        this.f3291b = null;
        this.f3292c = null;
        this.f3294e = null;
        this.f3291b = context;
        this.f3294e = FileDownloader.getDownLoadManager(this.f3291b);
        this.f3292c = new HttpRequest();
    }

    /* renamed from: a */
    public int m2144a(JSONObject jSONObject, RequestListener requestListener) {
        if (jSONObject == null) {
            return 20014;
        }
        if (requestListener != null) {
            try {
                this.f3293d = requestListener;
            } catch (Exception e) {
                DebugLog.LogE(e);
                return 20003;
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("header", C3694af.m1854e(this.f3291b, new C3692ad()));
        jSONObject2.put("info", jSONObject);
        DebugLog.LogS("post data:" + jSONObject2);
        byte[] zip5xEncode = Encrypter.zip5xEncode(jSONObject2.toString().getBytes());
        this.f3292c.setConectType(1);
        this.f3292c.setRequest(f3289f, null, zip5xEncode);
        this.f3292c.startRequest(this.f3290a);
        return 0;
    }

    /* renamed from: a */
    public int m2143a(String str, String str2, String str3, FileDownloadListener fileDownloadListener) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 20012;
        }
        return this.f3294e.startDownload(str, str2, str3, fileDownloadListener);
    }

    /* renamed from: a */
    public static synchronized JSONObject m2137a(String str, String str2) {
        synchronized (C3733l.class) {
            if (TextUtils.isEmpty(str2)) {
                return m2140b(str, "");
            }
            JSONObject m2140b = m2140b(str, "");
            if (m2140b == null) {
                return null;
            }
            JSONObject m2140b2 = m2140b(str2, "");
            if (m2140b2 == null) {
                return m2140b;
            }
            return m2141b(m2140b, m2140b2);
        }
    }

    /* renamed from: a */
    public static boolean m2138a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null) {
            if (jSONObject2 == null) {
                return true;
            }
            try {
                if (!jSONObject.getString("resver").equalsIgnoreCase("unkown")) {
                    if (jSONObject2.getString("resver").equalsIgnoreCase("unkown")) {
                        return true;
                    }
                    if (DataUtil.compareJsonString("appid", jSONObject, jSONObject2) && DataUtil.compareJsonString("resid", jSONObject, jSONObject2) && DataUtil.compareJsonString("restype", jSONObject, jSONObject2) && DataUtil.compareJsonString("wakelist", jSONObject, jSONObject2)) {
                        if (jSONObject.getLong("restime") > jSONObject2.getLong("restime")) {
                            return true;
                        }
                    }
                }
            } catch (JSONException e) {
                DebugLog.LogE(e);
            }
        }
        return false;
    }

    /* renamed from: b */
    public static JSONObject m2140b(String str, String str2) {
        MSCSessionInfo mSCSessionInfo = new MSCSessionInfo();
        try {
            DebugLog.LogD("getIvwResInfo resPath: " + str);
            int QIVWGetResInfo = MSC.QIVWGetResInfo(str.getBytes(), str2.getBytes(), mSCSessionInfo);
            if (QIVWGetResInfo != 0) {
                DebugLog.LogE("read ivw resoure error:" + QIVWGetResInfo);
                return null;
            }
            try {
                String str3 = new String(mSCSessionInfo.buffer, "utf-8");
                DebugLog.LogS("resInfo:" + new String(mSCSessionInfo.buffer));
                return m2142c(str, str3);
            } catch (UnsupportedEncodingException e) {
                DebugLog.LogE(e);
                return null;
            }
        } catch (Exception unused) {
            DebugLog.LogE("get res info error");
            return null;
        } catch (UnsatisfiedLinkError unused2) {
            DebugLog.LogE("get res info error, unsatisfiedlinkerror");
            return null;
        }
    }

    /* renamed from: a */
    public void m2145a() {
        HttpRequest httpRequest = this.f3292c;
        if (httpRequest != null) {
            httpRequest.cancel();
            this.f3292c = null;
        }
        this.f3293d = null;
    }

    /* renamed from: c */
    private static JSONObject m2142c(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("respath", str);
            JSONObject jSONObject2 = new JSONObject(new JSONTokener(str2));
            JSONObject jSONObject3 = jSONObject2.getJSONObject("athinfo");
            jSONObject.put("appid", jSONObject3.getString("appid"));
            jSONObject.put("resid", jSONObject3.getString("resid"));
            String string = jSONObject3.getString("restype");
            jSONObject.put("restype", string);
            if (!LinkFormat.DOMAIN.equalsIgnoreCase(string)) {
                jSONObject.put(AIUIConstant.KEY_UID, jSONObject3.getString(AIUIConstant.KEY_UID));
                jSONObject.put("expiredate", jSONObject3.getString("expiredate"));
            }
            JSONArray jSONArray = jSONObject2.getJSONObject("ivwword").getJSONArray("words");
            String str3 = "";
            for (int i = 0; i < jSONArray.length(); i++) {
                str3 = (((str3 + jSONArray.getJSONObject(i).getString("text")) + ":") + jSONArray.getJSONObject(i).getInt("threshold")) + ",";
            }
            jSONObject.put("wakelist", str3.substring(0, str3.length() - 1));
            try {
                jSONObject.put("resver", jSONObject2.getString("resver"));
            } catch (JSONException unused) {
                jSONObject.put("resver", "unkown");
            }
            try {
                jSONObject.put("restime", jSONObject2.getLong("restime"));
            } catch (JSONException unused2) {
                jSONObject.put("restime", 0L);
            }
            jSONObject.put("wakever", SpeechUtility.getUtility() != null ? SpeechUtility.getUtility().getParameter("ver_ivw") : "");
            DebugLog.LogS("root:" + jSONObject.toString());
            return jSONObject;
        } catch (JSONException unused3) {
            DebugLog.LogD("compose resinfo error");
            return null;
        }
    }

    /* renamed from: b */
    private static JSONObject m2141b(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            if (jSONObject.getString("resver").equalsIgnoreCase("unkown")) {
                DebugLog.LogS("user ivw resver unkown");
                return null;
            }
            if (!jSONObject2.getString("resver").equalsIgnoreCase("unkown") && jSONObject.getString("resver").equalsIgnoreCase(jSONObject2.getString("resver"))) {
                return (DataUtil.compareJsonString("appid", jSONObject, jSONObject2) && DataUtil.compareJsonString("resid", jSONObject, jSONObject2) && DataUtil.compareJsonString("restype", jSONObject, jSONObject2) && DataUtil.compareJsonString("wakelist", jSONObject, jSONObject2) && DataUtil.compareJsonLong("restime", jSONObject, jSONObject2)) ? jSONObject2 : jSONObject;
            }
            DebugLog.LogS("cfg ivw resver unkown or unequal");
            return jSONObject;
        } catch (JSONException e) {
            DebugLog.LogE(e);
            return null;
        }
    }
}
