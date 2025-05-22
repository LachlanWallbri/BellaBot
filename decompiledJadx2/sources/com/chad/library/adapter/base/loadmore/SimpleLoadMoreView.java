package com.chad.library.adapter.base.loadmore;

import com.chad.library.C1559R;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class SimpleLoadMoreView extends LoadMoreView {
    @Override // com.chad.library.adapter.base.loadmore.LoadMoreView
    public int getLayoutId() {
        return C1559R.layout.brvah_quick_view_load_more;
    }

    @Override // com.chad.library.adapter.base.loadmore.LoadMoreView
    protected int getLoadingViewId() {
        return C1559R.id.load_more_loading_view;
    }

    @Override // com.chad.library.adapter.base.loadmore.LoadMoreView
    protected int getLoadFailViewId() {
        return C1559R.id.load_more_load_fail_view;
    }

    @Override // com.chad.library.adapter.base.loadmore.LoadMoreView
    protected int getLoadEndViewId() {
        return C1559R.id.load_more_load_end_view;
    }
}
