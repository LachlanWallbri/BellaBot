package com.pudutech.pd_network.report;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: HandlerOldReportData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016R.\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/pd_network/report/HistoryBody;", "", "size", "", "history", "Ljava/util/ArrayList;", "Lcom/pudutech/pd_network/report/HistoryContent;", "Lkotlin/collections/ArrayList;", "(ILjava/util/ArrayList;)V", "getHistory", "()Ljava/util/ArrayList;", "setHistory", "(Ljava/util/ArrayList;)V", "getSize", "()I", "setSize", "(I)V", "toString", "", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class HistoryBody {
    private ArrayList<HistoryContent> history;
    private int size;

    public HistoryBody() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    public HistoryBody(int i, ArrayList<HistoryContent> arrayList) {
        this.size = i;
        this.history = arrayList;
    }

    public final int getSize() {
        return this.size;
    }

    public final void setSize(int i) {
        this.size = i;
    }

    public /* synthetic */ HistoryBody(int i, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? (ArrayList) null : arrayList);
    }

    public final ArrayList<HistoryContent> getHistory() {
        return this.history;
    }

    public final void setHistory(ArrayList<HistoryContent> arrayList) {
        this.history = arrayList;
    }

    public String toString() {
        return "HistoryBody(size=" + this.size + ", history=" + this.history + ')';
    }
}
