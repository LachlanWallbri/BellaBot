package com.aliyun.alink.linksdk.tmp.device.asynctask.init;

import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask;
import com.aliyun.alink.linksdk.tmp.device.deviceshadow.TDeviceShadow;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ShadowInitTask extends DeviceAsyncTask<ModelSerializeTask> implements IDevListener {
    protected WeakReference<TDeviceShadow> mShadowRef;

    public ShadowInitTask(TDeviceShadow tDeviceShadow, IDevListener iDevListener) {
        super(null, iDevListener);
        this.mShadowRef = new WeakReference<>(tDeviceShadow);
    }

    @Override // com.aliyun.alink.linksdk.tmp.device.asynctask.DeviceAsyncTask, com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTask
    public boolean action() {
        TDeviceShadow tDeviceShadow = this.mShadowRef.get();
        if (tDeviceShadow != null) {
            tDeviceShadow.init(this);
            return true;
        }
        onFail(null, new ErrorInfo(300, "param is invalid"));
        return true;
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onSuccess(Object obj, OutputParams outputParams) {
        taskSuccess(null, null);
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onFail(Object obj, ErrorInfo errorInfo) {
        taskError(null, errorInfo);
    }
}
