package androidx.constraintlayout.solver.widgets;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f77x;

    /* renamed from: y */
    public int f78y;

    public void setBounds(int i, int i2, int i3, int i4) {
        this.f77x = i;
        this.f78y = i2;
        this.width = i3;
        this.height = i4;
    }

    void grow(int i, int i2) {
        this.f77x -= i;
        this.f78y -= i2;
        this.width += i * 2;
        this.height += i2 * 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean intersects(Rectangle rectangle) {
        int i;
        int i2;
        int i3 = this.f77x;
        int i4 = rectangle.f77x;
        return i3 >= i4 && i3 < i4 + rectangle.width && (i = this.f78y) >= (i2 = rectangle.f78y) && i < i2 + rectangle.height;
    }

    public boolean contains(int i, int i2) {
        int i3;
        int i4 = this.f77x;
        return i >= i4 && i < i4 + this.width && i2 >= (i3 = this.f78y) && i2 < i3 + this.height;
    }

    public int getCenterX() {
        return (this.f77x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f78y + this.height) / 2;
    }
}
