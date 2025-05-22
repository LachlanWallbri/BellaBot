package com.aliyun.alink.linksdk.tmp.device.asynctask.setup;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpSetupRequestBuilder;
import com.aliyun.alink.linksdk.tmp.data.auth.AccessInfo;
import com.aliyun.alink.linksdk.tmp.data.auth.SetupData;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.asynctask.service.InvokeServiceTask;
import com.aliyun.alink.linksdk.tmp.device.payload.setup.SetupRequestPayload;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.AuthInfoCreater;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SetupTask extends DeviceAsyncTask<InvokeServiceTask> implements IRequestHandler {
    private static final String TAG = "[Tmp]SetupTask";
    protected SetupData mSetupData;

    public SetupTask(Object obj, DeviceBasicData deviceBasicData, DeviceImpl deviceImpl, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setTag(obj);
        setDeviceBasicData(deviceBasicData);
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        if (tmpCommonResponse != null && tmpCommonResponse.isSuccess()) {
            ALog.m479d(TAG, "SetupTask onLoad taskSuccess");
            taskSuccess(tmpCommonRequest, tmpCommonResponse);
        } else {
            ALog.m479d(TAG, "SetupTask onLoad onError");
            onError(tmpCommonRequest, new ErrorInfo(300, "errror"));
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        ALog.m479d(TAG, "SetupTask onError");
        taskError(tmpCommonRequest, errorInfo);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void taskSuccess(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        SetupData setupData = this.mSetupData;
        if (setupData != null && "ServerAuthInfo".equalsIgnoreCase(setupData.configType)) {
            ALog.m479d(TAG, "setup success change provisioner key");
            for (int i = 0; i < this.mSetupData.configValue.size(); i++) {
                AccessInfo createAccessInfo = AuthInfoCreater.getInstance().createAccessInfo(this.mSetupData.configValue.get(i).authCode, this.mSetupData.configValue.get(i).authSecret, "2");
                TmpStorage.getInstance().saveAccessInfo(this.mDeviceBasicData.getDevId(), createAccessInfo.mAccessKey, createAccessInfo.mAccessToken, true, TmpStorage.FLAG_PROVISIONER);
            }
        }
        super.taskSuccess((SetupTask) tmpCommonRequest, (TmpCommonRequest) tmpCommonResponse);
    }

    public void setParams(Object obj) {
        this.mSetupData = (SetupData) GsonUtils.fromJson(String.valueOf(obj), new TypeToken<SetupData>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.setup.SetupTask.1
        }.getType());
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        super.action();
        SetupRequestPayload setupRequestPayload = new SetupRequestPayload(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName());
        setupRequestPayload.setParams(this.mSetupData);
        boolean send = this.mConnect.send(TmpSetupRequestBuilder.createBuilder(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName()).setAddr(this.mDeviceBasicData.getAddr()).setPort(this.mDeviceBasicData.getPort()).setTag(this.mTag).setProductKey(this.mDeviceBasicData.getProductKey()).setDeviceName(this.mDeviceBasicData.getDeviceName()).setIsSecure(true).setPayloadData(setupRequestPayload).createRequest(), this);
        ALog.m479d(TAG, "SetupTask action bRet：" + send + " ConfigData:" + this.mSetupData.toString());
        return send;
    }
}
