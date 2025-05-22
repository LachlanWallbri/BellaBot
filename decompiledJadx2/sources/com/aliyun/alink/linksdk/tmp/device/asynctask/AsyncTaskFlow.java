package com.aliyun.alink.linksdk.tmp.device.asynctask;

import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AsyncTaskFlow<Request, Response> {
    protected static final String TAG = "[Tmp]AsyncTaskFlow";
    protected static Executor mThreadPoolExecutor;
    protected AsyncTask<Request, Response> mCurTask;
    protected List<AsyncTask> mTaskList = new LinkedList();

    public AsyncTaskFlow() {
        if (mThreadPoolExecutor == null) {
            mThreadPoolExecutor = android.os.AsyncTask.THREAD_POOL_EXECUTOR;
        }
    }

    public void onTaskSuccess(AsyncTask asyncTask, Request request, Response response) {
        next(asyncTask, request, response);
    }

    public void onTaskError(AsyncTask asyncTask, Request request, ErrorInfo errorInfo) {
        AsyncTask<Request, Response> asyncTask2 = this.mCurTask;
        if (asyncTask2 == null || asyncTask2 != asyncTask) {
            return;
        }
        asyncTask2.onFlowError(request, errorInfo);
    }

    public boolean action() {
        if (this.mTaskList.isEmpty()) {
            return false;
        }
        AsyncTask<Request, Response> asyncTask = this.mTaskList.get(0);
        this.mCurTask = asyncTask;
        asyncTask.onPreTasResult(null, null, null);
        try {
            mThreadPoolExecutor.execute(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTaskFlow.1
                @Override // java.lang.Runnable
                public void run() {
                    AsyncTaskFlow.this.mCurTask.action();
                }
            });
            return true;
        } catch (Exception e) {
            ALog.m480e(TAG, "action error:" + e.toString());
            return true;
        }
    }

    public void flowComplete(Request request, Response response, ErrorInfo errorInfo) {
        AsyncTask<Request, Response> asyncTask = this.mCurTask;
        if (asyncTask != null) {
            asyncTask.onFlowComplete(request, response, errorInfo);
        }
    }

    protected void next(AsyncTask asyncTask, Request request, Response response) {
        AsyncTask<Request, Response> findNextTask = findNextTask(asyncTask);
        if (findNextTask == null) {
            flowComplete(request, response, new ErrorInfo(300, "task flow error"));
            return;
        }
        AsyncTask<Request, Response> asyncTask2 = this.mCurTask;
        if (findNextTask == asyncTask2) {
            return;
        }
        this.mCurTask = findNextTask;
        findNextTask.onPreTasResult(asyncTask2, request, response);
        mThreadPoolExecutor.execute(new Runnable() { // from class: com.aliyun.alink.linksdk.tmp.device.asynctask.AsyncTaskFlow.2
            @Override // java.lang.Runnable
            public void run() {
                AsyncTaskFlow.this.mCurTask.action();
            }
        });
    }

    protected AsyncTask findNextTask(AsyncTask asyncTask) {
        AsyncTask<Request, Response> asyncTask2 = this.mCurTask;
        if (asyncTask != asyncTask2) {
            return asyncTask2;
        }
        int indexOf = this.mTaskList.indexOf(asyncTask2) + 1;
        if (indexOf >= this.mTaskList.size()) {
            return null;
        }
        return this.mTaskList.get(indexOf);
    }

    public AsyncTaskFlow appendTask(AsyncTask asyncTask) {
        asyncTask.setTaskFlow(this);
        this.mTaskList.add(asyncTask);
        return this;
    }
}
