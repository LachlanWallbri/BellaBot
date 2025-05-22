package org.hash.mock.debug.view.sheet.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;
import org.hash.mock.debug.view.sheet.sweetpick.MenuListViewHandler;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class ViewpagerAdapter extends PagerAdapter {
    List<MenuListViewHandler> mMenuListViewHandlers;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ViewpagerAdapter(List<MenuListViewHandler> list) {
        this.mMenuListViewHandlers = null;
        this.mMenuListViewHandlers = list;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mMenuListViewHandlers.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.mMenuListViewHandlers.get(i).onCreateView(viewGroup));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView(this.mMenuListViewHandlers.get(i).onCreateView(viewGroup));
        return this.mMenuListViewHandlers.get(i).onCreateView(viewGroup);
    }
}
