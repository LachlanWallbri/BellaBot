package com.iflytek.aiui.data.video;

import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/* renamed from: com.iflytek.aiui.data.video.f */
/* loaded from: classes4.dex */
class C3558f {

    /* renamed from: a */
    private final HashMap<C3553a, SortedSet<C3557e>> f2194a = new HashMap<>();

    /* renamed from: a */
    public boolean m832a(C3557e c3557e) {
        for (C3553a c3553a : this.f2194a.keySet()) {
            if (c3553a.m820c(c3557e)) {
                SortedSet<C3557e> sortedSet = this.f2194a.get(c3553a);
                if (sortedSet.contains(c3557e)) {
                    return false;
                }
                sortedSet.add(c3557e);
                return true;
            }
        }
        TreeSet treeSet = new TreeSet();
        treeSet.add(c3557e);
        this.f2194a.put(C3553a.m817d(c3557e.m831c(), c3557e.m830b()), treeSet);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m833b() {
        this.f2194a.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public Set<C3553a> m834c() {
        return this.f2194a.keySet();
    }

    /* renamed from: d */
    public void m835d(C3553a c3553a) {
        this.f2194a.remove(c3553a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public SortedSet<C3557e> m836e(C3553a c3553a) {
        return this.f2194a.get(c3553a);
    }
}
