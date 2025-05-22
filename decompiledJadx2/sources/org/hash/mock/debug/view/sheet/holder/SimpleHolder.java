package org.hash.mock.debug.view.sheet.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.pudutech.disinfect.baselib.C4429R;
import org.hash.mock.debug.view.sheet.entity.SimpleEntity;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class SimpleHolder extends BaseSweetHolder<SimpleEntity> {
    public RelativeLayout itemRl;

    /* renamed from: iv */
    public ImageView f10008iv;
    private TextView nameTV;

    public SimpleHolder(View view) {
        super(getItemView(view));
        this.f10008iv = (ImageView) this.itemView.findViewById(C4429R.id.icon);
        this.nameTV = (TextView) this.itemView.findViewById(C4429R.id.nameTV);
        this.itemRl = (RelativeLayout) this.itemView.findViewById(C4429R.id.itemRl);
    }

    @Override // org.hash.mock.debug.view.sheet.holder.BaseSweetHolder, org.hash.mock.debug.view.sheet.holder.ISweetHolder
    public View getContent() {
        return this.itemRl;
    }

    private static View getItemView(View view) {
        return LayoutInflater.from(view.getContext()).inflate(C4429R.layout.debug_item_horizon_rv, (ViewGroup) null, false);
    }

    @Override // org.hash.mock.debug.view.sheet.holder.BaseSweetHolder, org.hash.mock.debug.view.sheet.holder.ISweetHolder
    public void bind(SimpleEntity simpleEntity) {
        super.bind((SimpleHolder) simpleEntity);
        this.itemRl.setTag(Integer.valueOf(getAdapterPosition()));
        if (simpleEntity.iconId != 0) {
            this.f10008iv.setVisibility(0);
            this.f10008iv.setImageResource(simpleEntity.iconId);
        } else if (simpleEntity.icon != null) {
            this.f10008iv.setVisibility(0);
            this.f10008iv.setImageDrawable(simpleEntity.icon);
        } else {
            this.f10008iv.setVisibility(8);
        }
        this.nameTV.setText(simpleEntity.title);
        this.nameTV.setTextColor(simpleEntity.titleColor);
    }
}
