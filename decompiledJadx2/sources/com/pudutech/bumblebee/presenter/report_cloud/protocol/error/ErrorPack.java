package com.pudutech.bumblebee.presenter.report_cloud.protocol.error;

import com.pudutech.bumblebee.presenter.report_cloud.protocol.Head;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class ErrorPack extends Head {
    public double battery = 0.0d;
    public List<ErrorString> errors = new ArrayList();

    public ErrorPack() {
        this.type = "error";
    }
}
