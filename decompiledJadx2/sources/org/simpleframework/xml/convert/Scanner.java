package org.simpleframework.xml.convert;

import java.lang.annotation.Annotation;

/* loaded from: classes9.dex */
interface Scanner {
    <T extends Annotation> T scan(Class<T> cls);
}
