package com.pudutech.bumblebee.presenter.cruise_task;

import android.content.Context;
import android.content.SharedPreferences;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: CruiseConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseConfig;", "", "()V", "FILE_NAME", "", CruiseConfig.KEY_ARRIVAL_DELAY_AUTO_FINISH, "TAG", ES6Iterator.VALUE_PROPERTY, "", "delayAutoFinish_ms", "getDelayAutoFinish_ms", "()J", "setDelayAutoFinish_ms", "(J)V", "editor", "Landroid/content/SharedPreferences$Editor;", "initConfig", "", "context", "Landroid/content/Context;", "initConfig$module_bumblebee_presenter_robotRelease", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruiseConfig {
    private static final String KEY_ARRIVAL_DELAY_AUTO_FINISH = "KEY_ARRIVAL_DELAY_AUTO_FINISH";
    private static SharedPreferences.Editor editor;
    public static final CruiseConfig INSTANCE = new CruiseConfig();
    private static final String TAG = "CruiseConfig";
    private static final String FILE_NAME = "CruiseConfig";
    private static long delayAutoFinish_ms = 20000;

    private CruiseConfig() {
    }

    public final void initConfig$module_bumblebee_presenter_robotRelease(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(TAG, "initConfig context=" + context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        if (sharedPreferences != null) {
            INSTANCE.setDelayAutoFinish_ms(sharedPreferences.getLong(KEY_ARRIVAL_DELAY_AUTO_FINISH, delayAutoFinish_ms));
        }
        editor = sharedPreferences.edit();
    }

    public final long getDelayAutoFinish_ms() {
        return delayAutoFinish_ms;
    }

    public final void setDelayAutoFinish_ms(long j) {
        SharedPreferences.Editor putLong;
        Pdlog.m3276v(TAG, "set delayAutoFinish_ms=" + j);
        if (delayAutoFinish_ms != j) {
            delayAutoFinish_ms = j;
            SharedPreferences.Editor editor2 = editor;
            if (editor2 == null || (putLong = editor2.putLong(KEY_ARRIVAL_DELAY_AUTO_FINISH, delayAutoFinish_ms)) == null) {
                return;
            }
            putLong.apply();
        }
    }
}
