package com.pudutech.calibrationalgorithm.downwardrgbd.algo;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;

/* compiled from: RunLineSegment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\u0007\u001a\u00020\bH\u0086 J1\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\bH\u0086 R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/RunLineSegment;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "initParam", "", "process", "", "fd", "", "rows", "cols", "memSize", "save", "CalibrationAlgorithm_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RunLineSegment {
    private final String TAG = "DownwardRGBDRunLineSegment";

    public final native boolean initParam();

    public final native float[] process(int fd, int rows, int cols, int memSize, boolean save);

    public RunLineSegment() {
        Pdlog.m3273d(this.TAG, "load line_segment so");
        System.loadLibrary("line_segment");
    }

    public final String getTAG() {
        return this.TAG;
    }
}
