package androidx.arch.core.executor;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class TaskExecutor {
    public abstract void executeOnDiskIO(Runnable runnable);

    public abstract boolean isMainThread();

    public abstract void postToMainThread(Runnable runnable);

    public void executeOnMainThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            postToMainThread(runnable);
        }
    }
}
