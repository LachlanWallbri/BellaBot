package net.lucode.hackware.magicindicator.abs;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes8.dex */
public interface IPagerNavigator {
    void notifyDataSetChanged();

    void onAttachToMagicIndicator();

    void onDetachFromMagicIndicator();

    void onPageScrollStateChanged(int i);

    void onPageScrolled(int i, float f, int i2);

    void onPageSelected(int i);
}
