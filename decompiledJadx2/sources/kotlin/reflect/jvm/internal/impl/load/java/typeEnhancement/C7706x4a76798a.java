package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: signatureEnhancement.kt */
/* renamed from: kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement$SignatureParts$extractQualifiersFromAnnotations$2 */
/* loaded from: classes2.dex */
final class C7706x4a76798a<T> extends Lambda implements Function2<T, T, T> {
    public static final C7706x4a76798a INSTANCE = new C7706x4a76798a();

    C7706x4a76798a() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public final <T> T invoke(T t, T t2) {
        if (t == null || t2 == null || Intrinsics.areEqual(t, t2)) {
            return t != null ? t : t2;
        }
        return null;
    }
}
