package org.jetbrains.anko;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoContext;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: AnkoContext.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001c\u0010\u0013\u001a\u00020\u00142\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0014J\u0018\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\fH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, m3961d2 = {"Lorg/jetbrains/anko/AnkoContextImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lorg/jetbrains/anko/AnkoContext;", "ctx", "Landroid/content/Context;", "owner", "setContentView", "", "(Landroid/content/Context;Ljava/lang/Object;Z)V", "getCtx", "()Landroid/content/Context;", "myView", "Landroid/view/View;", "getOwner", "()Ljava/lang/Object;", "Ljava/lang/Object;", "view", "getView", "()Landroid/view/View;", "addView", "", "params", "Landroid/view/ViewGroup$LayoutParams;", "alreadyHasView", "doAddView", "context", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public class AnkoContextImpl<T> implements AnkoContext<T> {
    private final Context ctx;
    private View myView;
    private final T owner;
    private final boolean setContentView;

    public AnkoContextImpl(Context ctx, T t, boolean z) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        this.ctx = ctx;
        this.owner = t;
        this.setContentView = z;
    }

    @Override // org.jetbrains.anko.AnkoContext, android.view.ViewManager
    public void removeView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        AnkoContext.DefaultImpls.removeView(this, view);
    }

    @Override // org.jetbrains.anko.AnkoContext, android.view.ViewManager
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(params, "params");
        AnkoContext.DefaultImpls.updateViewLayout(this, view, params);
    }

    @Override // org.jetbrains.anko.AnkoContext
    public Context getCtx() {
        return this.ctx;
    }

    @Override // org.jetbrains.anko.AnkoContext
    public T getOwner() {
        return this.owner;
    }

    @Override // org.jetbrains.anko.AnkoContext
    public View getView() {
        View view = this.myView;
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("View was not set previously");
    }

    @Override // android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams params) {
        if (view == null) {
            return;
        }
        if (this.myView != null) {
            alreadyHasView();
        }
        this.myView = view;
        if (this.setContentView) {
            doAddView(getCtx(), view);
        }
    }

    private final void doAddView(Context context, View view) {
        if (context instanceof Activity) {
            ((Activity) context).setContentView(view);
        } else {
            if (!(context instanceof ContextWrapper)) {
                throw new IllegalStateException("Context is not an Activity, can't set content view");
            }
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            Intrinsics.checkExpressionValueIsNotNull(baseContext, "context.baseContext");
            doAddView(baseContext, view);
        }
    }

    protected void alreadyHasView() {
        throw new IllegalStateException("View is already set: " + this.myView);
    }
}
