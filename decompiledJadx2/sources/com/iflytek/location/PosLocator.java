package com.iflytek.location;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.aiui.pro.C3576af;
import com.iflytek.aiui.utils.log.DebugLog;
import com.iflytek.location.result.GPSLocResult;
import com.iflytek.location.result.LocParamsResult;
import com.iflytek.location.result.LocResult;
import com.iflytek.location.result.NetLocResult;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class PosLocator {
    public static final String AMAP_LOCATION_KEY = "50125c27acb4f10f93e07272ced00a30";
    private static final String TAG = "PosLocator";
    public static final int TYPE_GPS_LOCATION = 1;
    public static final int TYPE_LOCATION_PARAM = 2;
    public static final int TYPE_NET_LOCATION = 0;
    private static PosLocator locInstance;
    private HandlerThread mLocHandlerThread;
    private C3576af mLocationClient;
    private Handler mLocationHandler = null;

    private PosLocator(Context context) {
        this.mLocationClient = null;
        this.mLocHandlerThread = null;
        if (0 == 0) {
            C3576af c3576af = new C3576af(context);
            this.mLocationClient = c3576af;
            c3576af.m975a(AMAP_LOCATION_KEY);
        }
        if (this.mLocHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("locate-thread");
            this.mLocHandlerThread = handlerThread;
            handlerThread.start();
            handleThreadMsg(this.mLocHandlerThread);
        }
    }

    public static PosLocator getInstance(Context context) {
        PosLocator posLocator;
        synchronized (PosLocator.class) {
            if (locInstance == null) {
                locInstance = new PosLocator(context);
            }
            posLocator = locInstance;
        }
        return posLocator;
    }

    private void handleThreadMsg(HandlerThread handlerThread) {
        this.mLocationHandler = new Handler(handlerThread.getLooper()) { // from class: com.iflytek.location.PosLocator.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                LocResult parseApsJsonResp;
                byte[] m976a;
                super.handleMessage(message);
                LocationListener locationListener = (LocationListener) message.obj;
                int i = message.what;
                if (i == 0) {
                    String m977b = PosLocator.this.mLocationClient.m977b();
                    if (!TextUtils.isEmpty(m977b)) {
                        DebugLog.LogD(PosLocator.TAG, m977b);
                        parseApsJsonResp = PosLocator.this.parseApsJsonResp(m977b);
                    }
                    parseApsJsonResp = null;
                } else if (i != 1) {
                    if (i == 2 && (m976a = PosLocator.this.mLocationClient.m976a()) != null) {
                        try {
                            String str = new String(m976a, "utf-8");
                            DebugLog.LogD(PosLocator.TAG, str);
                            parseApsJsonResp = PosLocator.this.parseParamsResp(str);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    parseApsJsonResp = null;
                } else {
                    String m978c = PosLocator.this.mLocationClient.m978c();
                    if (!TextUtils.isEmpty(m978c)) {
                        DebugLog.LogD(PosLocator.TAG, m978c);
                        parseApsJsonResp = PosLocator.this.parseGpsJsonResp(m978c);
                    }
                    parseApsJsonResp = null;
                }
                if (parseApsJsonResp != null) {
                    locationListener.onResult(parseApsJsonResp);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocResult parseApsJsonResp(String str) {
        JSONObject jSONObject;
        NetLocResult netLocResult = new NetLocResult();
        netLocResult.setLocType(0);
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if ("0".equals(jSONObject.optString("status"))) {
            netLocResult.setErrorInfo(jSONObject.optString("info"));
            netLocResult.setErrorCode(Integer.parseInt(jSONObject.optString("infocode", "-1")));
            return netLocResult;
        }
        netLocResult.setErrorCode(0);
        netLocResult.setErrorInfo("success");
        netLocResult.setRetype(jSONObject.optString("retype"));
        netLocResult.setRdesc(jSONObject.optString("rdesc"));
        netLocResult.setAdcode(jSONObject.optString("adcode"));
        netLocResult.setCitycode(jSONObject.optString("citycode"));
        netLocResult.setDesc(jSONObject.optString(TmpConstant.SERVICE_DESC));
        netLocResult.setTime(jSONObject.optLong("apiTime"));
        JSONObject optJSONObject = jSONObject.optJSONObject(RequestParameters.SUBRESOURCE_LOCATION);
        if (optJSONObject != null) {
            netLocResult.setAccuracy((float) optJSONObject.optDouble("radius"));
            netLocResult.setLon(optJSONObject.optDouble("cenx"));
            netLocResult.setLat(optJSONObject.optDouble("ceny"));
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("revergeo");
        if (optJSONObject2 != null) {
            netLocResult.setCountry(optJSONObject2.optString("country"));
            netLocResult.setProvince(optJSONObject2.optString("province"));
            netLocResult.setCity(optJSONObject2.optString("city"));
            netLocResult.setDistrict(optJSONObject2.optString("district"));
            netLocResult.setRoad(optJSONObject2.optString("road"));
            netLocResult.setStreet(optJSONObject2.optString("street"));
            netLocResult.setNumber(optJSONObject2.optString("number"));
            netLocResult.setPoiname(optJSONObject2.optString("poiname"));
            netLocResult.setAoiname(optJSONObject2.optString("aoiname"));
        }
        JSONObject optJSONObject3 = jSONObject.optJSONObject("indoor");
        if (optJSONObject3 != null) {
            netLocResult.setPoiid(optJSONObject3.optString("pid"));
            netLocResult.setFloor(optJSONObject3.optString("flr"));
            return netLocResult;
        }
        return netLocResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocResult parseGpsJsonResp(String str) {
        GPSLocResult gPSLocResult = new GPSLocResult();
        gPSLocResult.setLocType(1);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("altitude");
            String optString2 = jSONObject.optString("speed");
            String optString3 = jSONObject.optString("bearing");
            String optString4 = jSONObject.optString("lon");
            String optString5 = jSONObject.optString("lat");
            String optString6 = jSONObject.optString("accuracy");
            gPSLocResult.setAltitude(optString);
            gPSLocResult.setSpeed(optString2);
            gPSLocResult.setBearing(optString3);
            gPSLocResult.setLon(optString4);
            gPSLocResult.setLat(optString5);
            gPSLocResult.setAccuracy(Float.parseFloat(optString6));
            return gPSLocResult;
        } catch (Throwable th) {
            th.printStackTrace();
            return gPSLocResult;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LocResult parseParamsResp(String str) {
        LocParamsResult locParamsResult = new LocParamsResult();
        locParamsResult.setLocType(2);
        locParamsResult.setLocParams(str);
        return locParamsResult;
    }

    public void asyncDestroy() {
        C3576af c3576af = this.mLocationClient;
        if (c3576af != null) {
            c3576af.m979d();
        }
        HandlerThread handlerThread = this.mLocHandlerThread;
        if (handlerThread != null) {
            handlerThread.getLooper().quit();
            this.mLocHandlerThread = null;
        }
        this.mLocationClient = null;
        locInstance = null;
        this.mLocationHandler = null;
    }

    public void asyncGetLocation(int i, LocationListener locationListener) {
        if (this.mLocationHandler != null) {
            Message message = new Message();
            message.what = i;
            message.obj = locationListener;
            this.mLocationHandler.sendMessage(message);
        }
    }
}
