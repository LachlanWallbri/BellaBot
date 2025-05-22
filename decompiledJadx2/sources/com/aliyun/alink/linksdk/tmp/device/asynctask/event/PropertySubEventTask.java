package com.aliyun.alink.linksdk.tmp.device.asynctask.event;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpSubEventRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonResponsePayload;
import com.aliyun.alink.linksdk.tmp.device.payload.event.EventRequestPayload;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PropertySubEventTask extends DeviceAsyncTask<PropertySubEventTask> implements IRequestHandler {
    protected static final String TAG = "PropertySubEventTask";
    protected String mEventName;
    protected INotifyHandler mNotifyHandler;

    public PropertySubEventTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setDeviceBasicData(deviceBasicData);
        setDeviceImpl(deviceImpl);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PropertySubEventTask setNotifyListener(INotifyHandler iNotifyHandler) {
        this.mNotifyHandler = iNotifyHandler;
        return (PropertySubEventTask) this.mTask;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PropertySubEventTask setEventNameList(String str) {
        this.mEventName = str;
        return (PropertySubEventTask) this.mTask;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        if (tmpCommonResponse != null && tmpCommonResponse.isSuccess()) {
            CommonResponsePayload commonResponsePayload = (CommonResponsePayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<CommonResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.event.PropertySubEventTask.1
            }.getType());
            if (commonResponsePayload != null && commonResponsePayload.getCode() == 200) {
                LogCat.m469d(TAG, "onLoad normal success");
                taskSuccess(tmpCommonRequest, tmpCommonResponse);
                return;
            } else {
                LogCat.m469d(TAG, "onLoad normal error");
                return;
            }
        }
        LogCat.m469d(TAG, "onLoad error");
        taskError(tmpCommonRequest, new ErrorInfo(300, "response error"));
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        taskError(tmpCommonRequest, errorInfo);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        DeviceImpl deviceImpl;
        super.action();
        EventRequestPayload eventRequestPayload = new EventRequestPayload(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName());
        if (this.mDeviceModel == null && this.mDeviceImplRef != null && (deviceImpl = this.mDeviceImplRef.get()) != null) {
            this.mDeviceModel = deviceImpl.getDeviceModel();
        }
        eventRequestPayload.setMethod(this.mDeviceModel.getEventMethod(this.mEventName));
        this.mConnect.subscribe(TmpSubEventRequestBuilder.createBuilder().setTag(this.mTag).setPort(this.mDeviceBasicData.getPort()).setAddr(this.mDeviceBasicData.getAddr()).setProductKey(this.mDeviceBasicData.getProductKey()).setDeviceName(this.mDeviceBasicData.getDeviceName()).setPath(CommonRequestBuilder.formatPath(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName(), eventRequestPayload.getMethod(), "sys")).setPathMethod(eventRequestPayload.getMethod()).setObserveFlag(true).setIsSecure(true).setPayloadData(eventRequestPayload).createRequest(), this, this.mNotifyHandler);
        LogCat.m469d(TAG, "action mEventNameList:" + this.mEventName + " devId:" + this.mDeviceBasicData.getDeviceName());
        return true;
    }
}
