package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.NumberWithRadix;
import kotlin.reflect.jvm.internal.impl.utils.NumbersKt;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: utils.kt */
/* loaded from: classes2.dex */
public final class UtilsKt {
    public static final JavaDefaultValue lexicalCastFrom(KotlinType lexicalCastFrom, String value) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(lexicalCastFrom, "$this$lexicalCastFrom");
        Intrinsics.checkParameterIsNotNull(value, "value");
        ClassifierDescriptor mo5460getDeclarationDescriptor = lexicalCastFrom.getConstructor().mo5460getDeclarationDescriptor();
        if (mo5460getDeclarationDescriptor instanceof ClassDescriptor) {
            ClassDescriptor classDescriptor = (ClassDescriptor) mo5460getDeclarationDescriptor;
            if (classDescriptor.getKind() == ClassKind.ENUM_CLASS) {
                MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
                Name identifier = Name.identifier(value);
                Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(value)");
                ClassifierDescriptor contributedClassifier = unsubstitutedInnerClassesScope.mo5462getContributedClassifier(identifier, NoLookupLocation.FROM_BACKEND);
                if (!(contributedClassifier instanceof ClassDescriptor)) {
                    return null;
                }
                ClassDescriptor classDescriptor2 = (ClassDescriptor) contributedClassifier;
                if (classDescriptor2.getKind() == ClassKind.ENUM_ENTRY) {
                    return new EnumEntry(classDescriptor2);
                }
                return null;
            }
        }
        KotlinType makeNotNullable = TypeUtilsKt.makeNotNullable(lexicalCastFrom);
        NumberWithRadix extractRadix = NumbersKt.extractRadix(value);
        String component1 = extractRadix.component1();
        int component2 = extractRadix.component2();
        try {
        } catch (IllegalArgumentException unused) {
            obj = null;
        }
        if (KotlinBuiltIns.isBoolean(makeNotNullable)) {
            obj = Boolean.valueOf(Boolean.parseBoolean(value));
        } else if (KotlinBuiltIns.isChar(makeNotNullable)) {
            obj = StringsKt.singleOrNull(value);
        } else if (KotlinBuiltIns.isByte(makeNotNullable)) {
            obj = StringsKt.toByteOrNull(component1, component2);
        } else if (KotlinBuiltIns.isShort(makeNotNullable)) {
            obj = StringsKt.toShortOrNull(component1, component2);
        } else if (KotlinBuiltIns.isInt(makeNotNullable)) {
            obj = StringsKt.toIntOrNull(component1, component2);
        } else if (KotlinBuiltIns.isLong(makeNotNullable)) {
            obj = StringsKt.toLongOrNull(component1, component2);
        } else if (KotlinBuiltIns.isFloat(makeNotNullable)) {
            obj = StringsKt.toFloatOrNull(value);
        } else if (KotlinBuiltIns.isDouble(makeNotNullable)) {
            obj = StringsKt.toDoubleOrNull(value);
        } else {
            if (KotlinBuiltIns.isString(makeNotNullable)) {
                obj = null;
            }
            obj = null;
        }
        if (obj != null) {
            return new Constant(obj);
        }
        return null;
    }
}
