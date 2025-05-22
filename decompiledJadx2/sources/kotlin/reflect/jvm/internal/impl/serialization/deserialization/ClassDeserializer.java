package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Iterator;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;

/* compiled from: ClassDeserializer.kt */
/* loaded from: classes2.dex */
public final class ClassDeserializer {
    private final Function1<ClassKey, ClassDescriptor> classes;
    private final DeserializationComponents components;
    public static final Companion Companion = new Companion(null);
    private static final Set<ClassId> BLACK_LIST = SetsKt.setOf(ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.cloneable.toSafe()));

    public ClassDeserializer(DeserializationComponents components) {
        Intrinsics.checkParameterIsNotNull(components, "components");
        this.components = components;
        this.classes = components.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1<ClassKey, ClassDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.serialization.deserialization.ClassDeserializer$classes$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final ClassDescriptor invoke(ClassDeserializer.ClassKey key) {
                ClassDescriptor createClass;
                Intrinsics.checkParameterIsNotNull(key, "key");
                createClass = ClassDeserializer.this.createClass(key);
                return createClass;
            }
        });
    }

    public static /* synthetic */ ClassDescriptor deserializeClass$default(ClassDeserializer classDeserializer, ClassId classId, ClassData classData, int i, Object obj) {
        if ((i & 2) != 0) {
            classData = (ClassData) null;
        }
        return classDeserializer.deserializeClass(classId, classData);
    }

    public final ClassDescriptor deserializeClass(ClassId classId, ClassData classData) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        return this.classes.invoke(new ClassKey(classId, classData));
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00bb A[EDGE_INSN: B:43:0x00bb->B:44:0x00bb BREAK  A[LOOP:1: B:34:0x0093->B:47:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[LOOP:1: B:34:0x0093->B:47:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final ClassDescriptor createClass(ClassKey classKey) {
        Object obj;
        DeserializationContext createContext;
        boolean z;
        ClassId classId = classKey.getClassId();
        Iterator<ClassDescriptorFactory> it = this.components.getFictitiousClassDescriptorFactories().iterator();
        while (it.hasNext()) {
            ClassDescriptor createClass = it.next().createClass(classId);
            if (createClass != null) {
                return createClass;
            }
        }
        if (BLACK_LIST.contains(classId)) {
            return null;
        }
        ClassData classData = classKey.getClassData();
        if (classData == null) {
            classData = this.components.getClassDataFinder().findClassData(classId);
        }
        if (classData != null) {
            NameResolver component1 = classData.component1();
            ProtoBuf.Class component2 = classData.component2();
            BinaryVersion component3 = classData.component3();
            SourceElement component4 = classData.component4();
            ClassId outerClassId = classId.getOuterClassId();
            if (outerClassId != null) {
                ClassDescriptor deserializeClass$default = deserializeClass$default(this, outerClassId, null, 2, null);
                if (!(deserializeClass$default instanceof DeserializedClassDescriptor)) {
                    deserializeClass$default = null;
                }
                DeserializedClassDescriptor deserializedClassDescriptor = (DeserializedClassDescriptor) deserializeClass$default;
                if (deserializedClassDescriptor == null) {
                    return null;
                }
                Name shortClassName = classId.getShortClassName();
                Intrinsics.checkExpressionValueIsNotNull(shortClassName, "classId.shortClassName");
                if (!deserializedClassDescriptor.hasNestedClass$deserialization(shortClassName)) {
                    return null;
                }
                createContext = deserializedClassDescriptor.getC();
            } else {
                PackageFragmentProvider packageFragmentProvider = this.components.getPackageFragmentProvider();
                FqName packageFqName = classId.getPackageFqName();
                Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
                Iterator<T> it2 = packageFragmentProvider.getPackageFragments(packageFqName).iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it2.next();
                    PackageFragmentDescriptor packageFragmentDescriptor = (PackageFragmentDescriptor) obj;
                    if (packageFragmentDescriptor instanceof DeserializedPackageFragment) {
                        Name shortClassName2 = classId.getShortClassName();
                        Intrinsics.checkExpressionValueIsNotNull(shortClassName2, "classId.shortClassName");
                        if (!((DeserializedPackageFragment) packageFragmentDescriptor).hasTopLevelClass(shortClassName2)) {
                            z = false;
                            if (!z) {
                                break;
                            }
                        }
                    }
                    z = true;
                    if (!z) {
                    }
                }
                PackageFragmentDescriptor packageFragmentDescriptor2 = (PackageFragmentDescriptor) obj;
                if (packageFragmentDescriptor2 != null) {
                    DeserializationComponents deserializationComponents = this.components;
                    ProtoBuf.TypeTable typeTable = component2.getTypeTable();
                    Intrinsics.checkExpressionValueIsNotNull(typeTable, "classProto.typeTable");
                    TypeTable typeTable2 = new TypeTable(typeTable);
                    VersionRequirementTable.Companion companion = VersionRequirementTable.Companion;
                    ProtoBuf.VersionRequirementTable versionRequirementTable = component2.getVersionRequirementTable();
                    Intrinsics.checkExpressionValueIsNotNull(versionRequirementTable, "classProto.versionRequirementTable");
                    createContext = deserializationComponents.createContext(packageFragmentDescriptor2, component1, typeTable2, companion.create(versionRequirementTable), component3, null);
                }
            }
            return new DeserializedClassDescriptor(createContext, component2, component1, component3, component4);
        }
        return null;
    }

    /* compiled from: ClassDeserializer.kt */
    /* loaded from: classes2.dex */
    public static final class ClassKey {
        private final ClassData classData;
        private final ClassId classId;

        public ClassKey(ClassId classId, ClassData classData) {
            Intrinsics.checkParameterIsNotNull(classId, "classId");
            this.classId = classId;
            this.classData = classData;
        }

        public final ClassData getClassData() {
            return this.classData;
        }

        public final ClassId getClassId() {
            return this.classId;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ClassKey) && Intrinsics.areEqual(this.classId, ((ClassKey) obj).classId);
        }

        public int hashCode() {
            return this.classId.hashCode();
        }
    }

    /* compiled from: ClassDeserializer.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Set<ClassId> getBLACK_LIST() {
            return ClassDeserializer.BLACK_LIST;
        }
    }
}
