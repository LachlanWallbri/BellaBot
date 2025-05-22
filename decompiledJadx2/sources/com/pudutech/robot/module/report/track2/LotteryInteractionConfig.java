package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrackConstant.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/LotteryInteractionConfig;", "", "prize_level", "", "prize_description", "", "prize_rate", "", "(ILjava/lang/String;F)V", "getPrize_description", "()Ljava/lang/String;", "getPrize_level", "()I", "getPrize_rate", "()F", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class LotteryInteractionConfig {
    private final String prize_description;
    private final int prize_level;
    private final float prize_rate;

    public static /* synthetic */ LotteryInteractionConfig copy$default(LotteryInteractionConfig lotteryInteractionConfig, int i, String str, float f, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = lotteryInteractionConfig.prize_level;
        }
        if ((i2 & 2) != 0) {
            str = lotteryInteractionConfig.prize_description;
        }
        if ((i2 & 4) != 0) {
            f = lotteryInteractionConfig.prize_rate;
        }
        return lotteryInteractionConfig.copy(i, str, f);
    }

    /* renamed from: component1, reason: from getter */
    public final int getPrize_level() {
        return this.prize_level;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPrize_description() {
        return this.prize_description;
    }

    /* renamed from: component3, reason: from getter */
    public final float getPrize_rate() {
        return this.prize_rate;
    }

    public final LotteryInteractionConfig copy(int prize_level, String prize_description, float prize_rate) {
        Intrinsics.checkParameterIsNotNull(prize_description, "prize_description");
        return new LotteryInteractionConfig(prize_level, prize_description, prize_rate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LotteryInteractionConfig)) {
            return false;
        }
        LotteryInteractionConfig lotteryInteractionConfig = (LotteryInteractionConfig) other;
        return this.prize_level == lotteryInteractionConfig.prize_level && Intrinsics.areEqual(this.prize_description, lotteryInteractionConfig.prize_description) && Float.compare(this.prize_rate, lotteryInteractionConfig.prize_rate) == 0;
    }

    public int hashCode() {
        int i = this.prize_level * 31;
        String str = this.prize_description;
        return ((i + (str != null ? str.hashCode() : 0)) * 31) + Float.floatToIntBits(this.prize_rate);
    }

    public String toString() {
        return "LotteryInteractionConfig(prize_level=" + this.prize_level + ", prize_description=" + this.prize_description + ", prize_rate=" + this.prize_rate + ")";
    }

    public LotteryInteractionConfig(int i, String prize_description, float f) {
        Intrinsics.checkParameterIsNotNull(prize_description, "prize_description");
        this.prize_level = i;
        this.prize_description = prize_description;
        this.prize_rate = f;
    }

    public final int getPrize_level() {
        return this.prize_level;
    }

    public final String getPrize_description() {
        return this.prize_description;
    }

    public final float getPrize_rate() {
        return this.prize_rate;
    }
}
