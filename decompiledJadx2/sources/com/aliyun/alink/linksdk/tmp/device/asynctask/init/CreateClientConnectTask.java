package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener;
import com.aliyun.alink.linksdk.tmp.config.DefaultClientConfig;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.data.auth.AccessInfo;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.device.panel.data.AccessInfoPayload;
import com.aliyun.alink.linksdk.tmp.device.panel.data.ProductInfoPayload;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.storage.TmpStorage;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CreateClientConnectTask extends CreateConnectTask {
    public CreateClientConnectTask(DeviceImpl deviceImpl, DeviceBasicData deviceBasicData, DeviceConfig deviceConfig, IDevListener iDevListener) {
        super(deviceImpl, deviceBasicData, deviceConfig, iDevListener);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        DefaultClientConfig defaultClientConfig = (DefaultClientConfig) this.mConfig;
        if (!TextUtils.isEmpty(this.mConfig.getBasicData().getIotId()) && (TextUtils.isEmpty(((DefaultClientConfig) this.mConfig).getAccessKey()) || TextUtils.isEmpty(((DefaultClientConfig) this.mConfig).getAccessToken()))) {
            queryAccessInfo();
            return true;
        }
        if (!TextUtils.isEmpty(this.mConfig.getBasicData().getIotId()) && TextUtils.isEmpty(defaultClientConfig.mDateFormat)) {
            queryProductInfo();
            return true;
        }
        createConnect();
        return true;
    }

    protected void queryProductInfo() {
        final DefaultClientConfig defaultClientConfig = (DefaultClientConfig) this.mConfig;
        if (!TextUtils.isEmpty(defaultClientConfig.mDateFormat)) {
            createConnect();
            return;
        }
        ProductInfoPayload.ProductInfo productInfo = TmpStorage.getInstance().getProductInfo(this.mConfig.getBasicData().getIotId());
        if (productInfo != null && !TextUtils.isEmpty(productInfo.dataFormat)) {
            defaultClientConfig.mDateFormat = productInfo.dataFormat;
            createConnect();
        } else {
            TmpSdk.getCloudProxy().queryProductInfo(this.mConfig.getBasicData().getIotId(), new ICloudProxyListener() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateClientConnectTask.1
                @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
                public void onResponse(String str, Object obj) {
                    if (obj == null) {
                        ALog.m480e("[Tmp]CreateConnectTask", "queryProductInfo aResponse error null");
                        CreateClientConnectTask.this.createConnect();
                        return;
                    }
                    ProductInfoPayload productInfoPayload = (ProductInfoPayload) GsonUtils.fromJson(obj.toString(), new TypeToken<ProductInfoPayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateClientConnectTask.1.1
                    }.getType());
                    if (productInfoPayload == null || productInfoPayload.data == null || TextUtils.isEmpty(productInfoPayload.data.dataFormat)) {
                        ALog.m480e("[Tmp]CreateConnectTask", "queryProductInfo payload error ");
                        CreateClientConnectTask.this.createConnect();
                        return;
                    }
                    defaultClientConfig.mDateFormat = productInfoPayload.data.dataFormat;
                    TmpStorage.getInstance().saveProductInfo(CreateClientConnectTask.this.mConfig.getBasicData().getIotId(), productInfoPayload.data);
                    ALog.m479d("[Tmp]CreateConnectTask", "queryProductInfo onResponse dataFormat:" + defaultClientConfig.mDateFormat + " payload:" + productInfoPayload);
                    CreateClientConnectTask.this.createConnect();
                }

                @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
                public void onFailure(String str, AError aError) {
                    CreateClientConnectTask.this.createConnect();
                }
            });
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateConnectTask, com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onSuccess(Object obj, OutputParams outputParams) {
        super.onSuccess(obj, outputParams);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateConnectTask
    protected void createConnect() {
        DefaultClientConfig defaultClientConfig = (DefaultClientConfig) this.mConfig;
        DeviceManager.getInstance().addDevIotId(defaultClientConfig.getDevId(), defaultClientConfig.getBasicData().getIotId());
        super.createConnect();
    }

    protected void queryAccessInfo() {
        AccessInfo accessInfo;
        ALog.m479d("[Tmp]CreateConnectTask", "queryAccessInfo start");
        if (TextUtils.isEmpty(this.mConfig.getBasicData().getDevId())) {
            TmpStorage.getInstance().getDeviceInfo(this.mConfig.getBasicData().getIotId());
            accessInfo = null;
        } else {
            accessInfo = TmpStorage.getInstance().getAccessInfo(this.mConfig.getBasicData().getDevId());
            updateProDev(this.mConfig.getBasicData().getProductKey(), this.mConfig.getBasicData().getDeviceName());
        }
        if (accessInfo != null) {
            DefaultClientConfig defaultClientConfig = (DefaultClientConfig) this.mConfig;
            defaultClientConfig.setAccessKey(accessInfo.mAccessKey);
            defaultClientConfig.setAccessToken(accessInfo.mAccessToken);
            queryProductInfo();
            return;
        }
        TmpSdk.getCloudProxy().queryAccessInfo(this.mConfig.getBasicData().getIotId(), new ICloudProxyListener() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateClientConnectTask.2
            @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
            public void onResponse(String str, Object obj) {
                AccessInfoPayload accessInfoPayload = (AccessInfoPayload) GsonUtils.fromJson(obj.toString(), new TypeToken<AccessInfoPayload>() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.init.CreateClientConnectTask.2.1
                }.getType());
                ALog.m479d("[Tmp]CreateConnectTask", "queryAccessInfo onResponse payload:" + accessInfoPayload);
                if (accessInfoPayload != null && accessInfoPayload.data != null && !accessInfoPayload.data.isEmpty()) {
                    AccessInfoPayload.AlcsDeviceInfo alcsDeviceInfo = accessInfoPayload.data.get(0);
                    DefaultClientConfig defaultClientConfig2 = (DefaultClientConfig) CreateClientConnectTask.this.mConfig;
                    defaultClientConfig2.setAccessKey(alcsDeviceInfo.accessKey);
                    defaultClientConfig2.setAccessToken(alcsDeviceInfo.accessToken);
                    CreateClientConnectTask.this.updateProDev(alcsDeviceInfo.productKey, alcsDeviceInfo.deviceName);
                    TmpStorage.DeviceInfo deviceInfo = new TmpStorage.DeviceInfo(alcsDeviceInfo.productKey, alcsDeviceInfo.deviceName);
                    TmpStorage.getInstance().saveDeviceInfo(CreateClientConnectTask.this.mConfig.getBasicData().getIotId(), alcsDeviceInfo.productKey, alcsDeviceInfo.deviceName);
                    TmpStorage.getInstance().saveAccessInfo(deviceInfo.getId(), alcsDeviceInfo.accessKey, alcsDeviceInfo.accessToken);
                } else {
                    ALog.m480e("[Tmp]CreateConnectTask", "queryAccessInfo onResponse payload null");
                    AccessInfo accessInfo2 = TmpStorage.getInstance().getAccessInfo(CreateClientConnectTask.this.mConfig.getBasicData().getDevId(), "local");
                    if (accessInfo2 != null && !TextUtils.isEmpty(accessInfo2.mAccessKey) && !TextUtils.isEmpty(accessInfo2.mAccessToken)) {
                        DefaultClientConfig defaultClientConfig3 = (DefaultClientConfig) CreateClientConnectTask.this.mConfig;
                        defaultClientConfig3.setAccessKey(accessInfo2.mAccessKey);
                        defaultClientConfig3.setAccessToken(accessInfo2.mAccessToken);
                    }
                }
                CreateClientConnectTask.this.queryProductInfo();
            }

            @Override // com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener
            public void onFailure(String str, AError aError) {
                ALog.m480e("[Tmp]CreateConnectTask", "queryAccessInfo onResponse  error:" + aError);
                AccessInfo accessInfo2 = TmpStorage.getInstance().getAccessInfo(CreateClientConnectTask.this.mConfig.getBasicData().getDevId(), "local");
                if (accessInfo2 != null && !TextUtils.isEmpty(accessInfo2.mAccessKey) && !TextUtils.isEmpty(accessInfo2.mAccessToken)) {
                    DefaultClientConfig defaultClientConfig2 = (DefaultClientConfig) CreateClientConnectTask.this.mConfig;
                    defaultClientConfig2.setAccessKey(accessInfo2.mAccessKey);
                    defaultClientConfig2.setAccessToken(accessInfo2.mAccessToken);
                }
                CreateClientConnectTask.this.queryProductInfo();
            }
        });
    }
}
