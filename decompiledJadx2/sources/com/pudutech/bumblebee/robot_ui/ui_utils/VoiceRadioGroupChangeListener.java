package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.RadioGroup;
import androidx.core.view.ViewGroupKt;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.track.report.ClickReport;
import com.pudutech.bumblebee.robot_ui.util.PlaySound;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.robot.module.report.track2.TrackKey;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.apache.commons.logging.LogFactory;

/* compiled from: VoiceRadioGroupChangeListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B]\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00126\u0010\b\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\t¢\u0006\u0002\u0010\u0010J\u001a\u0010\b\u001a\u00020\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u0007H\u0016R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000RA\u0010\b\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui_utils/VoiceRadioGroupChangeListener;", "Landroid/widget/RadioGroup$OnCheckedChangeListener;", "param", "", "", "", LogFactory.PRIORITY_KEY, "", "onCheckedChanged", "Lkotlin/Function2;", "Landroid/widget/RadioGroup;", "Lkotlin/ParameterName;", "name", MapElement.Key.GROUP, "checkedId", "", "(Ljava/util/Map;ILkotlin/jvm/functions/Function2;)V", "TAG", "getOnCheckedChanged", "()Lkotlin/jvm/functions/Function2;", "getParam", "()Ljava/util/Map;", "getPriority", "()I", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class VoiceRadioGroupChangeListener implements RadioGroup.OnCheckedChangeListener {
    private final String TAG;
    private final Function2<RadioGroup, Integer, Unit> onCheckedChanged;
    private final Map<String, Object> param;
    private final int priority;

    /* JADX WARN: Multi-variable type inference failed */
    public VoiceRadioGroupChangeListener(Map<String, ? extends Object> param, int i, Function2<? super RadioGroup, ? super Integer, Unit> onCheckedChanged) {
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(onCheckedChanged, "onCheckedChanged");
        this.param = param;
        this.priority = i;
        this.onCheckedChanged = onCheckedChanged;
        this.TAG = "VoiceRadioGroupChangeListener";
    }

    public /* synthetic */ VoiceRadioGroupChangeListener(Map map, int i, Function2 function2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? MapsKt.emptyMap() : map, (i2 & 2) != 0 ? 0 : i, function2);
    }

    public final Map<String, Object> getParam() {
        return this.param;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final Function2<RadioGroup, Integer, Unit> getOnCheckedChanged() {
        return this.onCheckedChanged;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Context context;
        Resources resources;
        Sequence<View> children;
        Sequence filter;
        Iterator it;
        Boolean valueOf = (group == null || (children = ViewGroupKt.getChildren(group)) == null || (filter = SequencesKt.filter(children, new Function1<View, Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.ui_utils.VoiceRadioGroupChangeListener$onCheckedChanged$isPressed$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(View view) {
                return Boolean.valueOf(invoke2(view));
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final boolean invoke2(View it2) {
                Intrinsics.checkParameterIsNotNull(it2, "it");
                return it2.isPressed();
            }
        })) == null || (it = filter.iterator()) == null) ? null : Boolean.valueOf(it.hasNext());
        Pdlog.m3273d(this.TAG, "onCheckedChanged: " + valueOf + ',' + group + ',' + checkedId);
        if (!Intrinsics.areEqual((Object) valueOf, (Object) true)) {
            return;
        }
        this.onCheckedChanged.invoke(group, Integer.valueOf(checkedId));
        PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C4188R.raw.btn_click_1);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(this.param);
        if (group != null) {
            try {
                context = group.getContext();
            } catch (Exception e) {
                Pdlog.m3273d(this.TAG, "onCheckedChanged: " + e.getMessage());
                linkedHashMap.put(TrackKey.RADIO_GROUP_SELECT, "null");
            }
            if (context != null && (resources = context.getResources()) != null && (r11 = resources.getResourceEntryName(checkedId)) != null) {
                linkedHashMap.put(TrackKey.RADIO_GROUP_SELECT, r11);
                if (group == null) {
                    ClickReport.INSTANCE.onClickEvent(group, linkedHashMap, this.priority);
                    return;
                }
                return;
            }
        }
        String resourceEntryName = "null";
        linkedHashMap.put(TrackKey.RADIO_GROUP_SELECT, resourceEntryName);
        if (group == null) {
        }
    }
}
