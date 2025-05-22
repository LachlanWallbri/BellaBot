package com.pudutech.disinfect.baselib.util;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: MMKVConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001 B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0013X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006!"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/MMKVConfig;", "", "()V", "KEY_CUR_MAP_NAME", "", "KEY_IS_ACTIVE", "SETTING_ADVANCE_AUTO_RECHARGE", "SETTING_ADVANCE_AUTO_RECHARGE_INTERVAL", "SETTING_ADVANCE_AUTO_RECHARGE_INTERVAL_TIME_END", "SETTING_ADVANCE_AUTO_RECHARGE_INTERVAL_TIME_START", "SETTING_ADVANCE_AUTO_RECHARGE_TIME", "SETTING_ADVANCE_CARPET_MODE", "SETTING_ADVANCE_HIGH_SPEED", "SETTING_ADVANCE_RELOADING_MODE", "SETTING_ADVANCE_STEADY_MODE", "SETTING_BOX_TYPE", "SETTING_FIRST_PALLET", "SETTING_KEYBOARD_TYPE", "SETTING_KEYBOARD_TYPE_DEF", "", MMKVConfig.SETTING_PSW, "SETTING_RECHARGE", "SETTING_SECOND_PALLET", "SETTING_SPEED_CRUISE", "SETTING_SPEED_DELIVER", "SETTING_SPEED_GO_HOME", "SETTING_STATION", "SETTING_TABLE_INPUT", "SETTING_TABLE_INPUT_DEF", "SETTING_THIRD_PALLET", "SETTING_VOICE_CRUISE_MODE", "SETTING_VOICE_DELIVER_FINISH", "BoxType", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MMKVConfig {
    public static final MMKVConfig INSTANCE = new MMKVConfig();
    public static final String KEY_CUR_MAP_NAME = "key_cur_map_name";
    public static final String KEY_IS_ACTIVE = "key_is_active";
    public static final String SETTING_ADVANCE_AUTO_RECHARGE = "setting_advance_auto_recharge";
    public static final String SETTING_ADVANCE_AUTO_RECHARGE_INTERVAL = "setting_advance_auto_recharge_interaval";
    public static final String SETTING_ADVANCE_AUTO_RECHARGE_INTERVAL_TIME_END = "setting_advance_auto_recharge_interaval_time_end";
    public static final String SETTING_ADVANCE_AUTO_RECHARGE_INTERVAL_TIME_START = "setting_advance_auto_recharge_interaval_time_start";
    public static final String SETTING_ADVANCE_AUTO_RECHARGE_TIME = "setting_advance_auto_recharge_time";
    public static final String SETTING_ADVANCE_CARPET_MODE = "setting_advance_carpet_mode";
    public static final String SETTING_ADVANCE_HIGH_SPEED = "setting_advance_high_speed";
    public static final String SETTING_ADVANCE_RELOADING_MODE = "setting_advance_reloading_mode";
    public static final String SETTING_ADVANCE_STEADY_MODE = "setting_advance_steady_mode";
    public static final String SETTING_BOX_TYPE = "setting_box_type";
    public static final String SETTING_FIRST_PALLET = "setting_first_pallet";
    public static final String SETTING_KEYBOARD_TYPE = "setting_keyboard_type";
    public static final int SETTING_KEYBOARD_TYPE_DEF = 1;
    public static final String SETTING_PSW = "SETTING_PSW";
    public static final String SETTING_RECHARGE = "setting_recharge";
    public static final String SETTING_SECOND_PALLET = "setting_second_pallet";
    public static final String SETTING_SPEED_CRUISE = "setting_speed_cruise";
    public static final String SETTING_SPEED_DELIVER = "setting_speed_deliver";
    public static final String SETTING_SPEED_GO_HOME = "setting_speed_go_home";
    public static final String SETTING_STATION = "setting_station";
    public static final String SETTING_TABLE_INPUT = "setting_table_input";
    public static final int SETTING_TABLE_INPUT_DEF = 1;
    public static final String SETTING_THIRD_PALLET = "setting_third_pallet";
    public static final String SETTING_VOICE_CRUISE_MODE = "setting_voice_cruise_mode";
    public static final String SETTING_VOICE_DELIVER_FINISH = "setting_voice_deliver_finish";

    private MMKVConfig() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: MMKVConfig.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/util/MMKVConfig$BoxType;", "", "()V", "FOUR_BOX", "", "THREE_SPLIT_BOTTOM_BOX", "THREE_SPLIT_TOP_BOX", "TWO_BOX", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class BoxType {
        public static final String FOUR_BOX = "four_box";
        public static final BoxType INSTANCE = new BoxType();
        public static final String THREE_SPLIT_BOTTOM_BOX = "three_split_bottom_box";
        public static final String THREE_SPLIT_TOP_BOX = "three_split_top_box";
        public static final String TWO_BOX = "two_box";

        private BoxType() {
        }
    }
}
