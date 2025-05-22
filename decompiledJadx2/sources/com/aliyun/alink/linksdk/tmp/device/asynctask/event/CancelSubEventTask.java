package com.aliyun.alink.linksdk.tmp.device.asynctask.event;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpCancelSubEventRequestBuilder;
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
public class CancelSubEventTask extends DeviceAsyncTask<CancelSubEventTask> implements IRequestHandler {
    protected String mEventName;
    protected INotifyHandler mNotifyHandler;

    public CancelSubEventTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setDeviceBasicData(deviceBasicData);
        setDeviceImpl(deviceImpl);
    }

    public CancelSubEventTask setEventNameList(String str) {
        this.mEventName = str;
        return this;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        DeviceImpl deviceImpl;
        super.action();
        EventRequestPayload eventRequestPayload = new EventRequestPayload(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName());
        if (this.mDeviceModel == null && this.mDeviceImplRef != null && (deviceImpl = this.mDeviceImplRef.get()) != null) {
            this.mDeviceModel = deviceImpl.getDeviceModel();
        }
        if (this.mDeviceModel != null) {
            eventRequestPayload.setMethod(this.mDeviceModel.getEventMethod(this.mEventName));
        }
        TmpCommonRequest createRequest = TmpCancelSubEventRequestBuilder.createBuilder().setAddr(this.mDeviceBasicData.getAddr()).setPort(this.mDeviceBasicData.getPort()).setProductKey(this.mDeviceBasicData.getProductKey()).setDeviceName(this.mDeviceBasicData.getDeviceName()).setTag(this.mTag).setIsSecure(true).setObserveFlag(false).setPathMethod(eventRequestPayload.getMethod()).setPath(CommonRequestBuilder.formatPath(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName(), eventRequestPayload.getMethod(), "sys")).setPayloadData(eventRequestPayload).createRequest();
        if (this.mConnect != null) {
            this.mConnect.unsubscribe(createRequest, this);
        }
        LogCat.m469d("[Tmp]DeviceAsyncTask", "action mEventNameList:" + this.mEventName + " devId:" + this.mDeviceBasicData.getDeviceName());
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        if (tmpCommonResponse != null && tmpCommonResponse.isSuccess() && ((CommonResponsePayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<CommonResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.event.CancelSubEventTask.1
        }.getType())).getCode() == 200) {
            LogCat.m469d("[Tmp]DeviceAsyncTask", "onLoad normal success");
            taskSuccess(tmpCommonRequest, tmpCommonResponse);
        } else {
            LogCat.m469d("[Tmp]DeviceAsyncTask", "onLoad normal error");
            taskError(tmpCommonRequest, new ErrorInfo(300, "response error"));
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        taskError(tmpCommonRequest, errorInfo);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void taskSuccess(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        removeEvent();
        super.taskSuccess((CancelSubEventTask) tmpCommonRequest, (TmpCommonRequest) tmpCommonResponse);
    }

    protected boolean removeEvent() {
        DeviceImpl deviceImpl = this.mDeviceImplRef.get();
        if (deviceImpl == null || TextUtils.isEmpty(this.mEventName)) {
            return false;
        }
        deviceImpl.removeSubscribeEventAndListener(this.mEventName);
        return true;
    }
}
