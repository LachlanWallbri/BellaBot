package org.hash.mock.debug.view.sheet.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.pudutech.disinfect.baselib.C4429R;
import org.hash.mock.debug.view.radio.GroupRadioView;
import org.hash.mock.debug.view.sheet.entity.RadioEntity;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class RadioHolder extends BaseSweetHolder<RadioEntity> {
    private final GroupRadioView hostSpace;
    private final TextView nameTV;

    public RadioHolder(View view) {
        super(getItemView(view));
        this.nameTV = (TextView) this.itemView.findViewById(C4429R.id.nameTV);
        this.hostSpace = (GroupRadioView) this.itemView.findViewById(C4429R.id.radio_group_host);
    }

    private static View getItemView(View view) {
        return LayoutInflater.from(view.getContext()).inflate(C4429R.layout.debug_item_radio, (ViewGroup) view, false);
    }

    @Override // org.hash.mock.debug.view.sheet.holder.BaseSweetHolder, org.hash.mock.debug.view.sheet.holder.ISweetHolder
    public void bind(RadioEntity radioEntity) {
        super.bind((RadioHolder) radioEntity);
        this.nameTV.setText(radioEntity.title);
        this.nameTV.setTextColor(radioEntity.titleColor);
        this.hostSpace.setCheck(radioEntity.getCheckPosition());
        this.hostSpace.setGroupBtnName(radioEntity.getGroupBtnName());
        this.hostSpace.setOnGroupBtnClickListener(radioEntity.getOnCheckedChangeListener());
    }
}
