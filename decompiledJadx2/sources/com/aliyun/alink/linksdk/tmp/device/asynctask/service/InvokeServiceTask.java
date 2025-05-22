package com.aliyun.alink.linksdk.tmp.device.asynctask.service;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpServiceRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.payload.KeyValuePair;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.device.payload.service.ServiceRequestPayload;
import com.aliyun.alink.linksdk.tmp.device.payload.service.ServiceResponsePayload;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class InvokeServiceTask extends DeviceAsyncTask<InvokeServiceTask> implements IRequestHandler {
    protected static final String TAG = "[Tmp]InvokeServiceTask";
    protected List<KeyValuePair> mServiceArgs;
    protected String mServiceIdentifier;

    public InvokeServiceTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setDeviceBasicData(deviceBasicData);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask
    public InvokeServiceTask setDeviceModel(DeviceModel deviceModel) {
        this.mDeviceModel = deviceModel;
        return this;
    }

    public InvokeServiceTask setServiceIdentifier(String str) {
        this.mServiceIdentifier = str;
        return this;
    }

    public InvokeServiceTask setServiceArgs(List<KeyValuePair> list) {
        this.mServiceArgs = list;
        return this;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        super.action();
        HashMap hashMap = new HashMap();
        List<KeyValuePair> list = this.mServiceArgs;
        if (list != null && !list.isEmpty()) {
            for (KeyValuePair keyValuePair : this.mServiceArgs) {
                hashMap.put(keyValuePair.getKey(), keyValuePair.getValueWrapper());
            }
        }
        if (this.mDeviceBasicData == null || this.mDeviceModel == null || this.mConnect == null) {
            ALog.m480e(TAG, "mDeviceBasicData or mDeviceModel or mConnect null");
            taskError(null, new ErrorInfo(300, "param is invalid"));
            return false;
        }
        ServiceRequestPayload serviceRequestPayload = new ServiceRequestPayload(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName());
        serviceRequestPayload.setMethod(this.mDeviceModel.getServiceMethod(this.mServiceIdentifier));
        serviceRequestPayload.setParams((Map<String, ValueWrapper>) hashMap);
        this.mConnect.send(TmpServiceRequestBuilder.createBuilder().setAddr(this.mDeviceBasicData.getAddr()).setPort(this.mDeviceBasicData.getPort()).setTag(this.mTag).setProductKey(this.mDeviceModel.getProfile().getProdKey()).setDeviceName(this.mDeviceModel.getProfile().getName()).setPathMethod(this.mDeviceModel.getServiceMethod(this.mServiceIdentifier)).setIsSecure(true).setPayloadData(serviceRequestPayload).createRequest(), this);
        LogCat.m469d(TAG, "mServiceIdentifier:" + this.mServiceIdentifier + " mServiceArgs:" + this.mServiceArgs);
        return false;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void onFlowComplete(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse, ErrorInfo errorInfo) {
        if (this.mDeviceHandler == null) {
            LogCat.m471e(TAG, "onFlowComplete handler empty error");
        } else if (tmpCommonRequest != null) {
            this.mDeviceHandler.onSuccess(tmpCommonRequest.getTag(), new OutputParams(((ServiceResponsePayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<ServiceResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.service.InvokeServiceTask.1
            }.getType())).getData()));
        } else {
            super.onFlowComplete(tmpCommonRequest, tmpCommonResponse, errorInfo);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        ServiceResponsePayload serviceResponsePayload;
        if (tmpCommonResponse != null && tmpCommonResponse.isSuccess() && (serviceResponsePayload = (ServiceResponsePayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<ServiceResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.service.InvokeServiceTask.2
        }.getType())) != null && serviceResponsePayload.getCode() == 200) {
            taskSuccess(tmpCommonRequest, tmpCommonResponse);
            return;
        }
        LogCat.m471e(TAG, "onLoad error response:" + tmpCommonResponse);
        taskError(tmpCommonRequest, new ErrorInfo(300, "response error"));
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        taskError(tmpCommonRequest, errorInfo);
    }
}
