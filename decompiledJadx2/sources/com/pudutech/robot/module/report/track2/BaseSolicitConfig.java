package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: robot_solicit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseSolicitConfig;", "", "()V", "PeanutSolicitConfig", "Lcom/pudutech/robot/module/report/track2/BaseSolicitConfig$PeanutSolicitConfig;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseSolicitConfig {

    /* compiled from: robot_solicit.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BaseSolicitConfig$PeanutSolicitConfig;", "Lcom/pudutech/robot/module/report/track2/BaseSolicitConfig;", "play_solicit_voice", "", "show_face_icon", "(ZZ)V", "getPlay_solicit_voice", "()Z", "getShow_face_icon", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class PeanutSolicitConfig extends BaseSolicitConfig {
        private final boolean play_solicit_voice;
        private final boolean show_face_icon;

        public static /* synthetic */ PeanutSolicitConfig copy$default(PeanutSolicitConfig peanutSolicitConfig, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = peanutSolicitConfig.play_solicit_voice;
            }
            if ((i & 2) != 0) {
                z2 = peanutSolicitConfig.show_face_icon;
            }
            return peanutSolicitConfig.copy(z, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getPlay_solicit_voice() {
            return this.play_solicit_voice;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getShow_face_icon() {
            return this.show_face_icon;
        }

        public final PeanutSolicitConfig copy(boolean play_solicit_voice, boolean show_face_icon) {
            return new PeanutSolicitConfig(play_solicit_voice, show_face_icon);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PeanutSolicitConfig)) {
                return false;
            }
            PeanutSolicitConfig peanutSolicitConfig = (PeanutSolicitConfig) other;
            return this.play_solicit_voice == peanutSolicitConfig.play_solicit_voice && this.show_face_icon == peanutSolicitConfig.show_face_icon;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.play_solicit_voice;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            boolean z2 = this.show_face_icon;
            return i + (z2 ? 1 : z2 ? 1 : 0);
        }

        public String toString() {
            return "PeanutSolicitConfig(play_solicit_voice=" + this.play_solicit_voice + ", show_face_icon=" + this.show_face_icon + ")";
        }

        public PeanutSolicitConfig(boolean z, boolean z2) {
            super(null);
            this.play_solicit_voice = z;
            this.show_face_icon = z2;
        }

        public final boolean getPlay_solicit_voice() {
            return this.play_solicit_voice;
        }

        public final boolean getShow_face_icon() {
            return this.show_face_icon;
        }
    }

    private BaseSolicitConfig() {
    }

    public /* synthetic */ BaseSolicitConfig(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
