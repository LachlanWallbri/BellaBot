package androidx.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes.dex */
public interface LayoutInflaterFactory {
    View onCreateView(View view, String str, Context context, AttributeSet attributeSet);
}
