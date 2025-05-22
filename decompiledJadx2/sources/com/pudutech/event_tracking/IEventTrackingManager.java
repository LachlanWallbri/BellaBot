package com.pudutech.event_tracking;

import android.content.Context;
import android.view.View;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.event_tracking.click.ClickArgs;
import com.pudutech.event_tracking.component.LocationSource;
import com.pudutech.event_tracking.custom.CustomArgs;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: PuduEventTrackingManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0006H&J-\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u001b\u0010\u000f\u001a\u0017\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00030\u0010j\u0002`\u0012¢\u0006\u0002\b\u0013H&J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0006H&J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0006H&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0006H&J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 H&J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0006H&J\u0010\u0010#\u001a\u00020\u00032\u0006\u0010$\u001a\u00020%H&J\b\u0010&\u001a\u00020\u0003H&¨\u0006'"}, m3961d2 = {"Lcom/pudutech/event_tracking/IEventTrackingManager;", "", "addHeaderCustom", "", "pair", "Lkotlin/Pair;", "", "customEvent", "event", "Lcom/pudutech/event_tracking/custom/CustomArgs;", "hardwareVersion", "version", "init", "context", "Landroid/content/Context;", "block", "Lkotlin/Function1;", "Lcom/pudutech/event_tracking/_EventTrackingManagerBuilder;", "Lcom/pudutech/event_tracking/EventTrackingManagerBuilder;", "Lkotlin/ExtensionFunctionType;", "onClick", "v", "Landroid/view/View;", "params", "Lcom/pudutech/event_tracking/click/ClickArgs;", "onPageStart", "name", "onPageStop", "refreshUserId", "id", "registerLocationSource", "mLocationSource", "Lcom/pudutech/event_tracking/component/LocationSource;", "removeHeaderCustom", TransferTable.COLUMN_KEY, "switchReportData", "open", "", "unRegisterLocationSource", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface IEventTrackingManager {
    void addHeaderCustom(Pair<String, ? extends Object> pair);

    void customEvent(CustomArgs event);

    void hardwareVersion(String version);

    void init(Context context, Function1<? super _EventTrackingManagerBuilder, Unit> block);

    void onClick(View v, ClickArgs params);

    void onPageStart(String name);

    void onPageStop(String name);

    void refreshUserId(String id);

    void registerLocationSource(LocationSource mLocationSource);

    void removeHeaderCustom(String key);

    void switchReportData(boolean open);

    void unRegisterLocationSource();
}
