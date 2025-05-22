package com.pudutech.voiceinteraction.component.cmd;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: ICmdAdatper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/cmd/ICmdAdatper;", ExifInterface.GPS_DIRECTION_TRUE, "", "transformationCmd", "Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "data", "(Ljava/lang/Object;)Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface ICmdAdatper<T> {
    CmdBean transformationCmd(T data);
}
