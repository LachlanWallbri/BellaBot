package com.pudutech.robot.module.voice.pkg;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoicePackageInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R$\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u0017@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0012\"\u0004\b\u001f\u0010\u0014R$\u0010 \u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0012\"\u0004\b\"\u0010\u0014R$\u0010#\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\r\"\u0004\b%\u0010\u000f¨\u0006&"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/pkg/VoicePackageInfo;", "", "()V", "<set-?>", "", "downloadProgress", "getDownloadProgress", "()I", "setDownloadProgress$module_robot_voice_release", "(I)V", "", "id", "getId", "()J", "setId$module_robot_voice_release", "(J)V", "", "isDownloading", "()Z", "setDownloading$module_robot_voice_release", "(Z)V", "isExist", "setExist$module_robot_voice_release", "", "name", "getName", "()Ljava/lang/String;", "setName$module_robot_voice_release", "(Ljava/lang/String;)V", "newVersionAvailable", "getNewVersionAvailable", "setNewVersionAvailable$module_robot_voice_release", "selected", "getSelected", "setSelected$module_robot_voice_release", "version_code", "getVersion_code", "setVersion_code$module_robot_voice_release", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoicePackageInfo {
    private int downloadProgress;
    private boolean isDownloading;
    private boolean isExist;
    private boolean newVersionAvailable;
    private boolean selected;
    private long version_code;
    private String name = "";
    private long id = -1;

    public final String getName() {
        return this.name;
    }

    public final void setName$module_robot_voice_release(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final long getId() {
        return this.id;
    }

    public final void setId$module_robot_voice_release(long j) {
        this.id = j;
    }

    public final long getVersion_code() {
        return this.version_code;
    }

    public final void setVersion_code$module_robot_voice_release(long j) {
        this.version_code = j;
    }

    /* renamed from: isExist, reason: from getter */
    public final boolean getIsExist() {
        return this.isExist;
    }

    public final void setExist$module_robot_voice_release(boolean z) {
        this.isExist = z;
    }

    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected$module_robot_voice_release(boolean z) {
        this.selected = z;
    }

    public final boolean getNewVersionAvailable() {
        return this.newVersionAvailable;
    }

    public final void setNewVersionAvailable$module_robot_voice_release(boolean z) {
        this.newVersionAvailable = z;
    }

    /* renamed from: isDownloading, reason: from getter */
    public final boolean getIsDownloading() {
        return this.isDownloading;
    }

    public final void setDownloading$module_robot_voice_release(boolean z) {
        this.isDownloading = z;
    }

    public final int getDownloadProgress() {
        return this.downloadProgress;
    }

    public final void setDownloadProgress$module_robot_voice_release(int i) {
        this.downloadProgress = i;
    }
}
