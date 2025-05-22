package com.pudutech.resources.language;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.Soundex;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: LanguageUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0018\u001a\u00020\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006H\u0002J \u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\u0012J \u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\u0012J\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/pudutech/resources/language/LanguageUtils;", "", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "FILE_NAME", "", "KEY_LANGUAGE", "TAG", "getAppContext", "()Landroid/content/Context;", "current", "Lcom/pudutech/resources/language/Option;", "getCurrent", "()Lcom/pudutech/resources/language/Option;", "setCurrent", "(Lcom/pudutech/resources/language/Option;)V", "<set-?>", "", "needSet", "getNeedSet", "()Z", "sharedPreferences", "Landroid/content/SharedPreferences;", "getOption", "string", "setLocale", "", "context", "option", "saveConfig", "setSettingLocale", "setSystemLanguage", "locale", "Ljava/util/Locale;", "updateLanguage", "resources_bellabot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class LanguageUtils {
    private final String FILE_NAME;
    private final String KEY_LANGUAGE;
    private final String TAG;
    private final Context appContext;
    private Option current;
    private boolean needSet;
    private final SharedPreferences sharedPreferences;

    public LanguageUtils(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        this.appContext = appContext;
        this.TAG = "LanguageUtils";
        this.FILE_NAME = "locale";
        this.KEY_LANGUAGE = "language";
        SharedPreferences sharedPreferences = this.appContext.getSharedPreferences(this.FILE_NAME, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "appContext.getSharedPref…ME, Context.MODE_PRIVATE)");
        this.sharedPreferences = sharedPreferences;
        String string = this.sharedPreferences.getString(this.KEY_LANGUAGE, "");
        Pdlog.m3275i(this.TAG, "sharedPreferences get=" + string);
        this.current = getOption(string);
        Pdlog.m3275i(this.TAG, "init. current=" + this.current);
    }

    public final Context getAppContext() {
        return this.appContext;
    }

    public final Option getCurrent() {
        return this.current;
    }

    public final void setCurrent(Option option) {
        Intrinsics.checkParameterIsNotNull(option, "<set-?>");
        this.current = option;
    }

    public final boolean getNeedSet() {
        return this.needSet;
    }

    private final Option getOption(String string) {
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            this.needSet = true;
            Pdlog.m3277w(this.TAG, "string=null. return default=" + SupportedLocale.INSTANCE.getDefault());
            return SupportedLocale.INSTANCE.getDefault();
        }
        if (string == null) {
            Intrinsics.throwNpe();
        }
        List split$default = StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null);
        String str2 = (String) split$default.get(0);
        String str3 = (String) split$default.get(1);
        for (Option option : SupportedLocale.INSTANCE.getList()) {
            if (Intrinsics.areEqual(option.getLocale().getLanguage(), str2) && Intrinsics.areEqual(option.getLocale().getCountry(), str3)) {
                return option;
            }
        }
        this.needSet = true;
        Pdlog.m3277w(this.TAG, "wrong string=" + string + ". split=" + str2 + ' ' + str3 + ". return default=" + SupportedLocale.INSTANCE.getDefault());
        return SupportedLocale.INSTANCE.getDefault();
    }

    public static /* synthetic */ void setSettingLocale$default(LanguageUtils languageUtils, Context context, Option option, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        languageUtils.setSettingLocale(context, option, z);
    }

    public final void setSettingLocale(Context context, Option option, boolean saveConfig) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(option, "option");
        Locale locale = option.getLocale();
        Pdlog.m3275i(this.TAG, "set locale. appContext=" + context + " local=" + locale);
        StringBuilder sb = new StringBuilder();
        sb.append(option.getLocale().getLanguage());
        sb.append(Soundex.SILENT_MARKER);
        sb.append(option.getLocale().getCountry());
        String sb2 = sb.toString();
        if (saveConfig) {
            this.sharedPreferences.edit().putString(this.KEY_LANGUAGE, sb2).apply();
        }
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        Configuration config = resources.getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(config, "config");
        LocaleList locales = config.getLocales();
        Intrinsics.checkExpressionValueIsNotNull(locales, "config.locales");
        Pdlog.m3275i(this.TAG, "set locale. currentLocales=" + locales);
        if (locales.size() > 0) {
            Locale l = locales.get(0);
            Intrinsics.checkExpressionValueIsNotNull(l, "l");
            if (Intrinsics.areEqual(l.getLanguage(), locale.getLanguage()) && Intrinsics.areEqual(l.getCountry(), locale.getCountry())) {
                Pdlog.m3275i(this.TAG, "set locale. currentLocales same ,do not need reset");
                return;
            }
        }
        config.setLocale(locale);
        context.createConfigurationContext(config);
    }

    public static /* synthetic */ void setLocale$default(LanguageUtils languageUtils, Context context, Option option, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        languageUtils.setLocale(context, option, z);
    }

    public final void setLocale(Context context, Option option, boolean saveConfig) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(option, "option");
        Locale locale = option.getLocale();
        Pdlog.m3275i(this.TAG, "set locale. appContext=" + context + " local=" + locale);
        StringBuilder sb = new StringBuilder();
        sb.append(option.getLocale().getLanguage());
        sb.append(Soundex.SILENT_MARKER);
        sb.append(option.getLocale().getCountry());
        String sb2 = sb.toString();
        if (saveConfig) {
            this.sharedPreferences.edit().putString(this.KEY_LANGUAGE, sb2).apply();
        }
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        Configuration config = resources.getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(config, "config");
        LocaleList locales = config.getLocales();
        Intrinsics.checkExpressionValueIsNotNull(locales, "config.locales");
        Pdlog.m3275i(this.TAG, "set locale. currentLocales=" + locales);
        if (locales.size() > 0) {
            Locale l = locales.get(0);
            Intrinsics.checkExpressionValueIsNotNull(l, "l");
            if (Intrinsics.areEqual(l.getLanguage(), locale.getLanguage()) && Intrinsics.areEqual(l.getCountry(), locale.getCountry())) {
                Pdlog.m3275i(this.TAG, "set locale. currentLocales same ,do not need reset");
                return;
            }
        }
        config.setLocale(locale);
        context.createConfigurationContext(config);
        updateLanguage(locale);
        setSystemLanguage(locale);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x016d A[Catch: Exception -> 0x01df, TryCatch #1 {Exception -> 0x01df, blocks: (B:24:0x012c, B:26:0x016d, B:28:0x01d7, B:29:0x01de), top: B:23:0x012c }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d7 A[Catch: Exception -> 0x01df, TryCatch #1 {Exception -> 0x01df, blocks: (B:24:0x012c, B:26:0x016d, B:28:0x01d7, B:29:0x01de), top: B:23:0x012c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void updateLanguage(Locale locale) {
        String str;
        String str2;
        Object obj;
        String str3;
        Object invoke;
        long elapsedRealtime;
        Class<?> cls;
        Object invoke2;
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Pdlog.m3275i(this.TAG, "updateLanguage " + locale);
        try {
            elapsedRealtime = SystemClock.elapsedRealtime();
            cls = Class.forName("android.app.IActivityManager");
            Intrinsics.checkExpressionValueIsNotNull(cls, "Class.forName(\"android.app.IActivityManager\")");
            Class<?> cls2 = Class.forName("android.app.ActivityManager");
            Intrinsics.checkExpressionValueIsNotNull(cls2, "Class.forName(\"android.app.ActivityManager\")");
            str2 = "Class.forName(\"android.app.IActivityManager\")";
            try {
                Method declaredMethod = cls2.getDeclaredMethod("getService", new Class[0]);
                Intrinsics.checkExpressionValueIsNotNull(declaredMethod, "clzActMagNative.getDeclaredMethod(\"getService\")");
                invoke2 = declaredMethod.invoke(cls2, new Object[0]);
                Method declaredMethod2 = cls.getDeclaredMethod("getConfiguration", new Class[0]);
                Intrinsics.checkExpressionValueIsNotNull(declaredMethod2, "clzIActMag.getDeclaredMethod(\"getConfiguration\")");
                obj = declaredMethod2.invoke(invoke2, new Object[0]);
            } catch (Exception e) {
                e = e;
                str = "clzIActMag.getDeclaredMethod(\"getConfiguration\")";
            }
        } catch (Exception e2) {
            e = e2;
            str = "clzIActMag.getDeclaredMethod(\"getConfiguration\")";
            str2 = "Class.forName(\"android.app.IActivityManager\")";
        }
        try {
            if (obj == null) {
                str = "clzIActMag.getDeclaredMethod(\"getConfiguration\")";
                obj = true;
                str3 = "null cannot be cast to non-null type android.content.res.Configuration";
                try {
                    throw new TypeCastException(str3);
                } catch (Exception e3) {
                    e = e3;
                    Pdlog.m3274e(this.TAG, "exception " + e.getLocalizedMessage() + ' ' + Log.getStackTraceString(e));
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    Class<?> cls3 = Class.forName("android.app.IActivityManager");
                    Intrinsics.checkExpressionValueIsNotNull(cls3, str2);
                    Class<?> cls4 = Class.forName("android.app.ActivityManagerNative");
                    Intrinsics.checkExpressionValueIsNotNull(cls4, "Class.forName(\"android.app.ActivityManagerNative\")");
                    String str4 = str3;
                    Method declaredMethod3 = cls4.getDeclaredMethod("getDefault", new Class[0]);
                    Intrinsics.checkExpressionValueIsNotNull(declaredMethod3, "clzActMagNative.getDeclaredMethod(\"getDefault\")");
                    Object invoke3 = declaredMethod3.invoke(cls4, new Object[0]);
                    Method declaredMethod4 = cls3.getDeclaredMethod("getConfiguration", new Class[0]);
                    Intrinsics.checkExpressionValueIsNotNull(declaredMethod4, str);
                    invoke = declaredMethod4.invoke(invoke3, new Object[0]);
                    if (invoke == null) {
                    }
                }
            } else {
                Configuration configuration = (Configuration) obj;
                str = "clzIActMag.getDeclaredMethod(\"getConfiguration\")";
                try {
                    configuration.setLocales(new LocaleList(locale));
                    Class<?> cls5 = Class.forName("android.content.res.Configuration");
                    Intrinsics.checkExpressionValueIsNotNull(cls5, "Class.forName(\"android.content.res.Configuration\")");
                    Field field = cls5.getField("userSetLocale");
                    Intrinsics.checkExpressionValueIsNotNull(field, "clzConfig.getField(\"userSetLocale\")");
                    field.set(configuration, true);
                    Method declaredMethod5 = cls.getDeclaredMethod("updatePersistentConfiguration", Configuration.class);
                    Intrinsics.checkExpressionValueIsNotNull(declaredMethod5, "clzIActMag.getDeclaredMe…:class.java\n            )");
                    declaredMethod5.invoke(invoke2, configuration);
                    BackupManager.dataChanged("com.android.providers.settings");
                    String str5 = this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("spend time to update configure , 8.1  ");
                    sb.append(SystemClock.elapsedRealtime() - elapsedRealtime);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str5, objArr);
                } catch (Exception e4) {
                    e = e4;
                    obj = true;
                    str3 = "null cannot be cast to non-null type android.content.res.Configuration";
                    Pdlog.m3274e(this.TAG, "exception " + e.getLocalizedMessage() + ' ' + Log.getStackTraceString(e));
                    try {
                        long elapsedRealtime22 = SystemClock.elapsedRealtime();
                        Class<?> cls32 = Class.forName("android.app.IActivityManager");
                        Intrinsics.checkExpressionValueIsNotNull(cls32, str2);
                        Class<?> cls42 = Class.forName("android.app.ActivityManagerNative");
                        Intrinsics.checkExpressionValueIsNotNull(cls42, "Class.forName(\"android.app.ActivityManagerNative\")");
                        String str42 = str3;
                        Method declaredMethod32 = cls42.getDeclaredMethod("getDefault", new Class[0]);
                        Intrinsics.checkExpressionValueIsNotNull(declaredMethod32, "clzActMagNative.getDeclaredMethod(\"getDefault\")");
                        Object invoke32 = declaredMethod32.invoke(cls42, new Object[0]);
                        Method declaredMethod42 = cls32.getDeclaredMethod("getConfiguration", new Class[0]);
                        Intrinsics.checkExpressionValueIsNotNull(declaredMethod42, str);
                        invoke = declaredMethod42.invoke(invoke32, new Object[0]);
                        if (invoke == null) {
                        }
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                }
            }
        } catch (Exception e6) {
            e = e6;
            str3 = "null cannot be cast to non-null type android.content.res.Configuration";
            Pdlog.m3274e(this.TAG, "exception " + e.getLocalizedMessage() + ' ' + Log.getStackTraceString(e));
            long elapsedRealtime222 = SystemClock.elapsedRealtime();
            Class<?> cls322 = Class.forName("android.app.IActivityManager");
            Intrinsics.checkExpressionValueIsNotNull(cls322, str2);
            Class<?> cls422 = Class.forName("android.app.ActivityManagerNative");
            Intrinsics.checkExpressionValueIsNotNull(cls422, "Class.forName(\"android.app.ActivityManagerNative\")");
            String str422 = str3;
            Method declaredMethod322 = cls422.getDeclaredMethod("getDefault", new Class[0]);
            Intrinsics.checkExpressionValueIsNotNull(declaredMethod322, "clzActMagNative.getDeclaredMethod(\"getDefault\")");
            Object invoke322 = declaredMethod322.invoke(cls422, new Object[0]);
            Method declaredMethod422 = cls322.getDeclaredMethod("getConfiguration", new Class[0]);
            Intrinsics.checkExpressionValueIsNotNull(declaredMethod422, str);
            invoke = declaredMethod422.invoke(invoke322, new Object[0]);
            if (invoke == null) {
                throw new TypeCastException(str422);
            }
            Configuration configuration2 = (Configuration) invoke;
            configuration2.setLocales(new LocaleList(locale));
            Class<?> cls6 = Class.forName("android.content.res.Configuration");
            Intrinsics.checkExpressionValueIsNotNull(cls6, "Class.forName(\"android.content.res.Configuration\")");
            Field field2 = cls6.getField("userSetLocale");
            Intrinsics.checkExpressionValueIsNotNull(field2, "clzConfig.getField(\"userSetLocale\")");
            field2.set(configuration2, obj);
            Class[] clsArr = {Configuration.class};
            Method declaredMethod6 = cls322.getDeclaredMethod("updateConfiguration", (Class[]) Arrays.copyOf(clsArr, clsArr.length));
            Intrinsics.checkExpressionValueIsNotNull(declaredMethod6, "clzIActMag.getDeclaredMe…nfiguration\", *clzParams)");
            declaredMethod6.invoke(invoke322, configuration2);
            BackupManager.dataChanged("com.android.providers.settings");
            Pdlog.m3273d(this.TAG, "spend time to update configure  " + (SystemClock.elapsedRealtime() - elapsedRealtime222));
        }
    }

    public final void setSystemLanguage(Locale locale) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Pair<Integer, String> execCommand = Tools.execCommand("setprop persist.sys.locale " + locale.toLanguageTag(), true);
        Pdlog.m3273d(this.TAG, "set system language " + locale.getLanguage() + ' ' + locale.getCountry() + " result=" + ((Integer) execCommand.first));
    }
}
