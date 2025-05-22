package com.pudutech.event_tracking;

import com.pudutech.event_tracking.component.LocationSource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PuduEventTrackingManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u00106\u001a\u000207J\u001f\u00100\u001a\u0002072\u0017\u00108\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020709¢\u0006\u0002\b:R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR&\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR\u001a\u0010-\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001b\"\u0004\b/\u0010\u001dR\u001a\u00100\u001a\u000201X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105¨\u0006;"}, m3961d2 = {"Lcom/pudutech/event_tracking/_EventTrackingManagerBuilder;", "", "()V", "autoReportStorage", "", "getAutoReportStorage", "()Z", "setAutoReportStorage", "(Z)V", "filterActivity", "", "", "getFilterActivity", "()Ljava/util/List;", "setFilterActivity", "(Ljava/util/List;)V", "filterFragment", "getFilterFragment", "setFilterFragment", "forceTrackDeviceInfo", "Lcom/pudutech/event_tracking/TrackDeviceInfo;", "getForceTrackDeviceInfo", "()Lcom/pudutech/event_tracking/TrackDeviceInfo;", "setForceTrackDeviceInfo", "(Lcom/pudutech/event_tracking/TrackDeviceInfo;)V", "hardwareVersion", "getHardwareVersion", "()Ljava/lang/String;", "setHardwareVersion", "(Ljava/lang/String;)V", "headCustom", "", "getHeadCustom", "()Ljava/util/Map;", "setHeadCustom", "(Ljava/util/Map;)V", "locationSource", "Lcom/pudutech/event_tracking/component/LocationSource;", "getLocationSource", "()Lcom/pudutech/event_tracking/component/LocationSource;", "setLocationSource", "(Lcom/pudutech/event_tracking/component/LocationSource;)V", "reportData", "getReportData", "setReportData", "userId", "getUserId", "setUserId", "workRuler", "Lcom/pudutech/event_tracking/WorkRuler;", "getWorkRuler$event_tracking_release", "()Lcom/pudutech/event_tracking/WorkRuler;", "setWorkRuler$event_tracking_release", "(Lcom/pudutech/event_tracking/WorkRuler;)V", "check", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class _EventTrackingManagerBuilder {
    private TrackDeviceInfo forceTrackDeviceInfo;
    private LocationSource locationSource;
    private Map<String, Object> headCustom = new LinkedHashMap();
    private boolean autoReportStorage = true;
    private List<String> filterActivity = CollectionsKt.emptyList();
    private List<String> filterFragment = CollectionsKt.listOf("androidx.lifecycle.ReportFragment");
    private String userId = "";
    private String hardwareVersion = "";
    private WorkRuler workRuler = new WorkRuler();
    private boolean reportData = true;

    public final void check() {
    }

    public final Map<String, Object> getHeadCustom() {
        return this.headCustom;
    }

    public final void setHeadCustom(Map<String, Object> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.headCustom = map;
    }

    public final boolean getAutoReportStorage() {
        return this.autoReportStorage;
    }

    public final void setAutoReportStorage(boolean z) {
        this.autoReportStorage = z;
    }

    public final List<String> getFilterActivity() {
        return this.filterActivity;
    }

    public final void setFilterActivity(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.filterActivity = list;
    }

    public final List<String> getFilterFragment() {
        return this.filterFragment;
    }

    public final void setFilterFragment(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.filterFragment = list;
    }

    public final LocationSource getLocationSource() {
        return this.locationSource;
    }

    public final void setLocationSource(LocationSource locationSource) {
        this.locationSource = locationSource;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.userId = str;
    }

    public final TrackDeviceInfo getForceTrackDeviceInfo() {
        return this.forceTrackDeviceInfo;
    }

    public final void setForceTrackDeviceInfo(TrackDeviceInfo trackDeviceInfo) {
        this.forceTrackDeviceInfo = trackDeviceInfo;
    }

    public final String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public final void setHardwareVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.hardwareVersion = str;
    }

    /* renamed from: getWorkRuler$event_tracking_release, reason: from getter */
    public final WorkRuler getWorkRuler() {
        return this.workRuler;
    }

    public final void setWorkRuler$event_tracking_release(WorkRuler workRuler) {
        Intrinsics.checkParameterIsNotNull(workRuler, "<set-?>");
        this.workRuler = workRuler;
    }

    public final boolean getReportData() {
        return this.reportData;
    }

    public final void setReportData(boolean z) {
        this.reportData = z;
    }

    public final void workRuler(Function1<? super WorkRuler, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        WorkRuler workRuler = new WorkRuler();
        block.invoke(workRuler);
        this.workRuler = workRuler;
    }
}
