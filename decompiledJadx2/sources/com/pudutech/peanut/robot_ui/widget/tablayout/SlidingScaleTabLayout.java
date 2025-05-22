package com.pudutech.peanut.robot_ui.widget.tablayout;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.widget.tablayout.listener.OnTabSelectListener;
import com.pudutech.peanut.robot_ui.widget.tablayout.transformer.IViewPagerTransformer;
import com.pudutech.peanut.robot_ui.widget.tablayout.transformer.TabScaleTransformer;
import com.pudutech.peanut.robot_ui.widget.tablayout.utils.UnreadMsgUtils;
import com.pudutech.peanut.robot_ui.widget.tablayout.widget.MsgView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
public class SlidingScaleTabLayout extends HorizontalScrollView implements ViewPager.OnPageChangeListener {
    private static final int BOTTOM = 1;
    private static final int CENTER = 2;
    private static final int STYLE_BLOCK = 2;
    private static final int STYLE_NORMAL = 0;
    private static final int STYLE_TRIANGLE = 1;
    private static final int TEXT_BOLD_BOTH = 2;
    private static final int TEXT_BOLD_NONE = 0;
    private static final int TEXT_BOLD_WHEN_SELECT = 1;
    private static final int TOP = 0;
    private TabScaleTransformer defaultTransformer;
    private TabSelectionListener listener;
    private Context mContext;
    private float mCurrentPositionOffset;
    private int mCurrentTab;
    private int mDividerColor;
    private float mDividerPadding;
    private Paint mDividerPaint;
    private float mDividerWidth;
    private int mHeight;
    private int mIndicatorColor;
    private float mIndicatorCornerRadius;
    private GradientDrawable mIndicatorDrawable;
    private int mIndicatorGravity;
    private float mIndicatorHeight;
    private float mIndicatorMarginBottom;
    private float mIndicatorMarginLeft;
    private float mIndicatorMarginRight;
    private float mIndicatorMarginTop;
    private Rect mIndicatorRect;
    private int mIndicatorStyle;
    private float mIndicatorWidth;
    private boolean mIndicatorWidthEqualTitle;
    private SparseBooleanArray mInitSetMap;
    private int mLastScrollX;
    private OnTabSelectListener mListener;
    private Paint mRectPaint;
    private boolean mSnapOnTabClick;
    private int mTabCount;
    private int mTabGravity;
    private int mTabMarginBottom;
    private int mTabMarginTop;
    private float mTabPadding;
    private Rect mTabRect;
    private boolean mTabSpaceEqual;
    private float mTabWidth;
    private LinearLayout mTabsContainer;
    private boolean mTextAllCaps;
    private int mTextBold;
    private Paint mTextPaint;
    private int mTextSelectColor;
    private float mTextSelectSize;
    private int mTextUnSelectColor;
    private float mTextUnSelectSize;
    private ArrayList<String> mTitles;
    private Paint mTrianglePaint;
    private Path mTrianglePath;
    private int mUnderlineColor;
    private int mUnderlineGravity;
    private float mUnderlineHeight;
    private ViewPager mViewPager;
    private float margin;

    /* loaded from: classes5.dex */
    public interface TabSelectionListener {
        void selection(int i);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    public SlidingScaleTabLayout(Context context) {
        this(context, null, 0);
    }

    public SlidingScaleTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingScaleTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIndicatorRect = new Rect();
        this.mTabRect = new Rect();
        this.mIndicatorDrawable = new GradientDrawable();
        this.mRectPaint = new Paint(1);
        this.mDividerPaint = new Paint(1);
        this.mTrianglePaint = new Paint(1);
        this.mTrianglePath = new Path();
        this.mIndicatorStyle = 0;
        this.mTextPaint = new Paint(1);
        this.mInitSetMap = new SparseBooleanArray();
        setFillViewport(true);
        setWillNotDraw(false);
        setClipChildren(false);
        setClipToPadding(false);
        this.mContext = context;
        this.mTabsContainer = new LinearLayout(context);
        addView(this.mTabsContainer);
        obtainAttributes(context, attributeSet);
        String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height");
        if (attributeValue.equals("-1") || attributeValue.equals("-2")) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.layout_height});
        this.mHeight = obtainStyledAttributes.getDimensionPixelSize(0, -2);
        obtainStyledAttributes.recycle();
    }

    private void obtainAttributes(Context context, AttributeSet attributeSet) {
        float f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5508R.styleable.SlidingScaleTabLayout);
        this.mIndicatorStyle = obtainStyledAttributes.getInt(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_style, 0);
        this.mIndicatorColor = obtainStyledAttributes.getColor(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_color, Color.parseColor(this.mIndicatorStyle == 2 ? "#4B6A87" : "#ffffff"));
        int i = C5508R.styleable.SlidingScaleTabLayout_tl_indicator_height;
        int i2 = this.mIndicatorStyle;
        if (i2 == 1) {
            f = 4.0f;
        } else {
            f = i2 == 2 ? -1 : 2;
        }
        this.mIndicatorHeight = obtainStyledAttributes.getDimension(i, dp2px(f));
        this.mIndicatorWidth = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_width, dp2px(this.mIndicatorStyle == 1 ? 10.0f : -1.0f));
        this.mIndicatorCornerRadius = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_corner_radius, dp2px(this.mIndicatorStyle == 2 ? -1.0f : 0.0f));
        this.mIndicatorMarginLeft = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_margin_left, dp2px(0.0f));
        this.mIndicatorMarginTop = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_margin_top, dp2px(this.mIndicatorStyle == 2 ? 7.0f : 0.0f));
        this.mIndicatorMarginRight = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_margin_right, dp2px(0.0f));
        this.mIndicatorMarginBottom = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_margin_bottom, dp2px(this.mIndicatorStyle != 2 ? 0.0f : 7.0f));
        this.mIndicatorGravity = obtainStyledAttributes.getInt(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_gravity, 80);
        this.mIndicatorWidthEqualTitle = obtainStyledAttributes.getBoolean(C5508R.styleable.SlidingScaleTabLayout_tl_indicator_width_equal_title, false);
        this.mUnderlineColor = obtainStyledAttributes.getColor(C5508R.styleable.SlidingScaleTabLayout_tl_underline_color, Color.parseColor("#ffffff"));
        this.mUnderlineHeight = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_underline_height, dp2px(0.0f));
        this.mUnderlineGravity = obtainStyledAttributes.getInt(C5508R.styleable.SlidingScaleTabLayout_tl_underline_gravity, 80);
        this.mDividerColor = obtainStyledAttributes.getColor(C5508R.styleable.SlidingScaleTabLayout_tl_divider_color, Color.parseColor("#ffffff"));
        this.mDividerWidth = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_divider_width, dp2px(0.0f));
        this.mDividerPadding = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_divider_padding, dp2px(12.0f));
        this.mTextUnSelectSize = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_textUnSelectSize, sp2px(14.0f));
        this.mTextSelectSize = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_textSelectSize, this.mTextUnSelectSize);
        this.mTextSelectColor = obtainStyledAttributes.getColor(C5508R.styleable.SlidingScaleTabLayout_tl_textSelectColor, Color.parseColor("#ffffff"));
        this.mTextUnSelectColor = obtainStyledAttributes.getColor(C5508R.styleable.SlidingScaleTabLayout_tl_textUnSelectColor, Color.parseColor("#AAffffff"));
        this.mTextBold = obtainStyledAttributes.getInt(C5508R.styleable.SlidingScaleTabLayout_tl_textBold, 0);
        this.mTextAllCaps = obtainStyledAttributes.getBoolean(C5508R.styleable.SlidingScaleTabLayout_tl_textAllCaps, false);
        this.mTabSpaceEqual = obtainStyledAttributes.getBoolean(C5508R.styleable.SlidingScaleTabLayout_tl_tab_space_equal, false);
        this.mTabWidth = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_tab_width, dp2px(-1.0f));
        this.mTabPadding = obtainStyledAttributes.getDimension(C5508R.styleable.SlidingScaleTabLayout_tl_tab_padding, (this.mTabSpaceEqual || this.mTabWidth > 0.0f) ? dp2px(0.0f) : dp2px(20.0f));
        this.mTabMarginTop = obtainStyledAttributes.getDimensionPixelSize(C5508R.styleable.SlidingScaleTabLayout_tl_tab_marginTop, 0);
        this.mTabMarginBottom = obtainStyledAttributes.getDimensionPixelSize(C5508R.styleable.SlidingScaleTabLayout_tl_tab_marginBottom, 0);
        this.mTabGravity = obtainStyledAttributes.getInt(C5508R.styleable.SlidingScaleTabLayout_tl_tab_gravity, 2);
        obtainStyledAttributes.recycle();
    }

    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null || viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager or ViewPager adapter can not be NULL !");
        }
        this.mViewPager = viewPager;
        initViewPagerListener();
    }

    public void setViewPager(ViewPager viewPager, String[] strArr) {
        if (viewPager == null || viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager or ViewPager adapter can not be NULL !");
        }
        if (strArr == null || strArr.length == 0) {
            throw new IllegalStateException("Titles can not be EMPTY !");
        }
        if (strArr.length != viewPager.getAdapter().getCount()) {
            throw new IllegalStateException("Titles length must be the same as the page count !");
        }
        this.mViewPager = viewPager;
        this.mTitles = new ArrayList<>();
        Collections.addAll(this.mTitles, strArr);
        initViewPagerListener();
    }

    public void setViewPager(ViewPager viewPager, String[] strArr, FragmentActivity fragmentActivity, ArrayList<Fragment> arrayList) {
        if (viewPager == null) {
            throw new IllegalStateException("ViewPager can not be NULL !");
        }
        if (strArr == null || strArr.length == 0) {
            throw new IllegalStateException("Titles can not be EMPTY !");
        }
        this.mViewPager = viewPager;
        this.mViewPager.setAdapter(new InnerPagerAdapter(fragmentActivity.getSupportFragmentManager(), arrayList, strArr));
        initViewPagerListener();
    }

    private void initViewPagerListener() {
        this.mViewPager.removeOnPageChangeListener(this);
        this.mViewPager.addOnPageChangeListener(this);
        initTransformer();
        notifyDataSetChanged();
    }

    private void initTransformer() {
        if (this.mTextUnSelectSize != this.mTextSelectSize) {
            this.defaultTransformer = new TabScaleTransformer(this, this.mViewPager.getAdapter(), this.mTextSelectSize, this.mTextUnSelectSize);
            this.mViewPager.setPageTransformer(true, this.defaultTransformer);
        }
    }

    public void notifyDataSetChanged() {
        this.mTabsContainer.removeAllViews();
        ArrayList<String> arrayList = this.mTitles;
        this.mTabCount = arrayList == null ? this.mViewPager.getAdapter().getCount() : arrayList.size();
        for (int i = 0; i < this.mTabCount; i++) {
            View inflate = View.inflate(this.mContext, C5508R.layout.layout_tab, null);
            setTabLayoutParams((TextView) inflate.findViewById(C5508R.id.tv_tab_title));
            ArrayList<String> arrayList2 = this.mTitles;
            addTab(i, (arrayList2 == null ? this.mViewPager.getAdapter().getPageTitle(i) : arrayList2.get(i)).toString(), inflate);
        }
        updateTabStyles();
    }

    private void setTabLayoutParams(TextView textView) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.topMargin = this.mTabMarginTop;
        layoutParams.bottomMargin = this.mTabMarginBottom;
        int i = this.mTabGravity;
        if (i == 0) {
            layoutParams.addRule(10);
        } else if (i == 1) {
            layoutParams.addRule(12);
        } else {
            layoutParams.addRule(15);
        }
        textView.setLayoutParams(layoutParams);
    }

    public void addNewTab(String str) {
        View inflate = View.inflate(this.mContext, C5508R.layout.layout_tab, null);
        ArrayList<String> arrayList = this.mTitles;
        if (arrayList != null) {
            arrayList.add(str);
        }
        ArrayList<String> arrayList2 = this.mTitles;
        addTab(this.mTabCount, (arrayList2 == null ? this.mViewPager.getAdapter().getPageTitle(this.mTabCount) : arrayList2.get(this.mTabCount)).toString(), inflate);
        ArrayList<String> arrayList3 = this.mTitles;
        this.mTabCount = arrayList3 == null ? this.mViewPager.getAdapter().getCount() : arrayList3.size();
        updateTabStyles();
    }

    private void addTab(int i, String str, View view) {
        TextView textView = (TextView) view.findViewById(C5508R.id.tv_tab_title);
        if (textView != null) {
            textView.setTextSize(0, i == this.mCurrentTab ? this.mTextSelectSize : this.mTextUnSelectSize);
            if (str != null) {
                textView.setText(str);
            }
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.widget.tablayout.SlidingScaleTabLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int indexOfChild = SlidingScaleTabLayout.this.mTabsContainer.indexOfChild(view2);
                if (indexOfChild != -1) {
                    if (SlidingScaleTabLayout.this.mViewPager.getCurrentItem() != indexOfChild) {
                        if (SlidingScaleTabLayout.this.mSnapOnTabClick) {
                            SlidingScaleTabLayout.this.mViewPager.setCurrentItem(indexOfChild, false);
                        } else {
                            SlidingScaleTabLayout.this.mViewPager.setCurrentItem(indexOfChild);
                        }
                        if (SlidingScaleTabLayout.this.mListener != null) {
                            SlidingScaleTabLayout.this.mListener.onTabSelect(indexOfChild);
                            return;
                        }
                        return;
                    }
                    if (SlidingScaleTabLayout.this.mListener != null) {
                        SlidingScaleTabLayout.this.mListener.onTabReselect(indexOfChild);
                    }
                }
            }
        });
        LinearLayout.LayoutParams layoutParams = this.mTabSpaceEqual ? new LinearLayout.LayoutParams(0, -1, 1.0f) : new LinearLayout.LayoutParams(-2, -1);
        float f = this.mTabWidth;
        if (f > 0.0f) {
            layoutParams = new LinearLayout.LayoutParams((int) f, -1);
        }
        this.mTabsContainer.addView(view, i, layoutParams);
    }

    private void updateTabStyles() {
        int i = 0;
        while (i < this.mTabCount) {
            TextView textView = (TextView) this.mTabsContainer.getChildAt(i).findViewById(C5508R.id.tv_tab_title);
            if (textView != null) {
                textView.setTextColor(i == this.mCurrentTab ? this.mTextSelectColor : this.mTextUnSelectColor);
                float f = this.mTabPadding;
                textView.setPadding((int) f, 0, (int) f, 0);
                if (this.mTextAllCaps) {
                    textView.setText(textView.getText().toString().toUpperCase());
                }
                int i2 = this.mTextBold;
                if (i2 == 2) {
                    textView.getPaint().setFakeBoldText(true);
                } else if (i2 == 1 && i == this.mCurrentTab) {
                    textView.getPaint().setFakeBoldText(true);
                } else if (this.mTextBold == 0) {
                    textView.getPaint().setFakeBoldText(false);
                }
            }
            i++;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.mCurrentTab = i;
        this.mCurrentPositionOffset = f;
        scrollToCurrentTab();
        invalidate();
    }

    public void setmTabsContainer(TabSelectionListener tabSelectionListener) {
        this.listener = tabSelectionListener;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.listener.selection(i);
        updateTabSelection(i);
    }

    private void scrollToCurrentTab() {
        if (this.mTabCount <= 0) {
            return;
        }
        int width = (int) (this.mCurrentPositionOffset * this.mTabsContainer.getChildAt(this.mCurrentTab).getWidth());
        int left = this.mTabsContainer.getChildAt(this.mCurrentTab).getLeft() + width;
        if (this.mCurrentTab > 0 || width > 0) {
            int width2 = left - ((getWidth() / 2) - getPaddingLeft());
            calcIndicatorRect();
            left = width2 + ((this.mTabRect.right - this.mTabRect.left) / 2);
        }
        if (left != this.mLastScrollX) {
            this.mLastScrollX = left;
            scrollTo(left, 0);
        }
    }

    private void updateTabSelection(int i) {
        int i2 = 0;
        while (i2 < this.mTabCount) {
            View childAt = this.mTabsContainer.getChildAt(i2);
            boolean z = i2 == i;
            TextView textView = (TextView) childAt.findViewById(C5508R.id.tv_tab_title);
            if (textView != null) {
                textView.setTextColor(z ? this.mTextSelectColor : this.mTextUnSelectColor);
                if (this.mTextBold == 1) {
                    textView.getPaint().setFakeBoldText(z);
                }
            }
            i2++;
        }
    }

    private void calcIndicatorRect() {
        View childAt = this.mTabsContainer.getChildAt(this.mCurrentTab);
        float left = childAt.getLeft();
        float right = childAt.getRight();
        if (this.mIndicatorStyle == 0 && this.mIndicatorWidthEqualTitle) {
            this.margin = ((right - left) - this.mTextPaint.measureText(((TextView) childAt.findViewById(C5508R.id.tv_tab_title)).getText().toString())) / 2.0f;
        }
        int i = this.mCurrentTab;
        if (i < this.mTabCount - 1) {
            View childAt2 = this.mTabsContainer.getChildAt(i + 1);
            float left2 = childAt2.getLeft();
            float right2 = childAt2.getRight();
            float f = this.mCurrentPositionOffset;
            left += (left2 - left) * f;
            right += f * (right2 - right);
            if (this.mIndicatorStyle == 0 && this.mIndicatorWidthEqualTitle) {
                float measureText = ((right2 - left2) - this.mTextPaint.measureText(((TextView) childAt2.findViewById(C5508R.id.tv_tab_title)).getText().toString())) / 2.0f;
                float f2 = this.margin;
                this.margin = f2 + (this.mCurrentPositionOffset * (measureText - f2));
            }
        }
        Rect rect = this.mIndicatorRect;
        int i2 = (int) left;
        rect.left = i2;
        int i3 = (int) right;
        rect.right = i3;
        if (this.mIndicatorStyle == 0 && this.mIndicatorWidthEqualTitle) {
            float f3 = this.margin;
            rect.left = (int) ((left + f3) - 1.0f);
            rect.right = (int) ((right - f3) - 1.0f);
        }
        Rect rect2 = this.mTabRect;
        rect2.left = i2;
        rect2.right = i3;
        if (this.mIndicatorWidth < 0.0f) {
            return;
        }
        float left3 = childAt.getLeft() + ((childAt.getWidth() - this.mIndicatorWidth) / 2.0f);
        if (this.mCurrentTab < this.mTabCount - 1) {
            left3 += this.mCurrentPositionOffset * ((childAt.getWidth() / 2) + (this.mTabsContainer.getChildAt(r2 + 1).getWidth() / 2));
        }
        Rect rect3 = this.mIndicatorRect;
        rect3.left = (int) left3;
        rect3.right = (int) (rect3.left + this.mIndicatorWidth);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode() || this.mTabCount <= 0) {
            return;
        }
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        float f = this.mDividerWidth;
        if (f > 0.0f) {
            this.mDividerPaint.setStrokeWidth(f);
            this.mDividerPaint.setColor(this.mDividerColor);
            for (int i = 0; i < this.mTabCount - 1; i++) {
                View childAt = this.mTabsContainer.getChildAt(i);
                canvas.drawLine(childAt.getRight() + paddingLeft, this.mDividerPadding, childAt.getRight() + paddingLeft, height - this.mDividerPadding, this.mDividerPaint);
            }
        }
        if (this.mUnderlineHeight > 0.0f) {
            this.mRectPaint.setColor(this.mUnderlineColor);
            if (this.mUnderlineGravity == 80) {
                float f2 = height;
                canvas.drawRect(paddingLeft, f2 - this.mUnderlineHeight, this.mTabsContainer.getWidth() + paddingLeft, f2, this.mRectPaint);
            } else {
                canvas.drawRect(paddingLeft, 0.0f, this.mTabsContainer.getWidth() + paddingLeft, this.mUnderlineHeight, this.mRectPaint);
            }
        }
        calcIndicatorRect();
        int i2 = this.mIndicatorStyle;
        if (i2 == 1) {
            if (this.mIndicatorHeight > 0.0f) {
                this.mTrianglePaint.setColor(this.mIndicatorColor);
                this.mTrianglePath.reset();
                float f3 = height;
                this.mTrianglePath.moveTo(this.mIndicatorRect.left + paddingLeft, f3);
                this.mTrianglePath.lineTo((this.mIndicatorRect.left / 2) + paddingLeft + (this.mIndicatorRect.right / 2), f3 - this.mIndicatorHeight);
                this.mTrianglePath.lineTo(paddingLeft + this.mIndicatorRect.right, f3);
                this.mTrianglePath.close();
                canvas.drawPath(this.mTrianglePath, this.mTrianglePaint);
                return;
            }
            return;
        }
        if (i2 != 2) {
            if (this.mIndicatorHeight > 0.0f) {
                this.mIndicatorDrawable.setColor(this.mIndicatorColor);
                if (this.mIndicatorGravity == 80) {
                    this.mIndicatorDrawable.setBounds(((int) this.mIndicatorMarginLeft) + paddingLeft + this.mIndicatorRect.left, (height - ((int) this.mIndicatorHeight)) - ((int) this.mIndicatorMarginBottom), (paddingLeft + this.mIndicatorRect.right) - ((int) this.mIndicatorMarginRight), height - ((int) this.mIndicatorMarginBottom));
                } else {
                    this.mIndicatorDrawable.setBounds(((int) this.mIndicatorMarginLeft) + paddingLeft + this.mIndicatorRect.left, (int) this.mIndicatorMarginTop, (paddingLeft + this.mIndicatorRect.right) - ((int) this.mIndicatorMarginRight), ((int) this.mIndicatorHeight) + ((int) this.mIndicatorMarginTop));
                }
                this.mIndicatorDrawable.setCornerRadius(this.mIndicatorCornerRadius);
                this.mIndicatorDrawable.draw(canvas);
                return;
            }
            return;
        }
        if (this.mIndicatorHeight < 0.0f) {
            this.mIndicatorHeight = (height - this.mIndicatorMarginTop) - this.mIndicatorMarginBottom;
        }
        float f4 = this.mIndicatorHeight;
        if (f4 > 0.0f) {
            float f5 = this.mIndicatorCornerRadius;
            if (f5 < 0.0f || f5 > f4 / 2.0f) {
                this.mIndicatorCornerRadius = this.mIndicatorHeight / 2.0f;
            }
            this.mIndicatorDrawable.setColor(this.mIndicatorColor);
            this.mIndicatorDrawable.setBounds(((int) this.mIndicatorMarginLeft) + paddingLeft + this.mIndicatorRect.left, (int) this.mIndicatorMarginTop, (int) ((paddingLeft + this.mIndicatorRect.right) - this.mIndicatorMarginRight), (int) (this.mIndicatorMarginTop + this.mIndicatorHeight));
            this.mIndicatorDrawable.setCornerRadius(this.mIndicatorCornerRadius);
            this.mIndicatorDrawable.draw(canvas);
        }
    }

    public void setCurrentTab(int i) {
        this.mCurrentTab = i;
        this.mViewPager.setCurrentItem(i);
    }

    public void setCurrentTab(int i, boolean z) {
        this.mCurrentTab = i;
        this.mViewPager.setCurrentItem(i, z);
    }

    public void setIndicatorStyle(int i) {
        this.mIndicatorStyle = i;
        invalidate();
    }

    public void setTabPadding(float f) {
        this.mTabPadding = dp2px(f);
        updateTabStyles();
    }

    public void setTabSpaceEqual(boolean z) {
        this.mTabSpaceEqual = z;
        updateTabStyles();
    }

    public void setTabWidth(float f) {
        this.mTabWidth = dp2px(f);
        updateTabStyles();
    }

    public void setIndicatorColor(int i) {
        this.mIndicatorColor = i;
        invalidate();
    }

    public void setIndicatorHeight(float f) {
        this.mIndicatorHeight = dp2px(f);
        invalidate();
    }

    public void setIndicatorWidth(float f) {
        this.mIndicatorWidth = dp2px(f);
        invalidate();
    }

    public void setIndicatorCornerRadius(float f) {
        this.mIndicatorCornerRadius = dp2px(f);
        invalidate();
    }

    public void setIndicatorGravity(int i) {
        this.mIndicatorGravity = i;
        invalidate();
    }

    public void setIndicatorMargin(float f, float f2, float f3, float f4) {
        this.mIndicatorMarginLeft = dp2px(f);
        this.mIndicatorMarginTop = dp2px(f2);
        this.mIndicatorMarginRight = dp2px(f3);
        this.mIndicatorMarginBottom = dp2px(f4);
        invalidate();
    }

    public void setIndicatorWidthEqualTitle(boolean z) {
        this.mIndicatorWidthEqualTitle = z;
        invalidate();
    }

    public void setUnderlineColor(int i) {
        this.mUnderlineColor = i;
        invalidate();
    }

    public void setUnderlineHeight(float f) {
        this.mUnderlineHeight = dp2px(f);
        invalidate();
    }

    public void setUnderlineGravity(int i) {
        this.mUnderlineGravity = i;
        invalidate();
    }

    public void setDividerColor(int i) {
        this.mDividerColor = i;
        invalidate();
    }

    public void setDividerWidth(float f) {
        this.mDividerWidth = dp2px(f);
        invalidate();
    }

    public void setDividerPadding(float f) {
        this.mDividerPadding = dp2px(f);
        invalidate();
    }

    public void setTextSelectsize(float f) {
        this.mTextSelectSize = sp2px(f);
        initTransformer();
        updateTabStyles();
    }

    public void setTextUnselectSize(int i) {
        this.mTextUnSelectSize = i;
        initTransformer();
        updateTabStyles();
    }

    public void setTextSelectColor(int i) {
        this.mTextSelectColor = i;
        updateTabStyles();
    }

    public void setTextUnselectColor(int i) {
        this.mTextUnSelectColor = i;
        updateTabStyles();
    }

    public void setTextBold(int i) {
        this.mTextBold = i;
        updateTabStyles();
    }

    public void setTextAllCaps(boolean z) {
        this.mTextAllCaps = z;
        updateTabStyles();
    }

    public void setSnapOnTabClick(boolean z) {
        this.mSnapOnTabClick = z;
    }

    public int getTabCount() {
        return this.mTabCount;
    }

    public int getCurrentTab() {
        return this.mCurrentTab;
    }

    public int getIndicatorStyle() {
        return this.mIndicatorStyle;
    }

    public float getTabPadding() {
        return this.mTabPadding;
    }

    public boolean isTabSpaceEqual() {
        return this.mTabSpaceEqual;
    }

    public float getTabWidth() {
        return this.mTabWidth;
    }

    public int getIndicatorColor() {
        return this.mIndicatorColor;
    }

    public float getIndicatorHeight() {
        return this.mIndicatorHeight;
    }

    public float getIndicatorWidth() {
        return this.mIndicatorWidth;
    }

    public float getIndicatorCornerRadius() {
        return this.mIndicatorCornerRadius;
    }

    public float getIndicatorMarginLeft() {
        return this.mIndicatorMarginLeft;
    }

    public float getIndicatorMarginTop() {
        return this.mIndicatorMarginTop;
    }

    public float getIndicatorMarginRight() {
        return this.mIndicatorMarginRight;
    }

    public float getIndicatorMarginBottom() {
        return this.mIndicatorMarginBottom;
    }

    public int getUnderlineColor() {
        return this.mUnderlineColor;
    }

    public float getUnderlineHeight() {
        return this.mUnderlineHeight;
    }

    public int getDividerColor() {
        return this.mDividerColor;
    }

    public float getDividerWidth() {
        return this.mDividerWidth;
    }

    public float getDividerPadding() {
        return this.mDividerPadding;
    }

    public float getTextSelectSize() {
        return this.mTextSelectSize;
    }

    public float getTextUnselectSize() {
        return this.mTextUnSelectSize;
    }

    public int getTextSelectColor() {
        return this.mTextSelectColor;
    }

    public int getTextUnselectColor() {
        return this.mTextUnSelectColor;
    }

    public int getTextBold() {
        return this.mTextBold;
    }

    public boolean isTextAllCaps() {
        return this.mTextAllCaps;
    }

    public List<IViewPagerTransformer> getTransformers() {
        return this.defaultTransformer.getTransformers();
    }

    public void setTransformers(List<IViewPagerTransformer> list) {
        this.defaultTransformer.setTransformers(list);
    }

    public TextView getTitleView(int i) {
        return (TextView) this.mTabsContainer.getChildAt(i).findViewById(C5508R.id.tv_tab_title);
    }

    public void showMsg(int i, int i2) {
        int i3 = this.mTabCount;
        if (i >= i3) {
            i = i3 - 1;
        }
        MsgView msgView = (MsgView) this.mTabsContainer.getChildAt(i).findViewById(C5508R.id.rtv_msg_tip);
        if (msgView != null) {
            UnreadMsgUtils.show(msgView, i2);
            if (this.mInitSetMap.get(i)) {
                return;
            }
            setMsgMargin(i, 4.0f, 2.0f);
            this.mInitSetMap.put(i, true);
        }
    }

    public void showDot(int i) {
        int i2 = this.mTabCount;
        if (i >= i2) {
            i = i2 - 1;
        }
        showMsg(i, 0);
    }

    public void hideMsg(int i) {
        int i2 = this.mTabCount;
        if (i >= i2) {
            i = i2 - 1;
        }
        MsgView msgView = (MsgView) this.mTabsContainer.getChildAt(i).findViewById(C5508R.id.rtv_msg_tip);
        if (msgView != null) {
            msgView.setVisibility(8);
        }
    }

    public void setMsgMargin(int i, float f, float f2) {
        float f3;
        int i2 = this.mTabCount;
        if (i >= i2) {
            i = i2 - 1;
        }
        View childAt = this.mTabsContainer.getChildAt(i);
        MsgView msgView = (MsgView) childAt.findViewById(C5508R.id.rtv_msg_tip);
        if (msgView != null) {
            TextView textView = (TextView) childAt.findViewById(C5508R.id.tv_tab_title);
            this.mTextPaint.setTextSize(i == this.mCurrentTab ? this.mTextSelectSize : this.mTextUnSelectSize);
            float measureText = this.mTextPaint.measureText(textView.getText().toString());
            float descent = this.mTextPaint.descent() - this.mTextPaint.ascent();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) msgView.getLayoutParams();
            float f4 = this.mTabWidth;
            if (f4 >= 0.0f) {
                f3 = f4 / 2.0f;
                measureText /= 2.0f;
            } else {
                f3 = this.mTabPadding;
            }
            marginLayoutParams.leftMargin = (int) (f3 + measureText + dp2px(f));
            int i3 = this.mHeight;
            marginLayoutParams.topMargin = i3 > 0 ? (((int) (i3 - descent)) / 2) - dp2px(f2) : 0;
            msgView.setLayoutParams(marginLayoutParams);
        }
    }

    public MsgView getMsgView(int i) {
        int i2 = this.mTabCount;
        if (i >= i2) {
            i = i2 - 1;
        }
        return (MsgView) this.mTabsContainer.getChildAt(i).findViewById(C5508R.id.rtv_msg_tip);
    }

    public TextView getTitle(int i) {
        int i2 = this.mTabCount;
        if (i >= i2) {
            i = i2 - 1;
        }
        View childAt = this.mTabsContainer.getChildAt(i);
        if (childAt == null) {
            return null;
        }
        return (TextView) childAt.findViewById(C5508R.id.tv_tab_title);
    }

    public void setOnTabSelectListener(OnTabSelectListener onTabSelectListener) {
        this.mListener = onTabSelectListener;
    }

    /* loaded from: classes5.dex */
    class InnerPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;
        private String[] titles;

        @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        public InnerPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> arrayList, String[] strArr) {
            super(fragmentManager);
            this.fragments = arrayList;
            this.titles = strArr;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.fragments.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return this.titles[i];
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            return this.fragments.get(i);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("mCurrentTab", this.mCurrentTab);
        return bundle;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mCurrentTab = bundle.getInt("mCurrentTab");
            parcelable = bundle.getParcelable("instanceState");
            if (this.mCurrentTab != 0 && this.mTabsContainer.getChildCount() > 0) {
                updateTabSelection(this.mCurrentTab);
                scrollToCurrentTab();
            }
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected int dp2px(float f) {
        return (int) ((f * this.mContext.getResources().getDisplayMetrics().density) + 0.5f);
    }

    protected int sp2px(float f) {
        return (int) ((f * this.mContext.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}
