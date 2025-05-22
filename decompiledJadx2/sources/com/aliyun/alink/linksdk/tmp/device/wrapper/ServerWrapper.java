package com.aliyun.alink.linksdk.tmp.device.wrapper;

import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.listener.IDevRawDataListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.listener.ITRawDataRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ServerWrapper extends CommonDevWrapper {
    public ServerWrapper(DeviceConfig deviceConfig) {
        super(deviceConfig);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ServerWrapper() {
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public String regRes(String str, boolean z, ITResRequestHandler iTResRequestHandler) {
        return this.mDeviceImpl.regRes(str, z, iTResRequestHandler);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean unRegRes(String str, ITResRequestHandler iTResRequestHandler) {
        return this.mDeviceImpl.unRegRes(str, iTResRequestHandler);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean triggerRes(String str, OutputParams outputParams, IPublishResourceListener iPublishResourceListener) {
        return this.mDeviceImpl.triggerRes(str, outputParams, iPublishResourceListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean setPropertyValue(Map<String, ValueWrapper> map, boolean z, IPublishResourceListener iPublishResourceListener) {
        return this.mDeviceImpl.setPropertyValue(map, z, iPublishResourceListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean sendRawData(byte[] bArr, IDevRawDataListener iDevRawDataListener) {
        return this.mDeviceImpl.publishRawData(bArr, iDevRawDataListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.wrapper.CommonDevWrapper, com.aliyun.alink.linksdk.tmp.api.IDevice
    public boolean regRawRes(boolean z, ITRawDataRequestHandler iTRawDataRequestHandler) {
        return this.mDeviceImpl.regRawRes(z, iTRawDataRequestHandler);
    }
}
