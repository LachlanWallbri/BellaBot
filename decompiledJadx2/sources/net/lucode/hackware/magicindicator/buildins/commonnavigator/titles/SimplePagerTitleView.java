package net.lucode.hackware.magicindicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.widget.TextView;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes8.dex */
public class SimplePagerTitleView extends TextView implements IMeasurablePagerTitleView {
    protected int mNormalColor;
    protected int mSelectedColor;

    public void onEnter(int i, int i2, float f, boolean z) {
    }

    public void onLeave(int i, int i2, float f, boolean z) {
    }

    public SimplePagerTitleView(Context context) {
        super(context, null);
        init(context);
    }

    private void init(Context context) {
        setGravity(17);
        int dip2px = UIUtil.dip2px(context, 10.0d);
        setPadding(dip2px, 0, dip2px, 0);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    public void onSelected(int i, int i2) {
        setTextColor(this.mSelectedColor);
    }

    public void onDeselected(int i, int i2) {
        setTextColor(this.mNormalColor);
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView
    public int getContentLeft() {
        String charSequence;
        Rect rect = new Rect();
        if (getText().toString().contains("\n")) {
            charSequence = "";
            for (String str : getText().toString().split("\\n")) {
                if (str.length() > charSequence.length()) {
                    charSequence = str;
                }
            }
        } else {
            charSequence = getText().toString();
        }
        getPaint().getTextBounds(charSequence, 0, charSequence.length(), rect);
        return (getLeft() + (getWidth() / 2)) - (rect.width() / 2);
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView
    public int getContentTop() {
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) ((getHeight() / 2) - ((fontMetrics.bottom - fontMetrics.top) / 2.0f));
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView
    public int getContentRight() {
        String charSequence;
        Rect rect = new Rect();
        if (getText().toString().contains("\n")) {
            charSequence = "";
            for (String str : getText().toString().split("\\n")) {
                if (str.length() > charSequence.length()) {
                    charSequence = str;
                }
            }
        } else {
            charSequence = getText().toString();
        }
        getPaint().getTextBounds(charSequence, 0, charSequence.length(), rect);
        return getLeft() + (getWidth() / 2) + (rect.width() / 2);
    }

    @Override // net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView
    public int getContentBottom() {
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        return (int) ((getHeight() / 2) + ((fontMetrics.bottom - fontMetrics.top) / 2.0f));
    }

    public int getSelectedColor() {
        return this.mSelectedColor;
    }

    public void setSelectedColor(int i) {
        this.mSelectedColor = i;
    }

    public int getNormalColor() {
        return this.mNormalColor;
    }

    public void setNormalColor(int i) {
        this.mNormalColor = i;
    }
}
