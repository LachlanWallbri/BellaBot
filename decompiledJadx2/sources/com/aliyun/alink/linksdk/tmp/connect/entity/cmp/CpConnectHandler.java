package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

import com.aliyun.alink.linksdk.cmp.manager.connect.IRegisterConnectListener;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.connect.ConnectFactory;
import com.aliyun.alink.linksdk.tmp.connect.TmpSyncRequestHandler;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CpConnectHandler implements IRegisterConnectListener {
    protected String mConnectId;
    protected IDevListener mListener;

    public CpConnectHandler(String str, IDevListener iDevListener) {
        this.mListener = iDevListener;
        this.mConnectId = str;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
    public void onSuccess() {
        final OutputParams outputParams = new OutputParams(ConnectFactory.mAlcsConnecteId, new ValueWrapper.StringValueWrapper(this.mConnectId));
        if (this.mListener != null) {
            TmpSyncRequestHandler.mMsgHandler.post(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpConnectHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    CpConnectHandler.this.mListener.onSuccess(null, outputParams);
                }
            });
        }
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
    public void onFailure(AError aError) {
        final ErrorInfo errorInfo;
        if (aError == null) {
            errorInfo = new ErrorInfo(300, "param is invalid");
        } else {
            errorInfo = new ErrorInfo(aError.getSubCode(), aError.getMsg());
        }
        if (this.mListener != null) {
            TmpSyncRequestHandler.mMsgHandler.post(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpConnectHandler.2
                @Override // java.lang.Runnable
                public void run() {
                    CpConnectHandler.this.mListener.onFail(null, errorInfo);
                }
            });
        }
    }
}
