package com.aliyun.alink.linksdk.tmp.connect;

import android.os.Looper;
import android.os.Message;
import com.aliyun.alink.linksdk.tmp.device.asynctask.MessageHandler;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpSyncRequestHandler implements IRequestHandler, INotifyHandler {
    protected static final String TAG = "[Tmp]TmpSyncRequestHandler";
    public static MessageHandler mMsgHandler;
    protected HandlerInterceptor mInterceptor;
    protected INotifyHandler mNotifyHandler;
    protected TmpCommonRequest mRequest;
    protected IRequestHandler mRequestHandler;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static abstract class HandlerInterceptor implements IRequestHandler {
    }

    public TmpSyncRequestHandler(IRequestHandler iRequestHandler, HandlerInterceptor handlerInterceptor, TmpCommonRequest tmpCommonRequest) {
        this(iRequestHandler, tmpCommonRequest);
        this.mInterceptor = handlerInterceptor;
    }

    public static void init() {
        if (mMsgHandler == null) {
            mMsgHandler = new MessageHandler(Looper.getMainLooper());
        }
    }

    public TmpSyncRequestHandler(IRequestHandler iRequestHandler, TmpCommonRequest tmpCommonRequest) {
        this.mRequestHandler = iRequestHandler;
        this.mRequest = tmpCommonRequest;
        if (isMulThreadCallback()) {
            LogCat.m469d(TAG, "TmpSyncRequestHandler multhead callback");
        } else if (mMsgHandler == null) {
            mMsgHandler = new MessageHandler(Looper.getMainLooper());
        }
    }

    public TmpSyncRequestHandler(INotifyHandler iNotifyHandler, TmpCommonRequest tmpCommonRequest) {
        this.mNotifyHandler = iNotifyHandler;
        this.mRequest = tmpCommonRequest;
        if (isMulThreadCallback()) {
            LogCat.m469d(TAG, "TmpSyncRequestHandler multhead callback");
        } else if (mMsgHandler == null) {
            mMsgHandler = new MessageHandler(Looper.getMainLooper());
        }
    }

    public TmpSyncRequestHandler(INotifyHandler iNotifyHandler) {
        this.mNotifyHandler = iNotifyHandler;
    }

    public TmpSyncRequestHandler setRequest(TmpCommonRequest tmpCommonRequest) {
        this.mRequest = tmpCommonRequest;
        if (isMulThreadCallback()) {
            LogCat.m469d(TAG, "TmpSyncRequestHandler multhead callback");
        } else if (mMsgHandler == null) {
            mMsgHandler = new MessageHandler(Looper.getMainLooper());
        }
        return this;
    }

    protected boolean isMulThreadCallback() {
        TmpCommonRequest tmpCommonRequest = this.mRequest;
        return tmpCommonRequest != null && tmpCommonRequest.isMulThreadCallback();
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        ALog.m479d(TAG, "onLoad response :" + tmpCommonResponse);
        if (this.mRequestHandler == null) {
            ALog.m480e(TAG, "onLoad handler empty");
            return;
        }
        if (isMulThreadCallback()) {
            ALog.m479d(TAG, "onLoad mulcallback");
            this.mRequestHandler.onLoad(this.mRequest, tmpCommonResponse);
            return;
        }
        ALog.m479d(TAG, "onLoad mainthreadcallback");
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = new LoadMsg(this.mRequestHandler, this.mInterceptor, this.mRequest, tmpCommonResponse);
        mMsgHandler.sendMessage(obtain);
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IRequestHandler
    public void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
        ALog.m479d(TAG, "onError ");
        if (this.mRequestHandler == null) {
            LogCat.m471e(TAG, "onError handler empty");
            return;
        }
        if (isMulThreadCallback()) {
            LogCat.m469d(TAG, "onError mulcallback");
            this.mRequestHandler.onError(this.mRequest, errorInfo);
            return;
        }
        LogCat.m469d(TAG, "onError mainthreadcallback");
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.obj = new ErrorMsg(this.mRequestHandler, this.mInterceptor, this.mRequest, errorInfo);
        mMsgHandler.sendMessage(obtain);
    }

    @Override // com.aliyun.alink.linksdk.tmp.event.INotifyHandler
    public void onMessage(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        ALog.m479d(TAG, "onMessage ");
        if (this.mNotifyHandler == null) {
            LogCat.m471e(TAG, "onMessage handler empty");
            return;
        }
        if (isMulThreadCallback()) {
            LogCat.m469d(TAG, "onMessage mulcallback");
            this.mNotifyHandler.onMessage(this.mRequest, tmpCommonResponse);
            return;
        }
        LogCat.m469d(TAG, "onMessage mainthreadcallback");
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = new NotifyMsg(this.mNotifyHandler, this.mInterceptor, this.mRequest, tmpCommonResponse);
        mMsgHandler.sendMessage(obtain);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class LoadMsg {
        public HandlerInterceptor mInterceptor;
        public TmpCommonRequest mRequest;
        public IRequestHandler mRequestHandler;
        public TmpCommonResponse mResponse;

        public LoadMsg(IRequestHandler iRequestHandler, HandlerInterceptor handlerInterceptor, TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
            this.mRequestHandler = iRequestHandler;
            this.mRequest = tmpCommonRequest;
            this.mResponse = tmpCommonResponse;
            this.mInterceptor = handlerInterceptor;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ErrorMsg {
        public ErrorInfo mErrorInfo;
        public HandlerInterceptor mInterceptor;
        public TmpCommonRequest mRequest;
        public IRequestHandler mRequestHandler;

        public ErrorMsg(IRequestHandler iRequestHandler, HandlerInterceptor handlerInterceptor, TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo) {
            this.mRequestHandler = iRequestHandler;
            this.mRequest = tmpCommonRequest;
            this.mErrorInfo = errorInfo;
            this.mInterceptor = handlerInterceptor;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class NotifyMsg {
        public HandlerInterceptor mInterceptor;
        public INotifyHandler mNotifyHandler;
        public TmpCommonRequest mRequest;
        public TmpCommonResponse mResponse;

        public NotifyMsg(INotifyHandler iNotifyHandler, HandlerInterceptor handlerInterceptor, TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
            this.mNotifyHandler = iNotifyHandler;
            this.mRequest = tmpCommonRequest;
            this.mResponse = tmpCommonResponse;
            this.mInterceptor = handlerInterceptor;
        }
    }
}
