package com.youth.banner.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.youth.banner.C5961R;
import com.youth.banner.holder.IViewHolder;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public abstract class BannerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements IViewHolder<T, VH> {
    protected List<T> mDatas = new ArrayList();
    private int mIncreaseCount = 2;
    private OnBannerListener<T> mOnBannerListener;
    private VH mViewHolder;

    public BannerAdapter(List<T> list) {
        setDatas(list);
    }

    public void setDatas(List<T> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.mDatas = list;
        notifyDataSetChanged();
    }

    public T getData(int i) {
        return this.mDatas.get(i);
    }

    public T getRealData(int i) {
        return this.mDatas.get(getRealPosition(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(VH vh, int i) {
        this.mViewHolder = vh;
        final int realPosition = getRealPosition(i);
        final T t = this.mDatas.get(realPosition);
        vh.itemView.setTag(C5961R.id.banner_data_key, t);
        vh.itemView.setTag(C5961R.id.banner_pos_key, Integer.valueOf(realPosition));
        onBindView(vh, this.mDatas.get(realPosition), realPosition, getRealCount());
        if (this.mOnBannerListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.youth.banner.adapter.-$$Lambda$BannerAdapter$wNFq7ZQXQPi8uE7jfosVT1VYptk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BannerAdapter.this.lambda$onBindViewHolder$0$BannerAdapter(t, realPosition, view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$BannerAdapter(Object obj, int i, View view) {
        this.mOnBannerListener.OnBannerClick(obj, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        final VH vh = (VH) onCreateHolder(viewGroup, i);
        vh.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.youth.banner.adapter.-$$Lambda$BannerAdapter$2Gkka5K2rV0zY8zScMXMzHFoYpg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BannerAdapter.this.lambda$onCreateViewHolder$1$BannerAdapter(vh, view);
            }
        });
        return vh;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void lambda$onCreateViewHolder$1$BannerAdapter(RecyclerView.ViewHolder viewHolder, View view) {
        if (this.mOnBannerListener != null) {
            this.mOnBannerListener.OnBannerClick(viewHolder.itemView.getTag(C5961R.id.banner_data_key), ((Integer) viewHolder.itemView.getTag(C5961R.id.banner_pos_key)).intValue());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getRealCount() > 1 ? getRealCount() + this.mIncreaseCount : getRealCount();
    }

    public int getRealCount() {
        List<T> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getRealPosition(int i) {
        return BannerUtils.getRealPosition(this.mIncreaseCount == 2, i, getRealCount());
    }

    public void setOnBannerListener(OnBannerListener<T> onBannerListener) {
        this.mOnBannerListener = onBannerListener;
    }

    public VH getViewHolder() {
        return this.mViewHolder;
    }

    public void setIncreaseCount(int i) {
        this.mIncreaseCount = i;
    }
}
