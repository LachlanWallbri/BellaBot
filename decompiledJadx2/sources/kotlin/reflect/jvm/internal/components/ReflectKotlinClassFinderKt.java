package kotlin.reflect.jvm.internal.components;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: ReflectKotlinClassFinder.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, m3961d2 = {"toRuntimeFqName", "", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "descriptors.runtime"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class ReflectKotlinClassFinderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String toRuntimeFqName(ClassId classId) {
        String asString = classId.getRelativeClassName().asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "relativeClassName.asString()");
        String replace$default = StringsKt.replace$default(asString, FilenameUtils.EXTENSION_SEPARATOR, Typography.dollar, false, 4, (Object) null);
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "packageFqName");
        if (packageFqName.isRoot()) {
            return replace$default;
        }
        return classId.getPackageFqName() + FilenameUtils.EXTENSION_SEPARATOR + replace$default;
    }
}
