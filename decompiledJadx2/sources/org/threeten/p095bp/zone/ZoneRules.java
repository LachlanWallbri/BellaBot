package org.threeten.p095bp.zone;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.threeten.p095bp.Duration;
import org.threeten.p095bp.Instant;
import org.threeten.p095bp.LocalDateTime;
import org.threeten.p095bp.ZoneOffset;
import org.threeten.p095bp.jdk8.Jdk8Methods;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class ZoneRules {
    public abstract boolean equals(Object obj);

    public abstract Duration getDaylightSavings(Instant instant);

    public abstract ZoneOffset getOffset(Instant instant);

    public abstract ZoneOffset getOffset(LocalDateTime localDateTime);

    public abstract ZoneOffset getStandardOffset(Instant instant);

    public abstract ZoneOffsetTransition getTransition(LocalDateTime localDateTime);

    public abstract List<ZoneOffsetTransitionRule> getTransitionRules();

    public abstract List<ZoneOffsetTransition> getTransitions();

    public abstract List<ZoneOffset> getValidOffsets(LocalDateTime localDateTime);

    public abstract int hashCode();

    public abstract boolean isDaylightSavings(Instant instant);

    public abstract boolean isFixedOffset();

    public abstract boolean isValidOffset(LocalDateTime localDateTime, ZoneOffset zoneOffset);

    public abstract ZoneOffsetTransition nextTransition(Instant instant);

    public abstract ZoneOffsetTransition previousTransition(Instant instant);

    /* renamed from: of */
    public static ZoneRules m4252of(ZoneOffset zoneOffset, ZoneOffset zoneOffset2, List<ZoneOffsetTransition> list, List<ZoneOffsetTransition> list2, List<ZoneOffsetTransitionRule> list3) {
        Jdk8Methods.requireNonNull(zoneOffset, "baseStandardOffset");
        Jdk8Methods.requireNonNull(zoneOffset2, "baseWallOffset");
        Jdk8Methods.requireNonNull(list, "standardOffsetTransitionList");
        Jdk8Methods.requireNonNull(list2, "transitionList");
        Jdk8Methods.requireNonNull(list3, "lastRules");
        return new StandardZoneRules(zoneOffset, zoneOffset2, list, list2, list3);
    }

    /* renamed from: of */
    public static ZoneRules m4251of(ZoneOffset zoneOffset) {
        Jdk8Methods.requireNonNull(zoneOffset, TypedValues.Cycle.S_WAVE_OFFSET);
        return new Fixed(zoneOffset);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    static final class Fixed extends ZoneRules implements Serializable {
        private static final long serialVersionUID = -8733721350312276297L;
        private final ZoneOffset offset;

        @Override // org.threeten.p095bp.zone.ZoneRules
        public ZoneOffsetTransition getTransition(LocalDateTime localDateTime) {
            return null;
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public boolean isDaylightSavings(Instant instant) {
            return false;
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public boolean isFixedOffset() {
            return true;
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public ZoneOffsetTransition nextTransition(Instant instant) {
            return null;
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public ZoneOffsetTransition previousTransition(Instant instant) {
            return null;
        }

        Fixed(ZoneOffset zoneOffset) {
            this.offset = zoneOffset;
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public ZoneOffset getOffset(Instant instant) {
            return this.offset;
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public ZoneOffset getOffset(LocalDateTime localDateTime) {
            return this.offset;
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public List<ZoneOffset> getValidOffsets(LocalDateTime localDateTime) {
            return Collections.singletonList(this.offset);
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public boolean isValidOffset(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
            return this.offset.equals(zoneOffset);
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public ZoneOffset getStandardOffset(Instant instant) {
            return this.offset;
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public Duration getDaylightSavings(Instant instant) {
            return Duration.ZERO;
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public List<ZoneOffsetTransition> getTransitions() {
            return Collections.emptyList();
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public List<ZoneOffsetTransitionRule> getTransitionRules() {
            return Collections.emptyList();
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Fixed) {
                return this.offset.equals(((Fixed) obj).offset);
            }
            if (!(obj instanceof StandardZoneRules)) {
                return false;
            }
            StandardZoneRules standardZoneRules = (StandardZoneRules) obj;
            return standardZoneRules.isFixedOffset() && this.offset.equals(standardZoneRules.getOffset(Instant.EPOCH));
        }

        @Override // org.threeten.p095bp.zone.ZoneRules
        public int hashCode() {
            return ((((this.offset.hashCode() + 31) ^ 1) ^ 1) ^ (this.offset.hashCode() + 31)) ^ 1;
        }

        public String toString() {
            return "FixedRules:" + this.offset;
        }
    }
}
