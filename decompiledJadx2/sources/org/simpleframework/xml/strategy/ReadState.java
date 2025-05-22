package org.simpleframework.xml.strategy;

import org.simpleframework.xml.util.WeakCache;

/* loaded from: classes9.dex */
class ReadState extends WeakCache<ReadGraph> {
    private final Contract contract;
    private final Loader loader = new Loader();

    public ReadState(Contract contract) {
        this.contract = contract;
    }

    public ReadGraph find(Object obj) throws Exception {
        ReadGraph fetch = fetch(obj);
        return fetch != null ? fetch : create(obj);
    }

    private ReadGraph create(Object obj) throws Exception {
        ReadGraph fetch = fetch(obj);
        if (fetch != null) {
            return fetch;
        }
        ReadGraph readGraph = new ReadGraph(this.contract, this.loader);
        cache(obj, readGraph);
        return readGraph;
    }
}
