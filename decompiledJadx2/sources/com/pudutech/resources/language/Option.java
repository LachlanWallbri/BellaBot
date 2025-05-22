package com.pudutech.resources.language;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: Option.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/resources/language/Option;", "", "locale", "Ljava/util/Locale;", "displayName", "", "(Ljava/util/Locale;Ljava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "getLocale", "()Ljava/util/Locale;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "resources_bellabot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class Option {
    private final String displayName;
    private final Locale locale;

    public static /* synthetic */ Option copy$default(Option option, Locale locale, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            locale = option.locale;
        }
        if ((i & 2) != 0) {
            str = option.displayName;
        }
        return option.copy(locale, str);
    }

    /* renamed from: component1, reason: from getter */
    public final Locale getLocale() {
        return this.locale;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    public final Option copy(Locale locale, String displayName) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(displayName, "displayName");
        return new Option(locale, displayName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Option)) {
            return false;
        }
        Option option = (Option) other;
        return Intrinsics.areEqual(this.locale, option.locale) && Intrinsics.areEqual(this.displayName, option.displayName);
    }

    public int hashCode() {
        Locale locale = this.locale;
        int hashCode = (locale != null ? locale.hashCode() : 0) * 31;
        String str = this.displayName;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "Option(locale=" + this.locale + ", displayName=" + this.displayName + ")";
    }

    public Option(Locale locale, String displayName) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(displayName, "displayName");
        this.locale = locale;
        this.displayName = displayName;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final Locale getLocale() {
        return this.locale;
    }
}
