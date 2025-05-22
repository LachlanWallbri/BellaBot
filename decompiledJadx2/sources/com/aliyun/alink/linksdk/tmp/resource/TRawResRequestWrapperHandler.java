package com.aliyun.alink.linksdk.tmp.resource;

import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.listener.ITRawDataRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TRawResRequestWrapperHandler implements ITResRequestInnerHandler {
    private static final String TAG = "[Tmp]TRawResRequestWrapperHandler";
    protected ITRawDataRequestHandler mHandler;

    public TRawResRequestWrapperHandler(ITRawDataRequestHandler iTRawDataRequestHandler) {
        this.mHandler = iTRawDataRequestHandler;
    }

    @Override // com.aliyun.alink.linksdk.tmp.resource.ITResRequestInnerHandler
    public void onProcess(String str, String str2, Object obj, ITResResponseCallback iTResResponseCallback) {
        ALog.m479d(TAG, "onProcess identifier:" + str2 + " topic:" + str2 + " payload:" + obj + " mHandler:" + this.mHandler);
        ITRawDataRequestHandler iTRawDataRequestHandler = this.mHandler;
        if (iTRawDataRequestHandler != null) {
            iTRawDataRequestHandler.onProcess(str, obj, iTResResponseCallback);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onSuccess(Object obj, OutputParams outputParams) {
        ALog.m479d(TAG, "onSuccess identifier:" + outputParams + " returnValue: mHandler:" + this.mHandler);
        ITRawDataRequestHandler iTRawDataRequestHandler = this.mHandler;
        if (iTRawDataRequestHandler != null) {
            iTRawDataRequestHandler.onSuccess(obj, outputParams);
        }
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
    public void onFail(Object obj, ErrorInfo errorInfo) {
        ALog.m479d(TAG, "onFail errorInfo:" + errorInfo + " returnValue: mHandler:" + this.mHandler);
        ITRawDataRequestHandler iTRawDataRequestHandler = this.mHandler;
        if (iTRawDataRequestHandler != null) {
            iTRawDataRequestHandler.onSuccess(obj, errorInfo);
        }
    }
}
