package com.pudutech.calibrationalgorithm;

import com.pudutech.calibrationalgorithm.downwardrgbd.DownwardRGBD;
import kotlin.Metadata;

/* compiled from: CalibrationAlogrithm.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/calibrationalgorithm/CalibrationAlgorithm;", "", "()V", "downwardRGBD", "Lcom/pudutech/calibrationalgorithm/downwardrgbd/DownwardRGBD;", "getDownwardRGBD", "()Lcom/pudutech/calibrationalgorithm/downwardrgbd/DownwardRGBD;", "CalibrationAlgorithm_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CalibrationAlgorithm {
    public static final CalibrationAlgorithm INSTANCE = new CalibrationAlgorithm();
    private static final DownwardRGBD downwardRGBD = new DownwardRGBD();

    private CalibrationAlgorithm() {
    }

    public final DownwardRGBD getDownwardRGBD() {
        return downwardRGBD;
    }
}
