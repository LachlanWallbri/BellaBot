package androidx.navigation.p002ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.customview.widget.Openable;
import androidx.navigation.FloatingWindow;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
abstract class AbstractAppBarOnDestinationChangedListener implements NavController.OnDestinationChangedListener {
    private ValueAnimator mAnimator;
    private DrawerArrowDrawable mArrowDrawable;
    private final Context mContext;
    private final WeakReference<Openable> mOpenableLayoutWeakReference;
    private final Set<Integer> mTopLevelDestinations;

    protected abstract void setNavigationIcon(Drawable drawable, int i);

    protected abstract void setTitle(CharSequence charSequence);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractAppBarOnDestinationChangedListener(Context context, AppBarConfiguration appBarConfiguration) {
        this.mContext = context;
        this.mTopLevelDestinations = appBarConfiguration.getTopLevelDestinations();
        Openable openableLayout = appBarConfiguration.getOpenableLayout();
        if (openableLayout != null) {
            this.mOpenableLayoutWeakReference = new WeakReference<>(openableLayout);
        } else {
            this.mOpenableLayoutWeakReference = null;
        }
    }

    @Override // androidx.navigation.NavController.OnDestinationChangedListener
    public void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle) {
        if (navDestination instanceof FloatingWindow) {
            return;
        }
        WeakReference<Openable> weakReference = this.mOpenableLayoutWeakReference;
        Openable openable = weakReference != null ? weakReference.get() : null;
        if (this.mOpenableLayoutWeakReference != null && openable == null) {
            navController.removeOnDestinationChangedListener(this);
            return;
        }
        CharSequence label = navDestination.getLabel();
        if (label != null) {
            StringBuffer stringBuffer = new StringBuffer();
            Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(label);
            while (matcher.find()) {
                String group = matcher.group(1);
                if (bundle != null && bundle.containsKey(group)) {
                    matcher.appendReplacement(stringBuffer, "");
                    stringBuffer.append(bundle.get(group).toString());
                } else {
                    throw new IllegalArgumentException("Could not find " + group + " in " + bundle + " to fill label " + ((Object) label));
                }
            }
            matcher.appendTail(stringBuffer);
            setTitle(stringBuffer);
        }
        boolean matchDestinations = NavigationUI.matchDestinations(navDestination, this.mTopLevelDestinations);
        boolean z = false;
        if (openable == null && matchDestinations) {
            setNavigationIcon(null, 0);
            return;
        }
        if (openable != null && matchDestinations) {
            z = true;
        }
        setActionBarUpIndicator(z);
    }

    private void setActionBarUpIndicator(boolean z) {
        boolean z2;
        int i;
        if (this.mArrowDrawable == null) {
            this.mArrowDrawable = new DrawerArrowDrawable(this.mContext);
            z2 = false;
        } else {
            z2 = true;
        }
        DrawerArrowDrawable drawerArrowDrawable = this.mArrowDrawable;
        if (z) {
            i = C0542R.string.nav_app_bar_open_drawer_description;
        } else {
            i = C0542R.string.nav_app_bar_navigate_up_description;
        }
        setNavigationIcon(drawerArrowDrawable, i);
        float f = z ? 0.0f : 1.0f;
        if (z2) {
            float progress = this.mArrowDrawable.getProgress();
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.mAnimator = ObjectAnimator.ofFloat(this.mArrowDrawable, "progress", progress, f);
            this.mAnimator.start();
            return;
        }
        this.mArrowDrawable.setProgress(f);
    }
}
