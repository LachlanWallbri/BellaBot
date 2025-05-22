package org.jetbrains.anko;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: AnkoContext.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 \u0013*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0001\u0013J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, m3961d2 = {"Lorg/jetbrains/anko/AnkoContext;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/ViewManager;", "ctx", "Landroid/content/Context;", "getCtx", "()Landroid/content/Context;", "owner", "getOwner", "()Ljava/lang/Object;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "removeView", "", "updateViewLayout", "params", "Landroid/view/ViewGroup$LayoutParams;", "Companion", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
@AnkoContextDslMarker
/* loaded from: classes2.dex */
public interface AnkoContext<T> extends ViewManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    Context getCtx();

    T getOwner();

    View getView();

    @Override // android.view.ViewManager
    void removeView(View view);

    @Override // android.view.ViewManager
    void updateViewLayout(View view, ViewGroup.LayoutParams params);

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* compiled from: AnkoContext.kt */
    @Metadata(m3959bv = {1, 0, 1}, m3962k = 3, m3963mv = {1, 1, 5})
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        public static <T> void updateViewLayout(AnkoContext<? extends T> ankoContext, View view, ViewGroup.LayoutParams params) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(params, "params");
            throw new UnsupportedOperationException();
        }

        public static <T> void removeView(AnkoContext<? extends T> ankoContext, View view) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            throw new UnsupportedOperationException();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* compiled from: AnkoContext.kt */
    @Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00052\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u001e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nJ#\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0001\u0010\u0005*\u00020\r2\u0006\u0010\b\u001a\u0002H\u0005¢\u0006\u0002\u0010\u000eJ1\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00052\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006\u0010"}, m3961d2 = {"Lorg/jetbrains/anko/AnkoContext$Companion;", "", "()V", "create", "Lorg/jetbrains/anko/AnkoContext;", ExifInterface.GPS_DIRECTION_TRUE, "ctx", "Landroid/content/Context;", "owner", "setContentView", "", "(Landroid/content/Context;Ljava/lang/Object;Z)Lorg/jetbrains/anko/AnkoContext;", "createDelegate", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)Lorg/jetbrains/anko/AnkoContext;", "createReusable", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* bridge */ /* synthetic */ AnkoContext create$default(Companion companion, Context context, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.create(context, z);
        }

        public final AnkoContext<Context> create(Context ctx, boolean setContentView) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new AnkoContextImpl(ctx, ctx, setContentView);
        }

        public static /* bridge */ /* synthetic */ AnkoContext createReusable$default(Companion companion, Context context, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.createReusable(context, z);
        }

        public final AnkoContext<Context> createReusable(Context ctx, boolean setContentView) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ReusableAnkoContext(ctx, ctx, setContentView);
        }

        public static /* bridge */ /* synthetic */ AnkoContext create$default(Companion companion, Context context, Object obj, boolean z, int i, Object obj2) {
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.create(context, obj, z);
        }

        public final <T> AnkoContext<T> create(Context ctx, T owner, boolean setContentView) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new AnkoContextImpl(ctx, owner, setContentView);
        }

        public static /* bridge */ /* synthetic */ AnkoContext createReusable$default(Companion companion, Context context, Object obj, boolean z, int i, Object obj2) {
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.createReusable(context, obj, z);
        }

        public final <T> AnkoContext<T> createReusable(Context ctx, T owner, boolean setContentView) {
            Intrinsics.checkParameterIsNotNull(ctx, "ctx");
            return new ReusableAnkoContext(ctx, owner, setContentView);
        }

        /* JADX WARN: Incorrect types in method signature: <T:Landroid/view/ViewGroup;>(TT;)Lorg/jetbrains/anko/AnkoContext<TT;>; */
        public final AnkoContext createDelegate(ViewGroup owner) {
            Intrinsics.checkParameterIsNotNull(owner, "owner");
            return new DelegatingAnkoContext(owner);
        }
    }
}
