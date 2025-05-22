package com.aliyun.alink.p022h2.api;

import android.text.TextUtils;
import com.aliyun.alink.p022h2.p025b.C0879a;
import com.tencent.bugly.BuglyStrategy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Profile {
    public static final int DEFAULT_PORT = 443;
    public static final boolean ENABLE_SSL = true;

    /* renamed from: a */
    private String f506a;

    /* renamed from: b */
    private String f507b;

    /* renamed from: c */
    private int f508c;

    /* renamed from: d */
    private int f509d = BuglyStrategy.C5863a.MAX_USERDATA_VALUE_LENGTH;

    /* renamed from: e */
    private int f510e = 60000;

    /* renamed from: f */
    private boolean f511f = false;

    /* renamed from: g */
    private int f512g = Runtime.getRuntime().availableProcessors();

    /* renamed from: h */
    private int f513h = Runtime.getRuntime().availableProcessors() * 4;

    /* renamed from: i */
    private int f514i = 1024;

    /* renamed from: j */
    private IAuthSign f515j = null;

    /* renamed from: k */
    private IAuthCallback f516k = null;

    /* renamed from: m */
    private URL f518m = null;

    /* renamed from: l */
    private Map<String, String> f517l = new HashMap();

    public String getHost() {
        URL url = this.f518m;
        if (url != null) {
            return url.getHost();
        }
        return this.f507b;
    }

    public int getPort() {
        URL url = this.f518m;
        if (url != null) {
            return url.getPort();
        }
        int i = this.f508c;
        if (i == -1) {
            return 443;
        }
        return i;
    }

    public int getHeartBeatInterval() {
        return this.f509d;
    }

    public int getHeartBeatTimeOut() {
        return this.f510e;
    }

    public Map<String, String> getAuthParams() {
        return this.f517l;
    }

    /* renamed from: a */
    private URL m205a() {
        try {
            URL url = new URL(this.f506a);
            this.f518m = url;
            return url;
        } catch (MalformedURLException e) {
            C0879a.m210d("Profile", "fail to parse url " + this.f506a + "," + e.getMessage());
            this.f518m = null;
            return null;
        }
    }

    private Profile(String str) {
        this.f506a = str;
        m205a();
    }

    public IAuthSign getAuthSign() {
        return this.f515j;
    }

    public IAuthCallback getAuthCallback() {
        return this.f516k;
    }

    public static Profile getDeviceProfile(String str, String str2, String str3, String str4, String str5) {
        Profile profile = new Profile(str);
        profile.f517l.put("name", Constraint.AUTH_TYPE_DEVICE_NAME);
        profile.f517l.put(Constraint.PARAM_PRODUCT_KEY, str2);
        profile.f517l.put(Constraint.PARAM_DEVICE_NAME, str3);
        profile.f517l.put(Constraint.PARAM_DEVICE_SECRET, str4);
        profile.f517l.put(Constraint.PARAM_CLIENT_ID, str5);
        profile.f511f = false;
        return profile;
    }

    public static Profile getDeviceProfile(String str, Map<String, String> map, IAuthCallback iAuthCallback) {
        Profile profile = new Profile(str);
        profile.f517l.putAll(map);
        if (iAuthCallback != null) {
            profile.f516k = iAuthCallback;
        }
        profile.f511f = false;
        return profile;
    }

    public static Profile getAppKeyProfile(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("appSecret param error.");
        }
        Profile profile = new Profile(str);
        profile.f517l.put("name", Constraint.AUTH_TYPE_APP_KEY);
        profile.f517l.put(Constraint.PARAM_APP_KEY, str2);
        profile.f517l.put(Constraint.PARAM_APP_SECRET, str3);
        profile.f511f = true;
        return profile;
    }

    public static Profile getAppKeyProfile(String str, String str2, IAuthSign iAuthSign) {
        if (iAuthSign == null || TextUtils.isEmpty(iAuthSign.getSignMethod())) {
            throw new IllegalArgumentException("authSign param error.");
        }
        Profile profile = new Profile(str);
        profile.f517l.put("name", Constraint.AUTH_TYPE_APP_KEY);
        profile.f517l.put(Constraint.PARAM_SIGN_METHOD, iAuthSign.getSignMethod());
        profile.f517l.put(Constraint.PARAM_APP_KEY, str2);
        profile.f511f = true;
        profile.f515j = iAuthSign;
        return profile;
    }
}
