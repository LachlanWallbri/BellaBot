package com.liulishuo.okdownload.core.exception;

import java.io.IOException;

/* loaded from: classes4.dex */
public class NetworkPolicyException extends IOException {
    public NetworkPolicyException() {
        super("Only allows downloading this task on the wifi network type!");
    }
}
