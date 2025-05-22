package com.aliyun.alink.linksdk.tmp.devicemodel.loader;

import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LoaderWrapperHandler implements ILoaderHandler {
    protected static final String TAG = "[Tmp]SerWrapperListener";
    protected ILoaderHandler mListener;
    protected volatile int taskCount = 0;

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.ILoaderHandler
    public void onSerialize(String str) {
    }

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.ILoaderHandler
    public void onSerializeError(String str) {
    }

    public LoaderWrapperHandler(ILoaderHandler iLoaderHandler) {
        this.mListener = iLoaderHandler;
    }

    public void increaseTaskCount() {
        this.taskCount++;
        LogCat.m469d(TAG, "increaseTaskCount taskCount:" + this.taskCount);
    }

    public void decreaseTaskCount() {
        this.taskCount--;
        LogCat.m469d(TAG, "decreaseTaskCount taskCount:" + this.taskCount);
    }

    public boolean isAllTaskFinished() {
        LogCat.m469d(TAG, "isAllTaskFinished taskCount:" + this.taskCount);
        return this.taskCount == 0;
    }

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.ILoaderHandler
    public void onDeserialize(DeviceModel deviceModel) {
        if (this.mListener == null) {
            LogCat.m471e(TAG, "onDeserialize listener error");
        } else if (isAllTaskFinished()) {
            this.mListener.onDeserialize(deviceModel);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.ILoaderHandler
    public void onDeserializeError(String str) {
        ILoaderHandler iLoaderHandler = this.mListener;
        if (iLoaderHandler == null) {
            LogCat.m471e(TAG, "onDeserializeError listener error");
            return;
        }
        iLoaderHandler.onDeserializeError(str);
        this.mListener = null;
        LogCat.m469d(TAG, "onDeserializeError callback empty listener");
    }
}
