package com.pudu.library.loracall.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes4.dex */
public @interface DeviceType {
    public static final int BELLA = 2;
    public static final int FENHUANG = 4;
    public static final int HAOLA = 3;
    public static final int HLS = 1;
    public static final int UNDEFINED = 0;
}
