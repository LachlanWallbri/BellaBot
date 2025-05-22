package com.pudutech.mirsdk.dance;

import com.pudutech.mirsdk.aidl.serialize.Dance;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;

/* compiled from: IDance.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u001e\u0010\b\u001a\u00020\t2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000bH&J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\rH&¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mirsdk/dance/IDance;", "", "startDance", "", "dances", "", "Lcom/pudutech/mirsdk/aidl/serialize/Dance;", "stopDance", "suspendWarningWelfunction", "", "warning", "Lkotlin/Pair;", "", "", "triggerError", "error", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface IDance {
    int startDance(List<Dance> dances);

    int stopDance();

    void suspendWarningWelfunction(Pair<Boolean, String> warning);

    void triggerError(String error);
}
