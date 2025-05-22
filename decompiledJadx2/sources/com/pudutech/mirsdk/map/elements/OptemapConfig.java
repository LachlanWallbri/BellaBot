package com.pudutech.mirsdk.map.elements;

import com.iflytek.aiui.constant.InternalConstant;
import kotlin.Metadata;

/* compiled from: OptemapConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0013\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0018\u0010\u0006\"\u0004\b\u0019\u0010\bR\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\bR\u001e\u0010#\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b$\u0010\u0006\"\u0004\b%\u0010\bR\u001c\u0010&\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\r\"\u0004\b(\u0010\u000fR\u001e\u0010)\u001a\u0004\u0018\u00010*X\u0086\u000e¢\u0006\u0010\n\u0002\u0010/\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u00060"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/elements/OptemapConfig;", "", "()V", "free_thresh", "", "getFree_thresh", "()Ljava/lang/Double;", "setFree_thresh", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", InternalConstant.DTYPE_IMAGE, "", "getImage", "()Ljava/lang/String;", "setImage", "(Ljava/lang/String;)V", "negate", "", "getNegate", "()Ljava/lang/Integer;", "setNegate", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "occupied_thresh", "getOccupied_thresh", "setOccupied_thresh", "origin", "", "getOrigin", "()[D", "setOrigin", "([D)V", "resolution", "getResolution", "setResolution", "score", "getScore", "setScore", "traj_md5", "getTraj_md5", "setTraj_md5", "use", "", "getUse", "()Ljava/lang/Boolean;", "setUse", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class OptemapConfig {
    private Double free_thresh;
    private String image;
    private Integer negate;
    private Double occupied_thresh;
    private double[] origin;
    private Double resolution;
    private Double score;
    private String traj_md5;
    private Boolean use;

    public final String getImage() {
        return this.image;
    }

    public final void setImage(String str) {
        this.image = str;
    }

    public final Double getResolution() {
        return this.resolution;
    }

    public final void setResolution(Double d) {
        this.resolution = d;
    }

    public final double[] getOrigin() {
        return this.origin;
    }

    public final void setOrigin(double[] dArr) {
        this.origin = dArr;
    }

    public final Integer getNegate() {
        return this.negate;
    }

    public final void setNegate(Integer num) {
        this.negate = num;
    }

    public final Double getOccupied_thresh() {
        return this.occupied_thresh;
    }

    public final void setOccupied_thresh(Double d) {
        this.occupied_thresh = d;
    }

    public final Double getFree_thresh() {
        return this.free_thresh;
    }

    public final void setFree_thresh(Double d) {
        this.free_thresh = d;
    }

    public final Double getScore() {
        return this.score;
    }

    public final void setScore(Double d) {
        this.score = d;
    }

    public final Boolean getUse() {
        return this.use;
    }

    public final void setUse(Boolean bool) {
        this.use = bool;
    }

    public final String getTraj_md5() {
        return this.traj_md5;
    }

    public final void setTraj_md5(String str) {
        this.traj_md5 = str;
    }
}
