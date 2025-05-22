package com.pudutech.rgbdlib.config;

import kotlin.Metadata;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes6.dex
 */
/* compiled from: ExtrinsicsJson.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0016\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u0011\u0010\u000b\"\u0004\b\u0012\u0010\rR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u0013\u0010\u000b\"\u0004\b\u0014\u0010\rR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u0017\u0010\u000b\"\u0004\b\u0018\u0010\r¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/rgbdlib/config/ExtrinsicsJson;", "", "x", "", "y", CompressorStreamFactory.f8930Z, "yaw", "pitch", "roll", "(Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/Float;)V", "getPitch", "()Ljava/lang/Float;", "setPitch", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "getRoll", "setRoll", "getX", "setX", "getY", "setY", "getYaw", "setYaw", "getZ", "setZ", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ExtrinsicsJson {
    private Float pitch;
    private Float roll;
    private Float x;
    private Float y;
    private Float yaw;
    private Float z;

    public ExtrinsicsJson(Float f, Float f2, Float f3, Float f4, Float f5, Float f6) {
        this.x = f;
        this.y = f2;
        this.z = f3;
        this.yaw = f4;
        this.pitch = f5;
        this.roll = f6;
    }

    public final Float getPitch() {
        return this.pitch;
    }

    public final Float getRoll() {
        return this.roll;
    }

    public final Float getX() {
        return this.x;
    }

    public final Float getY() {
        return this.y;
    }

    public final Float getYaw() {
        return this.yaw;
    }

    public final Float getZ() {
        return this.z;
    }

    public final void setPitch(Float f) {
        this.pitch = f;
    }

    public final void setRoll(Float f) {
        this.roll = f;
    }

    public final void setX(Float f) {
        this.x = f;
    }

    public final void setY(Float f) {
        this.y = f;
    }

    public final void setYaw(Float f) {
        this.yaw = f;
    }

    public final void setZ(Float f) {
        this.z = f;
    }
}
