package okhttp3.internal;

import java.util.concurrent.ThreadFactory;

/* compiled from: lambda */
/* renamed from: okhttp3.internal.-$$Lambda$Util$h7t7benxn5wsEKvjxE9svqr_kuY, reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$Util$h7t7benxn5wsEKvjxE9svqr_kuY implements ThreadFactory {
    private final /* synthetic */ String f$0;
    private final /* synthetic */ boolean f$1;

    public /* synthetic */ $$Lambda$Util$h7t7benxn5wsEKvjxE9svqr_kuY(String str, boolean z) {
        this.f$0 = str;
        this.f$1 = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return Util.lambda$threadFactory$0(this.f$0, this.f$1, runnable);
    }
}
