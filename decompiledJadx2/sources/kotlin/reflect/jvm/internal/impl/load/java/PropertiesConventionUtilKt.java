package kotlin.reflect.jvm.internal.impl.load.java;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: propertiesConventionUtil.kt */
/* loaded from: classes2.dex */
public final class PropertiesConventionUtilKt {
    public static final Name propertyNameByGetMethodName(Name methodName) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Name propertyNameFromAccessorMethodName$default = propertyNameFromAccessorMethodName$default(methodName, TmpConstant.PROPERTY_IDENTIFIER_GET, false, null, 12, null);
        return propertyNameFromAccessorMethodName$default != null ? propertyNameFromAccessorMethodName$default : propertyNameFromAccessorMethodName$default(methodName, "is", false, null, 8, null);
    }

    public static final Name propertyNameBySetMethodName(Name methodName, boolean z) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        return propertyNameFromAccessorMethodName$default(methodName, TmpConstant.PROPERTY_IDENTIFIER_SET, false, z ? "is" : null, 4, null);
    }

    public static final List<Name> propertyNamesBySetMethodName(Name methodName) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        return CollectionsKt.listOfNotNull((Object[]) new Name[]{propertyNameBySetMethodName(methodName, false), propertyNameBySetMethodName(methodName, true)});
    }

    static /* synthetic */ Name propertyNameFromAccessorMethodName$default(Name name, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        return propertyNameFromAccessorMethodName(name, str, z, str2);
    }

    private static final Name propertyNameFromAccessorMethodName(Name name, String str, boolean z, String str2) {
        if (name.isSpecial()) {
            return null;
        }
        String identifier = name.getIdentifier();
        Intrinsics.checkExpressionValueIsNotNull(identifier, "methodName.identifier");
        if (!StringsKt.startsWith$default(identifier, str, false, 2, (Object) null) || identifier.length() == str.length()) {
            return null;
        }
        char charAt = identifier.charAt(str.length());
        if ('a' <= charAt && 'z' >= charAt) {
            return null;
        }
        if (str2 == null) {
            if (!z) {
                return name;
            }
            String decapitalizeSmartForCompiler = CapitalizeDecapitalizeKt.decapitalizeSmartForCompiler(StringsKt.removePrefix(identifier, (CharSequence) str), true);
            if (Name.isValidIdentifier(decapitalizeSmartForCompiler)) {
                return Name.identifier(decapitalizeSmartForCompiler);
            }
            return null;
        }
        if (_Assertions.ENABLED && !z) {
            throw new AssertionError("Assertion failed");
        }
        return Name.identifier(str2 + StringsKt.removePrefix(identifier, (CharSequence) str));
    }

    public static final List<Name> getPropertyNamesCandidatesByAccessorName(Name name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        String asString = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
        if (JvmAbi.isGetterName(asString)) {
            return CollectionsKt.listOfNotNull(propertyNameByGetMethodName(name));
        }
        if (JvmAbi.isSetterName(asString)) {
            return propertyNamesBySetMethodName(name);
        }
        return BuiltinSpecialProperties.INSTANCE.getPropertyNameCandidatesBySpecialGetterName(name);
    }
}
