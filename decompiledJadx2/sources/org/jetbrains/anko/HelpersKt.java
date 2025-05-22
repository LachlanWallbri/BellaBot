package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import androidx.exifinterface.media.ExifInterface;
import com.amitshekhar.utils.DataType;
import com.loc.C3898x;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Helpers.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0086\b\u001a\u001f\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\f0\nH\u0086\b\u001a\u001f\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\f0\nH\u0086\b\u001a\u001f\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\f0\nH\u0086\b\u001a¶\u0001\u0010\u0010\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0011*\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0086\b¢\u0006\u0002\u0010$\u001a¶\u0001\u0010\u0010\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0011*\u00020%2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0086\b¢\u0006\u0002\u0010&\u001a¶\u0001\u0010\u0010\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0011*\u00020'2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0086\b¢\u0006\u0002\u0010(\u001aº\u0001\u0010\u0010\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u0011*\u0006\u0012\u0002\b\u00030)2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\nH\u0086\b¢\u0006\u0002\u0010*\u001a\u0012\u0010+\u001a\u00020\u0001*\u00020\u00012\u0006\u0010,\u001a\u00020\u0001\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0003¨\u0006-"}, m3961d2 = {"gray", "", "getGray", "(I)I", "opaque", "getOpaque", "attempt", "Lorg/jetbrains/anko/AttemptResult;", ExifInterface.GPS_DIRECTION_TRUE, C3898x.f4339h, "Lkotlin/Function0;", "doBeforeSdk", "", "version", "doFromSdk", "doIfSdk", "configuration", "", "Landroid/app/Activity;", "screenSize", "Lorg/jetbrains/anko/ScreenSize;", "density", "Lkotlin/ranges/ClosedRange;", "language", "", "orientation", "Lorg/jetbrains/anko/Orientation;", DataType.LONG, "", "fromSdk", "sdk", "uiMode", "Lorg/jetbrains/anko/UiMode;", "nightMode", "rightToLeft", "smallestWidth", "(Landroid/app/Activity;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Landroid/app/Fragment;", "(Landroid/app/Fragment;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Landroid/content/Context;", "(Landroid/content/Context;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Lorg/jetbrains/anko/AnkoContext;", "(Lorg/jetbrains/anko/AnkoContext;Lorg/jetbrains/anko/ScreenSize;Lkotlin/ranges/ClosedRange;Ljava/lang/String;Lorg/jetbrains/anko/Orientation;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Lorg/jetbrains/anko/UiMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withAlpha", "alpha", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class HelpersKt {
    public static final int getGray(int i) {
        return (i << 16) | (i << 8) | i;
    }

    public static final int getOpaque(int i) {
        return i | ((int) 4278190080L);
    }

    public static final int withAlpha(int i, int i2) {
        if (i2 >= 0 && i2 <= 255) {
            return (i & 16777215) | (i2 << 24);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public static /* bridge */ /* synthetic */ Object configuration$default(Context receiver, ScreenSize screenSize, ClosedRange closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0 f, int i, Object obj) {
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
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (AnkoInternals.testConfiguration(receiver, screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return f.invoke();
        }
        return null;
    }

    public static final <T> T configuration(Context receiver, ScreenSize screenSize, ClosedRange<Integer> closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (AnkoInternals.testConfiguration(receiver, screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3)) {
            return f.invoke();
        }
        return null;
    }

    public static /* bridge */ /* synthetic */ Object configuration$default(Activity receiver, ScreenSize screenSize, ClosedRange closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0 f, int i, Object obj) {
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
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (AnkoInternals.testConfiguration(receiver, screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return f.invoke();
        }
        return null;
    }

    public static final <T> T configuration(Activity receiver, ScreenSize screenSize, ClosedRange<Integer> closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (AnkoInternals.testConfiguration(receiver, screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3)) {
            return f.invoke();
        }
        return null;
    }

    public static /* bridge */ /* synthetic */ Object configuration$default(AnkoContext receiver, ScreenSize screenSize, ClosedRange closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0 f, int i, Object obj) {
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
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (AnkoInternals.testConfiguration(receiver.getCtx(), screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return f.invoke();
        }
        return null;
    }

    public static final <T> T configuration(AnkoContext<?> receiver, ScreenSize screenSize, ClosedRange<Integer> closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (AnkoInternals.testConfiguration(receiver.getCtx(), screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3)) {
            return f.invoke();
        }
        return null;
    }

    public static /* bridge */ /* synthetic */ Object configuration$default(Fragment receiver, ScreenSize screenSize, ClosedRange closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0 f, int i, Object obj) {
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
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Activity activity = receiver.getActivity();
        if (activity == null || !AnkoInternals.testConfiguration(activity, screenSize2, closedRange2, str2, orientation2, bool4, num4, num5, uiMode2, bool5, bool6, num6)) {
            return null;
        }
        return f.invoke();
    }

    public static final <T> T configuration(Fragment receiver, ScreenSize screenSize, ClosedRange<Integer> closedRange, String str, Orientation orientation, Boolean bool, Integer num, Integer num2, UiMode uiMode, Boolean bool2, Boolean bool3, Integer num3, Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        Activity activity = receiver.getActivity();
        if (activity == null || !AnkoInternals.testConfiguration(activity, screenSize, closedRange, str, orientation, bool, num, num2, uiMode, bool2, bool3, num3)) {
            return null;
        }
        return f.invoke();
    }

    public static final void doBeforeSdk(int i, Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (Build.VERSION.SDK_INT <= i) {
            f.invoke();
        }
    }

    public static final void doFromSdk(int i, Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (Build.VERSION.SDK_INT >= i) {
            f.invoke();
        }
    }

    public static final void doIfSdk(int i, Function0<Unit> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (Build.VERSION.SDK_INT == i) {
            f.invoke();
        }
    }

    public static final <T> AttemptResult<T> attempt(Function0<? extends T> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        T t = null;
        Throwable th = (Throwable) null;
        try {
            t = f.invoke();
        } catch (Throwable th2) {
            th = th2;
        }
        return new AttemptResult<>(t, th);
    }
}
