package com.pudutech.bumblebee.robot_ui.util.video;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener1;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import com.pudu.demo.video.CacheUtils;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseApplication;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: VideoPreloadManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006J\b\u0010\u000e\u001a\u00020\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/video/VideoPreloadManager;", "Lcom/pudutech/bumblebee/robot_ui/util/video/PreloadManager;", "()V", "TAG", "", "mDownTaskList", "", "Lcom/liulishuo/okdownload/DownloadTask;", "mVideoList", "Lcom/pudutech/bumblebee/robot_ui/util/video/VideoPreBean;", "cancel", "", "initVideoUlr", "urls", "load", "DefDownloadListener", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VideoPreloadManager implements PreloadManager {
    private final String TAG = "VideoPreloadManager";
    private List<DownloadTask> mDownTaskList;
    private List<VideoPreBean> mVideoList;

    public final void initVideoUlr(List<String> urls) {
        Intrinsics.checkParameterIsNotNull(urls, "urls");
        this.mVideoList = new ArrayList();
        for (String str : urls) {
            File proxyCacheFile = CacheUtils.INSTANCE.getProxyCacheFile(BaseApplication.INSTANCE.getInstance(), str);
            if (proxyCacheFile.exists()) {
                Pdlog.m3273d(this.TAG, "init() already exists curUrl =" + str);
            } else {
                VideoPreBean videoPreBean = new VideoPreBean(str, proxyCacheFile, false);
                List<VideoPreBean> list = this.mVideoList;
                if (list != null) {
                    list.add(videoPreBean);
                }
            }
        }
        Pdlog.m3273d(this.TAG, "init() mVideoList =" + this.mVideoList);
    }

    @Override // com.pudutech.bumblebee.robot_ui.util.video.PreloadManager
    public void load() {
        DownloadTask[] downloadTaskArr;
        List<VideoPreBean> list = this.mVideoList;
        if (list == null) {
            Pdlog.m3273d(this.TAG, "load() mVideoList is null");
            return;
        }
        if (list == null) {
            Intrinsics.throwNpe();
        }
        if (list.size() <= 0) {
            Pdlog.m3273d(this.TAG, "load() mVideoList.size <=0 ");
            return;
        }
        this.mDownTaskList = new ArrayList();
        List<VideoPreBean> list2 = this.mVideoList;
        if (list2 != null) {
            for (VideoPreBean videoPreBean : list2) {
                CacheUtils.INSTANCE.createNewFile(videoPreBean.getFile());
                DownloadTask task = new DownloadTask.Builder(videoPreBean.getUrl(), videoPreBean.getFile().getParentFile()).setFilename(videoPreBean.getFile().getName()).setMinIntervalMillisCallbackProcess(30).setPassIfAlreadyCompleted(false).build();
                List<DownloadTask> list3 = this.mDownTaskList;
                if (list3 != null) {
                    Intrinsics.checkExpressionValueIsNotNull(task, "task");
                    list3.add(task);
                }
                Pdlog.m3273d(this.TAG, "load() DownloadTask add =" + task);
            }
        }
        List<DownloadTask> list4 = this.mDownTaskList;
        if (list4 != null) {
            Object[] array = list4.toArray(new DownloadTask[0]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            downloadTaskArr = (DownloadTask[]) array;
        } else {
            downloadTaskArr = null;
        }
        DownloadTask.enqueue(downloadTaskArr, new DefDownloadListener());
    }

    @Override // com.pudutech.bumblebee.robot_ui.util.video.PreloadManager
    public void cancel() {
        List<DownloadTask> list = this.mDownTaskList;
        if (list != null) {
            Object[] array = list.toArray(new DownloadTask[0]);
            if (array != null) {
                DownloadTask.cancel((DownloadTask[]) array);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        List<VideoPreBean> list2 = this.mVideoList;
        if (list2 != null) {
            for (VideoPreBean videoPreBean : list2) {
                if (!videoPreBean.isLoadCompleted()) {
                    CacheUtils.INSTANCE.deleteOrThrow(videoPreBean.getFile());
                }
            }
        }
        Pdlog.m3273d(this.TAG, "cancel() mVideoList =" + this.mVideoList);
    }

    /* compiled from: VideoPreloadManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J \u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J0\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/video/VideoPreloadManager$DefDownloadListener;", "Lcom/liulishuo/okdownload/core/listener/DownloadListener1;", "(Lcom/pudutech/bumblebee/robot_ui/util/video/VideoPreloadManager;)V", "connected", "", "task", "Lcom/liulishuo/okdownload/DownloadTask;", "blockCount", "", "currentOffset", "", "totalLength", "progress", "retry", "cause", "Lcom/liulishuo/okdownload/core/cause/ResumeFailedCause;", "taskEnd", "Lcom/liulishuo/okdownload/core/cause/EndCause;", "realCause", "Ljava/lang/Exception;", "Lkotlin/Exception;", "model", "Lcom/liulishuo/okdownload/core/listener/assist/Listener1Assist$Listener1Model;", "taskStart", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    private final class DefDownloadListener extends DownloadListener1 {
        public DefDownloadListener() {
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void taskStart(DownloadTask task, Listener1Assist.Listener1Model model) {
            Intrinsics.checkParameterIsNotNull(task, "task");
            Intrinsics.checkParameterIsNotNull(model, "model");
            Pdlog.m3275i(VideoPreloadManager.this.TAG, "download.taskStart > task:" + task + " model:" + model + ' ');
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void taskEnd(DownloadTask task, EndCause cause, Exception realCause, Listener1Assist.Listener1Model model) {
            String str;
            Intrinsics.checkParameterIsNotNull(task, "task");
            Intrinsics.checkParameterIsNotNull(cause, "cause");
            Intrinsics.checkParameterIsNotNull(model, "model");
            Pdlog.m3275i(VideoPreloadManager.this.TAG, "download.taskEnd > task:" + task + " cause:" + cause + " e:" + realCause + " model:" + model + ' ');
            if (cause.ordinal() == EndCause.COMPLETED.ordinal() || realCause == null) {
                File file = task.getFile();
                if (file == null || (str = file.getPath()) == null) {
                    str = "";
                }
                Pdlog.m3273d(VideoPreloadManager.this.TAG, "taskEnd() path =" + str);
                List<VideoPreBean> list = VideoPreloadManager.this.mVideoList;
                if (list != null) {
                    for (VideoPreBean videoPreBean : list) {
                        String path = videoPreBean.getFile().getPath();
                        Intrinsics.checkExpressionValueIsNotNull(path, "it.file.path");
                        if (StringsKt.contains$default((CharSequence) path, (CharSequence) str, false, 2, (Object) null)) {
                            videoPreBean.setLoadCompleted(true);
                        }
                    }
                }
            }
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void retry(DownloadTask task, ResumeFailedCause cause) {
            Intrinsics.checkParameterIsNotNull(task, "task");
            Intrinsics.checkParameterIsNotNull(cause, "cause");
            Pdlog.m3275i(VideoPreloadManager.this.TAG, "retry > task:" + task + " cause:" + cause + ' ');
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void connected(DownloadTask task, int blockCount, long currentOffset, long totalLength) {
            Intrinsics.checkParameterIsNotNull(task, "task");
            Pdlog.m3275i(VideoPreloadManager.this.TAG, "connected > task:" + task + " blockCount:" + blockCount + " currentOffset:" + currentOffset + " totalLength:" + totalLength);
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
        public void progress(DownloadTask task, long currentOffset, long totalLength) {
            Intrinsics.checkParameterIsNotNull(task, "task");
            int i = (int) (100 * (((float) currentOffset) / ((float) totalLength)));
            if (i == 0 || i == 20 || i == 50 || i == 100) {
                Pdlog.m3275i(VideoPreloadManager.this.TAG, "progress =" + i);
            }
        }
    }
}
