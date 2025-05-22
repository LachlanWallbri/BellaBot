package com.pudutech.robotselfclean.filter;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: DecorationFilter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/robotselfclean/filter/DecorationFilter;", "Lcom/pudutech/robotselfclean/filter/IFilter;", "()V", "DECORATION_PATH", "", "onCreateDeleteList", "", "onCreateRetainList", "RobotSelfClean_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DecorationFilter implements IFilter {
    private final String DECORATION_PATH = "/sdcard/pudu/decoration";

    @Override // com.pudutech.robotselfclean.filter.IFilter
    public List<String> onCreateDeleteList() {
        return CollectionsKt.listOf(this.DECORATION_PATH);
    }

    @Override // com.pudutech.robotselfclean.filter.IFilter
    public List<String> onCreateRetainList() {
        return CollectionsKt.emptyList();
    }
}
