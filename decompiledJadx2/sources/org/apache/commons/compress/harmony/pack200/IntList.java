package org.apache.commons.compress.harmony.pack200;

import java.util.Arrays;

/* loaded from: classes9.dex */
public class IntList {
    private int[] array;
    private int firstIndex;
    private int lastIndex;
    private int modCount;

    public IntList() {
        this(10);
    }

    public IntList(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.lastIndex = 0;
        this.firstIndex = 0;
        this.array = new int[i];
    }

    public boolean add(int i) {
        if (this.lastIndex == this.array.length) {
            growAtEnd(1);
        }
        int[] iArr = this.array;
        int i2 = this.lastIndex;
        this.lastIndex = i2 + 1;
        iArr[i2] = i;
        this.modCount++;
        return true;
    }

    public void add(int i, int i2) {
        int i3 = this.lastIndex;
        int i4 = this.firstIndex;
        int i5 = i3 - i4;
        if (i > 0 && i < i5) {
            if (i4 == 0 && i3 == this.array.length) {
                growForInsert(i, 1);
            } else {
                if (i >= i5 / 2 || this.firstIndex <= 0) {
                    int i6 = this.lastIndex;
                    int[] iArr = this.array;
                    if (i6 != iArr.length) {
                        int i7 = this.firstIndex + i;
                        System.arraycopy(iArr, i7, iArr, i7 + 1, i5 - i);
                        this.lastIndex++;
                    }
                }
                int[] iArr2 = this.array;
                int i8 = this.firstIndex;
                int i9 = i8 - 1;
                this.firstIndex = i9;
                System.arraycopy(iArr2, i8, iArr2, i9, i);
            }
            this.array[i + this.firstIndex] = i2;
        } else if (i == 0) {
            if (this.firstIndex == 0) {
                growAtFront(1);
            }
            int[] iArr3 = this.array;
            int i10 = this.firstIndex - 1;
            this.firstIndex = i10;
            iArr3[i10] = i2;
        } else if (i == i5) {
            if (this.lastIndex == this.array.length) {
                growAtEnd(1);
            }
            int[] iArr4 = this.array;
            int i11 = this.lastIndex;
            this.lastIndex = i11 + 1;
            iArr4[i11] = i2;
        } else {
            throw new IndexOutOfBoundsException();
        }
        this.modCount++;
    }

    public void clear() {
        int i = this.firstIndex;
        int i2 = this.lastIndex;
        if (i != i2) {
            Arrays.fill(this.array, i, i2, -1);
            this.lastIndex = 0;
            this.firstIndex = 0;
            this.modCount++;
        }
    }

    public int get(int i) {
        if (i >= 0) {
            int i2 = this.lastIndex;
            int i3 = this.firstIndex;
            if (i < i2 - i3) {
                return this.array[i3 + i];
            }
        }
        throw new IndexOutOfBoundsException("" + i);
    }

    private void growAtEnd(int i) {
        int i2 = this.lastIndex;
        int i3 = this.firstIndex;
        int i4 = i2 - i3;
        int[] iArr = this.array;
        if (i3 >= i - (iArr.length - i2)) {
            int i5 = i2 - i3;
            if (i4 > 0) {
                System.arraycopy(iArr, i3, iArr, 0, i4);
            }
            this.firstIndex = 0;
            this.lastIndex = i5;
            return;
        }
        int i6 = i4 / 2;
        if (i <= i6) {
            i = i6;
        }
        if (i < 12) {
            i = 12;
        }
        int[] iArr2 = new int[i + i4];
        if (i4 > 0) {
            System.arraycopy(this.array, this.firstIndex, iArr2, 0, i4);
            this.firstIndex = 0;
            this.lastIndex = i4;
        }
        this.array = iArr2;
    }

    private void growAtFront(int i) {
        int i2 = this.lastIndex;
        int i3 = this.firstIndex;
        int i4 = i2 - i3;
        int[] iArr = this.array;
        if ((iArr.length - i2) + i3 >= i) {
            int length = iArr.length - i4;
            if (i4 > 0) {
                System.arraycopy(iArr, i3, iArr, length, i4);
            }
            this.firstIndex = length;
            this.lastIndex = this.array.length;
            return;
        }
        int i5 = i4 / 2;
        if (i <= i5) {
            i = i5;
        }
        if (i < 12) {
            i = 12;
        }
        int[] iArr2 = new int[i + i4];
        if (i4 > 0) {
            System.arraycopy(this.array, this.firstIndex, iArr2, iArr2.length - i4, i4);
        }
        this.firstIndex = iArr2.length - i4;
        this.lastIndex = iArr2.length;
        this.array = iArr2;
    }

    private void growForInsert(int i, int i2) {
        int i3 = this.lastIndex - this.firstIndex;
        int i4 = i3 / 2;
        if (i2 > i4) {
            i4 = i2;
        }
        if (i4 < 12) {
            i4 = 12;
        }
        int i5 = i3 + i4;
        int[] iArr = new int[i5];
        int i6 = i4 - i2;
        System.arraycopy(this.array, this.firstIndex + i, iArr, i6 + i + i2, i3 - i);
        System.arraycopy(this.array, this.firstIndex, iArr, i6, i);
        this.firstIndex = i6;
        this.lastIndex = i5;
        this.array = iArr;
    }

    public void increment(int i) {
        if (i >= 0) {
            int i2 = this.lastIndex;
            int i3 = this.firstIndex;
            if (i < i2 - i3) {
                int[] iArr = this.array;
                int i4 = i3 + i;
                iArr[i4] = iArr[i4] + 1;
                return;
            }
        }
        throw new IndexOutOfBoundsException("" + i);
    }

    public boolean isEmpty() {
        return this.lastIndex == this.firstIndex;
    }

    public int remove(int i) {
        int i2;
        int i3 = this.lastIndex;
        int i4 = this.firstIndex;
        int i5 = i3 - i4;
        if (i < 0 || i >= i5) {
            throw new IndexOutOfBoundsException();
        }
        if (i == i5 - 1) {
            int[] iArr = this.array;
            int i6 = i3 - 1;
            this.lastIndex = i6;
            i2 = iArr[i6];
            iArr[this.lastIndex] = 0;
        } else if (i == 0) {
            int[] iArr2 = this.array;
            i2 = iArr2[i4];
            this.firstIndex = i4 + 1;
            iArr2[i4] = 0;
        } else {
            int i7 = i4 + i;
            int[] iArr3 = this.array;
            int i8 = iArr3[i7];
            if (i < i5 / 2) {
                System.arraycopy(iArr3, i4, iArr3, i4 + 1, i);
                int[] iArr4 = this.array;
                int i9 = this.firstIndex;
                this.firstIndex = i9 + 1;
                iArr4[i9] = 0;
            } else {
                System.arraycopy(iArr3, i7 + 1, iArr3, i7, (i5 - i) - 1);
                int[] iArr5 = this.array;
                int i10 = this.lastIndex - 1;
                this.lastIndex = i10;
                iArr5[i10] = 0;
            }
            i2 = i8;
        }
        if (this.firstIndex == this.lastIndex) {
            this.lastIndex = 0;
            this.firstIndex = 0;
        }
        this.modCount++;
        return i2;
    }

    public int size() {
        return this.lastIndex - this.firstIndex;
    }

    public int[] toArray() {
        int i = this.lastIndex;
        int i2 = this.firstIndex;
        int i3 = i - i2;
        int[] iArr = new int[i3];
        System.arraycopy(this.array, i2, iArr, 0, i3);
        return iArr;
    }

    public void addAll(IntList intList) {
        growAtEnd(intList.size());
        for (int i = 0; i < intList.size(); i++) {
            add(intList.get(i));
        }
    }
}
