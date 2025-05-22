package com.pudutech.remotemaintenance.interf;

import com.pudutech.remotemaintenance.bean.CFile;
import com.pudutech.remotemaintenance.listener.OnUploadFileListener;
import kotlin.Metadata;

/* compiled from: IFileUploadInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J!\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00018\u00002\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¢\u0006\u0002\u0010\t¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/interf/IFileUploadInterface;", "F", "Lcom/pudutech/remotemaintenance/bean/CFile;", "", "uploadFile", "", "file", "listener", "Lcom/pudutech/remotemaintenance/listener/OnUploadFileListener;", "(Lcom/pudutech/remotemaintenance/bean/CFile;Lcom/pudutech/remotemaintenance/listener/OnUploadFileListener;)V", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface IFileUploadInterface<F extends CFile> {
    void uploadFile(F file, OnUploadFileListener listener);
}
