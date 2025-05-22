package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ANNOTATION_CLASS' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* compiled from: KotlinTarget.kt */
/* loaded from: classes2.dex */
public final class KotlinTarget {
    private static final /* synthetic */ KotlinTarget[] $VALUES;
    private static final Set<KotlinTarget> ALL_TARGET_SET;
    public static final KotlinTarget ANNOTATION_CLASS;
    public static final KotlinTarget ANONYMOUS_FUNCTION;
    public static final KotlinTarget CLASS;
    public static final KotlinTarget CLASS_ONLY;
    public static final KotlinTarget COMPANION_OBJECT;
    public static final KotlinTarget CONSTRUCTOR;
    public static final Companion Companion;
    private static final Set<KotlinTarget> DEFAULT_TARGET_SET;
    public static final KotlinTarget DESTRUCTURING_DECLARATION;
    public static final KotlinTarget ENUM_CLASS;
    public static final KotlinTarget ENUM_ENTRY;
    public static final KotlinTarget EXPRESSION;
    public static final KotlinTarget FIELD;
    public static final KotlinTarget FILE;
    public static final KotlinTarget FUNCTION;
    public static final KotlinTarget INITIALIZER;
    public static final KotlinTarget INTERFACE;
    public static final KotlinTarget LAMBDA_EXPRESSION;
    public static final KotlinTarget LOCAL_CLASS;
    public static final KotlinTarget LOCAL_FUNCTION;
    public static final KotlinTarget LOCAL_VARIABLE;
    public static final KotlinTarget MEMBER_FUNCTION;
    public static final KotlinTarget MEMBER_PROPERTY;
    public static final KotlinTarget MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE;
    public static final KotlinTarget MEMBER_PROPERTY_WITH_BACKING_FIELD;
    public static final KotlinTarget MEMBER_PROPERTY_WITH_DELEGATE;
    public static final KotlinTarget OBJECT;
    public static final KotlinTarget OBJECT_LITERAL;
    public static final KotlinTarget PROPERTY;
    public static final KotlinTarget PROPERTY_GETTER;
    public static final KotlinTarget PROPERTY_PARAMETER;
    public static final KotlinTarget PROPERTY_SETTER;
    public static final KotlinTarget STAR_PROJECTION;
    public static final KotlinTarget TOP_LEVEL_FUNCTION;
    public static final KotlinTarget TOP_LEVEL_PROPERTY;
    public static final KotlinTarget TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE;
    public static final KotlinTarget TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD;
    public static final KotlinTarget TOP_LEVEL_PROPERTY_WITH_DELEGATE;
    public static final KotlinTarget TYPE;
    public static final KotlinTarget TYPEALIAS;
    public static final KotlinTarget TYPE_PARAMETER;
    public static final KotlinTarget TYPE_PROJECTION;
    private static final Map<AnnotationUseSiteTarget, KotlinTarget> USE_SITE_MAPPING;
    public static final KotlinTarget VALUE_PARAMETER;
    private static final HashMap<String, KotlinTarget> map;
    private final String description;
    private final boolean isDefault;

    public static KotlinTarget valueOf(String str) {
        return (KotlinTarget) Enum.valueOf(KotlinTarget.class, str);
    }

    public static KotlinTarget[] values() {
        return (KotlinTarget[]) $VALUES.clone();
    }

    private KotlinTarget(String str, int i, String str2, boolean z) {
        this.description = str2;
        this.isDefault = z;
    }

    /* synthetic */ KotlinTarget(String str, int i, String str2, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, (i2 & 2) != 0 ? true : z);
    }

    static {
        KotlinTarget kotlinTarget = new KotlinTarget("CLASS", 0, Name.LABEL, false, 2, null);
        CLASS = kotlinTarget;
        DefaultConstructorMarker defaultConstructorMarker = null;
        KotlinTarget kotlinTarget2 = new KotlinTarget("ANNOTATION_CLASS", 1, "annotation class", false, 2, defaultConstructorMarker);
        ANNOTATION_CLASS = kotlinTarget2;
        KotlinTarget kotlinTarget3 = new KotlinTarget("TYPE_PARAMETER", 2, "type parameter", false);
        TYPE_PARAMETER = kotlinTarget3;
        DefaultConstructorMarker defaultConstructorMarker2 = null;
        KotlinTarget kotlinTarget4 = new KotlinTarget("PROPERTY", 3, "property", false, 2, defaultConstructorMarker2);
        PROPERTY = kotlinTarget4;
        KotlinTarget kotlinTarget5 = new KotlinTarget("FIELD", 4, "field", false, 2, null);
        FIELD = kotlinTarget5;
        KotlinTarget kotlinTarget6 = new KotlinTarget("LOCAL_VARIABLE", 5, "local variable", false, 2, defaultConstructorMarker2);
        LOCAL_VARIABLE = kotlinTarget6;
        KotlinTarget kotlinTarget7 = new KotlinTarget("VALUE_PARAMETER", 6, "value parameter", false, 2, null);
        VALUE_PARAMETER = kotlinTarget7;
        KotlinTarget kotlinTarget8 = new KotlinTarget("CONSTRUCTOR", 7, "constructor", false, 2, defaultConstructorMarker);
        CONSTRUCTOR = kotlinTarget8;
        KotlinTarget kotlinTarget9 = new KotlinTarget("FUNCTION", 8, "function", false, 2, null);
        FUNCTION = kotlinTarget9;
        KotlinTarget kotlinTarget10 = new KotlinTarget("PROPERTY_GETTER", 9, "getter", false, 2, null);
        PROPERTY_GETTER = kotlinTarget10;
        KotlinTarget kotlinTarget11 = new KotlinTarget("PROPERTY_SETTER", 10, "setter", false, 2, null);
        PROPERTY_SETTER = kotlinTarget11;
        KotlinTarget kotlinTarget12 = new KotlinTarget("TYPE", 11, "type usage", false);
        TYPE = kotlinTarget12;
        KotlinTarget kotlinTarget13 = new KotlinTarget("EXPRESSION", 12, "expression", false);
        EXPRESSION = kotlinTarget13;
        KotlinTarget kotlinTarget14 = new KotlinTarget("FILE", 13, "file", false);
        FILE = kotlinTarget14;
        KotlinTarget kotlinTarget15 = new KotlinTarget("TYPEALIAS", 14, "typealias", false);
        TYPEALIAS = kotlinTarget15;
        KotlinTarget kotlinTarget16 = new KotlinTarget("TYPE_PROJECTION", 15, "type projection", false);
        TYPE_PROJECTION = kotlinTarget16;
        KotlinTarget kotlinTarget17 = new KotlinTarget("STAR_PROJECTION", 16, "star projection", false);
        STAR_PROJECTION = kotlinTarget17;
        KotlinTarget kotlinTarget18 = new KotlinTarget("PROPERTY_PARAMETER", 17, "property constructor parameter", false);
        PROPERTY_PARAMETER = kotlinTarget18;
        KotlinTarget kotlinTarget19 = new KotlinTarget("CLASS_ONLY", 18, Name.LABEL, false);
        CLASS_ONLY = kotlinTarget19;
        KotlinTarget kotlinTarget20 = new KotlinTarget("OBJECT", 19, "object", false);
        OBJECT = kotlinTarget20;
        KotlinTarget kotlinTarget21 = new KotlinTarget("COMPANION_OBJECT", 20, "companion object", false);
        COMPANION_OBJECT = kotlinTarget21;
        KotlinTarget kotlinTarget22 = new KotlinTarget("INTERFACE", 21, "interface", false);
        INTERFACE = kotlinTarget22;
        KotlinTarget kotlinTarget23 = new KotlinTarget("ENUM_CLASS", 22, "enum class", false);
        ENUM_CLASS = kotlinTarget23;
        KotlinTarget kotlinTarget24 = new KotlinTarget("ENUM_ENTRY", 23, "enum entry", false);
        ENUM_ENTRY = kotlinTarget24;
        KotlinTarget kotlinTarget25 = new KotlinTarget("LOCAL_CLASS", 24, "local class", false);
        LOCAL_CLASS = kotlinTarget25;
        KotlinTarget kotlinTarget26 = new KotlinTarget("LOCAL_FUNCTION", 25, "local function", false);
        LOCAL_FUNCTION = kotlinTarget26;
        KotlinTarget kotlinTarget27 = new KotlinTarget("MEMBER_FUNCTION", 26, "member function", false);
        MEMBER_FUNCTION = kotlinTarget27;
        KotlinTarget kotlinTarget28 = new KotlinTarget("TOP_LEVEL_FUNCTION", 27, "top level function", false);
        TOP_LEVEL_FUNCTION = kotlinTarget28;
        KotlinTarget kotlinTarget29 = new KotlinTarget("MEMBER_PROPERTY", 28, "member property", false);
        MEMBER_PROPERTY = kotlinTarget29;
        KotlinTarget kotlinTarget30 = new KotlinTarget("MEMBER_PROPERTY_WITH_BACKING_FIELD", 29, "member property with backing field", false);
        MEMBER_PROPERTY_WITH_BACKING_FIELD = kotlinTarget30;
        KotlinTarget kotlinTarget31 = new KotlinTarget("MEMBER_PROPERTY_WITH_DELEGATE", 30, "member property with delegate", false);
        MEMBER_PROPERTY_WITH_DELEGATE = kotlinTarget31;
        KotlinTarget kotlinTarget32 = new KotlinTarget("MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE", 31, "member property without backing field or delegate", false);
        MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE = kotlinTarget32;
        KotlinTarget kotlinTarget33 = new KotlinTarget("TOP_LEVEL_PROPERTY", 32, "top level property", false);
        TOP_LEVEL_PROPERTY = kotlinTarget33;
        KotlinTarget kotlinTarget34 = new KotlinTarget("TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD", 33, "top level property with backing field", false);
        TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD = kotlinTarget34;
        KotlinTarget kotlinTarget35 = new KotlinTarget("TOP_LEVEL_PROPERTY_WITH_DELEGATE", 34, "top level property with delegate", false);
        TOP_LEVEL_PROPERTY_WITH_DELEGATE = kotlinTarget35;
        KotlinTarget kotlinTarget36 = new KotlinTarget("TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE", 35, "top level property without backing field or delegate", false);
        TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE = kotlinTarget36;
        KotlinTarget kotlinTarget37 = new KotlinTarget("INITIALIZER", 36, "initializer", false);
        INITIALIZER = kotlinTarget37;
        KotlinTarget kotlinTarget38 = new KotlinTarget("DESTRUCTURING_DECLARATION", 37, "destructuring declaration", false);
        DESTRUCTURING_DECLARATION = kotlinTarget38;
        KotlinTarget kotlinTarget39 = new KotlinTarget("LAMBDA_EXPRESSION", 38, "lambda expression", false);
        LAMBDA_EXPRESSION = kotlinTarget39;
        KotlinTarget kotlinTarget40 = new KotlinTarget("ANONYMOUS_FUNCTION", 39, "anonymous function", false);
        ANONYMOUS_FUNCTION = kotlinTarget40;
        KotlinTarget kotlinTarget41 = new KotlinTarget("OBJECT_LITERAL", 40, "object literal", false);
        OBJECT_LITERAL = kotlinTarget41;
        $VALUES = new KotlinTarget[]{kotlinTarget, kotlinTarget2, kotlinTarget3, kotlinTarget4, kotlinTarget5, kotlinTarget6, kotlinTarget7, kotlinTarget8, kotlinTarget9, kotlinTarget10, kotlinTarget11, kotlinTarget12, kotlinTarget13, kotlinTarget14, kotlinTarget15, kotlinTarget16, kotlinTarget17, kotlinTarget18, kotlinTarget19, kotlinTarget20, kotlinTarget21, kotlinTarget22, kotlinTarget23, kotlinTarget24, kotlinTarget25, kotlinTarget26, kotlinTarget27, kotlinTarget28, kotlinTarget29, kotlinTarget30, kotlinTarget31, kotlinTarget32, kotlinTarget33, kotlinTarget34, kotlinTarget35, kotlinTarget36, kotlinTarget37, kotlinTarget38, kotlinTarget39, kotlinTarget40, kotlinTarget41};
        Companion = new Companion(null);
        map = new HashMap<>();
        for (KotlinTarget kotlinTarget42 : values()) {
            map.put(kotlinTarget42.name(), kotlinTarget42);
        }
        KotlinTarget[] values = values();
        ArrayList arrayList = new ArrayList();
        for (KotlinTarget kotlinTarget43 : values) {
            if (kotlinTarget43.isDefault) {
                arrayList.add(kotlinTarget43);
            }
        }
        DEFAULT_TARGET_SET = CollectionsKt.toSet(arrayList);
        ALL_TARGET_SET = ArraysKt.toSet(values());
        USE_SITE_MAPPING = MapsKt.mapOf(TuplesKt.m3968to(AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER, VALUE_PARAMETER), TuplesKt.m3968to(AnnotationUseSiteTarget.FIELD, FIELD), TuplesKt.m3968to(AnnotationUseSiteTarget.PROPERTY, PROPERTY), TuplesKt.m3968to(AnnotationUseSiteTarget.FILE, FILE), TuplesKt.m3968to(AnnotationUseSiteTarget.PROPERTY_GETTER, PROPERTY_GETTER), TuplesKt.m3968to(AnnotationUseSiteTarget.PROPERTY_SETTER, PROPERTY_SETTER), TuplesKt.m3968to(AnnotationUseSiteTarget.RECEIVER, VALUE_PARAMETER), TuplesKt.m3968to(AnnotationUseSiteTarget.SETTER_PARAMETER, VALUE_PARAMETER), TuplesKt.m3968to(AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD, FIELD));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: KotlinTarget.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
