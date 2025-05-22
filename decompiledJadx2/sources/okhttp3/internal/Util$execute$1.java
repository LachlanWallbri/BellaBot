package okhttp3.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Util.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "run"}, m3962k = 3, m3963mv = {1, 1, 15})
/* loaded from: classes2.dex */
public final class Util$execute$1 implements Runnable {
    final /* synthetic */ Function0 $block;
    final /* synthetic */ String $name;

    public Util$execute$1(String str, Function0 function0) {
        this.$name = str;
        this.$block = function0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.$name;
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "currentThread");
        String name = currentThread.getName();
        currentThread.setName(str);
        try {
            this.$block.invoke();
        } finally {
            currentThread.setName(name);
        }
    }
}
