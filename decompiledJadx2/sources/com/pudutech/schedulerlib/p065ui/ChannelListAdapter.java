package com.pudutech.schedulerlib.p065ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.p065ui.ChannelListAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: ChannelListAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001d\u001eB#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0014\u001a\u00020\u0007H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J\u0018\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0007H\u0016R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/ChannelListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/pudutech/schedulerlib/ui/ChannelListAdapter$CViewHolder;", "context", "Landroid/content/Context;", "channelList", "", "", "touchListener", "Lcom/pudutech/schedulerlib/ui/ChannelListAdapter$ButtonTouchCallback;", "(Landroid/content/Context;Ljava/util/List;Lcom/pudutech/schedulerlib/ui/ChannelListAdapter$ButtonTouchCallback;)V", "callback", "getChannelList", "()Ljava/util/List;", "setChannelList", "(Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getItemCount", "onBindViewHolder", "", "holder", RequestParameters.POSITION, "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ButtonTouchCallback", "CViewHolder", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ChannelListAdapter extends RecyclerView.Adapter<CViewHolder> {
    private final ButtonTouchCallback callback;
    private List<Integer> channelList;
    private Context context;

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: ChannelListAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/ChannelListAdapter$ButtonTouchCallback;", "", "touch", "", "id", "", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public interface ButtonTouchCallback {
        void touch(int id);
    }

    public ChannelListAdapter(Context context, List<Integer> channelList, ButtonTouchCallback touchListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(channelList, "channelList");
        Intrinsics.checkParameterIsNotNull(touchListener, "touchListener");
        this.context = context;
        this.channelList = channelList;
        this.callback = touchListener;
    }

    public final List<Integer> getChannelList() {
        return this.channelList;
    }

    public final Context getContext() {
        return this.context;
    }

    public final void setChannelList(List<Integer> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.channelList = list;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.channelList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(CViewHolder holder, final int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        holder.getChannelTextView().setText(String.valueOf(this.channelList.get(position).intValue()));
        holder.getChannelTextView().setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.schedulerlib.ui.ChannelListAdapter$onBindViewHolder$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChannelListAdapter.ButtonTouchCallback buttonTouchCallback;
                buttonTouchCallback = ChannelListAdapter.this.callback;
                buttonTouchCallback.touch(position);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public CViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View v = LayoutInflater.from(this.context).inflate(C5725R.layout.schedulerlib_item_channel, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(v, "v");
        return new CViewHolder(v);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: ChannelListAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/schedulerlib/ui/ChannelListAdapter$CViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "channelTextView", "Landroid/widget/Button;", "getChannelTextView", "()Landroid/widget/Button;", "setChannelTextView", "(Landroid/widget/Button;)V", "schedulerlib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class CViewHolder extends RecyclerView.ViewHolder {
        private Button channelTextView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            View findViewById = itemView.findViewById(C5725R.id.tv_channel);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "itemView.findViewById(R.id.tv_channel)");
            this.channelTextView = (Button) findViewById;
        }

        public final Button getChannelTextView() {
            return this.channelTextView;
        }

        public final void setChannelTextView(Button button) {
            Intrinsics.checkParameterIsNotNull(button, "<set-?>");
            this.channelTextView = button;
        }
    }
}
