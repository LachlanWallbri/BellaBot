package com.pudutech.opengl_draw.concurrent;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes5.dex */
public class CircularBlockingDeque<T> implements Iterable<T> {
    private final T[] deque;
    private final int limit;
    private final Object mutex = new Object();
    private int start = 0;
    private int length = 0;

    public CircularBlockingDeque(int i) {
        this.deque = (T[]) new Object[i];
        this.limit = i;
    }

    public boolean addLast(T t) {
        synchronized (this.mutex) {
            this.deque[(this.start + this.length) % this.limit] = t;
            if (this.length == this.limit) {
                this.start = (this.start + 1) % this.limit;
            } else {
                this.length++;
            }
            this.mutex.notify();
        }
        return true;
    }

    public boolean addFirst(T t) {
        synchronized (this.mutex) {
            if (this.start - 1 < 0) {
                this.start = this.limit - 1;
            } else {
                this.start--;
            }
            this.deque[this.start] = t;
            if (this.length < this.limit) {
                this.length++;
            }
            this.mutex.notify();
        }
        return true;
    }

    public T takeFirst() throws InterruptedException {
        T t;
        synchronized (this.mutex) {
            while (this.length <= 0) {
                this.mutex.wait();
            }
            t = this.deque[this.start];
            this.start = (this.start + 1) % this.limit;
            this.length--;
        }
        return t;
    }

    public T peekFirst() {
        synchronized (this.mutex) {
            if (this.length <= 0) {
                return null;
            }
            return this.deque[this.start];
        }
    }

    public T takeLast() throws InterruptedException {
        T t;
        synchronized (this.mutex) {
            while (this.length <= 0) {
                this.mutex.wait();
            }
            t = this.deque[((this.start + this.length) - 1) % this.limit];
            this.length--;
        }
        return t;
    }

    public T peekLast() {
        synchronized (this.mutex) {
            if (this.length <= 0) {
                return null;
            }
            return this.deque[((this.start + this.length) - 1) % this.limit];
        }
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new Iterator<T>() { // from class: com.pudutech.opengl_draw.concurrent.CircularBlockingDeque.1
            int offset = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.offset < CircularBlockingDeque.this.length;
            }

            @Override // java.util.Iterator
            public T next() {
                if (this.offset != CircularBlockingDeque.this.length) {
                    T t = (T) CircularBlockingDeque.this.deque[(CircularBlockingDeque.this.start + this.offset) % CircularBlockingDeque.this.limit];
                    this.offset++;
                    return t;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
