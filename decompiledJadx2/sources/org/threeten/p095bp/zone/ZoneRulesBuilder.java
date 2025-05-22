package org.threeten.p095bp.zone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.threeten.p095bp.DayOfWeek;
import org.threeten.p095bp.LocalDate;
import org.threeten.p095bp.LocalDateTime;
import org.threeten.p095bp.LocalTime;
import org.threeten.p095bp.Month;
import org.threeten.p095bp.Year;
import org.threeten.p095bp.ZoneOffset;
import org.threeten.p095bp.chrono.ChronoLocalDate;
import org.threeten.p095bp.chrono.IsoChronology;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.TemporalAdjusters;
import org.threeten.p095bp.zone.ZoneOffsetTransitionRule;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ZoneRulesBuilder {
    private Map<Object, Object> deduplicateMap;
    private List<TZWindow> windowList = new ArrayList();

    public ZoneRulesBuilder addWindow(ZoneOffset zoneOffset, LocalDateTime localDateTime, ZoneOffsetTransitionRule.TimeDefinition timeDefinition) {
        Jdk8Methods.requireNonNull(zoneOffset, "standardOffset");
        Jdk8Methods.requireNonNull(localDateTime, "until");
        Jdk8Methods.requireNonNull(timeDefinition, "untilDefinition");
        TZWindow tZWindow = new TZWindow(zoneOffset, localDateTime, timeDefinition);
        if (this.windowList.size() > 0) {
            tZWindow.validateWindowOrder(this.windowList.get(r2.size() - 1));
        }
        this.windowList.add(tZWindow);
        return this;
    }

    public ZoneRulesBuilder addWindowForever(ZoneOffset zoneOffset) {
        return addWindow(zoneOffset, LocalDateTime.MAX, ZoneOffsetTransitionRule.TimeDefinition.WALL);
    }

    public ZoneRulesBuilder setFixedSavingsToWindow(int i) {
        if (this.windowList.isEmpty()) {
            throw new IllegalStateException("Must add a window before setting the fixed savings");
        }
        this.windowList.get(r0.size() - 1).setFixedSavings(i);
        return this;
    }

    public ZoneRulesBuilder addRuleToWindow(LocalDateTime localDateTime, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i) {
        Jdk8Methods.requireNonNull(localDateTime, "transitionDateTime");
        return addRuleToWindow(localDateTime.getYear(), localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), (DayOfWeek) null, localDateTime.toLocalTime(), false, timeDefinition, i);
    }

    public ZoneRulesBuilder addRuleToWindow(int i, Month month, int i2, LocalTime localTime, boolean z, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i3) {
        return addRuleToWindow(i, i, month, i2, (DayOfWeek) null, localTime, z, timeDefinition, i3);
    }

    public ZoneRulesBuilder addRuleToWindow(int i, int i2, Month month, int i3, DayOfWeek dayOfWeek, LocalTime localTime, boolean z, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i4) {
        Jdk8Methods.requireNonNull(month, "month");
        Jdk8Methods.requireNonNull(localTime, "time");
        Jdk8Methods.requireNonNull(timeDefinition, "timeDefinition");
        ChronoField.YEAR.checkValidValue(i);
        ChronoField.YEAR.checkValidValue(i2);
        if (i3 < -28 || i3 > 31 || i3 == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
        if (z && !localTime.equals(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        }
        if (this.windowList.isEmpty()) {
            throw new IllegalStateException("Must add a window before adding a rule");
        }
        this.windowList.get(r1.size() - 1).addRule(i, i2, month, i3, dayOfWeek, localTime, z ? 1 : 0, timeDefinition, i4);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoneRulesBuilder addRuleToWindow(int i, int i2, Month month, int i3, DayOfWeek dayOfWeek, LocalTime localTime, int i4, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i5) {
        Jdk8Methods.requireNonNull(month, "month");
        Jdk8Methods.requireNonNull(timeDefinition, "timeDefinition");
        ChronoField.YEAR.checkValidValue(i);
        ChronoField.YEAR.checkValidValue(i2);
        if (i3 < -28 || i3 > 31 || i3 == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
        if (this.windowList.isEmpty()) {
            throw new IllegalStateException("Must add a window before adding a rule");
        }
        this.windowList.get(r1.size() - 1).addRule(i, i2, month, i3, dayOfWeek, localTime, i4, timeDefinition, i5);
        return this;
    }

    public ZoneRules toRules(String str) {
        return toRules(str, new HashMap());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoneRules toRules(String str, Map<Object, Object> map) {
        Iterator<TZWindow> it;
        Jdk8Methods.requireNonNull(str, "zoneId");
        this.deduplicateMap = map;
        if (this.windowList.isEmpty()) {
            throw new IllegalStateException("No windows have been added to the builder");
        }
        ArrayList arrayList = new ArrayList(4);
        ArrayList arrayList2 = new ArrayList(256);
        ArrayList arrayList3 = new ArrayList(2);
        int i = 0;
        TZWindow tZWindow = this.windowList.get(0);
        ZoneOffset zoneOffset = tZWindow.standardOffset;
        int intValue = tZWindow.fixedSavingAmountSecs != null ? tZWindow.fixedSavingAmountSecs.intValue() : 0;
        ZoneOffset zoneOffset2 = (ZoneOffset) deduplicate(ZoneOffset.ofTotalSeconds(zoneOffset.getTotalSeconds() + intValue));
        LocalDateTime localDateTime = (LocalDateTime) deduplicate(LocalDateTime.m4201of(Year.MIN_VALUE, 1, 1, 0, 0));
        Iterator<TZWindow> it2 = this.windowList.iterator();
        ZoneOffset zoneOffset3 = zoneOffset2;
        while (it2.hasNext()) {
            TZWindow next = it2.next();
            next.tidy(localDateTime.getYear());
            Integer num = next.fixedSavingAmountSecs;
            if (num == null) {
                num = Integer.valueOf(i);
                for (TZRule tZRule : next.ruleList) {
                    if (tZRule.toTransition(zoneOffset, intValue).toEpochSecond() > localDateTime.toEpochSecond(zoneOffset3)) {
                        break;
                    }
                    num = Integer.valueOf(tZRule.savingAmountSecs);
                }
            }
            if (zoneOffset.equals(next.standardOffset)) {
                it = it2;
            } else {
                it = it2;
                arrayList.add(deduplicate(new ZoneOffsetTransition(LocalDateTime.ofEpochSecond(localDateTime.toEpochSecond(zoneOffset3), i, zoneOffset), zoneOffset, next.standardOffset)));
                zoneOffset = (ZoneOffset) deduplicate(next.standardOffset);
            }
            ZoneOffset zoneOffset4 = (ZoneOffset) deduplicate(ZoneOffset.ofTotalSeconds(zoneOffset.getTotalSeconds() + num.intValue()));
            if (!zoneOffset3.equals(zoneOffset4)) {
                arrayList2.add((ZoneOffsetTransition) deduplicate(new ZoneOffsetTransition(localDateTime, zoneOffset3, zoneOffset4)));
            }
            intValue = num.intValue();
            for (TZRule tZRule2 : next.ruleList) {
                ZoneOffsetTransition zoneOffsetTransition = (ZoneOffsetTransition) deduplicate(tZRule2.toTransition(zoneOffset, intValue));
                if ((zoneOffsetTransition.toEpochSecond() < localDateTime.toEpochSecond(zoneOffset3) ? 1 : i) == 0 && zoneOffsetTransition.toEpochSecond() < next.createDateTimeEpochSecond(intValue) && !zoneOffsetTransition.getOffsetBefore().equals(zoneOffsetTransition.getOffsetAfter())) {
                    arrayList2.add(zoneOffsetTransition);
                    intValue = tZRule2.savingAmountSecs;
                }
                i = 0;
            }
            for (TZRule tZRule3 : next.lastRuleList) {
                arrayList3.add((ZoneOffsetTransitionRule) deduplicate(tZRule3.toTransitionRule(zoneOffset, intValue)));
                intValue = tZRule3.savingAmountSecs;
            }
            zoneOffset3 = (ZoneOffset) deduplicate(next.createWallOffset(intValue));
            i = 0;
            localDateTime = (LocalDateTime) deduplicate(LocalDateTime.ofEpochSecond(next.createDateTimeEpochSecond(intValue), 0, zoneOffset3));
            it2 = it;
        }
        return new StandardZoneRules(tZWindow.standardOffset, zoneOffset2, arrayList, arrayList2, arrayList3);
    }

    <T> T deduplicate(T t) {
        if (!this.deduplicateMap.containsKey(t)) {
            this.deduplicateMap.put(t, t);
        }
        return (T) this.deduplicateMap.get(t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class TZWindow {
        private Integer fixedSavingAmountSecs;
        private final ZoneOffset standardOffset;
        private final ZoneOffsetTransitionRule.TimeDefinition timeDefinition;
        private final LocalDateTime windowEnd;
        private List<TZRule> ruleList = new ArrayList();
        private int maxLastRuleStartYear = Year.MIN_VALUE;
        private List<TZRule> lastRuleList = new ArrayList();

        TZWindow(ZoneOffset zoneOffset, LocalDateTime localDateTime, ZoneOffsetTransitionRule.TimeDefinition timeDefinition) {
            this.windowEnd = localDateTime;
            this.timeDefinition = timeDefinition;
            this.standardOffset = zoneOffset;
        }

        void setFixedSavings(int i) {
            if (this.ruleList.size() > 0 || this.lastRuleList.size() > 0) {
                throw new IllegalStateException("Window has DST rules, so cannot have fixed savings");
            }
            this.fixedSavingAmountSecs = Integer.valueOf(i);
        }

        void addRule(int i, int i2, Month month, int i3, DayOfWeek dayOfWeek, LocalTime localTime, int i4, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i5) {
            if (this.fixedSavingAmountSecs != null) {
                throw new IllegalStateException("Window has a fixed DST saving, so cannot have DST rules");
            }
            if (this.ruleList.size() >= 2000) {
                throw new IllegalStateException("Window has reached the maximum number of allowed rules");
            }
            boolean z = false;
            int i6 = i2;
            if (i6 == 999999999) {
                z = true;
                i6 = i;
            }
            for (int i7 = i; i7 <= i6; i7++) {
                TZRule tZRule = new TZRule(i7, month, i3, dayOfWeek, localTime, i4, timeDefinition, i5);
                if (z) {
                    this.lastRuleList.add(tZRule);
                    this.maxLastRuleStartYear = Math.max(i, this.maxLastRuleStartYear);
                } else {
                    this.ruleList.add(tZRule);
                }
            }
        }

        void validateWindowOrder(TZWindow tZWindow) {
            if (this.windowEnd.isBefore(tZWindow.windowEnd)) {
                throw new IllegalStateException("Windows must be added in date-time order: " + this.windowEnd + " < " + tZWindow.windowEnd);
            }
        }

        void tidy(int i) {
            if (this.lastRuleList.size() == 1) {
                throw new IllegalStateException("Cannot have only one rule defined as being forever");
            }
            if (this.windowEnd.equals(LocalDateTime.MAX)) {
                this.maxLastRuleStartYear = Math.max(this.maxLastRuleStartYear, i) + 1;
                for (TZRule tZRule : this.lastRuleList) {
                    addRule(tZRule.year, this.maxLastRuleStartYear, tZRule.month, tZRule.dayOfMonthIndicator, tZRule.dayOfWeek, tZRule.time, tZRule.adjustDays, tZRule.timeDefinition, tZRule.savingAmountSecs);
                    tZRule.year = this.maxLastRuleStartYear + 1;
                }
                int i2 = this.maxLastRuleStartYear;
                if (i2 == 999999999) {
                    this.lastRuleList.clear();
                } else {
                    this.maxLastRuleStartYear = i2 + 1;
                }
            } else {
                int year = this.windowEnd.getYear();
                for (TZRule tZRule2 : this.lastRuleList) {
                    addRule(tZRule2.year, year + 1, tZRule2.month, tZRule2.dayOfMonthIndicator, tZRule2.dayOfWeek, tZRule2.time, tZRule2.adjustDays, tZRule2.timeDefinition, tZRule2.savingAmountSecs);
                }
                this.lastRuleList.clear();
                this.maxLastRuleStartYear = Year.MAX_VALUE;
            }
            Collections.sort(this.ruleList);
            Collections.sort(this.lastRuleList);
            if (this.ruleList.size() == 0 && this.fixedSavingAmountSecs == null) {
                this.fixedSavingAmountSecs = 0;
            }
        }

        boolean isSingleWindowStandardOffset() {
            return this.windowEnd.equals(LocalDateTime.MAX) && this.timeDefinition == ZoneOffsetTransitionRule.TimeDefinition.WALL && this.fixedSavingAmountSecs == null && this.lastRuleList.isEmpty() && this.ruleList.isEmpty();
        }

        ZoneOffset createWallOffset(int i) {
            return ZoneOffset.ofTotalSeconds(this.standardOffset.getTotalSeconds() + i);
        }

        long createDateTimeEpochSecond(int i) {
            ZoneOffset createWallOffset = createWallOffset(i);
            return this.timeDefinition.createDateTime(this.windowEnd, this.standardOffset, createWallOffset).toEpochSecond(createWallOffset);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public class TZRule implements Comparable<TZRule> {
        private int adjustDays;
        private int dayOfMonthIndicator;
        private DayOfWeek dayOfWeek;
        private Month month;
        private int savingAmountSecs;
        private LocalTime time;
        private ZoneOffsetTransitionRule.TimeDefinition timeDefinition;
        private int year;

        TZRule(int i, Month month, int i2, DayOfWeek dayOfWeek, LocalTime localTime, int i3, ZoneOffsetTransitionRule.TimeDefinition timeDefinition, int i4) {
            this.year = i;
            this.month = month;
            this.dayOfMonthIndicator = i2;
            this.dayOfWeek = dayOfWeek;
            this.time = localTime;
            this.adjustDays = i3;
            this.timeDefinition = timeDefinition;
            this.savingAmountSecs = i4;
        }

        ZoneOffsetTransition toTransition(ZoneOffset zoneOffset, int i) {
            LocalDateTime localDateTime = (LocalDateTime) ZoneRulesBuilder.this.deduplicate(LocalDateTime.m4207of(((LocalDate) ZoneRulesBuilder.this.deduplicate(toLocalDate())).plusDays(this.adjustDays), this.time));
            ZoneOffset zoneOffset2 = (ZoneOffset) ZoneRulesBuilder.this.deduplicate(ZoneOffset.ofTotalSeconds(zoneOffset.getTotalSeconds() + i));
            return new ZoneOffsetTransition((LocalDateTime) ZoneRulesBuilder.this.deduplicate(this.timeDefinition.createDateTime(localDateTime, zoneOffset, zoneOffset2)), zoneOffset2, (ZoneOffset) ZoneRulesBuilder.this.deduplicate(ZoneOffset.ofTotalSeconds(zoneOffset.getTotalSeconds() + this.savingAmountSecs)));
        }

        ZoneOffsetTransitionRule toTransitionRule(ZoneOffset zoneOffset, int i) {
            if (this.dayOfMonthIndicator < 0 && this.month != Month.FEBRUARY) {
                this.dayOfMonthIndicator = this.month.maxLength() - 6;
            }
            ZoneOffsetTransition transition = toTransition(zoneOffset, i);
            return new ZoneOffsetTransitionRule(this.month, this.dayOfMonthIndicator, this.dayOfWeek, this.time, this.adjustDays, this.timeDefinition, zoneOffset, transition.getOffsetBefore(), transition.getOffsetAfter());
        }

        @Override // java.lang.Comparable
        public int compareTo(TZRule tZRule) {
            int i = this.year - tZRule.year;
            if (i == 0) {
                i = this.month.compareTo(tZRule.month);
            }
            if (i == 0) {
                i = toLocalDate().compareTo((ChronoLocalDate) tZRule.toLocalDate());
            }
            if (i != 0) {
                return i;
            }
            long secondOfDay = this.time.toSecondOfDay() + (this.adjustDays * 86400);
            long secondOfDay2 = tZRule.time.toSecondOfDay() + (tZRule.adjustDays * 86400);
            if (secondOfDay < secondOfDay2) {
                return -1;
            }
            return secondOfDay > secondOfDay2 ? 1 : 0;
        }

        private LocalDate toLocalDate() {
            int i = this.dayOfMonthIndicator;
            if (i < 0) {
                LocalDate m4200of = LocalDate.m4200of(this.year, this.month, this.month.length(IsoChronology.INSTANCE.isLeapYear(this.year)) + 1 + this.dayOfMonthIndicator);
                DayOfWeek dayOfWeek = this.dayOfWeek;
                return dayOfWeek != null ? m4200of.with(TemporalAdjusters.previousOrSame(dayOfWeek)) : m4200of;
            }
            LocalDate m4200of2 = LocalDate.m4200of(this.year, this.month, i);
            DayOfWeek dayOfWeek2 = this.dayOfWeek;
            return dayOfWeek2 != null ? m4200of2.with(TemporalAdjusters.nextOrSame(dayOfWeek2)) : m4200of2;
        }
    }
}
