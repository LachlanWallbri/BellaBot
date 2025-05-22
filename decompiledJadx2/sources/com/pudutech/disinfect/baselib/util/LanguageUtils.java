package com.pudutech.disinfect.baselib.util;

import android.content.Context;
import android.content.res.Resources;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.base.BaseApp;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LanguageUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/LanguageUtils;", "", "()V", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LanguageUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: LanguageUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\u0006J\u0006\u0010\u000e\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u0010\u001a\u00020\u0006J\u0006\u0010\u0011\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u0013\u001a\u00020\u0006J\u0006\u0010\u0014\u001a\u00020\u0006J\u0006\u0010\u0015\u001a\u00020\u0006J\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\u0006J\u0006\u0010\u0018\u001a\u00020\u0006J\u0006\u0010\u0019\u001a\u00020\u0006J\u0006\u0010\u001a\u001a\u00020\u0006J\u0006\u0010\u001b\u001a\u00020\u0006J\u0006\u0010\u001c\u001a\u00020\u0006J\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u0006J\u000e\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001e¨\u0006 "}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/LanguageUtils$Companion;", "", "()V", "getLocaleStr", "", "isUnderscore", "", "isALaBo", "isDE", "context", "Landroid/content/Context;", "isEnUS", "isEnglish", "isEsLA", "isEt", "isFr", "isHrHR", "isKaGE", "isKo", "isMS", "isNoSupportMerchant", "isNoSupportTts", "isRU", "isRo", "isRu", "isSlSI", "isSr", "isSupportCustomSpeaker", "isZh", "locale", "Ljava/util/Locale;", "isZhTw", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isZh(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Locale locale = context.getResources().getConfiguration().locale;
            Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
            String language = locale.getLanguage();
            String country = locale.getCountry();
            locale.toString();
            Intrinsics.checkExpressionValueIsNotNull(language, "language");
            if (!StringsKt.endsWith$default(language, "zh", false, 2, (Object) null)) {
                return false;
            }
            Intrinsics.checkExpressionValueIsNotNull(country, "country");
            return StringsKt.endsWith$default(country, "CN", false, 2, (Object) null);
        }

        public final boolean isZh() {
            return isZh(BaseApp.INSTANCE.getINSTANCE());
        }

        public final boolean isZh(Locale locale) {
            Intrinsics.checkParameterIsNotNull(locale, "locale");
            String language = locale.getLanguage();
            String country = locale.getCountry();
            Intrinsics.checkExpressionValueIsNotNull(language, "language");
            if (!StringsKt.endsWith$default(language, "zh", false, 2, (Object) null)) {
                return false;
            }
            Intrinsics.checkExpressionValueIsNotNull(country, "country");
            return StringsKt.endsWith$default(country, "CN", false, 2, (Object) null);
        }

        public final boolean isZhTw(Locale locale) {
            Intrinsics.checkParameterIsNotNull(locale, "locale");
            String language = locale.getLanguage();
            String country = locale.getCountry();
            locale.toString();
            Intrinsics.checkExpressionValueIsNotNull(language, "language");
            if (!StringsKt.endsWith$default(language, "zh", false, 2, (Object) null)) {
                return false;
            }
            Intrinsics.checkExpressionValueIsNotNull(country, "country");
            return StringsKt.endsWith$default(country, "TW", false, 2, (Object) null);
        }

        public final boolean isKo(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Locale locale = context.getResources().getConfiguration().locale;
            Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
            String language = locale.getLanguage();
            Intrinsics.checkExpressionValueIsNotNull(language, "language");
            return StringsKt.endsWith$default(language, "ko", false, 2, (Object) null);
        }

        public final boolean isEnglish(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Locale locale = context.getResources().getConfiguration().locale;
            Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
            String language = locale.getLanguage();
            Intrinsics.checkExpressionValueIsNotNull(language, "language");
            return StringsKt.endsWith$default(language, "en", false, 2, (Object) null);
        }

        public final boolean isFr(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Locale locale = context.getResources().getConfiguration().locale;
            Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
            String language = locale.getLanguage();
            Intrinsics.checkExpressionValueIsNotNull(language, "language");
            return StringsKt.endsWith$default(language, "fr", false, 2, (Object) null);
        }

        public final boolean isDE(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Locale locale = context.getResources().getConfiguration().locale;
            Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
            String language = locale.getLanguage();
            Intrinsics.checkExpressionValueIsNotNull(language, "language");
            return StringsKt.endsWith$default(language, "de", false, 2, (Object) null);
        }

        public final boolean isRU(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Locale locale = context.getResources().getConfiguration().locale;
            Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
            String language = locale.getLanguage();
            Intrinsics.checkExpressionValueIsNotNull(language, "language");
            return StringsKt.endsWith$default(language, "ru", false, 2, (Object) null);
        }

        public static /* synthetic */ String getLocaleStr$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.getLocaleStr(z);
        }

        public final String getLocaleStr(boolean isUnderscore) {
            Resources resources = BaseApp.INSTANCE.getINSTANCE().getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "BaseApp.INSTANCE.resources");
            Locale locale = resources.getConfiguration().locale;
            String str = isUnderscore ? "_" : "-";
            Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
            String str2 = (locale.getLanguage() + str) + locale.getCountry();
            Pdlog.m3273d("LanguageUtils", "getLocaleStr---:" + str2);
            return str2;
        }

        public final boolean isEsLA() {
            return getLocaleStr$default(this, false, 1, null).equals("es-US");
        }

        public final boolean isALaBo() {
            return getLocaleStr$default(this, false, 1, null).equals("ar-AR");
        }

        public final boolean isZhTw() {
            return getLocaleStr$default(this, false, 1, null).equals("zh-TW");
        }

        public final boolean isHrHR() {
            return getLocaleStr$default(this, false, 1, null).equals("hr-HR");
        }

        public final boolean isSlSI() {
            return getLocaleStr$default(this, false, 1, null).equals("sl-SI");
        }

        public final boolean isEnUS() {
            return getLocaleStr$default(this, false, 1, null).equals("en-US");
        }

        public final boolean isEt() {
            return Intrinsics.areEqual(getLocaleStr$default(this, false, 1, null), "et-EE");
        }

        public final boolean isRu() {
            return Intrinsics.areEqual(getLocaleStr$default(this, false, 1, null), "ru-RU");
        }

        public final boolean isRo() {
            return Intrinsics.areEqual(getLocaleStr$default(this, false, 1, null), "ro-RO");
        }

        public final boolean isSr() {
            return Intrinsics.areEqual(getLocaleStr$default(this, false, 1, null), "sr-RS");
        }

        public final boolean isMS() {
            return Intrinsics.areEqual(getLocaleStr$default(this, false, 1, null), "ms-MY");
        }

        public final boolean isKaGE() {
            return Intrinsics.areEqual(getLocaleStr$default(this, false, 1, null), "ka-GE");
        }

        public final boolean isNoSupportTts() {
            Companion companion = this;
            return companion.isHrHR() || companion.isSlSI() || companion.isEt() || companion.isKaGE();
        }

        public final boolean isNoSupportMerchant() {
            Companion companion = this;
            return companion.isSr() || companion.isRo() || companion.isHrHR() || companion.isSlSI() || companion.isEt() || companion.isMS() || companion.isKaGE();
        }

        public final boolean isSupportCustomSpeaker() {
            Companion companion = this;
            return companion.isZh() || companion.isZhTw() || companion.isEnUS() || companion.isRu();
        }
    }
}
