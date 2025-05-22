package com.liulishuo.okdownload.core.listener.assist;

import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ListenerModel;

/* loaded from: classes4.dex */
public class ListenerModelHandler<T extends ListenerModel> implements ListenerAssist {
    private Boolean alwaysRecoverModel;
    private final ModelCreator<T> creator;
    final SparseArray<T> modelList = new SparseArray<>();
    volatile T singleTaskModel;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public interface ListenerModel {
        int getId();

        void onInfoValid(BreakpointInfo breakpointInfo);
    }

    /* loaded from: classes4.dex */
    public interface ModelCreator<T extends ListenerModel> {
        T create(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenerModelHandler(ModelCreator<T> modelCreator) {
        this.creator = modelCreator;
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public boolean isAlwaysRecoverAssistModel() {
        Boolean bool = this.alwaysRecoverModel;
        return bool != null && bool.booleanValue();
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModel(boolean z) {
        this.alwaysRecoverModel = Boolean.valueOf(z);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModelIfNotSet(boolean z) {
        if (this.alwaysRecoverModel == null) {
            this.alwaysRecoverModel = Boolean.valueOf(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public T addAndGetModel(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        T create = this.creator.create(downloadTask.getId());
        synchronized (this) {
            if (this.singleTaskModel == null) {
                this.singleTaskModel = create;
            } else {
                this.modelList.put(downloadTask.getId(), create);
            }
            if (breakpointInfo != null) {
                create.onInfoValid(breakpointInfo);
            }
        }
        return create;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public T getOrRecoverModel(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        T t;
        int id = downloadTask.getId();
        synchronized (this) {
            t = (this.singleTaskModel == null || this.singleTaskModel.getId() != id) ? null : this.singleTaskModel;
        }
        if (t == null) {
            t = this.modelList.get(id);
        }
        return (t == null && isAlwaysRecoverAssistModel()) ? addAndGetModel(downloadTask, breakpointInfo) : t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public T removeOrCreate(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        T t;
        int id = downloadTask.getId();
        synchronized (this) {
            if (this.singleTaskModel != null && this.singleTaskModel.getId() == id) {
                t = this.singleTaskModel;
                this.singleTaskModel = null;
            } else {
                t = this.modelList.get(id);
                this.modelList.remove(id);
            }
        }
        if (t == null) {
            t = this.creator.create(id);
            if (breakpointInfo != null) {
                t.onInfoValid(breakpointInfo);
            }
        }
        return t;
    }
}
