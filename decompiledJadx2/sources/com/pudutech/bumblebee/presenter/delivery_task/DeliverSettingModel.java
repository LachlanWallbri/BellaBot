package com.pudutech.bumblebee.presenter.delivery_task;

import android.content.Context;
import com.pudutech.base.SpUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliverSettingModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\bJ\u0016\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\fJ\u0016\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliverSettingModel;", "", "()V", "KEY_DELIVER_SETTING_VOICE_SWITCH", "", "KEY_OUTLET_SELECT_SWITCH", "KEY_TABLE_COLUMN", "getOutletSelectSwitchState", "", "context", "Landroid/content/Context;", "getTableColumn", "", "getVoiceSwitchState", "saveOutletSelectSwitchState", "", "state", "saveTableColumn", "column", "saveVoiceSwitchState", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliverSettingModel {
    public static final DeliverSettingModel INSTANCE = new DeliverSettingModel();
    public static final String KEY_DELIVER_SETTING_VOICE_SWITCH = "key_deliver_setting_voice_switch";
    public static final String KEY_OUTLET_SELECT_SWITCH = "key_outlet_select_switch";
    public static final String KEY_TABLE_COLUMN = "key_table_column";

    private DeliverSettingModel() {
    }

    public final boolean getVoiceSwitchState(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return SpUtils.get(context, KEY_DELIVER_SETTING_VOICE_SWITCH, false);
    }

    public final void saveVoiceSwitchState(Context context, boolean state) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        SpUtils.set(context, KEY_DELIVER_SETTING_VOICE_SWITCH, state);
    }

    public final int getTableColumn(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return SpUtils.get(context, KEY_TABLE_COLUMN, 4);
    }

    public final void saveTableColumn(Context context, int column) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        SpUtils.set(context, KEY_TABLE_COLUMN, column);
    }

    public final boolean getOutletSelectSwitchState(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return SpUtils.get(context, KEY_OUTLET_SELECT_SWITCH, false);
    }

    public final void saveOutletSelectSwitchState(Context context, boolean state) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        SpUtils.set(context, KEY_OUTLET_SELECT_SWITCH, state);
    }
}
