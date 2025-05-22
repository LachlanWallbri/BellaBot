package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.rpc.StatusCode;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class WatchdogTimeoutException extends ApiException {
    public static final StatusCode LOCAL_ABORTED_STATUS_CODE = new StatusCode() { // from class: com.google.api.gax.rpc.WatchdogTimeoutException.1
        @Override // com.google.api.gax.rpc.StatusCode
        public Object getTransportCode() {
            return null;
        }

        @Override // com.google.api.gax.rpc.StatusCode
        public StatusCode.Code getCode() {
            return StatusCode.Code.ABORTED;
        }
    };
    private static final long serialVersionUID = -777463630112442086L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WatchdogTimeoutException(String str, boolean z) {
        super(str, null, LOCAL_ABORTED_STATUS_CODE, z);
    }
}
