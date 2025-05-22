package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.bumblebee.robot_ui.C4188R;

/* loaded from: classes4.dex */
public class MaxHeightRecyclerView extends RecyclerView {
    private int mMaxHeight;

    public void setMaxHeight(int i) {
        this.mMaxHeight = i;
        requestLayout();
    }

    public MaxHeightRecyclerView(Context context) {
        super(context);
    }

    public MaxHeightRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context, attributeSet);
    }

    public MaxHeightRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4188R.styleable.MaxHeightRecyclerView);
        this.mMaxHeight = obtainStyledAttributes.getLayoutDimension(C4188R.styleable.MaxHeightRecyclerView_maxHeight, this.mMaxHeight);
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = this.mMaxHeight;
        if (i3 > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
    }
}
