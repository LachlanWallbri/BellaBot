package org.jetbrains.anko;

import android.view.View;
import android.widget.RelativeLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: RelativeLayoutLayoutParamsHelpers.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0017\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\r\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\n\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u000b\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\f\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\r\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\u0017\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0012\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\r\u0010\u0012\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u0014\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u001b\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b\u001a\u0017\u0010\u001d\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b\u001a\u0015\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0017\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0017\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0086\b¨\u0006 "}, m3961d2 = {"above", "", "Landroid/widget/RelativeLayout$LayoutParams;", "view", "Landroid/view/View;", "id", "", "alignEnd", "alignParentBottom", "alignParentEnd", "alignParentLeft", "alignParentRight", "alignParentStart", "alignParentTop", "alignStart", "baselineOf", "below", "bottomOf", "centerHorizontally", "centerInParent", "centerVertically", "endOf", "leftOf", "rightOf", "sameBottom", "sameEnd", "sameLeft", "sameRight", "sameStart", "sameTop", "startOf", "topOf", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class RelativeLayoutLayoutParamsHelpersKt {
    public static final void topOf(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(2, id);
    }

    public static final void above(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(2, id);
    }

    public static final void bottomOf(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(3, id);
    }

    public static final void below(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(3, id);
    }

    public static final void leftOf(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(0, id);
    }

    public static final void startOf(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(16, id);
    }

    public static final void rightOf(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(1, id);
    }

    public static final void endOf(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(17, id);
    }

    public static final void sameLeft(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(5, id);
    }

    public static final void sameStart(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(18, id);
    }

    public static final void sameTop(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(6, id);
    }

    public static final void sameRight(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(7, id);
    }

    public static final void sameEnd(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(19, id);
    }

    public static final void sameBottom(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(8, id);
    }

    public static final void topOf(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(2, i);
    }

    public static final void above(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(2, i);
    }

    public static final void below(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(3, i);
    }

    public static final void bottomOf(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(3, i);
    }

    public static final void leftOf(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(0, i);
    }

    public static final void startOf(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(16, i);
    }

    public static final void rightOf(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(1, i);
    }

    public static final void endOf(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(17, i);
    }

    public static final void sameLeft(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(5, i);
    }

    public static final void sameStart(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(18, i);
    }

    public static final void sameTop(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(6, i);
    }

    public static final void sameRight(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(7, i);
    }

    public static final void sameEnd(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(19, i);
    }

    public static final void sameBottom(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(8, i);
    }

    public static final void alignStart(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(18, i);
    }

    public static final void alignEnd(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(19, i);
    }

    public static final void alignParentTop(RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(10);
    }

    public static final void alignParentRight(RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(11);
    }

    public static final void alignParentBottom(RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(12);
    }

    public static final void alignParentLeft(RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(9);
    }

    public static final void centerHorizontally(RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(14);
    }

    public static final void centerVertically(RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(15);
    }

    public static final void centerInParent(RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(13);
    }

    public static final void alignParentStart(RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(20);
    }

    public static final void alignParentEnd(RelativeLayout.LayoutParams receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(21);
    }

    public static final void baselineOf(RelativeLayout.LayoutParams receiver, View view) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id == -1) {
            throw new AnkoException("Id is not set for " + view);
        }
        receiver.addRule(4, id);
    }

    public static final void baselineOf(RelativeLayout.LayoutParams receiver, int i) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        receiver.addRule(4, i);
    }
}
