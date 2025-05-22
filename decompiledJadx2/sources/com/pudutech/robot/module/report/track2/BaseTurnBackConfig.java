package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: turn_back.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig;", "", "()V", "BellaBaseTurnBackConfig", "PeanutBaseTurnBackConfig", "PhoenixBaseTurnBackConfig", "Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig$BellaBaseTurnBackConfig;", "Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig$PeanutBaseTurnBackConfig;", "Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig$PhoenixBaseTurnBackConfig;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseTurnBackConfig {
    private BaseTurnBackConfig() {
    }

    public /* synthetic */ BaseTurnBackConfig(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: turn_back.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig$BellaBaseTurnBackConfig;", "Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig;", "music", "", "animation", "speed", "", "page_key", "", "(ZZFLjava/lang/String;)V", "getAnimation", "()Z", "getMusic", "getPage_key", "()Ljava/lang/String;", "getSpeed", "()F", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class BellaBaseTurnBackConfig extends BaseTurnBackConfig {
        private final boolean animation;
        private final boolean music;
        private final String page_key;
        private final float speed;

        public static /* synthetic */ BellaBaseTurnBackConfig copy$default(BellaBaseTurnBackConfig bellaBaseTurnBackConfig, boolean z, boolean z2, float f, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = bellaBaseTurnBackConfig.music;
            }
            if ((i & 2) != 0) {
                z2 = bellaBaseTurnBackConfig.animation;
            }
            if ((i & 4) != 0) {
                f = bellaBaseTurnBackConfig.speed;
            }
            if ((i & 8) != 0) {
                str = bellaBaseTurnBackConfig.page_key;
            }
            return bellaBaseTurnBackConfig.copy(z, z2, f, str);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getMusic() {
            return this.music;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getAnimation() {
            return this.animation;
        }

        /* renamed from: component3, reason: from getter */
        public final float getSpeed() {
            return this.speed;
        }

        /* renamed from: component4, reason: from getter */
        public final String getPage_key() {
            return this.page_key;
        }

        public final BellaBaseTurnBackConfig copy(boolean music, boolean animation, float speed, String page_key) {
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            return new BellaBaseTurnBackConfig(music, animation, speed, page_key);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BellaBaseTurnBackConfig)) {
                return false;
            }
            BellaBaseTurnBackConfig bellaBaseTurnBackConfig = (BellaBaseTurnBackConfig) other;
            return this.music == bellaBaseTurnBackConfig.music && this.animation == bellaBaseTurnBackConfig.animation && Float.compare(this.speed, bellaBaseTurnBackConfig.speed) == 0 && Intrinsics.areEqual(this.page_key, bellaBaseTurnBackConfig.page_key);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        public int hashCode() {
            boolean z = this.music;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            boolean z2 = this.animation;
            int floatToIntBits = (((i + (z2 ? 1 : z2 ? 1 : 0)) * 31) + Float.floatToIntBits(this.speed)) * 31;
            String str = this.page_key;
            return floatToIntBits + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "BellaBaseTurnBackConfig(music=" + this.music + ", animation=" + this.animation + ", speed=" + this.speed + ", page_key=" + this.page_key + ")";
        }

        public final boolean getMusic() {
            return this.music;
        }

        public final boolean getAnimation() {
            return this.animation;
        }

        public final float getSpeed() {
            return this.speed;
        }

        public final String getPage_key() {
            return this.page_key;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BellaBaseTurnBackConfig(boolean z, boolean z2, float f, String page_key) {
            super(null);
            Intrinsics.checkParameterIsNotNull(page_key, "page_key");
            this.music = z;
            this.animation = z2;
            this.speed = f;
            this.page_key = page_key;
        }
    }

    /* compiled from: turn_back.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig$PeanutBaseTurnBackConfig;", "Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig;", "music", "", "speed", "", "(ZF)V", "getMusic", "()Z", "getSpeed", "()F", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PeanutBaseTurnBackConfig extends BaseTurnBackConfig {
        private final boolean music;
        private final float speed;

        public static /* synthetic */ PeanutBaseTurnBackConfig copy$default(PeanutBaseTurnBackConfig peanutBaseTurnBackConfig, boolean z, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                z = peanutBaseTurnBackConfig.music;
            }
            if ((i & 2) != 0) {
                f = peanutBaseTurnBackConfig.speed;
            }
            return peanutBaseTurnBackConfig.copy(z, f);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getMusic() {
            return this.music;
        }

        /* renamed from: component2, reason: from getter */
        public final float getSpeed() {
            return this.speed;
        }

        public final PeanutBaseTurnBackConfig copy(boolean music, float speed) {
            return new PeanutBaseTurnBackConfig(music, speed);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PeanutBaseTurnBackConfig)) {
                return false;
            }
            PeanutBaseTurnBackConfig peanutBaseTurnBackConfig = (PeanutBaseTurnBackConfig) other;
            return this.music == peanutBaseTurnBackConfig.music && Float.compare(this.speed, peanutBaseTurnBackConfig.speed) == 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.music;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (r0 * 31) + Float.floatToIntBits(this.speed);
        }

        public String toString() {
            return "PeanutBaseTurnBackConfig(music=" + this.music + ", speed=" + this.speed + ")";
        }

        public final boolean getMusic() {
            return this.music;
        }

        public final float getSpeed() {
            return this.speed;
        }

        public PeanutBaseTurnBackConfig(boolean z, float f) {
            super(null);
            this.music = z;
            this.speed = f;
        }
    }

    /* compiled from: turn_back.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig$PhoenixBaseTurnBackConfig;", "Lcom/pudutech/robot/module/report/track2/BaseTurnBackConfig;", "speed", "", "(F)V", "getSpeed", "()F", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PhoenixBaseTurnBackConfig extends BaseTurnBackConfig {
        private final float speed;

        public static /* synthetic */ PhoenixBaseTurnBackConfig copy$default(PhoenixBaseTurnBackConfig phoenixBaseTurnBackConfig, float f, int i, Object obj) {
            if ((i & 1) != 0) {
                f = phoenixBaseTurnBackConfig.speed;
            }
            return phoenixBaseTurnBackConfig.copy(f);
        }

        /* renamed from: component1, reason: from getter */
        public final float getSpeed() {
            return this.speed;
        }

        public final PhoenixBaseTurnBackConfig copy(float speed) {
            return new PhoenixBaseTurnBackConfig(speed);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof PhoenixBaseTurnBackConfig) && Float.compare(this.speed, ((PhoenixBaseTurnBackConfig) other).speed) == 0;
            }
            return true;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.speed);
        }

        public String toString() {
            return "PhoenixBaseTurnBackConfig(speed=" + this.speed + ")";
        }

        public final float getSpeed() {
            return this.speed;
        }

        public PhoenixBaseTurnBackConfig(float f) {
            super(null);
            this.speed = f;
        }
    }
}
