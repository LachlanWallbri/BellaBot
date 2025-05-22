package com.pudutech.bumblebee.robot_ui.util.video;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoPreBean.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/video/VideoPreBean;", "", "url", "", "file", "Ljava/io/File;", "isLoadCompleted", "", "(Ljava/lang/String;Ljava/io/File;Z)V", "getFile", "()Ljava/io/File;", "setFile", "(Ljava/io/File;)V", "()Z", "setLoadCompleted", "(Z)V", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class VideoPreBean {
    private File file;
    private boolean isLoadCompleted;
    private String url;

    public static /* synthetic */ VideoPreBean copy$default(VideoPreBean videoPreBean, String str, File file, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = videoPreBean.url;
        }
        if ((i & 2) != 0) {
            file = videoPreBean.file;
        }
        if ((i & 4) != 0) {
            z = videoPreBean.isLoadCompleted;
        }
        return videoPreBean.copy(str, file, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component2, reason: from getter */
    public final File getFile() {
        return this.file;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsLoadCompleted() {
        return this.isLoadCompleted;
    }

    public final VideoPreBean copy(String url, File file, boolean isLoadCompleted) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(file, "file");
        return new VideoPreBean(url, file, isLoadCompleted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VideoPreBean)) {
            return false;
        }
        VideoPreBean videoPreBean = (VideoPreBean) other;
        return Intrinsics.areEqual(this.url, videoPreBean.url) && Intrinsics.areEqual(this.file, videoPreBean.file) && this.isLoadCompleted == videoPreBean.isLoadCompleted;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.url;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        File file = this.file;
        int hashCode2 = (hashCode + (file != null ? file.hashCode() : 0)) * 31;
        boolean z = this.isLoadCompleted;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "VideoPreBean(url=" + this.url + ", file=" + this.file + ", isLoadCompleted=" + this.isLoadCompleted + ")";
    }

    public VideoPreBean(String url, File file, boolean z) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(file, "file");
        this.url = url;
        this.file = file;
        this.isLoadCompleted = z;
    }

    public final File getFile() {
        return this.file;
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean isLoadCompleted() {
        return this.isLoadCompleted;
    }

    public final void setFile(File file) {
        Intrinsics.checkParameterIsNotNull(file, "<set-?>");
        this.file = file;
    }

    public final void setLoadCompleted(boolean z) {
        this.isLoadCompleted = z;
    }

    public final void setUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.url = str;
    }
}
