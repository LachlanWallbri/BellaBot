package androidx.lifecycle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes.dex */
public class ViewModelStores {
    private ViewModelStores() {
    }

    @Deprecated
    /* renamed from: of */
    public static ViewModelStore m21of(FragmentActivity fragmentActivity) {
        return fragmentActivity.getViewModelStore();
    }

    @Deprecated
    /* renamed from: of */
    public static ViewModelStore m20of(Fragment fragment) {
        return fragment.getViewModelStore();
    }
}
