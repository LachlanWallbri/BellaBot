package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: AnnotationUseSiteTarget.kt */
/* loaded from: classes2.dex */
public enum AnnotationUseSiteTarget {
    FIELD(null, 1, null),
    FILE(null, 1, null),
    PROPERTY(0 == true ? 1 : 0, 1, null),
    PROPERTY_GETTER(TmpConstant.PROPERTY_IDENTIFIER_GET),
    PROPERTY_SETTER(TmpConstant.PROPERTY_IDENTIFIER_SET),
    RECEIVER(0 == true ? 1 : 0, 1, null),
    CONSTRUCTOR_PARAMETER("param"),
    SETTER_PARAMETER("setparam"),
    PROPERTY_DELEGATE_FIELD("delegate");

    private final String renderName;

    AnnotationUseSiteTarget(String str) {
        if (str == null) {
            String name = name();
            if (name == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            str = name.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toLowerCase()");
        }
        this.renderName = str;
    }

    /* synthetic */ AnnotationUseSiteTarget(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (String) null : str);
    }

    public final String getRenderName() {
        return this.renderName;
    }
}
