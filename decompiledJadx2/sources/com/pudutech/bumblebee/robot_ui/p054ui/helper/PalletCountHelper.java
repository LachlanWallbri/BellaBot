package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.general_task.PalletContract;
import com.pudutech.bumblebee.presenter.general_task.PalletPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: PalletCountHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0004J\b\u0010\u0012\u001a\u00020\u0004H\u0002J\u0006\u0010\u0013\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0004J\u0016\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0015J(\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00042\u0016\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020 0\u001fj\b\u0012\u0004\u0012\u00020 `!H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\""}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/PalletCountHelper;", "Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$ViewInterface;", "()V", "DEFUALT_COUNT", "", "OLD_PALLET_BOTTOM", "", "OLD_PALLET_TOP", PalletCountHelper.SP_PALLET_COUNT, "SP_PALLET_ENABLE_INDEX", "TAG", "palletPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/PalletPresenter;", "getPalletPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/PalletPresenter;", "palletPresenter$delegate", "Lkotlin/Lazy;", "getCount", "getDefaultCount", "getFirstTray", "isPalletEnable", "", "i", "setCount", "", "c", "setPalletEnable", "boolean", "showPallets", "visibleNum", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/general_task/PalletContract$PalletModel;", "Lkotlin/collections/ArrayList;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class PalletCountHelper implements PalletContract.ViewInterface {
    private static final int DEFUALT_COUNT;
    public static final PalletCountHelper INSTANCE;
    private static final String OLD_PALLET_BOTTOM;
    private static final String OLD_PALLET_TOP;
    private static final String SP_PALLET_COUNT;
    private static final String SP_PALLET_ENABLE_INDEX;
    private static final String TAG;

    /* renamed from: palletPresenter$delegate, reason: from kotlin metadata */
    private static final Lazy palletPresenter;

    private final PalletPresenter getPalletPresenter() {
        return (PalletPresenter) palletPresenter.getValue();
    }

    static {
        PalletCountHelper palletCountHelper = new PalletCountHelper();
        INSTANCE = palletCountHelper;
        TAG = TAG;
        SP_PALLET_COUNT = SP_PALLET_COUNT;
        SP_PALLET_ENABLE_INDEX = SP_PALLET_ENABLE_INDEX;
        OLD_PALLET_TOP = OLD_PALLET_TOP;
        OLD_PALLET_BOTTOM = OLD_PALLET_BOTTOM;
        DEFUALT_COUNT = 4;
        palletPresenter = LazyKt.lazy(new Function0<PalletPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.PalletCountHelper$palletPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PalletPresenter invoke() {
                PalletPresenter palletPresenter2;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(PalletPresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(PalletPresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    palletPresenter2 = new PalletPresenter();
                } else {
                    presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(PalletPresenter.class).toString());
                    if (!(basePresenterInterface instanceof PalletPresenter)) {
                        basePresenterInterface = null;
                    }
                    palletPresenter2 = (PalletPresenter) basePresenterInterface;
                }
                if (palletPresenter2 != null) {
                    return palletPresenter2;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.general_task.PalletPresenter");
            }
        });
        palletCountHelper.getPalletPresenter().replaceView(palletCountHelper);
        int defaultCount = palletCountHelper.getDefaultCount();
        Pdlog.m3273d(TAG, "PalletCountHelper defualt = " + defaultCount);
        palletCountHelper.setCount(defaultCount);
    }

    private PalletCountHelper() {
    }

    private final int getDefaultCount() {
        Pdlog.m3273d(TAG, "getDefaultCount");
        int i = SpUtils.get(RobotContext.INSTANCE.getContext(), SP_PALLET_COUNT, 0);
        if (i == 0) {
            int i2 = SpUtils.get(RobotContext.INSTANCE.getContext(), OLD_PALLET_BOTTOM, 0);
            int i3 = SpUtils.get(RobotContext.INSTANCE.getContext(), OLD_PALLET_TOP, 0);
            int i4 = i2 + i3;
            Pdlog.m3273d(TAG, "oldBottom = " + i2 + " , oldTop = " + i3);
            if (i4 == 0) {
                return DEFUALT_COUNT;
            }
            Pdlog.m3273d(TAG, "getDefaultCount , need use old value");
            return i4;
        }
        Pdlog.m3273d(TAG, "getDefaultCount , use new save value = " + i);
        return i;
    }

    public final void setCount(int c) {
        Pdlog.m3273d(TAG, "setCount " + c);
        getPalletPresenter().setPalletNum(c);
        SpUtils.set(RobotContext.INSTANCE.getContext(), SP_PALLET_COUNT, c);
    }

    public final int getCount() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), SP_PALLET_COUNT, getDefaultCount());
    }

    public final boolean isPalletEnable(int i) {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), SP_PALLET_ENABLE_INDEX + i, true);
    }

    public final void setPalletEnable(int i, boolean r5) {
        SpUtils.set(RobotContext.INSTANCE.getContext(), SP_PALLET_ENABLE_INDEX + i, r5);
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.PalletContract.ViewInterface
    public void showPallets(int visibleNum, ArrayList<PalletContract.PalletModel> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Pdlog.m3273d(TAG, "showPallets " + visibleNum);
    }

    public final int getFirstTray() {
        int count = getCount() - 1;
        if (count >= 0) {
            int i = 0;
            while (!isPalletEnable(i)) {
                if (i != count) {
                    i++;
                }
            }
            return i;
        }
        return 0;
    }
}
