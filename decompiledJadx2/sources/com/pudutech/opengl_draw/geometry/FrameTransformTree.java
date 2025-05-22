package com.pudutech.opengl_draw.geometry;

import androidx.core.util.Preconditions;
import com.pudutech.opengl_draw.concurrent.CircularBlockingDeque;
import com.pudutech.opengl_draw.namespace.GraphName;
import java.sql.Time;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class FrameTransformTree {
    private static final int TRANSFORM_QUEUE_CAPACITY = 16;
    private final Object mutex = new Object();
    private final Map<GraphName, CircularBlockingDeque<LazyFrameTransform>> transforms = new ConcurrentHashMap();

    public FrameTransform lookUp(GraphName graphName) {
        Preconditions.checkNotNull(graphName);
        return getLatest(graphName.toRelative());
    }

    private FrameTransform getLatest(GraphName graphName) {
        LazyFrameTransform peekFirst;
        CircularBlockingDeque<LazyFrameTransform> circularBlockingDeque = this.transforms.get(graphName);
        if (circularBlockingDeque == null || (peekFirst = circularBlockingDeque.peekFirst()) == null) {
            return null;
        }
        return peekFirst.get();
    }

    public FrameTransform get(String str) {
        Preconditions.checkNotNull(str);
        return lookUp(GraphName.m3302of(str));
    }

    public FrameTransform lookUp(GraphName graphName, Time time) {
        Preconditions.checkNotNull(graphName);
        Preconditions.checkNotNull(time);
        return get(graphName, time);
    }

    private FrameTransform get(GraphName graphName, Time time) {
        LazyFrameTransform lazyFrameTransform;
        CircularBlockingDeque<LazyFrameTransform> circularBlockingDeque = this.transforms.get(graphName);
        if (circularBlockingDeque == null) {
            return null;
        }
        synchronized (this.mutex) {
            Iterator<LazyFrameTransform> it = circularBlockingDeque.iterator();
            lazyFrameTransform = null;
            while (it.hasNext()) {
                LazyFrameTransform next = it.next();
                if (lazyFrameTransform == null) {
                    lazyFrameTransform = next;
                }
            }
        }
        if (lazyFrameTransform == null) {
            return null;
        }
        return lazyFrameTransform.get();
    }

    public FrameTransform get(String str, Time time) {
        Preconditions.checkNotNull(str);
        return lookUp(GraphName.m3302of(str), time);
    }

    public FrameTransform transform(GraphName graphName, GraphName graphName2) {
        Preconditions.checkNotNull(graphName);
        Preconditions.checkNotNull(graphName2);
        GraphName relative = graphName.toRelative();
        GraphName relative2 = graphName2.toRelative();
        if (relative.equals(relative2)) {
            return new FrameTransform(Transform.identity(), relative, relative2, null);
        }
        FrameTransform transformToRoot = transformToRoot(relative);
        FrameTransform transformToRoot2 = transformToRoot(relative2);
        if (transformToRoot == null && transformToRoot2 == null) {
            return null;
        }
        if (transformToRoot == null) {
            if (transformToRoot2.getTargetFrame().equals(relative)) {
                return transformToRoot2.invert();
            }
            return null;
        }
        if (transformToRoot2 == null) {
            if (transformToRoot.getTargetFrame().equals(relative2)) {
                return transformToRoot;
            }
            return null;
        }
        if (transformToRoot.getTargetFrame().equals(transformToRoot2.getTargetFrame())) {
            return new FrameTransform(transformToRoot2.getTransform().invert().multiply(transformToRoot.getTransform()), relative, relative2, transformToRoot.getTime());
        }
        return null;
    }

    public FrameTransform transform(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        return transform(GraphName.m3302of(str), GraphName.m3302of(str2));
    }

    FrameTransform transformToRoot(GraphName graphName) {
        FrameTransform latest = getLatest(graphName);
        if (latest == null) {
            return null;
        }
        while (true) {
            FrameTransform lookUp = lookUp(latest.getTargetFrame(), latest.getTime());
            if (lookUp == null) {
                return latest;
            }
            latest = new FrameTransform(lookUp.getTransform().multiply(latest.getTransform()), graphName, lookUp.getTargetFrame(), latest.getTime());
        }
    }
}
