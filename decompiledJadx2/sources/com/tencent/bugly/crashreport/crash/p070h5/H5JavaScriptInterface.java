package com.tencent.bugly.crashreport.crash.p070h5;

import android.webkit.JavascriptInterface;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.C5940x;
import com.tencent.bugly.proguard.C5942z;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class H5JavaScriptInterface {

    /* renamed from: a */
    private static HashSet<Integer> f7971a = new HashSet<>();

    /* renamed from: b */
    private String f7972b = null;

    /* renamed from: c */
    private Thread f7973c = null;

    /* renamed from: d */
    private String f7974d = null;

    /* renamed from: e */
    private Map<String, String> f7975e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(CrashReport.WebViewInterface webViewInterface) {
        String str = null;
        if (webViewInterface == null || f7971a.contains(Integer.valueOf(webViewInterface.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        f7971a.add(Integer.valueOf(webViewInterface.hashCode()));
        h5JavaScriptInterface.f7973c = Thread.currentThread();
        Thread thread = h5JavaScriptInterface.f7973c;
        if (thread != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            for (int i = 2; i < thread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = thread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    sb.append(stackTraceElement.toString());
                    sb.append("\n");
                }
            }
            str = sb.toString();
        }
        h5JavaScriptInterface.f7974d = str;
        HashMap hashMap = new HashMap();
        StringBuilder sb2 = new StringBuilder();
        sb2.append((Object) webViewInterface.getContentDescription());
        hashMap.put("[WebView] ContentDescription", sb2.toString());
        h5JavaScriptInterface.f7975e = hashMap;
        return h5JavaScriptInterface;
    }

    /* renamed from: a */
    private static C5890a m3582a(String str) {
        String string;
        if (str != null && str.length() > 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                C5890a c5890a = new C5890a();
                c5890a.f7976a = jSONObject.getString("projectRoot");
                if (c5890a.f7976a == null) {
                    return null;
                }
                c5890a.f7977b = jSONObject.getString("context");
                if (c5890a.f7977b == null) {
                    return null;
                }
                c5890a.f7978c = jSONObject.getString("url");
                if (c5890a.f7978c == null) {
                    return null;
                }
                c5890a.f7979d = jSONObject.getString("userAgent");
                if (c5890a.f7979d == null) {
                    return null;
                }
                c5890a.f7980e = jSONObject.getString("language");
                if (c5890a.f7980e == null) {
                    return null;
                }
                c5890a.f7981f = jSONObject.getString("name");
                if (c5890a.f7981f == null || c5890a.f7981f.equals("null") || (string = jSONObject.getString("stacktrace")) == null) {
                    return null;
                }
                int indexOf = string.indexOf("\n");
                if (indexOf < 0) {
                    C5940x.m3824d("H5 crash stack's format is wrong!", new Object[0]);
                    return null;
                }
                c5890a.f7983h = string.substring(indexOf + 1);
                c5890a.f7982g = string.substring(0, indexOf);
                int indexOf2 = c5890a.f7982g.indexOf(":");
                if (indexOf2 > 0) {
                    c5890a.f7982g = c5890a.f7982g.substring(indexOf2 + 1);
                }
                c5890a.f7984i = jSONObject.getString("file");
                if (c5890a.f7981f == null) {
                    return null;
                }
                c5890a.f7985j = jSONObject.getLong("lineNumber");
                if (c5890a.f7985j < 0) {
                    return null;
                }
                c5890a.f7986k = jSONObject.getLong("columnNumber");
                if (c5890a.f7986k < 0) {
                    return null;
                }
                C5940x.m3818a("H5 crash information is following: ", new Object[0]);
                C5940x.m3818a("[projectRoot]: " + c5890a.f7976a, new Object[0]);
                C5940x.m3818a("[context]: " + c5890a.f7977b, new Object[0]);
                C5940x.m3818a("[url]: " + c5890a.f7978c, new Object[0]);
                C5940x.m3818a("[userAgent]: " + c5890a.f7979d, new Object[0]);
                C5940x.m3818a("[language]: " + c5890a.f7980e, new Object[0]);
                C5940x.m3818a("[name]: " + c5890a.f7981f, new Object[0]);
                C5940x.m3818a("[message]: " + c5890a.f7982g, new Object[0]);
                C5940x.m3818a("[stacktrace]: \n" + c5890a.f7983h, new Object[0]);
                C5940x.m3818a("[file]: " + c5890a.f7984i, new Object[0]);
                C5940x.m3818a("[lineNumber]: " + c5890a.f7985j, new Object[0]);
                C5940x.m3818a("[columnNumber]: " + c5890a.f7986k, new Object[0]);
                return c5890a;
            } catch (Throwable th) {
                if (!C5940x.m3819a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    @JavascriptInterface
    public void printLog(String str) {
        C5940x.m3824d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            C5940x.m3824d("Payload from JS is null.", new Object[0]);
            return;
        }
        String m3879b = C5942z.m3879b(str.getBytes());
        String str2 = this.f7972b;
        if (str2 != null && str2.equals(m3879b)) {
            C5940x.m3824d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
            return;
        }
        this.f7972b = m3879b;
        C5940x.m3824d("Handling JS exception ...", new Object[0]);
        C5890a m3582a = m3582a(str);
        if (m3582a == null) {
            C5940x.m3824d("Failed to parse payload.", new Object[0]);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        if (m3582a.f7976a != null) {
            linkedHashMap2.put("[JS] projectRoot", m3582a.f7976a);
        }
        if (m3582a.f7977b != null) {
            linkedHashMap2.put("[JS] context", m3582a.f7977b);
        }
        if (m3582a.f7978c != null) {
            linkedHashMap2.put("[JS] url", m3582a.f7978c);
        }
        if (m3582a.f7979d != null) {
            linkedHashMap2.put("[JS] userAgent", m3582a.f7979d);
        }
        if (m3582a.f7984i != null) {
            linkedHashMap2.put("[JS] file", m3582a.f7984i);
        }
        if (m3582a.f7985j != 0) {
            linkedHashMap2.put("[JS] lineNumber", Long.toString(m3582a.f7985j));
        }
        linkedHashMap.putAll(linkedHashMap2);
        linkedHashMap.putAll(this.f7975e);
        linkedHashMap.put("Java Stack", this.f7974d);
        Thread thread = this.f7973c;
        if (m3582a != null) {
            InnerApi.postH5CrashAsync(thread, m3582a.f7981f, m3582a.f7982g, m3582a.f7983h, linkedHashMap);
        }
    }
}
