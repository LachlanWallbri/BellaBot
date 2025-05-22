package kotlin.reflect.jvm.internal.impl.resolve.deprecation;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: deprecation.kt */
/* loaded from: classes2.dex */
public final class DeprecationKt {
    private static final CallableDescriptor.UserDataKey<Object> DEPRECATED_FUNCTION_KEY = new CallableDescriptor.UserDataKey<Object>() { // from class: kotlin.reflect.jvm.internal.impl.resolve.deprecation.DeprecationKt$DEPRECATED_FUNCTION_KEY$1
    };

    public static final CallableDescriptor.UserDataKey<Object> getDEPRECATED_FUNCTION_KEY() {
        return DEPRECATED_FUNCTION_KEY;
    }
}
