package io.opencensus.contrib.http.util;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import io.opencensus.stats.Stats;
import io.opencensus.stats.View;
import io.opencensus.stats.ViewManager;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class HttpViews {
    static final ImmutableSet<View> HTTP_SERVER_VIEWS_SET = ImmutableSet.m680of(HttpViewConstants.HTTP_SERVER_COMPLETED_COUNT_VIEW, HttpViewConstants.HTTP_SERVER_SENT_BYTES_VIEW, HttpViewConstants.HTTP_SERVER_RECEIVED_BYTES_VIEW, HttpViewConstants.HTTP_SERVER_LATENCY_VIEW);
    static final ImmutableSet<View> HTTP_CLIENT_VIEWS_SET = ImmutableSet.m680of(HttpViewConstants.HTTP_CLIENT_COMPLETED_COUNT_VIEW, HttpViewConstants.HTTP_CLIENT_RECEIVED_BYTES_VIEW, HttpViewConstants.HTTP_CLIENT_SENT_BYTES_VIEW, HttpViewConstants.HTTP_CLIENT_ROUNDTRIP_LATENCY_VIEW);

    private HttpViews() {
    }

    public static final void registerAllClientViews() {
        registerAllClientViews(Stats.getViewManager());
    }

    static void registerAllClientViews(ViewManager viewManager) {
        UnmodifiableIterator<View> it = HTTP_CLIENT_VIEWS_SET.iterator();
        while (it.hasNext()) {
            viewManager.registerView(it.next());
        }
    }

    public static final void registerAllServerViews() {
        registerAllServerViews(Stats.getViewManager());
    }

    static void registerAllServerViews(ViewManager viewManager) {
        UnmodifiableIterator<View> it = HTTP_SERVER_VIEWS_SET.iterator();
        while (it.hasNext()) {
            viewManager.registerView(it.next());
        }
    }

    public static final void registerAllViews() {
        registerAllViews(Stats.getViewManager());
    }

    static void registerAllViews(ViewManager viewManager) {
        registerAllClientViews(viewManager);
        registerAllServerViews(viewManager);
    }
}
