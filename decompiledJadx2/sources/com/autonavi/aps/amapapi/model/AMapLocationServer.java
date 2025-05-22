package com.autonavi.aps.amapapi.model;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amap.api.location.AMapLocation;
import com.loc.C3876cx;
import com.loc.C3880f;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AMapLocationServer extends AMapLocation {

    /* renamed from: d */
    public static String f1212d;

    /* renamed from: e */
    boolean f1213e;

    /* renamed from: f */
    private String f1214f;

    /* renamed from: g */
    private int f1215g;

    /* renamed from: h */
    private String f1216h;

    /* renamed from: i */
    private String f1217i;

    /* renamed from: j */
    private JSONObject f1218j;

    /* renamed from: k */
    private String f1219k;

    /* renamed from: l */
    private String f1220l;

    /* renamed from: m */
    private long f1221m;

    /* renamed from: n */
    private String f1222n;

    public AMapLocationServer(String str) {
        super(str);
        this.f1214f = "";
        this.f1216h = "";
        this.f1217i = "new";
        this.f1218j = null;
        this.f1219k = "";
        this.f1213e = true;
        this.f1220l = "";
        this.f1221m = 0L;
        this.f1222n = null;
    }

    /* renamed from: a */
    public final String m541a() {
        return this.f1214f;
    }

    @Override // com.amap.api.location.AMapLocation
    /* renamed from: a */
    public final JSONObject mo490a(int i) {
        try {
            JSONObject mo490a = super.mo490a(i);
            if (i == 1) {
                mo490a.put("retype", this.f1216h);
                mo490a.put("cens", this.f1220l);
                mo490a.put("poiid", this.f1076a);
                mo490a.put("floor", this.f1077b);
                mo490a.put("coord", this.f1215g);
                mo490a.put("mcell", this.f1219k);
                mo490a.put(TmpConstant.SERVICE_DESC, this.f1078c);
                mo490a.put("address", getAddress());
                if (this.f1218j != null && C3876cx.m2979a(mo490a, "offpct")) {
                    mo490a.put("offpct", this.f1218j.getString("offpct"));
                }
            } else if (i != 2 && i != 3) {
                return mo490a;
            }
            mo490a.put("type", this.f1217i);
            mo490a.put("isReversegeo", this.f1213e);
            return mo490a;
        } catch (Throwable th) {
            C3880f.m3097a(th, "AmapLoc", "toStr");
            return null;
        }
    }

    /* renamed from: a */
    public final void m542a(long j) {
        this.f1221m = j;
    }

    /* renamed from: a */
    public final void m543a(String str) {
        this.f1214f = str;
    }

    /* renamed from: a */
    public final void m544a(JSONObject jSONObject) {
        this.f1218j = jSONObject;
    }

    /* renamed from: a */
    public final void m545a(boolean z) {
        this.f1213e = z;
    }

    /* renamed from: b */
    public final int m546b() {
        return this.f1215g;
    }

    /* renamed from: b */
    public final void m547b(String str) {
        int i;
        if (!TextUtils.isEmpty(str)) {
            if (getProvider().equals("gps")) {
                this.f1215g = 0;
                return;
            } else if (str.equals("0")) {
                this.f1215g = 0;
                return;
            } else if (str.equals("1")) {
                i = 1;
                this.f1215g = i;
            }
        }
        i = -1;
        this.f1215g = i;
    }

    /* renamed from: b */
    public final void m548b(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                C3880f.m3096a(this, jSONObject);
                if (C3876cx.m2979a(jSONObject, "type")) {
                    this.f1217i = jSONObject.getString("type");
                }
                if (C3876cx.m2979a(jSONObject, "retype")) {
                    this.f1216h = jSONObject.getString("retype");
                }
                if (C3876cx.m2979a(jSONObject, "cens")) {
                    String string = jSONObject.getString("cens");
                    if (!TextUtils.isEmpty(string)) {
                        String[] split = string.split("\\*");
                        int length = split.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            String str = split[i];
                            if (!TextUtils.isEmpty(str)) {
                                String[] split2 = str.split(",");
                                setLongitude(Double.parseDouble(split2[0]));
                                setLatitude(Double.parseDouble(split2[1]));
                                setAccuracy(Integer.parseInt(split2[2]));
                                break;
                            }
                            i++;
                        }
                        this.f1220l = string;
                    }
                }
                if (C3876cx.m2979a(jSONObject, TmpConstant.SERVICE_DESC)) {
                    this.f1078c = jSONObject.getString(TmpConstant.SERVICE_DESC);
                }
                if (C3876cx.m2979a(jSONObject, "poiid")) {
                    setBuildingId(jSONObject.getString("poiid"));
                }
                if (C3876cx.m2979a(jSONObject, "pid")) {
                    setBuildingId(jSONObject.getString("pid"));
                }
                if (C3876cx.m2979a(jSONObject, "floor")) {
                    setFloor(jSONObject.getString("floor"));
                }
                if (C3876cx.m2979a(jSONObject, "flr")) {
                    setFloor(jSONObject.getString("flr"));
                }
                if (C3876cx.m2979a(jSONObject, "coord")) {
                    m547b(jSONObject.getString("coord"));
                }
                if (C3876cx.m2979a(jSONObject, "mcell")) {
                    this.f1219k = jSONObject.getString("mcell");
                }
                if (C3876cx.m2979a(jSONObject, "isReversegeo")) {
                    this.f1213e = jSONObject.getBoolean("isReversegeo");
                }
            } catch (Throwable th) {
                C3880f.m3097a(th, "AmapLoc", "AmapLoc");
            }
        }
    }

    /* renamed from: c */
    public final String m549c() {
        return this.f1216h;
    }

    /* renamed from: c */
    public final void m550c(String str) {
        this.f1216h = str;
    }

    /* renamed from: d */
    public final String m551d() {
        return this.f1217i;
    }

    /* renamed from: d */
    public final void m552d(String str) {
        this.f1217i = str;
    }

    /* renamed from: e */
    public final JSONObject m553e() {
        return this.f1218j;
    }

    /* renamed from: e */
    public final void m554e(String str) {
        this.f1078c = str;
    }

    /* renamed from: f */
    public final String m555f() {
        return this.f1219k;
    }

    /* renamed from: f */
    public final void m556f(String str) {
        this.f1222n = str;
    }

    /* renamed from: g */
    public final AMapLocationServer m557g() {
        String str = this.f1219k;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(",");
        if (split.length != 3) {
            return null;
        }
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setProvider(getProvider());
        aMapLocationServer.setLongitude(Double.parseDouble(split[0]));
        aMapLocationServer.setLatitude(Double.parseDouble(split[1]));
        aMapLocationServer.setAccuracy(Float.parseFloat(split[2]));
        aMapLocationServer.setCityCode(getCityCode());
        aMapLocationServer.setAdCode(getAdCode());
        aMapLocationServer.setCountry(getCountry());
        aMapLocationServer.setProvince(getProvince());
        aMapLocationServer.setCity(getCity());
        aMapLocationServer.setTime(getTime());
        aMapLocationServer.f1217i = this.f1217i;
        aMapLocationServer.m547b(String.valueOf(this.f1215g));
        if (C3876cx.m2977a(aMapLocationServer)) {
            return aMapLocationServer;
        }
        return null;
    }

    /* renamed from: h */
    public final boolean m558h() {
        return this.f1213e;
    }

    /* renamed from: i */
    public final long m559i() {
        return this.f1221m;
    }

    /* renamed from: j */
    public final String m560j() {
        return this.f1222n;
    }

    @Override // com.amap.api.location.AMapLocation
    public void setFloor(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace("F", "");
            try {
                Integer.parseInt(str);
            } catch (Throwable th) {
                C3880f.m3097a(th, "AmapLoc", "setFloor");
                str = null;
            }
        }
        this.f1077b = str;
    }

    @Override // com.amap.api.location.AMapLocation
    public String toStr(int i) {
        JSONObject jSONObject;
        try {
            jSONObject = super.mo490a(i);
            jSONObject.put("nb", this.f1222n);
        } catch (Throwable th) {
            C3880f.m3097a(th, "AMapLocation", "toStr part2");
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }
}
