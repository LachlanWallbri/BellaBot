package org.jetbrains.anko.support.p094v4;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amitshekhar.utils.DataType;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.anko.AnkoContext;
import org.jetbrains.anko.AnkoContextImpl;
import org.jetbrains.anko.ContextUtilsKt;
import org.jetbrains.anko.Orientation;
import org.jetbrains.anko.ScreenSize;
import org.jetbrains.anko.UiMode;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: Support.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00022\u001d\u0010\u0003\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u001a¶\u0001\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\t*\u00020\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\b0\u001cH\u0086\b¢\u0006\u0002\u0010\u001d\u001a&\u0010\u001e\u001a\u0002H\b\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u001f*\u00020\u00022\u0006\u0010 \u001a\u00020\u000eH\u0086\b¢\u0006\u0002\u0010!\u001a(\u0010\"\u001a\u0004\u0018\u0001H\b\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\u001f*\u00020\u00022\u0006\u0010 \u001a\u00020\u000eH\u0086\b¢\u0006\u0002\u0010!\u001aI\u0010#\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u0002*\u0002H\b2.\u0010$\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t0&0%\"\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t0&¢\u0006\u0002\u0010'¨\u0006("}, m3961d2 = {"UI", "Lorg/jetbrains/anko/AnkoContext;", "Landroidx/fragment/app/Fragment;", "init", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "configuration", ExifInterface.GPS_DIRECTION_TRUE, "", "screenSize", "Lorg/jetbrains/anko/ScreenSize;", "density", "Lkotlin/ranges/ClosedRange;", "", "language", "", "orientation", "Lorg/jetbrains/anko/Orientation;", DataType.LONG, "", "fromSdk", "sdk", "uiMode", "Lorg/jetbrains/anko/UiMode;", "nightMode", "rightToLeft", "smallestWidth", "Lkotlin/Function0;", "(Landroid/support/v4/app/Fragment;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "find", "Landroid/view/View;", "id", "(Landroid/support/v4/app/Fragment;I)Landroid/view/View;", "findOptional", "withArguments", "params", "", "Lkotlin/Pair;", "(Landroid/support/v4/app/Fragment;[Lkotlin/Pair;)Landroid/support/v4/app/Fragment;", "supportV4-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SupportKt {
    private static final <T extends View> T find(Fragment fragment, int i) {
        View view = fragment.getView();
        View findViewById = view != null ? view.findViewById(i) : null;
        Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    private static final <T extends View> T findOptional(Fragment fragment, int i) {
        View view = fragment.getView();
        View findViewById = view != null ? view.findViewById(i) : null;
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) findViewById;
    }

    /* renamed from: UI */
    public static final AnkoContext<Fragment> m4178UI(Fragment receiver$0, Function1<? super AnkoContext<? extends Fragment>, Unit> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        AnkoInternals ankoInternals = AnkoInternals.INSTANCE;
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        AnkoContextImpl ankoContextImpl = new AnkoContextImpl(requireActivity, receiver$0, false);
        init.invoke(ankoContextImpl);
        return ankoContextImpl;
    }

    public static /* synthetic */ Object configuration$default(Fragment receiver$0, ScreenSize screenSize, ClosedRange closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0 init, int i, Object obj) {
        ScreenSize screenSize2 = (i & 1) != 0 ? (ScreenSize) null : screenSize;
        ClosedRange closedRange2 = (i & 2) != 0 ? (ClosedRange) null : closedRange;
        String str2 = (i & 4) != 0 ? (String) null : str;
        Orientation orientation2 = (i & 8) != 0 ? (Orientation) null : orientation;
        Boolean bool4 = (i & 16) != 0 ? (Boolean) null : bool;
        Integer num4 = (i & 32) != 0 ? (Integer) null : num;
        Integer num5 = (i & 64) != 0 ? (Integer) null : num2;
        UiMode uiMode2 = (i & 128) != 0 ? (UiMode) null : uiMode;
        Boolean bool5 = (i & 256) != 0 ? (Boolean) null : bool2;
        Boolean bool6 = (i & 512) != 0 ? (Boolean) null : bool3;
        Integer num6 = (i & 1024) != 0 ? (Integer) null : num3;
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        FragmentActivity activity = receiver$0.getActivity();
        if (activity == null || !AnkoInternals.testConfiguration(activity, screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return null;
        }
        return init.invoke();
    }

    public static final <T> T configuration(Fragment receiver$0, ScreenSize screenSize, ClosedRange<Integer> closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0<? extends T> init) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(init, "init");
        FragmentActivity activity = receiver$0.getActivity();
        if (activity == null || !AnkoInternals.testConfiguration(activity, screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3)) {
            return null;
        }
        return init.invoke();
    }

    public static final <T extends Fragment> T withArguments(T receiver$0, Pair<String, ? extends Object>... params) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(params, "params");
        receiver$0.setArguments(ContextUtilsKt.bundleOf((Pair[]) Arrays.copyOf(params, params.length)));
        return receiver$0;
    }
}
