package androidx.transition;

import android.view.View;
import android.view.ViewGroup;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
interface GhostView {
    void reserveEndViewTransition(ViewGroup viewGroup, View view);

    void setVisibility(int i);
}
