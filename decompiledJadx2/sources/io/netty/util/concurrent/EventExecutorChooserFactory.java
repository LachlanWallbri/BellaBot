package io.netty.util.concurrent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface EventExecutorChooserFactory {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public interface EventExecutorChooser {
        EventExecutor next();
    }

    EventExecutorChooser newChooser(EventExecutor[] eventExecutorArr);
}
