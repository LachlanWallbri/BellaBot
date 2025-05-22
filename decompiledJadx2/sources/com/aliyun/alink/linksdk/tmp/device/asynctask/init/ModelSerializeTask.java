package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.ConnectFactory;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.connect.builder.TmpGetTslRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.payload.discovery.GetTslResponsePayload;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.devicemodel.loader.ILoaderHandler;
import com.aliyun.alink.linksdk.tmp.devicemodel.loader.RootDeviceModelSerializer;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ModelSerializeTask extends DeviceAsyncTask<ModelSerializeTask> implements IRequestHandler, ILoaderHandler {
    protected static final String TAG = "[Tmp]SerializeTask";
    protected DeviceConfig mConfig;

    public ModelSerializeTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        super(deviceImpl, iDevListener);
        setDeviceImpl(deviceImpl);
        setDeviceBasicData(deviceBasicData);
        this.mConfig = deviceConfig;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        String tsl;
        DeviceModel modelObject = RootDeviceModelSerializer.getInstance().getModelObject(this.mDeviceBasicData.getDevId());
        if (modelObject != null) {
            ALog.m479d(TAG, "action deviceModel found ret");
            DeviceImpl deviceImpl = this.mDeviceImplRef.get();
            if (deviceImpl != null) {
                deviceImpl.setDeviceModel(modelObject);
            }
            taskSuccess(null, null);
            return true;
        }
        if (TextUtils.isEmpty(this.mDeviceBasicData.getDeviceModelJson())) {
            if (TextUtils.isEmpty(this.mDeviceBasicData.getDevId())) {
                TmpStorage.DeviceInfo deviceInfo = TmpStorage.getInstance().getDeviceInfo(this.mConfig.getBasicData().getIotId());
                tsl = deviceInfo != null ? TmpStorage.getInstance().getTsl(deviceInfo.getId()) : null;
            } else {
                tsl = TmpStorage.getInstance().getTsl(this.mDeviceBasicData.getDevId());
            }
            if (TextUtils.isEmpty(tsl)) {
                this.mConnect = ConnectFactory.createConnect(ConnectSDK.getInstance().getApiGatewayConnectId());
                return this.mConnect.send(TmpGetTslRequestBuilder.createBuilder().setTag(this.mTag).setIotId(this.mConfig.getBasicData().getIotId()).createRequest(), this);
            }
            this.mDeviceBasicData.setDeviceModelJson(tsl);
            onLoad(null, null);
        } else {
            onLoad(null, null);
        }
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void onFlowComplete(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse, ErrorInfo errorInfo) {
        onFlowError(tmpCommonRequest, errorInfo);
    }

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.ILoaderHandler
    public void onDeserialize(DeviceModel deviceModel) {
        LogCat.m469d(TAG, "onDeserialize:" + deviceModel);
        if (deviceModel != null) {
            RootDeviceModelSerializer.getInstance().insertModelObject(this.mDeviceBasicData.getDevId(), deviceModel);
        }
        DeviceImpl deviceImpl = this.mDeviceImplRef.get();
        if (deviceImpl != null) {
            deviceImpl.setDeviceModel(deviceModel);
        }
        taskSuccess(null, null);
    }

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.ILoaderHandler
    public void onDeserializeError(String str) {
        LogCat.m469d(TAG, "onDeserializeError:" + str);
        taskError(null, new ErrorInfo(300, "param is invalid"));
    }

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.ILoaderHandler
    public void onSerialize(String str) {
        LogCat.m471e(TAG, "onSerialize");
    }

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.ILoaderHandler
    public void onSerializeError(String str) {
        LogCat.m471e(TAG, "onSerializeError");
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        GetTslResponsePayload getTslResponsePayload;
        ALog.m479d(TAG, "onLoad response:" + tmpCommonResponse);
        if (tmpCommonResponse != null && (getTslResponsePayload = (GetTslResponsePayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<GetTslResponsePayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.init.ModelSerializeTask.1
        }.getType())) != null && getTslResponsePayload.data != null) {
            String json = GsonUtils.toJson(getTslResponsePayload.data);
            this.mDeviceBasicData.setDeviceModelJson(json);
            TmpStorage.getInstance().saveTsl(this.mDeviceBasicData.getDevId(), json);
            TmpStorage.getInstance().saveDeviceInfo(this.mConfig.getBasicData().getIotId(), this.mDeviceBasicData.getProdKey(), this.mDeviceBasicData.getDeviceName());
        }
        RootDeviceModelSerializer.getInstance().deserialize(RootDeviceModelSerializer.SINGLEEXTEND_DEVICEMODELSERIALIZER_ID, this.mDeviceBasicData.getDeviceModelJson(), this);
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        taskError(tmpCommonRequest, errorInfo);
    }
}
