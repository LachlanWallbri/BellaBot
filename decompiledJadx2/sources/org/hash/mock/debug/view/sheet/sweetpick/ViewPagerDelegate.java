package org.hash.mock.debug.view.sheet.sweetpick;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import androidx.viewpager.widget.ViewPager;
import com.pudutech.disinfect.baselib.C4429R;
import java.util.ArrayList;
import org.hash.mock.debug.view.sheet.adapter.ViewpagerAdapter;
import org.hash.mock.debug.view.sheet.entity.SimpleEntity;
import org.hash.mock.debug.view.sheet.sweetpick.MenuListViewHandler;
import org.hash.mock.debug.view.sheet.sweetpick.SweetSheet;
import org.hash.mock.debug.view.sheet.widget.CRImageView;
import org.hash.mock.debug.view.sheet.widget.FreeGrowUpParent;
import org.hash.mock.debug.view.sheet.widget.IndicatorView;
import org.hash.mock.debug.view.sheet.widget.SweetView;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class ViewPagerDelegate extends Delegate {
    private int mContentViewHeight;
    private FreeGrowUpParent mFreeGrowUpParentRelativeLayout;
    private IndicatorView mIndicatorView;
    private MenuListViewHandler mMenuListViewHandler;
    private ArrayList<MenuListViewHandler> mMenuListViewHandlers;
    private int mNumColumns = 3;
    private SweetSheet.OnMenuItemClickListener mOnMenuItemClickListener;
    private SweetView mSweetView;
    private ViewPager mViewPager;
    private CRImageView sliderIm;

    @Override // org.hash.mock.debug.view.sheet.sweetpick.Delegate
    protected View createView() {
        View inflate = LayoutInflater.from(this.mParentVG.getContext()).inflate(C4429R.layout.debug_layout_sweet, (ViewGroup) null, false);
        this.mSweetView = (SweetView) inflate.findViewById(C4429R.id.f5004sv);
        this.mFreeGrowUpParentRelativeLayout = (FreeGrowUpParent) inflate.findViewById(C4429R.id.freeGrowUpParentF);
        this.sliderIm = (CRImageView) inflate.findViewById(C4429R.id.sliderIM);
        this.mIndicatorView = (IndicatorView) inflate.findViewById(C4429R.id.indicatorView);
        this.mIndicatorView.alphaDismiss(false);
        this.mSweetView.setAnimationListener(new AnimationImp());
        this.mViewPager = (ViewPager) inflate.findViewById(C4429R.id.f5006vp);
        int i = this.mContentViewHeight;
        if (i > 0) {
            this.mFreeGrowUpParentRelativeLayout.setContentHeight(i);
        }
        return inflate;
    }

    public ViewPagerDelegate setContentHeight(int i) {
        FreeGrowUpParent freeGrowUpParent;
        if (i > 0 && (freeGrowUpParent = this.mFreeGrowUpParentRelativeLayout) != null) {
            freeGrowUpParent.setContentHeight(i);
        } else {
            this.mContentViewHeight = i;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hash.mock.debug.view.sheet.sweetpick.Delegate
    public void setMenuList(SparseArray<ArrayList<SimpleEntity>> sparseArray) {
        this.mMenuListViewHandlers = new ArrayList<>();
        this.mNumColumns = sparseArray.size();
        for (int i = 0; i < sparseArray.size(); i++) {
            MenuListViewHandler instant = MenuListViewHandler.getInstant(i, sparseArray.get(i));
            instant.setOnMenuItemClickListener(new OnFragmentInteractionListenerImp());
            this.mMenuListViewHandlers.add(instant);
        }
        this.mViewPager.setAdapter(new ViewpagerAdapter(this.mMenuListViewHandlers));
        this.mIndicatorView.setViewPager(this.mViewPager);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: org.hash.mock.debug.view.sheet.sweetpick.ViewPagerDelegate.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                ViewPagerDelegate.this.selectPosition(i2);
            }
        });
        selectPosition(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hash.mock.debug.view.sheet.sweetpick.Delegate
    public void show() {
        super.show();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        if (this.mRootView.getParent() != null) {
            this.mParentVG.removeView(this.mRootView);
        }
        this.mParentVG.addView(this.mRootView, layoutParams);
        this.mSweetView.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hash.mock.debug.view.sheet.sweetpick.Delegate
    public void setOnMenuItemClickListener(SweetSheet.OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectPosition(int i) {
        this.mMenuListViewHandler = this.mMenuListViewHandlers.get(i);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    class OnFragmentInteractionListenerImp implements MenuListViewHandler.OnFragmentInteractionListener {
        OnFragmentInteractionListenerImp() {
        }

        @Override // org.hash.mock.debug.view.sheet.sweetpick.MenuListViewHandler.OnFragmentInteractionListener
        public void onFragmentInteraction(View view, SimpleEntity simpleEntity) {
            if (ViewPagerDelegate.this.mOnMenuItemClickListener == null || !ViewPagerDelegate.this.mOnMenuItemClickListener.onItemClick(view, simpleEntity)) {
                return;
            }
            ViewPagerDelegate.this.delayedDismiss();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    class AnimationImp implements SweetView.AnimationListener {
        AnimationImp() {
        }

        @Override // org.hash.mock.debug.view.sheet.widget.SweetView.AnimationListener
        public void onStart() {
            ViewPagerDelegate.this.mFreeGrowUpParentRelativeLayout.reset();
            ViewPagerDelegate.this.mStatus = SweetSheet.Status.SHOWING;
            ViewPagerDelegate.this.sliderIm.setVisibility(4);
            ViewPagerDelegate.this.mIndicatorView.setVisibility(4);
            if (ViewPagerDelegate.this.mMenuListViewHandler != null) {
                ViewPagerDelegate.this.mMenuListViewHandler.animationOnStart();
            }
        }

        @Override // org.hash.mock.debug.view.sheet.widget.SweetView.AnimationListener
        public void onEnd() {
            if (ViewPagerDelegate.this.mStatus == SweetSheet.Status.SHOWING) {
                ViewPagerDelegate.this.mIndicatorView.alphaShow(true);
                ViewPagerDelegate.this.mIndicatorView.setVisibility(0);
                ViewPagerDelegate.this.mIndicatorView.circularReveal(ViewPagerDelegate.this.mIndicatorView.getWidth() / 2, ViewPagerDelegate.this.mIndicatorView.getHeight() / 2, 0.0f, ViewPagerDelegate.this.mIndicatorView.getWidth(), 2000L, new DecelerateInterpolator());
                ViewPagerDelegate.this.mStatus = SweetSheet.Status.SHOW;
                ViewPagerDelegate.this.sliderIm.setVisibility(0);
                ViewPagerDelegate.this.sliderIm.circularReveal(ViewPagerDelegate.this.sliderIm.getWidth() / 2, ViewPagerDelegate.this.sliderIm.getHeight() / 2, 0.0f, ViewPagerDelegate.this.sliderIm.getWidth());
            }
        }

        @Override // org.hash.mock.debug.view.sheet.widget.SweetView.AnimationListener
        public void onContentShow() {
            if (ViewPagerDelegate.this.mMenuListViewHandler != null) {
                ViewPagerDelegate.this.mMenuListViewHandler.notifyAnimation();
            }
        }
    }
}
