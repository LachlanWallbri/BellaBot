package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p036e;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.alcs.lpbs.api.PluginMgr;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalConnect;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.ICloudChannelFactory;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDataDownListener;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener;
import com.aliyun.alink.linksdk.alcs.lpbs.p031a.p032a.C0921a;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CloudCreateListener.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.e.a */
/* loaded from: classes.dex */
public class C0926a implements PalConnectListener {

    /* renamed from: a */
    private static final String f726a = "[AlcsLPBS]CloudCreateListener";

    /* renamed from: b */
    private PalDeviceInfo f727b;

    /* renamed from: c */
    private PalConnectListener f728c;

    /* renamed from: d */
    private C0921a f729d;

    /* renamed from: e */
    private IDataDownListener f730e;

    /* renamed from: f */
    private IPalConnect f731f;

    public C0926a(PalDeviceInfo palDeviceInfo, PalConnectListener palConnectListener, IDataDownListener iDataDownListener, C0921a c0921a, IPalConnect iPalConnect) {
        this.f727b = palDeviceInfo;
        this.f728c = palConnectListener;
        this.f729d = c0921a;
        this.f730e = iDataDownListener;
        this.f731f = iPalConnect;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener
    public void onLoad(final int i, final Map<String, Object> map, PalDeviceInfo palDeviceInfo) {
        ALog.m479d(f726a, "onLoad errorCode:" + i + " params:" + map);
        if (i == 0 && PluginMgr.getInstance().isDataToCloud(this.f727b)) {
            this.f729d.m342a(this.f727b, map, this.f730e, new ICloudChannelFactory.FactoryListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.a.e.a.1
                @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.ICloudChannelFactory.FactoryListener
                public void onCreate(PalDeviceInfo palDeviceInfo2, IThingCloudChannel iThingCloudChannel) {
                    ALog.m479d(C0926a.f726a, "onCreate channel:" + iThingCloudChannel + " mConnect: " + C0926a.this.f731f);
                    if (C0926a.this.f728c != null) {
                        C0926a.this.f728c.onLoad(i, map, palDeviceInfo2);
                    }
                    if (iThingCloudChannel != null) {
                        C0926a c0926a = C0926a.this;
                        Map map2 = map;
                        c0926a.m353a(map2 != null ? (String) map2.get("version") : null, palDeviceInfo2.productModel, palDeviceInfo2.deviceId, iThingCloudChannel);
                        C0926a c0926a2 = C0926a.this;
                        Map map3 = map;
                        c0926a2.m354b(map3 != null ? (String) map3.get("activateInfo") : null, palDeviceInfo2.productModel, palDeviceInfo2.deviceId, iThingCloudChannel);
                        try {
                            if (C0926a.this.f731f != null) {
                                C0926a.this.f731f.onCloudChannelCreate(iThingCloudChannel);
                            }
                        } catch (Throwable th) {
                            ALog.m480e(C0926a.f726a, "onCloudChannelCreate error:" + th.toString());
                        }
                    }
                }
            });
            return;
        }
        PalConnectListener palConnectListener = this.f728c;
        if (palConnectListener != null) {
            palConnectListener.onLoad(i, map, palDeviceInfo);
        }
    }

    /* renamed from: a */
    void m353a(final String str, String str2, String str3, IThingCloudChannel iThingCloudChannel) {
        if (iThingCloudChannel == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            ALog.m480e(f726a, "reportVersion channel:" + iThingCloudChannel + " version:" + str + " productKey:" + str2 + " deviceName:" + str3);
            return;
        }
        final String str4 = "/ota/device/inform/" + str2 + "/" + str3;
        HashMap hashMap = new HashMap();
        hashMap.put("id", 1);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("version", str);
        hashMap.put("params", hashMap2);
        iThingCloudChannel.reportData(str4, new JSONObject(hashMap).toString(), new IThingCloudChannel.IChannelActionListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.a.e.a.2
            @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel.IChannelActionListener
            public void onSuccess() {
                ALog.m483i(C0926a.f726a, "reportVersion success version:" + str + " topic:" + str4);
            }

            @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel.IChannelActionListener
            public void onFailed(AError aError) {
                ALog.m484w(C0926a.f726a, "reportVersion fail. aError:" + aError);
            }
        });
    }

    /* renamed from: b */
    void m354b(final String str, String str2, String str3, IThingCloudChannel iThingCloudChannel) {
        if (iThingCloudChannel == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            ALog.m480e(f726a, "reportActivateInfo channel:" + iThingCloudChannel + " activateInfo:" + str + " productKey:" + str2 + " deviceName:" + str3);
            return;
        }
        final String str4 = "/sys/" + str2 + "/" + str3 + "/thing/deviceinfo/update";
        HashMap hashMap = new HashMap();
        hashMap.put("id", Long.valueOf(new SecureRandom().nextLong()));
        hashMap.put("version", "1.0");
        hashMap.put("method", "thing.deviceinfo.update");
        ArrayList arrayList = new ArrayList();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("attrKey", "SYS_ALIOS_ACTIVATION");
        hashMap2.put("attrValue", str);
        hashMap2.put("domain", "SYSTEM");
        arrayList.add(hashMap2);
        hashMap.put("params", arrayList);
        String jSONString = new JSONObject(hashMap).toJSONString();
        ALog.m479d(f726a, "payload:" + jSONString);
        iThingCloudChannel.reportData(str4, jSONString, new IThingCloudChannel.IChannelActionListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.a.e.a.3
            @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel.IChannelActionListener
            public void onSuccess() {
                ALog.m483i(C0926a.f726a, "reportActivateInfo success activateInfo:" + str + " topic:" + str4);
            }

            @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel.IChannelActionListener
            public void onFailed(AError aError) {
                ALog.m484w(C0926a.f726a, "reportActivateInfo fail. aError:" + aError);
            }
        });
    }
}
