package com.loc;

import android.os.Bundle;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amap.api.fence.DistrictItem;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.PoiItem;
import com.amap.api.location.DPoint;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: GeoFenceSearchResultParser.java */
/* renamed from: com.loc.c */
/* loaded from: classes4.dex */
public final class C3852c {

    /* renamed from: a */
    private static long f3862a;

    /* renamed from: a */
    public static int m2741a(String str, List<GeoFence> list, Bundle bundle) {
        int i;
        int i2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            char c = 0;
            int optInt = jSONObject.optInt("status", 0);
            i = jSONObject.optInt("infocode", 0);
            if (optInt == 1) {
                try {
                    JSONArray optJSONArray = jSONObject.optJSONArray("pois");
                    if (optJSONArray != null) {
                        int i3 = 0;
                        while (i3 < optJSONArray.length()) {
                            GeoFence geoFence = new GeoFence();
                            PoiItem poiItem = new PoiItem();
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i3);
                            poiItem.setPoiId(jSONObject2.optString("id"));
                            poiItem.setPoiName(jSONObject2.optString("name"));
                            poiItem.setPoiType(jSONObject2.optString("type"));
                            poiItem.setTypeCode(jSONObject2.optString("typecode"));
                            poiItem.setAddress(jSONObject2.optString("address"));
                            String optString = jSONObject2.optString(RequestParameters.SUBRESOURCE_LOCATION);
                            if (optString != null) {
                                String[] split = optString.split(",");
                                poiItem.setLongitude(Double.parseDouble(split[c]));
                                poiItem.setLatitude(Double.parseDouble(split[1]));
                                List<List<DPoint>> arrayList = new ArrayList<>();
                                ArrayList arrayList2 = new ArrayList();
                                i2 = i;
                                try {
                                    DPoint dPoint = new DPoint(poiItem.getLatitude(), poiItem.getLongitude());
                                    arrayList2.add(dPoint);
                                    arrayList.add(arrayList2);
                                    geoFence.setPointList(arrayList);
                                    geoFence.setCenter(dPoint);
                                } catch (Throwable unused) {
                                    return i2;
                                }
                            } else {
                                i2 = i;
                            }
                            poiItem.setTel(jSONObject2.optString("tel"));
                            poiItem.setProvince(jSONObject2.optString("pname"));
                            poiItem.setCity(jSONObject2.optString("cityname"));
                            poiItem.setAdname(jSONObject2.optString("adname"));
                            geoFence.setPoiItem(poiItem);
                            StringBuilder sb = new StringBuilder();
                            sb.append(m2742a());
                            geoFence.setFenceId(sb.toString());
                            if (bundle != null) {
                                geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
                                geoFence.setPendingIntentAction(bundle.getString("pendingIntentAction"));
                                geoFence.setType(2);
                                geoFence.setRadius(bundle.getFloat("geoRadius"));
                                geoFence.setExpiration(bundle.getLong("expiration"));
                                geoFence.setActivatesAction(bundle.getInt("activatesAction", 1));
                            }
                            if (list != null) {
                                list.add(geoFence);
                            }
                            i3++;
                            i = i2;
                            c = 0;
                        }
                    }
                } catch (Throwable unused2) {
                    return i;
                }
            }
            return i;
        } catch (Throwable unused3) {
            i = -1;
        }
    }

    /* renamed from: a */
    public static synchronized long m2742a() {
        long j;
        synchronized (C3852c.class) {
            boolean z = false;
            while (!z) {
                long m2985b = C3876cx.m2985b();
                if (m2985b == f3862a) {
                    try {
                        Thread.currentThread();
                        Thread.sleep(1L);
                    } catch (InterruptedException unused) {
                    }
                } else {
                    f3862a = m2985b;
                    z = true;
                }
            }
            j = f3862a;
        }
        return j;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e5 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private List<DPoint> m2743a(List<DPoint> list, float f) {
        double longitude;
        double latitude;
        DPoint dPoint;
        double d;
        double d2;
        double m2963a;
        List<DPoint> list2 = list;
        if (list2 == null) {
            return null;
        }
        if (list.size() <= 2) {
            return list2;
        }
        ArrayList arrayList = new ArrayList();
        DPoint dPoint2 = list2.get(0);
        int i = 1;
        DPoint dPoint3 = list2.get(list.size() - 1);
        double d3 = 0.0d;
        int i2 = 0;
        int i3 = 1;
        double d4 = 0.0d;
        while (i3 < list.size() - i) {
            DPoint dPoint4 = list2.get(i3);
            double longitude2 = dPoint4.getLongitude() - dPoint2.getLongitude();
            double latitude2 = dPoint4.getLatitude() - dPoint2.getLatitude();
            double longitude3 = dPoint3.getLongitude() - dPoint2.getLongitude();
            double latitude3 = dPoint3.getLatitude() - dPoint2.getLatitude();
            double d5 = ((longitude2 * longitude3) + (latitude2 * latitude3)) / ((longitude3 * longitude3) + (latitude3 * latitude3));
            if (d5 < d3 || (dPoint2.getLongitude() == dPoint3.getLongitude() && dPoint2.getLatitude() == dPoint3.getLatitude())) {
                longitude = dPoint2.getLongitude();
                latitude = dPoint2.getLatitude();
            } else if (d5 > 1.0d) {
                longitude = dPoint3.getLongitude();
                latitude = dPoint3.getLatitude();
            } else {
                double longitude4 = dPoint2.getLongitude() + (longitude3 * d5);
                d = dPoint2.getLatitude() + (d5 * latitude3);
                dPoint = dPoint2;
                d2 = longitude4;
                m2963a = C3876cx.m2963a(new DPoint(dPoint4.getLatitude(), dPoint4.getLongitude()), new DPoint(d, d2));
                if (m2963a <= d4) {
                    d4 = m2963a;
                    i2 = i3;
                }
                i3++;
                list2 = list;
                dPoint2 = dPoint;
                i = 1;
                d3 = 0.0d;
            }
            d = latitude;
            dPoint = dPoint2;
            d2 = longitude;
            m2963a = C3876cx.m2963a(new DPoint(dPoint4.getLatitude(), dPoint4.getLongitude()), new DPoint(d, d2));
            if (m2963a <= d4) {
            }
            i3++;
            list2 = list;
            dPoint2 = dPoint;
            i = 1;
            d3 = 0.0d;
        }
        DPoint dPoint5 = dPoint2;
        if (d4 < f) {
            arrayList.add(dPoint5);
            arrayList.add(dPoint3);
            return arrayList;
        }
        List<DPoint> m2743a = m2743a(list.subList(0, i2 + 1), f);
        List<DPoint> m2743a2 = m2743a(list.subList(i2, list.size()), f);
        arrayList.addAll(m2743a);
        arrayList.remove(arrayList.size() - 1);
        arrayList.addAll(m2743a2);
        return arrayList;
    }

    /* renamed from: b */
    public static int m2744b(String str, List<GeoFence> list, Bundle bundle) {
        return m2741a(str, list, bundle);
    }

    /* renamed from: c */
    public final int m2745c(String str, List<GeoFence> list, Bundle bundle) {
        int i;
        int i2;
        String str2;
        JSONArray optJSONArray;
        ArrayList arrayList;
        ArrayList arrayList2;
        boolean z;
        int i3;
        float f;
        long j;
        String str3;
        String str4;
        List<GeoFence> list2;
        String str5;
        String str6;
        GeoFence geoFence;
        int i4;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status", 0);
            i = jSONObject.optInt("infocode", 0);
            float f2 = 0.0f;
            long j2 = 0;
            String str7 = null;
            if (bundle != null) {
                try {
                    str7 = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                    String string = bundle.getString("pendingIntentAction");
                    float f3 = bundle.getFloat("geoRadius");
                    long j3 = bundle.getLong("expiration");
                    i2 = bundle.getInt("activatesAction");
                    str2 = string;
                    f2 = f3;
                    j2 = j3;
                } catch (Throwable unused) {
                    return i;
                }
            } else {
                i2 = 0;
                str2 = null;
            }
            if (optInt == 1 && (optJSONArray = jSONObject.optJSONArray("districts")) != null) {
                int i5 = 0;
                while (i5 < optJSONArray.length()) {
                    ArrayList arrayList3 = new ArrayList();
                    ArrayList arrayList4 = new ArrayList();
                    GeoFence geoFence2 = new GeoFence();
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i5);
                    String optString = jSONObject2.optString("citycode");
                    String optString2 = jSONObject2.optString("adcode");
                    JSONArray jSONArray = optJSONArray;
                    String optString3 = jSONObject2.optString("name");
                    int i6 = i;
                    try {
                        String string2 = jSONObject2.getString("center");
                        int i7 = i5;
                        DPoint dPoint = new DPoint();
                        String str8 = ",";
                        if (string2 != null) {
                            String[] split = string2.split(",");
                            arrayList = arrayList3;
                            arrayList2 = arrayList4;
                            dPoint.setLatitude(Double.parseDouble(split[1]));
                            dPoint.setLongitude(Double.parseDouble(split[0]));
                            geoFence2.setCenter(dPoint);
                        } else {
                            arrayList = arrayList3;
                            arrayList2 = arrayList4;
                        }
                        geoFence2.setCustomId(str7);
                        geoFence2.setPendingIntentAction(str2);
                        geoFence2.setType(3);
                        geoFence2.setRadius(f2);
                        geoFence2.setExpiration(j2);
                        geoFence2.setActivatesAction(i2);
                        StringBuilder sb = new StringBuilder();
                        sb.append(m2742a());
                        geoFence2.setFenceId(sb.toString());
                        String optString4 = jSONObject2.optString("polyline");
                        if (optString4 != null) {
                            String[] split2 = optString4.split("\\|");
                            int length = split2.length;
                            i3 = i2;
                            float f4 = Float.MAX_VALUE;
                            float f5 = Float.MIN_VALUE;
                            int i8 = 0;
                            while (i8 < length) {
                                float f6 = f2;
                                String str9 = split2[i8];
                                String[] strArr = split2;
                                DistrictItem districtItem = new DistrictItem();
                                long j4 = j2;
                                List<DPoint> arrayList5 = new ArrayList<>();
                                districtItem.setCitycode(optString);
                                districtItem.setAdcode(optString2);
                                districtItem.setDistrictName(optString3);
                                String[] split3 = str9.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
                                String str10 = optString3;
                                int i9 = 0;
                                while (i9 < split3.length) {
                                    String[] split4 = split3[i9].split(str8);
                                    String str11 = str8;
                                    String str12 = optString;
                                    if (split4.length > 1) {
                                        String str13 = split4[1];
                                        String str14 = split4[0];
                                        str5 = str7;
                                        str6 = str2;
                                        double parseDouble = Double.parseDouble(str13);
                                        geoFence = geoFence2;
                                        i4 = length;
                                        arrayList5.add(new DPoint(parseDouble, Double.parseDouble(str14)));
                                    } else {
                                        str5 = str7;
                                        str6 = str2;
                                        geoFence = geoFence2;
                                        i4 = length;
                                    }
                                    i9++;
                                    str7 = str5;
                                    geoFence2 = geoFence;
                                    str8 = str11;
                                    optString = str12;
                                    str2 = str6;
                                    length = i4;
                                }
                                String str15 = str8;
                                String str16 = optString;
                                String str17 = str7;
                                String str18 = str2;
                                GeoFence geoFence3 = geoFence2;
                                int i10 = length;
                                if (arrayList5.size() > 100.0f) {
                                    try {
                                        arrayList5 = m2743a(arrayList5, 100.0f);
                                    } catch (Throwable unused2) {
                                        return i6;
                                    }
                                }
                                ArrayList arrayList6 = arrayList2;
                                arrayList6.add(arrayList5);
                                districtItem.setPolyline(arrayList5);
                                ArrayList arrayList7 = arrayList;
                                arrayList7.add(districtItem);
                                f5 = Math.max(f5, C3798a.m2363b(dPoint, arrayList5));
                                f4 = Math.min(f4, C3798a.m2355a(dPoint, arrayList5));
                                i8++;
                                str7 = str17;
                                geoFence2 = geoFence3;
                                arrayList2 = arrayList6;
                                arrayList = arrayList7;
                                f2 = f6;
                                split2 = strArr;
                                j2 = j4;
                                optString3 = str10;
                                str8 = str15;
                                optString = str16;
                                str2 = str18;
                                length = i10;
                            }
                            z = false;
                            f = f2;
                            j = j2;
                            str3 = str7;
                            str4 = str2;
                            GeoFence geoFence4 = geoFence2;
                            geoFence4.setMaxDis2Center(f5);
                            geoFence4.setMinDis2Center(f4);
                            geoFence4.setDistrictItemList(arrayList);
                            geoFence4.setPointList(arrayList2);
                            list2 = list;
                            if (list2 != null) {
                                list2.add(geoFence4);
                            }
                        } else {
                            z = false;
                            i3 = i2;
                            f = f2;
                            j = j2;
                            str3 = str7;
                            str4 = str2;
                            list2 = list;
                        }
                        i5 = i7 + 1;
                        str7 = str3;
                        optJSONArray = jSONArray;
                        i = i6;
                        i2 = i3;
                        f2 = f;
                        j2 = j;
                        str2 = str4;
                    } catch (Throwable unused3) {
                        return i6;
                    }
                }
            }
            return i;
        } catch (Throwable unused4) {
            i = -1;
        }
    }
}
