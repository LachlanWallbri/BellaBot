package com.scwang.smartrefresh.layout.constant;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class SpinnerStyle {
    public final int ordinal;
    public static final SpinnerStyle Translate = new SpinnerStyle(0);
    public static final SpinnerStyle Scale = new SpinnerStyle(1);
    public static final SpinnerStyle FixedBehind = new SpinnerStyle(2);
    public static final SpinnerStyle FixedFront = new SpinnerStyle(3);
    public static final SpinnerStyle MatchLayout = new SpinnerStyle(4);
    public static final SpinnerStyle[] values = {Translate, Scale, FixedBehind, FixedFront, MatchLayout};

    private SpinnerStyle(int i) {
        this.ordinal = i;
    }
}
