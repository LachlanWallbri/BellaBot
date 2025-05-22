package org.threeten.p095bp.chrono;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import org.threeten.p095bp.DateTimeException;
import org.threeten.p095bp.LocalDate;
import org.threeten.p095bp.jdk8.DefaultInterfaceEra;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.TemporalField;
import org.threeten.p095bp.temporal.ValueRange;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class JapaneseEra extends DefaultInterfaceEra implements Serializable {
    private static final int ADDITIONAL_VALUE = 4;
    static final int ERA_OFFSET = 2;
    private static final long serialVersionUID = 1466499369062886794L;
    private final int eraValue;
    private final transient String name;
    private final transient LocalDate since;
    public static final JapaneseEra MEIJI = new JapaneseEra(-1, LocalDate.m4199of(1868, 9, 8), "Meiji");
    public static final JapaneseEra TAISHO = new JapaneseEra(0, LocalDate.m4199of(1912, 7, 30), "Taisho");
    public static final JapaneseEra SHOWA = new JapaneseEra(1, LocalDate.m4199of(1926, 12, 25), "Showa");
    public static final JapaneseEra HEISEI = new JapaneseEra(2, LocalDate.m4199of(1989, 1, 8), "Heisei");
    public static final JapaneseEra REIWA = new JapaneseEra(3, LocalDate.m4199of(2019, 5, 1), "Reiwa");
    private static final AtomicReference<JapaneseEra[]> KNOWN_ERAS = new AtomicReference<>(new JapaneseEra[]{MEIJI, TAISHO, SHOWA, HEISEI, REIWA});

    private static int ordinal(int i) {
        return i + 1;
    }

    private JapaneseEra(int i, LocalDate localDate, String str) {
        this.eraValue = i;
        this.since = localDate;
        this.name = str;
    }

    private Object readResolve() throws ObjectStreamException {
        try {
            return m4238of(this.eraValue);
        } catch (DateTimeException e) {
            InvalidObjectException invalidObjectException = new InvalidObjectException("Invalid era");
            invalidObjectException.initCause(e);
            throw invalidObjectException;
        }
    }

    public static JapaneseEra registerEra(LocalDate localDate, String str) {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS.get();
        if (japaneseEraArr.length > 5) {
            throw new DateTimeException("Only one additional Japanese era can be added");
        }
        Jdk8Methods.requireNonNull(localDate, "since");
        Jdk8Methods.requireNonNull(str, "name");
        if (!localDate.isAfter(REIWA.since)) {
            throw new DateTimeException("Invalid since date for additional Japanese era, must be after Reiwa");
        }
        JapaneseEra japaneseEra = new JapaneseEra(4, localDate, str);
        JapaneseEra[] japaneseEraArr2 = (JapaneseEra[]) Arrays.copyOf(japaneseEraArr, 6);
        japaneseEraArr2[5] = japaneseEra;
        if (KNOWN_ERAS.compareAndSet(japaneseEraArr, japaneseEraArr2)) {
            return japaneseEra;
        }
        throw new DateTimeException("Only one additional Japanese era can be added");
    }

    /* renamed from: of */
    public static JapaneseEra m4238of(int i) {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS.get();
        if (i < MEIJI.eraValue || i > japaneseEraArr[japaneseEraArr.length - 1].eraValue) {
            throw new DateTimeException("japaneseEra is invalid");
        }
        return japaneseEraArr[ordinal(i)];
    }

    public static JapaneseEra valueOf(String str) {
        Jdk8Methods.requireNonNull(str, "japaneseEra");
        for (JapaneseEra japaneseEra : KNOWN_ERAS.get()) {
            if (str.equals(japaneseEra.name)) {
                return japaneseEra;
            }
        }
        throw new IllegalArgumentException("Era not found: " + str);
    }

    public static JapaneseEra[] values() {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS.get();
        return (JapaneseEra[]) Arrays.copyOf(japaneseEraArr, japaneseEraArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JapaneseEra from(LocalDate localDate) {
        if (localDate.isBefore(MEIJI.since)) {
            throw new DateTimeException("Date too early: " + localDate);
        }
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS.get();
        for (int length = japaneseEraArr.length - 1; length >= 0; length--) {
            JapaneseEra japaneseEra = japaneseEraArr[length];
            if (localDate.compareTo((ChronoLocalDate) japaneseEra.since) >= 0) {
                return japaneseEra;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalDate startDate() {
        return this.since;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalDate endDate() {
        int ordinal = ordinal(this.eraValue);
        JapaneseEra[] values = values();
        if (ordinal >= values.length - 1) {
            return LocalDate.MAX;
        }
        return values[ordinal + 1].startDate().minusDays(1L);
    }

    @Override // org.threeten.p095bp.chrono.Era
    public int getValue() {
        return this.eraValue;
    }

    @Override // org.threeten.p095bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.p095bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField == ChronoField.ERA) {
            return JapaneseChronology.INSTANCE.range(ChronoField.ERA);
        }
        return super.range(temporalField);
    }

    public String toString() {
        return this.name;
    }

    private Object writeReplace() {
        return new Ser((byte) 2, this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(getValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JapaneseEra readExternal(DataInput dataInput) throws IOException {
        return m4238of(dataInput.readByte());
    }
}
