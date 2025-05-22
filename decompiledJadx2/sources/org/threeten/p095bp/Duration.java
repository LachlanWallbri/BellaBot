package org.threeten.p095bp;

import androidx.exifinterface.media.ExifInterface;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;
import org.threeten.p095bp.format.DateTimeParseException;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.ChronoUnit;
import org.threeten.p095bp.temporal.Temporal;
import org.threeten.p095bp.temporal.TemporalAmount;
import org.threeten.p095bp.temporal.TemporalUnit;
import org.threeten.p095bp.temporal.UnsupportedTemporalTypeException;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class Duration implements TemporalAmount, Comparable<Duration>, Serializable {
    private static final int NANOS_PER_MILLI = 1000000;
    private static final int NANOS_PER_SECOND = 1000000000;
    private static final long serialVersionUID = 3078945930695997490L;
    private final int nanos;
    private final long seconds;
    public static final Duration ZERO = new Duration(0, 0);
    private static final BigInteger BI_NANOS_PER_SECOND = BigInteger.valueOf(1000000000);
    private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)D)?(T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)(?:[.,]([0-9]{0,9}))?S)?)?", 2);

    public static Duration ofDays(long j) {
        return create(Jdk8Methods.safeMultiply(j, 86400), 0);
    }

    public static Duration ofHours(long j) {
        return create(Jdk8Methods.safeMultiply(j, 3600), 0);
    }

    public static Duration ofMinutes(long j) {
        return create(Jdk8Methods.safeMultiply(j, 60), 0);
    }

    public static Duration ofSeconds(long j) {
        return create(j, 0);
    }

    public static Duration ofSeconds(long j, long j2) {
        return create(Jdk8Methods.safeAdd(j, Jdk8Methods.floorDiv(j2, 1000000000L)), Jdk8Methods.floorMod(j2, 1000000000));
    }

    public static Duration ofMillis(long j) {
        long j2 = j / 1000;
        int i = (int) (j % 1000);
        if (i < 0) {
            i += 1000;
            j2--;
        }
        return create(j2, i * NANOS_PER_MILLI);
    }

    public static Duration ofNanos(long j) {
        long j2 = j / 1000000000;
        int i = (int) (j % 1000000000);
        if (i < 0) {
            i += 1000000000;
            j2--;
        }
        return create(j2, i);
    }

    /* renamed from: of */
    public static Duration m4198of(long j, TemporalUnit temporalUnit) {
        return ZERO.plus(j, temporalUnit);
    }

    public static Duration from(TemporalAmount temporalAmount) {
        Jdk8Methods.requireNonNull(temporalAmount, "amount");
        Duration duration = ZERO;
        for (TemporalUnit temporalUnit : temporalAmount.getUnits()) {
            duration = duration.plus(temporalAmount.get(temporalUnit), temporalUnit);
        }
        return duration;
    }

    public static Duration between(Temporal temporal, Temporal temporal2) {
        long until = temporal.until(temporal2, ChronoUnit.SECONDS);
        long j = 0;
        if (temporal.isSupported(ChronoField.NANO_OF_SECOND) && temporal2.isSupported(ChronoField.NANO_OF_SECOND)) {
            try {
                long j2 = temporal.getLong(ChronoField.NANO_OF_SECOND);
                long j3 = temporal2.getLong(ChronoField.NANO_OF_SECOND) - j2;
                if (until > 0 && j3 < 0) {
                    j3 += 1000000000;
                } else if (until < 0 && j3 > 0) {
                    j3 -= 1000000000;
                } else if (until == 0 && j3 != 0) {
                    try {
                        until = temporal.until(temporal2.with(ChronoField.NANO_OF_SECOND, j2), ChronoUnit.SECONDS);
                    } catch (ArithmeticException | DateTimeException unused) {
                    }
                }
                j = j3;
            } catch (ArithmeticException | DateTimeException unused2) {
            }
        }
        return ofSeconds(until, j);
    }

    public static Duration parse(CharSequence charSequence) {
        Jdk8Methods.requireNonNull(charSequence, "text");
        Matcher matcher = PATTERN.matcher(charSequence);
        if (matcher.matches() && !ExifInterface.GPS_DIRECTION_TRUE.equals(matcher.group(3))) {
            boolean equals = "-".equals(matcher.group(1));
            String group = matcher.group(2);
            String group2 = matcher.group(4);
            String group3 = matcher.group(5);
            String group4 = matcher.group(6);
            String group5 = matcher.group(7);
            if (group != null || group2 != null || group3 != null || group4 != null) {
                try {
                    return create(equals, parseNumber(charSequence, group, 86400, "days"), parseNumber(charSequence, group2, 3600, "hours"), parseNumber(charSequence, group3, 60, "minutes"), parseNumber(charSequence, group4, 1, "seconds"), parseFraction(charSequence, group5, group4 != null && group4.charAt(0) == '-' ? -1 : 1));
                } catch (ArithmeticException e) {
                    throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: overflow", charSequence, 0).initCause(e));
                }
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Duration", charSequence, 0);
    }

    private static long parseNumber(CharSequence charSequence, String str, int i, String str2) {
        if (str == null) {
            return 0L;
        }
        try {
            if (str.startsWith("+")) {
                str = str.substring(1);
            }
            return Jdk8Methods.safeMultiply(Long.parseLong(str), i);
        } catch (ArithmeticException e) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: " + str2, charSequence, 0).initCause(e));
        } catch (NumberFormatException e2) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: " + str2, charSequence, 0).initCause(e2));
        }
    }

    private static int parseFraction(CharSequence charSequence, String str, int i) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        try {
            return Integer.parseInt((str + "000000000").substring(0, 9)) * i;
        } catch (ArithmeticException e) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: fraction", charSequence, 0).initCause(e));
        } catch (NumberFormatException e2) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: fraction", charSequence, 0).initCause(e2));
        }
    }

    private static Duration create(boolean z, long j, long j2, long j3, long j4, int i) {
        long safeAdd = Jdk8Methods.safeAdd(j, Jdk8Methods.safeAdd(j2, Jdk8Methods.safeAdd(j3, j4)));
        if (z) {
            return ofSeconds(safeAdd, i).negated();
        }
        return ofSeconds(safeAdd, i);
    }

    private static Duration create(long j, int i) {
        if ((i | j) == 0) {
            return ZERO;
        }
        return new Duration(j, i);
    }

    private Duration(long j, int i) {
        this.seconds = j;
        this.nanos = i;
    }

    @Override // org.threeten.p095bp.temporal.TemporalAmount
    public List<TemporalUnit> getUnits() {
        return Collections.unmodifiableList(Arrays.asList(ChronoUnit.SECONDS, ChronoUnit.NANOS));
    }

    @Override // org.threeten.p095bp.temporal.TemporalAmount
    public long get(TemporalUnit temporalUnit) {
        if (temporalUnit == ChronoUnit.SECONDS) {
            return this.seconds;
        }
        if (temporalUnit == ChronoUnit.NANOS) {
            return this.nanos;
        }
        throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
    }

    public boolean isZero() {
        return (this.seconds | ((long) this.nanos)) == 0;
    }

    public boolean isNegative() {
        return this.seconds < 0;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public int getNano() {
        return this.nanos;
    }

    public Duration withSeconds(long j) {
        return create(j, this.nanos);
    }

    public Duration withNanos(int i) {
        ChronoField.NANO_OF_SECOND.checkValidIntValue(i);
        return create(this.seconds, i);
    }

    public Duration plus(Duration duration) {
        return plus(duration.getSeconds(), duration.getNano());
    }

    public Duration plus(long j, TemporalUnit temporalUnit) {
        Jdk8Methods.requireNonNull(temporalUnit, "unit");
        if (temporalUnit == ChronoUnit.DAYS) {
            return plus(Jdk8Methods.safeMultiply(j, 86400), 0L);
        }
        if (temporalUnit.isDurationEstimated()) {
            throw new DateTimeException("Unit must not have an estimated duration");
        }
        if (j == 0) {
            return this;
        }
        if (temporalUnit instanceof ChronoUnit) {
            int i = C89081.$SwitchMap$org$threeten$bp$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()];
            if (i == 1) {
                return plusNanos(j);
            }
            if (i == 2) {
                return plusSeconds((j / 1000000000) * 1000).plusNanos((j % 1000000000) * 1000);
            }
            if (i == 3) {
                return plusMillis(j);
            }
            if (i == 4) {
                return plusSeconds(j);
            }
            return plusSeconds(Jdk8Methods.safeMultiply(temporalUnit.getDuration().seconds, j));
        }
        return plusSeconds(temporalUnit.getDuration().multipliedBy(j).getSeconds()).plusNanos(r7.getNano());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.threeten.bp.Duration$1 */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class C89081 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoUnit = new int[ChronoUnit.values().length];

        static {
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.NANOS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.MICROS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.MILLIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$threeten$bp$temporal$ChronoUnit[ChronoUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public Duration plusDays(long j) {
        return plus(Jdk8Methods.safeMultiply(j, 86400), 0L);
    }

    public Duration plusHours(long j) {
        return plus(Jdk8Methods.safeMultiply(j, 3600), 0L);
    }

    public Duration plusMinutes(long j) {
        return plus(Jdk8Methods.safeMultiply(j, 60), 0L);
    }

    public Duration plusSeconds(long j) {
        return plus(j, 0L);
    }

    public Duration plusMillis(long j) {
        return plus(j / 1000, (j % 1000) * 1000000);
    }

    public Duration plusNanos(long j) {
        return plus(0L, j);
    }

    private Duration plus(long j, long j2) {
        if ((j | j2) == 0) {
            return this;
        }
        return ofSeconds(Jdk8Methods.safeAdd(Jdk8Methods.safeAdd(this.seconds, j), j2 / 1000000000), this.nanos + (j2 % 1000000000));
    }

    public Duration minus(Duration duration) {
        long seconds = duration.getSeconds();
        int nano = duration.getNano();
        if (seconds == Long.MIN_VALUE) {
            return plus(Long.MAX_VALUE, -nano).plus(1L, 0L);
        }
        return plus(-seconds, -nano);
    }

    public Duration minus(long j, TemporalUnit temporalUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, temporalUnit).plus(1L, temporalUnit) : plus(-j, temporalUnit);
    }

    public Duration minusDays(long j) {
        return j == Long.MIN_VALUE ? plusDays(Long.MAX_VALUE).plusDays(1L) : plusDays(-j);
    }

    public Duration minusHours(long j) {
        return j == Long.MIN_VALUE ? plusHours(Long.MAX_VALUE).plusHours(1L) : plusHours(-j);
    }

    public Duration minusMinutes(long j) {
        return j == Long.MIN_VALUE ? plusMinutes(Long.MAX_VALUE).plusMinutes(1L) : plusMinutes(-j);
    }

    public Duration minusSeconds(long j) {
        return j == Long.MIN_VALUE ? plusSeconds(Long.MAX_VALUE).plusSeconds(1L) : plusSeconds(-j);
    }

    public Duration minusMillis(long j) {
        return j == Long.MIN_VALUE ? plusMillis(Long.MAX_VALUE).plusMillis(1L) : plusMillis(-j);
    }

    public Duration minusNanos(long j) {
        return j == Long.MIN_VALUE ? plusNanos(Long.MAX_VALUE).plusNanos(1L) : plusNanos(-j);
    }

    public Duration multipliedBy(long j) {
        if (j == 0) {
            return ZERO;
        }
        return j == 1 ? this : create(toSeconds().multiply(BigDecimal.valueOf(j)));
    }

    public Duration dividedBy(long j) {
        if (j != 0) {
            return j == 1 ? this : create(toSeconds().divide(BigDecimal.valueOf(j), RoundingMode.DOWN));
        }
        throw new ArithmeticException("Cannot divide by zero");
    }

    private BigDecimal toSeconds() {
        return BigDecimal.valueOf(this.seconds).add(BigDecimal.valueOf(this.nanos, 9));
    }

    private static Duration create(BigDecimal bigDecimal) {
        BigInteger bigIntegerExact = bigDecimal.movePointRight(9).toBigIntegerExact();
        BigInteger[] divideAndRemainder = bigIntegerExact.divideAndRemainder(BI_NANOS_PER_SECOND);
        if (divideAndRemainder[0].bitLength() > 63) {
            throw new ArithmeticException("Exceeds capacity of Duration: " + bigIntegerExact);
        }
        return ofSeconds(divideAndRemainder[0].longValue(), divideAndRemainder[1].intValue());
    }

    public Duration negated() {
        return multipliedBy(-1L);
    }

    public Duration abs() {
        return isNegative() ? negated() : this;
    }

    @Override // org.threeten.p095bp.temporal.TemporalAmount
    public Temporal addTo(Temporal temporal) {
        long j = this.seconds;
        if (j != 0) {
            temporal = temporal.plus(j, ChronoUnit.SECONDS);
        }
        int i = this.nanos;
        return i != 0 ? temporal.plus(i, ChronoUnit.NANOS) : temporal;
    }

    @Override // org.threeten.p095bp.temporal.TemporalAmount
    public Temporal subtractFrom(Temporal temporal) {
        long j = this.seconds;
        if (j != 0) {
            temporal = temporal.minus(j, ChronoUnit.SECONDS);
        }
        int i = this.nanos;
        return i != 0 ? temporal.minus(i, ChronoUnit.NANOS) : temporal;
    }

    public long toDays() {
        return this.seconds / 86400;
    }

    public long toHours() {
        return this.seconds / 3600;
    }

    public long toMinutes() {
        return this.seconds / 60;
    }

    public long toMillis() {
        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(this.seconds, 1000), this.nanos / NANOS_PER_MILLI);
    }

    public long toNanos() {
        return Jdk8Methods.safeAdd(Jdk8Methods.safeMultiply(this.seconds, 1000000000), this.nanos);
    }

    public long toDaysPart() {
        return this.seconds / 86400;
    }

    public int toHoursPart() {
        return (int) (toHours() % 24);
    }

    public int toMinutesPart() {
        return (int) (toMinutes() % 60);
    }

    public int toSecondsPart() {
        return (int) (this.seconds % 60);
    }

    public int toMillisPart() {
        return this.nanos / NANOS_PER_MILLI;
    }

    public int toNanosPart() {
        return this.nanos;
    }

    @Override // java.lang.Comparable
    public int compareTo(Duration duration) {
        int compareLongs = Jdk8Methods.compareLongs(this.seconds, duration.seconds);
        return compareLongs != 0 ? compareLongs : this.nanos - duration.nanos;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Duration)) {
            return false;
        }
        Duration duration = (Duration) obj;
        return this.seconds == duration.seconds && this.nanos == duration.nanos;
    }

    public int hashCode() {
        long j = this.seconds;
        return ((int) (j ^ (j >>> 32))) + (this.nanos * 51);
    }

    public String toString() {
        if (this == ZERO) {
            return "PT0S";
        }
        long j = this.seconds;
        long j2 = j / 3600;
        int i = (int) ((j % 3600) / 60);
        int i2 = (int) (j % 60);
        StringBuilder sb = new StringBuilder(24);
        sb.append("PT");
        if (j2 != 0) {
            sb.append(j2);
            sb.append('H');
        }
        if (i != 0) {
            sb.append(i);
            sb.append('M');
        }
        if (i2 == 0 && this.nanos == 0 && sb.length() > 2) {
            return sb.toString();
        }
        if (i2 >= 0 || this.nanos <= 0) {
            sb.append(i2);
        } else if (i2 == -1) {
            sb.append("-0");
        } else {
            sb.append(i2 + 1);
        }
        if (this.nanos > 0) {
            int length = sb.length();
            if (i2 < 0) {
                sb.append(2000000000 - this.nanos);
            } else {
                sb.append(this.nanos + 1000000000);
            }
            while (sb.charAt(sb.length() - 1) == '0') {
                sb.setLength(sb.length() - 1);
            }
            sb.setCharAt(length, FilenameUtils.EXTENSION_SEPARATOR);
        }
        sb.append('S');
        return sb.toString();
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(this.seconds);
        dataOutput.writeInt(this.nanos);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Duration readExternal(DataInput dataInput) throws IOException {
        return ofSeconds(dataInput.readLong(), dataInput.readInt());
    }
}
