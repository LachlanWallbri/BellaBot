package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: MemberScope.kt */
/* loaded from: classes2.dex */
public final class DescriptorKindFilter {
    public static final DescriptorKindFilter ALL;
    private static final int ALL_KINDS_MASK;
    public static final DescriptorKindFilter CALLABLES;
    private static final int CALLABLES_MASK;
    public static final DescriptorKindFilter CLASSIFIERS;
    private static final int CLASSIFIERS_MASK;
    public static final Companion Companion;
    private static final List<Companion.MaskToName> DEBUG_MASK_BIT_NAMES;
    private static final List<Companion.MaskToName> DEBUG_PREDEFINED_FILTERS_MASK_NAMES;
    public static final DescriptorKindFilter FUNCTIONS;
    private static final int FUNCTIONS_MASK;
    public static final DescriptorKindFilter NON_SINGLETON_CLASSIFIERS;
    private static final int NON_SINGLETON_CLASSIFIERS_MASK;
    public static final DescriptorKindFilter PACKAGES;
    private static final int PACKAGES_MASK;
    public static final DescriptorKindFilter SINGLETON_CLASSIFIERS;
    private static final int SINGLETON_CLASSIFIERS_MASK;
    public static final DescriptorKindFilter TYPE_ALIASES;
    private static final int TYPE_ALIASES_MASK;
    public static final DescriptorKindFilter VALUES;
    private static final int VALUES_MASK;
    public static final DescriptorKindFilter VARIABLES;
    private static final int VARIABLES_MASK;
    private static int nextMaskValue;
    private final List<DescriptorKindExclude> excludes;
    private final int kindMask;

    /* JADX WARN: Multi-variable type inference failed */
    public DescriptorKindFilter(int i, List<? extends DescriptorKindExclude> excludes) {
        Intrinsics.checkParameterIsNotNull(excludes, "excludes");
        this.excludes = excludes;
        Iterator it = excludes.iterator();
        while (it.hasNext()) {
            i &= ~((DescriptorKindExclude) it.next()).getFullyExcludedDescriptorKinds();
        }
        this.kindMask = i;
    }

    public /* synthetic */ DescriptorKindFilter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<DescriptorKindExclude> getExcludes() {
        return this.excludes;
    }

    public final int getKindMask() {
        return this.kindMask;
    }

    public final boolean acceptsKinds(int i) {
        return (i & this.kindMask) != 0;
    }

    public final DescriptorKindFilter restrictedToKindsOrNull(int i) {
        int i2 = i & this.kindMask;
        if (i2 == 0) {
            return null;
        }
        return new DescriptorKindFilter(i2, this.excludes);
    }

    public String toString() {
        Object obj;
        Iterator<T> it = DEBUG_PREDEFINED_FILTERS_MASK_NAMES.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Companion.MaskToName) obj).getMask() == this.kindMask) {
                break;
            }
        }
        Companion.MaskToName maskToName = (Companion.MaskToName) obj;
        String name = maskToName != null ? maskToName.getName() : null;
        if (name == null) {
            List<Companion.MaskToName> list = DEBUG_MASK_BIT_NAMES;
            ArrayList arrayList = new ArrayList();
            for (Companion.MaskToName maskToName2 : list) {
                String name2 = acceptsKinds(maskToName2.getMask()) ? maskToName2.getName() : null;
                if (name2 != null) {
                    arrayList.add(name2);
                }
            }
            name = CollectionsKt.joinToString$default(arrayList, " | ", null, null, 0, null, null, 62, null);
        }
        return "DescriptorKindFilter(" + name + ", " + this.excludes + ')';
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: MemberScope.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int nextMask() {
            int i = DescriptorKindFilter.nextMaskValue;
            DescriptorKindFilter.nextMaskValue <<= 1;
            return i;
        }

        public final int getNON_SINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.NON_SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getSINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getTYPE_ALIASES_MASK() {
            return DescriptorKindFilter.TYPE_ALIASES_MASK;
        }

        public final int getPACKAGES_MASK() {
            return DescriptorKindFilter.PACKAGES_MASK;
        }

        public final int getFUNCTIONS_MASK() {
            return DescriptorKindFilter.FUNCTIONS_MASK;
        }

        public final int getVARIABLES_MASK() {
            return DescriptorKindFilter.VARIABLES_MASK;
        }

        public final int getALL_KINDS_MASK() {
            return DescriptorKindFilter.ALL_KINDS_MASK;
        }

        public final int getCLASSIFIERS_MASK() {
            return DescriptorKindFilter.CLASSIFIERS_MASK;
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: MemberScope.kt */
        /* loaded from: classes2.dex */
        private static final class MaskToName {
            private final int mask;
            private final String name;

            public MaskToName(int i, String name) {
                Intrinsics.checkParameterIsNotNull(name, "name");
                this.mask = i;
                this.name = name;
            }

            public final int getMask() {
                return this.mask;
            }

            public final String getName() {
                return this.name;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Companion.MaskToName maskToName;
        Companion.MaskToName maskToName2;
        Companion companion = new Companion(null);
        Companion = companion;
        nextMaskValue = 1;
        NON_SINGLETON_CLASSIFIERS_MASK = companion.nextMask();
        SINGLETON_CLASSIFIERS_MASK = Companion.nextMask();
        TYPE_ALIASES_MASK = Companion.nextMask();
        PACKAGES_MASK = Companion.nextMask();
        FUNCTIONS_MASK = Companion.nextMask();
        VARIABLES_MASK = Companion.nextMask();
        ALL_KINDS_MASK = Companion.nextMask() - 1;
        int i = NON_SINGLETON_CLASSIFIERS_MASK;
        int i2 = SINGLETON_CLASSIFIERS_MASK;
        CLASSIFIERS_MASK = i | i2 | TYPE_ALIASES_MASK;
        int i3 = FUNCTIONS_MASK;
        int i4 = VARIABLES_MASK;
        VALUES_MASK = i2 | i3 | i4;
        CALLABLES_MASK = i3 | i4;
        int i5 = 2;
        ALL = new DescriptorKindFilter(ALL_KINDS_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        CALLABLES = new DescriptorKindFilter(CALLABLES_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        NON_SINGLETON_CLASSIFIERS = new DescriptorKindFilter(NON_SINGLETON_CLASSIFIERS_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        SINGLETON_CLASSIFIERS = new DescriptorKindFilter(SINGLETON_CLASSIFIERS_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        TYPE_ALIASES = new DescriptorKindFilter(TYPE_ALIASES_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        CLASSIFIERS = new DescriptorKindFilter(CLASSIFIERS_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        PACKAGES = new DescriptorKindFilter(PACKAGES_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        FUNCTIONS = new DescriptorKindFilter(FUNCTIONS_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        VARIABLES = new DescriptorKindFilter(VARIABLES_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        VALUES = new DescriptorKindFilter(VALUES_MASK, 0 == true ? 1 : 0, i5, 0 == true ? 1 : 0);
        Field[] fields = DescriptorKindFilter.class.getFields();
        Intrinsics.checkExpressionValueIsNotNull(fields, "T::class.java.fields");
        ArrayList<Field> arrayList = new ArrayList();
        for (Field it : fields) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            if (Modifier.isStatic(it.getModifiers())) {
                arrayList.add(it);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Field field : arrayList) {
            Object obj = field.get(null);
            if (!(obj instanceof DescriptorKindFilter)) {
                obj = null;
            }
            DescriptorKindFilter descriptorKindFilter = (DescriptorKindFilter) obj;
            if (descriptorKindFilter != null) {
                int i6 = descriptorKindFilter.kindMask;
                Intrinsics.checkExpressionValueIsNotNull(field, "field");
                String name = field.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "field.name");
                maskToName2 = new Companion.MaskToName(i6, name);
            } else {
                maskToName2 = null;
            }
            if (maskToName2 != null) {
                arrayList2.add(maskToName2);
            }
        }
        DEBUG_PREDEFINED_FILTERS_MASK_NAMES = CollectionsKt.toList(arrayList2);
        Field[] fields2 = DescriptorKindFilter.class.getFields();
        Intrinsics.checkExpressionValueIsNotNull(fields2, "T::class.java.fields");
        ArrayList arrayList3 = new ArrayList();
        for (Field it2 : fields2) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            if (Modifier.isStatic(it2.getModifiers())) {
                arrayList3.add(it2);
            }
        }
        ArrayList<Field> arrayList4 = new ArrayList();
        for (Object obj2 : arrayList3) {
            Field it3 = (Field) obj2;
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            if (Intrinsics.areEqual(it3.getType(), Integer.TYPE)) {
                arrayList4.add(obj2);
            }
        }
        ArrayList arrayList5 = new ArrayList();
        for (Field field2 : arrayList4) {
            Object obj3 = field2.get(null);
            if (obj3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            int intValue = ((Integer) obj3).intValue();
            if ((intValue == ((-intValue) & intValue)) == true) {
                Intrinsics.checkExpressionValueIsNotNull(field2, "field");
                String name2 = field2.getName();
                Intrinsics.checkExpressionValueIsNotNull(name2, "field.name");
                maskToName = new Companion.MaskToName(intValue, name2);
            } else {
                maskToName = null;
            }
            if (maskToName != null) {
                arrayList5.add(maskToName);
            }
        }
        DEBUG_MASK_BIT_NAMES = CollectionsKt.toList(arrayList5);
    }
}
