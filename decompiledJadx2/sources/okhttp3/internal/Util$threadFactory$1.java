package okhttp3.internal;

import java.util.concurrent.ThreadFactory;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: Util.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "Ljava/lang/Thread;", "runnable", "Ljava/lang/Runnable;", "kotlin.jvm.PlatformType", "newThread"}, m3962k = 3, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
final class Util$threadFactory$1 implements ThreadFactory {
    final /* synthetic */ boolean $daemon;
    final /* synthetic */ String $name;

    Util$threadFactory$1(String str, boolean z) {
        this.$name = str;
        this.$daemon = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.$name);
        thread.setDaemon(this.$daemon);
        return thread;
    }
}
