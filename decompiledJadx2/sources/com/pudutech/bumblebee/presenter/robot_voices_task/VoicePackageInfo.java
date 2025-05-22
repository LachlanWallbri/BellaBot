package com.pudutech.bumblebee.presenter.robot_voices_task;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoicePackageInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010(\u001a\u00020\u0017H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u0013R\u001a\u0010\u001f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0011\"\u0004\b!\u0010\u0013R\u001a\u0010\"\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\f\"\u0004\b$\u0010\u000eR\u001a\u0010%\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\b¨\u0006)"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "", "()V", "downloadProgress", "", "getDownloadProgress", "()I", "setDownloadProgress", "(I)V", "id", "", "getId", "()J", "setId", "(J)V", "isDownloading", "", "()Z", "setDownloading", "(Z)V", "isExist", "setExist", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "newVersionAvailable", "getNewVersionAvailable", "setNewVersionAvailable", "selected", "getSelected", "setSelected", "version_code", "getVersion_code", "setVersion_code", "viewType", "getViewType", "setViewType", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoicePackageInfo {
    private int downloadProgress;
    private boolean isDownloading;
    private boolean isExist;
    private boolean newVersionAvailable;
    private boolean selected;
    private long version_code;
    private int viewType;
    private String name = "";
    private long id = -1;

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final long getId() {
        return this.id;
    }

    public final void setId(long j) {
        this.id = j;
    }

    public final long getVersion_code() {
        return this.version_code;
    }

    public final void setVersion_code(long j) {
        this.version_code = j;
    }

    /* renamed from: isExist, reason: from getter */
    public final boolean getIsExist() {
        return this.isExist;
    }

    public final void setExist(boolean z) {
        this.isExist = z;
    }

    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
    }

    public final boolean getNewVersionAvailable() {
        return this.newVersionAvailable;
    }

    public final void setNewVersionAvailable(boolean z) {
        this.newVersionAvailable = z;
    }

    /* renamed from: isDownloading, reason: from getter */
    public final boolean getIsDownloading() {
        return this.isDownloading;
    }

    public final void setDownloading(boolean z) {
        this.isDownloading = z;
    }

    public final int getDownloadProgress() {
        return this.downloadProgress;
    }

    public final void setDownloadProgress(int i) {
        this.downloadProgress = i;
    }

    public final int getViewType() {
        return this.viewType;
    }

    public final void setViewType(int i) {
        this.viewType = i;
    }

    public String toString() {
        return "VoicePackageInfo(name='" + this.name + "', id=" + this.id + ", version_code=" + this.version_code + ", isExist=" + this.isExist + ", selected=" + this.selected + ", newVersionAvailable=" + this.newVersionAvailable + ", isDownloading=" + this.isDownloading + ", downloadProgress=" + this.downloadProgress + ", viewType=" + this.viewType + ')';
    }
}
