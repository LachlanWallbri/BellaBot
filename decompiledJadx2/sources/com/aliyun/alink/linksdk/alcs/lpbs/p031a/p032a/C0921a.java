package com.aliyun.alink.linksdk.alcs.lpbs.p031a.p032a;

import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.ICloudChannelFactory;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IDataDownListener;
import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CloudChannelMgr.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.a.a.a */
/* loaded from: classes.dex */
public class C0921a {

    /* renamed from: a */
    private static final String f715a = "[AlcsLPBS]CloudChannelMgr";

    /* renamed from: b */
    private Map<String, C0922b> f716b = new ConcurrentHashMap();

    /* renamed from: c */
    private ICloudChannelFactory f717c;

    /* renamed from: a */
    public void m340a(ICloudChannelFactory iCloudChannelFactory) {
        this.f717c = iCloudChannelFactory;
    }

    /* renamed from: a */
    public void m342a(PalDeviceInfo palDeviceInfo, Map<String, Object> map, final IDataDownListener iDataDownListener, final ICloudChannelFactory.FactoryListener factoryListener) {
        if (palDeviceInfo == null) {
            ALog.m480e(f715a, "createCloudChannel deviceInfo null");
            if (factoryListener != null) {
                factoryListener.onCreate(palDeviceInfo, null);
                return;
            }
            return;
        }
        ALog.m479d(f715a, "createCloudChannel deviceInfo:" + palDeviceInfo.toString() + " listener: " + iDataDownListener);
        C0922b c0922b = this.f716b.get(palDeviceInfo.getDevId());
        if (c0922b != null) {
            ALog.m479d(f715a, "channel exist");
            if (factoryListener != null) {
                factoryListener.onCreate(palDeviceInfo, c0922b);
                return;
            }
            return;
        }
        ICloudChannelFactory iCloudChannelFactory = this.f717c;
        if (iCloudChannelFactory == null) {
            ALog.m480e(f715a, "mCloudChannelFactory null");
            if (factoryListener != null) {
                factoryListener.onCreate(palDeviceInfo, null);
                return;
            }
            return;
        }
        iCloudChannelFactory.createCloudChannel(palDeviceInfo, map, new ICloudChannelFactory.FactoryListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.a.a.a.1
            @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.ICloudChannelFactory.FactoryListener
            public void onCreate(PalDeviceInfo palDeviceInfo2, IThingCloudChannel iThingCloudChannel) {
                ALog.m479d(C0921a.f715a, "createCloudChannel onCreate channel:" + iThingCloudChannel);
                if (iThingCloudChannel == null) {
                    ALog.m480e(C0921a.f715a, "createCloudChannel error channel null");
                    ICloudChannelFactory.FactoryListener factoryListener2 = factoryListener;
                    if (factoryListener2 != null) {
                        factoryListener2.onCreate(palDeviceInfo2, null);
                        return;
                    }
                    return;
                }
                IDataDownListener iDataDownListener2 = iDataDownListener;
                if (iDataDownListener2 != null) {
                    iThingCloudChannel.addDownDataListener(iDataDownListener2);
                }
                C0922b c0922b2 = new C0922b(iThingCloudChannel, iDataDownListener);
                C0921a.this.f716b.put(palDeviceInfo2.getDevId(), c0922b2);
                ICloudChannelFactory.FactoryListener factoryListener3 = factoryListener;
                if (factoryListener3 != null) {
                    factoryListener3.onCreate(palDeviceInfo2, c0922b2);
                }
            }
        });
    }

    /* renamed from: a */
    public void m341a(PalDeviceInfo palDeviceInfo) {
        if (palDeviceInfo == null) {
            ALog.m480e(f715a, "removeCloudChannel deviceInfo null");
            return;
        }
        ALog.m479d(f715a, "removeCloudChannel deviceInfo: " + palDeviceInfo.toString());
        C0922b c0922b = this.f716b.get(palDeviceInfo.getDevId());
        if (c0922b != null && c0922b.m344a() != null) {
            c0922b.m344a().removeDownDataListener(null);
        }
        ICloudChannelFactory iCloudChannelFactory = this.f717c;
        if (iCloudChannelFactory != null) {
            iCloudChannelFactory.releaseCloudChannel(palDeviceInfo);
        }
        this.f716b.remove(palDeviceInfo.getDevId());
    }

    /* renamed from: b */
    public IThingCloudChannel m343b(PalDeviceInfo palDeviceInfo) {
        return this.f716b.get(palDeviceInfo.getDevId());
    }
}
