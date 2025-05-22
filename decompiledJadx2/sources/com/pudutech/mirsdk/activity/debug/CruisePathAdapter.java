package com.pudutech.mirsdk.activity.debug;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruisePathAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/debug/CruisePathAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/pudutech/mirsdk/activity/debug/CruisePathAdapter$MyHolder;", "paths", "", "", "context", "Landroid/content/Context;", "(Ljava/util/List;Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "getPaths", "()Ljava/util/List;", "setPaths", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", RequestParameters.POSITION, "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MyHolder", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruisePathAdapter extends RecyclerView.Adapter<MyHolder> {
    private Context context;
    private List<String> paths;

    public CruisePathAdapter(List<String> paths, Context context) {
        Intrinsics.checkParameterIsNotNull(paths, "paths");
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.paths = paths;
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<String> getPaths() {
        return this.paths;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    public final void setPaths(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.paths = list;
    }

    /* compiled from: CruisePathAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/activity/debug/CruisePathAdapter$MyHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "textView", "Landroid/widget/TextView;", "getTextView", "()Landroid/widget/TextView;", "setTextView", "(Landroid/widget/TextView;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class MyHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyHolder(View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            this.textView = (TextView) itemView;
        }

        public final TextView getTextView() {
            return this.textView;
        }

        public final void setTextView(TextView textView) {
            Intrinsics.checkParameterIsNotNull(textView, "<set-?>");
            this.textView = textView;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        return new MyHolder(new TextView(this.context));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<String> list = this.paths;
        return (list != null ? Integer.valueOf(list.size()) : null).intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyHolder holder, int position) {
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        holder.getTextView().setText(this.paths.get(position));
    }
}
