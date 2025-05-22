package org.hash.mock.debug.view.sheet.holder;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.pudutech.disinfect.baselib.C4429R;
import org.hash.mock.debug.view.sheet.entity.SwitchEntity;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class SwitchHolder extends BaseSweetHolder<SwitchEntity> {
    private TextView info;
    private RelativeLayout itemRl;

    /* renamed from: iv */
    public Switch f10009iv;
    private TextView nameTV;

    public SwitchHolder(View view) {
        super(getItemView(view));
        setIsRecyclable(false);
        this.f10009iv = (Switch) this.itemView.findViewById(C4429R.id.f5001iv);
        this.nameTV = (TextView) this.itemView.findViewById(C4429R.id.nameTV);
        this.itemRl = (RelativeLayout) this.itemView.findViewById(C4429R.id.itemRl);
        this.info = (TextView) this.itemView.findViewById(C4429R.id.infoTV);
    }

    @Override // org.hash.mock.debug.view.sheet.holder.BaseSweetHolder, org.hash.mock.debug.view.sheet.holder.ISweetHolder
    public View getContent() {
        return this.itemRl;
    }

    private static View getItemView(View view) {
        return LayoutInflater.from(view.getContext()).inflate(C4429R.layout.debug_item_horizon_check, (ViewGroup) null, false);
    }

    @Override // org.hash.mock.debug.view.sheet.holder.BaseSweetHolder, org.hash.mock.debug.view.sheet.holder.ISweetHolder
    public void bind(SwitchEntity switchEntity) {
        super.bind((SwitchHolder) switchEntity);
        this.nameTV.setText(switchEntity.getTitle());
        this.nameTV.setTextColor(switchEntity.getTitleColor());
        if (!TextUtils.isEmpty(switchEntity.getInfo())) {
            this.info.setVisibility(0);
            this.info.setText(switchEntity.getInfo());
        } else {
            this.info.setVisibility(8);
        }
        this.f10009iv.setChecked(switchEntity.isChecked());
        this.f10009iv.setOnCheckedChangeListener(switchEntity.getOnCheckedChangeListener());
    }
}
