package org.hash.mock.debug.view.sheet.entity;

import android.widget.CompoundButton;
import androidx.core.view.ViewCompat;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class SwitchEntity extends SimpleEntity {
    public CharSequence info;
    private boolean isChecked;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    public CharSequence title;
    public int titleColor = ViewCompat.MEASURED_STATE_MASK;

    @Override // org.hash.mock.debug.view.sheet.entity.SimpleEntity
    public int getTitleColor() {
        return this.titleColor;
    }

    @Override // org.hash.mock.debug.view.sheet.entity.SimpleEntity
    public SwitchEntity setTitleColor(int i) {
        this.titleColor = i;
        return this;
    }

    @Override // org.hash.mock.debug.view.sheet.entity.SimpleEntity
    public CharSequence getTitle() {
        return this.title;
    }

    @Override // org.hash.mock.debug.view.sheet.entity.SimpleEntity
    public SwitchEntity setTitle(CharSequence charSequence) {
        this.title = charSequence;
        return this;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public SwitchEntity setChecked(boolean z) {
        this.isChecked = z;
        return this;
    }

    public CharSequence getInfo() {
        return this.info;
    }

    public SwitchEntity setInfo(CharSequence charSequence) {
        this.info = charSequence;
        return this;
    }

    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return this.onCheckedChangeListener;
    }

    public SwitchEntity setOnCheckedChangeListener(final CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: org.hash.mock.debug.view.sheet.entity.SwitchEntity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (compoundButton.isPressed()) {
                    SwitchEntity.this.isChecked = z;
                    onCheckedChangeListener.onCheckedChanged(compoundButton, z);
                }
            }
        };
        return this;
    }
}
