package com.scwang.smartrefresh.layout.api;

import android.animation.ValueAnimator;
import com.scwang.smartrefresh.layout.constant.RefreshState;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface RefreshKernel {
    ValueAnimator animSpinner(int i);

    RefreshKernel finishTwoLevel();

    RefreshContent getRefreshContent();

    RefreshLayout getRefreshLayout();

    RefreshKernel moveSpinner(int i, boolean z);

    RefreshKernel requestDefaultTranslationContentFor(RefreshInternal refreshInternal, boolean z);

    RefreshKernel requestDrawBackgroundFor(RefreshInternal refreshInternal, int i);

    RefreshKernel requestFloorDuration(int i);

    RefreshKernel requestNeedTouchEventFor(RefreshInternal refreshInternal, boolean z);

    RefreshKernel requestRemeasureHeightFor(RefreshInternal refreshInternal);

    RefreshKernel setState(RefreshState refreshState);

    RefreshKernel startTwoLevel(boolean z);
}
