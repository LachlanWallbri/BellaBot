package com.pudutech.bumblebee.presenter;

import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.presenter.activate_task.ActivatePresenter;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingContract;
import com.pudutech.disinfect.baselib.network.response.ShopBean;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.disinfect.baselib.util.LocalStoringUtil;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.Soundex;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Constant.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u00105\u001a\b\u0012\u0004\u0012\u00020706J\u0006\u00108\u001a\u00020\u0017J\u0006\u00109\u001a\u00020\u0017J\u0006\u0010:\u001a\u00020\u0011J\u0006\u0010;\u001a\u00020\u0017J\u0014\u0010<\u001a\u00020=2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020706J\u000e\u0010>\u001a\u00020=2\u0006\u0010\u0010\u001a\u00020\u0017J\u000e\u0010?\u001a\u00020=2\u0006\u0010\u0010\u001a\u00020\u0017J\u000e\u0010@\u001a\u00020=2\u0006\u0010A\u001a\u00020\u0011J\u0010\u0010B\u001a\u00020=2\b\u0010C\u001a\u0004\u0018\u00010DR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00178F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R$\u0010 \u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0016R$\u0010\"\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00118@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b#\u0010\u0014\"\u0004\b$\u0010\u0016R(\u0010%\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u00048@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R$\u0010,\u001a\u00020+2\u0006\u0010\u0010\u001a\u00020+8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u00101\u001a\u00020+2\u0006\u0010\u0010\u001a\u00020+8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b2\u0010.\"\u0004\b3\u00100R\u000e\u00104\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006E"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/Constant;", "", "()V", "KEY_CRUISE_CAN_CALL_SWITCH", "", "KEY_FUNCTION_SETTING_ORDERED", "KEY_FUNCTION_SETTING_STATE", "KEY_FUNCTION_UPDATE_TOOL_VERSION", "KEY_GREETER_POINT_CAN_CALL_SWITCH", "KEY_MERCHANT_DATA", "KEY_MERCHANT_SELECT_TYPE", Constant.KEY_RECYCLE_AUTO_COMPLETE, Constant.KEY_RECYCLE_AUTO_COMPLETE_SWITCH, Constant.KEY_RECYCLE_PAUSE_TIME, "KEY_SHOP_DATA", "TAG", ES6Iterator.VALUE_PROPERTY, "", "cruiseCanCallSwitch", "getCruiseCanCallSwitch", "()Z", "setCruiseCanCallSwitch", "(Z)V", "", "functionUpdateToolVersion", "getFunctionUpdateToolVersion", "()I", "setFunctionUpdateToolVersion", "(I)V", "greeterPointCanCallSwitch", "getGreeterPointCanCallSwitch", "setGreeterPointCanCallSwitch", "isRecycleAutoComplete", "setRecycleAutoComplete", "isSelectMerchantTts", "isSelectMerchantTts$module_bumblebee_presenter_robotRelease", "setSelectMerchantTts$module_bumblebee_presenter_robotRelease", "merchantTtsData", "getMerchantTtsData$module_bumblebee_presenter_robotRelease", "()Ljava/lang/String;", "setMerchantTtsData$module_bumblebee_presenter_robotRelease", "(Ljava/lang/String;)V", "preActiveType", "", "recycleCompleteTime", "getRecycleCompleteTime", "()J", "setRecycleCompleteTime", "(J)V", "recyclePauseTime", "getRecyclePauseTime", "setRecyclePauseTime", "robotActiveKey", "getFunctionSettingOrdered", "", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$FunctionType;", "getFunctionSettingState", "getPreActiveType", "getRobotActive", "getShopId", "setFunctionSettingOrdered", "", "setFunctionSettingState", "setPreActiveType", "setRobotActive", "active", "setShopBean", "shopBean", "Lcom/pudutech/disinfect/baselib/network/response/ShopBean;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Constant {
    public static final Constant INSTANCE = new Constant();
    private static final String KEY_CRUISE_CAN_CALL_SWITCH = "key_cruise_can_call_switch";
    public static final String KEY_FUNCTION_SETTING_ORDERED = "key_function_setting_ordered";
    public static final String KEY_FUNCTION_SETTING_STATE = "key_function_setting_state";
    private static final String KEY_FUNCTION_UPDATE_TOOL_VERSION = "key_function_update_tool_version";
    private static final String KEY_GREETER_POINT_CAN_CALL_SWITCH = "key_greeter_point_can_call_switch";
    private static final String KEY_MERCHANT_DATA = "key_merchant_data";
    private static final String KEY_MERCHANT_SELECT_TYPE = "key_merchant_select_type";
    private static final String KEY_RECYCLE_AUTO_COMPLETE = "KEY_RECYCLE_AUTO_COMPLETE";
    private static final String KEY_RECYCLE_AUTO_COMPLETE_SWITCH = "KEY_RECYCLE_AUTO_COMPLETE_SWITCH";
    private static final String KEY_RECYCLE_PAUSE_TIME = "KEY_RECYCLE_PAUSE_TIME";
    private static final String KEY_SHOP_DATA = "key_shop_data";
    private static final String TAG = "Constant";
    public static final String preActiveType = "pre_active_type";
    private static final String robotActiveKey = "robot_server_ac_key";

    private Constant() {
    }

    public final void setFunctionSettingState(int value) {
        SpUtils.INSTANCE.getPreferences(BusinessContext.INSTANCE.getContext()).edit().putInt(KEY_FUNCTION_SETTING_STATE, value).commit();
    }

    public final int getFunctionSettingState() {
        return SpUtils.INSTANCE.getPreferences(BusinessContext.INSTANCE.getContext()).getInt(KEY_FUNCTION_SETTING_STATE, 255);
    }

    public final void setFunctionSettingOrdered(List<? extends FunctionSettingContract.FunctionType> value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        List<? extends FunctionSettingContract.FunctionType> list = value;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FunctionSettingContract.FunctionType) it.next()).name());
        }
        Iterator it2 = arrayList.iterator();
        if (!it2.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        Object next = it2.next();
        while (it2.hasNext()) {
            next = ((String) next) + Soundex.SILENT_MARKER + ((String) it2.next());
        }
        String str = (String) next;
        Pdlog.m3273d("FunctionSettingOrdered", "setFunctionSettingOrdered: " + str);
        SpUtils.INSTANCE.getPreferences(BusinessContext.INSTANCE.getContext()).edit().putString(KEY_FUNCTION_SETTING_ORDERED, str).commit();
    }

    public final List<FunctionSettingContract.FunctionType> getFunctionSettingOrdered() {
        String functionsStr = SpUtils.INSTANCE.getPreferences(BusinessContext.INSTANCE.getContext()).getString(KEY_FUNCTION_SETTING_ORDERED, "");
        Pdlog.m3273d("FunctionSettingOrdered", "getFunctionSettingOrdered: " + functionsStr);
        Intrinsics.checkExpressionValueIsNotNull(functionsStr, "functionsStr");
        String str = functionsStr;
        if (StringsKt.isBlank(str)) {
            int functionSettingState = getFunctionSettingState();
            ArrayList arrayList = new ArrayList();
            for (FunctionSettingContract.FunctionType functionType : FunctionSettingContract.FunctionType.values()) {
                if ((functionType.getCode() & functionSettingState) == functionType.getCode()) {
                    arrayList.add(functionType);
                }
            }
            arrayList.add(FunctionSettingContract.FunctionType.SETTING);
            Pdlog.m3273d("FunctionSettingOrdered", "getFunctionSettingOrdered old: " + arrayList);
            return arrayList;
        }
        List split$default = StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
        Iterator it = split$default.iterator();
        while (it.hasNext()) {
            arrayList2.add(FunctionSettingContract.FunctionType.valueOf((String) it.next()));
        }
        return arrayList2;
    }

    public final void setRobotActive(boolean active) {
        SpUtils.set(BusinessContext.INSTANCE.getContext(), robotActiveKey, active);
    }

    public final boolean getRobotActive() {
        return SpUtils.get(BusinessContext.INSTANCE.getContext(), robotActiveKey, false);
    }

    public final void setPreActiveType(int value) {
        SpUtils.set(BusinessContext.INSTANCE.getContext(), preActiveType, value);
    }

    public final int getPreActiveType() {
        return SpUtils.get(BusinessContext.INSTANCE.getContext(), preActiveType, ActivatePresenter.ActiveType.OFFICIAL_TYPE.ordinal());
    }

    public final void setShopBean(ShopBean shopBean) {
        String str = (String) null;
        if (shopBean != null) {
            str = GsonSingleton.INSTANCE.getINSTANCE().toJson(shopBean);
            MMKVManager.INSTANCE.getINSTANCE().set(KEY_SHOP_DATA, str);
        }
        Pdlog.m3273d(TAG, "setShopBean() shopBean =" + shopBean + "  json =" + str);
    }

    public final int getShopId() {
        String string = MMKVManager.INSTANCE.getINSTANCE().getString(KEY_SHOP_DATA, "");
        ShopBean shopBean = (ShopBean) GsonSingleton.INSTANCE.getINSTANCE().fromJson(string, ShopBean.class);
        Pdlog.m3273d(TAG, "getShopId() json =" + string + "  shopBean =" + shopBean);
        if (shopBean != null) {
            return shopBean.getShop_id();
        }
        return -1;
    }

    public final void setMerchantTtsData$module_bumblebee_presenter_robotRelease(String str) {
        LocalStoringUtil.INSTANCE.put(KEY_MERCHANT_DATA, str);
    }

    public final String getMerchantTtsData$module_bumblebee_presenter_robotRelease() {
        return LocalStoringUtil.INSTANCE.get(KEY_MERCHANT_DATA, null);
    }

    public final void setSelectMerchantTts$module_bumblebee_presenter_robotRelease(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_MERCHANT_SELECT_TYPE, Boolean.valueOf(z));
    }

    public final boolean isSelectMerchantTts$module_bumblebee_presenter_robotRelease() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_MERCHANT_SELECT_TYPE, false);
    }

    public final void setGreeterPointCanCallSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_GREETER_POINT_CAN_CALL_SWITCH, Boolean.valueOf(z));
    }

    public final boolean getGreeterPointCanCallSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_GREETER_POINT_CAN_CALL_SWITCH, false);
    }

    public final void setCruiseCanCallSwitch(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_CRUISE_CAN_CALL_SWITCH, Boolean.valueOf(z));
    }

    public final boolean getCruiseCanCallSwitch() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_CRUISE_CAN_CALL_SWITCH, false);
    }

    public final void setRecycleAutoComplete(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_RECYCLE_AUTO_COMPLETE_SWITCH, Boolean.valueOf(z));
    }

    public final boolean isRecycleAutoComplete() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_RECYCLE_AUTO_COMPLETE_SWITCH, false);
    }

    public final void setRecycleCompleteTime(long j) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_RECYCLE_AUTO_COMPLETE, Long.valueOf(j));
    }

    public final long getRecycleCompleteTime() {
        return MMKVManager.INSTANCE.getINSTANCE().getLong(KEY_RECYCLE_AUTO_COMPLETE, 120000L);
    }

    public final void setRecyclePauseTime(long j) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_RECYCLE_PAUSE_TIME, Long.valueOf(j));
    }

    public final long getRecyclePauseTime() {
        return MMKVManager.INSTANCE.getINSTANCE().getLong(KEY_RECYCLE_PAUSE_TIME, 10000L);
    }

    public final void setFunctionUpdateToolVersion(int i) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_FUNCTION_UPDATE_TOOL_VERSION, Integer.valueOf(i));
    }

    public final int getFunctionUpdateToolVersion() {
        return MMKVManager.INSTANCE.getINSTANCE().getInt(KEY_FUNCTION_UPDATE_TOOL_VERSION, 0);
    }
}
