package androidx.navigation.p002ui;

import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.p002ui.AppBarConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: NavController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"navigateUp", "", "Landroidx/navigation/NavController;", "drawerLayout", "Landroidx/customview/widget/Openable;", "appBarConfiguration", "Landroidx/navigation/ui/AppBarConfiguration;", "navigation-ui-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class NavControllerKt {
    public static final boolean navigateUp(NavController navigateUp, Openable openable) {
        Intrinsics.checkParameterIsNotNull(navigateUp, "$this$navigateUp");
        NavGraph graph = navigateUp.getGraph();
        Intrinsics.checkExpressionValueIsNotNull(graph, "graph");
        AppBarConfigurationKt$AppBarConfiguration$1 appBarConfigurationKt$AppBarConfiguration$1 = AppBarConfigurationKt$AppBarConfiguration$1.INSTANCE;
        AppBarConfiguration build = new AppBarConfiguration.Builder(graph).setOpenableLayout(openable).setFallbackOnNavigateUpListener((AppBarConfiguration.OnNavigateUpListener) (appBarConfigurationKt$AppBarConfiguration$1 != null ? new C0535x56421ee5(appBarConfigurationKt$AppBarConfiguration$1) : appBarConfigurationKt$AppBarConfiguration$1)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        return NavigationUI.navigateUp(navigateUp, build);
    }

    public static final boolean navigateUp(NavController navigateUp, AppBarConfiguration appBarConfiguration) {
        Intrinsics.checkParameterIsNotNull(navigateUp, "$this$navigateUp");
        Intrinsics.checkParameterIsNotNull(appBarConfiguration, "appBarConfiguration");
        return NavigationUI.navigateUp(navigateUp, appBarConfiguration);
    }
}
