package com.loc;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.nio.ByteBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Parser.java */
/* renamed from: com.loc.co */
/* loaded from: classes4.dex */
public final class C3867co {

    /* renamed from: a */
    private StringBuilder f3945a = new StringBuilder();

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0077, code lost:
    
        if (r6.length() <= 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0079, code lost:
    
        r4.setCity(r6);
        r7 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00c9 A[Catch: all -> 0x0195, TRY_ENTER, TryCatch #0 {all -> 0x0195, blocks: (B:3:0x0007, B:5:0x0050, B:7:0x0058, B:9:0x0060, B:12:0x0069, B:13:0x007f, B:16:0x00c9, B:17:0x00da, B:19:0x00e6, B:20:0x00f5, B:23:0x0102, B:24:0x0108, B:26:0x010e, B:28:0x0116, B:30:0x011c, B:31:0x0122, B:33:0x0128, B:34:0x012e, B:36:0x0134, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0151, B:45:0x0179, B:47:0x0183, B:48:0x018b, B:52:0x018f, B:55:0x0073, B:57:0x0079), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e6 A[Catch: all -> 0x0195, TryCatch #0 {all -> 0x0195, blocks: (B:3:0x0007, B:5:0x0050, B:7:0x0058, B:9:0x0060, B:12:0x0069, B:13:0x007f, B:16:0x00c9, B:17:0x00da, B:19:0x00e6, B:20:0x00f5, B:23:0x0102, B:24:0x0108, B:26:0x010e, B:28:0x0116, B:30:0x011c, B:31:0x0122, B:33:0x0128, B:34:0x012e, B:36:0x0134, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0151, B:45:0x0179, B:47:0x0183, B:48:0x018b, B:52:0x018f, B:55:0x0073, B:57:0x0079), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0102 A[Catch: all -> 0x0195, TRY_ENTER, TryCatch #0 {all -> 0x0195, blocks: (B:3:0x0007, B:5:0x0050, B:7:0x0058, B:9:0x0060, B:12:0x0069, B:13:0x007f, B:16:0x00c9, B:17:0x00da, B:19:0x00e6, B:20:0x00f5, B:23:0x0102, B:24:0x0108, B:26:0x010e, B:28:0x0116, B:30:0x011c, B:31:0x0122, B:33:0x0128, B:34:0x012e, B:36:0x0134, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0151, B:45:0x0179, B:47:0x0183, B:48:0x018b, B:52:0x018f, B:55:0x0073, B:57:0x0079), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0128 A[Catch: all -> 0x0195, TryCatch #0 {all -> 0x0195, blocks: (B:3:0x0007, B:5:0x0050, B:7:0x0058, B:9:0x0060, B:12:0x0069, B:13:0x007f, B:16:0x00c9, B:17:0x00da, B:19:0x00e6, B:20:0x00f5, B:23:0x0102, B:24:0x0108, B:26:0x010e, B:28:0x0116, B:30:0x011c, B:31:0x0122, B:33:0x0128, B:34:0x012e, B:36:0x0134, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0151, B:45:0x0179, B:47:0x0183, B:48:0x018b, B:52:0x018f, B:55:0x0073, B:57:0x0079), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0134 A[Catch: all -> 0x0195, TryCatch #0 {all -> 0x0195, blocks: (B:3:0x0007, B:5:0x0050, B:7:0x0058, B:9:0x0060, B:12:0x0069, B:13:0x007f, B:16:0x00c9, B:17:0x00da, B:19:0x00e6, B:20:0x00f5, B:23:0x0102, B:24:0x0108, B:26:0x010e, B:28:0x0116, B:30:0x011c, B:31:0x0122, B:33:0x0128, B:34:0x012e, B:36:0x0134, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0151, B:45:0x0179, B:47:0x0183, B:48:0x018b, B:52:0x018f, B:55:0x0073, B:57:0x0079), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0140 A[Catch: all -> 0x0195, TryCatch #0 {all -> 0x0195, blocks: (B:3:0x0007, B:5:0x0050, B:7:0x0058, B:9:0x0060, B:12:0x0069, B:13:0x007f, B:16:0x00c9, B:17:0x00da, B:19:0x00e6, B:20:0x00f5, B:23:0x0102, B:24:0x0108, B:26:0x010e, B:28:0x0116, B:30:0x011c, B:31:0x0122, B:33:0x0128, B:34:0x012e, B:36:0x0134, B:37:0x013a, B:39:0x0140, B:41:0x0146, B:42:0x014b, B:43:0x0151, B:45:0x0179, B:47:0x0183, B:48:0x018b, B:52:0x018f, B:55:0x0073, B:57:0x0079), top: B:2:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d9  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static AMapLocationServer m2858a(String str) {
        String str2;
        String m2859b;
        String m2859b2;
        JSONArray optJSONArray;
        String str3;
        JSONArray optJSONArray2;
        StringBuilder sb;
        try {
            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("regeocode");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("addressComponent");
            aMapLocationServer.setCountry(m2859b(optJSONObject2.optString("country")));
            String m2859b3 = m2859b(optJSONObject2.optString("province"));
            aMapLocationServer.setProvince(m2859b3);
            String m2859b4 = m2859b(optJSONObject2.optString("citycode"));
            aMapLocationServer.setCityCode(m2859b4);
            String optString = optJSONObject2.optString("city");
            if (!m2859b4.endsWith("010") && !m2859b4.endsWith("021") && !m2859b4.endsWith("022") && !m2859b4.endsWith("023")) {
                str2 = m2859b(optString);
                aMapLocationServer.setCity(str2);
                m2859b = m2859b(optJSONObject2.optString("district"));
                aMapLocationServer.setDistrict(m2859b);
                String m2859b5 = m2859b(optJSONObject2.optString("adcode"));
                aMapLocationServer.setAdCode(m2859b5);
                JSONObject optJSONObject3 = optJSONObject2.optJSONObject("streetNumber");
                m2859b2 = m2859b(optJSONObject3.optString("street"));
                aMapLocationServer.setStreet(m2859b2);
                aMapLocationServer.setRoad(m2859b2);
                aMapLocationServer.setNumber(m2859b(optJSONObject3.optString("number")));
                optJSONArray = optJSONObject.optJSONArray("pois");
                if (optJSONArray.length() <= 0) {
                    str3 = m2859b(optJSONArray.getJSONObject(0).optString("name"));
                    aMapLocationServer.setPoiName(str3);
                } else {
                    str3 = null;
                }
                optJSONArray2 = optJSONObject.optJSONArray("aois");
                if (optJSONArray2.length() > 0) {
                    aMapLocationServer.setAoiName(m2859b(optJSONArray2.getJSONObject(0).optString("name")));
                }
                sb = new StringBuilder();
                if (!TextUtils.isEmpty(m2859b3)) {
                    sb.append(m2859b3);
                    sb.append(" ");
                }
                if (!TextUtils.isEmpty(str2) && (!m2859b3.contains("市") || !m2859b3.equals(str2))) {
                    sb.append(str2);
                    sb.append(" ");
                }
                if (!TextUtils.isEmpty(m2859b)) {
                    sb.append(m2859b);
                    sb.append(" ");
                }
                if (!TextUtils.isEmpty(m2859b2)) {
                    sb.append(m2859b2);
                    sb.append(" ");
                }
                if (!TextUtils.isEmpty(str3)) {
                    if (!TextUtils.isEmpty(m2859b5)) {
                        sb.append("靠近");
                    }
                    sb.append(str3);
                    sb.append(" ");
                }
                Bundle bundle = new Bundle();
                bundle.putString("citycode", aMapLocationServer.getCityCode());
                bundle.putString(TmpConstant.SERVICE_DESC, sb.toString());
                bundle.putString("adcode", aMapLocationServer.getAdCode());
                aMapLocationServer.setExtras(bundle);
                aMapLocationServer.m554e(sb.toString());
                aMapLocationServer.setAddress((m2859b5 != null || m2859b5.trim().length() <= 0) ? sb.toString() : sb.toString().replace(" ", ""));
                return aMapLocationServer;
            }
            str2 = optString;
            m2859b = m2859b(optJSONObject2.optString("district"));
            aMapLocationServer.setDistrict(m2859b);
            String m2859b52 = m2859b(optJSONObject2.optString("adcode"));
            aMapLocationServer.setAdCode(m2859b52);
            JSONObject optJSONObject32 = optJSONObject2.optJSONObject("streetNumber");
            m2859b2 = m2859b(optJSONObject32.optString("street"));
            aMapLocationServer.setStreet(m2859b2);
            aMapLocationServer.setRoad(m2859b2);
            aMapLocationServer.setNumber(m2859b(optJSONObject32.optString("number")));
            optJSONArray = optJSONObject.optJSONArray("pois");
            if (optJSONArray.length() <= 0) {
            }
            optJSONArray2 = optJSONObject.optJSONArray("aois");
            if (optJSONArray2.length() > 0) {
            }
            sb = new StringBuilder();
            if (!TextUtils.isEmpty(m2859b3)) {
            }
            if (!TextUtils.isEmpty(str2)) {
                sb.append(str2);
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(m2859b)) {
            }
            if (!TextUtils.isEmpty(m2859b2)) {
            }
            if (!TextUtils.isEmpty(str3)) {
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("citycode", aMapLocationServer.getCityCode());
            bundle2.putString(TmpConstant.SERVICE_DESC, sb.toString());
            bundle2.putString("adcode", aMapLocationServer.getAdCode());
            aMapLocationServer.setExtras(bundle2);
            aMapLocationServer.m554e(sb.toString());
            aMapLocationServer.setAddress((m2859b52 != null || m2859b52.trim().length() <= 0) ? sb.toString() : sb.toString().replace(" ", ""));
            return aMapLocationServer;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: b */
    private static String m2859b(String str) {
        return "[]".equals(str) ? "" : str;
    }

    /* renamed from: a */
    public final AMapLocationServer m2860a(String str, Context context, C3840bo c3840bo) {
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setErrorCode(7);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("status") || !jSONObject.has("info")) {
                this.f3945a.append("json is error " + str);
            }
            String string = jSONObject.getString("status");
            String string2 = jSONObject.getString("info");
            if (string.equals("0")) {
                this.f3945a.append("auth fail:" + string2);
            }
        } catch (Throwable th) {
            this.f3945a.append("json exception error:" + th.getMessage());
            C3880f.m3097a(th, "parser", "paseAuthFailurJson");
        }
        try {
            StringBuilder sb = this.f3945a;
            sb.append("#SHA1AndPackage#");
            sb.append(C3885k.m3126e(context));
            String str2 = c3840bo.f3700b.get("gsid").get(0);
            if (!TextUtils.isEmpty(str2)) {
                StringBuilder sb2 = this.f3945a;
                sb2.append(" #gsid#");
                sb2.append(str2);
            }
            String str3 = c3840bo.f3701c;
            if (!TextUtils.isEmpty(str3)) {
                this.f3945a.append(" #csid#" + str3);
            }
        } catch (Throwable unused) {
        }
        aMapLocationServer.setLocationDetail(this.f3945a.toString());
        if (this.f3945a.length() > 0) {
            StringBuilder sb3 = this.f3945a;
            sb3.delete(0, sb3.length());
        }
        return aMapLocationServer;
    }

    /* renamed from: a */
    public final AMapLocationServer m2861a(byte[] bArr) {
        AMapLocationServer aMapLocationServer;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        ByteBuffer byteBuffer = null;
        try {
            aMapLocationServer = new AMapLocationServer("");
        } catch (Throwable th) {
            try {
                AMapLocationServer aMapLocationServer2 = new AMapLocationServer("");
                aMapLocationServer2.setErrorCode(5);
                this.f3945a.append("parser data error:" + th.getMessage());
                aMapLocationServer2.setLocationDetail(this.f3945a.toString());
                aMapLocationServer = aMapLocationServer2;
            } finally {
                if (0 != 0) {
                    byteBuffer.clear();
                }
            }
        }
        if (bArr == null) {
            aMapLocationServer.setErrorCode(5);
            this.f3945a.append("byte[] is null");
            aMapLocationServer.setLocationDetail(this.f3945a.toString());
            this.f3945a.delete(0, this.f3945a.length());
            return aMapLocationServer;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.get() == 0) {
            aMapLocationServer.m543a(String.valueOf((int) wrap.getShort()));
            wrap.clear();
            if (wrap != null) {
                wrap.clear();
            }
            return aMapLocationServer;
        }
        aMapLocationServer.setLongitude(C3876cx.m2960a(wrap.getInt() / 1000000.0d));
        aMapLocationServer.setLatitude(C3876cx.m2960a(wrap.getInt() / 1000000.0d));
        aMapLocationServer.setAccuracy(wrap.getShort());
        aMapLocationServer.m547b(String.valueOf((int) wrap.get()));
        aMapLocationServer.m550c(String.valueOf((int) wrap.get()));
        if (wrap.get() == 1) {
            byte[] bArr2 = new byte[wrap.get() & 255];
            wrap.get(bArr2);
            try {
                aMapLocationServer.setCountry(new String(bArr2, "UTF-8"));
            } catch (Throwable unused) {
            }
            byte[] bArr3 = new byte[wrap.get() & 255];
            wrap.get(bArr3);
            try {
                str = new String(bArr3, "UTF-8");
                try {
                    aMapLocationServer.setProvince(str);
                } catch (Throwable unused2) {
                }
            } catch (Throwable unused3) {
                str = "";
            }
            byte[] bArr4 = new byte[wrap.get() & 255];
            wrap.get(bArr4);
            try {
                str2 = new String(bArr4, "UTF-8");
                try {
                    aMapLocationServer.setCity(str2);
                } catch (Throwable unused4) {
                }
            } catch (Throwable unused5) {
                str2 = "";
            }
            byte[] bArr5 = new byte[wrap.get() & 255];
            wrap.get(bArr5);
            try {
                str3 = new String(bArr5, "UTF-8");
                try {
                    aMapLocationServer.setDistrict(str3);
                } catch (Throwable unused6) {
                }
            } catch (Throwable unused7) {
                str3 = "";
            }
            byte[] bArr6 = new byte[wrap.get() & 255];
            wrap.get(bArr6);
            try {
                str4 = new String(bArr6, "UTF-8");
                try {
                    aMapLocationServer.setStreet(str4);
                    aMapLocationServer.setRoad(str4);
                } catch (Throwable unused8) {
                }
            } catch (Throwable unused9) {
                str4 = "";
            }
            byte[] bArr7 = new byte[wrap.get() & 255];
            wrap.get(bArr7);
            try {
                aMapLocationServer.setNumber(new String(bArr7, "UTF-8"));
            } catch (Throwable unused10) {
            }
            byte[] bArr8 = new byte[wrap.get() & 255];
            wrap.get(bArr8);
            try {
                str5 = new String(bArr8, "UTF-8");
                try {
                    aMapLocationServer.setPoiName(str5);
                } catch (Throwable unused11) {
                }
            } catch (Throwable unused12) {
                str5 = "";
            }
            byte[] bArr9 = new byte[wrap.get() & 255];
            wrap.get(bArr9);
            try {
                aMapLocationServer.setAoiName(new String(bArr9, "UTF-8"));
            } catch (Throwable unused13) {
            }
            byte[] bArr10 = new byte[wrap.get() & 255];
            wrap.get(bArr10);
            try {
                str6 = new String(bArr10, "UTF-8");
                try {
                    aMapLocationServer.setAdCode(str6);
                } catch (Throwable unused14) {
                }
            } catch (Throwable unused15) {
                str6 = "";
            }
            byte[] bArr11 = new byte[wrap.get() & 255];
            wrap.get(bArr11);
            try {
                aMapLocationServer.setCityCode(new String(bArr11, "UTF-8"));
            } catch (Throwable unused16) {
            }
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(str2) && (!str.contains("市") || !str.equals(str2))) {
                sb.append(str2);
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(str3)) {
                sb.append(str3);
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(str4)) {
                sb.append(str4);
                sb.append(" ");
            }
            if (!TextUtils.isEmpty(str5)) {
                if (!TextUtils.isEmpty(str6)) {
                    sb.append("靠近");
                }
                sb.append(str5);
                sb.append(" ");
            }
            Bundle bundle = new Bundle();
            bundle.putString("citycode", aMapLocationServer.getCityCode());
            bundle.putString(TmpConstant.SERVICE_DESC, sb.toString());
            bundle.putString("adcode", aMapLocationServer.getAdCode());
            aMapLocationServer.setExtras(bundle);
            aMapLocationServer.m554e(sb.toString());
            String adCode = aMapLocationServer.getAdCode();
            aMapLocationServer.setAddress((adCode == null || adCode.trim().length() <= 0) ? sb.toString() : sb.toString().replace(" ", ""));
        }
        wrap.get(new byte[wrap.get() & 255]);
        if (wrap.get() == 1) {
            wrap.getInt();
            wrap.getInt();
            wrap.getShort();
        }
        if (wrap.get() == 1) {
            byte[] bArr12 = new byte[wrap.get() & 255];
            wrap.get(bArr12);
            try {
                aMapLocationServer.setBuildingId(new String(bArr12, "UTF-8"));
            } catch (Throwable unused17) {
            }
            byte[] bArr13 = new byte[wrap.get() & 255];
            wrap.get(bArr13);
            try {
                aMapLocationServer.setFloor(new String(bArr13, "UTF-8"));
            } catch (Throwable unused18) {
            }
        }
        if (wrap.get() == 1) {
            wrap.get();
            wrap.getInt();
            wrap.get();
        }
        if (wrap.get() == 1) {
            aMapLocationServer.setTime(wrap.getLong());
        }
        byte[] bArr14 = new byte[wrap.getShort()];
        wrap.get(bArr14);
        try {
            AMapLocationServer.f1212d = new String(bArr14, "UTF-8");
        } catch (Throwable unused19) {
        }
        if (wrap != null) {
            wrap.clear();
        }
        if (this.f3945a.length() > 0) {
            StringBuilder sb2 = this.f3945a;
            sb2.delete(0, sb2.length());
        }
        return aMapLocationServer;
    }
}
