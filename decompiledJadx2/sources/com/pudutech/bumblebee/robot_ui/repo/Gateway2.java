package com.pudutech.bumblebee.robot_ui.repo;

import com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean;
import com.pudutech.disinfect.baselib.network.response.Gateway;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CallSettingRepo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0004¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/repo/Gateway2;", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/radiobtntv/IRadioBtnTvBean;", "mGateway", "Lcom/pudutech/disinfect/baselib/network/response/Gateway;", "(Lcom/pudutech/disinfect/baselib/network/response/Gateway;)V", "check", "", "getCheck", "()Z", "setCheck", "(Z)V", "getMGateway", "()Lcom/pudutech/disinfect/baselib/network/response/Gateway;", "setMGateway", "getName", "", "isCheck", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class Gateway2 implements IRadioBtnTvBean {
    private boolean check;
    private Gateway mGateway;

    public Gateway2(Gateway mGateway) {
        Intrinsics.checkParameterIsNotNull(mGateway, "mGateway");
        this.mGateway = mGateway;
    }

    public final Gateway getMGateway() {
        return this.mGateway;
    }

    public final void setMGateway(Gateway gateway) {
        Intrinsics.checkParameterIsNotNull(gateway, "<set-?>");
        this.mGateway = gateway;
    }

    public final boolean getCheck() {
        return this.check;
    }

    public final void setCheck(boolean z) {
        this.check = z;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean
    public boolean isCheck() {
        return this.check;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.IRadioBtnTvBean
    public String getName() {
        return this.mGateway.getName();
    }
}
