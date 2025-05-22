package org.simpleframework.xml.transform;

import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
class LocaleTransform implements Transform<Locale> {
    private final Pattern pattern = Pattern.compile("_");

    @Override // org.simpleframework.xml.transform.Transform
    public Locale read(String str) throws Exception {
        String[] split = this.pattern.split(str);
        if (split.length < 1) {
            throw new InvalidFormatException("Invalid locale %s", str);
        }
        return read(split);
    }

    private Locale read(String[] strArr) throws Exception {
        String[] strArr2 = {"", "", ""};
        for (int i = 0; i < strArr2.length; i++) {
            if (i < strArr.length) {
                strArr2[i] = strArr[i];
            }
        }
        return new Locale(strArr2[0], strArr2[1], strArr2[2]);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Locale locale) {
        return locale.toString();
    }
}
