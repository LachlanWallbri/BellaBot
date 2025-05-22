package com.annimon.stream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class IntPair<T> {
    private final int first;
    private final T second;

    public IntPair(int i, T t) {
        this.first = i;
        this.second = t;
    }

    public int getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public int hashCode() {
        int i = (679 + this.first) * 97;
        T t = this.second;
        return i + (t != null ? t.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IntPair intPair = (IntPair) obj;
        if (this.first != intPair.first) {
            return false;
        }
        T t = this.second;
        T t2 = intPair.second;
        if (t != t2) {
            return t != null && t.equals(t2);
        }
        return true;
    }

    public String toString() {
        return "IntPair[" + this.first + ", " + this.second + ']';
    }
}
