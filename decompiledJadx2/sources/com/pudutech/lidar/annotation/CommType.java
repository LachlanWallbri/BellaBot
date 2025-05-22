package com.pudutech.lidar.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes5.dex */
public @interface CommType {
    public static final int FIRMWARE = 6;
    public static final int NET = 2;
    public static final int NONE = 0;
    public static final int SLAMWARE = 5;
    public static final int TTYS2 = 3;
    public static final int TTYS4 = 4;
    public static final int USB = 1;
}
