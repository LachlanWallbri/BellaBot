package com.liulishuo.okdownload.core.listener.assist;

import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.assist.Listener4Assist;
import com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler;

/* loaded from: classes4.dex */
public class Listener4SpeedAssistExtend implements Listener4Assist.AssistExtend, ListenerModelHandler.ModelCreator<Listener4SpeedModel> {
    private Listener4SpeedCallback callback;

    /* loaded from: classes4.dex */
    public interface Listener4SpeedCallback {
        void blockEnd(DownloadTask downloadTask, int i, BlockInfo blockInfo, SpeedCalculator speedCalculator);

        void infoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z, Listener4SpeedModel listener4SpeedModel);

        void progress(DownloadTask downloadTask, long j, SpeedCalculator speedCalculator);

        void progressBlock(DownloadTask downloadTask, int i, long j, SpeedCalculator speedCalculator);

        void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, SpeedCalculator speedCalculator);
    }

    public void setCallback(Listener4SpeedCallback listener4SpeedCallback) {
        this.callback = listener4SpeedCallback;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.AssistExtend
    public boolean dispatchInfoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z, Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback == null) {
            return true;
        }
        listener4SpeedCallback.infoReady(downloadTask, breakpointInfo, z, (Listener4SpeedModel) listener4Model);
        return true;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.AssistExtend
    public boolean dispatchFetchProgress(DownloadTask downloadTask, int i, long j, Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedModel listener4SpeedModel = (Listener4SpeedModel) listener4Model;
        listener4SpeedModel.blockSpeeds.get(i).downloading(j);
        listener4SpeedModel.taskSpeed.downloading(j);
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback == null) {
            return true;
        }
        listener4SpeedCallback.progressBlock(downloadTask, i, listener4Model.blockCurrentOffsetMap.get(i).longValue(), listener4SpeedModel.getBlockSpeed(i));
        this.callback.progress(downloadTask, listener4Model.currentOffset, listener4SpeedModel.taskSpeed);
        return true;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.AssistExtend
    public boolean dispatchBlockEnd(DownloadTask downloadTask, int i, Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedModel listener4SpeedModel = (Listener4SpeedModel) listener4Model;
        listener4SpeedModel.blockSpeeds.get(i).endTask();
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback == null) {
            return true;
        }
        listener4SpeedCallback.blockEnd(downloadTask, i, listener4Model.info.getBlock(i), listener4SpeedModel.getBlockSpeed(i));
        return true;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.AssistExtend
    public boolean dispatchTaskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener4Assist.Listener4Model listener4Model) {
        SpeedCalculator speedCalculator;
        Listener4SpeedModel listener4SpeedModel = (Listener4SpeedModel) listener4Model;
        if (listener4SpeedModel.taskSpeed != null) {
            speedCalculator = listener4SpeedModel.taskSpeed;
            speedCalculator.endTask();
        } else {
            speedCalculator = new SpeedCalculator();
        }
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback == null) {
            return true;
        }
        listener4SpeedCallback.taskEnd(downloadTask, endCause, exc, speedCalculator);
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ModelCreator
    public Listener4SpeedModel create(int i) {
        return new Listener4SpeedModel(i);
    }

    /* loaded from: classes4.dex */
    public static class Listener4SpeedModel extends Listener4Assist.Listener4Model {
        SparseArray<SpeedCalculator> blockSpeeds;
        SpeedCalculator taskSpeed;

        public SpeedCalculator getTaskSpeed() {
            return this.taskSpeed;
        }

        public SpeedCalculator getBlockSpeed(int i) {
            return this.blockSpeeds.get(i);
        }

        public Listener4SpeedModel(int i) {
            super(i);
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4Assist.Listener4Model, com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ListenerModel
        public void onInfoValid(BreakpointInfo breakpointInfo) {
            super.onInfoValid(breakpointInfo);
            this.taskSpeed = new SpeedCalculator();
            this.blockSpeeds = new SparseArray<>();
            int blockCount = breakpointInfo.getBlockCount();
            for (int i = 0; i < blockCount; i++) {
                this.blockSpeeds.put(i, new SpeedCalculator());
            }
        }
    }
}
