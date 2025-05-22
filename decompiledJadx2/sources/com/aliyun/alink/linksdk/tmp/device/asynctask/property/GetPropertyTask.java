package com.aliyun.alink.linksdk.tmp.device.asynctask.property;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpGetPropertyRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.deviceshadow.TDeviceShadow;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.device.payload.property.GetPropertyRequestPayload;
import com.aliyun.alink.linksdk.tmp.device.payload.property.GetPropertyResponsePayload;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class GetPropertyTask extends DeviceAsyncTask<GetPropertyTask> implements IRequestHandler {
    protected static final String TAG = "AllPropertyTask";
    protected WeakReference<TDeviceShadow> mDeviceShadowRef;
    protected List<String> mPropertyIdentifierList;

    public GetPropertyTask(TDeviceShadow tDeviceShadow, DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        this.mDeviceShadowRef = new WeakReference<>(tDeviceShadow);
        setDeviceBasicData(deviceBasicData);
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        if (tmpCommonResponse != null && tmpCommonResponse.isSuccess()) {
            LogCat.m473i(TAG, "onLoad response success");
            GetPropertyResponsePayload getPropertyResponsePayload = (GetPropertyResponsePayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<GetPropertyResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.property.GetPropertyTask.1
            }.getType());
            if (getPropertyResponsePayload != null && getPropertyResponsePayload.getCode() == 200) {
                LogCat.m473i(TAG, "onLoad response payload success");
                Map<String, ValueWrapper> property = getPropertyResponsePayload.getProperty();
                TDeviceShadow tDeviceShadow = this.mDeviceShadowRef.get();
                if (property != null && property != null && !property.isEmpty() && tDeviceShadow != null) {
                    for (Map.Entry<String, ValueWrapper> entry : property.entrySet()) {
                        tDeviceShadow.setPropertyValue(entry.getKey(), entry.getValue(), false, (IPublishResourceListener) null);
                    }
                }
                LogCat.m473i(TAG, "onLoad taskSuccess");
                taskSuccess(tmpCommonRequest, tmpCommonResponse);
                return;
            }
        }
        LogCat.m473i(TAG, "onLoad taskError");
        taskError(tmpCommonRequest, new ErrorInfo(300, "response error"));
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        taskError(tmpCommonRequest, errorInfo);
    }

    public GetPropertyTask setPropertyList(List<String> list) {
        this.mPropertyIdentifierList = list;
        return this;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        String str;
        super.action();
        if (this.mDeviceBasicData == null || this.mDeviceModel == null || this.mConnect == null) {
            ALog.m480e(TAG, "mDeviceBasicData or mDeviceModel or mConnect null");
            taskError(null, new ErrorInfo(300, "param is invalid"));
            return false;
        }
        GetPropertyRequestPayload getPropertyRequestPayload = new GetPropertyRequestPayload(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName());
        getPropertyRequestPayload.setProperty(this.mPropertyIdentifierList);
        getPropertyRequestPayload.setMethod(this.mDeviceModel.getServiceMethod(TmpConstant.PROPERTY_IDENTIFIER_GET));
        this.mConnect.send(TmpGetPropertyRequestBuilder.createBuilder().setProductKey(this.mDeviceBasicData.getProductKey()).setDeviceName(this.mDeviceBasicData.getDeviceName()).setAddr(this.mDeviceBasicData.getAddr()).setPort(this.mDeviceBasicData.getPort()).setPathMethod(getPropertyRequestPayload.getMethod()).setPath(CommonRequestBuilder.formatPath(this.mDeviceBasicData.getProductKey(), this.mDeviceBasicData.getDeviceName(), getPropertyRequestPayload.getMethod(), "sys")).setTag(this.mTag).setIsSecure(true).setPayloadData(getPropertyRequestPayload).createRequest(), this);
        if (("properties :" + this.mPropertyIdentifierList) == null) {
            str = "empty";
        } else {
            str = this.mPropertyIdentifierList.toString() + " mIsSecure:" + this.mIsSecure;
        }
        LogCat.m469d(TAG, str);
        return true;
    }
}
