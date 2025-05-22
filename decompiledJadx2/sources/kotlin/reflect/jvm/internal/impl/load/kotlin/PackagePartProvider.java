package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: PackagePartProvider.kt */
/* loaded from: classes2.dex */
public interface PackagePartProvider {
    List<String> findPackageParts(String str);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: PackagePartProvider.kt */
    /* loaded from: classes2.dex */
    public static final class Empty implements PackagePartProvider {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider
        public List<String> findPackageParts(String packageFqName) {
            Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
            return CollectionsKt.emptyList();
        }
    }
}
