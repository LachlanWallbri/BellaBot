package com.pudutech.event_tracking.click;

import android.util.Log;
import android.view.View;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: track.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/event_tracking/click/PdClickWrap;", "Landroid/view/View$OnClickListener;", "debounce", "", "params", "Lcom/pudutech/event_tracking/click/ClickArgs;", "block", "(ILcom/pudutech/event_tracking/click/ClickArgs;Landroid/view/View$OnClickListener;)V", "canClickTime", "", "getCanClickTime", "()J", "setCanClickTime", "(J)V", "onClick", "", "v", "Landroid/view/View;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PdClickWrap implements View.OnClickListener {
    private final View.OnClickListener block;
    private long canClickTime;
    private final int debounce;
    private final ClickArgs params;

    public PdClickWrap(int i, ClickArgs params, View.OnClickListener block) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.debounce = i;
        this.params = params;
        this.block = block;
    }

    public final long getCanClickTime() {
        return this.canClickTime;
    }

    public final void setCanClickTime(long j) {
        this.canClickTime = j;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Log.i("PdWrapClick", "HOOK onClick");
        long currentTimeMillis = System.currentTimeMillis();
        if (this.canClickTime <= currentTimeMillis) {
            this.canClickTime = currentTimeMillis + this.debounce;
            PuduEventTrackingManager.INSTANCE.onClick(v, this.params);
            this.block.onClick(v);
        }
    }
}
