package com.pudutech.remotemaintenance.bean;

import com.pudutech.remotemaintenance.config.IoTConfig;
import kotlin.Metadata;

/* compiled from: CFile.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/bean/CFile;", "", "()V", IoTConfig.PARAM_FILE_URI, "", "getFileUri", "()Ljava/lang/String;", "setFileUri", "(Ljava/lang/String;)V", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public class CFile {
    private String fileUri;

    public final String getFileUri() {
        return this.fileUri;
    }

    public final void setFileUri(String str) {
        this.fileUri = str;
    }
}
