package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import org.apache.commons.io.FilenameUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: methodSignatureBuilding.kt */
/* loaded from: classes2.dex */
public final class SignatureBuildingComponents {
    public static final SignatureBuildingComponents INSTANCE = new SignatureBuildingComponents();

    private SignatureBuildingComponents() {
    }

    public final String javaLang(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return "java/lang/" + name;
    }

    public final String javaUtil(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return "java/util/" + name;
    }

    public final String javaFunction(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return "java/util/function/" + name;
    }

    public final LinkedHashSet<String> inJavaLang(String name, String... signatures) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(signatures, "signatures");
        return inClass(javaLang(name), (String[]) Arrays.copyOf(signatures, signatures.length));
    }

    public final LinkedHashSet<String> inJavaUtil(String name, String... signatures) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(signatures, "signatures");
        return inClass(javaUtil(name), (String[]) Arrays.copyOf(signatures, signatures.length));
    }

    public final LinkedHashSet<String> inClass(String internalName, String... signatures) {
        Intrinsics.checkParameterIsNotNull(internalName, "internalName");
        Intrinsics.checkParameterIsNotNull(signatures, "signatures");
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        for (String str : signatures) {
            linkedHashSet.add(internalName + FilenameUtils.EXTENSION_SEPARATOR + str);
        }
        return linkedHashSet;
    }

    public final String signature(ClassDescriptor classDescriptor, String jvmDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        Intrinsics.checkParameterIsNotNull(jvmDescriptor, "jvmDescriptor");
        return signature(MethodSignatureMappingKt.getInternalName(classDescriptor), jvmDescriptor);
    }

    public final String signature(String internalName, String jvmDescriptor) {
        Intrinsics.checkParameterIsNotNull(internalName, "internalName");
        Intrinsics.checkParameterIsNotNull(jvmDescriptor, "jvmDescriptor");
        return internalName + FilenameUtils.EXTENSION_SEPARATOR + jvmDescriptor;
    }

    public final String jvmDescriptor(String name, List<String> parameters, String ret) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
        Intrinsics.checkParameterIsNotNull(ret, "ret");
        return name + '(' + CollectionsKt.joinToString$default(parameters, "", null, null, 0, null, new Function1<String, String>() { // from class: kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents$jvmDescriptor$1
            @Override // kotlin.jvm.functions.Function1
            public final String invoke(String it) {
                String escapeClassName;
                Intrinsics.checkParameterIsNotNull(it, "it");
                escapeClassName = SignatureBuildingComponents.INSTANCE.escapeClassName(it);
                return escapeClassName;
            }
        }, 30, null) + ')' + escapeClassName(ret);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String escapeClassName(String str) {
        if (str.length() <= 1) {
            return str;
        }
        return Matrix.MATRIX_TYPE_RANDOM_LT + str + ';';
    }

    public final String[] constructors(String... signatures) {
        Intrinsics.checkParameterIsNotNull(signatures, "signatures");
        ArrayList arrayList = new ArrayList(signatures.length);
        for (String str : signatures) {
            arrayList.add("<init>(" + str + ")V");
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
