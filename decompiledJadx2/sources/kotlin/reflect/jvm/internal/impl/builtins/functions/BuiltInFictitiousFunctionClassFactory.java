package kotlin.reflect.jvm.internal.impl.builtins.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionInterfacePackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
/* loaded from: classes2.dex */
public final class BuiltInFictitiousFunctionClassFactory implements ClassDescriptorFactory {
    public static final Companion Companion = new Companion(null);
    private final ModuleDescriptor module;
    private final StorageManager storageManager;

    public BuiltInFictitiousFunctionClassFactory(StorageManager storageManager, ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(module, "module");
        this.storageManager = storageManager;
        this.module = module;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
    /* loaded from: classes2.dex */
    public static final class KindWithArity {
        private final int arity;
        private final FunctionClassDescriptor.Kind kind;

        public final FunctionClassDescriptor.Kind component1() {
            return this.kind;
        }

        public final int component2() {
            return this.arity;
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof KindWithArity) {
                    KindWithArity kindWithArity = (KindWithArity) obj;
                    if (Intrinsics.areEqual(this.kind, kindWithArity.kind)) {
                        if (this.arity == kindWithArity.arity) {
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            FunctionClassDescriptor.Kind kind = this.kind;
            return ((kind != null ? kind.hashCode() : 0) * 31) + this.arity;
        }

        public String toString() {
            return "KindWithArity(kind=" + this.kind + ", arity=" + this.arity + ")";
        }

        public KindWithArity(FunctionClassDescriptor.Kind kind, int i) {
            Intrinsics.checkParameterIsNotNull(kind, "kind");
            this.kind = kind;
            this.arity = i;
        }

        public final FunctionClassDescriptor.Kind getKind() {
            return this.kind;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final KindWithArity parseClassName(String str, FqName fqName) {
            FunctionClassDescriptor.Kind byClassNamePrefix = FunctionClassDescriptor.Kind.Companion.byClassNamePrefix(fqName, str);
            if (byClassNamePrefix == null) {
                return null;
            }
            Companion companion = this;
            int length = byClassNamePrefix.getClassNamePrefix().length();
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String substring = str.substring(length);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            Integer num = companion.toInt(substring);
            if (num != null) {
                return new KindWithArity(byClassNamePrefix, num.intValue());
            }
            return null;
        }

        @JvmStatic
        public final FunctionClassDescriptor.Kind getFunctionalClassKind(String className, FqName packageFqName) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
            KindWithArity parseClassName = parseClassName(className, packageFqName);
            if (parseClassName != null) {
                return parseClassName.getKind();
            }
            return null;
        }

        private final Integer toInt(String str) {
            if (str.length() == 0) {
                return null;
            }
            int length = str.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                int charAt = str.charAt(i2) - '0';
                if (charAt < 0 || 9 < charAt) {
                    return null;
                }
                i = (i * 10) + charAt;
            }
            return Integer.valueOf(i);
        }
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public boolean shouldCreateClass(FqName packageFqName, Name name) {
        Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
        Intrinsics.checkParameterIsNotNull(name, "name");
        String asString = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
        return (StringsKt.startsWith$default(asString, "Function", false, 2, (Object) null) || StringsKt.startsWith$default(asString, "KFunction", false, 2, (Object) null) || StringsKt.startsWith$default(asString, "SuspendFunction", false, 2, (Object) null) || StringsKt.startsWith$default(asString, "KSuspendFunction", false, 2, (Object) null)) && Companion.parseClassName(asString, packageFqName) != null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public ClassDescriptor createClass(ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        if (!classId.isLocal() && !classId.isNestedClass()) {
            String asString = classId.getRelativeClassName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "classId.relativeClassName.asString()");
            if (!StringsKt.contains$default((CharSequence) asString, (CharSequence) "Function", false, 2, (Object) null)) {
                return null;
            }
            FqName packageFqName = classId.getPackageFqName();
            Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
            KindWithArity parseClassName = Companion.parseClassName(asString, packageFqName);
            if (parseClassName != null) {
                FunctionClassDescriptor.Kind component1 = parseClassName.component1();
                int component2 = parseClassName.component2();
                List<PackageFragmentDescriptor> fragments = this.module.getPackage(packageFqName).getFragments();
                ArrayList arrayList = new ArrayList();
                for (Object obj : fragments) {
                    if (obj instanceof BuiltInsPackageFragment) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList();
                for (Object obj2 : arrayList2) {
                    if (obj2 instanceof FunctionInterfacePackageFragment) {
                        arrayList3.add(obj2);
                    }
                }
                Object obj3 = (FunctionInterfacePackageFragment) CollectionsKt.firstOrNull((List) arrayList3);
                if (obj3 == null) {
                    obj3 = CollectionsKt.first((List<? extends Object>) arrayList2);
                }
                return new FunctionClassDescriptor(this.storageManager, (BuiltInsPackageFragment) obj3, component1, component2);
            }
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public Collection<ClassDescriptor> getAllContributedClassesIfPossible(FqName packageFqName) {
        Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
        return SetsKt.emptySet();
    }
}
