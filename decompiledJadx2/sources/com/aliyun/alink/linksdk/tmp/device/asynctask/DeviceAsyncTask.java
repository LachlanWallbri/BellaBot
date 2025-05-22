package com.aliyun.alink.linksdk.tmp.device.asynctask;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;
import com.aliyun.alink.linksdk.tmp.connect.ConnectWrapper;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.device.DeviceImpl;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IDevRawDataListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class DeviceAsyncTask<Task> extends AsyncTask<TmpCommonRequest, TmpCommonResponse> {
    protected static final String TAG = "[Tmp]DeviceAsyncTask";
    protected DeviceConfig mConfig;
    protected ConnectWrapper mConnect;
    protected IDevRawDataListener mDevRawDataListener;
    protected DeviceBasicData mDeviceBasicData;
    protected IDevListener mDeviceHandler;
    protected WeakReference<DeviceImpl> mDeviceImplRef;
    protected DeviceModel mDeviceModel;
    protected Object mTag;
    protected Task mTask = this;
    protected boolean mIsSecure = true;

    /* JADX WARN: Multi-variable type inference failed */
    public DeviceAsyncTask(DeviceImpl deviceImpl, IDevListener iDevListener) {
        this.mDeviceImplRef = new WeakReference<>(deviceImpl);
        this.mDeviceHandler = iDevListener;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        DeviceImpl deviceImpl = this.mDeviceImplRef.get();
        if (deviceImpl == null) {
            return true;
        }
        this.mConnect = deviceImpl.getConnect();
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean onPreTasResult(AsyncTask asyncTask, TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        DeviceImpl deviceImpl = this.mDeviceImplRef.get();
        if (deviceImpl != null && this.mConnect == null) {
            this.mConnect = deviceImpl.getConnect();
        }
        return super.onPreTasResult(asyncTask, (AsyncTask) tmpCommonRequest, (TmpCommonRequest) tmpCommonResponse);
    }

    public Task setConfig(DeviceConfig deviceConfig) {
        this.mConfig = deviceConfig;
        return this.mTask;
    }

    public Task setRawDataListener(IDevRawDataListener iDevRawDataListener) {
        this.mDevRawDataListener = iDevRawDataListener;
        return this.mTask;
    }

    public Task setIsSecure(boolean z) {
        this.mIsSecure = z;
        return this.mTask;
    }

    public Task setDeviceBasicData(DeviceBasicData deviceBasicData) {
        this.mDeviceBasicData = deviceBasicData;
        return this.mTask;
    }

    public Task setConnectWrapper(ConnectWrapper connectWrapper) {
        this.mConnect = connectWrapper;
        return this.mTask;
    }

    public Task setDeviceImpl(DeviceImpl deviceImpl) {
        this.mDeviceImplRef = new WeakReference<>(deviceImpl);
        return this.mTask;
    }

    public Task setTag(Object obj) {
        this.mTag = obj;
        return this.mTask;
    }

    public Task setHandler(IDevListener iDevListener) {
        this.mDeviceHandler = iDevListener;
        return this.mTask;
    }

    public Task setDeviceModel(DeviceModel deviceModel) {
        this.mDeviceModel = deviceModel;
        return this.mTask;
    }

    protected void onNormalFlowComplete(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse, ErrorInfo errorInfo) {
        IDevListener iDevListener = this.mDeviceHandler;
        if (iDevListener == null) {
            LogCat.m471e(TAG, "onFlowComplete handler empty error");
        } else {
            this.mDeviceHandler = null;
            iDevListener.onSuccess(this.mTag, null);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void onFlowComplete(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse, ErrorInfo errorInfo) {
        onNormalFlowComplete(tmpCommonRequest, tmpCommonResponse, errorInfo);
    }

    protected void onNormalFlowError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        IDevListener iDevListener = this.mDeviceHandler;
        if (iDevListener == null) {
            LogCat.m477w(TAG, "onFlowError empty error");
        } else {
            this.mDeviceHandler = null;
            iDevListener.onFail(this.mTag, errorInfo);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public void onFlowError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        onNormalFlowError(tmpCommonRequest, errorInfo);
    }
}
