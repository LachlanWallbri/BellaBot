package com.pudutech.peanut.robot_ui.util;

import android.content.Context;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LanguageUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/util/LanguageUtils;", "", "()V", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class LanguageUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: LanguageUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/util/LanguageUtils$Companion;", "", "()V", "isEnglish", "", "context", "Landroid/content/Context;", "isKo", "isZh", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
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
            Intrinsics.checkExpressionValueIsNotNull(language, "language");
            return StringsKt.endsWith$default(language, "zh", false, 2, (Object) null);
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
    }
}
