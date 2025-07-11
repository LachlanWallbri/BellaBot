package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.Navigator;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Navigator.Name("NoOp")
/* loaded from: classes.dex */
public class NoOpNavigator extends Navigator<NavDestination> {
    @Override // androidx.navigation.Navigator
    public NavDestination navigate(NavDestination navDestination, Bundle bundle, NavOptions navOptions, Navigator.Extras extras) {
        return navDestination;
    }

    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        return true;
    }

    @Override // androidx.navigation.Navigator
    public NavDestination createDestination() {
        return new NavDestination(this);
    }
}
