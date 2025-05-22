package com.pudutech.calibrationalgorithm.downwardrgbd;

import com.pudutech.calibrationalgorithm.downwardrgbd.algo.Calibration;
import com.pudutech.calibrationalgorithm.downwardrgbd.algo.RunCapeSegment;
import com.pudutech.calibrationalgorithm.downwardrgbd.algo.RunLineSegment;
import com.pudutech.calibrationalgorithm.downwardrgbd.algo.RunRoadScout;
import kotlin.Metadata;

/* compiled from: DownwardRGBD.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/calibrationalgorithm/downwardrgbd/DownwardRGBD;", "", "()V", "calibrationTool", "Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/Calibration;", "getCalibrationTool", "()Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/Calibration;", "runCapeSegment", "Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/RunCapeSegment;", "getRunCapeSegment", "()Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/RunCapeSegment;", "runLineSegment", "Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/RunLineSegment;", "getRunLineSegment", "()Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/RunLineSegment;", "runRoadScout", "Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/RunRoadScout;", "getRunRoadScout", "()Lcom/pudutech/calibrationalgorithm/downwardrgbd/algo/RunRoadScout;", "CalibrationAlgorithm_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DownwardRGBD {
    private final Calibration calibrationTool = new Calibration();
    private final RunCapeSegment runCapeSegment = new RunCapeSegment();
    private final RunLineSegment runLineSegment = new RunLineSegment();
    private final RunRoadScout runRoadScout = new RunRoadScout();

    public final Calibration getCalibrationTool() {
        return this.calibrationTool;
    }

    public final RunCapeSegment getRunCapeSegment() {
        return this.runCapeSegment;
    }

    public final RunLineSegment getRunLineSegment() {
        return this.runLineSegment;
    }

    public final RunRoadScout getRunRoadScout() {
        return this.runRoadScout;
    }
}
