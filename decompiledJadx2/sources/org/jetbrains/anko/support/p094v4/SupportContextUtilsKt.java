package org.jetbrains.anko.support.p094v4;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SupportContextUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001f\u0010\u0007\u001a\u00020\b*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u000b\"\u0016\u0010\f\u001a\u00020\r*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, m3961d2 = {"act", "Landroidx/fragment/app/FragmentActivity;", "Landroidx/fragment/app/Fragment;", "act$annotations", "(Landroid/support/v4/app/Fragment;)V", "getAct", "(Landroid/support/v4/app/Fragment;)Landroid/support/v4/app/FragmentActivity;", "ctx", "Landroid/content/Context;", "ctx$annotations", "getCtx", "(Landroid/support/v4/app/Fragment;)Landroid/content/Context;", "defaultSharedPreferences", "Landroid/content/SharedPreferences;", "getDefaultSharedPreferences", "(Landroid/support/v4/app/Fragment;)Landroid/content/SharedPreferences;", "supportV4-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SupportContextUtilsKt {
    @Deprecated(message = "Use either activity or requireActivity", replaceWith = @ReplaceWith(expression = "activity", imports = {}))
    public static /* synthetic */ void act$annotations(Fragment fragment) {
    }

    @Deprecated(message = "Use either context or requireContext", replaceWith = @ReplaceWith(expression = "context", imports = {}))
    public static /* synthetic */ void ctx$annotations(Fragment fragment) {
    }

    public static final SharedPreferences getDefaultSharedPreferences(Fragment receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(receiver$0.getActivity());
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDef…aredPreferences(activity)");
        return defaultSharedPreferences;
    }

    public static final FragmentActivity getAct(Fragment receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return requireActivity;
    }

    public static final Context getCtx(Fragment receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return requireActivity;
    }
}
