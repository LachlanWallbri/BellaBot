package org.checkerframework.framework.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface CFComment {
    String[] value();
}
