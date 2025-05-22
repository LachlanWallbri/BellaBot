package org.threeten.p095bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.threeten.p095bp.jdk8.Jdk8Methods;
import org.threeten.p095bp.zone.ZoneRules;
import org.threeten.p095bp.zone.ZoneRulesException;
import org.threeten.p095bp.zone.ZoneRulesProvider;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class ZoneRegion extends ZoneId implements Serializable {
    private static final Pattern PATTERN = Pattern.compile("[A-Za-z][A-Za-z0-9~/._+-]+");
    private static final long serialVersionUID = 8386373296231747096L;

    /* renamed from: id */
    private final String f10291id;
    private final transient ZoneRules rules;

    private static ZoneRegion ofLenient(String str) {
        if (str.equals("Z") || str.startsWith("+") || str.startsWith("-")) {
            throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: " + str);
        }
        if (str.equals("UTC") || str.equals("GMT") || str.equals("UT")) {
            return new ZoneRegion(str, ZoneOffset.UTC.getRules());
        }
        if (str.startsWith("UTC+") || str.startsWith("GMT+") || str.startsWith("UTC-") || str.startsWith("GMT-")) {
            ZoneOffset m4225of = ZoneOffset.m4225of(str.substring(3));
            if (m4225of.getTotalSeconds() == 0) {
                return new ZoneRegion(str.substring(0, 3), m4225of.getRules());
            }
            return new ZoneRegion(str.substring(0, 3) + m4225of.getId(), m4225of.getRules());
        }
        if (str.startsWith("UT+") || str.startsWith("UT-")) {
            ZoneOffset m4225of2 = ZoneOffset.m4225of(str.substring(2));
            if (m4225of2.getTotalSeconds() == 0) {
                return new ZoneRegion("UT", m4225of2.getRules());
            }
            return new ZoneRegion("UT" + m4225of2.getId(), m4225of2.getRules());
        }
        return ofId(str, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneRegion ofId(String str, boolean z) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        if (str.length() < 2 || !PATTERN.matcher(str).matches()) {
            throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: " + str);
        }
        ZoneRules zoneRules = null;
        try {
            zoneRules = ZoneRulesProvider.getRules(str, true);
        } catch (ZoneRulesException e) {
            if (str.equals("GMT0")) {
                zoneRules = ZoneOffset.UTC.getRules();
            } else if (z) {
                throw e;
            }
        }
        return new ZoneRegion(str, zoneRules);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZoneRegion(String str, ZoneRules zoneRules) {
        this.f10291id = str;
        this.rules = zoneRules;
    }

    @Override // org.threeten.p095bp.ZoneId
    public String getId() {
        return this.f10291id;
    }

    @Override // org.threeten.p095bp.ZoneId
    public ZoneRules getRules() {
        ZoneRules zoneRules = this.rules;
        return zoneRules != null ? zoneRules : ZoneRulesProvider.getRules(this.f10291id, false);
    }

    private Object writeReplace() {
        return new Ser((byte) 7, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.threeten.p095bp.ZoneId
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(7);
        writeExternal(dataOutput);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.f10291id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ZoneId readExternal(DataInput dataInput) throws IOException {
        return ofLenient(dataInput.readUTF());
    }
}
