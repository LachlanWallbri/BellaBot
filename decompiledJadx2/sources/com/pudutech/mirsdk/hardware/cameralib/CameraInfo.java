package com.pudutech.mirsdk.hardware.cameralib;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* compiled from: CameraLib.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/cameralib/CameraInfo;", "", "name", "", "vidPidList", "", "bpp", "", "configFile", "cameraJobLoop", "Lkotlinx/coroutines/Job;", "(Ljava/lang/String;[Ljava/lang/String;ILjava/lang/String;Lkotlinx/coroutines/Job;)V", "getBpp", "()I", "setBpp", "(I)V", "getCameraJobLoop", "()Lkotlinx/coroutines/Job;", "setCameraJobLoop", "(Lkotlinx/coroutines/Job;)V", "getConfigFile", "()Ljava/lang/String;", "setConfigFile", "(Ljava/lang/String;)V", "getName", "setName", "getVidPidList", "()[Ljava/lang/String;", "setVidPidList", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "MarkerCamera_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class CameraInfo {
    private int bpp;
    private Job cameraJobLoop;
    private String configFile;
    private String name;
    private String[] vidPidList;

    public CameraInfo(String name, String[] vidPidList, int i, String configFile, Job job) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(vidPidList, "vidPidList");
        Intrinsics.checkParameterIsNotNull(configFile, "configFile");
        this.name = name;
        this.vidPidList = vidPidList;
        this.bpp = i;
        this.configFile = configFile;
        this.cameraJobLoop = job;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String[] getVidPidList() {
        return this.vidPidList;
    }

    public final void setVidPidList(String[] strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "<set-?>");
        this.vidPidList = strArr;
    }

    public final int getBpp() {
        return this.bpp;
    }

    public final void setBpp(int i) {
        this.bpp = i;
    }

    public final String getConfigFile() {
        return this.configFile;
    }

    public final void setConfigFile(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.configFile = str;
    }

    public final Job getCameraJobLoop() {
        return this.cameraJobLoop;
    }

    public final void setCameraJobLoop(Job job) {
        this.cameraJobLoop = job;
    }
}
