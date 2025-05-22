package com.pudutech.mpmodule.p060ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.mpmodule.C5441R;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class CStateRecyclerView extends FrameLayout {
    private onBtnClickListener mBtnClickListener;
    private Context mContext;
    private View mEmptyView;
    private InnerRecyclerView mRecyclerView;
    private onViewEmptyCallback mViewEmptyCallback;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface onBtnClickListener {
        void onBtnClick();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface onViewEmptyCallback {
        void onViewEmpty();
    }

    public CStateRecyclerView(Context context) {
        this(context, null);
    }

    public CStateRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CStateRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        init();
    }

    private void init() {
        this.mRecyclerView = new InnerRecyclerView(this, this.mContext);
        addView(this.mRecyclerView);
    }

    public void showEmpty(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            strArr = new String[]{getResources().getString(C5441R.string.pdStr10_53)};
        }
        showEmptyView(strArr);
    }

    private void showEmptyView(String... strArr) {
        if (this.mEmptyView == null) {
            this.mEmptyView = new ListEmptyTipView(this.mContext);
            addView(this.mEmptyView);
        }
        setEmptyViewContent(this.mEmptyView, strArr);
        this.mEmptyView.setVisibility(0);
        this.mRecyclerView.showEmpty();
    }

    public void showData() {
        View view = this.mEmptyView;
        if (view != null) {
            view.setVisibility(8);
        }
        this.mRecyclerView.showData();
    }

    private void setEmptyViewContent(View view, String... strArr) {
        ((TextView) view.findViewById(C5441R.id.empty_tip_text)).setText(strArr[0]);
        Button button = (Button) view.findViewById(C5441R.id.empty_view_btn_add_music);
        if (strArr.length > 1) {
            button.getPaint().setFakeBoldText(true);
            button.setText(strArr[1]);
            button.setVisibility(0);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mpmodule.ui.widget.CStateRecyclerView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    CStateRecyclerView.this.mBtnClickListener.onBtnClick();
                }
            });
            return;
        }
        button.setVisibility(8);
    }

    public InnerRecyclerView getRealRecyclerView() {
        return this.mRecyclerView;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public final class InnerRecyclerView extends RecyclerView {
        private final RecyclerView.AdapterDataObserver observer;

        public InnerRecyclerView(CStateRecyclerView cStateRecyclerView, Context context) {
            this(cStateRecyclerView, context, null);
        }

        public InnerRecyclerView(CStateRecyclerView cStateRecyclerView, Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        public InnerRecyclerView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.observer = new RecyclerView.AdapterDataObserver() { // from class: com.pudutech.mpmodule.ui.widget.CStateRecyclerView.InnerRecyclerView.1
                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onChanged() {
                    InnerRecyclerView.this.checkIsEmpty();
                }

                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onItemRangeInserted(int i2, int i3) {
                    InnerRecyclerView.this.checkIsEmpty();
                }

                @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
                public void onItemRangeRemoved(int i2, int i3) {
                    InnerRecyclerView.this.checkIsEmpty();
                }
            };
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void setAdapter(RecyclerView.Adapter adapter) {
            if (adapter != null) {
                if (adapter.hasObservers()) {
                    adapter.unregisterAdapterDataObserver(this.observer);
                }
                super.setAdapter(adapter);
                adapter.registerAdapterDataObserver(this.observer);
                checkIsEmpty();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void checkIsEmpty() {
            if (getAdapter() == null || getAdapter().getItemCount() != 0) {
                return;
            }
            if (CStateRecyclerView.this.mViewEmptyCallback != null) {
                CStateRecyclerView.this.mViewEmptyCallback.onViewEmpty();
            } else {
                CStateRecyclerView.this.showEmpty(new String[0]);
            }
        }

        void showData() {
            setVisibility(0);
        }

        void showEmpty() {
            setVisibility(8);
        }
    }

    public void setmViewEmptyCallback(onViewEmptyCallback onviewemptycallback) {
        this.mViewEmptyCallback = onviewemptycallback;
    }

    public void setOnBtnClickListener(onBtnClickListener onbtnclicklistener) {
        this.mBtnClickListener = onbtnclicklistener;
    }
}
