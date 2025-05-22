package com.scwang.smartrefresh.layout.api;

import android.view.View;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.listener.OnStateChangedListener;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface RefreshInternal extends OnStateChangedListener {
    SpinnerStyle getSpinnerStyle();

    View getView();

    boolean isSupportHorizontalDrag();

    int onFinish(RefreshLayout refreshLayout, boolean z);

    void onHorizontalDrag(float f, int i, int i2);

    void onInitialized(RefreshKernel refreshKernel, int i, int i2);

    void onMoving(boolean z, float f, int i, int i2, int i3);

    void onReleased(RefreshLayout refreshLayout, int i, int i2);

    void onStartAnimator(RefreshLayout refreshLayout, int i, int i2);

    void setPrimaryColors(int... iArr);
}
