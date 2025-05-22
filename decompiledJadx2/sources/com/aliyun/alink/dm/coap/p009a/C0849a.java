package com.aliyun.alink.dm.coap.p009a;

import com.aliyun.alink.dm.p017k.C0859a;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: BaseList.java */
/* renamed from: com.aliyun.alink.dm.coap.a.a */
/* loaded from: classes.dex */
public class C0849a<T> {

    /* renamed from: a */
    protected ArrayList<T> f364a;

    /* renamed from: b */
    private final Object f365b = new Object();

    public C0849a() {
        this.f364a = null;
        this.f364a = new ArrayList<>();
    }

    /* renamed from: a */
    public void m108a(T t) {
        C0859a.m131a("BaseList", "addChain chain=" + t);
        synchronized (this.f365b) {
            if (!this.f364a.contains(t)) {
                this.f364a.add(t);
            }
        }
    }

    /* renamed from: b */
    public void m110b(T t) {
        C0859a.m131a("BaseList", "removeChain chain=" + t);
        synchronized (this.f365b) {
            this.f364a.remove(t);
        }
    }

    /* renamed from: a */
    public int m106a() {
        int size;
        synchronized (this.f365b) {
            size = this.f364a.size();
        }
        return size;
    }

    /* renamed from: a */
    public T m107a(int i) {
        T t;
        if (i <= -1 || i >= m106a()) {
            return null;
        }
        synchronized (this.f365b) {
            t = this.f364a.get(i);
        }
        return t;
    }

    /* renamed from: b */
    public void m109b() {
        synchronized (this.f365b) {
            this.f364a.clear();
        }
    }
}
