package org.hash.mock.debug.view.sheet.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.pudutech.disinfect.baselib.C4429R;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class FreeGrowUpParent extends RelativeLayout implements GrowUpParent {
    private int currentHeight;
    private float downY;
    private boolean isGrowUp;
    private int mOffset;
    private int originalHeight;
    private int parentHeight;

    public FreeGrowUpParent(Context context) {
        super(context);
        this.originalHeight = -1;
        this.isGrowUp = true;
        this.mOffset = -1;
    }

    public FreeGrowUpParent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.originalHeight = -1;
        this.isGrowUp = true;
        this.mOffset = -1;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C4429R.styleable.FreeGrowUpParent);
        this.mOffset = obtainStyledAttributes.getDimensionPixelOffset(C4429R.styleable.FreeGrowUpParent_offset, -1);
        obtainStyledAttributes.recycle();
    }

    public FreeGrowUpParent(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.originalHeight = -1;
        this.isGrowUp = true;
        this.mOffset = -1;
    }

    public FreeGrowUpParent(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.originalHeight = -1;
        this.isGrowUp = true;
        this.mOffset = -1;
    }

    private boolean checkGrowUpEnable() {
        if (getParent() == null || !(getParent() instanceof ViewGroup) || !this.isGrowUp) {
            return false;
        }
        this.parentHeight = ((ViewGroup) getParent()).getHeight();
        return true;
    }

    private void setOriginalHeight() {
        if (this.originalHeight == -1) {
            this.originalHeight = getHeight();
        }
        this.currentHeight = getHeight();
    }

    private void changeLayoutParamsHeight(int i) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int min = Math.min(this.currentHeight + i, this.parentHeight + this.mOffset);
        int i2 = this.originalHeight;
        if (min < i2) {
            min = i2;
        }
        layoutParams.height = min;
        requestLayout();
    }

    public boolean isGrowUp() {
        return this.isGrowUp;
    }

    public void setIsGrowUp(boolean z) {
        this.isGrowUp = z;
    }

    public void reset() {
        if (this.originalHeight != -1) {
            getLayoutParams().height = this.originalHeight;
            requestLayout();
        }
    }

    @Override // org.hash.mock.debug.view.sheet.widget.GrowUpParent
    public boolean onParentHandMotionEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 2) {
                return true;
            }
            changeLayoutParamsHeight((int) (this.downY - motionEvent.getRawY()));
            return true;
        }
        if (!checkGrowUpEnable()) {
            return false;
        }
        this.downY = motionEvent.getRawY();
        setOriginalHeight();
        return true;
    }

    public void setContentHeight(int i) {
        this.originalHeight = -1;
        getLayoutParams().height = i + getContext().getResources().getDimensionPixelOffset(C4429R.dimen.arc_max_height);
        requestLayout();
    }
}
