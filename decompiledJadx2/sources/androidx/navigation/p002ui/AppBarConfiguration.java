package androidx.navigation.p002ui;

import android.view.Menu;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavGraph;
import java.util.HashSet;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class AppBarConfiguration {
    private final OnNavigateUpListener mFallbackOnNavigateUpListener;
    private final Openable mOpenableLayout;
    private final Set<Integer> mTopLevelDestinations;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public interface OnNavigateUpListener {
        boolean onNavigateUp();
    }

    private AppBarConfiguration(Set<Integer> set, Openable openable, OnNavigateUpListener onNavigateUpListener) {
        this.mTopLevelDestinations = set;
        this.mOpenableLayout = openable;
        this.mFallbackOnNavigateUpListener = onNavigateUpListener;
    }

    public Set<Integer> getTopLevelDestinations() {
        return this.mTopLevelDestinations;
    }

    public Openable getOpenableLayout() {
        return this.mOpenableLayout;
    }

    @Deprecated
    public DrawerLayout getDrawerLayout() {
        Object obj = this.mOpenableLayout;
        if (obj instanceof DrawerLayout) {
            return (DrawerLayout) obj;
        }
        return null;
    }

    public OnNavigateUpListener getFallbackOnNavigateUpListener() {
        return this.mFallbackOnNavigateUpListener;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static final class Builder {
        private OnNavigateUpListener mFallbackOnNavigateUpListener;
        private Openable mOpenableLayout;
        private final Set<Integer> mTopLevelDestinations = new HashSet();

        public Builder(NavGraph navGraph) {
            this.mTopLevelDestinations.add(Integer.valueOf(NavigationUI.findStartDestination(navGraph).getId()));
        }

        public Builder(Menu menu) {
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                this.mTopLevelDestinations.add(Integer.valueOf(menu.getItem(i).getItemId()));
            }
        }

        public Builder(int... iArr) {
            for (int i : iArr) {
                this.mTopLevelDestinations.add(Integer.valueOf(i));
            }
        }

        public Builder(Set<Integer> set) {
            this.mTopLevelDestinations.addAll(set);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Deprecated
        public Builder setDrawerLayout(DrawerLayout drawerLayout) {
            this.mOpenableLayout = drawerLayout;
            return this;
        }

        public Builder setOpenableLayout(Openable openable) {
            this.mOpenableLayout = openable;
            return this;
        }

        public Builder setFallbackOnNavigateUpListener(OnNavigateUpListener onNavigateUpListener) {
            this.mFallbackOnNavigateUpListener = onNavigateUpListener;
            return this;
        }

        public AppBarConfiguration build() {
            return new AppBarConfiguration(this.mTopLevelDestinations, this.mOpenableLayout, this.mFallbackOnNavigateUpListener);
        }
    }
}
