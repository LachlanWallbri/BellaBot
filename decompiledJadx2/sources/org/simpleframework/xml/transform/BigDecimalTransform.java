package org.simpleframework.xml.transform;

import java.math.BigDecimal;

/* loaded from: classes9.dex */
class BigDecimalTransform implements Transform<BigDecimal> {
    @Override // org.simpleframework.xml.transform.Transform
    public BigDecimal read(String str) {
        return new BigDecimal(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(BigDecimal bigDecimal) {
        return bigDecimal.toString();
    }
}
