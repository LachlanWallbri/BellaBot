package com.aliyun.alink.linksdk.tmp.device.configuration;

import com.aliyun.alink.linksdk.tmp.listener.IProvisionResponser;
import com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ProvisionResponser implements IProvisionResponser {
    private static final String TAG = "[Tmp]ProvisionResponser";
    protected Object mData;
    protected ErrorInfo mErrorInfo;
    protected int mListenerCount;
    protected ITResResponseCallback mRspCallback;
    protected AtomicInteger mCurFinishCount = new AtomicInteger(0);
    protected boolean mRet = true;

    public ProvisionResponser(ITResResponseCallback iTResResponseCallback, int i) {
        this.mRspCallback = iTResResponseCallback;
        this.mListenerCount = i;
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IProvisionResponser
    public void onComplete(String str, ErrorInfo errorInfo, Object obj) {
        boolean z;
        int incrementAndGet = this.mCurFinishCount.incrementAndGet();
        ALog.m479d(TAG, "onComplete identifer :" + str + " Ret:" + this.mRet + " finishedCount:" + incrementAndGet + " mListenerCount:" + this.mListenerCount + " errorInfo:" + errorInfo + " data:" + obj);
        if (errorInfo == null || errorInfo.getErrorCode() == 200) {
            z = true;
        } else {
            z = false;
            this.mErrorInfo = errorInfo;
            this.mData = obj;
        }
        boolean z2 = this.mRet | z;
        this.mRet = z2;
        if (incrementAndGet >= this.mListenerCount) {
            if (z2) {
                this.mData = obj;
            }
            this.mRspCallback.onComplete(str, this.mErrorInfo, this.mData);
        }
    }
}
