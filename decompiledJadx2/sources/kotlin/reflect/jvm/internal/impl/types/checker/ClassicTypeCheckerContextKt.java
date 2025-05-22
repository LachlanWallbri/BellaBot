package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.jvm.internal.Reflection;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: ClassicTypeCheckerContext.kt */
/* loaded from: classes2.dex */
public final class ClassicTypeCheckerContextKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String errorMessage(Object obj) {
        return "ClassicTypeCheckerContext couldn't handle " + Reflection.getOrCreateKotlinClass(obj.getClass()) + ' ' + obj;
    }
}
