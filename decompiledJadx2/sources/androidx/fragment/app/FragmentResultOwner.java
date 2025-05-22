package androidx.fragment.app;

import android.os.Bundle;
import androidx.lifecycle.LifecycleOwner;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface FragmentResultOwner {
    void clearFragmentResult(String str);

    void clearFragmentResultListener(String str);

    void setFragmentResult(String str, Bundle bundle);

    void setFragmentResultListener(String str, LifecycleOwner lifecycleOwner, FragmentResultListener fragmentResultListener);
}
