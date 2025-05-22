package kotlin.reflect.jvm.internal.impl.utils;

import java.util.ArrayList;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: Jsr305State.kt */
/* loaded from: classes2.dex */
public final class Jsr305State {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Jsr305State.class), "description", "getDescription()[Ljava/lang/String;"))};
    public static final Companion Companion = new Companion(null);
    public static final Jsr305State DEFAULT = new Jsr305State(ReportLevel.WARN, null, MapsKt.emptyMap(), false, 8, null);
    public static final Jsr305State DISABLED = new Jsr305State(ReportLevel.IGNORE, ReportLevel.IGNORE, MapsKt.emptyMap(), false, 8, null);
    public static final Jsr305State STRICT = new Jsr305State(ReportLevel.STRICT, ReportLevel.STRICT, MapsKt.emptyMap(), false, 8, null);
    private final Lazy description$delegate;
    private final boolean enableCompatqualCheckerFrameworkAnnotations;
    private final ReportLevel global;
    private final ReportLevel migration;
    private final Map<String, ReportLevel> user;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof Jsr305State) {
                Jsr305State jsr305State = (Jsr305State) obj;
                if (Intrinsics.areEqual(this.global, jsr305State.global) && Intrinsics.areEqual(this.migration, jsr305State.migration) && Intrinsics.areEqual(this.user, jsr305State.user)) {
                    if (this.enableCompatqualCheckerFrameworkAnnotations == jsr305State.enableCompatqualCheckerFrameworkAnnotations) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        ReportLevel reportLevel = this.global;
        int hashCode = (reportLevel != null ? reportLevel.hashCode() : 0) * 31;
        ReportLevel reportLevel2 = this.migration;
        int hashCode2 = (hashCode + (reportLevel2 != null ? reportLevel2.hashCode() : 0)) * 31;
        Map<String, ReportLevel> map = this.user;
        int hashCode3 = (hashCode2 + (map != null ? map.hashCode() : 0)) * 31;
        boolean z = this.enableCompatqualCheckerFrameworkAnnotations;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "Jsr305State(global=" + this.global + ", migration=" + this.migration + ", user=" + this.user + ", enableCompatqualCheckerFrameworkAnnotations=" + this.enableCompatqualCheckerFrameworkAnnotations + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Jsr305State(ReportLevel global, ReportLevel reportLevel, Map<String, ? extends ReportLevel> user, boolean z) {
        Intrinsics.checkParameterIsNotNull(global, "global");
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.global = global;
        this.migration = reportLevel;
        this.user = user;
        this.enableCompatqualCheckerFrameworkAnnotations = z;
        this.description$delegate = LazyKt.lazy(new Function0<String[]>() { // from class: kotlin.reflect.jvm.internal.impl.utils.Jsr305State$description$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String[] invoke() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(Jsr305State.this.getGlobal().getDescription());
                ReportLevel migration = Jsr305State.this.getMigration();
                if (migration != null) {
                    arrayList.add("under-migration:" + migration.getDescription());
                }
                for (Map.Entry<String, ReportLevel> entry : Jsr305State.this.getUser().entrySet()) {
                    arrayList.add('@' + entry.getKey() + ':' + entry.getValue().getDescription());
                }
                Object[] array = arrayList.toArray(new String[0]);
                if (array != null) {
                    return (String[]) array;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        });
    }

    public final ReportLevel getGlobal() {
        return this.global;
    }

    public final ReportLevel getMigration() {
        return this.migration;
    }

    public final Map<String, ReportLevel> getUser() {
        return this.user;
    }

    public /* synthetic */ Jsr305State(ReportLevel reportLevel, ReportLevel reportLevel2, Map map, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reportLevel, reportLevel2, map, (i & 8) != 0 ? true : z);
    }

    public final boolean getEnableCompatqualCheckerFrameworkAnnotations() {
        return this.enableCompatqualCheckerFrameworkAnnotations;
    }

    public final boolean getDisabled() {
        return this == DISABLED;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: Jsr305State.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
