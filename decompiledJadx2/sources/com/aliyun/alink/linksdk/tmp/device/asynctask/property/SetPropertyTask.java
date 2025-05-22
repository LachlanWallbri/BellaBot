package com.aliyun.alink.linksdk.tmp.device.asynctask.property;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpSetPropertyRequestBuilder;
import com.aliyun.alink.linksdk.tmp.data.p044ut.ExtraData;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.deviceshadow.TDeviceShadow;
import com.aliyun.alink.linksdk.tmp.device.payload.KeyValuePair;
import com.aliyun.alink.linksdk.tmp.device.payload.property.SetPropertyRequestPayload;
import com.aliyun.alink.linksdk.tmp.device.payload.property.SetPropertyResponsePayload;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SetPropertyTask extends DeviceAsyncTask<SetPropertyTask> implements IRequestHandler {
    protected static final String TAG = "[Tmp]SetPropertyTask";
    protected WeakReference<TDeviceShadow> mDeviceShadowRef;
    protected ExtraData mExtraData;
    protected List<KeyValuePair> mPropertyPair;

    public SetPropertyTask(TDeviceShadow tDeviceShadow, DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setDeviceBasicData(deviceBasicData);
        this.mDeviceShadowRef = new WeakReference<>(tDeviceShadow);
    }

    public SetPropertyTask setProperties(List<KeyValuePair> list) {
        this.mPropertyPair = list;
        return this;
    }

    public SetPropertyTask setExtraData(ExtraData extraData) {
        this.mExtraData = extraData;
        return this;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        SetPropertyResponsePayload setPropertyResponsePayload;
        if (tmpCommonResponse != null && tmpCommonResponse.isSuccess() && (setPropertyResponsePayload = (SetPropertyResponsePayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<SetPropertyResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.property.SetPropertyTask.1
        }.getType())) != null && setPropertyResponsePayload.getCode() == 200) {
            TDeviceShadow tDeviceShadow = this.mDeviceShadowRef.get();
            for (KeyValuePair keyValuePair : this.mPropertyPair) {
                if (tDeviceShadow != null) {
                    tDeviceShadow.setPropertyValue(keyValuePair.getKey(), keyValuePair.getValueWrapper(), false, (IPublishResourceListener) null);
                }
            }
            LogCat.m469d(TAG, "onLoad success");
            taskSuccess(tmpCommonRequest, tmpCommonResponse);
            return;
        }
        LogCat.m469d(TAG, "onLoad error response:" + tmpCommonResponse.toString());
        taskError(tmpCommonRequest, new ErrorInfo(300, "response error"));
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        taskError(tmpCommonRequest, errorInfo);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        super.action();
        if (this.mDeviceBasicData == null || this.mDeviceModel == null || this.mConnect == null) {
            ALog.m480e(TAG, "mDeviceBasicData or mDeviceModel or mConnect null ：" + this.mDeviceBasicData + " mDeviceModel：" + this.mDeviceModel + " mConnect：" + this.mConnect);
            taskError(null, new ErrorInfo(300, "param is invalid"));
            return false;
        }
        SetPropertyRequestPayload setPropertyRequestPayload = new SetPropertyRequestPayload(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName());
        HashMap hashMap = new HashMap();
        List<KeyValuePair> list = this.mPropertyPair;
        if (list == null || list.isEmpty()) {
            taskError(null, new ErrorInfo(300, "param is invalid"));
            return false;
        }
        for (KeyValuePair keyValuePair : this.mPropertyPair) {
            hashMap.put(keyValuePair.getKey(), keyValuePair.getValueWrapper());
        }
        setPropertyRequestPayload.setParams(hashMap);
        setPropertyRequestPayload.setMethod(this.mDeviceModel.getServiceMethod(TmpConstant.PROPERTY_IDENTIFIER_SET));
        TmpSetPropertyRequestBuilder createBuilder = TmpSetPropertyRequestBuilder.createBuilder();
        ExtraData extraData = this.mExtraData;
        this.mConnect.send(createBuilder.setPerformanceId(extraData == null ? 0 : extraData.performanceId).setAddr(this.mDeviceBasicData.getAddr()).setPort(this.mDeviceBasicData.getPort()).setProductKey(this.mDeviceBasicData.getProductKey()).setDeviceName(this.mDeviceBasicData.getDeviceName()).setPathMethod(setPropertyRequestPayload.getMethod()).setPath(CommonRequestBuilder.formatPath(this.mDeviceModel.getProfile(), setPropertyRequestPayload.getMethod())).setTag(this.mTag).setIsSecure(true).setPayloadData(setPropertyRequestPayload).createRequest(), this);
        LogCat.m469d(TAG, "properties :" + this.mPropertyPair + " mIsSecure:" + this.mIsSecure);
        return false;
    }
}
