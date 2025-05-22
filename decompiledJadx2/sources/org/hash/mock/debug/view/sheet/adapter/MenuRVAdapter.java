package org.hash.mock.debug.view.sheet.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import java.util.List;
import org.hash.mock.debug.view.sheet.entity.RadioEntity;
import org.hash.mock.debug.view.sheet.entity.SimpleEntity;
import org.hash.mock.debug.view.sheet.entity.SwitchEntity;
import org.hash.mock.debug.view.sheet.entity.TextEntity;
import org.hash.mock.debug.view.sheet.holder.BaseSweetHolder;
import org.hash.mock.debug.view.sheet.holder.RadioHolder;
import org.hash.mock.debug.view.sheet.holder.SimpleHolder;
import org.hash.mock.debug.view.sheet.holder.SwitchHolder;
import org.hash.mock.debug.view.sheet.holder.TextHolder;
import org.hash.mock.debug.view.sheet.listener.SingleClickListener;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class MenuRVAdapter extends RecyclerView.Adapter<BaseSweetHolder> {
    private static final int TYPE_CHECK = 1;
    private static final int TYPE_RADIO = 3;
    private static final int TYPE_SIMPLE = 0;
    private static final int TYPE_TEXT = 2;
    private List<SimpleEntity> mDataList;
    private boolean mIsAnimation;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private SingleClickListener mSingleClickListener = new SingleClickListener(new View.OnClickListener() { // from class: org.hash.mock.debug.view.sheet.adapter.MenuRVAdapter.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            if (MenuRVAdapter.this.mOnItemClickListener != null) {
                MenuRVAdapter.this.mOnItemClickListener.onItemClick(null, view, intValue, intValue);
            }
        }
    });

    public MenuRVAdapter(List<SimpleEntity> list) {
        this.mDataList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public BaseSweetHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new SwitchHolder(viewGroup);
        }
        if (i == 2) {
            return new TextHolder(viewGroup);
        }
        if (i == 3) {
            return new RadioHolder(viewGroup);
        }
        return new SimpleHolder(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.mDataList.get(i) instanceof SwitchEntity) {
            return 1;
        }
        if (this.mDataList.get(i) instanceof TextEntity) {
            return 2;
        }
        return this.mDataList.get(i) instanceof RadioEntity ? 3 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseSweetHolder baseSweetHolder, int i) {
        baseSweetHolder.getContent().setOnClickListener(this.mSingleClickListener);
        baseSweetHolder.bind(this.mDataList.get(i));
        if (this.mIsAnimation) {
            animation(baseSweetHolder);
        }
    }

    private void animation(RecyclerView.ViewHolder viewHolder) {
        ViewHelper.setAlpha(viewHolder.itemView, 0.0f);
        ViewHelper.setTranslationY(viewHolder.itemView, 300.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(viewHolder.itemView, "translationY", 500.0f, 0.0f);
        ofFloat.setDuration(300L);
        ofFloat.setInterpolator(new OvershootInterpolator(1.6f));
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(viewHolder.itemView, "alpha", 0.0f, 1.0f);
        ofFloat2.setDuration(100L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.setStartDelay(viewHolder.getAdapterPosition() * 30);
        animatorSet.start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDataList.size();
    }

    public void notifyAnimation() {
        this.mIsAnimation = true;
        notifyDataSetChanged();
    }

    public void notifyNoAimation() {
        this.mIsAnimation = false;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
