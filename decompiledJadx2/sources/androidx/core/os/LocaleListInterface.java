package androidx.core.os;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
interface LocaleListInterface {
    Locale get(int i);

    Locale getFirstMatch(String[] strArr);

    Object getLocaleList();

    int indexOf(Locale locale);

    boolean isEmpty();

    int size();

    String toLanguageTags();
}
