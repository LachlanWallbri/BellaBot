package com.pudutech.bumblebee.presenter.setting;

import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.mvp_base.BaseViewInterface;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: FunctionSettingContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract;", "", "FunctionType", "PresenterInterface", "ViewInterface", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface FunctionSettingContract {

    /* compiled from: FunctionSettingContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u0007H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH&J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0003H&J\u0016\u0010\r\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\tH&¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BasePresenterInterface;", "checkFunctionState", "", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$FunctionType;", "loadFunctionState", "", "loadOrderedFunctions", "", "updateFunctionState", "", "state", "updateOrderedFunctions", "list", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface PresenterInterface extends BasePresenterInterface {
        boolean checkFunctionState(FunctionType value);

        Map<FunctionType, Boolean> loadFunctionState();

        List<FunctionType> loadOrderedFunctions();

        void updateFunctionState(FunctionType value, boolean state);

        void updateOrderedFunctions(List<? extends FunctionType> list);
    }

    /* compiled from: FunctionSettingContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseViewInterface;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface ViewInterface extends BaseViewInterface {
    }

    /* compiled from: FunctionSettingContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$FunctionType;", "", "code", "", "(Ljava/lang/String;II)V", "getCode", "()I", "DELIVER_FUNCTION", "CRUISE_FUNCTION", "DIRECT_DELIVER_FUNCTION", "GREETER_FUNCTION", "SPECIAL_FUNCTION", "RETURN_FUNCTION", "BIRTHDAY_FUNCTION", "MUSIC_FUNCTION", "SETTING", "RECYCLE_FUNCTION", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum FunctionType {
        DELIVER_FUNCTION(1),
        CRUISE_FUNCTION(2),
        DIRECT_DELIVER_FUNCTION(4),
        GREETER_FUNCTION(8),
        SPECIAL_FUNCTION(16),
        RETURN_FUNCTION(32),
        BIRTHDAY_FUNCTION(64),
        MUSIC_FUNCTION(128),
        SETTING(256),
        RECYCLE_FUNCTION(512);

        private final int code;

        FunctionType(int i) {
            this.code = i;
        }

        public final int getCode() {
            return this.code;
        }
    }
}
