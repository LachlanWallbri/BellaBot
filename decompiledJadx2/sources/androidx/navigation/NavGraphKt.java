package androidx.navigation;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: NavGraph.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0086\u0002\u001a\u0017\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\n\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0086\n\u001a\u0015\u0010\n\u001a\u00020\b*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0086\n¨\u0006\f"}, m3961d2 = {"contains", "", "Landroidx/navigation/NavGraph;", "id", "", TmpConstant.PROPERTY_IDENTIFIER_GET, "Landroidx/navigation/NavDestination;", "minusAssign", "", "node", "plusAssign", "other", "navigation-common-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class NavGraphKt {
    public static final NavDestination get(NavGraph get, int i) {
        Intrinsics.checkParameterIsNotNull(get, "$this$get");
        NavDestination findNode = get.findNode(i);
        if (findNode != null) {
            return findNode;
        }
        throw new IllegalArgumentException("No destination for " + i + " was found in " + get);
    }

    public static final boolean contains(NavGraph contains, int i) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return contains.findNode(i) != null;
    }

    public static final void plusAssign(NavGraph plusAssign, NavDestination node) {
        Intrinsics.checkParameterIsNotNull(plusAssign, "$this$plusAssign");
        Intrinsics.checkParameterIsNotNull(node, "node");
        plusAssign.addDestination(node);
    }

    public static final void plusAssign(NavGraph plusAssign, NavGraph other) {
        Intrinsics.checkParameterIsNotNull(plusAssign, "$this$plusAssign");
        Intrinsics.checkParameterIsNotNull(other, "other");
        plusAssign.addAll(other);
    }

    public static final void minusAssign(NavGraph minusAssign, NavDestination node) {
        Intrinsics.checkParameterIsNotNull(minusAssign, "$this$minusAssign");
        Intrinsics.checkParameterIsNotNull(node, "node");
        minusAssign.remove(node);
    }
}
