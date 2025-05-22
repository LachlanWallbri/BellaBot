package org.hash.mock.debug.view.sheet.entity;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.view.ViewCompat;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class SimpleEntity {
    public Drawable icon;
    public int iconId;
    private View.OnClickListener onClickListener;
    public CharSequence title;
    public int titleColor = ViewCompat.MEASURED_STATE_MASK;

    public View.OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public SimpleEntity setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public int getIconId() {
        return this.iconId;
    }

    public SimpleEntity setIconId(int i) {
        this.iconId = i;
        return this;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public SimpleEntity setTitleColor(int i) {
        this.titleColor = i;
        return this;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public SimpleEntity setTitle(CharSequence charSequence) {
        this.title = charSequence;
        return this;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public SimpleEntity setIcon(Drawable drawable) {
        this.icon = drawable;
        return this;
    }
}
