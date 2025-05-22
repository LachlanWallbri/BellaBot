package com.pudutech.freeinstall_ui.utils;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConfigDataHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR*\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/ConfigDataHelper;", "", "()V", "cameraSupportType", "Lcom/pudutech/freeinstall_ui/utils/CameraSupportType;", "getCameraSupportType", "()Lcom/pudutech/freeinstall_ui/utils/CameraSupportType;", "setCameraSupportType", "(Lcom/pudutech/freeinstall_ui/utils/CameraSupportType;)V", "deviceSize", "", "getDeviceSize", "()I", "setDeviceSize", "(I)V", "functionList", "Ljava/util/ArrayList;", "Lcom/pudutech/freeinstall_ui/utils/BusinessFunction;", "Lkotlin/collections/ArrayList;", "getFunctionList", "()Ljava/util/ArrayList;", "setFunctionList", "(Ljava/util/ArrayList;)V", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ConfigDataHelper {
    public static final ConfigDataHelper INSTANCE = new ConfigDataHelper();
    private static CameraSupportType cameraSupportType = CameraSupportType.CAMERA_ALL;
    private static ArrayList<BusinessFunction> functionList = CollectionsKt.arrayListOf(BusinessFunction.FUNCTION_ASSURES, BusinessFunction.FUNCTION_MEALS, BusinessFunction.FUNCTION_RECEPTIONIST, BusinessFunction.FUNCTION_CRUISE, BusinessFunction.FUNCTION_CHARGE);
    private static int deviceSize = 4;

    private ConfigDataHelper() {
    }

    public final CameraSupportType getCameraSupportType() {
        return cameraSupportType;
    }

    public final void setCameraSupportType(CameraSupportType cameraSupportType2) {
        Intrinsics.checkParameterIsNotNull(cameraSupportType2, "<set-?>");
        cameraSupportType = cameraSupportType2;
    }

    public final ArrayList<BusinessFunction> getFunctionList() {
        return functionList;
    }

    public final void setFunctionList(ArrayList<BusinessFunction> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        functionList = arrayList;
    }

    public final int getDeviceSize() {
        return deviceSize;
    }

    public final void setDeviceSize(int i) {
        deviceSize = i;
    }
}
