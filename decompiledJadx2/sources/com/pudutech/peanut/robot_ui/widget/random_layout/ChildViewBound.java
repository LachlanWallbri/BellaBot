package com.pudutech.peanut.robot_ui.widget.random_layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* loaded from: classes5.dex */
public class ChildViewBound extends FrameLayout.LayoutParams {
    private int childBottom;
    private int childLeft;
    private int childRight;
    private int childTop;

    public ChildViewBound(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChildViewBound(int i, int i2) {
        super(i, i2);
    }

    public ChildViewBound(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public int getChildLeft() {
        return this.childLeft;
    }

    public void setChildLeft(int i) {
        this.childLeft = i;
    }

    public int getChildTop() {
        return this.childTop;
    }

    public void setChildTop(int i) {
        this.childTop = i;
    }

    public int getChildRight() {
        return this.childRight;
    }

    public void setChildRight(int i) {
        this.childRight = i;
    }

    public int getChildBottom() {
        return this.childBottom;
    }

    public void setChildBottom(int i) {
        this.childBottom = i;
    }

    public void clear() {
        this.childLeft = 0;
        this.childTop = 0;
        this.childRight = 0;
        this.childBottom = 0;
    }

    public void setChildViewBound(int i, int i2, int i3, int i4) {
        this.childLeft = i;
        this.childTop = i2;
        this.childRight = i3;
        this.childBottom = i4;
    }

    public String toString() {
        return "ChildViewBound [childLeft=" + this.childLeft + ", childTop=" + this.childTop + ", childRight=" + this.childRight + ", childBottom=" + this.childBottom + "]";
    }
}
