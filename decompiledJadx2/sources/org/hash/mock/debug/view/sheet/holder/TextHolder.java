package org.hash.mock.debug.view.sheet.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.pudutech.disinfect.baselib.C4429R;
import org.hash.mock.debug.view.sheet.entity.TextEntity;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class TextHolder extends BaseSweetHolder<TextEntity> {
    private TextView nameTV;

    public TextHolder(View view) {
        super(getItemView(view));
        this.nameTV = (TextView) this.itemView.findViewById(C4429R.id.rv_title);
    }

    private static View getItemView(View view) {
        return LayoutInflater.from(view.getContext()).inflate(C4429R.layout.debug_item_header, (ViewGroup) view, false);
    }

    @Override // org.hash.mock.debug.view.sheet.holder.BaseSweetHolder, org.hash.mock.debug.view.sheet.holder.ISweetHolder
    public void bind(TextEntity textEntity) {
        super.bind((TextHolder) textEntity);
        this.nameTV.setText(textEntity.title);
    }
}
