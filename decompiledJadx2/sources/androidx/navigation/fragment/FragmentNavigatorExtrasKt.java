package androidx.navigation.fragment;

import android.view.View;
import androidx.navigation.fragment.FragmentNavigator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: FragmentNavigatorExtras.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003\"\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\u0010\u0007¨\u0006\b"}, m3961d2 = {"FragmentNavigatorExtras", "Landroidx/navigation/fragment/FragmentNavigator$Extras;", "sharedElements", "", "Lkotlin/Pair;", "Landroid/view/View;", "", "([Lkotlin/Pair;)Landroidx/navigation/fragment/FragmentNavigator$Extras;", "navigation-fragment-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class FragmentNavigatorExtrasKt {
    public static final FragmentNavigator.Extras FragmentNavigatorExtras(Pair<? extends View, String>... sharedElements) {
        Intrinsics.checkParameterIsNotNull(sharedElements, "sharedElements");
        FragmentNavigator.Extras.Builder builder = new FragmentNavigator.Extras.Builder();
        for (Pair<? extends View, String> pair : sharedElements) {
            builder.addSharedElement(pair.component1(), pair.component2());
        }
        FragmentNavigator.Extras build = builder.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "FragmentNavigator.Extras…      }\n        }.build()");
        return build;
    }
}
