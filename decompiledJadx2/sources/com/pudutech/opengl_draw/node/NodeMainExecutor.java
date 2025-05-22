package com.pudutech.opengl_draw.node;

import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes5.dex */
public interface NodeMainExecutor {
    void execute(NodeMain nodeMain);

    void execute(NodeMain nodeMain, Collection<NodeListener> collection);

    ScheduledExecutorService getScheduledExecutorService();

    void shutdown();

    void shutdownNodeMain(NodeMain nodeMain);
}
