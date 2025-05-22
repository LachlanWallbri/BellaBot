package com.aliyun.alink.linksdk.alcs.lpbs.component.cloud;

import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ICloudChannelFactory {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface FactoryListener {
        void onCreate(PalDeviceInfo palDeviceInfo, IThingCloudChannel iThingCloudChannel);
    }

    void createCloudChannel(PalDeviceInfo palDeviceInfo, Map<String, Object> map, FactoryListener factoryListener);

    void releaseCloudChannel(PalDeviceInfo palDeviceInfo);
}
