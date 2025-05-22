package com.aliyun.alink.linksdk.tmp.device.asynctask.discovery;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpReDiscoveryRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ProbeTask extends DeviceAsyncTask<DiscoveryTask> implements IRequestHandler {
    public ProbeTask(DeviceImpl deviceImpl, ConnectWrapper connectWrapper, DeviceBasicData deviceBasicData, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setDeviceBasicData(deviceBasicData);
        setConnectWrapper(connectWrapper);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        super.action();
        if (this.mDeviceBasicData == null) {
            ALog.m484w("[Tmp]DeviceAsyncTask", "ProbeTask action mDeviceBasicData: null");
            return false;
        }
        TmpCommonRequest createRequest = TmpReDiscoveryRequestBuilder.createBuilder().setProductKey(this.mDeviceBasicData.getProductKey()).setDeviceName(this.mDeviceBasicData.getDeviceName()).createRequest();
        StringBuilder sb = new StringBuilder();
        sb.append("ProbeTask action mDeviceBasicData:");
        sb.append(this.mDeviceBasicData == null ? "null" : this.mDeviceBasicData.toString());
        ALog.m479d("[Tmp]DeviceAsyncTask", sb.toString());
        return this.mConnect.reDiscoveryDevice(createRequest, this);
    }

    public void stop() {
        ALog.m479d("[Tmp]DeviceAsyncTask", "stop set mDeviceHandler null");
        this.mDeviceHandler = null;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        ALog.m479d("[Tmp]DeviceAsyncTask", "ProbeTask onLoad online  mDeviceBasicData:" + this.mDeviceBasicData.getDevId());
        taskSuccess(tmpCommonRequest, tmpCommonResponse);
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        ALog.m479d("[Tmp]DeviceAsyncTask", "ProbeTask onError offline mDeviceBasicData:" + this.mDeviceBasicData.getDevId());
        taskError(tmpCommonRequest, errorInfo);
    }
}
