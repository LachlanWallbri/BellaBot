package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.io.InputStream;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: KotlinMetadataFinder.kt */
/* loaded from: classes2.dex */
public interface KotlinMetadataFinder {
    InputStream findBuiltInsData(FqName fqName);
}
