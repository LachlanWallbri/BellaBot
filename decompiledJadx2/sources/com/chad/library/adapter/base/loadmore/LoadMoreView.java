package com.chad.library.adapter.base.loadmore;

import com.chad.library.adapter.base.BaseViewHolder;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class LoadMoreView {
    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_END = 4;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_LOADING = 2;
    private int mLoadMoreStatus = 1;
    private boolean mLoadMoreEndGone = false;

    public abstract int getLayoutId();

    protected abstract int getLoadEndViewId();

    protected abstract int getLoadFailViewId();

    protected abstract int getLoadingViewId();

    public void setLoadMoreStatus(int i) {
        this.mLoadMoreStatus = i;
    }

    public int getLoadMoreStatus() {
        return this.mLoadMoreStatus;
    }

    public void convert(BaseViewHolder baseViewHolder) {
        int i = this.mLoadMoreStatus;
        if (i == 1) {
            visibleLoading(baseViewHolder, false);
            visibleLoadFail(baseViewHolder, false);
            visibleLoadEnd(baseViewHolder, false);
            return;
        }
        if (i == 2) {
            visibleLoading(baseViewHolder, true);
            visibleLoadFail(baseViewHolder, false);
            visibleLoadEnd(baseViewHolder, false);
        } else if (i == 3) {
            visibleLoading(baseViewHolder, false);
            visibleLoadFail(baseViewHolder, true);
            visibleLoadEnd(baseViewHolder, false);
        } else {
            if (i != 4) {
                return;
            }
            visibleLoading(baseViewHolder, false);
            visibleLoadFail(baseViewHolder, false);
            visibleLoadEnd(baseViewHolder, true);
        }
    }

    private void visibleLoading(BaseViewHolder baseViewHolder, boolean z) {
        baseViewHolder.setGone(getLoadingViewId(), z);
    }

    private void visibleLoadFail(BaseViewHolder baseViewHolder, boolean z) {
        baseViewHolder.setGone(getLoadFailViewId(), z);
    }

    private void visibleLoadEnd(BaseViewHolder baseViewHolder, boolean z) {
        int loadEndViewId = getLoadEndViewId();
        if (loadEndViewId != 0) {
            baseViewHolder.setGone(loadEndViewId, z);
        }
    }

    public final void setLoadMoreEndGone(boolean z) {
        this.mLoadMoreEndGone = z;
    }

    public final boolean isLoadEndMoreGone() {
        if (getLoadEndViewId() == 0) {
            return true;
        }
        return this.mLoadMoreEndGone;
    }

    @Deprecated
    public boolean isLoadEndGone() {
        return this.mLoadMoreEndGone;
    }
}
