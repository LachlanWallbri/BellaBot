package org.hash.mock.debug.view.sheet.sweetpick;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.hash.mock.debug.view.sheet.entity.SimpleEntity;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class SweetSheet {
    public static final String Tag = "SweetSheet";
    private Delegate mDelegate;
    private Effect mEffect = new DimEffect(8.0f);
    private SparseArray<ArrayList<SimpleEntity>> mMenuEntities;
    private OnMenuItemClickListener mOnMenuItemClickListener;
    private ViewGroup mParentVG;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public interface OnMenuItemClickListener {
        boolean onItemClick(View view, SimpleEntity simpleEntity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public enum Status {
        SHOW,
        SHOWING,
        DISMISS,
        DISMISSING
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public enum Type {
        RecyclerView,
        Viewpager
    }

    public SweetSheet(ViewGroup viewGroup) {
        this.mParentVG = viewGroup;
    }

    public void setDelegate(Delegate delegate) {
        this.mDelegate = delegate;
        this.mDelegate.init(this.mParentVG);
        setup();
    }

    public Delegate getDelegate() {
        return this.mDelegate;
    }

    private void setup() {
        OnMenuItemClickListener onMenuItemClickListener = this.mOnMenuItemClickListener;
        if (onMenuItemClickListener != null) {
            this.mDelegate.setOnMenuItemClickListener(onMenuItemClickListener);
        }
        SparseArray<ArrayList<SimpleEntity>> sparseArray = this.mMenuEntities;
        if (sparseArray != null) {
            this.mDelegate.setMenuList(sparseArray);
        }
        this.mDelegate.setBackgroundEffect(this.mEffect);
        this.mDelegate.setBackgroundClickEnable(true);
    }

    public void setBackgroundEffect(Effect effect) {
        Delegate delegate = this.mDelegate;
        if (delegate != null) {
            delegate.setBackgroundEffect(effect);
        } else {
            this.mEffect = effect;
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        Delegate delegate = this.mDelegate;
        if (delegate != null) {
            delegate.setOnMenuItemClickListener(onMenuItemClickListener);
        } else {
            this.mOnMenuItemClickListener = onMenuItemClickListener;
        }
    }

    public void show() {
        Delegate delegate = this.mDelegate;
        if (delegate != null) {
            delegate.show();
        } else {
            Log.e(Tag, "you must setDelegate before");
        }
    }

    public void dismiss() {
        Delegate delegate = this.mDelegate;
        if (delegate != null) {
            delegate.dismiss();
        } else {
            Log.e(Tag, "you must setDelegate before");
        }
    }

    public void toggle() {
        Delegate delegate = this.mDelegate;
        if (delegate != null) {
            delegate.toggle();
        } else {
            Log.e(Tag, "you must setDelegate before");
        }
    }

    public boolean isShow() {
        Delegate delegate = this.mDelegate;
        return delegate != null && (delegate.getStatus() == Status.SHOW || this.mDelegate.getStatus() == Status.SHOWING);
    }

    public void setMenuList(SparseArray<ArrayList<SimpleEntity>> sparseArray) {
        Delegate delegate = this.mDelegate;
        if (delegate != null) {
            delegate.setMenuList(sparseArray);
        } else {
            this.mMenuEntities = sparseArray;
        }
    }
}
