package org.jetbrains.anko.support.p094v4;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.iflytek.cloud.SpeechConstant;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.IntentsKt;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SupportIntents.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001\u001a&\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004\u001aN\u0010\t\u001a\u00020\n\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\f*\u00020\u00022.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\u0086\b¢\u0006\u0002\u0010\u0010\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0004\u001a\u001c\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004\u001a\u001c\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u001aN\u0010\u0015\u001a\u00020\u0016\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u0017*\u00020\u00022.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\u0086\b¢\u0006\u0002\u0010\u0018\u001aV\u0010\u0019\u001a\u00020\u0016\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u0017*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\u0086\b¢\u0006\u0002\u0010\u001c\u001aN\u0010\u001d\u001a\u00020\u0016\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u001e*\u00020\u00022.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\u0086\b¢\u0006\u0002\u0010\u0018\u001aN\u0010\u001f\u001a\u00020\u0016\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u001e*\u00020\u00022.\u0010\r\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f0\u000e\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000fH\u0086\b¢\u0006\u0002\u0010\u0018¨\u0006 "}, m3961d2 = {"browse", "", "Landroidx/fragment/app/Fragment;", "url", "", "newTask", "email", SpeechConstant.SUBJECT, "text", "intentFor", "Landroid/content/Intent;", ExifInterface.GPS_DIRECTION_TRUE, "", "params", "", "Lkotlin/Pair;", "(Landroid/support/v4/app/Fragment;[Lkotlin/Pair;)Landroid/content/Intent;", "makeCall", "number", "sendSMS", "share", "startActivity", "", "Landroid/app/Activity;", "(Landroid/support/v4/app/Fragment;[Lkotlin/Pair;)V", "startActivityForResult", "requestCode", "", "(Landroid/support/v4/app/Fragment;I[Lkotlin/Pair;)V", "startService", "Landroid/app/Service;", "stopService", "supportV4-base_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes9.dex */
public final class SupportIntentsKt {
    public static final boolean browse(Fragment receiver$0, String url, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(url, "url");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return IntentsKt.browse(requireActivity, url, z);
    }

    public static /* synthetic */ boolean browse$default(Fragment fragment, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return browse(fragment, str, z);
    }

    public static final boolean share(Fragment receiver$0, String text, String subject) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return IntentsKt.share(requireActivity, text, subject);
    }

    public static /* synthetic */ boolean share$default(Fragment fragment, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return share(fragment, str, str2);
    }

    public static /* synthetic */ boolean email$default(Fragment fragment, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            str3 = "";
        }
        return email(fragment, str, str2, str3);
    }

    public static final boolean email(Fragment receiver$0, String email, String subject, String text) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, "text");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return IntentsKt.email(requireActivity, email, subject, text);
    }

    public static final boolean makeCall(Fragment receiver$0, String number) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(number, "number");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return IntentsKt.makeCall(requireActivity, number);
    }

    public static final boolean sendSMS(Fragment receiver$0, String number, String text) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, "text");
        FragmentActivity requireActivity = receiver$0.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        return IntentsKt.sendSMS(requireActivity, number, text);
    }

    public static /* synthetic */ boolean sendSMS$default(Fragment fragment, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return sendSMS(fragment, str, str2);
    }

    private static final <T extends Activity> void startActivity(Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        AnkoInternals.internalStartActivity(requireActivity, Activity.class, pairArr);
    }

    private static final <T extends Activity> void startActivityForResult(Fragment fragment, int i, Pair<String, ? extends Object>... pairArr) {
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        fragment.startActivityForResult(AnkoInternals.createIntent(requireActivity, Activity.class, pairArr), i);
    }

    private static final <T extends Service> void startService(Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        AnkoInternals.internalStartService(requireActivity, Service.class, pairArr);
    }

    private static final <T extends Service> void stopService(Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        AnkoInternals.internalStopService(requireActivity, Service.class, pairArr);
    }

    private static final <T> Intent intentFor(Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        FragmentActivity requireActivity = fragment.requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.createIntent(requireActivity, Object.class, pairArr);
    }
}
