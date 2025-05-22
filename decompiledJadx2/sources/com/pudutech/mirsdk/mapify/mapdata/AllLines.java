package com.pudutech.mirsdk.mapify.mapdata;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AllLines.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/mapdata/AllLines;", "", "()V", "end", "Lcom/pudutech/mirsdk/mapify/mapdata/End;", "getEnd", "()Lcom/pudutech/mirsdk/mapify/mapdata/End;", "setEnd", "(Lcom/pudutech/mirsdk/mapify/mapdata/End;)V", "start", "Lcom/pudutech/mirsdk/mapify/mapdata/Start;", "getStart", "()Lcom/pudutech/mirsdk/mapify/mapdata/Start;", "setStart", "(Lcom/pudutech/mirsdk/mapify/mapdata/Start;)V", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class AllLines {
    private Start start = new Start();
    private End end = new End();

    public final Start getStart() {
        return this.start;
    }

    public final void setStart(Start start) {
        Intrinsics.checkParameterIsNotNull(start, "<set-?>");
        this.start = start;
    }

    public final End getEnd() {
        return this.end;
    }

    public final void setEnd(End end) {
        Intrinsics.checkParameterIsNotNull(end, "<set-?>");
        this.end = end;
    }
}
