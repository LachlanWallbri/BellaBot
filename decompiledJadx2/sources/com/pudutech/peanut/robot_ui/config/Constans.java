package com.pudutech.peanut.robot_ui.config;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.disinfect.baselib.network.response.ShopBean;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.peanut.presenter.BusinessSetting;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.bean.TaskModel;
import com.pudutech.peanut.robot_ui.p063ui.helper.PalletCountHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Constans.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\bA\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b'\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010W\u001a\u00020XJ\u0006\u0010Y\u001a\u00020\u0004J\u0006\u0010Z\u001a\u00020\u0007J\u0006\u0010[\u001a\u00020\u0007J\u0006\u0010\\\u001a\u00020]J\u0006\u0010^\u001a\u00020]J\u0006\u0010_\u001a\u00020]J\u0006\u0010`\u001a\u00020]J\u0006\u0010a\u001a\u00020]J\u0006\u0010b\u001a\u00020]J\u0006\u0010c\u001a\u00020]J0\u0010d\u001a,\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040e\u0018\u00010ej\u001a\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00040ej\b\u0012\u0004\u0012\u00020\u0004`f\u0018\u0001`fJ\u001a\u0010g\u001a\u0016\u0012\u0004\u0012\u00020h\u0018\u00010ej\n\u0012\u0004\u0012\u00020h\u0018\u0001`fJ\u0006\u0010i\u001a\u00020XJ\u0006\u0010j\u001a\u00020XJ\u0006\u0010k\u001a\u00020XJ\u0006\u0010l\u001a\u00020XJ\u0006\u0010m\u001a\u00020IJ\u0006\u0010n\u001a\u00020]J\u0006\u0010o\u001a\u00020]J\u0006\u0010p\u001a\u00020]J\u0006\u0010q\u001a\u00020\u0004J\u0006\u0010r\u001a\u00020sJ\u0006\u0010t\u001a\u00020]J\u0006\u0010u\u001a\u00020]J\u0006\u0010v\u001a\u00020\u0007J\u0006\u0010w\u001a\u00020\u0007J\u0006\u0010x\u001a\u00020\u0004J\u001e\u0010y\u001a\u00020z2\u0016\u0010{\u001a\u0012\u0012\u0004\u0012\u00020|0ej\b\u0012\u0004\u0012\u00020|`fJ\u001e\u0010}\u001a\u00020z2\u0016\u0010{\u001a\u0012\u0012\u0004\u0012\u00020h0ej\b\u0012\u0004\u0012\u00020h`fJ\u0017\u0010~\u001a\u00020z2\u0006\u0010\u007f\u001a\u00020\u00042\u0007\u0010\u0080\u0001\u001a\u00020\u0004J\u0010\u0010\u0081\u0001\u001a\u00020z2\u0007\u0010\u0082\u0001\u001a\u00020XJ\u0010\u0010\u0083\u0001\u001a\u00020z2\u0007\u0010\u0084\u0001\u001a\u00020\u0004J\u0010\u0010\u0085\u0001\u001a\u00020z2\u0007\u0010\u0084\u0001\u001a\u00020\u0007J\u0010\u0010\u0086\u0001\u001a\u00020z2\u0007\u0010\u0087\u0001\u001a\u00020\u0007J\u0010\u0010\u0088\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u008a\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u008b\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u008c\u0001\u001a\u00020z2\u0007\u0010\u008d\u0001\u001a\u00020]J\u0010\u0010\u008e\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u008f\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u0090\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u0091\u0001\u001a\u00020z2\u0007\u0010\u0082\u0001\u001a\u00020XJ\u0010\u0010\u0092\u0001\u001a\u00020z2\u0007\u0010\u0082\u0001\u001a\u00020XJ\u0010\u0010\u0093\u0001\u001a\u00020z2\u0007\u0010\u0082\u0001\u001a\u00020XJ\u0010\u0010\u0094\u0001\u001a\u00020z2\u0007\u0010\u0082\u0001\u001a\u00020XJ\u0010\u0010\u0095\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u0096\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u0097\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u0098\u0001\u001a\u00020z2\u0007\u0010\u0099\u0001\u001a\u00020\u0004J\u0010\u0010\u009a\u0001\u001a\u00020z2\u0007\u0010\u009b\u0001\u001a\u00020sJ\u0010\u0010\u009c\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u009d\u0001\u001a\u00020z2\u0007\u0010\u0089\u0001\u001a\u00020]J\u0010\u0010\u009e\u0001\u001a\u00020z2\u0007\u0010\u009f\u0001\u001a\u00020\u0007J\u0010\u0010 \u0001\u001a\u00020z2\u0007\u0010\u009f\u0001\u001a\u00020\u0007J\u0010\u0010¡\u0001\u001a\u00020z2\u0007\u0010¢\u0001\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020IX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020IX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020IX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020IX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006£\u0001"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/config/Constans;", "", "()V", "ACTIVE_FIRST", "", "ALL_PEANUT_SPEED", "APP_UPDATE_POWER", "", "CRUISE_VOICE_DEFAULT_INTERVAL", "DEBUG_PWD", "EXTRA_VOLUME_STREAM_TYPE", "GREETER_GUIDE_VOICE_DEFAULT_INTERVAL", "GREETER_VOICE_DEFAULT_INTERVAL", "KEY_CHARGING_POSITION", "KEY_CHARGING_POWER", "KEY_CRUISE_ID", "KEY_CRUISE_SWITCH", "KEY_CRUISE_TTS_CONFIG", "KEY_CRUISE_TTS_TYPE", "KEY_CRUISE_VOICE_INTERVAL", "KEY_CRUISE_VOICE_SWITCH", "KEY_DELIVERY_HISTORY_TASK", "KEY_DELIVERY_RETURN_HISTORY_TASK", "KEY_DELIVER_FACE_SWITCH", "KEY_DELIVER_FINISH_VOICE_ADVANCE_SWITCH", "KEY_DELIVER_RECYCLE_PLATE_SWITCH", "KEY_DELIVER_TTS_CONFIG", "KEY_DELIVER_TTS_TYPE", "KEY_DELIVER_VOICE_SWITCH", "KEY_GREETER_FACE_SWITCH", "KEY_GREETER_GUIDE_VOICE_INTERVAL", "KEY_GREETER_SWITCH", "KEY_GREETER_VOICE_INTERVAL", "KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_0", "KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_1", "KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_2", "KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_3", "KEY_ITERACTION_SWITCH", "KEY_LATTICE_CRUISE", "KEY_LATTICE_GUIDE_TABLE", "KEY_LATTICE_TURN_BACK", "KEY_LATTICE_WELCOME_AREA", "KEY_PEANUT_SPEED_CURISE", "KEY_PEANUT_SPEED_DIRECT", "KEY_PEANUT_SPEED_MD", "KEY_PEANUT_SPEED_RETURN", "KEY_RECYCLING_PLATE_SWITCH", Constans.KEY_ROW_TABLE_GROUP, "KEY_SETTING_ABOUT_INFO_SWITCH", "KEY_SETTING_STEADY_MODE_SWITCH", "KEY_SHOP_ID", "KEY_SHOP_NAME", "KEY_SHOP_URL", "KEY_SINGLE_TRAY_MULTI_TABLE_SWITCH", "KEY_SOFTWARE_LAST_VC", "KEY_SOFTWARE_LAST_VN", "KEY_SOLICIT_TTS_CONFIG", "KEY_SOLICIT_TTS_TYPE", "KEY_SOLICIT_VOICE_INTERVAL", "KEY_SOLICIT_VOICE_SWITCH", "KEY_SPACES_MODE_SELECT_ARRIVE_VOICE", "KEY_SPACES_MODE_SELECT_MUSIC", "KEY_SYSTEM_UPDATE_COUNT", "KEY_TABLE_INPUT_SMART_DEFAULT_TYPE", "KEY_TABLE_INPUT_TYPE", "KEY_TTS_VOICE_TYPE", "KEY_USHER_TTS_CONFIG", "KEY_USHER_TTS_TYPE", "KEY_USHER_VOICE_INTERVAL", "MAX_WORD_COUNT", "PWD_PUBLIC", "SETTING_PWD", "SOLICIT_BACK_DEFAULT_INTERVAL", "", "SOLICIT_VOICE_DEFAULT_INTERVAL", "SOLICIT_WAKE_DEFAULT_INTERVAL", "SPEED_KEY", "TAG", "TIME_INTERVAL_MS", "TIME_ITEM_INTERVAL_MS", "TYPE_FEATURE_AIVOICE", "TYPE_FEATURE_DIALOG", "TYPE_FEATURE_INPUT", "TYPE_FEATURE_NOMAL", "TYPE_FEATURE_STANDBY", "USHER_VOICE_DEFAULT_INTERVAL", "VOLUME_CHANGED_ACTION", "getAllSpeed", "", "getChargingPosition", "getChargingPower", "getCruiseId", "getCruiseSwitch", "", "getCruiseVoiceSwitch", "getDeliverFaceSwitch", "getDeliverFinishVoiceAdvanceSwitch", "getDeliverVoiceSwitch", "getGreeterFaceSwitch", "getGreeterSwitch", "getLastDeliveryTask", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getLastReturnTask", "Lcom/pudutech/peanut/robot_ui/bean/TaskModel;", "getPeanutCuriseSpeed", "getPeanutDirectSpeed", "getPeanutMdSpeed", "getPeanutReturnSpeed", "getPlayLooperArriveVoiceTime", "getRecyclingPlateSwitch", "getSettingAboutInfoSwitch", "getSettingSteadyModeSwitch", "getShopGroup", "getShopInfo", "Lcom/pudutech/disinfect/baselib/network/response/ShopBean;", "getSingleTrayMultiTableSwitch", "getSolicitVoiceSwitch", "getTableInputSmartDefaultType", "getTableInputType", "getTtsVoiceType", "saveLastDeliveryTask", "", "list", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "saveLastReturnTask", "saveLastSoftwareVersion", "vn", "vc", "setAllSpeed", "speed", "setChargingPosition", "s", "setChargingPower", "setCruiseId", "cruiseId", "setCruiseSwitch", "boolean", "setCruiseVoiceSwitch", "setDeliverFaceSwitch", "setDeliverFinishVoiceAdvanceSwitch", "b", "setDeliverVoiceSwitch", "setGreeterFaceSwitch", "setGreeterSwitch", "setPeanutCuriseSpeed", "setPeanutDirectSpeed", "setPeanutMdSpeed", "setPeanutReturnSpeed", "setRecyclingPlateSwitch", "setSettingAboutInfoSwitch", "setSettingSteadyModeSwitch", "setShopGroup", "data", "setShopInfo", "bean", "setSingleTrayMultiTableSwitch", "setSolicitVoiceSwitch", "setTableInputSmartDefaultType", "i", "setTableInputType", "setTtsVoiceType", "type", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Constans {
    public static final String ACTIVE_FIRST = "active_first";
    public static final String ALL_PEANUT_SPEED = "key_peanut_speed_all";
    public static final int APP_UPDATE_POWER = 20;
    public static final String CRUISE_VOICE_DEFAULT_INTERVAL = "20";
    public static final String DEBUG_PWD = "pudu666";
    public static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";
    public static final String GREETER_GUIDE_VOICE_DEFAULT_INTERVAL = "20";
    public static final String GREETER_VOICE_DEFAULT_INTERVAL = "20";
    public static final String KEY_CHARGING_POSITION = "key_charging_position";
    public static final String KEY_CHARGING_POWER = "key_charging_power";
    public static final String KEY_CRUISE_ID = "key_cruise_id";
    public static final String KEY_CRUISE_SWITCH = "key_cruise_switch";
    public static final String KEY_CRUISE_TTS_CONFIG = "key_cruise_tts_config";
    public static final String KEY_CRUISE_TTS_TYPE = "key_cruise_tts_type";
    public static final String KEY_CRUISE_VOICE_INTERVAL = "key_cruise_voice_interval";
    public static final String KEY_CRUISE_VOICE_SWITCH = "key_cruise_voice_switch";
    private static final String KEY_DELIVERY_HISTORY_TASK = "key_delivery_history_task";
    private static final String KEY_DELIVERY_RETURN_HISTORY_TASK = "key_delivery_return_history_task";
    public static final String KEY_DELIVER_FACE_SWITCH = "key_deliver_face_switch";
    private static final String KEY_DELIVER_FINISH_VOICE_ADVANCE_SWITCH = "key_deliver_finish_voice_advance_switch";
    public static final String KEY_DELIVER_RECYCLE_PLATE_SWITCH = "key_deliver_recycle_plate_switch";
    public static final String KEY_DELIVER_TTS_CONFIG = "key_deliver_tts_config";
    public static final String KEY_DELIVER_TTS_TYPE = "key_deliver_tts_type";
    public static final String KEY_DELIVER_VOICE_SWITCH = "key_deliver_voice_switch";
    public static final String KEY_GREETER_FACE_SWITCH = "key_greeter_face_switch";
    public static final String KEY_GREETER_GUIDE_VOICE_INTERVAL = "key_greeter_guide_voice_interval";
    public static final String KEY_GREETER_SWITCH = "key_greeter_switch";
    public static final String KEY_GREETER_VOICE_INTERVAL = "key_greeter_voice_interval";
    public static final String KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_0 = "key_interaction_prize_probability_level_0";
    public static final String KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_1 = "key_interaction_prize_probability_level_1";
    public static final String KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_2 = "key_interaction_prize_probability_level_2";
    public static final String KEY_ITERACTION_PRIZE_PROBABILITY_LEVEL_3 = "key_interaction_prize_probability_level_3";
    public static final String KEY_ITERACTION_SWITCH = "key_interaction_switch";
    public static final String KEY_LATTICE_CRUISE = "key_lattice_cruise";
    public static final String KEY_LATTICE_GUIDE_TABLE = "key_lattice_guide_table";
    public static final String KEY_LATTICE_TURN_BACK = "key_lattice_turn_back";
    public static final String KEY_LATTICE_WELCOME_AREA = "key_lattice_welcome_area";
    public static final String KEY_PEANUT_SPEED_CURISE = "key_peanut_speed_curise";
    public static final String KEY_PEANUT_SPEED_DIRECT = "key_peanut_speed_direct";
    public static final String KEY_PEANUT_SPEED_MD = "key_peanut_speed_md";
    public static final String KEY_PEANUT_SPEED_RETURN = "key_peanut_speed_return";
    public static final String KEY_RECYCLING_PLATE_SWITCH = "key_recycling_plate_switch";
    public static final String KEY_ROW_TABLE_GROUP = "KEY_ROW_TABLE_GROUP";
    public static final String KEY_SETTING_ABOUT_INFO_SWITCH = "key_setting_about_info_switch";
    public static final String KEY_SETTING_STEADY_MODE_SWITCH = "key_setting_steady_mode_switch";
    public static final String KEY_SHOP_ID = "key_shop_id";
    public static final String KEY_SHOP_NAME = "key_shop_name";
    public static final String KEY_SHOP_URL = "key_shop_url";
    public static final String KEY_SINGLE_TRAY_MULTI_TABLE_SWITCH = "key_single_tray_multi_table_switch";
    public static final String KEY_SOFTWARE_LAST_VC = "key_software_last_vc";
    public static final String KEY_SOFTWARE_LAST_VN = "key_software_last_vn";
    public static final String KEY_SOLICIT_TTS_CONFIG = "key_cruise_tts_config";
    public static final String KEY_SOLICIT_TTS_TYPE = "key_solicit_tts_type";
    public static final String KEY_SOLICIT_VOICE_INTERVAL = "key_solicit_voice_interval";
    public static final String KEY_SOLICIT_VOICE_SWITCH = "key_solicit_voice_switch";
    public static final String KEY_SPACES_MODE_SELECT_ARRIVE_VOICE = "key_spaces_mode_select_arrive_voice";
    public static final String KEY_SPACES_MODE_SELECT_MUSIC = "key_spaces_mode_select_music";
    public static final String KEY_SYSTEM_UPDATE_COUNT = "key_system_update_count";
    private static final String KEY_TABLE_INPUT_SMART_DEFAULT_TYPE = "key_table_input_smart_default_type";
    private static final String KEY_TABLE_INPUT_TYPE = "key_table_input_type";
    public static final String KEY_TTS_VOICE_TYPE = "key_tts_voice_type";
    public static final String KEY_USHER_TTS_CONFIG = "key_usher_tts_config";
    public static final String KEY_USHER_TTS_TYPE = "key_usher_tts_type";
    public static final String KEY_USHER_VOICE_INTERVAL = "key_usher_voice_interval";
    public static final int MAX_WORD_COUNT = 30;
    public static final String PWD_PUBLIC = "pudupw";
    public static final String SETTING_PWD = "pudu888";
    public static final long SOLICIT_BACK_DEFAULT_INTERVAL = 15000;
    public static final String SOLICIT_VOICE_DEFAULT_INTERVAL = "20";
    public static final long SOLICIT_WAKE_DEFAULT_INTERVAL = 25000;
    public static final String SPEED_KEY = "key_robot_speed_level";
    public static final long TIME_INTERVAL_MS = 600;
    public static final long TIME_ITEM_INTERVAL_MS = 300;
    public static final int TYPE_FEATURE_AIVOICE = 2;
    public static final int TYPE_FEATURE_DIALOG = 4;
    public static final int TYPE_FEATURE_INPUT = 1;
    public static final int TYPE_FEATURE_NOMAL = 0;
    public static final int TYPE_FEATURE_STANDBY = 3;
    public static final String USHER_VOICE_DEFAULT_INTERVAL = "20";
    public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    public static final Constans INSTANCE = new Constans();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private Constans() {
    }

    public final String getChargingPosition() {
        String str;
        Pdlog.m3274e(TAG, "getChargingPiles = " + RobotMapManager.INSTANCE.getChargingPiles());
        List<String> chargingPiles = RobotMapManager.INSTANCE.getChargingPiles();
        if (chargingPiles == null || (str = (String) CollectionsKt.firstOrNull((List) chargingPiles)) == null) {
            str = "";
        }
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_CHARGING_POSITION, str);
    }

    public final void setChargingPosition(String s) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        Pdlog.m3273d(TAG, "setChargingPosition : s = " + s + "; ");
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_CHARGING_POSITION, s);
    }

    public final int getChargingPower() {
        Pdlog.m3274e(TAG, "getChargingPower = " + BatteryInfoManager.INSTANCE.getPower() + " power = " + SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_CHARGING_POWER, 0));
        Integer power = BatteryInfoManager.INSTANCE.getPower();
        return power != null ? power.intValue() : SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_CHARGING_POWER, 0);
    }

    public final void setChargingPower(int s) {
        Pdlog.m3273d(TAG, "setChargingPower : s = " + s + "; ");
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_CHARGING_POWER, s);
    }

    public final String getTtsVoiceType() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_TTS_VOICE_TYPE, "x_chongchong");
    }

    public final void setTtsVoiceType(String type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Pdlog.m3273d(TAG, "setTtsVoiceType : type = " + type + "; ");
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_TTS_VOICE_TYPE, type);
    }

    public final ShopBean getShopInfo() {
        return new ShopBean(SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_SHOP_ID, -1), SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_SHOP_NAME, ""), SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_SHOP_URL, ""));
    }

    public final void setShopInfo(ShopBean bean) {
        Intrinsics.checkParameterIsNotNull(bean, "bean");
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_SHOP_ID, bean.getShop_id());
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_SHOP_NAME, bean.getName());
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_SHOP_URL, bean.getUrl());
    }

    public final float getAllSpeed() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), ALL_PEANUT_SPEED, 0.6f);
    }

    public final void setAllSpeed(float speed) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), ALL_PEANUT_SPEED, speed);
    }

    public final float getPeanutMdSpeed() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_PEANUT_SPEED_MD, getAllSpeed());
    }

    public final void setPeanutMdSpeed(float speed) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_PEANUT_SPEED_MD, speed);
    }

    public final float getPeanutCuriseSpeed() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_PEANUT_SPEED_CURISE, getAllSpeed());
    }

    public final void setPeanutCuriseSpeed(float speed) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_PEANUT_SPEED_CURISE, speed);
    }

    public final float getPeanutDirectSpeed() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_PEANUT_SPEED_DIRECT, getAllSpeed());
    }

    public final void setPeanutDirectSpeed(float speed) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_PEANUT_SPEED_DIRECT, speed);
    }

    public final float getPeanutReturnSpeed() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_PEANUT_SPEED_RETURN, getAllSpeed());
    }

    public final void setPeanutReturnSpeed(float speed) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_PEANUT_SPEED_RETURN, speed);
    }

    public final boolean getRecyclingPlateSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_recycling_plate_switch", false);
    }

    public final void setRecyclingPlateSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_recycling_plate_switch", r3);
    }

    public final boolean getDeliverFaceSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_deliver_face_switch", true);
    }

    public final void setDeliverFaceSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_deliver_face_switch", r3);
    }

    public final boolean getDeliverVoiceSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_DELIVER_VOICE_SWITCH, false);
    }

    public final void setDeliverVoiceSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_DELIVER_VOICE_SWITCH, r3);
    }

    public final boolean getCruiseSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_CRUISE_SWITCH, false);
    }

    public final void setCruiseSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_CRUISE_SWITCH, r3);
    }

    public final boolean getGreeterSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_GREETER_SWITCH, false);
    }

    public final void setGreeterSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_GREETER_SWITCH, r3);
    }

    public final boolean getGreeterFaceSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_GREETER_FACE_SWITCH, true);
    }

    public final void setGreeterFaceSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_GREETER_FACE_SWITCH, r3);
    }

    public final boolean getCruiseVoiceSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_CRUISE_VOICE_SWITCH, false);
    }

    public final void setCruiseVoiceSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_CRUISE_VOICE_SWITCH, r3);
    }

    public final boolean getSolicitVoiceSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_SOLICIT_VOICE_SWITCH, false);
    }

    public final void setSolicitVoiceSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_SOLICIT_VOICE_SWITCH, r3);
    }

    public final int getCruiseId() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_CRUISE_ID, -1);
    }

    public final void setCruiseId(int cruiseId) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_CRUISE_ID, cruiseId);
    }

    public final boolean getSettingAboutInfoSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_setting_about_info_switch", true);
    }

    public final void setSettingAboutInfoSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_setting_about_info_switch", r3);
    }

    public final boolean getSettingSteadyModeSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_setting_steady_mode_switch", false);
    }

    public final void setSettingSteadyModeSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_setting_steady_mode_switch", r3);
    }

    public final boolean getSingleTrayMultiTableSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), "key_single_tray_multi_table_switch", false);
    }

    public final void setSingleTrayMultiTableSwitch(boolean r3) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_single_tray_multi_table_switch", r3);
    }

    public final boolean getDeliverFinishVoiceAdvanceSwitch() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_DELIVER_FINISH_VOICE_ADVANCE_SWITCH, false);
    }

    public final void setDeliverFinishVoiceAdvanceSwitch(boolean b) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_DELIVER_FINISH_VOICE_ADVANCE_SWITCH, b);
    }

    public final long getPlayLooperArriveVoiceTime() {
        if (BusinessSetting.INSTANCE.getArrivalLoopVoiceDelayTime_ms() > 0) {
            return BusinessSetting.INSTANCE.getArrivalLoopVoiceDelayTime_ms();
        }
        return 15000L;
    }

    public final void saveLastSoftwareVersion(String vn, String vc) {
        Intrinsics.checkParameterIsNotNull(vn, "vn");
        Intrinsics.checkParameterIsNotNull(vc, "vc");
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_software_last_vn", vn);
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_software_last_vc", vc);
    }

    public final int getTableInputType() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_TABLE_INPUT_TYPE, 0);
    }

    public final void setTableInputType(int i) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_TABLE_INPUT_TYPE, i);
    }

    public final int getTableInputSmartDefaultType() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_TABLE_INPUT_SMART_DEFAULT_TYPE, 0);
    }

    public final void setTableInputSmartDefaultType(int i) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_TABLE_INPUT_SMART_DEFAULT_TYPE, i);
    }

    public final void saveLastDeliveryTask(ArrayList<TrayModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ArrayList arrayList = new ArrayList();
        int count = PalletCountHelper.INSTANCE.getCount();
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TrayModel trayModel = (TrayModel) obj;
            if (i <= count - 1) {
                ArrayList arrayList2 = new ArrayList();
                arrayList.add(arrayList2);
                Iterator<T> it = trayModel.getAllDestinations().iterator();
                while (it.hasNext()) {
                    arrayList2.add(((DeliveryModel) it.next()).getDestination());
                }
            }
            i = i2;
        }
        String s = new Gson().toJson(arrayList);
        Pdlog.m3273d(TAG, "saveLastDeliveryTask : list = " + list + "; " + s);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_DELIVERY_HISTORY_TASK, s);
    }

    public final void saveLastReturnTask(ArrayList<TaskModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        ArrayList arrayList = new ArrayList();
        int count = PalletCountHelper.INSTANCE.getCount();
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TaskModel taskModel = (TaskModel) obj;
            if (i <= count - 1) {
                arrayList.add(new TaskModel(taskModel.isReturn(), taskModel.getContent(), new TrayModel()));
            }
            i = i2;
        }
        String s = new Gson().toJson(arrayList);
        Pdlog.m3273d(TAG, "saveLastDeliveryTask : list = " + list + "; " + s);
        Context context = RobotContext.INSTANCE.getContext();
        Intrinsics.checkExpressionValueIsNotNull(s, "s");
        SpUtils.set(context, KEY_DELIVERY_RETURN_HISTORY_TASK, s);
    }

    public final ArrayList<TaskModel> getLastReturnTask() {
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_DELIVERY_RETURN_HISTORY_TASK, "");
        if (str.length() == 0) {
            return null;
        }
        try {
            return (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<TaskModel>>() { // from class: com.pudutech.peanut.robot_ui.config.Constans$getLastReturnTask$list$1
            }.getType());
        } catch (Exception unused) {
            return null;
        }
    }

    public final ArrayList<ArrayList<String>> getLastDeliveryTask() {
        String str = SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_DELIVERY_HISTORY_TASK, "");
        if (str.length() == 0) {
            return null;
        }
        try {
            return (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<ArrayList<String>>>() { // from class: com.pudutech.peanut.robot_ui.config.Constans$getLastDeliveryTask$list$1
            }.getType());
        } catch (Exception unused) {
            return null;
        }
    }

    public final String getShopGroup() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), KEY_ROW_TABLE_GROUP, "");
    }

    public final void setShopGroup(String data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        SpUtils.set(RobotContext.INSTANCE.getContext(), KEY_ROW_TABLE_GROUP, data);
    }
}
