package com.pudutech.pd_network.storage.task;

import android.os.Build;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import com.pudutech.pd_network.IOssTaskController;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.PdNetConfig;
import com.pudutech.pd_network.log.NetWorkLog;
import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: DownloadTaskController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0017\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001c\u001a\u00020\u000fH\u0002J\b\u0010\u001d\u001a\u00020\u0012H\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001fH\u0016J\u0010\u0010!\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\"\u001a\u00020\u001fH\u0016J\b\u0010#\u001a\u00020\u001fH\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0003X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0016\u0010\u0019\u001a\n \u001b*\u0004\u0018\u00010\u001a0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/pd_network/storage/task/DownloadTaskController;", "Lcom/pudutech/pd_network/IOssTaskController;", "url", "", "downloadFile", "Ljava/io/File;", "(Ljava/lang/String;Ljava/io/File;)V", "MAX_RECONNECT_COUNT", "", "getMAX_RECONNECT_COUNT", "()I", "TAG", "callback", "Lcom/pudutech/pd_network/OssCallback;", "canResume", "", "crtConnectCount", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/pd_network/OssState;", "crtState", "setCrtState", "(Lcom/pudutech/pd_network/OssState;)V", "mCallback", "com/pudutech/pd_network/storage/task/DownloadTaskController$mCallback$1", "Lcom/pudutech/pd_network/storage/task/DownloadTaskController$mCallback$1;", "task", "Lcom/liulishuo/okdownload/DownloadTask;", "kotlin.jvm.PlatformType", "checkState", "currentState", "pause", "", "resume", "setCallback", "start", "stop", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DownloadTaskController implements IOssTaskController {
    private final int MAX_RECONNECT_COUNT;
    private final String TAG;
    private OssCallback callback;
    private boolean canResume;
    private int crtConnectCount;
    private OssState crtState;
    private final File downloadFile;
    private final DownloadTaskController$mCallback$1 mCallback;
    private final DownloadTask task;

    /* JADX WARN: Type inference failed for: r3v8, types: [com.pudutech.pd_network.storage.task.DownloadTaskController$mCallback$1] */
    public DownloadTaskController(String url, File downloadFile) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(downloadFile, "downloadFile");
        this.downloadFile = downloadFile;
        this.MAX_RECONNECT_COUNT = 10;
        this.TAG = "DownloadTaskController";
        DownloadTask.Builder passIfAlreadyCompleted = new DownloadTask.Builder(url, this.downloadFile.getParentFile()).setFilename(this.downloadFile.getName()).setMinIntervalMillisCallbackProcess(1000).setPassIfAlreadyCompleted(false);
        if (Build.VERSION.SDK_INT >= 29) {
            passIfAlreadyCompleted.setConnectionCount(1);
        }
        this.task = passIfAlreadyCompleted.build();
        this.crtState = OssState.WAITING;
        this.canResume = true;
        this.mCallback = new DownloadListener1() { // from class: com.pudutech.pd_network.storage.task.DownloadTaskController$mCallback$1
            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            public void taskStart(DownloadTask task, Listener1Assist.Listener1Model model) {
                String str;
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(model, "model");
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                str = DownloadTaskController.this.TAG;
                netWorkLog.mo3280i(str, "download.taskStart > task:" + task + " model:" + model + ' ');
                DownloadTaskController.this.setCrtState(OssState.IN_PROGRESS);
                DownloadTaskController.this.crtConnectCount = 0;
            }

            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            public void taskEnd(DownloadTask task, EndCause cause, Exception e, Listener1Assist.Listener1Model model) {
                String str;
                OssCallback ossCallback;
                int i;
                String str2;
                int i2;
                int i3;
                boolean z;
                File file;
                OssState ossState;
                OssCallback ossCallback2;
                File file2;
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(cause, "cause");
                Intrinsics.checkParameterIsNotNull(model, "model");
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                str = DownloadTaskController.this.TAG;
                netWorkLog.mo3280i(str, "download.taskEnd > cause:" + cause + " e:" + e + " model:" + model + " task:" + task + ' ');
                if (e == null && cause == EndCause.COMPLETED) {
                    DownloadTaskController.this.setCrtState(OssState.COMPLETED);
                    ossCallback2 = DownloadTaskController.this.callback;
                    if (ossCallback2 != null) {
                        file2 = DownloadTaskController.this.downloadFile;
                        String path = file2.getPath();
                        Intrinsics.checkExpressionValueIsNotNull(path, "downloadFile.path");
                        ossCallback2.onCompleted(path);
                        return;
                    }
                    return;
                }
                if (cause == EndCause.CANCELED) {
                    DownloadTaskController downloadTaskController = DownloadTaskController.this;
                    z = downloadTaskController.canResume;
                    if (!z) {
                        file = DownloadTaskController.this.downloadFile;
                        file.delete();
                        ossState = OssState.CANCELED;
                    } else {
                        ossState = OssState.PAUSED;
                    }
                    downloadTaskController.setCrtState(ossState);
                    return;
                }
                if (cause == EndCause.ERROR && (e instanceof SocketTimeoutException)) {
                    i = DownloadTaskController.this.crtConnectCount;
                    if (i < DownloadTaskController.this.getMAX_RECONNECT_COUNT()) {
                        NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                        str2 = DownloadTaskController.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("download.taskEnd.reConnect ");
                        i2 = DownloadTaskController.this.crtConnectCount;
                        sb.append(i2);
                        netWorkLog2.mo3280i(str2, sb.toString());
                        DownloadTaskController downloadTaskController2 = DownloadTaskController.this;
                        i3 = downloadTaskController2.crtConnectCount;
                        downloadTaskController2.crtConnectCount = i3 + 1;
                        DownloadTaskController.this.setCrtState(OssState.PAUSED);
                        DownloadTaskController.this.resume();
                        return;
                    }
                }
                DownloadTaskController.this.setCrtState(OssState.FAILED);
                ossCallback = DownloadTaskController.this.callback;
                if (ossCallback != null) {
                    if (e == null) {
                        e = new IOException("未知的错误异常: " + cause);
                    }
                    ossCallback.onError(e);
                }
            }

            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            public void retry(DownloadTask task, ResumeFailedCause cause) {
                String str;
                Intrinsics.checkParameterIsNotNull(task, "task");
                Intrinsics.checkParameterIsNotNull(cause, "cause");
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                str = DownloadTaskController.this.TAG;
                netWorkLog.mo3280i(str, "retry > cause:" + cause + " task:" + task + ' ');
            }

            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            public void connected(DownloadTask task, int blockCount, long currentOffset, long totalLength) {
                String str;
                Intrinsics.checkParameterIsNotNull(task, "task");
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                str = DownloadTaskController.this.TAG;
                netWorkLog.mo3280i(str, "connected > blockCount:" + blockCount + " currentOffset:" + currentOffset + " totalLength:" + totalLength + " task: " + task + ' ');
            }

            /* JADX WARN: Code restructure failed: missing block: B:5:0x000f, code lost:
            
                r3 = r2.this$0.callback;
             */
            @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void progress(DownloadTask task, long bytesCurrent, long bytesTotal) {
                OssCallback ossCallback;
                Intrinsics.checkParameterIsNotNull(task, "task");
                if (bytesCurrent > bytesTotal || bytesTotal == 0 || ossCallback == null) {
                    return;
                }
                ossCallback.onProgressChanged(bytesCurrent, bytesTotal);
            }
        };
        OssCallback ossCallback = this.callback;
        if (ossCallback != null) {
            ossCallback.onStateChanged(OssState.WAITING);
        }
    }

    public final int getMAX_RECONNECT_COUNT() {
        return this.MAX_RECONNECT_COUNT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCrtState(OssState ossState) {
        OssCallback ossCallback = this.callback;
        if (ossCallback != null) {
            ossCallback.onStateChanged(ossState);
        }
        this.crtState = ossState;
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    /* renamed from: currentState, reason: from getter */
    public OssState getCrtState() {
        return this.crtState;
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void start() {
        if (this.callback == null) {
            PdNetConfig.INSTANCE.throwOrLog("为避免丢失状态，请先设置回调 setCallback(callback: OssCallback) 后，在启动任务。");
        } else if (getCrtState() == OssState.WAITING) {
            this.task.enqueue(this.mCallback);
        } else {
            PdNetConfig.INSTANCE.throwOrLog("每个任务只允许start一次");
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void stop() {
        if (getCrtState() == OssState.WAITING) {
            PdNetConfig.INSTANCE.throwOrLog("task has not started");
        } else if (checkState()) {
            this.canResume = false;
            this.task.cancel();
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void resume() {
        if (checkState()) {
            if (getCrtState() != OssState.PAUSED) {
                PdNetConfig.INSTANCE.throwOrLog("cant resume : task is " + getCrtState());
                return;
            }
            this.task.enqueue(this.mCallback);
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void pause() {
        if (getCrtState() != OssState.IN_PROGRESS) {
            PdNetConfig.INSTANCE.throwOrLog("cant pause : task is " + getCrtState());
            return;
        }
        if (checkState()) {
            this.task.cancel();
        }
    }

    @Override // com.pudutech.pd_network.IOssTaskController
    public void setCallback(OssCallback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.callback = callback;
    }

    private final boolean checkState() {
        if (getCrtState() == OssState.CANCELED) {
            PdNetConfig.INSTANCE.throwOrLog("task is canceled");
            return false;
        }
        if (getCrtState() == OssState.COMPLETED) {
            PdNetConfig.INSTANCE.throwOrLog("task is completed");
            return false;
        }
        if (getCrtState() != OssState.FAILED) {
            return true;
        }
        PdNetConfig.INSTANCE.throwOrLog("task is failed");
        return false;
    }
}
