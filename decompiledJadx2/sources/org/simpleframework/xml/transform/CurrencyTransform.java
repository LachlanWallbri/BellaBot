package org.simpleframework.xml.transform;

import java.util.Currency;

/* loaded from: classes9.dex */
class CurrencyTransform implements Transform<Currency> {
    @Override // org.simpleframework.xml.transform.Transform
    public Currency read(String str) {
        return Currency.getInstance(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(Currency currency) {
        return currency.toString();
    }
}
