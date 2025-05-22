package com.chad.library.adapter.base.listener;

import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class SimpleClickListener implements RecyclerView.OnItemTouchListener {
    public static String TAG = "SimpleClickListener";
    protected BaseQuickAdapter baseQuickAdapter;
    private GestureDetectorCompat mGestureDetector;
    private boolean mIsPrepressed = false;
    private boolean mIsShowPress = false;
    private View mPressedView = null;
    private RecyclerView recyclerView;

    private boolean isHeaderOrFooterView(int i) {
        return i == 1365 || i == 273 || i == 819 || i == 546;
    }

    public abstract void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public abstract void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i);

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        BaseViewHolder baseViewHolder;
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            this.recyclerView = recyclerView;
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        } else if (recyclerView2 != recyclerView) {
            this.recyclerView = recyclerView;
            this.baseQuickAdapter = (BaseQuickAdapter) this.recyclerView.getAdapter();
            this.mGestureDetector = new GestureDetectorCompat(this.recyclerView.getContext(), new ItemTouchHelperGestureListener(this.recyclerView));
        }
        if (!this.mGestureDetector.onTouchEvent(motionEvent) && motionEvent.getActionMasked() == 1 && this.mIsShowPress) {
            View view = this.mPressedView;
            if (view != null && ((baseViewHolder = (BaseViewHolder) this.recyclerView.getChildViewHolder(view)) == null || !isHeaderOrFooterView(baseViewHolder.getItemViewType()))) {
                this.mPressedView.setPressed(false);
            }
            this.mIsShowPress = false;
            this.mIsPrepressed = false;
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private class ItemTouchHelperGestureListener implements GestureDetector.OnGestureListener {
        private RecyclerView recyclerView;

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            SimpleClickListener.this.mIsPrepressed = true;
            SimpleClickListener.this.mPressedView = this.recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
            if (!SimpleClickListener.this.mIsPrepressed || SimpleClickListener.this.mPressedView == null) {
                return;
            }
            SimpleClickListener.this.mIsShowPress = true;
        }

        ItemTouchHelperGestureListener(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (SimpleClickListener.this.mIsPrepressed && SimpleClickListener.this.mPressedView != null) {
                if (this.recyclerView.getScrollState() != 0) {
                    return false;
                }
                View view = SimpleClickListener.this.mPressedView;
                BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.getChildViewHolder(view);
                if (SimpleClickListener.this.isHeaderOrFooterPosition(baseViewHolder.getLayoutPosition())) {
                    return false;
                }
                HashSet<Integer> childClickViewIds = baseViewHolder.getChildClickViewIds();
                Set<Integer> nestViews = baseViewHolder.getNestViews();
                if (childClickViewIds == null || childClickViewIds.size() <= 0) {
                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, view);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    if (childClickViewIds != null && childClickViewIds.size() > 0) {
                        Iterator<Integer> it = childClickViewIds.iterator();
                        while (it.hasNext()) {
                            View findViewById = view.findViewById(it.next().intValue());
                            if (findViewById != null) {
                                findViewById.setPressed(false);
                            }
                        }
                    }
                    SimpleClickListener simpleClickListener = SimpleClickListener.this;
                    simpleClickListener.onItemClick(simpleClickListener.baseQuickAdapter, view, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                } else {
                    for (Integer num : childClickViewIds) {
                        View findViewById2 = view.findViewById(num.intValue());
                        if (findViewById2 != null) {
                            if (SimpleClickListener.this.inRangeOfView(findViewById2, motionEvent) && findViewById2.isEnabled()) {
                                if (nestViews != null && nestViews.contains(num)) {
                                    return false;
                                }
                                SimpleClickListener.this.setPressViewHotSpot(motionEvent, findViewById2);
                                findViewById2.setPressed(true);
                                SimpleClickListener simpleClickListener2 = SimpleClickListener.this;
                                simpleClickListener2.onItemChildClick(simpleClickListener2.baseQuickAdapter, findViewById2, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                                resetPressedView(findViewById2);
                                return true;
                            }
                            findViewById2.setPressed(false);
                        }
                    }
                    SimpleClickListener.this.setPressViewHotSpot(motionEvent, view);
                    SimpleClickListener.this.mPressedView.setPressed(true);
                    Iterator<Integer> it2 = childClickViewIds.iterator();
                    while (it2.hasNext()) {
                        View findViewById3 = view.findViewById(it2.next().intValue());
                        if (findViewById3 != null) {
                            findViewById3.setPressed(false);
                        }
                    }
                    SimpleClickListener simpleClickListener3 = SimpleClickListener.this;
                    simpleClickListener3.onItemClick(simpleClickListener3.baseQuickAdapter, view, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                }
                resetPressedView(view);
            }
            return true;
        }

        private void resetPressedView(final View view) {
            if (view != null) {
                view.postDelayed(new Runnable() { // from class: com.chad.library.adapter.base.listener.SimpleClickListener.ItemTouchHelperGestureListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        View view2 = view;
                        if (view2 != null) {
                            view2.setPressed(false);
                        }
                    }
                }, 50L);
            }
            SimpleClickListener.this.mIsPrepressed = false;
            SimpleClickListener.this.mPressedView = null;
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x00a9  */
        /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
        @Override // android.view.GestureDetector.OnGestureListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onLongPress(MotionEvent motionEvent) {
            boolean z;
            if (this.recyclerView.getScrollState() != 0 || !SimpleClickListener.this.mIsPrepressed || SimpleClickListener.this.mPressedView == null) {
                return;
            }
            SimpleClickListener.this.mPressedView.performHapticFeedback(0);
            BaseViewHolder baseViewHolder = (BaseViewHolder) this.recyclerView.getChildViewHolder(SimpleClickListener.this.mPressedView);
            if (SimpleClickListener.this.isHeaderOrFooterPosition(baseViewHolder.getLayoutPosition())) {
                return;
            }
            HashSet<Integer> itemChildLongClickViewIds = baseViewHolder.getItemChildLongClickViewIds();
            Set<Integer> nestViews = baseViewHolder.getNestViews();
            if (itemChildLongClickViewIds != null && itemChildLongClickViewIds.size() > 0) {
                for (Integer num : itemChildLongClickViewIds) {
                    View findViewById = SimpleClickListener.this.mPressedView.findViewById(num.intValue());
                    if (SimpleClickListener.this.inRangeOfView(findViewById, motionEvent) && findViewById.isEnabled()) {
                        if (nestViews == null || !nestViews.contains(num)) {
                            SimpleClickListener.this.setPressViewHotSpot(motionEvent, findViewById);
                            SimpleClickListener simpleClickListener = SimpleClickListener.this;
                            simpleClickListener.onItemChildLongClick(simpleClickListener.baseQuickAdapter, findViewById, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                            findViewById.setPressed(true);
                            SimpleClickListener.this.mIsShowPress = true;
                        }
                        z = true;
                        if (z) {
                            SimpleClickListener simpleClickListener2 = SimpleClickListener.this;
                            simpleClickListener2.onItemLongClick(simpleClickListener2.baseQuickAdapter, SimpleClickListener.this.mPressedView, baseViewHolder.getLayoutPosition() - SimpleClickListener.this.baseQuickAdapter.getHeaderLayoutCount());
                            SimpleClickListener simpleClickListener3 = SimpleClickListener.this;
                            simpleClickListener3.setPressViewHotSpot(motionEvent, simpleClickListener3.mPressedView);
                            SimpleClickListener.this.mPressedView.setPressed(true);
                            if (itemChildLongClickViewIds != null) {
                                Iterator<Integer> it = itemChildLongClickViewIds.iterator();
                                while (it.hasNext()) {
                                    View findViewById2 = SimpleClickListener.this.mPressedView.findViewById(it.next().intValue());
                                    if (findViewById2 != null) {
                                        findViewById2.setPressed(false);
                                    }
                                }
                            }
                            SimpleClickListener.this.mIsShowPress = true;
                            return;
                        }
                        return;
                    }
                }
            }
            z = false;
            if (z) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPressViewHotSpot(MotionEvent motionEvent, View view) {
        if (Build.VERSION.SDK_INT < 21 || view == null || view.getBackground() == null) {
            return;
        }
        view.getBackground().setHotspot(motionEvent.getRawX(), motionEvent.getY() - view.getY());
    }

    public boolean inRangeOfView(View view, MotionEvent motionEvent) {
        int[] iArr = new int[2];
        if (view != null && view.isShown()) {
            view.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            if (motionEvent.getRawX() >= i && motionEvent.getRawX() <= i + view.getWidth() && motionEvent.getRawY() >= i2 && motionEvent.getRawY() <= i2 + view.getHeight()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHeaderOrFooterPosition(int i) {
        if (this.baseQuickAdapter == null) {
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView == null) {
                return false;
            }
            this.baseQuickAdapter = (BaseQuickAdapter) recyclerView.getAdapter();
        }
        int itemViewType = this.baseQuickAdapter.getItemViewType(i);
        return itemViewType == 1365 || itemViewType == 273 || itemViewType == 819 || itemViewType == 546;
    }
}
