package com.pudutech.bumblebee.presenter.report_cloud.protocol.selfcheck;

import com.pudutech.bumblebee.presenter.report_cloud.protocol.Head;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class Boot extends Head {
    public double battery = 0.0d;
    public String systemid = "";
    public List<CheckResult> checkResult = new ArrayList();

    public Boot() {
        this.type = "boot";
    }
}
