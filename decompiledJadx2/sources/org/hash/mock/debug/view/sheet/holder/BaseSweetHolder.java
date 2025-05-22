package org.hash.mock.debug.view.sheet.holder;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class BaseSweetHolder<T> extends RecyclerView.ViewHolder implements ISweetHolder<T> {
    public BaseSweetHolder(View view) {
        super(view);
    }

    @Override // org.hash.mock.debug.view.sheet.holder.ISweetHolder
    public void bind(T t) {
        if (getContent() != null) {
            getContent().setTag(Integer.valueOf(getAdapterPosition()));
        }
    }

    @Override // org.hash.mock.debug.view.sheet.holder.ISweetHolder
    public View getContent() {
        return this.itemView;
    }
}
