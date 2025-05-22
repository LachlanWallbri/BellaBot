package com.pudutech.module_robot_selfcheck.data;

import com.pudutech.resources.language.Option;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Language.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/data/Language;", "", "option", "Lcom/pudutech/resources/language/Option;", "(Lcom/pudutech/resources/language/Option;)V", "getOption", "()Lcom/pudutech/resources/language/Option;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class Language {
    private final Option option;

    public static /* synthetic */ Language copy$default(Language language, Option option, int i, Object obj) {
        if ((i & 1) != 0) {
            option = language.option;
        }
        return language.copy(option);
    }

    /* renamed from: component1, reason: from getter */
    public final Option getOption() {
        return this.option;
    }

    public final Language copy(Option option) {
        Intrinsics.checkParameterIsNotNull(option, "option");
        return new Language(option);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof Language) && Intrinsics.areEqual(this.option, ((Language) other).option);
        }
        return true;
    }

    public int hashCode() {
        Option option = this.option;
        if (option != null) {
            return option.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "Language(option=" + this.option + ")";
    }

    public Language(Option option) {
        Intrinsics.checkParameterIsNotNull(option, "option");
        this.option = option;
    }

    public final Option getOption() {
        return this.option;
    }
}
