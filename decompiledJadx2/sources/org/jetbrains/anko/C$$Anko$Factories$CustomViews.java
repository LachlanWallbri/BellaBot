package org.jetbrains.anko;

import android.R;
import android.content.Context;
import android.widget.EditText;
import android.widget.ProgressBar;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: CustomViews.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\r0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, m3961d2 = {"org/jetbrains/anko/$$Anko$Factories$CustomViews", "", "()V", "EDIT_TEXT", "Lkotlin/Function1;", "Landroid/content/Context;", "Landroid/widget/EditText;", "getEDIT_TEXT", "()Lkotlin/jvm/functions/Function1;", "HORIZONTAL_PROGRESS_BAR_FACTORY", "Landroid/widget/ProgressBar;", "getHORIZONTAL_PROGRESS_BAR_FACTORY", "VERTICAL_LAYOUT_FACTORY", "Lorg/jetbrains/anko/_LinearLayout;", "getVERTICAL_LAYOUT_FACTORY", "platform-base_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* renamed from: org.jetbrains.anko.$$Anko$Factories$CustomViews, reason: invalid class name */
/* loaded from: classes9.dex */
public final class C$$Anko$Factories$CustomViews {
    public static final C$$Anko$Factories$CustomViews INSTANCE = new C$$Anko$Factories$CustomViews();
    private static final Function1<Context, _LinearLayout> VERTICAL_LAYOUT_FACTORY = new Function1<Context, _LinearLayout>() { // from class: org.jetbrains.anko.$$Anko$Factories$CustomViews$VERTICAL_LAYOUT_FACTORY$1
        @Override // kotlin.jvm.functions.Function1
        public final _LinearLayout invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            _LinearLayout _linearlayout = new _LinearLayout(ctx);
            _linearlayout.setOrientation(1);
            return _linearlayout;
        }
    };
    private static final Function1<Context, EditText> EDIT_TEXT = new Function1<Context, EditText>() { // from class: org.jetbrains.anko.$$Anko$Factories$CustomViews$EDIT_TEXT$1
        @Override // kotlin.jvm.functions.Function1
        public final EditText invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new EditText(ctx);
        }
    };
    private static final Function1<Context, ProgressBar> HORIZONTAL_PROGRESS_BAR_FACTORY = new Function1<Context, ProgressBar>() { // from class: org.jetbrains.anko.$$Anko$Factories$CustomViews$HORIZONTAL_PROGRESS_BAR_FACTORY$1
        @Override // kotlin.jvm.functions.Function1
        public final ProgressBar invoke(Context ctx) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ProgressBar(ctx, null, R.attr.progressBarStyleHorizontal);
        }
    };

    private C$$Anko$Factories$CustomViews() {
    }

    public final Function1<Context, _LinearLayout> getVERTICAL_LAYOUT_FACTORY() {
        return VERTICAL_LAYOUT_FACTORY;
    }

    public final Function1<Context, EditText> getEDIT_TEXT() {
        return EDIT_TEXT;
    }

    public final Function1<Context, ProgressBar> getHORIZONTAL_PROGRESS_BAR_FACTORY() {
        return HORIZONTAL_PROGRESS_BAR_FACTORY;
    }
}
