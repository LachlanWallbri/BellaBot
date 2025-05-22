package com.pudu.demo.video;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoPreLoadModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudu/demo/video/VideoPreLoadModel;", "", "originalUrl", "", "(Ljava/lang/String;)V", "getOriginalUrl", "()Ljava/lang/String;", "setOriginalUrl", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class VideoPreLoadModel {
    private String originalUrl;

    public static /* synthetic */ VideoPreLoadModel copy$default(VideoPreLoadModel videoPreLoadModel, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = videoPreLoadModel.originalUrl;
        }
        return videoPreLoadModel.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getOriginalUrl() {
        return this.originalUrl;
    }

    public final VideoPreLoadModel copy(String originalUrl) {
        Intrinsics.checkParameterIsNotNull(originalUrl, "originalUrl");
        return new VideoPreLoadModel(originalUrl);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof VideoPreLoadModel) && Intrinsics.areEqual(this.originalUrl, ((VideoPreLoadModel) other).originalUrl);
        }
        return true;
    }

    public int hashCode() {
        String str = this.originalUrl;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "VideoPreLoadModel(originalUrl=" + this.originalUrl + ")";
    }

    public VideoPreLoadModel(String originalUrl) {
        Intrinsics.checkParameterIsNotNull(originalUrl, "originalUrl");
        this.originalUrl = originalUrl;
    }

    public final String getOriginalUrl() {
        return this.originalUrl;
    }

    public final void setOriginalUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.originalUrl = str;
    }
}
