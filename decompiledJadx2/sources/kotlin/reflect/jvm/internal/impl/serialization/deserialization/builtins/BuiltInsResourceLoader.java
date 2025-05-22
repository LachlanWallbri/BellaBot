package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.InputStream;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: BuiltInsResourceLoader.kt */
/* loaded from: classes2.dex */
public final class BuiltInsResourceLoader {
    public final InputStream loadResource(String path) {
        InputStream resourceAsStream;
        Intrinsics.checkParameterIsNotNull(path, "path");
        ClassLoader classLoader = getClass().getClassLoader();
        return (classLoader == null || (resourceAsStream = classLoader.getResourceAsStream(path)) == null) ? ClassLoader.getSystemResourceAsStream(path) : resourceAsStream;
    }
}
