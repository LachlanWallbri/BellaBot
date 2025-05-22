package com.pudutech.peanut.robot_ui.widget.tablayout.transformer;

import android.view.View;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.pudutech.peanut.robot_ui.widget.tablayout.SlidingScaleTabLayout;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class TabScaleTransformer implements ViewPager.PageTransformer {
    private PagerAdapter pagerAdapter;
    private SlidingScaleTabLayout slidingScaleTabLayout;
    private float textSelectSize;
    private float textUnSelectSize;
    private List<IViewPagerTransformer> transformers = null;

    public TabScaleTransformer(SlidingScaleTabLayout slidingScaleTabLayout, PagerAdapter pagerAdapter, float f, float f2) {
        this.slidingScaleTabLayout = slidingScaleTabLayout;
        this.pagerAdapter = pagerAdapter;
        this.textSelectSize = f;
        this.textUnSelectSize = f2;
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, final float f) {
        final TextView title = this.slidingScaleTabLayout.getTitle(this.pagerAdapter.getItemPosition(view));
        if (title == null) {
            return;
        }
        title.post(new Runnable() { // from class: com.pudutech.peanut.robot_ui.widget.tablayout.transformer.TabScaleTransformer.1
            @Override // java.lang.Runnable
            public void run() {
                float f2 = f;
                if (f2 < -1.0f || f2 > 1.0f) {
                    title.setTextSize(0, TabScaleTransformer.this.textUnSelectSize);
                } else {
                    title.setTextSize(0, TabScaleTransformer.this.textSelectSize - Math.abs((TabScaleTransformer.this.textSelectSize - TabScaleTransformer.this.textUnSelectSize) * f));
                }
            }
        });
        List<IViewPagerTransformer> list = this.transformers;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<IViewPagerTransformer> it = this.transformers.iterator();
        while (it.hasNext()) {
            it.next().transformPage(view, f);
        }
    }

    public List<IViewPagerTransformer> getTransformers() {
        return this.transformers;
    }

    public void setTransformers(List<IViewPagerTransformer> list) {
        this.transformers = list;
    }
}
