package org.simpleframework.xml.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class OutputStack extends ArrayList<OutputNode> {
    private final Set active;

    public OutputStack(Set set) {
        this.active = set;
    }

    public OutputNode pop() {
        int size = size();
        if (size <= 0) {
            return null;
        }
        return purge(size - 1);
    }

    public OutputNode top() {
        int size = size();
        if (size <= 0) {
            return null;
        }
        return get(size - 1);
    }

    public OutputNode bottom() {
        if (size() <= 0) {
            return null;
        }
        return get(0);
    }

    public OutputNode push(OutputNode outputNode) {
        this.active.add(outputNode);
        add(outputNode);
        return outputNode;
    }

    public OutputNode purge(int i) {
        OutputNode remove = remove(i);
        if (remove != null) {
            this.active.remove(remove);
        }
        return remove;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<OutputNode> iterator() {
        return new Sequence();
    }

    /* loaded from: classes9.dex */
    private class Sequence implements Iterator<OutputNode> {
        private int cursor;

        public Sequence() {
            this.cursor = OutputStack.this.size();
        }

        @Override // java.util.Iterator
        public OutputNode next() {
            if (!hasNext()) {
                return null;
            }
            OutputStack outputStack = OutputStack.this;
            int i = this.cursor - 1;
            this.cursor = i;
            return outputStack.get(i);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.cursor > 0;
        }

        @Override // java.util.Iterator
        public void remove() {
            OutputStack.this.purge(this.cursor);
        }
    }
}
