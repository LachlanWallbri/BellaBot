package org.mozilla.javascript;

import org.mozilla.javascript.ContextFactory;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
@Deprecated
/* loaded from: classes2.dex */
public interface ContextListener extends ContextFactory.Listener {
    @Deprecated
    void contextEntered(Context context);

    @Deprecated
    void contextExited(Context context);
}
