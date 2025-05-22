package org.hash.mock.debug.view.sheet.sweetpick;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class DimEffect implements Effect {
    private float Value;

    public DimEffect(float f) {
        this.Value = f;
    }

    @Override // org.hash.mock.debug.view.sheet.sweetpick.Effect
    public void effect(ViewGroup viewGroup, ImageView imageView) {
        imageView.setBackgroundColor(Color.argb((int) (this.Value * 150.0f), 150, 150, 150));
    }
}
