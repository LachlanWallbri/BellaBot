package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DefaultServerConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper;
import com.aliyun.alink.linksdk.tmp.connect.IConnect;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.resource.DiscoveryResHander;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class RegDefaultResTask extends DeviceAsyncTask<RegDefaultResTask> {
    protected static DiscoveryResHander mDiscoveryResHandler;
    protected static Object mSynchronizedObj = new Object();

    public RegDefaultResTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setDeviceImpl(deviceImpl);
        setDeviceBasicData(deviceBasicData);
        setConfig(deviceConfig);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public synchronized boolean action() {
        DeviceImpl deviceImpl = this.mDeviceImplRef.get();
        try {
            synchronized (mSynchronizedObj) {
                if (mDiscoveryResHandler == null) {
                    mDiscoveryResHandler = new DiscoveryResHander(this.mConfig.getBasicData().getProductKey(), this.mConfig.getBasicData().getDeviceName(), deviceImpl == null ? null : deviceImpl.getDeviceModel());
                } else {
                    mDiscoveryResHandler.setData(this.mConfig.getBasicData().getProductKey(), this.mConfig.getBasicData().getDeviceName(), deviceImpl == null ? null : deviceImpl.getDeviceModel());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((DeviceConfig.DeviceType.SERVER == this.mConfig.getDeviceType() || DeviceConfig.DeviceType.PROVISION_RECEIVER == this.mConfig.getDeviceType()) && DefaultServerConfig.ConnectType.isConnectContainCoap(((DefaultServerConfig) this.mConfig).getConnectType())) {
            ConnectWrapper connect = deviceImpl.getConnect();
            ALog.m479d("[Tmp]DeviceAsyncTask", "regRes METHOD_IDENTIFIER_DEV connect+:" + connect);
            if (connect != null) {
                deviceImpl.regRes(connect.getConnectId(IConnect.ConnectType.CONNECT_TYPE_COAP), "dev", false, mDiscoveryResHandler);
            }
        }
        taskSuccess(null, null);
        return false;
    }
}
