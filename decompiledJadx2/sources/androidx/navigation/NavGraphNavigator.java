package androidx.navigation;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.navigation.Navigator;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Navigator.Name(NotificationCompat.CATEGORY_NAVIGATION)
/* loaded from: classes.dex */
public class NavGraphNavigator extends Navigator<NavGraph> {
    private final NavigatorProvider mNavigatorProvider;

    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        return true;
    }

    public NavGraphNavigator(NavigatorProvider navigatorProvider) {
        this.mNavigatorProvider = navigatorProvider;
    }

    @Override // androidx.navigation.Navigator
    public NavGraph createDestination() {
        return new NavGraph(this);
    }

    @Override // androidx.navigation.Navigator
    public NavDestination navigate(NavGraph navGraph, Bundle bundle, NavOptions navOptions, Navigator.Extras extras) {
        int startDestination = navGraph.getStartDestination();
        if (startDestination == 0) {
            throw new IllegalStateException("no start destination defined via app:startDestination for " + navGraph.getDisplayName());
        }
        NavDestination findNode = navGraph.findNode(startDestination, false);
        if (findNode == null) {
            throw new IllegalArgumentException("navigation destination " + navGraph.getStartDestDisplayName() + " is not a direct child of this NavGraph");
        }
        return this.mNavigatorProvider.getNavigator(findNode.getNavigatorName()).navigate(findNode, findNode.addInDefaultArgs(bundle), navOptions, extras);
    }
}
