package com.liulishuo.okdownload.core.listener.assist;

import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.assist.Listener4Assist.Listener4Model;
import com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler;

/* loaded from: classes4.dex */
public class Listener4Assist<T extends Listener4Model> implements ListenerAssist {
    private AssistExtend assistExtend;
    Listener4Callback callback;
    private final ListenerModelHandler<T> modelHandler;

    /* loaded from: classes4.dex */
    public interface AssistExtend {
        boolean dispatchBlockEnd(DownloadTask downloadTask, int i, Listener4Model listener4Model);

        boolean dispatchFetchProgress(DownloadTask downloadTask, int i, long j, Listener4Model listener4Model);

        boolean dispatchInfoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z, Listener4Model listener4Model);

        boolean dispatchTaskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener4Model listener4Model);
    }

    /* loaded from: classes4.dex */
    public interface Listener4Callback {
        void blockEnd(DownloadTask downloadTask, int i, BlockInfo blockInfo);

        void infoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z, Listener4Model listener4Model);

        void progress(DownloadTask downloadTask, long j);

        void progressBlock(DownloadTask downloadTask, int i, long j);

        void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener4Model listener4Model);
    }

    Listener4Assist(ListenerModelHandler<T> listenerModelHandler) {
        this.modelHandler = listenerModelHandler;
    }

    public Listener4Assist(ListenerModelHandler.ModelCreator<T> modelCreator) {
        this.modelHandler = new ListenerModelHandler<>(modelCreator);
    }

    public void setCallback(Listener4Callback listener4Callback) {
        this.callback = listener4Callback;
    }

    public void setAssistExtend(AssistExtend assistExtend) {
        this.assistExtend = assistExtend;
    }

    public AssistExtend getAssistExtend() {
        return this.assistExtend;
    }

    public void infoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z) {
        Listener4Callback listener4Callback;
        T addAndGetModel = this.modelHandler.addAndGetModel(downloadTask, breakpointInfo);
        AssistExtend assistExtend = this.assistExtend;
        if ((assistExtend == null || !assistExtend.dispatchInfoReady(downloadTask, breakpointInfo, z, addAndGetModel)) && (listener4Callback = this.callback) != null) {
            listener4Callback.infoReady(downloadTask, breakpointInfo, z, addAndGetModel);
        }
    }

    public void fetchProgress(DownloadTask downloadTask, int i, long j) {
        Listener4Callback listener4Callback;
        T orRecoverModel = this.modelHandler.getOrRecoverModel(downloadTask, downloadTask.getInfo());
        if (orRecoverModel == null) {
            return;
        }
        long longValue = orRecoverModel.blockCurrentOffsetMap.get(i).longValue() + j;
        orRecoverModel.blockCurrentOffsetMap.put(i, Long.valueOf(longValue));
        orRecoverModel.currentOffset += j;
        AssistExtend assistExtend = this.assistExtend;
        if ((assistExtend == null || !assistExtend.dispatchFetchProgress(downloadTask, i, j, orRecoverModel)) && (listener4Callback = this.callback) != null) {
            listener4Callback.progressBlock(downloadTask, i, longValue);
            this.callback.progress(downloadTask, orRecoverModel.currentOffset);
        }
    }

    public void fetchEnd(DownloadTask downloadTask, int i) {
        Listener4Callback listener4Callback;
        T orRecoverModel = this.modelHandler.getOrRecoverModel(downloadTask, downloadTask.getInfo());
        if (orRecoverModel == null) {
            return;
        }
        AssistExtend assistExtend = this.assistExtend;
        if ((assistExtend == null || !assistExtend.dispatchBlockEnd(downloadTask, i, orRecoverModel)) && (listener4Callback = this.callback) != null) {
            listener4Callback.blockEnd(downloadTask, i, orRecoverModel.info.getBlock(i));
        }
    }

    public synchronized void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
        T removeOrCreate = this.modelHandler.removeOrCreate(downloadTask, downloadTask.getInfo());
        if (this.assistExtend == null || !this.assistExtend.dispatchTaskEnd(downloadTask, endCause, exc, removeOrCreate)) {
            if (this.callback != null) {
                this.callback.taskEnd(downloadTask, endCause, exc, removeOrCreate);
            }
        }
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public boolean isAlwaysRecoverAssistModel() {
        return this.modelHandler.isAlwaysRecoverAssistModel();
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModel(boolean z) {
        this.modelHandler.setAlwaysRecoverAssistModel(z);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModelIfNotSet(boolean z) {
        this.modelHandler.setAlwaysRecoverAssistModelIfNotSet(z);
    }

    /* loaded from: classes4.dex */
    public static class Listener4Model implements ListenerModelHandler.ListenerModel {
        SparseArray<Long> blockCurrentOffsetMap;
        long currentOffset;

        /* renamed from: id */
        private final int f3521id;
        BreakpointInfo info;

        public Listener4Model(int i) {
            this.f3521id = i;
        }

        SparseArray<Long> getBlockCurrentOffsetMap() {
            return this.blockCurrentOffsetMap;
        }

        public long getCurrentOffset() {
            return this.currentOffset;
        }

        public long getBlockCurrentOffset(int i) {
            return this.blockCurrentOffsetMap.get(i).longValue();
        }

        public SparseArray<Long> cloneBlockCurrentOffsetMap() {
            return this.blockCurrentOffsetMap.clone();
        }

        public BreakpointInfo getInfo() {
            return this.info;
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ListenerModel
        public int getId() {
            return this.f3521id;
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ListenerModel
        public void onInfoValid(BreakpointInfo breakpointInfo) {
            this.info = breakpointInfo;
            this.currentOffset = breakpointInfo.getTotalOffset();
            SparseArray<Long> sparseArray = new SparseArray<>();
            int blockCount = breakpointInfo.getBlockCount();
            for (int i = 0; i < blockCount; i++) {
                sparseArray.put(i, Long.valueOf(breakpointInfo.getBlock(i).getCurrentOffset()));
            }
            this.blockCurrentOffsetMap = sparseArray;
        }
    }
}
