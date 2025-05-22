package com.aliyun.alink.linksdk.tmp.device.asynctask;

import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class AsyncTask<Request, Response> {
    protected static final String TAG = "[Tmp]AsyncTask";
    protected AsyncTaskFlow<Request, Response> mTaskFlow;

    public abstract boolean action();

    public abstract void onFlowComplete(Request request, Response response, ErrorInfo errorInfo);

    public abstract void onFlowError(Request request, ErrorInfo errorInfo);

    public void setTaskFlow(AsyncTaskFlow asyncTaskFlow) {
        this.mTaskFlow = asyncTaskFlow;
    }

    public void taskError(Request request, ErrorInfo errorInfo) {
        LogCat.m477w(TAG, "taskError");
        AsyncTaskFlow<Request, Response> asyncTaskFlow = this.mTaskFlow;
        if (asyncTaskFlow != null) {
            asyncTaskFlow.onTaskError(this, request, errorInfo);
        }
    }

    public void taskSuccess(Request request, Response response) {
        LogCat.m469d(TAG, "taskSuccess");
        AsyncTaskFlow<Request, Response> asyncTaskFlow = this.mTaskFlow;
        if (asyncTaskFlow != null) {
            asyncTaskFlow.onTaskSuccess(this, request, response);
        }
    }

    public boolean onPreTasResult(AsyncTask asyncTask, Request request, Response response) {
        LogCat.m469d(TAG, "onPreTasResult");
        return true;
    }
}
