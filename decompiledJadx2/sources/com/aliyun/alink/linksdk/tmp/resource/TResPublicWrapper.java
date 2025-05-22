package com.aliyun.alink.linksdk.tmp.resource;

import com.aliyun.alink.linksdk.cmp.core.base.AResource;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TResPublicWrapper implements IConnectPublishResourceListener {
    private static final String TAG = "[Tmp]TResPublicWrapper";
    private IPublishResourceListener mListener;
    private String mResId;

    public TResPublicWrapper(String str, IPublishResourceListener iPublishResourceListener) {
        this.mListener = iPublishResourceListener;
        this.mResId = str;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectPublishResourceListener
    public void onSuccess(AResource aResource) {
        ALog.m479d(TAG, "onSuccess mResId:" + this.mResId + " mListener:" + this.mListener + " aResource:" + aResource);
        IPublishResourceListener iPublishResourceListener = this.mListener;
        if (iPublishResourceListener != null) {
            iPublishResourceListener.onSuccess(this.mResId, null);
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectPublishResourceListener
    public void onFailure(AResource aResource, AError aError) {
        ALog.m479d(TAG, "onSuccess mResId:" + this.mResId + " mListener:" + this.mListener + " aResource:" + aResource);
        if (this.mListener == null || aError == null || aError.getCode() == 517) {
            return;
        }
        this.mListener.onError(this.mResId, aError);
    }
}
