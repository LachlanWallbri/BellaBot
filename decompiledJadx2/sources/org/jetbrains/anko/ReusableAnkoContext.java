package org.jetbrains.anko;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: AnkoContext.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0014R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0005\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, m3961d2 = {"Lorg/jetbrains/anko/ReusableAnkoContext;", ExifInterface.GPS_DIRECTION_TRUE, "Lorg/jetbrains/anko/AnkoContextImpl;", "ctx", "Landroid/content/Context;", "owner", "setContentView", "", "(Landroid/content/Context;Ljava/lang/Object;Z)V", "getCtx", "()Landroid/content/Context;", "getOwner", "()Ljava/lang/Object;", "Ljava/lang/Object;", "alreadyHasView", "", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class ReusableAnkoContext<T> extends AnkoContextImpl<T> {
    private final Context ctx;
    private final T owner;

    @Override // org.jetbrains.anko.AnkoContextImpl
    protected void alreadyHasView() {
    }

    @Override // org.jetbrains.anko.AnkoContextImpl, org.jetbrains.anko.AnkoContext
    public Context getCtx() {
        return this.ctx;
    }

    @Override // org.jetbrains.anko.AnkoContextImpl, org.jetbrains.anko.AnkoContext
    public T getOwner() {
        return this.owner;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReusableAnkoContext(Context ctx, T t, boolean z) {
        super(ctx, t, z);
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        this.ctx = ctx;
        this.owner = t;
    }
}
