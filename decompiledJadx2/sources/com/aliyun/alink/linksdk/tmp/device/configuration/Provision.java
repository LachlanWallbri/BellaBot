package com.aliyun.alink.linksdk.tmp.device.configuration;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.api.IProvision;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Provision implements IProvision {
    private static final String TAG = "[Tmp]Provision";
    private DeviceImpl mDeviceImpl;

    public Provision(DeviceConfig deviceConfig) {
        DeviceBasicData deviceBasicData = DeviceManager.getInstance().getDeviceBasicData(deviceConfig.getBasicData().getDevId());
        this.mDeviceImpl = new DeviceImpl(deviceConfig, deviceBasicData == null ? new DeviceBasicData(deviceConfig.getBasicData()) : deviceBasicData);
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IProvision
    public void provisionInit(Object obj, IDevListener iDevListener) {
        ALog.m479d(TAG, "init tag");
        this.mDeviceImpl.init(obj, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IProvision
    public void unInit() {
        ALog.m479d(TAG, "unInit");
        DeviceImpl deviceImpl = this.mDeviceImpl;
        if (deviceImpl != null) {
            deviceImpl.unInit();
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.api.IProvision
    public boolean setConfiData(Object obj, Object obj2, IDevListener iDevListener) {
        ALog.m479d(TAG, "setup configData:" + obj);
        return this.mDeviceImpl.setup(obj, obj2, iDevListener);
    }
}
