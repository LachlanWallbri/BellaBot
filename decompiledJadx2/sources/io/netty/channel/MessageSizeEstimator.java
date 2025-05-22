package io.netty.channel;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface MessageSizeEstimator {

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public interface Handle {
        int size(Object obj);
    }

    Handle newHandle();
}
