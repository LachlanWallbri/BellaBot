package com.pudutech.robot.peripherals.common;

import com.pudutech.robot.peripherals.config.LightBeltAnimation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LightBeltsPlayHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/common/PlayLightAnimation;", "", "mode", "Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;", "temp", "(Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;)V", "getMode", "()Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;", "setMode", "(Lcom/pudutech/robot/peripherals/config/LightBeltAnimation;)V", "getTemp", "setTemp", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class PlayLightAnimation {
    private LightBeltAnimation mode;
    private LightBeltAnimation temp;

    public PlayLightAnimation() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ PlayLightAnimation copy$default(PlayLightAnimation playLightAnimation, LightBeltAnimation lightBeltAnimation, LightBeltAnimation lightBeltAnimation2, int i, Object obj) {
        if ((i & 1) != 0) {
            lightBeltAnimation = playLightAnimation.mode;
        }
        if ((i & 2) != 0) {
            lightBeltAnimation2 = playLightAnimation.temp;
        }
        return playLightAnimation.copy(lightBeltAnimation, lightBeltAnimation2);
    }

    /* renamed from: component1, reason: from getter */
    public final LightBeltAnimation getMode() {
        return this.mode;
    }

    /* renamed from: component2, reason: from getter */
    public final LightBeltAnimation getTemp() {
        return this.temp;
    }

    public final PlayLightAnimation copy(LightBeltAnimation mode, LightBeltAnimation temp) {
        return new PlayLightAnimation(mode, temp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayLightAnimation)) {
            return false;
        }
        PlayLightAnimation playLightAnimation = (PlayLightAnimation) other;
        return Intrinsics.areEqual(this.mode, playLightAnimation.mode) && Intrinsics.areEqual(this.temp, playLightAnimation.temp);
    }

    public int hashCode() {
        LightBeltAnimation lightBeltAnimation = this.mode;
        int hashCode = (lightBeltAnimation != null ? lightBeltAnimation.hashCode() : 0) * 31;
        LightBeltAnimation lightBeltAnimation2 = this.temp;
        return hashCode + (lightBeltAnimation2 != null ? lightBeltAnimation2.hashCode() : 0);
    }

    public String toString() {
        return "PlayLightAnimation(mode=" + this.mode + ", temp=" + this.temp + ")";
    }

    public PlayLightAnimation(LightBeltAnimation lightBeltAnimation, LightBeltAnimation lightBeltAnimation2) {
        this.mode = lightBeltAnimation;
        this.temp = lightBeltAnimation2;
    }

    public /* synthetic */ PlayLightAnimation(LightBeltAnimation lightBeltAnimation, LightBeltAnimation lightBeltAnimation2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (LightBeltAnimation) null : lightBeltAnimation, (i & 2) != 0 ? (LightBeltAnimation) null : lightBeltAnimation2);
    }

    public final LightBeltAnimation getMode() {
        return this.mode;
    }

    public final void setMode(LightBeltAnimation lightBeltAnimation) {
        this.mode = lightBeltAnimation;
    }

    public final LightBeltAnimation getTemp() {
        return this.temp;
    }

    public final void setTemp(LightBeltAnimation lightBeltAnimation) {
        this.temp = lightBeltAnimation;
    }
}
