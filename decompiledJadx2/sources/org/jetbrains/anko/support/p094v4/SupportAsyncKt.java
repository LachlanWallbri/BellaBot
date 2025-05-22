package org.jetbrains.anko.support.p094v4;

import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.loc.C3898x;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AnkoAsyncContext;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SupportAsync.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0004\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0087\b\u001a\u001d\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0004\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a.\u0010\u0006\u001a\u00020\u0007\"\b\b\u0000\u0010\b*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\b0\t2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00010\n\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u000e\b\u0004\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0087\b¨\u0006\f"}, m3961d2 = {"onUiThread", "", "Landroidx/fragment/app/Fragment;", C3898x.f4339h, "Lkotlin/Function0;", "runOnUiThread", "supportFragmentUiThread", "", ExifInterface.GPS_DIRECTION_TRUE, "Lorg/jetbrains/anko/AnkoAsyncContext;", "Lkotlin/Function1;", "uiThread", "supportV4-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SupportAsyncKt {
    public static final <T extends Fragment> boolean supportFragmentUiThread(AnkoAsyncContext<T> receiver$0, final Function1<? super T, Unit> f) {
        FragmentActivity activity;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(f, "f");
        final T t = receiver$0.getWeakRef().get();
        if (t != null) {
            Intrinsics.checkExpressionValueIsNotNull(t, "weakRef.get() ?: return true");
            if (!t.isDetached() && (activity = t.getActivity()) != null) {
                Intrinsics.checkExpressionValueIsNotNull(activity, "fragment.activity ?: return true");
                activity.runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.support.v4.SupportAsyncKt$supportFragmentUiThread$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Function1.this.invoke(t);
                    }
                });
            }
        }
        return true;
    }

    @Deprecated(message = "Use onUiThread() instead", replaceWith = @ReplaceWith(expression = "onUiThread(f)", imports = {}))
    public static final void uiThread(Fragment receiver$0, final Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(f, "f");
        receiver$0.requireActivity().runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.support.v4.SupportAsyncKt$uiThread$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        });
    }

    @Deprecated(message = "Use runOnUiThread() instead", replaceWith = @ReplaceWith(expression = "runOnUiThread(f)", imports = {}))
    public static final void onUiThread(Fragment receiver$0, final Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(f, "f");
        receiver$0.requireActivity().runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.support.v4.SupportAsyncKt$onUiThread$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        });
    }

    public static final void runOnUiThread(Fragment receiver$0, final Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(f, "f");
        receiver$0.requireActivity().runOnUiThread(new Runnable() { // from class: org.jetbrains.anko.support.v4.SupportAsyncKt$runOnUiThread$1
            @Override // java.lang.Runnable
            public final void run() {
                Function0.this.invoke();
            }
        });
    }
}
