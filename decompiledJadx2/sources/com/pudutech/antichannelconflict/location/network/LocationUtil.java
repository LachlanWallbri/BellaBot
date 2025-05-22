package com.pudutech.antichannelconflict.location.network;

import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.antichannelconflict.escape.util.GsonUtils;
import com.pudutech.base.Pdlog;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: LocationUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013JH\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u001628\u0010\u0017\u001a4\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0015\u0012\u0013\u0018\u00010\u001d¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00130\u0018R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/location/network/LocationUtil;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "locationUrl", "getLocationUrl", "setLocationUrl", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "setOkHttpClient", "(Lokhttp3/OkHttpClient;)V", "init", "", "startLocate", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "Lcom/pudutech/antichannelconflict/location/network/LocationReq;", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "code", "Lcom/pudutech/antichannelconflict/location/network/LocationData;", "data", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LocationUtil {
    public static final LocationUtil INSTANCE = new LocationUtil();
    private static String TAG = "LocationUtil";
    private static String locationUrl = "https://apilocate.amap.com/position";
    private static OkHttpClient okHttpClient;

    private LocationUtil() {
    }

    public final OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public final void setOkHttpClient(OkHttpClient okHttpClient2) {
        okHttpClient = okHttpClient2;
    }

    public final String getTAG() {
        return TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        TAG = str;
    }

    public final String getLocationUrl() {
        return locationUrl;
    }

    public final void setLocationUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        locationUrl = str;
    }

    public final synchronized void init() {
        if (okHttpClient != null) {
            return;
        }
        try {
            okHttpClient = new OkHttpClient().newBuilder().callTimeout(10000L, TimeUnit.MILLISECONDS).build();
            Pdlog.m3273d(TAG, "init");
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "init: " + Log.getStackTraceString(e));
        }
    }

    public final void startLocate(LocationReq req, final Function2<? super Integer, ? super LocationData, Unit> callback) {
        Call newCall;
        Intrinsics.checkParameterIsNotNull(req, "req");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(TAG, "startLocate ");
        try {
            String str = locationUrl + req.toString();
            Pdlog.m3273d(TAG, "request:" + locationUrl);
            Request build = new Request.Builder().url(str).get().build();
            OkHttpClient okHttpClient2 = okHttpClient;
            if (okHttpClient2 == null || (newCall = okHttpClient2.newCall(build)) == null) {
                return;
            }
            newCall.enqueue(new Callback() { // from class: com.pudutech.antichannelconflict.location.network.LocationUtil$startLocate$1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    Intrinsics.checkParameterIsNotNull(call, "call");
                    Intrinsics.checkParameterIsNotNull(e, "e");
                    Function2.this.invoke(-1, null);
                    Pdlog.m3273d(LocationUtil.INSTANCE.getTAG(), "startLocate onFailure: call=" + call + "  e=" + e);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) {
                    String str2;
                    LocationResp locationResp;
                    String string;
                    Intrinsics.checkParameterIsNotNull(call, "call");
                    Intrinsics.checkParameterIsNotNull(response, "response");
                    ResponseBody body = response.body();
                    try {
                        if (body != null && (string = body.string()) != null) {
                            if (string == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                            }
                            String obj = StringsKt.trim((CharSequence) string).toString();
                            if (obj != null) {
                                str2 = StringsKt.replace$default(obj, "\"", "'", false, 4, (Object) null);
                                Pdlog.m3273d(LocationUtil.INSTANCE.getTAG(), "startLocate success: response=" + str2 + ' ');
                                locationResp = (LocationResp) GsonUtils.fromJson(str2, LocationResp.class);
                                if (!Intrinsics.areEqual(locationResp.getStatus(), "1") && (!Intrinsics.areEqual(locationResp.getResult().getType(), "0"))) {
                                    Function2.this.invoke(200, locationResp.getResult());
                                } else {
                                    Function2.this.invoke(-1, null);
                                }
                                return;
                            }
                        }
                        locationResp = (LocationResp) GsonUtils.fromJson(str2, LocationResp.class);
                        if (!Intrinsics.areEqual(locationResp.getStatus(), "1")) {
                        }
                        Function2.this.invoke(-1, null);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Pdlog.m3273d(LocationUtil.INSTANCE.getTAG(), "startLocate Exception ", e.getMessage());
                        Function2.this.invoke(-1, null);
                        return;
                    }
                    str2 = null;
                    Pdlog.m3273d(LocationUtil.INSTANCE.getTAG(), "startLocate success: response=" + str2 + ' ');
                }
            });
        } catch (Exception e) {
            callback.invoke(-1, null);
            Pdlog.m3274e(TAG, "startLocate: " + Log.getStackTraceString(e));
        }
    }
}
