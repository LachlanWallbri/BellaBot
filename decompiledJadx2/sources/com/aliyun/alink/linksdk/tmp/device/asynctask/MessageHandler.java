package com.aliyun.alink.linksdk.tmp.device.asynctask;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.aliyun.alink.linksdk.tmp.connect.TmpSyncRequestHandler;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MessageHandler extends Handler {
    public static final int ONERROR_MSG = 2;
    public static final int ONLOAD_MSG = 1;
    public static final int ONNOTIFY_MSG = 3;
    protected static final String TAG = "[Tmp]MessageHandler";

    public MessageHandler(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what == 1) {
            LogCat.m469d(TAG, "handleMessage ONLOAD_MSG");
            TmpSyncRequestHandler.LoadMsg loadMsg = (TmpSyncRequestHandler.LoadMsg) message.obj;
            if (loadMsg != null) {
                if (loadMsg.mInterceptor != null) {
                    loadMsg.mInterceptor.onLoad(loadMsg.mRequest, loadMsg.mResponse);
                }
                if (loadMsg.mRequestHandler != null) {
                    loadMsg.mRequestHandler.onLoad(loadMsg.mRequest, loadMsg.mResponse);
                    return;
                }
                return;
            }
            return;
        }
        if (message.what == 2) {
            LogCat.m469d(TAG, "handleMessage ONERROR_MSG");
            TmpSyncRequestHandler.ErrorMsg errorMsg = (TmpSyncRequestHandler.ErrorMsg) message.obj;
            if (errorMsg != null) {
                if (errorMsg.mInterceptor != null) {
                    errorMsg.mInterceptor.onError(errorMsg.mRequest, errorMsg.mErrorInfo);
                }
                if (errorMsg.mRequestHandler != null) {
                    errorMsg.mRequestHandler.onError(errorMsg.mRequest, errorMsg.mErrorInfo);
                    return;
                }
                return;
            }
            return;
        }
        if (message.what == 3) {
            LogCat.m469d(TAG, "handleMessage ONNOTIFY_MSG");
            TmpSyncRequestHandler.NotifyMsg notifyMsg = (TmpSyncRequestHandler.NotifyMsg) message.obj;
            if (notifyMsg == null || notifyMsg.mNotifyHandler == null) {
                return;
            }
            notifyMsg.mNotifyHandler.onMessage(notifyMsg.mRequest, notifyMsg.mResponse);
            return;
        }
        LogCat.m471e(TAG, "handleMessage other");
        super.handleMessage(message);
    }
}
