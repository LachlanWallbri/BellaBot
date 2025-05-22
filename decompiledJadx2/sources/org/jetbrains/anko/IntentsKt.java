package org.jetbrains.anko;

import android.app.Activity;
import android.app.Fragment;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.net.MailTo;
import androidx.exifinterface.media.ExifInterface;
import com.iflytek.cloud.SpeechConstant;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.jetbrains.anko.internals.AnkoInternals;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: Intents.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000V\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0086\b\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001\u001a#\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001H\u0086\b\u001a\r\u0010\b\u001a\u00020\t*\u00020\tH\u0086\b\u001a\r\u0010\n\u001a\u00020\t*\u00020\tH\u0086\b\u001a\r\u0010\u000b\u001a\u00020\t*\u00020\tH\u0086\b\u001a)\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0086\b\u001a&\u0010\f\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u001a-\u0010\f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0086\b\u001a\r\u0010\u000f\u001a\u00020\t*\u00020\tH\u0086\b\u001aN\u0010\u0010\u001a\u00020\t\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u00020\u00022.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u0010\u0016\u001aN\u0010\u0010\u001a\u00020\t\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u00020\u00062.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u0010\u0017\u001aR\u0010\u0010\u001a\u00020\t\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u0006\u0012\u0002\b\u00030\u00072.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u0010\u0018\u001a\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0004H\u0086\b\u001a\u0012\u0010\u0019\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0004\u001a\u0019\u0010\u0019\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u001a\u001a\u00020\u0004H\u0086\b\u001a\r\u0010\u001b\u001a\u00020\t*\u00020\tH\u0086\b\u001a\r\u0010\u0005\u001a\u00020\t*\u00020\tH\u0086\b\u001a\r\u0010\u001c\u001a\u00020\t*\u00020\tH\u0086\b\u001a\r\u0010\u001d\u001a\u00020\t*\u00020\tH\u0086\b\u001a\u001f\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0086\b\u001a\u001c\u0010\u001e\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u001a#\u0010\u001e\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u0004H\u0086\b\u001a\u001f\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004H\u0086\b\u001a\u001c\u0010\u001f\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004\u001a#\u0010\u001f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0004H\u0086\b\u001a\r\u0010 \u001a\u00020\t*\u00020\tH\u0086\b\u001aN\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020\u00022.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u0010$\u001aN\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020\u00062.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u0010%\u001aR\u0010!\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u0006\u0012\u0002\b\u00030\u00072.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u0010&\u001aV\u0010'\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020#2\u0006\u0010(\u001a\u00020)2.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u0010*\u001aV\u0010'\u001a\u00020\"\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020#*\u00020\u00022\u0006\u0010(\u001a\u00020)2.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u0010+\u001aP\u0010,\u001a\u0004\u0018\u00010-\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u00020\u00022.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u0010/\u001aP\u0010,\u001a\u0004\u0018\u00010-\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u00020\u00062.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u00100\u001aT\u0010,\u001a\u0004\u0018\u00010-\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u0006\u0012\u0002\b\u00030\u00072.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u00101\u001aN\u00102\u001a\u00020\u0001\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u00020\u00022.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u00103\u001aN\u00102\u001a\u00020\u0001\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u00020\u00062.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u00104\u001aR\u00102\u001a\u00020\u0001\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020.*\u0006\u0012\u0002\b\u00030\u00072.\u0010\u0013\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00150\u0014\"\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015H\u0086\b¢\u0006\u0002\u00105¨\u00066"}, m3961d2 = {"browse", "", "Landroid/app/Fragment;", "url", "", "newTask", "Landroid/content/Context;", "Lorg/jetbrains/anko/AnkoContext;", "clearTask", "Landroid/content/Intent;", "clearTop", "clearWhenTaskReset", "email", SpeechConstant.SUBJECT, "text", "excludeFromRecents", "intentFor", ExifInterface.GPS_DIRECTION_TRUE, "", "params", "", "Lkotlin/Pair;", "(Landroid/app/Fragment;[Lkotlin/Pair;)Landroid/content/Intent;", "(Landroid/content/Context;[Lkotlin/Pair;)Landroid/content/Intent;", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)Landroid/content/Intent;", "makeCall", "number", "multipleTask", "noAnimation", "noHistory", "sendSMS", "share", "singleTop", "startActivity", "", "Landroid/app/Activity;", "(Landroid/app/Fragment;[Lkotlin/Pair;)V", "(Landroid/content/Context;[Lkotlin/Pair;)V", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)V", "startActivityForResult", "requestCode", "", "(Landroid/app/Activity;I[Lkotlin/Pair;)V", "(Landroid/app/Fragment;I[Lkotlin/Pair;)V", "startService", "Landroid/content/ComponentName;", "Landroid/app/Service;", "(Landroid/app/Fragment;[Lkotlin/Pair;)Landroid/content/ComponentName;", "(Landroid/content/Context;[Lkotlin/Pair;)Landroid/content/ComponentName;", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)Landroid/content/ComponentName;", "stopService", "(Landroid/app/Fragment;[Lkotlin/Pair;)Z", "(Landroid/content/Context;[Lkotlin/Pair;)Z", "(Lorg/jetbrains/anko/AnkoContext;[Lkotlin/Pair;)Z", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class IntentsKt {
    private static final <T extends Activity> void startActivity(Context context, Pair<String, ? extends Object>... pairArr) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        AnkoInternals.internalStartActivity(context, Activity.class, pairArr);
    }

    private static final <T extends Activity> void startActivity(AnkoContext<?> ankoContext, Pair<String, ? extends Object>... pairArr) {
        Context ctx = ankoContext.getCtx();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        AnkoInternals.internalStartActivity(ctx, Activity.class, pairArr);
    }

    private static final <T extends Activity> void startActivity(Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        Activity activity = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        AnkoInternals.internalStartActivity(activity, Activity.class, pairArr);
    }

    private static final <T extends Activity> void startActivityForResult(Activity activity, int i, Pair<String, ? extends Object>... pairArr) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        AnkoInternals.internalStartActivityForResult(activity, Activity.class, i, pairArr);
    }

    private static final <T extends Service> ComponentName startService(Context context, Pair<String, ? extends Object>... pairArr) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.internalStartService(context, Service.class, pairArr);
    }

    private static final <T extends Service> ComponentName startService(AnkoContext<?> ankoContext, Pair<String, ? extends Object>... pairArr) {
        Context ctx = ankoContext.getCtx();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.internalStartService(ctx, Service.class, pairArr);
    }

    private static final <T extends Service> ComponentName startService(Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        Activity activity = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.internalStartService(activity, Service.class, pairArr);
    }

    private static final <T extends Service> boolean stopService(Context context, Pair<String, ? extends Object>... pairArr) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.internalStopService(context, Service.class, pairArr);
    }

    private static final <T extends Service> boolean stopService(AnkoContext<?> ankoContext, Pair<String, ? extends Object>... pairArr) {
        Context ctx = ankoContext.getCtx();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.internalStopService(ctx, Service.class, pairArr);
    }

    private static final <T extends Service> boolean stopService(Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        Activity activity = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.internalStopService(activity, Service.class, pairArr);
    }

    private static final <T> Intent intentFor(Context context, Pair<String, ? extends Object>... pairArr) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.createIntent(context, Object.class, pairArr);
    }

    private static final <T> Intent intentFor(AnkoContext<?> ankoContext, Pair<String, ? extends Object>... pairArr) {
        Context ctx = ankoContext.getCtx();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.createIntent(ctx, Object.class, pairArr);
    }

    private static final <T> Intent intentFor(Fragment fragment, Pair<String, ? extends Object>... pairArr) {
        Activity activity = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return AnkoInternals.createIntent(activity, Object.class, pairArr);
    }

    public static final Intent clearTask(Intent receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intent intent = receiver;
        intent.addFlags(32768);
        return intent;
    }

    public static final Intent clearTop(Intent receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intent intent = receiver;
        intent.addFlags(67108864);
        return intent;
    }

    public static final Intent clearWhenTaskReset(Intent receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intent intent = receiver;
        intent.addFlags(524288);
        return intent;
    }

    public static final Intent excludeFromRecents(Intent receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intent intent = receiver;
        intent.addFlags(8388608);
        return intent;
    }

    public static final Intent multipleTask(Intent receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intent intent = receiver;
        intent.addFlags(134217728);
        return intent;
    }

    public static final Intent newTask(Intent receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intent intent = receiver;
        intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        return intent;
    }

    public static final Intent noAnimation(Intent receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intent intent = receiver;
        intent.addFlags(65536);
        return intent;
    }

    public static final Intent noHistory(Intent receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intent intent = receiver;
        intent.addFlags(1073741824);
        return intent;
    }

    public static final Intent singleTop(Intent receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intent intent = receiver;
        intent.addFlags(PKIFailureInfo.duplicateCertReq);
        return intent;
    }

    public static final boolean browse(AnkoContext<?> receiver, String url, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return browse(receiver.getCtx(), url, z);
    }

    public static /* bridge */ /* synthetic */ boolean browse$default(AnkoContext receiver, String url, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return browse(receiver.getCtx(), url, z);
    }

    public static final boolean browse(Fragment receiver, String url, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return browse(receiver.getActivity(), url, z);
    }

    public static /* bridge */ /* synthetic */ boolean browse$default(Fragment receiver, String url, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return browse(receiver.getActivity(), url, z);
    }

    public static /* bridge */ /* synthetic */ boolean browse$default(Context context, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return browse(context, str, z);
    }

    public static final boolean browse(Context receiver, String url, boolean z) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(url, "url");
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(url));
            if (z) {
                intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            }
            receiver.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final boolean share(AnkoContext<?> receiver, String text, String subject) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return share(receiver.getCtx(), text, subject);
    }

    public static /* bridge */ /* synthetic */ boolean share$default(AnkoContext receiver, String text, String subject, int i, Object obj) {
        if ((i & 2) != 0) {
            subject = "";
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return share(receiver.getCtx(), text, subject);
    }

    public static final boolean share(Fragment receiver, String text, String subject) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return share(receiver.getActivity(), text, subject);
    }

    public static /* bridge */ /* synthetic */ boolean share$default(Fragment receiver, String text, String subject, int i, Object obj) {
        if ((i & 2) != 0) {
            subject = "";
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        return share(receiver.getActivity(), text, subject);
    }

    public static /* bridge */ /* synthetic */ boolean share$default(Context context, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return share(context, str, str2);
    }

    public static final boolean share(Context receiver, String text, String subject) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", subject);
            intent.putExtra("android.intent.extra.TEXT", text);
            receiver.startActivity(Intent.createChooser(intent, null));
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final boolean email(AnkoContext<?> receiver, String email, String subject, String text) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return email(receiver.getCtx(), email, subject, text);
    }

    public static /* bridge */ /* synthetic */ boolean email$default(AnkoContext receiver, String email, String subject, String text, int i, Object obj) {
        if ((i & 2) != 0) {
            subject = "";
        }
        if ((i & 4) != 0) {
            text = "";
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return email(receiver.getCtx(), email, subject, text);
    }

    public static final boolean email(Fragment receiver, String email, String subject, String text) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return email(receiver.getActivity(), email, subject, text);
    }

    public static /* bridge */ /* synthetic */ boolean email$default(Fragment receiver, String email, String subject, String text, int i, Object obj) {
        if ((i & 2) != 0) {
            subject = "";
        }
        if ((i & 4) != 0) {
            text = "";
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return email(receiver.getActivity(), email, subject, text);
    }

    public static /* bridge */ /* synthetic */ boolean email$default(Context context, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            str3 = "";
        }
        return email(context, str, str2, str3);
    }

    public static final boolean email(Context receiver, String email, String subject, String text) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(email, "email");
        Intrinsics.checkParameterIsNotNull(subject, "subject");
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
        intent.putExtra("android.intent.extra.EMAIL", new String[]{email});
        if (subject.length() > 0) {
            intent.putExtra("android.intent.extra.SUBJECT", subject);
        }
        if (text.length() > 0) {
            intent.putExtra("android.intent.extra.TEXT", text);
        }
        if (intent.resolveActivity(receiver.getPackageManager()) == null) {
            return false;
        }
        receiver.startActivity(intent);
        return true;
    }

    public static final boolean makeCall(AnkoContext<?> receiver, String number) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        return makeCall(receiver.getCtx(), number);
    }

    public static final boolean makeCall(Fragment receiver, String number) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        return makeCall(receiver.getActivity(), number);
    }

    public static final boolean makeCall(Context receiver, String number) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        try {
            receiver.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + number)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static final boolean sendSMS(AnkoContext<?> receiver, String number, String text) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return sendSMS(receiver.getCtx(), number, text);
    }

    public static /* bridge */ /* synthetic */ boolean sendSMS$default(AnkoContext receiver, String number, String text, int i, Object obj) {
        if ((i & 2) != 0) {
            text = "";
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return sendSMS(receiver.getCtx(), number, text);
    }

    public static final boolean sendSMS(Fragment receiver, String number, String text) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return sendSMS(receiver.getActivity(), number, text);
    }

    public static /* bridge */ /* synthetic */ boolean sendSMS$default(Fragment receiver, String number, String text, int i, Object obj) {
        if ((i & 2) != 0) {
            text = "";
        }
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, "text");
        return sendSMS(receiver.getActivity(), number, text);
    }

    public static /* bridge */ /* synthetic */ boolean sendSMS$default(Context context, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return sendSMS(context, str, str2);
    }

    public static final boolean sendSMS(Context receiver, String number, String text) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(text, "text");
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + number));
            intent.putExtra("sms_body", text);
            receiver.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static final <T extends Activity> void startActivityForResult(Fragment fragment, int i, Pair<String, ? extends Object>... pairArr) {
        Activity activity = fragment.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        fragment.startActivityForResult(AnkoInternals.createIntent(activity, Activity.class, pairArr), i);
    }
}
